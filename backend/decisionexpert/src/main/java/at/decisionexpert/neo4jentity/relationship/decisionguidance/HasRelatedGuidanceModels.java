package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_RELATEDGUIDANCEMODEL")
public class HasRelatedGuidanceModels extends DGMAttributeRelationship<DecisionGuidanceModel> {


    public HasRelatedGuidanceModels(DecisionGuidanceModel startNode, DecisionGuidanceModel endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasRelatedGuidanceModels(DecisionGuidanceModel startNode, DecisionGuidanceModel endNode) {
        super(startNode, endNode);
    }

    public HasRelatedGuidanceModels() {
        super();
    }
}
