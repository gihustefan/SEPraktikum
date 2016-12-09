package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type = "HAS_DESIGNOPTION")
public class HasDesignOption extends DGMAttributeRelationship<DesignOption> {

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode) {
        super(startNode, endNode);
    }

    public HasDesignOption() {
        super();
    }

}
