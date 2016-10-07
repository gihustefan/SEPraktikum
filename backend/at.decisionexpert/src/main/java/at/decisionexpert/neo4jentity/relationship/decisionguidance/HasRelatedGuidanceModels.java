package at.decisionexpert.neo4jentity.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RelationshipEntity(type="HAS_RELATEDGUIDANCEMODEL")
public class HasRelatedGuidanceModels extends DateRelationship<DecisionGuidanceModel, DecisionGuidanceModel> {

    @Property
    private String description;
    @Property
    private int ordering;

    public HasRelatedGuidanceModels(DecisionGuidanceModel startNode, DecisionGuidanceModel endNode, String description, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.ordering = ordering;
    }

    public HasRelatedGuidanceModels(DecisionGuidanceModel startNode, DecisionGuidanceModel endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasRelatedGuidanceModels() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
