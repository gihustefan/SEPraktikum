package at.decisionexpert.neo4jentity.relationship.component.technologyoption;

import at.decisionexpert.neo4jentity.node.Implication;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@RelationshipEntity(type = "HAS_IMPLICATION")
public class TOHasImplication extends TOAttributeRelationship<Implication> {

    public TOHasImplication(TechnologyOption startNode, Implication endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public TOHasImplication(TechnologyOption startNode, Implication endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public TOHasImplication() {
        super();
        // TODO Auto-generated constructor stub
    }
}