package at.decisionexpert.neo4jentity.relationship.component;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public abstract class ComponentAttributeRelationship<V extends CoreData> extends DateRelationship<Component, V> {

    @Property
    private String description;
    @Property
    private String definition;
    @Property
    private int ordering;

    public ComponentAttributeRelationship(Component startNode, V endNode) {
        super(startNode, endNode);
    }

    public ComponentAttributeRelationship(Component startNode, V endNode, String description, String definition, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.definition = definition;
        this.ordering = ordering;
    }

    public ComponentAttributeRelationship() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
