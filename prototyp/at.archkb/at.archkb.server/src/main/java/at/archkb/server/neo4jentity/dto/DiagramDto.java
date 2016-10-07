package at.archkb.server.neo4jentity.dto;

import at.archkb.server.neo4jentity.node.Diagram;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Rainer on 31.05.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagramDto {

    private Long id;

    private String description;

    private String path;

    public DiagramDto() {

    }

    public DiagramDto(Diagram diagram) {
        this.id = diagram.getId();
        this.description = diagram.getDescription();
        this.path = diagram.getPath();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
