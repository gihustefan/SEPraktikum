package at.archkb.server.neo4jentity.dto;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.Diagram;
import com.fasterxml.jackson.annotation.JsonInclude;

import at.archkb.server.neo4jentity.relationship.APAttributeRelationship;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchProfileRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    private String path;

    public ArchProfileRelationDto() {
        super();
    }

    public ArchProfileRelationDto(APAttributeRelationship<? extends CoreData> apRelationship) {
        id = apRelationship.getId();
        name = apRelationship.getEndNode().getName();
        // Not fetching the definition from the end node, but from the relationship itself! (copied from end node on creation)
        definition = apRelationship.getDefinition();
        description = apRelationship.getDescription();
        ordering = apRelationship.getOrdering();
        idAttribute = apRelationship.getEndNode().getId();
    }

    public ArchProfileRelationDto(CoreData apAttribute) {
        name = apAttribute.getName();
        definition = apAttribute.getDefinition();
        idAttribute = apAttribute.getId();
    }

    public ArchProfileRelationDto(Diagram diagram) {
        path = diagram.getPath();
        idAttribute = diagram.getId();
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

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
