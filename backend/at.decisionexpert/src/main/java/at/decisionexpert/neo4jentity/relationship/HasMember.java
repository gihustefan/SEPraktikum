package at.decisionexpert.neo4jentity.relationship;

import at.decisionexpert.neo4jentity.node.Group;
import at.decisionexpert.neo4jentity.node.User;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@RelationshipEntity(type = "HAS_MEMBER")
public class HasMember extends DateRelationship<Group, User> {

    public HasMember(Group startNode, User endNode) {
        super(startNode, endNode);
    }

    public HasMember() {
        super();
    }
}
