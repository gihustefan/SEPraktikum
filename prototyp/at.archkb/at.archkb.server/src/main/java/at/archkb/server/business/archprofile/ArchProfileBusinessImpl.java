package at.archkb.server.business.archprofile;

import at.archkb.server.business.coredata.CoreDataBusiness;
import at.archkb.server.business.user.UserBusiness;
import at.archkb.server.exception.ArchProfileNotFoundException;
import at.archkb.server.exception.ArchProfileNotPermittedException;
import at.archkb.server.neo4jentity.dto.*;
import at.archkb.server.neo4jentity.node.*;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;
import at.archkb.server.neo4jentity.relationship.HasTradeoff;
import at.archkb.server.repository.node.ArchProfileAttributeRepository;
import at.archkb.server.repository.node.ArchProfileRepository;
import at.archkb.server.repository.node.DiagramRepository;
import at.archkb.server.repository.relationship.APAttributeRelationshipRepository;
import at.archkb.server.repository.relationship.HasTradeoffRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class ArchProfileBusinessImpl implements ArchProfileBusiness {

    @Autowired
    private ArchProfileRepository archProfileRepository;

    @Autowired
    private APAttributeRelationshipRepository apAttributeRelationshipRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CoreDataBusiness coreDataBusiness;

    @Autowired
    private ArchProfileAttributeRepository archProfileAttributeRepository;

    @Autowired
    private HasTradeoffRepository hasTradeoffRepository;

    @Autowired
    private DiagramRepository diagramRepository;

    @Autowired
    private ServletContext servletContext;

    @Override
    @Transactional(readOnly = true)
    public ArchProfileDto getArchProfile(Long id) {

        // TODO: check other "unvalid" values
        // return empty ArchProfile
        if (id < 0) {
            throw new ArchProfileNotFoundException();
        }

        ArchProfile archProfile = archProfileRepository.findOne(id, 1);

        // When not found -> just return an empty ArchProfile POJO
        if (archProfile == null) {
            throw new ArchProfileNotFoundException();
        }

        // Fetching the authenticated user
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the user which requested the ArchProfile can actually see it
        boolean isCreator;
        boolean isAdmin;
        if(authenticatedUser == null) {
            isCreator = false;
            isAdmin = false;
        } else {
            isCreator = archProfile.getCreator() != null  && authenticatedUser.getId() == archProfile.getCreator().getId();
            isAdmin = authenticatedUser.getAuthorities() != null && authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        }

        // Either the archProfile isPublished, The user is the creator or the user is an admin
        boolean canSee = archProfile.isPublished() || isCreator || isAdmin;

        if (!canSee)
            throw new ArchProfileNotPermittedException();

        // Additionally must add the tradeoffs as well (would be level 2)
        archProfile.getTradeoffs().forEach(hasTradeoff -> hasTradeoff.setEndNode(neo4jOperations.load(Tradeoff.class, hasTradeoff.getEndNode().getId(), 1)));

        return new ArchProfileDto(archProfile);
    }

    @Override
    @Transactional
    public ArchProfileDto createArchProfile(ArchProfileChangeRequestDto archProfile) {
        // Setting the owner of the archProfile -> the authenticated user
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        ArchProfile newArchProfile = new ArchProfile(user);

        if (archProfile != null) {
            newArchProfile.setTitle(archProfile.getTitle());
            newArchProfile.setDescription(archProfile.getDescription());
        }

        // On creation the New ArchProfile must be unpublished
        newArchProfile.setPublished(false);

        return new ArchProfileDto(archProfileRepository.save(newArchProfile));
    }

    @Override
    @Transactional
    public void deleteArchProfile(Long id) {
        archProfileRepository.delete(id);
    }

    @Override
    @Transactional
    public ArchProfileDto updateArchProfileProperties(Long id, ArchProfileChangeRequestDto newValues) {
        ArchProfile loaded = archProfileRepository.findOne(id);
        Assert.notNull(loaded);

        if (newValues.getTitle() != null) {
            loaded.setTitle(newValues.getTitle());
        }

        if (newValues.getDescription() != null) {
            loaded.setDescription(newValues.getDescription());
        }

        if (newValues.getPublished() != null) {
            loaded.setPublished(newValues.getPublished());
        }

        return new ArchProfileDto(archProfileRepository.save(loaded));
    }

    @Override
    @Transactional
    public <T extends APAttributeRelationship<? extends CoreData>> ArchProfileRelationDto updateExistingRelationAttribute(
            Long idArchProfile, Long idArchProfileRelation, ArchProfileRelationDto newValues, Class<T> clazz) {

        // Fetch AP Relation from Graph DB
        T loadedRelation = apAttributeRelationshipRepository.findById(clazz, idArchProfileRelation);
        Assert.notNull(loadedRelation);

        // Setting the new value for the description
        if (newValues.getDescription() != null)
            loadedRelation.setDescription(newValues.getDescription());

        // Setting the new value for the definition
        if (newValues.getDefinition() != null)
            loadedRelation.setDefinition(newValues.getDefinition());

        // Setting the ordering -> must change the ordering values of other AP
        // Relationships as well
        if (newValues.getOrdering() != null) {

            T loadedRelationSameOrdering = apAttributeRelationshipRepository.findByOrdering(clazz, idArchProfile, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new ArchProfileRelationDto(loadedRelation);
    }

    @Override
    @Transactional
    public <T extends APAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idArchProfile, Long idArchProfileRelation, Class<T> relationClass) {
        Assert.notNull(idArchProfile);
        Assert.notNull(idArchProfileRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T archProfileRelationToDelete = apAttributeRelationshipRepository.findById(relationClass, idArchProfileRelation);

        if (archProfileRelationToDelete == null)
            return;

        apAttributeRelationshipRepository.delete(archProfileRelationToDelete);

        // TODO Reorder

    }


    @Transactional
    public <T extends APAttributeRelationship<A>, A extends CoreData> ArchProfileRelationDto createRelation(Long idArchProfile, ArchProfileRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idArchProfile);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            apAttributeRelationshipRepository.delete(apAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        ArchProfile archProfile = archProfileRepository.findOne(idArchProfile, 0);

        // ArchProfile is not allowed to be null
        Assert.notNull(archProfile);

        A toNode = archProfileAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = coreDataBusiness.createCoreData(attributeInfo.getName(), attributeInfo.getDefinition(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(archProfile);
            newRelationship.setEndNode(toNode);
            // Setting the definition of the Relation to the same as the toNode (than we dont have to worry about changes in the toNode -> users can alter the definition of the properties themselves!)
            newRelationship.setDefinition(toNode.getDefinition());
            newRelationship.setDescription(attributeInfo.getDescription());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = apAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new ArchProfileRelationDto(newRelationship);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional
    public DiagramDto createDiagram(Long idArchProfile, MultipartFile diagramFile) {
        Assert.notNull(idArchProfile);
        Assert.notNull(diagramFile);

        ArchProfile archProfile = archProfileRepository.findOne(idArchProfile, 0);
        Assert.notNull(archProfile);

        try {
            String filePath = uploadDiagramFile(diagramFile, idArchProfile);

            Diagram diagram = new Diagram();
            diagram.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            diagram.setCreator(userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            diagram.setLastModified(diagram.getCreationDate());
            diagram.setPath(filePath);

            // Adding the newly created Diagram to the ArchProfile
            archProfile.getDiagrams().add(diagramRepository.save(diagram));

            archProfileRepository.save(archProfile);

            return new DiagramDto(diagram);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional
    public DiagramDto updateDiagram(Long idArchProfile, Long idDiagram, DiagramDto newValues) {
        Assert.notNull(idArchProfile);
        Assert.notNull(idDiagram);
        Assert.notNull(newValues);

        Diagram diagram = diagramRepository.findOne(idDiagram, 0);

        if (diagram == null)
            return null;

        // Updating the Diagram Description if set
        if (newValues.getDescription() != null) {
            diagram.setDescription(newValues.getDescription());
        }

        return new DiagramDto(diagram);
    }

    @Override
    @Transactional
    public void deleteDiagram(Long idArchProfile, Long idDiagram) {
        Assert.notNull(idArchProfile);
        Assert.notNull(idDiagram);

        ArchProfile archProfile = archProfileRepository.findOne(idArchProfile, 0);
        Assert.notNull(archProfile);

        Diagram diagram = diagramRepository.findOne(idDiagram, 0);
        Assert.notNull(diagram);

        diagramRepository.delete(diagram);
        // Remove the diagram from the ArchProfile Diagrams
        archProfile.getDiagrams().remove(diagram);
        archProfileRepository.save(archProfile);
    }

    /**
     * Creating a new Diagram file
     *
     * @param file Which file (byte[])
     * @return The relative path of the file -> so it is accessible over the browser
     * @throws IOException when reading the file
     */
    private String uploadDiagramFile(MultipartFile file, Long idArchProfile) throws IOException {
        byte fileBytes[] = file.getBytes();

        // Creating a fileName based on the file content
        String newFileName = DigestUtils.md5Hex(fileBytes) + "." + file.getContentType().split("/")[1];
        String[] folders = {"images", "archprofiles", idArchProfile.toString(), "diagrams"};

        // Generating the uploadPath (absolute)
        String uploadPath = servletContext.getRealPath("") + File.separator + "static" + File.separator;
        for (String folder : folders) {
            uploadPath += folder + File.separator;
        }
        uploadPath += newFileName;

        // Writing the file
        FileUtils.writeByteArrayToFile(new File(uploadPath), fileBytes);

        // Generating the relativePath (for referencing)
        String relativePath = "";
        for (String folder : folders) {
            relativePath += folder + "/";
        }
        relativePath += newFileName;

        return relativePath;
    }

    @Override
    @Transactional
    public ArchProfileTradeoffRelationDto updateExistingTradeoffRelationAttribute(Long idArchProfile, Long idArchProfileTradeoffRelation, ArchProfileTradeoffRelationDto newValues) {
        Assert.notNull(idArchProfile);
        Assert.notNull(idArchProfileTradeoffRelation);

        // Fetching the hasTradeoff from DB
        HasTradeoff hasTradeoff = hasTradeoffRepository.findOne(idArchProfileTradeoffRelation);
        Assert.notNull(hasTradeoff);

        // Setting new Description
        if (newValues.getDescription() != null) {
            hasTradeoff.setDescription(newValues.getDescription());
        }

        // Setting new Definition Over
        if (newValues.getDefinitionOver() != null) {
            hasTradeoff.setDefinitionOver(newValues.getDefinitionOver());
        }

        // Setting new Definition Under
        if (newValues.getDefinitionUnder() != null) {
            hasTradeoff.setDefinitionUnder(newValues.getDefinitionUnder());
        }

        if (newValues.getOrdering() != null) {

            HasTradeoff tradeoffRelationSameOrdering = hasTradeoffRepository.findByOrdering(idArchProfile, newValues.getOrdering());

            if (tradeoffRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            tradeoffRelationSameOrdering.setOrdering(hasTradeoff.getOrdering());
            hasTradeoff.setOrdering(newValues.getOrdering());

            // TODO REorder
        }

        return new ArchProfileTradeoffRelationDto(hasTradeoff);
    }

    @Override
    @Transactional
    public void deleteArchProfileTradeoffRelation(Long idArchProfile, Long idArchProfileTradeoffRelation) {
        Assert.notNull(idArchProfile);
        Assert.notNull(idArchProfileTradeoffRelation);

        HasTradeoff relationToDelete = hasTradeoffRepository.findOne(idArchProfileTradeoffRelation);

        if (relationToDelete == null)
            return;

        hasTradeoffRepository.delete(relationToDelete);

        // Reorder the ArchProfile Tradeoffs
        // TODO REORDER
    }

    @Override
    @Transactional
    public ArchProfileTradeoffRelationDto createTradeoffRelation(Long idArchProfile, ArchProfileTradeoffRelationDto tradeoffInfo) {
        Assert.notNull(idArchProfile);
        Assert.notNull(tradeoffInfo);
        Assert.notNull(tradeoffInfo.getIdAttributeOver());
        Assert.notNull(tradeoffInfo.getIdAttributeUnder());

        // Fetching the current user -> that wants to create the new Tradeoff
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        // Means that an existing tradeoff relation was altered
        // must delete this one and create a new one based on the given information!
        if (tradeoffInfo.getId() != null) {
            hasTradeoffRepository.delete(tradeoffInfo.getId());
        }

        // Fetching ArchProfile
        ArchProfile archProfile = archProfileRepository.findOne(idArchProfile, 0);
        Assert.notNull(archProfile);

        // Fetching the tradeoff
        // A new one will be created if a similar tradeoff is does not exist
        Tradeoff tradeoff = coreDataBusiness.getTradeoffByTradeoffItems(tradeoffInfo.getIdAttributeOver(), tradeoffInfo.getIdAttributeUnder());

        // Creating the new hasTradeoff Relationship
        HasTradeoff hasTradeoff = new HasTradeoff(archProfile, tradeoff, tradeoffInfo.getDescription(), tradeoffInfo.getOrdering(), tradeoff.getTradeoffItemOver().getDefinition(), tradeoff.getTradeoffItemUnder().getDefinition());

        hasTradeoff = hasTradeoffRepository.save(hasTradeoff);

        // Reordering Relations after adding
        // TODO REORDER

        return new ArchProfileTradeoffRelationDto(hasTradeoff);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchProfilePageableDto getNewestArchProfiles(Integer page, Integer size, boolean withUnpublished) {
        Assert.notNull(page);
        Assert.notNull(size);

        int skip = page * size;

        // Fetching the Objects
        List<ArchProfileDto> archProfilePage = withUnpublished ? archProfileRepository.findNewestArchProfiles(skip, size) : archProfileRepository.findNewestPublishedArchProfiles(skip, size);

        // Fetching tootal Count
        Long totalCount = withUnpublished ? archProfileRepository.countArchProfiles() : archProfileRepository.countPublishedArchProfiles();

        return new ArchProfilePageableDto(totalCount, archProfilePage);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchProfilePageableDto getUserArchProfiles(Long idUser, Integer page, Integer size) {
        Assert.notNull(idUser);
        Assert.notNull(page);
        Assert.notNull(size);

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the unpublished are fetched as well
        // e.g. when user is owner or user is admin
        boolean withUnpublished = authenticatedUser == null ? false : authenticatedUser.getId().equals(idUser) || authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        List<ArchProfileDto> archProfiles = withUnpublished ? archProfileRepository.findAllByUserId(idUser, page * size, size) : archProfileRepository.findPublishedByUserId(idUser, page * size, size);

        if (archProfiles == null || archProfiles.size() == 0) {
            return new ArchProfilePageableDto();
        }

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        Long countUserProfiles = withUnpublished ? archProfileRepository.countAllArchProfilesOfUser(idUser) : archProfileRepository.countPublishedArchProfilesOfUser(idUser);

        return new ArchProfilePageableDto(countUserProfiles, archProfiles);
    }
}