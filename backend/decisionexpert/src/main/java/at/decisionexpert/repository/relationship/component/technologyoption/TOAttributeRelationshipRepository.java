package at.decisionexpert.repository.relationship.component.technologyoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TOAttributeRelationshipRepository {

    /**
     * Loading a single TechnologyOption Relation by a given class and an Id
     *
     * @param clazz                 Which class
     * @param idTechnologyOptionRelation Which Element - identified by id
     * @return A single TechnologyOption Relation Attribute
     */
    <T extends TOAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                       Long idTechnologyOptionRelation);

    /**
     * Loading a single TechnologyOption Relation by a given class, the given
     * TechnologyOption and the ordering of the TechnologyOption Relation
     *
     * @param clazz         Which TechnologyOption Relation Class
     * @param idTechnologyOption Which TechnologyOption
     * @param ordering      The ordering of the TechnologyOption Relation
     * @return A single TechnologyOption Relation Attribute - based on the input
     */
    <T extends TOAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                             Long idTechnologyOption, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends TOAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idTechnologyOption Which TechnologyOption
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends TOAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idTechnologyOption, Class<T> relationClass);

    /**
     * Creating an empty relation for an TechnologyOption
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends TOAttributeRelationship<? extends CoreData>> T save(T relation);
}
