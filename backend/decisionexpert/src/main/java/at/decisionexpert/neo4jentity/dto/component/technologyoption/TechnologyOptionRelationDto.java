package at.decisionexpert.neo4jentity.dto.component.technologyoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnologyOptionRelationDto {
    private Long id;

    private String name;

    private Long idAttribute;

    private String rationale;

    private String description;

    private Integer ordering;

    public TechnologyOptionRelationDto() {
        super();
    }

    public TechnologyOptionRelationDto(TOAttributeRelationship<? extends CoreData> technologyOptionRelationship) {
        id = technologyOptionRelationship.getId();
        name = technologyOptionRelationship.getEndNode().getName();
        idAttribute = technologyOptionRelationship.getEndNode().getId();
        description = technologyOptionRelationship.getEndNode().getDescription();
        rationale = technologyOptionRelationship.getRationale();
        ordering = technologyOptionRelationship.getOrdering();
    }

    public TechnologyOptionRelationDto(CoreData componentAttribute) {
        name = componentAttribute.getName();
        description = componentAttribute.getDescription();
        idAttribute = componentAttribute.getId();
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

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
