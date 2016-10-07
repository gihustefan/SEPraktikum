package at.archkb.server.service.archprofile;

import at.archkb.server.neo4jentity.dto.*;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface ArchProfileService {

    ArchProfileDto getArchProfile(@NotNull Long id);

    /**
     * Getting the newest arch Profiles
     *
     * @param page which page
     * @param size how many items per page
     * @return The list of the newest ArchProfiles
     */
    ArchProfilePageableDto getNewestArchProfiles(@NotNull Integer page, @NotNull Integer size);

    /**
     * Getting the ArchProfiles of a specific user
     *
     * @param idUser Which user
     * @param page   which page
     * @param size   how many items per page
     * @return The list of the newest ArchProfiles
     */
    ArchProfilePageableDto getUserArchProfiles(@NotNull Long idUser, @NotNull Integer page, @NotNull Integer size);

    // Must be authenticated to create ArchProfiles
    @PreAuthorize("hasRole('ROLE_USER')")
    ArchProfileDto createArchProfile(ArchProfileChangeRequestDto archProfile);

    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    ArchProfileDto updateArchProfileProperties(@NotNull Long id, @NotNull ArchProfileChangeRequestDto newValues);

    // Can only delete ArchProfile if is authenticated and owner
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    void deleteArchProfile(@NotNull Long id);

    /**
     * Generic service for updating an existing ArchProfile Relationship (e.g.
     * Quality Attributes, etc.) - for neo4J relationships
     *
     * @param idArchProfile         Which ArchProfile
     * @param idArchProfileRelation Which ArchProfile Relationship (e.g. ArchProfile Quality
     *                              Attribute)
     * @param newValues             The new Values for the ArchProfile Relation -> for convinience
     *                              stored in a model
     * @param clazz                 The neo4j class information of the Type T (needed, because no
     *                              class information at runtime)
     * @return The updated and already persisted ArchProfile Relation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    <T extends APAttributeRelationship<? extends CoreData>> ArchProfileRelationDto updateExistingRelationAttribute(
            @NotNull Long idArchProfile, @NotNull Long idArchProfileRelation, ArchProfileRelationDto newValues,
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
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    <T extends APAttributeRelationship<? extends CoreData>> void deleteRelationAttribute(
            @NotNull Long idArchProfile, @NotNull Long idArchProfileRelation, Class<T> relationClass);

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
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    <T extends APAttributeRelationship<A>, A extends CoreData> ArchProfileRelationDto createRelation(
            @NotNull Long idArchProfile, ArchProfileRelationDto attributeInfo, Class<T> relationClass, Class<A> toNodeType);


    /**
     * Creating a new Diagram Relation --> cannot use generic one, because of the File part
     *
     * @param idArchProfile Which ArchProfile
     * @param imageFile     The Image that needs to be uploaded
     * @return A newly created ArchProfileRelation Dto with all the necessary information
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    DiagramDto createDiagram(@NotNull Long idArchProfile, @NotNull MultipartFile imageFile);

    /**
     * Updating an existing diagram
     *
     * @param idDiagram     Which diagram
     * @param idArchProfile Which Architecture Proifle
     * @param newValues     The new values for the diagram
     * @return updated diagram
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    DiagramDto updateDiagram(@NotNull Long idArchProfile, @NotNull Long idDiagram, @NotNull DiagramDto newValues);


    /**
     * Deleting a diagram
     *
     * @param idArchProfile Which ArchProfile
     * @param idDiagram     Which Diagram
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    void deleteDiagram(@NotNull Long idArchProfile, @NotNull Long idDiagram);

    /**
     * Updating existing Tradeoff Relation Attributes
     *
     * @param idArchProfile         Which ArchProfile
     * @param idArchProfileRelation Which ArchProfile Relation
     * @param newValues             The new Values
     * @return The altered ArchProfile Tradeoff Relation dto
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    ArchProfileTradeoffRelationDto updateExistingTradeoffRelationAttribute(@NotNull Long idArchProfile, @NotNull Long idArchProfileRelation, @NotNull ArchProfileTradeoffRelationDto newValues);

    /**
     * Deleting an existing Tradeoff Relation
     *
     * @param idArchProfile                 Which ArchProfile
     * @param idArchProfileTradeoffRelation Which ArchProfileRelation
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    void deleteArchProfileTradeoffRelation(@NotNull Long idArchProfile, @NotNull Long idArchProfileTradeoffRelation);

    /**
     * Creating A new Tradeoff Relation
     *
     * @param idArchProfile Which ArchProfile
     * @param tradeoffInfo  Tradeoff values
     * @return newly created Tradeoff Dto
     */
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#idArchProfile, 'at.archkb.server.neo4jentity.node.ArchProfile', 'OWNER')")
    ArchProfileTradeoffRelationDto createTradeoffRelation(@NotNull Long idArchProfile, ArchProfileTradeoffRelationDto tradeoffInfo);

}
