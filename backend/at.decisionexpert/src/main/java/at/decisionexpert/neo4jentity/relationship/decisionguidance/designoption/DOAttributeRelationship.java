package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public abstract class DOAttributeRelationship <V extends CoreData> extends DateRelationship<DesignOption, V> {

    @Property
    private String description;
    @Property
    private int ordering;

    public DOAttributeRelationship(DesignOption startNode, V endNode) {
        super(startNode, endNode);
    }

    public DOAttributeRelationship(DesignOption startNode, V endNode, String description, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.ordering = ordering;
    }

    public DOAttributeRelationship () {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
