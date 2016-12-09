package at.decisionexpert.neo4jentity.relationship.component;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public abstract class ComponentAttributeRelationship<V extends CoreData> extends DateRelationship<Component, V> {

    @Property
    private String rationale;
    @Property
    private int ordering;

    public ComponentAttributeRelationship(Component startNode, V endNode) {
        super(startNode, endNode);
    }

    public ComponentAttributeRelationship(Component startNode, V endNode, String rationale, int ordering) {
        super(startNode, endNode);
        this.rationale = rationale;
        this.ordering = ordering;
    }

    public ComponentAttributeRelationship() {
        super();
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
