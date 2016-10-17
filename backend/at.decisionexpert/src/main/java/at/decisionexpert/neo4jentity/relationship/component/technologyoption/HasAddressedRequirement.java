package at.decisionexpert.neo4jentity.relationship.component.technologyoption;

import at.decisionexpert.neo4jentity.node.Requirement;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@RelationshipEntity(type = "HAS_ADDRESSEDREQUIREMENT")
public class HasAddressedRequirement extends TOAttributeRelationship<Requirement> {

    public HasAddressedRequirement(TechnologyOption startNode, Requirement endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement(TechnologyOption startNode, Requirement endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasAddressedRequirement() {
        super();
        // TODO Auto-generated constructor stub
    }
}