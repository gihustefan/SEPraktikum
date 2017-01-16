package at.decisionexpert.service.decisionguidancemodel.designoption;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DesignOptionService {

    DesignOptionDto getDesignOption(@NotNull Long id);


    @PreAuthorize("hasRole('ROLE_USER')")
    DesignOptionDto createDesignOption(DesignOptionChangeRequestDto designOption);

    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    DesignOptionDto updateDesignOptionProperties(@NotNull Long id, @NotNull DesignOptionChangeRequestDto newValues);

    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.decisionexpert.neo4jentity.node.DecisionGuidanceModel', 'OWNER')")
    void deleteDesignOption(@NotNull Long id);

    /**
     * Adding an empty relation attribute for a given DesignOption
     *
     * @param idDesignOption Which DesignOption
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new DesignOptionRelation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDesignOption, 'at.decisionexpert.neo4jentity.node.DesignOption', 'OWNER')")
    <T extends DOAttributeRelationship<A>, A extends CoreData> DesignOptionRelationDto createRelation(
            @NotNull Long idDesignOption, DesignOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DesignOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDesignOption        Which DesignOption
     * @param idDesignOptionRelation Which DesignOption Relationship (e.g. DesignOption Quality
     *                              Attribute)
     * @param newValues             The new Values for the DesignOption Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted DesignOption Relation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDesignOption, 'at.decisionexpert.neo4jentity.node.DesignOption', 'OWNER')")
    <T extends DOAttributeRelationship<A>, A extends CoreData> DesignOptionRelationDto updateExistingRelationAttribute(
            @NotNull Long idDesignOption, @NotNull Long idDesignOptionRelation, DesignOptionRelationDto newValues,
            Class<T> clazz, Class<A> toNodeType);

    /**
     * Generic service for deleting an existing DesignOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDesignOption         Which DesignOption
     * @param idDesignOptionRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idDesignOption, 'at.decisionexpert.neo4jentity.node.DesignOption', 'OWNER')")
    <T extends DOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idDesignOption, @NotNull Long idDesignOptionRelation, Class<T> relationClass);
}
