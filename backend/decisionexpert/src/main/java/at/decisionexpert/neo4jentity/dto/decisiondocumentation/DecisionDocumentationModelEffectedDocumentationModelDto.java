package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasEffectedDocumentationModel;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumentationModelEffectedDocumentationModelDto {

    private Long id;

    private String title;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    public DecisionDocumentationModelEffectedDocumentationModelDto() {
    }

    public DecisionDocumentationModelEffectedDocumentationModelDto(HasEffectedDocumentationModel hasEffectedDocumentationModel) {
        setId(hasEffectedDocumentationModel.getId());
        setTitle(hasEffectedDocumentationModel.getEndNode().getTitle());
        setIdAttribute(hasEffectedDocumentationModel.getEndNode().getId());
        setDescription(hasEffectedDocumentationModel.getDescription());
        setOrdering(hasEffectedDocumentationModel.getOrdering());
        setIdAttribute(hasEffectedDocumentationModel.getEndNode().getId());
    }


    public DecisionDocumentationModelEffectedDocumentationModelDto(DecisionDocumentationModel ddmAttribute) {
        idAttribute = ddmAttribute.getId();
        title = ddmAttribute.getTitle();
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
