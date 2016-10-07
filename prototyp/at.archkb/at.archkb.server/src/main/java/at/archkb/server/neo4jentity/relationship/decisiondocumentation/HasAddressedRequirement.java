package at.archkb.server.neo4jentity.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.node.DecisionDocumentationModel;
import at.archkb.server.neo4jentity.node.DesignDecision;
import at.archkb.server.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_ADDRESSEDREQUIREMENT")
public class HasAddressedRequirement extends DDMAttributeRelationship<Requirement> {
    public HasAddressedRequirement(DecisionDocumentationModel startNode, Requirement endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement(DecisionDocumentationModel startNode, Requirement endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}
