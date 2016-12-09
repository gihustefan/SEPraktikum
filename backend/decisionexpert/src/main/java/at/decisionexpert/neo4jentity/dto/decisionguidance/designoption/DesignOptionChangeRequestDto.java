package at.decisionexpert.neo4jentity.dto.decisionguidance.designoption;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignOptionChangeRequestDto {

    private String title;

    private String description;

    private Boolean published;

    public DesignOptionChangeRequestDto(String title, String description, Boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public DesignOptionChangeRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
