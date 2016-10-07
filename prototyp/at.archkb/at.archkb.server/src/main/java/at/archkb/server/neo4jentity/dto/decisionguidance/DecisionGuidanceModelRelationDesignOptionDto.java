package at.archkb.server.neo4jentity.dto.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import at.archkb.server.neo4jentity.relationship.decisionguidance.HasDesignOption;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelRelationDesignOptionDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String definition;

    private String description;

    private Integer ordering;

    private List<DesignOptionRelationDto> implications;

    private List<DesignOptionRelationDto> addressedRequirements;

    private List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels;

    private List<DesignOptionRelationDto> requiredComponents;

    public DecisionGuidanceModelRelationDesignOptionDto() {
    }

    public DecisionGuidanceModelRelationDesignOptionDto(HasDesignOption hasDesignOption) {
        setId(hasDesignOption.getId());
        setName(hasDesignOption.getEndNode().getName());
        setIdAttribute(hasDesignOption.getEndNode().getId());
        setDescription(hasDesignOption.getDescription());
        setOrdering(hasDesignOption.getOrdering());
        setIdAttribute(hasDesignOption.getEndNode().getId());
        setImplications(getNeo4JRelationDto(hasDesignOption.getEndNode().getImplications()));
        setAddressedRequirements(getNeo4JRelationDto(hasDesignOption.getEndNode().getAddressedRequirements()));
        setRequiredComponents(getNeo4JRelationDto(hasDesignOption.getEndNode().getRequiredComponents()));

        // Effected Topics
        List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels = new ArrayList<>();
        hasDesignOption.getEndNode().getEffectedGuidanceModels().forEach(to -> {
            effectedGuidanceModels.add(new DesignOptionEffectedGuidanceModelsDto(to));
        });

        setEffectedGuidanceModels(effectedGuidanceModels);
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<DesignOptionRelationDto> getNeo4JRelationDto(
            Collection<? extends DOAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<DesignOptionRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new DesignOptionRelationDto(item));
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

    public List<DesignOptionRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<DesignOptionRelationDto> implications) {
        this.implications = implications;
    }

    public List<DesignOptionRelationDto> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(List<DesignOptionRelationDto> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }

    public List<DesignOptionEffectedGuidanceModelsDto> getEffectedGuidanceModels() {
        return effectedGuidanceModels;
    }

    public void setEffectedGuidanceModels(List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels) {
        this.effectedGuidanceModels = effectedGuidanceModels;
    }

    public List<DesignOptionRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DesignOptionRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }
}
