package at.decisionexpert.neo4jentity.relationship.component;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.Requirement;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@RelationshipEntity(type = "HAS_POTENTIALREQUIREMENT")
public class ComponentHasPotentialRequirement extends ComponentAttributeRelationship<Requirement> {

    public ComponentHasPotentialRequirement(Component startNode, Requirement endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public ComponentHasPotentialRequirement(Component startNode, Requirement endNode) {
        super(startNode, endNode);
    }

    public ComponentHasPotentialRequirement() {
        super();
    }
}