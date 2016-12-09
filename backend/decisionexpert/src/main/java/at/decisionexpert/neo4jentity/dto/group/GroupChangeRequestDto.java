package at.decisionexpert.neo4jentity.dto.group;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupChangeRequestDto {

    private String name;

    private String description;

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
}
