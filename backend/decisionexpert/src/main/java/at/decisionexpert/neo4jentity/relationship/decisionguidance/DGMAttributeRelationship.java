package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public abstract class DGMAttributeRelationship <V extends CoreData> extends DateRelationship<DecisionGuidanceModel, V> {

    @Property
    private String rationale;
    @Property
    private int ordering;

    public DGMAttributeRelationship(DecisionGuidanceModel startNode, V endNode) {
        super(startNode, endNode);
    }

    public DGMAttributeRelationship(DecisionGuidanceModel startNode, V endNode, String rationale, int ordering) {
        super(startNode, endNode);
        this.rationale = rationale;
        this.ordering = ordering;
    }

    public DGMAttributeRelationship () {
        super();
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
