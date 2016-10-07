package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Diagram extends Node {

    String path;

    String description;

    public Diagram(User creator, String path, String description) {
        super(creator);
        this.path = path;
        this.description = description;
    }

    public Diagram() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diagram diagram = (Diagram) o;

        if (path != null ? !path.equals(diagram.path) : diagram.path != null) return false;
        return description != null ? description.equals(diagram.description) : diagram.description == null;

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
