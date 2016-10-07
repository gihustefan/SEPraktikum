package at.archkb.server.business.archprofile;

import at.archkb.server.exception.ArchProfileNotFoundException;
import at.archkb.server.neo4jentity.dto.*;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;
import org.springframework.web.multipart.MultipartFile;

public interface ArchProfileBusiness {

    ArchProfileDto getArchProfile(Long id) throws ArchProfileNotFoundException;

    ArchProfileDto createArchProfile(ArchProfileChangeRequestDto archProfile);

    ArchProfileDto updateArchProfileProperties(Long idArchProfile, ArchProfileChangeRequestDto newValues);

    void deleteArchProfile(Long id);

    /**
     * Getting the newest arch Profiles
     *
     * @param page            which page
     * @param size            how many items per page
     * @param withUnpublished flag if the unpublished should be fetched or not
     * @return The list of the newest ArchProfiles
     */
    ArchProfilePageableDto getNewestArchProfiles(Integer page, Integer size, boolean withUnpublished);

    /**
     * Getting the ArchProfiles of a specific user
     *
     * @param idUser Which user
     * @param page   which page
     * @param size   how many items per page
     * @return The list of the newest ArchProfiles
     */
    ArchProfilePageableDto getUserArchProfiles(Long idUser, Integer page, Integer size);

    /**
     * Generic service for updating an existing ArchProfile Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idArchProfileRelation Which ArchProfile Relationship (e.g. ArchProfile Quality
     *                              Attribute)
     * @param newValues             The new Values for the ArchProfile Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted ArchProfile Relation
     */
    <T extends APAttributeRelationship<? extends CoreData>> ArchProfileRelationDto updateExistingRelationAttribute(
            Long idArchProfile, Long idArchProfileRelation, ArchProfileRelationDto newValues,
            Class<T> clazz);

    /**
     * Generic service for deleting an existing ArchProfile Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idArchProfile         Which ArchProfile
     * @param idArchProfileRelation Which Relation
     * @param relationClass         The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     */
    <T extends APAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            Long idArchProfile, Long idArchProfileRelation, Class<T> relationClass);

    /**
     * Adding an empty relation attribute for a given ArchProfile
     *
     * @param idArchProfile Which ArchProfile
     * @param attributeInfo The DTO where the information's of the relation and the toNode are stored (from client)
     * @param relationClass Which Relation (Given by Type)
     * @param toNodeType    To which node type
     * @param <T>           Type identifier for Relation Class
     * @return a new ArchProfileRelation
     */
    <T extends APAttributeRelationship<A>, A extends CoreData> ArchProfileRelationDto createRelation(
            Long idArchProfile, ArchProfileRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);

    /**
     * Creating a new Diagram upload and creating db reference
     *
     * @param idArchProfile Which ArchProfile
     * @param diagramFile   The Diagram File
     * @return the newly created information about the diagram file
     */
    DiagramDto createDiagram(Long idArchProfile, MultipartFile diagramFile);

    /**
     * Updating an existing diagram
     *
     * @param idDiagram     Which diagram
     * @param idArchProfile Which Architecture Proifle
     * @param newValues     The new values for the diagram
     * @return updated diagram
     */
    DiagramDto updateDiagram(Long idArchProfile, Long idDiagram, DiagramDto newValues);


    /**
     * Deleting a diagram
     *
     * @param idArchProfile Which ArchProfile
     * @param idDiagram     Which Diagram
     */
    void deleteDiagram(Long idArchProfile, Long idDiagram);

    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idArchProfile                 Which ArchProfile
     * @param idArchProfileTradeoffRelation Which ArchProfile Relation
     * @param newValues                     The new Values
     * @return The altered ArchProfile Tradeoff Relation dto
     */
    ArchProfileTradeoffRelationDto updateExistingTradeoffRelationAttribute(Long idArchProfile, Long idArchProfileTradeoffRelation, ArchProfileTradeoffRelationDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idArchProfile                 Which ArchProfile
     * @param idArchProfileTradeoffRelation Which ArchProfileRelation
     */
    void deleteArchProfileTradeoffRelation(Long idArchProfile, Long idArchProfileTradeoffRelation);

    /**
     * Creating A new Tradeoff Relation
     *
     * @param idArchProfile Which ArchProfile
     * @param tradeoffInfo  Tradeoff values
     * @return newly created Tradeoff Dto
     */
    ArchProfileTradeoffRelationDto createTradeoffRelation(Long idArchProfile, ArchProfileTradeoffRelationDto tradeoffInfo);
}
