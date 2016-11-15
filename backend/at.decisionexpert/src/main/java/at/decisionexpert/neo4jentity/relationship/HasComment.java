package at.decisionexpert.neo4jentity.relationship;

import at.decisionexpert.neo4jentity.node.Comment;
import at.decisionexpert.neo4jentity.node.Node;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@RelationshipEntity(type = "HAS_COMMENT")
public class HasComment <T extends Node> extends DateRelationship<T, Comment> {

    public HasComment(T startNode, Comment endNode) {
        super(startNode, endNode);
    }

    public HasComment() {
        super();
    }
}
