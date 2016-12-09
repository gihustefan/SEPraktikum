package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 05.10.16.
 */
@RelationshipEntity(type="HAS_EFFECTEDDOCUMENTATIONMODEL")
public class HasEffectedDocumentationModel extends DateRelationship<DecisionDocumentationModel, DecisionDocumentationModel> {
    @Property
    private String description;
    @Property
    private int ordering;

    public HasEffectedDocumentationModel(DecisionDocumentationModel startNode, DecisionDocumentationModel endNode, String description, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.ordering = ordering;
    }

    public HasEffectedDocumentationModel(DecisionDocumentationModel startNode, DecisionDocumentationModel endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasEffectedDocumentationModel() {
        super();
        // TODO Auto-generated constructor stub
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
