package at.archkb.server.business.decisiondocumentation;

import at.archkb.server.exception.DecisionDocumentationNotFoundException;
import at.archkb.server.neo4jentity.dto.decisiondocumentation.*;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationBusiness {

    DecisionDocumentationModelDto getDecisionDocumentationModel(Long id) throws DecisionDocumentationNotFoundException;

    DecisionDocumentationModelDto createDecisionDocumentationModel(DecisionDocumentationModelChangeRequestDto decisionDocumentationModel);

    DecisionDocumentationModelDto updateDecisionDocumentationModelProperties(Long id, DecisionDocumentationModelChangeRequestDto newValues);

    void deleteDecisionDocumentationModel(Long id);

    DecisionDocumentationModelPageableDto getNewestDecisionDocumentationModels(Integer page, Integer size, boolean withUnpublished);

    DecisionDocumentationModelPageableDto getUserDecisionDocumentationModels(Long idUser, Integer page, Integer size);

    /**
     * Adding an empty relation attribute for a given DecisionDocumentationModel
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new DecisionDocumentationModelRelation
     */
    <T extends DDMAttributeRelationship<A>, A extends CoreData> DecisionDocumentationModelRelationDto createRelation(
            Long idDecisionDocumentationModel, DecisionDocumentationModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DecisionDocumentationModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     *
     * @param idDecisionDocumentationModelRelation Which DecisionDocumentationModel Relationship (e.g. DecisionDocumentationModel Quality
     *                              Attribute)
     * @param newValues             The new Values for the DecisionDocumentationModel Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted DecisionDocumentationModel Relation
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> DecisionDocumentationModelRelationDto updateExistingRelationAttribute(
            Long idDecisionDocumentationModel, Long idDecisionDocumentationModelRelation, DecisionDocumentationModelRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing DecisionDocumentationModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionDocumentationModel         Which DecisionDocumentationModel
     * @param idDecisionDocumentationModelRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idDecisionDocumentationModel, Long idDecisionDocumentationModelRelation, Class<T> relationClass);


    /**
     * Creating A new EffectedDocumentation Relation
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentation
     * @param effectedDocumentationModelInfo  EffectedDocumentation values
     * @return newly created EffectedDocumentation Dto
     */
    DecisionDocumentationModelEffectedDocumentationModelDto createEffectedDocumentationModelRelation(Long idDecisionDocumentationModel, DecisionDocumentationModelEffectedDocumentationModelDto effectedDocumentationModelInfo);

    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idDecisionDocumentationModel                 Which DecisionDocumentation
     * @param idDecisionDocumentationEffectedDocumentationRelation Which DecisionDocumentationModel Relation
     * @param newValues                     The new Values
     * @return The altered DecisionDocumentation Tradeoff Relation dto
     */
    DecisionDocumentationModelEffectedDocumentationModelDto updateExistingEffectedDocumentationModelRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationEffectedDocumentationRelation, DecisionDocumentationModelEffectedDocumentationModelDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idDecisionDocumentationModel                 Which DecisionDocumentation
     * @param idDecisionDocumentationEffectedDocumentationRelation Which DecisionDocumentationRelation
     */
    void deleteEffectedDocumentationModelRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationEffectedDocumentationRelation);



    /**
     * Creating A new Tradeoff Relation
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentation
     * @param tradeoffInfo  Tradeoff values
     * @return newly created Tradeoff Dto
     */
    DecisionDocumentationModelTradeoffRelationDto createTradeoffRelation(Long idDecisionDocumentationModel, DecisionDocumentationModelTradeoffRelationDto tradeoffInfo);

    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idDecisionDocumentationModel                 Which DecisionDocumentation
     * @param idDecisionDocumentationTradeoffRelation Which Tradeoff Relation
     * @param newValues                     The new Values
     * @return The altered DecisionDocumentation Tradeoff Relation dto
     */
    DecisionDocumentationModelTradeoffRelationDto updateExistingTradeoffRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationTradeoffRelation, DecisionDocumentationModelTradeoffRelationDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idDecisionDocumentationModel                 Which DecisionDocumentation
     * @param idDecisionDocumentationTradeoffRelation Which Tradeoff
     */
    void deleteTradeoffRelationAttribute(Long idDecisionDocumentationModel, Long idDecisionDocumentationTradeoffRelation);
}
