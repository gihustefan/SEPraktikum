package at.decisionexpert.neo4jentity.dto.component.technologyoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnologyOptionDto {

    private Serializable id;

    private String name;

    private String definition;

    private String description;

    private List<TechnologyOptionRelationDto> implications;

    private List<TechnologyOptionRelationDto> addressedRequirements;

    public TechnologyOptionDto() {

    }

    public TechnologyOptionDto(TechnologyOption technologyOption) {
        setId(technologyOption.getId());
        setName(technologyOption.getName());
        setDefinition(technologyOption.getDefinition());
        setDescription(technologyOption.getDescription());

        // Implications
        setImplications(getNeo4JRelationDto(technologyOption.getImplications()));

        // Addressed Requirements
        setAddressedRequirements(getNeo4JRelationDto(technologyOption.getAddressedRequirements()));
    }

    private List<TechnologyOptionRelationDto> getNeo4JRelationDto(
            Collection<? extends TOAttributeRelationship<? extends CoreData>> collection) {

        if (collection == null)
            return null;

        List<TechnologyOptionRelationDto> result = new ArrayList<>();
        collection.forEach(item -> {
            result.add(new TechnologyOptionRelationDto(item));
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

    public List<TechnologyOptionRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<TechnologyOptionRelationDto> implications) {
        this.implications = implications;
    }

    public List<TechnologyOptionRelationDto> getAddressedRequirements() {
        return addressedRequirements;
    }

    public void setAddressedRequirements(List<TechnologyOptionRelationDto> addressedRequirements) {
        this.addressedRequirements = addressedRequirements;
    }
}
