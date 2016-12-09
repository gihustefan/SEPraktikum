package at.decisionexpert.neo4jentity.relationship;

import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.node.User;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@RelationshipEntity(type = "HAS_VOTE")
public class HasVote <T extends Node> extends DateRelationship<T, User> {

    @Property
    private boolean vote;

    public HasVote(T startNode, User endNode, boolean vote) {
        super(startNode, endNode);
        this.vote = vote;
    }

    public HasVote() {
        super();
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
