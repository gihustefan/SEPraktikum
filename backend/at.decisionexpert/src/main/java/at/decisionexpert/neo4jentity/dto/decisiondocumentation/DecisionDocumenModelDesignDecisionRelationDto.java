package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DesignDecision;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMAttributeRelationship;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasDesignDecision;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumenModelDesignDecisionRelationDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private Integer ordering;

    private String rationale;

    private List<DecisionDocumentationModelRelationDto> implications;

    private List<DecisionDocumentationModelRelationDto> addressedRequirements;

    private List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels;

    private List<DecisionDocumentationModelRelationDto> requiredComponents;

    public DecisionDocumenModelDesignDecisionRelationDto() {
    }

    public DecisionDocumenModelDesignDecisionRelationDto(HasDesignDecision hasDesignDecision) {
        setId(hasDesignDecision.getId());
        setName(hasDesignDecision.getEndNode().getName());
        setIdAttribute(hasDesignDecision.getEndNode().getId());
        setOrdering(hasDesignDecision.getOrdering());
        setRationale(hasDesignDecision.getRationale());
        setIdAttribute(hasDesignDecision.getEndNode().getId());
        setImplications(getNeo4JRelationDto(hasDesignDecision.getEndNode().getImplications()));
        setAddressedRequirements(getNeo4JRelationDto(hasDesignDecision.getEndNode().getAddressedRequirements()));
        setRequiredComponents(getNeo4JRelationDto(hasDesignDecision.getEndNode().getRequiredComponents()));

        // Effected Topics
        List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels = new ArrayList<>();
        hasDesignDecision.getEndNode().getEffectedDocumentationModels().forEach(to -> {
            effectedDocumentationModels.add(new DecisionDocumentationModelEffectedDocumentationModelDto(to));
        });

        setEffectedDocumentationModels(effectedDocumentationModels);
    }

    public DecisionDocumenModelDesignDecisionRelationDto(DesignDecision ddAttribute) {
        idAttribute = ddAttribute.getId();
        name = ddAttribute.getName();
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<DecisionDocumentationModelRelationDto> getNeo4JRelationDto(
            Collection<? extends DDMAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<DecisionDocumentationModelRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new DecisionDocumentationModelRelationDto(item));
        });
        return result;
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

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public List<DecisionDocumentationModelRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<DecisionDocumentationModelRelationDto> implications) {
        this.implications = implications;
    }

    public List<DecisionDocumentationModelRelationDto> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(List<DecisionDocumentationModelRelationDto> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public List<DecisionDocumentationModelEffectedDocumentationModelDto> getEffectedDocumentationModels() {
        return effectedDocumentationModels;
    }

    public void setEffectedDocumentationModels(List<DecisionDocumentationModelEffectedDocumentationModelDto> effectedDocumentationModels) {
        this.effectedDocumentationModels = effectedDocumentationModels;
    }

    public List<DecisionDocumentationModelRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DecisionDocumentationModelRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }
}
