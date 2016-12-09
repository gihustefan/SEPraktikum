package at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.DesignOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_REQUIREDCOMPONENT")
public class HasRequiredComponent extends DOAttributeRelationship<Component> {

    public HasRequiredComponent(DesignOption startNode, Component endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasRequiredComponent(DesignOption startNode, Component endNode) {
        super(startNode, endNode);
    }

    public HasRequiredComponent() {
        super();
    }
}
