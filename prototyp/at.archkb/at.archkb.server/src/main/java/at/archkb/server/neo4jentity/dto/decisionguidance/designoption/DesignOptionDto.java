package at.archkb.server.neo4jentity.dto.decisionguidance.designoption;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.DesignOption;
import at.archkb.server.neo4jentity.relationship.decisionguidance.designoption.DOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignOptionDto {

    private Serializable id;

    private String name;

    private String definition;

    private String description;

    private List<DesignOptionRelationDto> implications;

    private List<DesignOptionRelationDto> addressedRequirements;

    private List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels;

    private List<DesignOptionRelationDto> requiredComponents;

    public DesignOptionDto() {

    }

    public DesignOptionDto(DesignOption designOption) {
        setId(designOption.getId());
        setName(designOption.getName());
        setDefinition(designOption.getDefinition());
        setDescription(designOption.getDescription());

        // Implications
        setImplications(getNeo4JRelationDto(designOption.getImplications()));

        // Requirements
        setAddressedRequirements(getNeo4JRelationDto(designOption.getAddressedRequirements()));

        // EffectedTopics
        List<DesignOptionEffectedGuidanceModelsDto> effectedGuidanceModels = new ArrayList<>();
        designOption.getEffectedGuidanceModels().forEach(to -> {
            effectedGuidanceModels.add(new DesignOptionEffectedGuidanceModelsDto(to));
        });

        setEffectedGuidanceModels(effectedGuidanceModels);

        //RequiredComponents
        setRequiredComponents(getNeo4JRelationDto(designOption.getRequiredComponents()));
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

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
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

    public void setEffectedGuidanceModels(List<DesignOptionEffectedGuidanceModelsDto> effectedTopics) {
        this.effectedGuidanceModels = effectedGuidanceModels;
    }

    public List<DesignOptionRelationDto> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<DesignOptionRelationDto> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }
}
