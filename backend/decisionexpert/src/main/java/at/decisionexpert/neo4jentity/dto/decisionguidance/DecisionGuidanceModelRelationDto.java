package at.decisionexpert.neo4jentity.dto.decisionguidance;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.DGMAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String description;

    private String rationale;

    private Integer ordering;

    public DecisionGuidanceModelRelationDto() {
        super();
    }

    public DecisionGuidanceModelRelationDto(DGMAttributeRelationship <? extends CoreData> dgmRelationship) {
        id = dgmRelationship.getId();
        name = dgmRelationship.getEndNode().getName();
        idAttribute = dgmRelationship.getEndNode().getId();
        description = dgmRelationship.getEndNode().getDescription() != null ? dgmRelationship.getEndNode().getDescription() : "";
        rationale = dgmRelationship.getRationale() != null ? dgmRelationship.getRationale() : "";
        ordering = dgmRelationship.getOrdering();
    }

    public DecisionGuidanceModelRelationDto(CoreData dgmAttribute) {
        name = dgmAttribute.getName();
        description = dgmAttribute.getDescription();
        idAttribute = dgmAttribute.getId();
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

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}