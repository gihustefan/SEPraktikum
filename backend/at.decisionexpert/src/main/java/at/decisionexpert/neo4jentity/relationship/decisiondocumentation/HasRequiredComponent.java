package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_REQUIREDCOMPONENT")
public class HasRequiredComponent extends DDMAttributeRelationship<Component> {

    public HasRequiredComponent(DecisionDocumentationModel startNode, Component endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasRequiredComponent(DecisionDocumentationModel startNode, Component endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasRequiredComponent() {
        super();
        // TODO Auto-generated constructor stub
    }
}
