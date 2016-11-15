package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RelationshipEntity(type="HAS_ADDRESSEDREQUIREMENT")
public class HasAddressedRequirement extends DOAttributeRelationship<Requirement> {

    public HasAddressedRequirement(DesignOption startNode, Requirement endNode, String description, int ordering) {
        super(startNode, endNode, description, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement(DesignOption startNode, Requirement endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}
