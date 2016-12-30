package at.decisionexpert.neo4jentity.dto.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignOptionRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String description;

    private String rationale;

    private Integer ordering;

    public DesignOptionRelationDto() {
        super();
    }

    public DesignOptionRelationDto(DOAttributeRelationship<? extends CoreData> doRelationship) {
        id = doRelationship.getId();
        name = doRelationship.getEndNode().getName();
        idAttribute = doRelationship.getEndNode().getId();
        description = doRelationship.getEndNode().getDescription() != null ? doRelationship.getEndNode().getDescription() : "";
        rationale = doRelationship.getRationale() != null ? doRelationship.getRationale() : "";
        ordering = doRelationship.getOrdering();
    }

    public DesignOptionRelationDto(CoreData doAttribute) {
        name = doAttribute.getName();
        description = doAttribute.getDescription();
        idAttribute = doAttribute.getId();
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
