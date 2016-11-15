package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type = "HAS_DESIGNOPTION")
public class HasDesignOption extends DateRelationship<DecisionGuidanceModel, DesignOption> {

    @Property
    private int ordering;

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode, int ordering) {
        super(startNode, endNode);
        this.ordering = ordering;
    }

    public HasDesignOption(DecisionGuidanceModel startNode, DesignOption endNode) {
        super(startNode, endNode);
    }

    public HasDesignOption() {
        super();
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
