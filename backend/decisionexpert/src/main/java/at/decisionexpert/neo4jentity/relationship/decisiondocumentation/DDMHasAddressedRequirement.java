package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "DDM_HAS_ADDRESSEDREQUIREMENT")
public class DDMHasAddressedRequirement extends DDMAttributeRelationship<Requirement> {
    public DDMHasAddressedRequirement(DecisionDocumentationModel startNode, Requirement endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public DDMHasAddressedRequirement(DecisionDocumentationModel startNode, Requirement endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public DDMHasAddressedRequirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}
