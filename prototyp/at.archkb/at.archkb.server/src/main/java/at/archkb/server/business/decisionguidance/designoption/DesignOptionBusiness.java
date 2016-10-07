package at.archkb.server.business.decisionguidance.designoption;

import at.archkb.server.exception.DesignOptionNotFoundException;
import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionChangeRequestDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DesignOptionBusiness {

    DesignOptionDto getDesignOption(Long id) throws DesignOptionNotFoundException;

    DesignOptionDto createDesignOption(DesignOptionChangeRequestDto designOption);

    DesignOptionDto updateDesignOptionProperties(Long id, DesignOptionChangeRequestDto newValues);

    void deleteDesignOption(Long id);

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
    <T extends DOAttributeRelationship<A>, A extends CoreData> DesignOptionRelationDto createRelation(
            Long idDesignOption, DesignOptionRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Generic service for updating an existing DesignOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDesignOption Which DesignOption
     *
     * @param idDesignOptionRelation Which DesignOption Relationship (e.g. DesignOption Quality
     *                              Attribute)
     * @param newValues             The new Values for the DesignOption Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted DesignOption Relation
     */
    <T extends DOAttributeRelationship<? extends CoreData>> DesignOptionRelationDto updateExistingRelationAttribute(
            Long idDesignOption, Long idDesignOptionRelation, DesignOptionRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing DesignOption Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idDesignOption         Which DesignOption
     * @param idDesignOptionRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends DOAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idDesignOption, Long idDesignOptionRelation, Class<T> relationClass);

    /**
     * Creating A new Tradeoff Relation
     *
     * @param idDesignOption Which ArchProfile
     * @param guidanceModelInfo  Tradeoff values
     * @return newly created Tradeoff Dto
     */
    DesignOptionEffectedGuidanceModelsDto createGuidanceModelRelation(Long idDesignOption, DesignOptionEffectedGuidanceModelsDto guidanceModelInfo);

    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idDesignOption                Which ArchProfile
     * @param idDesignOptionRelation        Which ArchProfile Relation
     * @param newValues                     The new Values
     * @return The altered ArchProfile Tradeoff Relation dto
     */
    DesignOptionEffectedGuidanceModelsDto updateExistingGuidanceModelRelationAttribute(Long idDesignOption, Long idDesignOptionRelation, DesignOptionEffectedGuidanceModelsDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idDesignOption                 Which ArchProfile
     * @param idDesignOptionRelation         Which ArchProfileRelation
     */
    void deleteGuidanceModelRelationAttribute(Long idDesignOption, Long idDesignOptionRelation);
}
