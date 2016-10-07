package at.archkb.server.neo4jentity.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.node.DecisionDocumentationModel;
import at.archkb.server.neo4jentity.node.Implication;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_IMPLICATION")
public class HasImplication extends DDMAttributeRelationship<Implication> {

    public HasImplication(DecisionDocumentationModel startNode, Implication endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasImplication(DecisionDocumentationModel startNode, Implication endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasImplication() {
        super();
        // TODO Auto-generated constructor stub
    }
}
