package at.decisionexpert.neo4jentity.dto.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@QueryResult
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignOptionDto {

    private Serializable id;

    private String name;

    private String description;

    private List<DesignOptionRelationDto> implications;

    private List<DesignOptionRelationDto> addressedRequirements;

    private List<DesignOptionRelationDto> effectedGuidanceModels;

    private List<DesignOptionRelationDto> requiredComponents;

    private List<VoteRelationDto> votes;

    private int voteTrue;

    private int voteFalse;

    public DesignOptionDto() {

    }

    public DesignOptionDto(DesignOption designOption) {
        setId(designOption.getId());
        setName(designOption.getName());
        setDescription(designOption.getDescription());

        // Implications
        setImplications(getNeo4JRelationDto(designOption.getImplications()));

        // Requirements
        setAddressedRequirements(getNeo4JRelationDto(designOption.getAddressedRequirements()));

        // EffectedTopics
        setEffectedGuidanceModels(getNeo4JRelationDto(designOption.getEffectedGuidanceModels()));

        //RequiredComponents
        setRequiredComponents(getNeo4JRelationDto(designOption.getRequiredComponents()));

        //Votes
        List<VoteRelationDto> votes = new ArrayList<>();
        designOption.getVotes().forEach(to -> {
            votes.add(new VoteRelationDto(to));
        });
        setVotes(votes);
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<DesignOptionRelationDto> getNeo4JRelationDto(
            Collection<? extends DOAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<DesignOptionRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new DesignOptionRelationDto(item));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DesignOptionRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<DesignOptionRelationDto> implications) {
        this.implications = implications;
    }

    public List<DesignOptionRelationDto> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(List<DesignOptionRelationDto> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public List<DesignOptionRelationDto> getEffectedGuidanceModels() {
        return effectedGuidanceModels;
    }

    public void setEffectedGuidanceModels(List<DesignOptionRelationDto> effectedTopics) {
        this.effectedGuidanceModels = effectedGuidanceModels;
    }

    public List<DesignOptionRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DesignOptionRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
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
}
