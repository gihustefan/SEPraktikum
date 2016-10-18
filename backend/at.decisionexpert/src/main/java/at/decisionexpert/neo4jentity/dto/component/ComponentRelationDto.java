package at.decisionexpert.neo4jentity.dto.component;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    public ComponentRelationDto() {
        super();
    }

    public ComponentRelationDto(ComponentAttributeRelationship<? extends CoreData> componentRelationship) {
        id = componentRelationship.getId();
        name = componentRelationship.getEndNode().getName();
        idAttribute = componentRelationship.getEndNode().getId();
        definition = componentRelationship.getDefinition();
        description = componentRelationship.getDescription();
        ordering = componentRelationship.getOrdering();
    }

    public ComponentRelationDto(CoreData componentAttribute) {
        name = componentAttribute.getName();
        definition = componentAttribute.getDefinition();
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
