package at.archkb.server.repository.relationship;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;

public interface APAttributeRelationshipRepository {
    /**
     * Loading a single ArchProfile Relation by a given class and an Id
     *
     * @param clazz                 Which class (e.g. HAS_QUALITYATTRIBUTES)
     * @param idArchProfileRelation Which Element - identified by id
     * @return A single ArchProfile Relation Attribute
     */
    <T extends APAttributeRelationship<? extends CoreData>> T findById(Class<T> clazz,
                                                                       Long idArchProfileRelation);

    /**
     * Loading a single ArchProfile Relation by a given class, the given
     * ArchProfile and the ordering of the ArchProfile Relation
     *
     * @param clazz         Which ArchProfile Relation Class
     * @param idArchProfile Which arch Profile
     * @param ordering      The ordering of the ArchProfile Relation
     * @return A single ArchProfile Relation Attribute - based on the input
     */
    <T extends APAttributeRelationship<? extends CoreData>> T findByOrdering(Class<T> clazz,
                                                                             Long idArchProfile, Integer ordering);

    /**
     * Delete a Relation Attribute
     *
     * @param relation the Relation Attribute to delete
     */
    <T extends APAttributeRelationship<? extends CoreData>> void delete(T relation);

    /**
     * Requesting a list of AP Relationships with a ordering greater than a
     * given value. Needed for example when deleting an item -> all the others
     * ordering must be correctly adapted
     *
     * @param idArchProfile Which ArchProfile
     * @param relationClass Which Relationship
     * @return A List of AP Relationships with ordering bigger than the given
     * threshold
     */
    <T extends APAttributeRelationship<? extends CoreData>> Iterable<T> findAllRelations(
            Long idArchProfile, Class<T> relationClass);

    /**
     * Creating an empty relation for an ArchProfile
     *
     * @param relation Which Relation
     * @param <T>      Type identifying the relation
     * @return the created, empty Relation
     */
    <T extends APAttributeRelationship<? extends CoreData>> T save(T relation);
}
