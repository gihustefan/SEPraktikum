package at.decisionexpert.neo4jentity.relationship;

import at.decisionexpert.neo4jentity.node.Node;
import org.neo4j.ogm.annotation.Property;

import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class DateRelationship<K extends Node, V extends Node> extends Relationship<K, V> {

    @Property
    private long date;

    public DateRelationship(K startNode, V endNode) {
        super(startNode, endNode);
        date = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public DateRelationship() {
        super();
        date = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
