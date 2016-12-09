package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
public abstract class DDMAttributeRelationship <V extends CoreData> extends DateRelationship<DecisionDocumentationModel, V> {

    @Property
    private String description;
    @Property
    private String definition;
    @Property
    private int ordering;

    public DDMAttributeRelationship(DecisionDocumentationModel startNode, V endNode) {
        super(startNode, endNode);
    }

    public DDMAttributeRelationship(DecisionDocumentationModel startNode, V endNode, String description, String definition, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.definition = definition;
        this.ordering = ordering;
    }

    public DDMAttributeRelationship () {
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
