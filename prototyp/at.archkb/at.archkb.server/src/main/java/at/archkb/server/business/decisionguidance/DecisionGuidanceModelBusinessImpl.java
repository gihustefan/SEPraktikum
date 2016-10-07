package at.archkb.server.business.decisionguidance;

import at.archkb.server.business.coredata.CoreDataBusiness;
import at.archkb.server.business.user.UserBusiness;
import at.archkb.server.exception.DecisionGuidanceModelNotFoundException;
import at.archkb.server.exception.DecisionGuidanceModelNotPermittedException;
import at.archkb.server.neo4jentity.dto.decisionguidance.*;
import at.archkb.server.neo4jentity.node.*;
import at.archkb.server.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import at.archkb.server.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import at.archkb.server.repository.node.decisionguidance.DecisionGuidanceModelAttributeRepository;
import at.archkb.server.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.archkb.server.repository.node.decisionguidance.designoption.DesignOptionRepository;
import at.archkb.server.repository.relationship.decisionguidance.DGMAttributeRelationshipRepository;
import at.archkb.server.repository.relationship.decisionguidance.DGMHasGuidanceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@Component
public class DecisionGuidanceModelBusinessImpl implements DecisionGuidanceModelBusiness {

    @Autowired
    private DecisionGuidanceModelRepository decisionGuidanceModelRepository;

    @Autowired
    private DesignOptionRepository designOptionRepository;

    @Autowired
    private DGMAttributeRelationshipRepository dgmAttributeRelationshipRepository;

    @Autowired
    private DecisionGuidanceModelAttributeRepository decisionGuidanceModelAttributeRepository;

    @Autowired
    private DGMHasGuidanceModelRepository dgmHasGuidanceModelRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CoreDataBusiness coreDataBusiness;

    @Autowired
    private ServletContext servletContext;


    @Override
    @Transactional(readOnly = true)
    public DecisionGuidanceModelDto getDecisionGuidanceModel(Long id) {
        // TODO: check other "unvalid" values
        // return empty DecisionGuidanceModel
        if (id < 0) {
            throw new DecisionGuidanceModelNotFoundException();
        }

        DecisionGuidanceModel decisionGuidanceModel = decisionGuidanceModelRepository.findOne(id, 1);

        // When not found -> just return an empty DecisionGuidanceModel POJO
        if (decisionGuidanceModel == null) {
            throw new DecisionGuidanceModelNotFoundException();
        }

        // Fetching the authenticated user
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the user which requested the DecisionGuidanceModel can actually see it
        boolean isCreator;
        boolean isAdmin;
        if(authenticatedUser == null) {
            isCreator = false;
            isAdmin = false;
        } else {
            isCreator = decisionGuidanceModel.getCreator() != null  && authenticatedUser.getId() == decisionGuidanceModel.getCreator().getId();
            isAdmin = authenticatedUser.getAuthorities() != null && authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        }

        // Either the DecisionGuidanceModel isPublished, The user is the creator or the user is an admin
        boolean canSee = decisionGuidanceModel.isPublished() || isCreator || isAdmin;

        if (!canSee)
            throw new DecisionGuidanceModelNotPermittedException();

        // Additionally must add the tradeoffs as well (would be level 2)
        //decisionGuidanceModel.getTradeoffs().forEach(hasTradeoff -> hasTradeoff.setEndNode(neo4jOperations.load(Tradeoff.class, hasTradeoff.getEndNode().getId(), 1)));

        decisionGuidanceModel.getDesignOptions().forEach(hasDesignOption -> hasDesignOption.setEndNode(designOptionRepository.findOne(hasDesignOption.getEndNode().getId(), 1)));

        return new DecisionGuidanceModelDto(decisionGuidanceModel);
    }

    @Override
    @Transactional
    public DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel) {
        // Setting the owner of the decisionGuidanceModel -> the authenticated user
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        DecisionGuidanceModel newDecisionGuidanceModel = new DecisionGuidanceModel(user);

        if (decisionGuidanceModel != null) {
            newDecisionGuidanceModel.setTitle(decisionGuidanceModel.getTitle());
            newDecisionGuidanceModel.setDescription(decisionGuidanceModel.getDescription());
        }

        // On creation the New DecisionGuidanceModel must be unpublished
        newDecisionGuidanceModel.setPublished(false);

        return new DecisionGuidanceModelDto(decisionGuidanceModelRepository.save(newDecisionGuidanceModel));
    }

    @Override
    public DecisionGuidanceModelDto updateDecisionGuidanceModelProperties(Long id, DecisionGuidanceModelChangeRequestDto newValues) {
        DecisionGuidanceModel loaded = decisionGuidanceModelRepository.findOne(id);
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

        return new DecisionGuidanceModelDto(decisionGuidanceModelRepository.save(loaded));
    }

    @Override
    public void deleteDecisionGuidanceModel(Long id) {
        decisionGuidanceModelRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DecisionGuidanceModelPageableDto getNewestDecisionGuidanceModels(Integer page, Integer size, boolean withUnpublished) {
        Assert.notNull(page);
        Assert.notNull(size);

        int skip = page * size;

        // Fetching the Objects
        List<DecisionGuidanceModelDto> decisionGuidanceModelPage = withUnpublished ? decisionGuidanceModelRepository.findNewestDecisionGuidanceModels(skip, size) : decisionGuidanceModelRepository.findNewestPublishedDecisionGuidanceModels(skip, size);

        // Fetching total Count
        Long totalCount = withUnpublished ? decisionGuidanceModelRepository.countDecisionGuidanceModels() : decisionGuidanceModelRepository.countPublishedDecisionGuidanceModels();

        return new DecisionGuidanceModelPageableDto(totalCount, decisionGuidanceModelPage);
    }

    @Override
    public DecisionGuidanceModelPageableDto getUserDecisionGuidanceModels(Long idUser, Integer page, Integer size) {
        Assert.notNull(idUser);
        Assert.notNull(page);
        Assert.notNull(size);

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the unpublished are fetched as well
        // e.g. when user is owner or user is admin
        boolean withUnpublished = authenticatedUser == null ? false : authenticatedUser.getId().equals(idUser) || authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        List<DecisionGuidanceModelDto> decisionGuidanceModels = withUnpublished ? decisionGuidanceModelRepository.findAllByUserId(idUser, page * size, size) : decisionGuidanceModelRepository.findPublishedByUserId(idUser, page * size, size);

        if (decisionGuidanceModels == null || decisionGuidanceModels.size() == 0) {
            return new DecisionGuidanceModelPageableDto();
        }

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        Long countUserProfiles = withUnpublished ? decisionGuidanceModelRepository.countAllDecisionGuidanceModelsOfUser(idUser) : decisionGuidanceModelRepository.countPublishedDecisionGuidanceModelsOfUser(idUser);

        return new DecisionGuidanceModelPageableDto(countUserProfiles, decisionGuidanceModels);
    }

    @Override
    public <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRealtionDto createRelation(Long idDecisionGuidanceModel, DecisionGuidanceModelRealtionDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            dgmAttributeRelationshipRepository.delete(dgmAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        DecisionGuidanceModel decisionGuidanceModel = decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 0);

        // ArchProfile is not allowed to be null
        Assert.notNull(decisionGuidanceModel);

        A toNode = decisionGuidanceModelAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = coreDataBusiness.createCoreData(attributeInfo.getName(), attributeInfo.getDefinition(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(decisionGuidanceModel);
            newRelationship.setEndNode(toNode);
            // Setting the definition of the Relation to the same as the toNode (than we dont have to worry about changes in the toNode -> users can alter the definition of the properties themselves!)
            newRelationship.setDefinition(toNode.getDefinition());
            newRelationship.setDescription(attributeInfo.getDescription());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = dgmAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new DecisionGuidanceModelRealtionDto(newRelationship);

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
    public <T extends DGMAttributeRelationship<? extends CoreData>> DecisionGuidanceModelRealtionDto updateExistingRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRealtionDto newValues, Class<T> clazz) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = dgmAttributeRelationshipRepository.findById(clazz, idDecisionGuidanceModelRelation);
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

            T loadedRelationSameOrdering = dgmAttributeRelationshipRepository.findByOrdering(clazz, idDecisionGuidanceModel, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new DecisionGuidanceModelRealtionDto(loadedRelation);
    }

    @Override
    public <T extends DGMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, Class<T> relationClass) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(idDecisionGuidanceModelRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T decisionGuidanceModelRelationToDelete = dgmAttributeRelationshipRepository.findById(relationClass, idDecisionGuidanceModelRelation);

        if (decisionGuidanceModelRelationToDelete == null)
            return;

        dgmAttributeRelationshipRepository.delete(decisionGuidanceModelRelationToDelete);

        // TODO Reorder
    }

    @Override
    public DecisionGuidanceModelRelatedGuidanceModelsDto createGuidanceModelRelation(Long idDecisionGuidanceModel, DecisionGuidanceModelRelatedGuidanceModelsDto guidanceModelInfo) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(guidanceModelInfo);

        // Fetching the current user -> that wants to create the new Tradeoff
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        // Means that an existing tradeoff relation was altered
        // must delete this one and create a new one based on the given information!
        if (guidanceModelInfo.getId() != null) {
            dgmHasGuidanceModelRepository.delete(guidanceModelInfo.getId());
        }

        // Fetching ArchProfile
        DecisionGuidanceModel startDecisionGuidanceModel = decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 0);
        Assert.notNull(startDecisionGuidanceModel);

        DecisionGuidanceModel endDecisionGuidanceModel = decisionGuidanceModelRepository.findOne(guidanceModelInfo.getIdAttribute(), 0);
        Assert.notNull(endDecisionGuidanceModel);

        HasRelatedGuidanceModels hasRelatedGuidanceModels = new HasRelatedGuidanceModels(startDecisionGuidanceModel, endDecisionGuidanceModel, guidanceModelInfo.getDescription(), guidanceModelInfo.getOrdering());
        hasRelatedGuidanceModels = dgmHasGuidanceModelRepository.save(hasRelatedGuidanceModels);

        // Reordering Relations after adding
        // TODO REORDER

        return new DecisionGuidanceModelRelatedGuidanceModelsDto(hasRelatedGuidanceModels);
    }

    @Override
    public DecisionGuidanceModelRelatedGuidanceModelsDto updateExistingGuidanceModelRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelatedGuidanceModelsDto newValues) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(idDecisionGuidanceModelRelation);

        // Fetching the hasTradeoff from DB
        HasRelatedGuidanceModels hasRelatedGuidanceModels = dgmHasGuidanceModelRepository.findOne(idDecisionGuidanceModelRelation);
        Assert.notNull(hasRelatedGuidanceModels);

        // Setting new Description
        if (newValues.getDescription() != null) {
            hasRelatedGuidanceModels.setDescription(newValues.getDescription());
        }

        if (newValues.getOrdering() != null) {

            HasRelatedGuidanceModels guidanceModelRelationSameOrdering = dgmHasGuidanceModelRepository.findByOrdering(idDecisionGuidanceModel, newValues.getOrdering());

            if (guidanceModelRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            guidanceModelRelationSameOrdering.setOrdering(hasRelatedGuidanceModels.getOrdering());
            hasRelatedGuidanceModels.setOrdering(newValues.getOrdering());

            // TODO REorder
        }

        return new DecisionGuidanceModelRelatedGuidanceModelsDto(hasRelatedGuidanceModels);
    }

    @Override
    public void deleteGuidanceModelRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(idDecisionGuidanceModelRelation);

        // Fetch the relation that should be deleted
        HasRelatedGuidanceModels hasRelatedGuidanceModels = dgmHasGuidanceModelRepository.findOne(idDecisionGuidanceModelRelation);

        if (hasRelatedGuidanceModels == null)
            return;

        dgmHasGuidanceModelRepository.delete(hasRelatedGuidanceModels);

        // TODO Reorder
    }
}
