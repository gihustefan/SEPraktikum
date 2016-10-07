package at.archkb.server.neo4jentity.node;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import at.archkb.server.neo4jentity.relationship.HasArchitectureStyle;
import at.archkb.server.neo4jentity.relationship.HasConstraint;
import at.archkb.server.neo4jentity.relationship.HasDesignDecision;
import at.archkb.server.neo4jentity.relationship.HasDriver;
import at.archkb.server.neo4jentity.relationship.HasQualityAttribute;
import at.archkb.server.neo4jentity.relationship.HasTradeoff;

@NodeEntity
public class ArchProfile extends Node {

    @Relationship(type = "HAS_DESIGN_DECISION", direction = Relationship.OUTGOING)
    private Set<HasDesignDecision> designDecisions = new HashSet<>(0);

    @Relationship(type = "HAS_DRIVER", direction = Relationship.OUTGOING)
    private Set<HasDriver> drivers = new HashSet<>(0);

    @Relationship(type = "HAS_DIAGRAM", direction = Relationship.OUTGOING)
    private Set<Diagram> diagrams = new HashSet<>(0);

    @Relationship(type = "HAS_QUALITYATTRIBUTE", direction = Relationship.OUTGOING)
    private Set<HasQualityAttribute> qualityAttributes = new HashSet<>(0);

    @Relationship(type = "HAS_ARCHITECTURE_STYLE", direction = Relationship.OUTGOING)
    private Set<HasArchitectureStyle> archstyles = new HashSet<>(0);

    @Relationship(type = "HAS_CONSTRAINT", direction = Relationship.OUTGOING)
    private Set<HasConstraint> constraints = new HashSet<>(0);

    @Relationship(type = "HAS_TRADEOFF", direction = Relationship.OUTGOING)
    private Set<HasTradeoff> tradeoffs = new HashSet<>(0);

    @Relationship(type = "HAS_COMMENT", direction = Relationship.OUTGOING)
    private Set<Comment> comments = new HashSet<>(0);

    private String title;

    private String description;

    private Boolean published;

    public ArchProfile(User creator) {
        super(creator);
    }

    public ArchProfile() {
        super();
    }

    public Set<HasDesignDecision> getDesignDecisions() {
        return designDecisions;
    }

    public void setDesignDecisions(Set<HasDesignDecision> designDecisions) {
        this.designDecisions = designDecisions;
    }

    public Set<HasDriver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<HasDriver> drivers) {
        this.drivers = drivers;
    }

    public Set<Diagram> getDiagrams() {
        return diagrams;
    }

    public void setDiagrams(Set<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public Set<HasQualityAttribute> getQualityAttributes() {
        return qualityAttributes;
    }

    public void setQualityAttributes(Set<HasQualityAttribute> qualityAttributes) {
        this.qualityAttributes = qualityAttributes;
    }

    public Set<HasArchitectureStyle> getArchstyles() {
        return archstyles;
    }

    public void setArchstyles(Set<HasArchitectureStyle> archstyles) {
        this.archstyles = archstyles;
    }

    public Set<HasConstraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(Set<HasConstraint> constraints) {
        this.constraints = constraints;
    }

    public Set<HasTradeoff> getTradeoffs() {
        return tradeoffs;
    }

    public void setTradeoffs(Set<HasTradeoff> tradeoffs) {
        this.tradeoffs = tradeoffs;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
