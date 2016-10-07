package at.archkb.server.neo4jentity.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.node.DecisionDocumentationModel;
import at.archkb.server.neo4jentity.node.Tradeoff;
import at.archkb.server.neo4jentity.relationship.DateRelationship;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_TRADEOFF")
public class HasTradeoff extends DateRelationship<DecisionDocumentationModel, Tradeoff> {

    @Property
    private String description;
    @Property
    private int ordering;

    @Property
    private String definitionOver;

    @Property
    private String definitionUnder;

    public HasTradeoff(DecisionDocumentationModel startNode, Tradeoff endNode, String description, int ordering, String definitionOver, String definitionUnder) {
        super(startNode, endNode);
        this.description = description;
        this.ordering = ordering;
        this.definitionOver = definitionOver;
        this.definitionUnder = definitionUnder;
    }

    public HasTradeoff(DecisionDocumentationModel startNode, Tradeoff endNode) {
        super(startNode, endNode);
        // TODO Auto-generated constructor stub
    }

    public HasTradeoff() {
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

    public String getDefinitionOver() {
        return definitionOver;
    }

    public void setDefinitionOver(String definitionOver) {
        this.definitionOver = definitionOver;
    }

    public String getDefinitionUnder() {
        return definitionUnder;
    }

    public void setDefinitionUnder(String definitionUnder) {
        this.definitionUnder = definitionUnder;
    }
}
