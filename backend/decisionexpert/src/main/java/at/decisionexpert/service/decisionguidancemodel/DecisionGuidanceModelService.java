package at.decisionexpert.service.decisionguidancemodel;

import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public interface DecisionGuidanceModelService {

    DecisionGuidanceModelDto getDecisionGuidanceModel(@NotNull Long id);

    /**
     * Getting the newest DecisionGuidanceModels
     *
     * @param page which page
     * @param size how many items per page
     * @return The list of the newest DecisionGuidanceModels
     */
    DecisionGuidanceModelPageableDto getDecisionGuidanceModels(@NotNull Integer page, @NotNull Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType type, String searchText, Long groupId);

    /**
     * Getting the DecisionGuidanceModels of a specific user
     *
     * @param idUser Which user
     * @param page   which page
     * @param size   how many items per page
     * @return The list of the newest DecisionGuidanceModels
     */
    DecisionGuidanceModelPageableDto getUserDecisionGuidanceModel(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType type, DecisionGuidanceModelController.ModelState modelState);


    @PreAuthorize("hasRole('ROLE_USER')")
    DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel);

    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    DecisionGuidanceModelDto updateDecisionGuidanceModelProperties(@NotNull Long id, @NotNull DecisionGuidanceModelChangeRequestDto newValues);

    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    void deleteDecisionGuidanceModel(@NotNull Long id);

    @PreAuthorize("hasRole('ROLE_USER')")
    List<DecisionGuidanceModelRelationDto> getPotentialRequirements(@NotNull Long idDecisionGuidanceModel);

    /**
     * Adding an empty relation attribute for a given DecisionGuidanceModel
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new DecisionGuidanceModelRelation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionGuidanceModel, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto createRelation(
            @NotNull Long idDecisionGuidanceModel, DecisionGuidanceModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DecisionGuidanceModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionGuidanceModel         Which DecisionGuidanceModel
     * @param idDecisionGuidanceModelRelation Which DecisionGuidanceModel Relationship (e.g. DecisionGuidanceModel Quality
     *                              Attribute)
     * @param newValues             The new Values for the DecisionGuidanceModel Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted DecisionGuidanceModel Relation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionGuidanceModel, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto updateExistingRelationAttribute(
            @NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelationDto newValues,
            Class<T> clazz, Class<A> toNodeType);

    /**
     * Generic service for deleting an existing DecisionGuidanceModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionGuidanceModel         Which DecisionGuidanceModel
     * @param idDecisionGuidanceModelRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionGuidanceModel, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    <T extends DGMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idDecisionGuidanceModel, @NotNull Long idDecisionGuidanceModelRelation, Class<T> relationClass);
}
