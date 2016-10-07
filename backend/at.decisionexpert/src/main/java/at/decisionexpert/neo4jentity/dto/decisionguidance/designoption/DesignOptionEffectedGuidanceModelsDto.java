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

    private String title;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    public DesignOptionEffectedGuidanceModelsDto() {
    }

    public DesignOptionEffectedGuidanceModelsDto(HasEffectedGuidanceModels hasEffectedGuidanceModels) {
        setId(hasEffectedGuidanceModels.getId());
        setTitle(hasEffectedGuidanceModels.getEndNode().getTitle());
        setIdAttribute(hasEffectedGuidanceModels.getEndNode().getId());
        setDescription(hasEffectedGuidanceModels.getDescription());
        setOrdering(hasEffectedGuidanceModels.getOrdering());
        setIdAttribute(hasEffectedGuidanceModels.getEndNode().getId());
    }


    public DesignOptionEffectedGuidanceModelsDto(DecisionGuidanceModel dgmAttribute) {
        idAttribute = dgmAttribute.getId();
        title = dgmAttribute.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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
