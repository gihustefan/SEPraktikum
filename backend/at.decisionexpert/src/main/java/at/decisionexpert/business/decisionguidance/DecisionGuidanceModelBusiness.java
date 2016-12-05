package at.decisionexpert.business.decisionguidance;

import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.exception.DecisionGuidanceModelNotFoundException;
import at.decisionexpert.neo4jentity.dto.decisionguidance.*;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;

import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public interface DecisionGuidanceModelBusiness {

    DecisionGuidanceModelDto getDecisionGuidanceModel(Long id) throws DecisionGuidanceModelNotFoundException;

    DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel);

    DecisionGuidanceModelDto updateDecisionGuidanceModelProperties(Long id, DecisionGuidanceModelChangeRequestDto newValues);

    void deleteDecisionGuidanceModel(Long id);

    DecisionGuidanceModelPageableDto getDecisionGuidanceModels(Integer page, Integer size, boolean withUnpublished, DecisionGuidanceModelController.DecisionGuidanceModelType type);

    DecisionGuidanceModelPageableDto getUserDecisionGuidanceModels(Long idUser, Integer page, Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType ordering, DecisionGuidanceModelController.ModelState modelState);

    List<DecisionGuidanceModelRelationDto> getPotentialRequirements(Long idDecisionGuidanceModel);

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
    <T extends DGMAttributeRelationship<A>, A extends CoreData> DecisionGuidanceModelRelationDto createRelation(
            Long idDecisionGuidanceModel, DecisionGuidanceModelRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DecisionGuidanceModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     *
     * @param idDecisionGuidanceModelRelation Which DecisionGuidanceModel Relationship (e.g. DecisionGuidanceModel Quality
     *                              Attribute)
     * @param newValues             The new Values for the DecisionGuidanceModel Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted DecisionGuidanceModel Relation
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> DecisionGuidanceModelRelationDto updateExistingRelationAttribute(
            Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing DecisionGuidanceModel Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDecisionGuidanceModel         Which DecisionGuidanceModel
     * @param idDecisionGuidanceModelRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, Class<T> relationClass);


    /**
     * Creating A new GuidanceModel Relation
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     * @param guidanceModelInfo  GuidanceModel values
     * @return newly created GuidanceModel Dto
     */
    DecisionGuidanceModelRelatedGuidanceModelsDto createGuidanceModelRelation(Long idDecisionGuidanceModel, DecisionGuidanceModelRelatedGuidanceModelsDto guidanceModelInfo);

    /**
     * Updating existing GuidanceModel Relation Attributes
     *
     * @param idDecisionGuidanceModel                 Which DecisionGuidanceModel
     * @param idDecisionGuidanceModelRelation Which DecisionGuidanceModel Relation
     * @param newValues                     The new Values
     * @return The altered DecisionGuidanceModel GuidanceModel Relation dto
     */
    DecisionGuidanceModelRelatedGuidanceModelsDto updateExistingGuidanceModelRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation, DecisionGuidanceModelRelatedGuidanceModelsDto newValues);

    /**
     * Deleting an existing GuidanceModel Relation
     *
     * @param idDecisionGuidanceModel                 Which DecisionGuidanceModel
     * @param idDecisionGuidanceModelRelation Which DecisionGuidanceModel Relation
     */
    void deleteGuidanceModelRelationAttribute(Long idDecisionGuidanceModel, Long idDecisionGuidanceModelRelation);



    /**
     * Creating A new DesignOption Relation
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     * @param designOptionInfo  DesignOption values
     * @return newly created DesignOption Dto
     */
    DecisionGuidanceModelDesignOptionRelationDto createDesignOptionRelation(Long idDecisionGuidanceModel, DecisionGuidanceModelDesignOptionRelationDto designOptionInfo);

    /**
     * Updating existing DesignOption Relation Attributes
     *
     * @param idDecisionGuidanceModel                 Which DecisionGuidanceModel
     * @param idDesignOptionRelation Which DesignOption Relation
     * @param newValues                     The new Values
     * @return The altered DecisionGuidanceModel DesignOption Relation dto
     */
    DecisionGuidanceModelDesignOptionRelationDto updateExistingDesignOptionRelationAttribute(Long idDecisionGuidanceModel, Long idDesignOptionRelation, DecisionGuidanceModelDesignOptionRelationDto newValues);

    /**
     * Deleting an existing DesignOption Relation
     *
     * @param idDecisionGuidanceModel                 Which DecisionGuidanceModel
     * @param idDesignOptionRelation Which DesignOption Relation
     */
    void deleteDesignOptionRelationAttribute(Long idDecisionGuidanceModel, Long idDesignOptionRelation);
}
