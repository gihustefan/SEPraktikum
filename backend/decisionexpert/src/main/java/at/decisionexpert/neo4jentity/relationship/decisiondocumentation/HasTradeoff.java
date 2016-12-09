package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.node.Tradeoff;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_TRADEOFF")
public class HasTradeoff extends DateRelationship<DecisionDocumentationModel, Tradeoff> {

    @Property
    private int ordering;

    @Property
    private String descriptionOver;

    @Property
    private String descriptionUnder;

    public HasTradeoff(DecisionDocumentationModel startNode, Tradeoff endNode, int ordering, String descriptionOver, String descriptionUnder) {
        super(startNode, endNode);
        this.ordering = ordering;
        this.descriptionOver = descriptionOver;
        this.descriptionUnder = descriptionUnder;
    }

    public HasTradeoff(DecisionDocumentationModel startNode, Tradeoff endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasTradeoff() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getDescriptionOver() {
        return descriptionOver;
    }

    public void setDescriptionOver(String descriptionOver) {
        this.descriptionOver = descriptionOver;
    }

    public String getDescriptionUnder() {
        return descriptionUnder;
    }

    public void setDescriptionUnder(String descriptionUnder) {
        this.descriptionUnder = descriptionUnder;
    }
}
