package at.decisionexpert.neo4jentity.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.node.DecisionDocumentationModel;
import at.decisionexpert.neo4jentity.node.DesignDecision;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_DESIGNDECISION")
public class HasDesignDecision extends DateRelationship<DecisionDocumentationModel, DesignDecision> {

    enum Status{
        CHOSEN, NOTCHOSEN, NEGLECTED, OUTSTANDING
    }
    @Property
    private Status status;

    @Property
    private int ordering;

    @Property
    private String rationale;

    public HasDesignDecision(DecisionDocumentationModel startNode, DesignDecision endNode, String rationale, int ordering, Status status) {
        super(startNode, endNode);
        this.status = status;
        this.ordering = ordering;
        this.rationale = rationale;
    }

    public HasDesignDecision(DecisionDocumentationModel startNode, DesignDecision endNode, Status status) {
        super(startNode, endNode);
        this.status = status;
    }

    public HasDesignDecision() {
        super();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }
}
