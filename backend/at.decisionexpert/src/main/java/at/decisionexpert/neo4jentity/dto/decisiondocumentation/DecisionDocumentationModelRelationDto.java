package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumentationModelRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String description;

    private Integer ordering;

    private String path;

    public DecisionDocumentationModelRelationDto() {
        super();
    }

    public DecisionDocumentationModelRelationDto(DDMAttributeRelationship<? extends CoreData> ddmAttributeRelationship) {
        id = ddmAttributeRelationship.getId();
        name = ddmAttributeRelationship.getEndNode().getName();
        // Not fetching the definition from the end node, but from the relationship itself! (copied from end node on creation)
        description = ddmAttributeRelationship.getDescription();
        ordering = ddmAttributeRelationship.getOrdering();
        idAttribute = ddmAttributeRelationship.getEndNode().getId();
    }

    public DecisionDocumentationModelRelationDto(CoreData apAttribute) {
        name = apAttribute.getName();
        idAttribute = apAttribute.getId();
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
