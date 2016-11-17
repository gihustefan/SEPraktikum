package at.decisionexpert.neo4jentity.dto.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasDesignOption;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelDesignOptionRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private Integer ordering;

    private int voteTrue;

    private int voteFalse;

    private List<DesignOptionRelationDto> implications;

    private List<DesignOptionRelationDto> addressedRequirements;

    private List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels;

    private List<DesignOptionRelationDto> requiredComponents;

    public DecisionGuidanceModelDesignOptionRelationDto() {
    }

    public DecisionGuidanceModelDesignOptionRelationDto(HasDesignOption hasDesignOption) {
        setId(hasDesignOption.getId());
        setName(hasDesignOption.getEndNode().getName());
        setIdAttribute(hasDesignOption.getEndNode().getId());
        setOrdering(hasDesignOption.getOrdering());
        setVoteTrue(hasDesignOption.getEndNode().getVoteTrue());
        setVoteFalse(hasDesignOption.getEndNode().getVoteFalse());
        setIdAttribute(hasDesignOption.getEndNode().getId());
        setImplications(getNeo4JRelationDto(hasDesignOption.getEndNode().getImplications()));
        setAddressedRequirements(getNeo4JRelationDto(hasDesignOption.getEndNode().getAddressedRequirements()));
        setRequiredComponents(getNeo4JRelationDto(hasDesignOption.getEndNode().getRequiredComponents()));

        // Effected Topics
        List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels = new ArrayList<>();
        hasDesignOption.getEndNode().getEffectedGuidanceModels().forEach(to -> {
            effectedGuidanceModels.add(new DesignOptionEffectedGuidanceModelsDto(to));
        });

        setEffectedGuidanceModels(effectedGuidanceModels);
    }

    public DecisionGuidanceModelDesignOptionRelationDto(DesignOption doAttribute) {
        idAttribute = doAttribute.getId();
        name = doAttribute.getName();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
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

    public List<DesignOptionEffectedGuidanceModelsDto> getEffectedGuidanceModels() {
        return effectedGuidanceModels;
    }

    public void setEffectedGuidanceModels(List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels) {
        this.effectedGuidanceModels = effectedGuidanceModels;
    }

    public List<DesignOptionRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DesignOptionRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
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
