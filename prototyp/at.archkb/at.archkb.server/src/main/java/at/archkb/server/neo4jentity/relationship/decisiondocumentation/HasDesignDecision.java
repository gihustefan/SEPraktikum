package at.archkb.server.neo4jentity.relationship.decisiondocumentation;

import at.archkb.server.neo4jentity.node.DecisionDocumentationModel;
import at.archkb.server.neo4jentity.node.DesignDecision;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RelationshipEntity(type = "HAS_DESIGNDECISION")
public class HasDesignDecision extends DDMAttributeRelationship<DesignDecision> {

    enum Status{
        CHOSEN, NOTCHOSEN, NEGLECTED, OUTSTANDING
    }
    @Property
    private Status status;

    public HasDesignDecision(DecisionDocumentationModel startNode, DesignDecision endNode, String description, String definition, int ordering, Status status) {
        super(startNode, endNode, description, definition, ordering);
        this.status = status;
        // TODO Auto-generated constructor stub
    }

    public HasDesignDecision(DecisionDocumentationModel startNode, DesignDecision endNode, Status status) {
        super(startNode, endNode);
        this.status = status;
        // TODO Auto-generated constructor stub
    }

    public HasDesignDecision() {
        super();
        // TODO Auto-generated constructor stub
    }
}
