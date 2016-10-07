package at.archkb.server.neo4jentity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Rainer on 25.05.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchProfileChangeRequestDto {

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
