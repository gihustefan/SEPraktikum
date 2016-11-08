package at.decisionexpert.service.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentDto;
import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface ComponentService {

    ComponentDto getComponent(@NotNull Long componentId);

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
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idComponent, 'at.decisionexpert.neo4jentity.node.Component', 'OWNER')")
    <T extends ComponentAttributeRelationship<A>, A extends CoreData> ComponentRelationDto createRelation(
            @NotNull Long idComponent, ComponentRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing Component Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idComponent         Which Component
     * @param idComponentRelation Which Component Relationship (e.g. Component Quality
     *                              Attribute)
     * @param newValues             The new Values for the Component Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted Component Relation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idComponent, 'at.decisionexpert.neo4jentity.node.Component', 'OWNER')")
    <T extends ComponentAttributeRelationship<? extends CoreData>> ComponentRelationDto updateExistingRelationAttribute(
            @NotNull Long idComponent, @NotNull Long idComponentRelation, ComponentRelationDto newValues,
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
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idComponent, 'at.decisionexpert.neo4jentity.node.Component', 'OWNER')")
    <T extends ComponentAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idComponent, @NotNull Long idComponentRelation, Class<T> relationClass);
}
