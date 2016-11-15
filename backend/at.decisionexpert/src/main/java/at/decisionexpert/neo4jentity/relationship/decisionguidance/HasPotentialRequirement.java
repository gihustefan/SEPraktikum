package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_POTENTIALREQUIREMENT")
public class HasPotentialRequirement extends DGMAttributeRelationship<Requirement>{

    public HasPotentialRequirement(DecisionGuidanceModel startNode, Requirement endNode, String description, int ordering) {
        super(startNode, endNode, description, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasPotentialRequirement(DecisionGuidanceModel startNode, Requirement endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasPotentialRequirement() {
        super();
        // TODO Auto-generated constructor stub
    }

}
