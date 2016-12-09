package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.node.Implication;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "DDM_HAS_IMPLICATION")
public class DDMHasImplication extends DDMAttributeRelationship<Implication> {

    public DDMHasImplication(DecisionDocumentationModel startNode, Implication endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public DDMHasImplication(DecisionDocumentationModel startNode, Implication endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public DDMHasImplication() {
        super();
        // TODO Auto-generated constructor stub
    }
}
