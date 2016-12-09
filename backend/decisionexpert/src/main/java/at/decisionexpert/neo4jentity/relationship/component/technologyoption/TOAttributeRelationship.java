package at.decisionexpert.neo4jentity.relationship.component.technologyoption;

import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import at.decisionexpert.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public abstract class TOAttributeRelationship<V extends CoreData> extends DateRelationship<TechnologyOption, V> {

    @Property
    private String rationale;
    @Property
    private int ordering;

    public TOAttributeRelationship(TechnologyOption startNode, V endNode) {
        super(startNode, endNode);
    }

    public TOAttributeRelationship(TechnologyOption startNode, V endNode, String rationale, String definition, int ordering) {
        super(startNode, endNode);
        this.rationale = rationale;
        this.ordering = ordering;
    }

    public TOAttributeRelationship() {
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
