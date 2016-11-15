package at.decisionexpert.neo4jentity.dto.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasEffectedGuidanceModels;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignOptionEffectedGuidanceModelsDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String description;

    private Integer ordering;

    public DesignOptionEffectedGuidanceModelsDto() {
    }

    public DesignOptionEffectedGuidanceModelsDto(HasEffectedGuidanceModels hasEffectedGuidanceModels) {
        setId(hasEffectedGuidanceModels.getId());
        setName(hasEffectedGuidanceModels.getEndNode().getName());
        setIdAttribute(hasEffectedGuidanceModels.getEndNode().getId());
        setDescription(hasEffectedGuidanceModels.getDescription());
        setOrdering(hasEffectedGuidanceModels.getOrdering());
        setIdAttribute(hasEffectedGuidanceModels.getEndNode().getId());
    }


    public DesignOptionEffectedGuidanceModelsDto(DecisionGuidanceModel dgmAttribute) {
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
