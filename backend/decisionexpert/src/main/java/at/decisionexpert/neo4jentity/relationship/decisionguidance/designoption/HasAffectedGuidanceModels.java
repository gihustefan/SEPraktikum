package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_AFFECTEDGUIDANCEMODEL")
public class HasAffectedGuidanceModels extends DOAttributeRelationship<DecisionGuidanceModel> {

    public HasAffectedGuidanceModels(DesignOption startNode, DecisionGuidanceModel endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasAffectedGuidanceModels(DesignOption startNode, DecisionGuidanceModel endNode) {
        super(startNode, endNode);
    }

    public HasAffectedGuidanceModels() {
        super();
    }
}
