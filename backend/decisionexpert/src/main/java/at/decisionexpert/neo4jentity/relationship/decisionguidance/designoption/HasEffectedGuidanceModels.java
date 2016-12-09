package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_EFFECTEDGUIDANCEMODEL")
public class HasEffectedGuidanceModels extends DOAttributeRelationship<DecisionGuidanceModel> {

    public HasEffectedGuidanceModels(DesignOption startNode, DecisionGuidanceModel endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasEffectedGuidanceModels(DesignOption startNode, DecisionGuidanceModel endNode) {
        super(startNode, endNode);
    }

    public HasEffectedGuidanceModels() {
        super();
    }
}
