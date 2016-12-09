package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.node.Implication;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type = "HAS_IMPLICATION")
public class HasImplication extends DOAttributeRelationship<Implication> {

    public HasImplication(DesignOption startNode, Implication endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasImplication(DesignOption startNode, Implication endNode) {
        super(startNode, endNode);
    }
    
    public HasImplication() {
        super();
    }
}
