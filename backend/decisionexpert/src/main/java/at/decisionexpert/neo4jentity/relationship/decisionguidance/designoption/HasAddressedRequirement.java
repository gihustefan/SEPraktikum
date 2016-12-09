package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RelationshipEntity(type="HAS_ADDRESSEDREQUIREMENT")
public class HasAddressedRequirement extends DOAttributeRelationship<Requirement> {

    public HasAddressedRequirement(DesignOption startNode, Requirement endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasAddressedRequirement(DesignOption startNode, Requirement endNode) {
        super(startNode, endNode);
    }

    public HasAddressedRequirement() {
        super();
    }
}
