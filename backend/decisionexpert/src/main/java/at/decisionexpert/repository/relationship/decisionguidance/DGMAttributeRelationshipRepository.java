package at.decisionexpert.repository.relationship.decisionguidance;


import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
public interface DGMAttributeRelationshipRepository {
    /**
     * Loading a single DecisionGuidanceModel Relation by a given class and an Id
     *
     * @param clazz                 Which class (e.g. HAS_QUALITYATTRIBUTES)
     * @param idDecisionGuidanceModelRelation Which Element - identified by id
     * @return A single DecisionGuidanceModel Relation Attribute
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                        Long idDecisionGuidanceModelRelation);

    /**
     * Loading a single DecisionGuidanceModel Relation by a given class, the given
     * DecisionGuidanceModel and the ordering of the DecisionGuidanceModel Relation
     *
     * @param clazz         Which DecisionGuidanceModel Relation Class
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     * @param ordering      The ordering of the DecisionGuidanceModel Relation
     * @return A single DecisionGuidanceModel Relation Attribute - based on the input
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                              Long idDecisionGuidanceModel, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idDecisionGuidanceModel, Class<T> relationClass);

    /**
     * Creating an empty relation for an DecisionGuidanceModel
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends DGMAttributeRelationship<? extends CoreData>> T save(T relation);
}
