package at.decisionexpert.service.component.technologyoption;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;
import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TechnologyOptionService {

    TechnologyOptionDto getTechnologyOption(@NotNull Long componentId);

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
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idTechnologyOption, 'at.decisionexpert.neo4jentity.node.TechnologyOption', 'OWNER')")
    <T extends TOAttributeRelationship<A>, A extends CoreData> TechnologyOptionRelationDto createRelation(
            @NotNull Long idTechnologyOption, TechnologyOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing TechnologyOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idTechnologyOption         Which TechnologyOption
     * @param idTechnologyOptionRelation Which TechnologyOption Relationship (e.g. TechnologyOption Quality
     *                              Attribute)
     * @param newValues             The new Values for the TechnologyOption Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted TechnologyOption Relation
     */
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idTechnologyOption, 'at.decisionexpert.neo4jentity.node.TechnologyOption', 'OWNER')")
    <T extends TOAttributeRelationship<? extends CoreData>> TechnologyOptionRelationDto updateExistingRelationAttribute(
            @NotNull Long idTechnologyOption, @NotNull Long idTechnologyOptionRelation, TechnologyOptionRelationDto newValues,
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
    //    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idTechnologyOption, 'at.decisionexpert.neo4jentity.node.TechnologyOption', 'OWNER')")
    <T extends TOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idTechnologyOption, @NotNull Long idTechnologyOptionRelation, Class<T> relationClass);
}
