package at.decisionexpert.neo4jentity.dto.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelRelatedGuidanceModelsDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String description;

    private Integer ordering;

    public DecisionGuidanceModelRelatedGuidanceModelsDto() {
    }

    public DecisionGuidanceModelRelatedGuidanceModelsDto(HasRelatedGuidanceModels hasRelatedGuidanceModels) {
        setId(hasRelatedGuidanceModels.getId());
        setName(hasRelatedGuidanceModels.getEndNode().getName());
        setIdAttribute(hasRelatedGuidanceModels.getEndNode().getId());
        setDescription(hasRelatedGuidanceModels.getDescription());
        setOrdering(hasRelatedGuidanceModels.getOrdering());
        setIdAttribute(hasRelatedGuidanceModels.getEndNode().getId());
    }

    public DecisionGuidanceModelRelatedGuidanceModelsDto(DecisionGuidanceModel dgmAttribute) {
        idAttribute = dgmAttribute.getId();
        name = dgmAttribute.getName();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
