package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@QueryResult
@NodeEntity
public class Requirement extends CoreData {

    public Requirement(User creator, String name, String description) {
        super(creator, name, description);
        // TODO Auto-generated constructor stub
    }

    public Requirement(String name, String description) {
        super(name, description);
        // TODO Auto-generated constructor stub
    }

    public Requirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}
