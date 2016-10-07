package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasDesignOption;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasPotentialRequirement;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@NodeEntity
public class DecisionGuidanceModel extends Node {

    @Relationship(type = "HAS_POTENTIALREQUIREMENT", direction = Relationship.OUTGOING)
    private Set<HasPotentialRequirement> potentialRequirements = new HashSet<>(0);

    @Relationship(type = "HAS_DESIGNOPTION", direction = Relationship.OUTGOING)
    private Set<HasDesignOption> designOptions = new HashSet<>(0);

    @Relationship(type = "HAS_RELATEDGUIDANCEMODEL", direction = Relationship.OUTGOING)
    private Set<HasRelatedGuidanceModels> relatedGuidanceModels = new HashSet<>(0);

    private String title;

    private String description;

    private Boolean published;

    public DecisionGuidanceModel (User creator) {
        super(creator);
    }

    public DecisionGuidanceModel () {
        super();
    }

    public Set<HasPotentialRequirement> getPotentialRequirements() {
        return potentialRequirements;
    }

    public void setPotentialRequirements(Set<HasPotentialRequirement> potentialRequirements) {
        this.potentialRequirements = potentialRequirements;
    }

    public Set<HasDesignOption> getDesignOptions() {
        return designOptions;
    }

    public void setDesignOptions(Set<HasDesignOption> designOptions) {
        this.designOptions = designOptions;
    }

    public Set<HasRelatedGuidanceModels> getRelatedGuidanceModels() {
        return relatedGuidanceModels;
    }

    public void setRelatedGuidanceModels(Set<HasRelatedGuidanceModels> relatedTopics) {
        this.relatedGuidanceModels = relatedGuidanceModels;
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
