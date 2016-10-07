package at.archkb.server.repository.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DDMAttributeRelationshipRepository {
    /**
     * Loading a single DecisionDocumentationModel Relation by a given class and an Id
     *
     * @param clazz                 Which class (e.g. HAS_QUALITYATTRIBUTES)
     * @param idDecisionDocumentationModelRelation Which Element - identified by id
     * @return A single DecisionDocumentationModel Relation Attribute
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                        Long idDecisionDocumentationModelRelation);

    /**
     * Loading a single DecisionDocumentationModel Relation by a given class, the given
     * DecisionDocumentationModel and the ordering of the DecisionDocumentationModel Relation
     *
     * @param clazz         Which DecisionDocumentationModel Relation Class
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     * @param ordering      The ordering of the DecisionDocumentationModel Relation
     * @return A single DecisionDocumentationModel Relation Attribute - based on the input
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                              Long idDecisionDocumentationModel, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idDecisionDocumentationModel, Class<T> relationClass);

    /**
     * Creating an empty relation for an DecisionDocumentationModel
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends DDMAttributeRelationship<? extends CoreData>> T save(T relation);
}
