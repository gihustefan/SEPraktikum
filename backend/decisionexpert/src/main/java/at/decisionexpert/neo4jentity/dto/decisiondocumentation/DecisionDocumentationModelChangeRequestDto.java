package at.decisionexpert.neo4jentity.dto.decisiondocumentation;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecisionDocumentationModelChangeRequestDto {

    private String title;

    private Boolean published;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
