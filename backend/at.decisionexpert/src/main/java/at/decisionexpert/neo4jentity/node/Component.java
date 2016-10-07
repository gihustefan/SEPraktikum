package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by stefanhaselboeck on 21.09.16.
 */
@NodeEntity
public class Component extends CoreData {

    private String description;

    public Component(User creator, String name, String definition) {
        super(creator, name, definition);
        // TODO Auto-generated constructor stub
    }

    public Component(String name, String definition) {
        super(name, definition);
        // TODO Auto-generated constructor stub
    }

    public Component () {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
