package at.decisionexpert.business.component.technologyoption;

import at.decisionexpert.exception.TechnologyOptionNotFoundException;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TechnologyOptionBusiness {

    TechnologyOptionDto getTechnologyOption(Long technologyOptionId) throws TechnologyOptionNotFoundException;

    /**
     * Adding an empty relation attribute for a given TechnologyOption
     *
     * @param idTechnologyOption Which TechnologyOption
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new TechnologyOptionRelation
     */
    <T extends TOAttributeRelationship<A>, A extends CoreData> TechnologyOptionRelationDto createRelation(
            Long idTechnologyOption, TechnologyOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing TechnologyOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idTechnologyOption Which TechnologyOption
     *
     * @param idTechnologyOptionRelation Which TechnologyOption Relationship (e.g. TechnologyOption Quality
     *                              Attribute)
     * @param newValues             The new Values for the TechnologyOption Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted TechnologyOption Relation
     */
    <T extends TOAttributeRelationship<? extends CoreData>> TechnologyOptionRelationDto updateExistingRelationAttribute(
            Long idTechnologyOption, Long idTechnologyOptionRelation, TechnologyOptionRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing TechnologyOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idTechnologyOption         Which TechnologyOption
     * @param idTechnologyOptionRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends TOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idTechnologyOption, Long idTechnologyOptionRelation, Class<T> relationClass);
}
