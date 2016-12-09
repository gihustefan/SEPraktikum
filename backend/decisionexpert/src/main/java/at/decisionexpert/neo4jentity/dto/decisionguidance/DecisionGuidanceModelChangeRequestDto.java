package at.decisionexpert.neo4jentity.dto.decisionguidance;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionGuidanceModelChangeRequestDto {

    private String name;

    private String description;

    private Boolean published;

    public DecisionGuidanceModelChangeRequestDto(String name, String description, Boolean published) {
        this.name = name;
        this.description = description;
        this.published = published;
    }

    public DecisionGuidanceModelChangeRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
