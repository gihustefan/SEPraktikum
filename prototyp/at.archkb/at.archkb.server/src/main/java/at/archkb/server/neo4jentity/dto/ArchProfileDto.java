package at.archkb.server.neo4jentity.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import at.archkb.server.neo4jentity.node.CoreData;
import com.fasterxml.jackson.annotation.JsonInclude;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchProfileDto {

    private Serializable id;

    private String title;

    private Serializable ownerId;

    private String ownerName;

    private String description;

    private LocalDateTime created;

    private LocalDateTime modified;

    private Boolean published;

    private List<ArchProfileRelationDto> qualityAttributes;

    private List<ArchProfileRelationDto> constraints;

    private List<ArchProfileTradeoffRelationDto> tradeoffs;

    private List<ArchProfileRelationDto> architecturestyles;

    private List<ArchProfileRelationDto> drivers;

    private List<ArchProfileRelationDto> designDecisions;

    private List<DiagramDto> diagrams;

    public ArchProfileDto() {
        super();
    }

    /**
     * Initialize DTO based on a given Neo4J ArchProfile node
     *
     * @param archProfile Neo4J ArchProfile node
     */
    public ArchProfileDto(ArchProfile archProfile) {
        setId(archProfile.getId());
        setTitle(archProfile.getTitle());

        if (archProfile.getCreator() != null) {
            setOwnerId(archProfile.getCreator().getId());
            setOwnerName(archProfile.getCreator().getOriginalUsername());
        }

        setDescription(archProfile.getDescription());
        setCreated(LocalDateTime.ofInstant(Instant.ofEpochMilli(archProfile.getCreationDate()),
                TimeZone.getDefault().toZoneId()));
        setModified(LocalDateTime.ofInstant(Instant.ofEpochMilli(archProfile.getLastModified()),
                TimeZone.getDefault().toZoneId()));

        setPublished(archProfile.isPublished());

        // Quality Attributes
        setQualityAttributes(getNeo4JRelationDto(archProfile.getQualityAttributes()));
        // Constraints
        setConstraints(getNeo4JRelationDto(archProfile.getConstraints()));
        // ArchitecutrStyles
        setArchitecturestyles(getNeo4JRelationDto(archProfile.getArchstyles()));
        // Drivers
        setDrivers(getNeo4JRelationDto(archProfile.getDrivers()));
        // Design Decisions
        setDesignDecisions(getNeo4JRelationDto(archProfile.getDesignDecisions()));

        // Diagrams
        List<DiagramDto> diagrams = new ArrayList<>();
        archProfile.getDiagrams().forEach(diagram -> {
            diagrams.add(new DiagramDto(diagram));
        });
        setDiagrams(diagrams);

        // Tradeoffs
        List<ArchProfileTradeoffRelationDto> tradeoffs = new ArrayList<>();
        archProfile.getTradeoffs().forEach(to -> {
            tradeoffs.add(new ArchProfileTradeoffRelationDto(to));
        });

        setTradeoffs(tradeoffs);

    }

    /**
     * Simple Help method for converting Neo4J ArchProfile Attribute
     * Relationships into ArchProfileRaleationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<ArchProfileRelationDto> getNeo4JRelationDto(
            Collection<? extends APAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<ArchProfileRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new ArchProfileRelationDto(item));
        });
        return result;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Serializable getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Serializable ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ArchProfileRelationDto> getQualityAttributes() {
        return qualityAttributes;
    }

    public void setQualityAttributes(List<ArchProfileRelationDto> qualityAttributes) {
        this.qualityAttributes = qualityAttributes;
    }

    public List<ArchProfileRelationDto> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ArchProfileRelationDto> constraints) {
        this.constraints = constraints;
    }

    public List<ArchProfileTradeoffRelationDto> getTradeoffs() {
        return tradeoffs;
    }

    public void setTradeoffs(List<ArchProfileTradeoffRelationDto> tradeoffs) {
        this.tradeoffs = tradeoffs;
    }

    public List<ArchProfileRelationDto> getArchitecturestyles() {
        return architecturestyles;
    }

    public void setArchitecturestyles(List<ArchProfileRelationDto> architecturestyles) {
        this.architecturestyles = architecturestyles;
    }

    public List<ArchProfileRelationDto> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<ArchProfileRelationDto> drivers) {
        this.drivers = drivers;
    }

    public List<ArchProfileRelationDto> getDesignDecisions() {
        return designDecisions;
    }

    public void setDesignDecisions(List<ArchProfileRelationDto> designDecisions) {
        this.designDecisions = designDecisions;
    }

    public List<DiagramDto> getDiagrams() {
        return diagrams;
    }

    public void setDiagrams(List<DiagramDto> diagrams) {
        this.diagrams = diagrams;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setCreated(Long created) {
        this.created =LocalDateTime.ofInstant(Instant.ofEpochMilli(created),
                TimeZone.getDefault().toZoneId());
    }

    public void setModified(Long modified) {
        this.modified = LocalDateTime.ofInstant(Instant.ofEpochMilli(modified),
                TimeZone.getDefault().toZoneId());
    }
}
