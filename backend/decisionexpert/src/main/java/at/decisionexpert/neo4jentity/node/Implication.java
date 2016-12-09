package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@QueryResult
@NodeEntity
public class Implication extends CoreData {

    public Implication(User creator, String name, String description) {
        super(creator, name, description);
    }

    public Implication(String name, String description) {
        super(name, description);
    }

    public Implication() {
        super();
    }
}
