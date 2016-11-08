package at.decisionexpert.service.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.*;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationService {

    DecisionDocumentationModelDto getDecisionDocumentationModel(@NotNull Long id);

    /**
     * Getting the newest DecisionDocumentationModels
     *
     * @param page which page
     * @param size how many items per page
     * @return The list of the newest DecisionDocumentationModels
     */
    DecisionDocumentationModelPageableDto getNewestDecisionDocumentationModels(@NotNull Integer page, @NotNull Integer size);

    /**
     * Getting the DecisionDocumentationModels of a specific user
     *
     * @param idUser Which user
     * @param page   which page
     * @param size   how many items per page
     * @return The list of the newest DecisionDocumentationModels
     */
    DecisionDocumentationModelPageableDto getUserDecisionDocumentationModel(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size);

    //    @PreAuthorize("hasRole('ROLE_USER')")
    DecisionDocumentationModelDto createDecisionDocumentationModel(DecisionDocumentationModelChangeRequestDto decisionDocumentationModel);

    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionDocumentationModel', 'OWNER')")
    DecisionDocumentationModelDto updateDecisionDocumentationModelProperties(@NotNull Long id, @NotNull DecisionDocumentationModelChangeRequestDto newValues);

    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionDocumentationModel', 'OWNER')")
    void deleteDecisionDocumentationModel(@NotNull Long id);

    /**
     * Adding an empty relation attribute for a given DecisionDocumentationModel
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     * @param attributeInfo                The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass                Which Relation (Given by Type)
     * @param toNodeType                   To which node type
     * @param <T>                          Type identifier for Relation Class
     * @return a new DecisionDocumentationModelRelation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentationModel, 'at.decisionexpert.neo4jentity.node.DecisionDocumentationModel', 'OWNER')")
    <T extends DDMAttributeRelationship<A>, A extends CoreData> DecisionDocumentationModelRelationDto createRelation(
            @NotNull Long idDecisionDocumentationModel, DecisionDocumentationModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DecisionDocumentationModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionDocumentationModel         Which DecisionDocumentationModel
     * @param idDecisionDocumentationModelRelation Which DecisionDocumentationModel Relationship (e.g. DecisionDocumentationModel Quality
     *                                             Attribute)
     * @param newValues                            The new Values for the DecisionDocumentationModel Relation -> for convinience
     *                                             stored in a model
     * @param clazz                                The neo4j class information of the Type T (needed, because no
     *                                             class information at runtime)
     * @return The updated and already persisted DecisionDocumentationModel Relation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentationModel, 'at.decisionexpert.neo4jentity.node.DecisionDocumentationModel', 'OWNER')")
    <T extends DDMAttributeRelationship<? extends CoreData>> DecisionDocumentationModelRelationDto updateExistingRelationAttribute(
            @NotNull Long idDecisionDocumentationModel, @NotNull Long idDecisionDocumentationModelRelation, DecisionDocumentationModelRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing DecisionDocumentationModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionDocumentationModel         Which DecisionDocumentationModel
     * @param idDecisionDocumentationModelRelation Which Relation
     * @param relationClass                        The neo4j class information of the Type T (needed, because no
     *                                             class information at runtime)
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentationModel, 'at.decisionexpert.neo4jentity.node.DecisionDocumentationModel', 'OWNER')")
    <T extends DDMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idDecisionDocumentationModel, @NotNull Long idDecisionDocumentationModelRelation, Class<T> relationClass);

    /**
     * Creating A new EffectedDocumentationModel Relation
     *
     * @param idDecisionDocumentation Which DecisionDocumentation
     * @param guidanceModelInfo       EffectedDocumentationModel values
     * @return newly created EffectedDocumentationModel Dto
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    DecisionDocumentationModelEffectedDocumentationModelDto createDocumentationModelRelation(@NotNull Long idDecisionDocumentation, DecisionDocumentationModelEffectedDocumentationModelDto guidanceModelInfo);

    /**
     * Updating existing EffectedDocumentationModel Relation Attributes
     *
     * @param idDecisionDocumentation         Which DecisionDocumentation
     * @param idDecisionDocumentationRelation Which DecisionDocumentation Relation
     * @param newValues                       The new Values
     * @return The altered DecisionDocumentation EffectedDocumentationModel Relation dto
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    DecisionDocumentationModelEffectedDocumentationModelDto updateExistingDocumentationModelRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idDecisionDocumentationRelation, @NotNull DecisionDocumentationModelEffectedDocumentationModelDto newValues);

    /**
     * Deleting an existing EffectedDocumentationModel Relation
     *
     * @param idDecisionDocumentation         Which DecisionDocumentation
     * @param idDecisionDocumentationRelation Which DecisionDocumentation Relation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    void deleteDocumentationModelRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idDecisionDocumentationRelation);

    /**
     * Creating A new Tradeoff Relation
     *
     * @param idDecisionDocumentation Which DecisionDocumentation
     * @param tradeoffInfo  Tradeoff values
     * @return newly created Tradeoff Dto
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    DecisionDocumentationModelTradeoffRelationDto createTradeoffRelation(@NotNull Long idDecisionDocumentation, DecisionDocumentationModelTradeoffRelationDto tradeoffInfo);
    
    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idDecisionDocumentation         Which DecisionDocumentation
     * @param idTradeoffRelation Which Tradeoff Relation
     * @param newValues             The new Values
     * @return The altered DecisionDocumentation Tradeoff Relation dto
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    DecisionDocumentationModelTradeoffRelationDto updateExistingTradeoffRelationAttribute(@NotNull Long idDecisionDocumentation, @NotNull Long idTradeoffRelation, @NotNull DecisionDocumentationModelTradeoffRelationDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idDecisionDocumentation                 Which DecisionDocumentation
     * @param idTradeoffRelation Which Tradeoff Relation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDecisionDocumentation, 'at.decisionexpert.neo4jentity.node.DecisionDocumentation', 'OWNER')")
    void deleteDecisionDocumentationTradeoffRelation(@NotNull Long idDecisionDocumentation, @NotNull Long idTradeoffRelation);
}
