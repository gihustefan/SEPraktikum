package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@NodeEntity
public class Implication extends CoreData {

    public Implication(User creator, String name, String definition) {
        super(creator, name, definition);
        // TODO Auto-generated constructor stub
    }

    public Implication(String name, String definition) {
        super(name, definition);
        // TODO Auto-generated constructor stub
    }

    public Implication() {
        super();
        // TODO Auto-generated constructor stub
    }
}
