package at.decisionexpert.business.decisiondocumentation;

import at.decisionexpert.business.coredata.CreateCoreDataImpl;
import at.decisionexpert.business.coredata.DDMCoreDataBusiness;
import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.exception.DecisionDocumentationNotFoundException;
import at.decisionexpert.exception.DecisionDocumentationNotPermittedException;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.*;
import at.decisionexpert.neo4jentity.node.*;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasEffectedDocumentationModel;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasTradeoff;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.decisiondocumentation.DecisionDocumentationRepository;
import at.decisionexpert.repository.relationship.decisiondocumentation.DDMAttributeRelationshipRepository;
import at.decisionexpert.repository.relationship.decisiondocumentation.DDMHasEffectedDocumentationRepository;
import at.decisionexpert.repository.relationship.decisiondocumentation.DDMHasTradeoffRepository;
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
 * Created by stefanhaselboeck on 06.10.16.
 */
@Component
public class DecisionDocumentationBusinessImpl implements DecisionDocumentationBusiness {

    @Autowired
    private DecisionDocumentationRepository decisionDocumentationRepository;

    @Autowired
    private DDMAttributeRelationshipRepository ddmAttributeRelationshipRepository;

    @Autowired
    private NodeAttributeRepository nodeAttributeRepository;

    @Autowired
    private DDMHasEffectedDocumentationRepository ddmHasEffectedDocumentationRepository;

    @Autowired
    private DDMHasTradeoffRepository ddmHasTradeoffRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private DDMCoreDataBusiness ddmCoreDataBusiness;

    @Autowired
    private CreateCoreDataImpl createCoreDataImpl;

    @Autowired
    private ServletContext servletContext;

    @Override
    @Transactional(readOnly = true)
    public DecisionDocumentationModelDto getDecisionDocumentationModel(Long id) throws DecisionDocumentationNotFoundException {
        // TODO: check other "unvalid" values
        if (id < 0) {
            throw new DecisionDocumentationNotFoundException();
        }

        DecisionDocumentationModel decisionDocumentationModel = decisionDocumentationRepository.findOne(id, 1);

        // When not found -> just return an empty DecisionDocumentationModel POJO
        if (decisionDocumentationModel == null) {
            throw new DecisionDocumentationNotFoundException();
        }

        // Fetching the authenticated user
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the user which requested the DecisionDocumentationModel can actually see it
        boolean isCreator;
        boolean isAdmin;
        if(authenticatedUser == null) {
            isCreator = false;
            isAdmin = false;
        } else {
            isCreator = decisionDocumentationModel.getCreator() != null  && authenticatedUser.getId() == decisionDocumentationModel.getCreator().getId();
            isAdmin = authenticatedUser.getAuthorities() != null && authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        }

        // Either the DecisionDocumentationModel isPublished, The user is the creator or the user is an admin
        boolean canSee = decisionDocumentationModel.isPublished() || isCreator || isAdmin;

        if (!canSee)
            throw new DecisionDocumentationNotPermittedException();

        // Additionally must add the tradeoffs as well (would be level 2)
        decisionDocumentationModel.getTradeoffs().forEach(hasTradeoff -> hasTradeoff.setEndNode(neo4jOperations.load(Tradeoff.class, hasTradeoff.getEndNode().getId(), 1)));

        return new DecisionDocumentationModelDto(decisionDocumentationModel);
    }

    @Override
    @Transactional
    public DecisionDocumentationModelDto createDecisionDocumentationModel(DecisionDocumentationModelChangeRequestDto decisionDocumentationModel) {
        // Setting the owner of the decisionDocumentationModel -> the authenticated user
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        DecisionDocumentationModel newDecisionDocumentationModel = new DecisionDocumentationModel(user);

        if (decisionDocumentationModel != null) {
            newDecisionDocumentationModel.setTitle(decisionDocumentationModel.getTitle());
            newDecisionDocumentationModel.setDescription(decisionDocumentationModel.getDescription());
        }

        // On creation the New DecisionDocumentationModel must be unpublished
        newDecisionDocumentationModel.setPublished(false);

        return new DecisionDocumentationModelDto(decisionDocumentationRepository.save(newDecisionDocumentationModel));
    }

    @Override
    public DecisionDocumentationModelDto updateDecisionDocumentationModelProperties(Long id, DecisionDocumentationModelChangeRequestDto newValues) {
        DecisionDocumentationModel loaded = decisionDocumentationRepository.findOne(id);
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

        return new DecisionDocumentationModelDto(decisionDocumentationRepository.save(loaded));
    }

    @Override
    public void deleteDecisionDocumentationModel(Long id) {
        decisionDocumentationRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DecisionDocumentationModelPageableDto getNewestDecisionDocumentationModels(Integer page, Integer size, boolean withUnpublished) {
        Assert.notNull(page);
        Assert.notNull(size);

        int skip = page * size;

        // Fetching the Objects
        List<DecisionDocumentationModelDto> decisionDocumentationModelPage = withUnpublished ? decisionDocumentationRepository.findNewestDecisionDocumentationModels(skip, size) : decisionDocumentationRepository.findNewestPublishedDecisionDocumentationModels(skip, size);

        // Fetching total Count
        Long totalCount = withUnpublished ? decisionDocumentationRepository.countDecisionDocumentationModels() : decisionDocumentationRepository.countPublishedDecisionDocumentationModels();

        return new DecisionDocumentationModelPageableDto(totalCount, decisionDocumentationModelPage);
    }

    @Override
    public DecisionDocumentationModelPageableDto getUserDecisionDocumentationModels(Long idUser, Integer page, Integer size) {
        Assert.notNull(idUser);
        Assert.notNull(page);
        Assert.notNull(size);

        // If the authenticated User requests his own DecisionDocumentations => also load the unpublished ones!
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the unpublished are fetched as well
        // e.g. when user is owner or user is admin
        boolean withUnpublished = authenticatedUser == null ? false : authenticatedUser.getId().equals(idUser) || authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));

        // If the authenticated User requests his own DecisionDocumentations => also load the unpublished ones!
        List<DecisionDocumentationModelDto> decisionDocumentationModels = withUnpublished ? decisionDocumentationRepository.findAllByUserId(idUser, page * size, size) : decisionDocumentationRepository.findPublishedByUserId(idUser, page * size, size);

        if (decisionDocumentationModels == null || decisionDocumentationModels.size() == 0) {
            return new DecisionDocumentationModelPageableDto();
        }

        // If the authenticated User requests his own DecisionDocumentations => also load the unpublished ones!
        Long countUserProfiles = withUnpublished ? decisionDocumentationRepository.countAllDecisionDocumentationModelsOfUser(idUser) : decisionDocumentationRepository.countPublishedDecisionDocumentationModelsOfUser(idUser);

        return new DecisionDocumentationModelPageableDto(countUserProfiles, decisionDocumentationModels);
    }

    @Override
    public <T extends DDMAttributeRelationship<A>, A extends CoreData> DecisionDocumentationModelRelationDto createRelation(Long idDecisionDocumentationModel, DecisionDocumentationModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            ddmAttributeRelationshipRepository.delete(ddmAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        DecisionDocumentationModel decisionDocumentationModel = decisionDocumentationRepository.findOne(idDecisionDocumentationModel, 0);

        // DecisionDocumentation is not allowed to be null
        Assert.notNull(decisionDocumentationModel);

        A toNode = nodeAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), attributeInfo.getDefinition(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(decisionDocumentationModel);
            newRelationship.setEndNode(toNode);
            // Setting the definition of the Relation to the same as the toNode (than we dont have to worry about changes in the toNode -> users can alter the definition of the properties themselves!)
            newRelationship.setDefinition(toNode.getDefinition());
            newRelationship.setDescription(attributeInfo.getDescription());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = ddmAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new DecisionDocumentationModelRelationDto(newRelationship);

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
    public <T extends DDMAttributeRelationship<? extends CoreData>> DecisionDocumentationModelRelationDto updateExistingRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationModelRelation, DecisionDocumentationModelRelationDto newValues, Class<T> clazz) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = ddmAttributeRelationshipRepository.findById(clazz, idDecisionDocumentationModelRelation);
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

            T loadedRelationSameOrdering = ddmAttributeRelationshipRepository.findByOrdering(clazz, idDecisionDocumentationModel, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new DecisionDocumentationModelRelationDto(loadedRelation);
    }

    @Override
    public <T extends DDMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationModelRelation, Class<T> relationClass) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(idDecisionDocumentationModelRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T decisionDocumentationModelRelationToDelete = ddmAttributeRelationshipRepository.findById(relationClass, idDecisionDocumentationModelRelation);

        if (decisionDocumentationModelRelationToDelete == null)
            return;

        ddmAttributeRelationshipRepository.delete(decisionDocumentationModelRelationToDelete);

        // TODO Reorder
    }


//EffectedDocumentationModel
    @Override
    public DecisionDocumentationModelEffectedDocumentationModelDto createEffectedDocumentationModelRelation(Long idDecisionDocumentationModel, DecisionDocumentationModelEffectedDocumentationModelDto effectedDocumentationModelInfo) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(effectedDocumentationModelInfo);

        // Fetching the current user -> that wants to create the new Tradeoff
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        // Means that an existing tradeoff relation was altered
        // must delete this one and create a new one based on the given information!
        if (effectedDocumentationModelInfo.getId() != null) {
            ddmHasEffectedDocumentationRepository.delete(effectedDocumentationModelInfo.getId());
        }

        // Fetching DecisionDocumentation
        DecisionDocumentationModel startDecisionDocumentationModel = decisionDocumentationRepository.findOne(idDecisionDocumentationModel, 0);
        Assert.notNull(startDecisionDocumentationModel);

        DecisionDocumentationModel endDecisionDocumentationModel = decisionDocumentationRepository.findOne(effectedDocumentationModelInfo.getIdAttribute(), 0);
        Assert.notNull(endDecisionDocumentationModel);

        HasEffectedDocumentationModel hasRelatedDocumentationModels = new HasEffectedDocumentationModel(startDecisionDocumentationModel, endDecisionDocumentationModel, effectedDocumentationModelInfo.getDescription(), effectedDocumentationModelInfo.getOrdering());
        hasRelatedDocumentationModels = ddmHasEffectedDocumentationRepository.save(hasRelatedDocumentationModels);

        // Reordering Relations after adding
        // TODO REORDER

        return new DecisionDocumentationModelEffectedDocumentationModelDto(hasRelatedDocumentationModels);
    }

    @Override
    public DecisionDocumentationModelEffectedDocumentationModelDto updateExistingEffectedDocumentationModelRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationEffectedDocumentationRelation, DecisionDocumentationModelEffectedDocumentationModelDto newValues) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(idDecisionDocumentationEffectedDocumentationRelation);

        // Fetching the hasTradeoff from DB
        HasEffectedDocumentationModel hasEffectedDocumentationModel = ddmHasEffectedDocumentationRepository.findOne(idDecisionDocumentationEffectedDocumentationRelation);
        Assert.notNull(hasEffectedDocumentationModel);

        // Setting new Description
        if (newValues.getDescription() != null) {
            hasEffectedDocumentationModel.setDescription(newValues.getDescription());
        }

        if (newValues.getOrdering() != null) {

            HasEffectedDocumentationModel documentationModelRelationSameOrdering = ddmHasEffectedDocumentationRepository.findByOrdering(idDecisionDocumentationModel, newValues.getOrdering());

            if (documentationModelRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            documentationModelRelationSameOrdering.setOrdering(hasEffectedDocumentationModel.getOrdering());
            hasEffectedDocumentationModel.setOrdering(newValues.getOrdering());

            // TODO REorder
        }

        return new DecisionDocumentationModelEffectedDocumentationModelDto(hasEffectedDocumentationModel);
    }

    @Override
    public void deleteEffectedDocumentationModelRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationEffectedDocumentationRelation) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(idDecisionDocumentationEffectedDocumentationRelation);

        // Fetch the relation that should be deleted
        HasEffectedDocumentationModel hasEffectedDocumentationModel = ddmHasEffectedDocumentationRepository.findOne(idDecisionDocumentationEffectedDocumentationRelation);

        if (hasEffectedDocumentationModel == null)
            return;

        ddmHasEffectedDocumentationRepository.delete(hasEffectedDocumentationModel);

        // TODO Reorder
    }


//Tradeoff
    @Override
    public DecisionDocumentationModelTradeoffRelationDto createTradeoffRelation(Long idDecisionDocumentationModel, DecisionDocumentationModelTradeoffRelationDto tradeoffInfo) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(tradeoffInfo);
        Assert.notNull(tradeoffInfo.getIdAttributeOver());
        Assert.notNull(tradeoffInfo.getIdAttributeUnder());

        // Fetching the current user -> that wants to create the new Tradeoff
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        // Means that an existing tradeoff relation was altered
        // must delete this one and create a new one based on the given information!
        if (tradeoffInfo.getId() != null) {
            ddmHasTradeoffRepository.delete(tradeoffInfo.getId());
        }

        // Fetching DecisionDocumentation
        DecisionDocumentationModel decisionDocumentationModel = decisionDocumentationRepository.findOne(idDecisionDocumentationModel, 0);
        Assert.notNull(decisionDocumentationModel);

        // Fetching the tradeoff
        // A new one will be created if a similar tradeoff is does not exist
        Tradeoff tradeoff = ddmCoreDataBusiness.getTradeoffByTradeoffItems(tradeoffInfo.getIdAttributeOver(), tradeoffInfo.getIdAttributeUnder());

        // Creating the new hasTradeoff Relationship
        HasTradeoff hasTradeoff = new HasTradeoff(decisionDocumentationModel, tradeoff, tradeoffInfo.getDescription(), tradeoffInfo.getOrdering(), tradeoff.getTradeoffItemOver().getDefinition(), tradeoff.getTradeoffItemUnder().getDefinition());

        hasTradeoff = ddmHasTradeoffRepository.save(hasTradeoff);

        // Reordering Relations after adding
        // TODO REORDER

        return new DecisionDocumentationModelTradeoffRelationDto(hasTradeoff);
    }

    @Override
    public DecisionDocumentationModelTradeoffRelationDto updateExistingTradeoffRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationTradeoffRelation, DecisionDocumentationModelTradeoffRelationDto newValues) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(idDecisionDocumentationTradeoffRelation);

        // Fetching the hasTradeoff from DB
        HasTradeoff hasTradeoff = ddmHasTradeoffRepository.findOne(idDecisionDocumentationTradeoffRelation);
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

            HasTradeoff tradeoffRelationSameOrdering = ddmHasTradeoffRepository.findByOrdering(idDecisionDocumentationModel, newValues.getOrdering());

            if (tradeoffRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            tradeoffRelationSameOrdering.setOrdering(hasTradeoff.getOrdering());
            hasTradeoff.setOrdering(newValues.getOrdering());

            // TODO REorder
        }

        return new DecisionDocumentationModelTradeoffRelationDto(hasTradeoff);
    }

    @Override
    public void deleteTradeoffRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationTradeoffRelation) {
        Assert.notNull(idDecisionDocumentationModel);
        Assert.notNull(idDecisionDocumentationTradeoffRelation);

        HasTradeoff relationToDelete = ddmHasTradeoffRepository.findOne(idDecisionDocumentationTradeoffRelation);

        if (relationToDelete == null)
            return;

        ddmHasTradeoffRepository.delete(relationToDelete);

        // Reorder the DecisionDocumentation Tradeoffs
        // TODO REORDER
    }
}
