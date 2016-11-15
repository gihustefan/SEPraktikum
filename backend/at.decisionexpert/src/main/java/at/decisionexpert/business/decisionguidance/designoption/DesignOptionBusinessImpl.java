package at.decisionexpert.business.decisionguidance.designoption;

import at.decisionexpert.business.coredata.CreateCoreDataImpl;
import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.exception.DesignOptionNotFoundException;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasEffectedGuidanceModels;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.node.decisionguidance.designoption.DesignOptionRepository;
import at.decisionexpert.repository.relationship.decisionguidance.designoption.DOAttributeRelationshipRepository;
import at.decisionexpert.repository.relationship.decisionguidance.designoption.DOHasEffectedGuidanceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@Component
public class DesignOptionBusinessImpl implements DesignOptionBusiness{

    @Autowired
    private DesignOptionRepository designOptionRepository;

    @Autowired
    private DecisionGuidanceModelRepository decisionGuidanceModelRepository;

    @Autowired
    private DOAttributeRelationshipRepository doAttributeRelationshipRepository;

    @Autowired
    private DOHasEffectedGuidanceModelRepository doHasEffectedGuidanceModelRepository;

    @Autowired
    private NodeAttributeRepository nodeAttributeRepository;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CreateCoreDataImpl createCoreDataImpl;



    @Override
    public DesignOptionDto getDesignOption(Long id) throws DesignOptionNotFoundException {
        // TODO: check other "unvalid" values
        // return empty DecisionGuidanceModel
        if (id < 0) {
            throw new DesignOptionNotFoundException();
        }

        DesignOption designOption = designOptionRepository.findOne(id, 1);

        // When not found -> just return an empty DecisionGuidanceModel POJO
        if (designOption == null) {
            throw new DesignOptionNotFoundException();
        }
/*
        // Fetching the authenticated user
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the user which requested the DecisionGuidanceModel can actually see it
        boolean isCreator;
        boolean isAdmin;
        if(authenticatedUser == null) {
            isCreator = false;
            isAdmin = false;
        } else {
            isCreator = designOption.getCreator() != null  && authenticatedUser.getId() == designOption.getCreator().getId();
            isAdmin = authenticatedUser.getAuthorities() != null && authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        }

        // Either the DecisionGuidanceModel isPublished, The user is the creator or the user is an admin
        boolean canSee = isCreator || isAdmin;


        if (!canSee)
            throw new DesignOptionNotPermittedException();*/

        return new DesignOptionDto(designOption);
    }

    @Override
    public DesignOptionDto createDesignOption(DesignOptionChangeRequestDto designOption) {
        return null;
    }

    @Override
    public DesignOptionDto updateDesignOptionProperties(Long id, DesignOptionChangeRequestDto newValues) {
        return null;
    }

    @Override
    public void deleteDesignOption(Long id) {
        designOptionRepository.delete(id);
    }

    @Override
    public <T extends DOAttributeRelationship<A>, A extends CoreData> DesignOptionRelationDto createRelation(Long idDesignOption, DesignOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idDesignOption);
        Assert.notNull(relationClass);

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            doAttributeRelationshipRepository.delete(doAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        DesignOption designOption = designOptionRepository.findOne(idDesignOption, 0);

        // ArchProfile is not allowed to be null
        Assert.notNull(designOption);

        A toNode = nodeAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null)
            toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), "", toNodeType);
            //toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), attributeInfo.getDefinition(), toNodeType);

        try {

            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(designOption);
            newRelationship.setEndNode(toNode);
            // Setting the definition of the Relation to the same as the toNode (than we dont have to worry about changes in the toNode -> users can alter the definition of the properties themselves!)
            newRelationship.setDescription(attributeInfo.getDescription());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            // Saving the new Relationship
            newRelationship = doAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new DesignOptionRelationDto(newRelationship);

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
    public <T extends DOAttributeRelationship<? extends CoreData>> DesignOptionRelationDto updateExistingRelationAttribute(Long idDesignOption, Long idDesignOptionRelation, DesignOptionRelationDto newValues, Class<T> clazz) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = doAttributeRelationshipRepository.findById(clazz, idDesignOptionRelation);
        Assert.notNull(loadedRelation);

        // Setting the new value for the description
        if (newValues.getDescription() != null)
            loadedRelation.setDescription(newValues.getDescription());

        // Setting the ordering -> must change the ordering values of other AP
        // Relationships as well
        if (newValues.getOrdering() != null) {

            T loadedRelationSameOrdering = doAttributeRelationshipRepository.findByOrdering(clazz, idDesignOption, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());
        }

        // Persist the new Values
        return new DesignOptionRelationDto(loadedRelation);
    }

    @Override
    public <T extends DOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(Long idDesignOption, Long idDesignOptionRelation, Class<T> relationClass) {
        Assert.notNull(idDesignOption);
        Assert.notNull(idDesignOptionRelation);
        Assert.notNull(relationClass);

        // Fetch the relation that should be deleted
        T archProfileRelationToDelete = doAttributeRelationshipRepository.findById(relationClass, idDesignOptionRelation);

        if (archProfileRelationToDelete == null)
            return;

        doAttributeRelationshipRepository.delete(archProfileRelationToDelete);

        // TODO Reorder
    }

    @Override
    public DesignOptionEffectedGuidanceModelsDto createGuidanceModelRelation(Long idDesignOption, DesignOptionEffectedGuidanceModelsDto guidanceModelInfo) {
        Assert.notNull(idDesignOption);
        Assert.notNull(guidanceModelInfo);

        // Fetching the current user -> that wants to create the new Tradeoff
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        // Means that an existing tradeoff relation was altered
        // must delete this one and create a new one based on the given information!
        if (guidanceModelInfo.getId() != null) {
            doHasEffectedGuidanceModelRepository.delete(guidanceModelInfo.getId());
        }

        // Fetching ArchProfile
        DesignOption startDesignOption = designOptionRepository.findOne(idDesignOption, 0);
        Assert.notNull(startDesignOption);

        DecisionGuidanceModel endDecisionGuidanceModel = decisionGuidanceModelRepository.findOne(guidanceModelInfo.getIdAttribute(), 0);
        Assert.notNull(endDecisionGuidanceModel);

        HasEffectedGuidanceModels hasEffectedGuidanceModels;
        if (guidanceModelInfo.getDescription() != null && guidanceModelInfo.getOrdering() != null) {
            hasEffectedGuidanceModels = new HasEffectedGuidanceModels(startDesignOption, endDecisionGuidanceModel, guidanceModelInfo.getDescription(), guidanceModelInfo.getOrdering());
        } else {
            hasEffectedGuidanceModels = new HasEffectedGuidanceModels(startDesignOption, endDecisionGuidanceModel);
        }

        hasEffectedGuidanceModels = doHasEffectedGuidanceModelRepository.save(hasEffectedGuidanceModels);

        // Reordering Relations after adding
        // TODO REORDER

        return new DesignOptionEffectedGuidanceModelsDto(hasEffectedGuidanceModels);
    }

    @Override
    public DesignOptionEffectedGuidanceModelsDto updateExistingGuidanceModelRelationAttribute(Long idDesignOption, Long idDesignOptionRelation, DesignOptionEffectedGuidanceModelsDto newValues) {
        Assert.notNull(idDesignOption);
        Assert.notNull(idDesignOptionRelation);

        // Fetching the hasEffectedGuidanceModels from DB
        HasEffectedGuidanceModels hasEffectedGuidanceModels = doHasEffectedGuidanceModelRepository.findOne(idDesignOptionRelation);
        Assert.notNull(hasEffectedGuidanceModels);

        // Setting new Description
        if (newValues.getDescription() != null) {
            hasEffectedGuidanceModels.setDescription(newValues.getDescription());
        }

        if (newValues.getOrdering() != null) {

            HasEffectedGuidanceModels guidanceModelRelationSameOrdering = doHasEffectedGuidanceModelRepository.findByOrdering(idDesignOption, newValues.getOrdering());

            if (guidanceModelRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            guidanceModelRelationSameOrdering.setOrdering(hasEffectedGuidanceModels.getOrdering());
            hasEffectedGuidanceModels.setOrdering(newValues.getOrdering());

            // TODO REorder
        }

        return new DesignOptionEffectedGuidanceModelsDto(hasEffectedGuidanceModels);
    }

    @Override
    public void deleteGuidanceModelRelationAttribute(Long idDesignOption, Long idDesignOptionRelation) {
        Assert.notNull(idDesignOption);
        Assert.notNull(idDesignOptionRelation);

        // Fetch the relation that should be deleted
        HasEffectedGuidanceModels hasEffectedGuidanceModels = doHasEffectedGuidanceModelRepository.findOne(idDesignOptionRelation);

        if (hasEffectedGuidanceModels == null)
            return;

        doHasEffectedGuidanceModelRepository.delete(hasEffectedGuidanceModels);

        // TODO Reorder
    }
}
