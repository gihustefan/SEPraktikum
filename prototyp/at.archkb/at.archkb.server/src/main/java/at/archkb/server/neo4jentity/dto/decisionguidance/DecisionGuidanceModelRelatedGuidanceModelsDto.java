package at.archkb.server.neo4jentity.dto.decisionguidance;

import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import at.archkb.server.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelRelatedGuidanceModelsDto {

    private Long id;

    private String title;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    public DecisionGuidanceModelRelatedGuidanceModelsDto() {
    }

    public DecisionGuidanceModelRelatedGuidanceModelsDto(HasRelatedGuidanceModels hasRelatedGuidanceModels) {
        setId(hasRelatedGuidanceModels.getId());
        setTitle(hasRelatedGuidanceModels.getEndNode().getTitle());
        setIdAttribute(hasRelatedGuidanceModels.getEndNode().getId());
        setDescription(hasRelatedGuidanceModels.getDescription());
        setOrdering(hasRelatedGuidanceModels.getOrdering());
        setIdAttribute(hasRelatedGuidanceModels.getEndNode().getId());
    }

    public DecisionGuidanceModelRelatedGuidanceModelsDto(DecisionGuidanceModel dgmAttribute) {
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
