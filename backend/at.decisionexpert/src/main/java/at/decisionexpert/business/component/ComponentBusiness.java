package at.decisionexpert.business.component;

import at.decisionexpert.exception.ComponentNotFoundException;
import at.decisionexpert.neo4jentity.dto.component.ComponentDto;
import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface ComponentBusiness {

    ComponentDto getComponent(Long componentId) throws ComponentNotFoundException;

    /**
     * Adding an empty relation attribute for a given Component
     *
     * @param idComponent Which Component
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new ComponentRelation
     */
    <T extends ComponentAttributeRelationship<A>, A extends CoreData> ComponentRelationDto createRelation(
            Long idComponent, ComponentRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing Component Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idComponent Which Component
     *
     * @param idComponentRelation Which Component Relationship (e.g. Component Quality
     *                              Attribute)
     * @param newValues             The new Values for the Component Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted Component Relation
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> ComponentRelationDto updateExistingRelationAttribute(
            Long idComponent, Long idComponentRelation, ComponentRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing Component Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idComponent         Which Component
     * @param idComponentRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends ComponentAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idComponent, Long idComponentRelation, Class<T> relationClass);
}
