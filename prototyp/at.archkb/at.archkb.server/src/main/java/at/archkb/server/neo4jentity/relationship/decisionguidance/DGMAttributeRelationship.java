package at.archkb.server.neo4jentity.relationship.decisionguidance;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import at.archkb.server.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public abstract class DGMAttributeRelationship <V extends CoreData> extends DateRelationship<DecisionGuidanceModel, V> {

    @Property
    private String description;
    @Property
    private String definition;
    @Property
    private int ordering;

    public DGMAttributeRelationship(DecisionGuidanceModel startNode, V endNode) {
        super(startNode, endNode);
    }

    public DGMAttributeRelationship(DecisionGuidanceModel startNode, V endNode, String description, String definition, int ordering) {
        super(startNode, endNode);
        this.description = description;
        this.definition = definition;
        this.ordering = ordering;
    }

    public DGMAttributeRelationship () {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }
}
