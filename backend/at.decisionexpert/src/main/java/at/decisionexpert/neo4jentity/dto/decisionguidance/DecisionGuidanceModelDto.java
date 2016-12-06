package at.decisionexpert.neo4jentity.dto.decisionguidance;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@QueryResult
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelDto {

    private Serializable id;

    private String name;

    private Serializable ownerId;

    private String ownerName;

    private String description;

    private LocalDateTime created;

    private LocalDateTime modified;

    private Boolean published;

    private int voteTrue;

    private int voteFalse;

    private List<DecisionGuidanceModelRelationDto> potentialRequirements;

    private List<DecisionGuidanceModelDesignOptionRelationDto> designOptions;

    private List<DecisionGuidanceModelRelatedGuidanceModelsDto> relatedGuidanceModels;

    private List<CommentRelationDto> comments;

    private List<VoteRelationDto> votes;

    private BelongingRelationDto belonging;

    public DecisionGuidanceModelDto() {
        super();
    }

    /**
     * Initialize DTO based on a given Neo4J ArchProfile node
     *
     * @param decisionGuidanceModel Neo4J ArchProfile node
     */
    public DecisionGuidanceModelDto(DecisionGuidanceModel decisionGuidanceModel) {
        setId(decisionGuidanceModel.getId());
        setName(decisionGuidanceModel.getName());

        if (decisionGuidanceModel.getCreator() != null) {
            setOwnerId(decisionGuidanceModel.getCreator().getId());
            setOwnerName(decisionGuidanceModel.getCreator().getOriginalUsername());
        }

        setDescription(decisionGuidanceModel.getDescription());
        setCreated(LocalDateTime.ofInstant(Instant.ofEpochMilli(decisionGuidanceModel.getCreationDate()),
                TimeZone.getDefault().toZoneId()));
        setModified(LocalDateTime.ofInstant(Instant.ofEpochMilli(decisionGuidanceModel.getLastModified()),
                TimeZone.getDefault().toZoneId()));
        setPublished(decisionGuidanceModel.isPublished());

        // Requirements
        setPotentialRequirements(getNeo4JRelationDto(decisionGuidanceModel.getPotentialRequirements()));

        // Design Options
        List<DecisionGuidanceModelDesignOptionRelationDto> designOptions = new ArrayList<>();
        decisionGuidanceModel.getDesignOptions().forEach(to -> {
            designOptions.add(new DecisionGuidanceModelDesignOptionRelationDto(to));
        });
        setDesignOptions(designOptions);

        // Related Topics
        List<DecisionGuidanceModelRelatedGuidanceModelsDto> relatedGuidanceModels = new ArrayList<>();
        decisionGuidanceModel.getRelatedGuidanceModels().forEach(to -> {
            relatedGuidanceModels.add(new DecisionGuidanceModelRelatedGuidanceModelsDto(to));
        });
        setRelatedGuidanceModels(relatedGuidanceModels);

        //Comments
        List<CommentRelationDto> comments = new ArrayList<>();
        decisionGuidanceModel.getComments().forEach(to -> {
            comments.add(new CommentRelationDto(to));
        });
        setComments(comments);

        //Votes
        List<VoteRelationDto> votes = new ArrayList<>();
        decisionGuidanceModel.getVotes().forEach(to -> {
            votes.add(new VoteRelationDto(to));
            if (to.isVote()) {
                setVoteTrue(getVoteTrue()+1);
            } else {
                setVoteFalse(getVoteFalse()+1);
            }
        });
        setVotes(votes);

        //Belonging
        if (decisionGuidanceModel.getBelonging() != null) {
            setBelonging(new BelongingRelationDto(decisionGuidanceModel.getBelonging()));
        }
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<DecisionGuidanceModelRelationDto> getNeo4JRelationDto(
            Collection<? extends DGMAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<DecisionGuidanceModelRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new DecisionGuidanceModelRelationDto(item));
        });
        return result;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<DecisionGuidanceModelRelationDto> getPotentialRequirements() {
        return potentialRequirements;
    }

    public void setPotentialRequirements(List<DecisionGuidanceModelRelationDto> potentialRequirements) {
        this.potentialRequirements = potentialRequirements;
    }

    public List<DecisionGuidanceModelDesignOptionRelationDto> getDesignOptions() {
        return designOptions;
    }

    public void setDesignOptions(List<DecisionGuidanceModelDesignOptionRelationDto> designOptions) {
        this.designOptions = designOptions;
    }

    public List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedGuidanceModels() {
        return relatedGuidanceModels;
    }

    public void setRelatedGuidanceModels(List<DecisionGuidanceModelRelatedGuidanceModelsDto> relatedGuidanceModels) {
        this.relatedGuidanceModels = relatedGuidanceModels;
    }

    public List<CommentRelationDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentRelationDto> comments) {
        this.comments = comments;
    }

    public void setCreated(Long created) {
        this.created = LocalDateTime.ofInstant(Instant.ofEpochMilli(created),
                TimeZone.getDefault().toZoneId());
    }

    public void setModified(Long modified) {
        this.modified = LocalDateTime.ofInstant(Instant.ofEpochMilli(modified),
                TimeZone.getDefault().toZoneId());
    }

    public List<VoteRelationDto> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteRelationDto> votes) {
        this.votes = votes;
    }

    public int getVoteTrue() {
        return voteTrue;
    }

    public void setVoteTrue(int voteTrue) {
        this.voteTrue = voteTrue;
    }

    public int getVoteFalse() {
        return voteFalse;
    }

    public void setVoteFalse(int voteFalse) {
        this.voteFalse = voteFalse;
    }

    public BelongingRelationDto getBelonging() {
        return belonging;
    }

    public void setBelonging(BelongingRelationDto belonging) {
        this.belonging = belonging;
    }
}
