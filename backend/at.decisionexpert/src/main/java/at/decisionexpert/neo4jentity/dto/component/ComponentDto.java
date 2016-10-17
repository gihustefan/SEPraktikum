package at.decisionexpert.neo4jentity.dto.component;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.ComponentAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentDto {
    
    private Serializable id;

    private String name;

    private String definition;

    private String description;

    private List<ComponentRelationDto> potentialRequirements;

    private List<ComponentRelationTechnologyOptionDto> technologyOptions;


    public ComponentDto() {

    }

    public ComponentDto(Component component) {
        setId(component.getId());
        setName(component.getName());
        setDefinition(component.getDefinition());
        setDescription(component.getDescription());

        // Potential Requirements
        setPotentialRequirements(getNeo4JRelationDto(component.getPotentialRequirements()));

        // Technology Options
        List<ComponentRelationTechnologyOptionDto> technologyOptions = new ArrayList<>();
        component.getTechnologyOptions().forEach(to -> {
            technologyOptions.add(new ComponentRelationTechnologyOptionDto(to));
        });

        setTechnologyOptions(technologyOptions);
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
    private List<ComponentRelationDto> getNeo4JRelationDto(
            Collection<? extends ComponentAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<ComponentRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new ComponentRelationDto(item));
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

    public List<ComponentRelationDto> getPotentialRequirements() {
        return potentialRequirements;
    }

    public void setPotentialRequirements(List<ComponentRelationDto> potentialRequirements) {
        this.potentialRequirements = potentialRequirements;
    }

    public List<ComponentRelationTechnologyOptionDto> getTechnologyOptions() {
        return technologyOptions;
    }

    public void setTechnologyOptions(List<ComponentRelationTechnologyOptionDto> technologyOptions) {
        this.technologyOptions = technologyOptions;
    }
}
