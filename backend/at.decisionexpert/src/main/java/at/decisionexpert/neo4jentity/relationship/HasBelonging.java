package at.decisionexpert.neo4jentity.relationship;

import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.Node;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
@RelationshipEntity(type = "HAS_BELONGING")
public class HasBelonging  <T extends Node> extends DateRelationship<T, Group> {

    public HasBelonging(T startNode, Group endNode) {
        super(startNode, endNode);
    }

    public HasBelonging() {
        super();
    }
}
