package at.decisionexpert.repository.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DOAttributeRelationshipRepository {
    /**
     * Loading a single DesignOption Relation by a given class and an Id
     *
     * @param clazz                 Which class (e.g. HAS_QUALITYATTRIBUTES)
     * @param idDesignOptionRelation Which Element - identified by id
     * @return A single DesignOption Relation Attribute
     */
    <T extends DOAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                       Long idDesignOptionRelation);

    /**
     * Loading a single DesignOption Relation by a given class, the given
     * DesignOption and the ordering of the DesignOption Relation
     *
     * @param clazz         Which DesignOption Relation Class
     * @param idDesignOption Which DesignOption
     * @param ordering      The ordering of the DesignOption Relation
     * @return A single DesignOption Relation Attribute - based on the input
     */
    <T extends DOAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                             Long idDesignOption, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends DOAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idDesignOption Which DesignOption
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends DOAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idDesignOption, Class<T> relationClass);

    /**
     * Creating an empty relation for an DesignOption
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends DOAttributeRelationship<? extends CoreData>> T save(T relation);
}
