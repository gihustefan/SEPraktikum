package at.archkb.server.neo4jentity.relationship.decisionguidance;

import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import at.archkb.server.neo4jentity.node.DesignOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type = "HAS_DESIGNOPTION")
public class HasDesignOption extends DGMAttributeRelationship<DesignOption> {

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode, String description, String definition, int ordering) {
        super(startNode, endNode, description, definition, ordering);
        // TODO Auto-generated constructor stub
    }

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasDesignOption() {
        super();
        // TODO Auto-generated constructor stub
    }
}
