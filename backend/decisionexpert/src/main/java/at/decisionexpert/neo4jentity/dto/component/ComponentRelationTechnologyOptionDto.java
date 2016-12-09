package at.decisionexpert.neo4jentity.dto.component;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.component.HasTechnologyOption;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOAttributeRelationship;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentRelationTechnologyOptionDto {

    private Long id;

    private String name;

    private Long idAttribute;

    private String rationale;

    private String description;

    private Integer ordering;

    private List<TechnologyOptionRelationDto> addressedRequirement;

    private List<TechnologyOptionRelationDto> implications;

    public ComponentRelationTechnologyOptionDto() {}

    public ComponentRelationTechnologyOptionDto(HasTechnologyOption hasTechnologyOption) {
        setId(hasTechnologyOption.getId());
        setName(hasTechnologyOption.getEndNode().getName());
        setIdAttribute(hasTechnologyOption.getEndNode().getId());
        setDescription(hasTechnologyOption.getEndNode().getDescription());
        setRationale(hasTechnologyOption.getRationale());
        setOrdering(hasTechnologyOption.getOrdering());
        setIdAttribute(hasTechnologyOption.getEndNode().getId());
        setAddressedRequirement(getNeo4JRelationDto(hasTechnologyOption.getEndNode().getAddressedRequirements()));
        setImplications(getNeo4JRelationDto(hasTechnologyOption.getEndNode().getImplications()));
    }

    /**
     * Simple Help method for converting Neo4J DecisionGuidanceModel Attribute
     * Relationships into DecisionGuidanceModelRelationDtos (almost all of these are
     * similar)
     *
     * @param collection
     * @return
     */
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

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
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

    public List<TechnologyOptionRelationDto> getAddressedRequirement() {
        return addressedRequirement;
    }

    public void setAddressedRequirement(List<TechnologyOptionRelationDto> addressedRequirement) {
        this.addressedRequirement = addressedRequirement;
    }

    public List<TechnologyOptionRelationDto> getImplications() {
        return implications;
    }

    public void setImplications(List<TechnologyOptionRelationDto> implications) {
        this.implications = implications;
    }
}
