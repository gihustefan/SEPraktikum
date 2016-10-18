package at.decisionexpert.repository.relationship.component;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface CompAttributeRelationshipRepository {

    /**
     * Loading a single Component Relation by a given class and an Id
     *
     * @param clazz                 Which class
     * @param idComponentRelation Which Element - identified by id
     * @return A single Component Relation Attribute
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                              Long idComponentRelation);

    /**
     * Loading a single Component Relation by a given class, the given
     * Component and the ordering of the Component Relation
     *
     * @param clazz         Which Component Relation Class
     * @param idComponent Which Component
     * @param ordering      The ordering of the Component Relation
     * @return A single Component Relation Attribute - based on the input
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                              Long idComponent, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idComponent Which Component
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idComponent, Class<T> relationClass);

    /**
     * Creating an empty relation for an Component
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> T save(T relation);
}
