package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.node.Implication;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type = "HAS_IMPLICATION")
public class HasImplication extends DOAttributeRelationship<Implication> {

    public HasImplication(DesignOption startNode, Implication endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasImplication(DesignOption startNode, Implication endNode, String description, int ordering) {
        super(startNode, endNode, description, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasImplication() {
        super();
    }
}
