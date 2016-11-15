package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.HasComment;
import at.decisionexpert.neo4jentity.relationship.HasVote;
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

    @Relationship(type = "HAS_COMMENT", direction = Relationship.OUTGOING)
    private Set<HasComment> comments = new HashSet<>(0);

    @Relationship(type = "HAS_VOTE", direction = Relationship.OUTGOING)
    private Set<HasVote> votes = new HashSet<>(0);

    private String name;

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

    public Set<HasComment> getComments() {
        return comments;
    }

    public void setComments(Set<HasComment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<HasVote> getVotes() {
        return votes;
    }

    public void setVotes(Set<HasVote> votes) {
        this.votes = votes;
    }
}
