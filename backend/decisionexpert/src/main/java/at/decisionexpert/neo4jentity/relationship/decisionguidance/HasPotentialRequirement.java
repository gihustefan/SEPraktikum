package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_POTENTIALREQUIREMENT")
public class HasPotentialRequirement extends DGMAttributeRelationship<Requirement>{

    public HasPotentialRequirement(DecisionGuidanceModel startNode, Requirement endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasPotentialRequirement(DecisionGuidanceModel startNode, Requirement endNode) {
        super(startNode, endNode);
    }

    public HasPotentialRequirement() {
        super();
    }

}
