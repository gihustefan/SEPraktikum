package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@NodeEntity
public class Requirement extends CoreData {

    public Requirement(User creator, String name, String definition) {
        super(creator, name, definition);
        // TODO Auto-generated constructor stub
    }

    public Requirement(String name, String definition) {
        super(name, definition);
        // TODO Auto-generated constructor stub
    }

    public Requirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}
