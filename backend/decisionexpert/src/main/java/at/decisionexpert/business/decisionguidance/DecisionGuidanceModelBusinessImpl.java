package at.decisionexpert.business.decisionguidance;

import at.decisionexpert.business.coredata.CreateCoreDataImpl;
import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.exception.DecisionGuidanceModelNotFoundException;
import at.decisionexpert.exception.DecisionGuidanceModelNotPermittedException;
import at.decisionexpert.exception.RelationToGuidanceModelAlreadyExists;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.neo4jentity.node.UserAuthority;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import at.decisionexpert.repository.node.RequirementRepository;
import at.decisionexpert.repository.node.comment.CommentRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.node.decisionguidance.designoption.DesignOptionRepository;
import at.decisionexpert.repository.relationship.decisionguidance.DGMAttributeRelationshipRepository;
import at.decisionexpert.repository.relationship.decisionguidance.DGMHasDesignOptionRepository;
import at.decisionexpert.repository.relationship.decisionguidance.DGMHasGuidanceModelRepository;
import at.decisionexpert.repository.relationship.decisionguidance.DGMHasPotentialRequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private RequirementRepository requirementRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DGMAttributeRelationshipRepository dgmAttributeRelationshipRepository;

    @Autowired
    private NodeAttributeRepository nodeAttributeRepository;

    @Autowired
    private DGMHasGuidanceModelRepository dgmHasGuidanceModelRepository;

    @Autowired
    private DGMHasDesignOptionRepository dgmHasDesignOptionRepository;

    @Autowired
    private DGMHasPotentialRequirementRepository dgmHasPotentialRequirementRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CreateCoreDataImpl createCoreDataImpl;

    @Override
    public DecisionGuidanceModelDto getDecisionGuidanceModel(Long id) {
        if (id < 0) {
            throw new DecisionGuidanceModelNotFoundException();
        }

        DecisionGuidanceModel decisionGuidanceModel = decisionGuidanceModelRepository.findOne(id, 2);

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

        //if decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 2) doesn't work correctly --> happens sometimes, don't know why
        if (decisionGuidanceModel.getPotentialRequirements().size() == 0) {
            decisionGuidanceModel.setPotentialRequirements(dgmHasPotentialRequirementRepository.findAll(id));
        }
        decisionGuidanceModel.getDesignOptions().forEach(hasDesignOption -> hasDesignOption.getEndNode().setVotePositive(designOptionRepository.getVotePositiveForDO(hasDesignOption.getEndNode().getId())));
        decisionGuidanceModel.getDesignOptions().forEach(hasDesignOption -> hasDesignOption.getEndNode().setVoteNegative(designOptionRepository.getVoteNegativeForDO(hasDesignOption.getEndNode().getId())));
        //Add Design Option with all further connections, e.g., implications
        decisionGuidanceModel.getDesignOptions().forEach(hasDesignOption -> hasDesignOption.setEndNode(designOptionRepository.findOne(hasDesignOption.getEndNode().getId(), 1)));

        return new DecisionGuidanceModelDto(decisionGuidanceModel);
    }

    @Override
    public DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel) {
        // Setting the owner of the decisionGuidanceModel -> the authenticated user
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        DecisionGuidanceModel newDecisionGuidanceModel = new DecisionGuidanceModel(user, decisionGuidanceModel.getName(), decisionGuidanceModel.getDescription());

        // On creation the New DecisionGuidanceModel must be unpublished
        if (decisionGuidanceModel.getPublished() != null) {
            newDecisionGuidanceModel.setPublished(decisionGuidanceModel.getPublished());
        } else {
            newDecisionGuidanceModel.setPublished(false);
        }

        return new DecisionGuidanceModelDto(decisionGuidanceModelRepository.save(newDecisionGuidanceModel));
    }

    @Override
    public DecisionGuidanceModelDto updateDecisionGuidanceModelProperties(Long id, DecisionGuidanceModelChangeRequestDto newValues) {
        DecisionGuidanceModel loaded = decisionGuidanceModelRepository.findOne(id);
        Assert.notNull(loaded);

        if (newValues.getName() != null) {
            loaded.setName(newValues.getName());
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
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModels(Integer page, Integer size, boolean withUnpublished, DecisionGuidanceModelController.DecisionGuidanceModelType type, String searchText, Long groupId) {
        Assert.notNull(page);
        Assert.notNull(size);

        if (searchText == null) {
            searchText = "(?i).*.*";
        } else {
            searchText = "(?i).*" + searchText + ".*";
        }

        int skip = page * size;

        List<DecisionGuidanceModelDto> decisionGuidanceModelPage = null;
        if (type == DecisionGuidanceModelController.DecisionGuidanceModelType.ALPHABET) {
            decisionGuidanceModelPage = withUnpublished ? decisionGuidanceModelRepository.findAlphabetDecisionGuidanceModels(skip, size, searchText) : decisionGuidanceModelRepository.findAlphabetPublishedDecisionGuidanceModels(skip, size, searchText);
        } else if (type == DecisionGuidanceModelController.DecisionGuidanceModelType.RATING) {
            decisionGuidanceModelPage = withUnpublished ? decisionGuidanceModelRepository.findRatingDecisionGuidanceModels(skip, size, searchText) : decisionGuidanceModelRepository.findRatingPublishedDecisionGuidanceModels(skip, size, searchText);
        } else { //default DecisionGuidanceModelType.NEWEST
            decisionGuidanceModelPage = withUnpublished ? decisionGuidanceModelRepository.findNewestDecisionGuidanceModels(skip, size, searchText) : decisionGuidanceModelRepository.findNewestPublishedDecisionGuidanceModels(skip, size, searchText);
        }

        if (decisionGuidanceModelPage == null || decisionGuidanceModelPage.size() == 0) {
            return new DecisionGuidanceModelPageableDto();
        }

        // Fetching total Count
        Long totalCount = withUnpublished ? decisionGuidanceModelRepository.countDecisionGuidanceModels(searchText) : decisionGuidanceModelRepository.countPublishedDecisionGuidanceModels(searchText);

        return new DecisionGuidanceModelPageableDto(totalCount, decisionGuidanceModelPage);
    }

    @Override
    public DecisionGuidanceModelPageableDto getUserDecisionGuidanceModels(Long idUser, Integer page, Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType ordering, DecisionGuidanceModelController.ModelState modelState) {
        Assert.notNull(idUser);
        Assert.notNull(page);
        Assert.notNull(size);

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        User authenticatedUser = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        // Determine if the unpublished are fetched as well
        // e.g. when user is owner or user is admin
        boolean withUnpublished = authenticatedUser == null ? false : authenticatedUser.getId().equals(idUser) || authenticatedUser.getAuthorities().contains(new UserAuthority("ROLE_ADMIN"));
        //by default published, and unpublished(if the user has the needed premission) are returned
        boolean published = true;
        boolean unpublished = withUnpublished;
        if (modelState == DecisionGuidanceModelController.ModelState.PUBLISH) {
            published = true;
            unpublished = false;
        } else if (modelState == DecisionGuidanceModelController.ModelState.UNPUBLISHED) {
            published = false;
            unpublished = true;
        }

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        List<DecisionGuidanceModelDto> decisionGuidanceModels = null;
        if (ordering == DecisionGuidanceModelController.DecisionGuidanceModelType.ALPHABET) {
            if (published && unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findAlphabetAllByUserId(idUser, page * size, size);
            } else if (published && !unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findAlphabetPublishedByUserId(idUser, page * size, size);
            } else {
                decisionGuidanceModels = decisionGuidanceModelRepository.findAlphabetUnpublishedByUserId(idUser, page * size, size);
            }
        } else if (ordering == DecisionGuidanceModelController.DecisionGuidanceModelType.RATING) {
            if (published && unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findRatingAllByUserId(idUser, page * size, size);
            } else if (published && !unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findRatingPublishedByUserId(idUser, page * size, size);
            } else {
                decisionGuidanceModels = decisionGuidanceModelRepository.findRatingUnpublishedByUserId(idUser, page * size, size);
            }
        } else { //default DecisionGuidanceModelType.NEWEST
            if (published && unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findNewestAllByUserId(idUser, page * size, size);
            } else if (published && !unpublished) {
                decisionGuidanceModels = decisionGuidanceModelRepository.findNewestPublishedByUserId(idUser, page * size, size);
            } else {
                decisionGuidanceModels = decisionGuidanceModelRepository.findNewestUnpublishedByUserId(idUser, page * size, size);
            }
        }

        if (decisionGuidanceModels == null || decisionGuidanceModels.size() == 0) {
            return new DecisionGuidanceModelPageableDto();
        }

        // If the authenticated User requests his own ArchProfiles => also load the unpublished ones!
        Long countUserProfiles = decisionGuidanceModelRepository.countAllDecisionGuidanceModelsOfUser(idUser);
        if (published && !unpublished) {
            countUserProfiles = decisionGuidanceModelRepository.countPublishedDecisionGuidanceModelsOfUser(idUser);
        } else if (!published && unpublished) {
            countUserProfiles = decisionGuidanceModelRepository.countUnpublishedDecisionGuidanceModelsOfUser(idUser);
        }


        return new DecisionGuidanceModelPageableDto(countUserProfiles, decisionGuidanceModels);
    }

    @Override
    public List<DecisionGuidanceModelRelationDto> getPotentialRequirements(Long idDecisionGuidanceModel) {
        Assert.notNull(idDecisionGuidanceModel);

        List<DecisionGuidanceModelRelationDto> result = new ArrayList<>();

        DecisionGuidanceModel dgm = decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 2);

        if (dgm == null) {
            throw new DecisionGuidanceModelNotFoundException();
        }

        //if decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 2) doesn't work correctly --> happens sometimes, don't know why
        if (dgm.getPotentialRequirements().size() == 0) {
            dgm.setPotentialRequirements(dgmHasPotentialRequirementRepository.findAll(idDecisionGuidanceModel));
        }

        dgm.getPotentialRequirements().forEach(rq -> {
            result.add(new DecisionGuidanceModelRelationDto(rq));
        });

        return result;
    }

    @Override
    public <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto createRelation(Long idDecisionGuidanceModel, DecisionGuidanceModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType) {
        Assert.notNull(idDecisionGuidanceModel);
        Assert.notNull(relationClass);

        //Check if RelatedGuidanceModelRelation already exists
        if (relationClass.getName() == "at.decisionexpert.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels") {
            Assert.notNull(attributeInfo.getIdAttribute());
            if (dgmAttributeRelationshipRepository.findRelationByStartNodeEndNode(relationClass, idDecisionGuidanceModel, attributeInfo.getIdAttribute()).iterator().hasNext())
                throw new RelationToGuidanceModelAlreadyExists();
        }

        // Means that an existing relation was altered!
        // We have to delete this relation and create a new one!
        if (attributeInfo.getId() != null) {
            dgmAttributeRelationshipRepository.delete(dgmAttributeRelationshipRepository.findById(relationClass, attributeInfo.getId()));
        }

        DecisionGuidanceModel decisionGuidanceModel = decisionGuidanceModelRepository.findOne(idDecisionGuidanceModel, 0);

        // DecisionGuidanceModel is not allowed to be null
        Assert.notNull(decisionGuidanceModel);

        A toNode = nodeAttributeRepository.findById(attributeInfo.getIdAttribute(), toNodeType);

        // If toNode does not exist -> create a new one and use this one!
        if (toNode == null) {
            toNode = createCoreDataImpl.createCoreData(attributeInfo.getName(), attributeInfo.getDescription(), toNodeType);
        }

        try {
            Constructor constructor = relationClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            T newRelationship = (T) constructor.newInstance();
            Assert.notNull(newRelationship);

            // TODO get correct ordering
            newRelationship.setOrdering(attributeInfo.getOrdering() == null ? 0 : attributeInfo.getOrdering());

            newRelationship.setStartNode(decisionGuidanceModel);
            newRelationship.setEndNode(toNode);
            newRelationship.setRationale(attributeInfo.getRationale());
            newRelationship.setDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

            newRelationship = dgmAttributeRelationshipRepository.save(newRelationship);

            // Reorder all the other relations -> property ordering must be applied correctly!
            // TODO REorder

            return new DecisionGuidanceModelRelationDto(newRelationship);

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
    public <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto updateExistingRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelationDto newValues, Class<T> clazz, Class<A> toNodeType) {
        // Fetch AP Relation from Graph DB
        T loadedRelation = dgmAttributeRelationshipRepository.findById(clazz, idDecisionGuidanceModelRelation);
        Assert.notNull(loadedRelation);

        //Change Endnode
        if (newValues.getIdAttribute() != null) {
            if (nodeAttributeRepository.findById(newValues.getIdAttribute(), loadedRelation.getEndNode().getClass()) != null) {
                if (dgmAttributeRelationshipRepository.findRelationByStartNodeEndNode(clazz, idDecisionGuidanceModel, newValues.getIdAttribute()).iterator().hasNext())
                    throw new RelationToGuidanceModelAlreadyExists();
                dgmAttributeRelationshipRepository.delete(loadedRelation);
                loadedRelation.setId(null);
                loadedRelation.setEndNode(nodeAttributeRepository.findById(newValues.getIdAttribute(), toNodeType));
            }
        }

        // Setting the new value for the rationale
        if (newValues.getRationale() != null)
            loadedRelation.setRationale(newValues.getRationale());

        // Setting the new value for the name of the requirement
        if (newValues.getName() != null)
            loadedRelation.getEndNode().setName(newValues.getName());

        // Setting the new value for the definition of the requirement
        if (newValues.getDescription() != null)
            loadedRelation.getEndNode().setDescription(newValues.getDescription());


        // Setting the ordering -> must change the ordering values of other AP
        // Relationships as well
        if (newValues.getOrdering() != null) {

            /*T loadedRelationSameOrdering = dgmAttributeRelationshipRepository.findByOrdering(clazz, idDecisionGuidanceModel, newValues.getOrdering());

            if (loadedRelationSameOrdering == null)
                return null;

            // Switching places between the two relations (setting ordering).
            loadedRelationSameOrdering.setOrdering(loadedRelation.getOrdering());
            loadedRelation.setOrdering(newValues.getOrdering());*/
        }

        // Persist the new Values
        return new DecisionGuidanceModelRelationDto(dgmAttributeRelationshipRepository.save(loadedRelation));
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
}
