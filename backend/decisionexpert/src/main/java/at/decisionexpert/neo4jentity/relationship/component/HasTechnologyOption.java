package at.decisionexpert.neo4jentity.relationship.component;

import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
@RelationshipEntity(type = "HAS_TECHNOLOGYOPTION")
public class HasTechnologyOption extends ComponentAttributeRelationship<TechnologyOption> {

    public HasTechnologyOption(Component startNode, TechnologyOption endNode, String rationale, int ordering) {
        super(startNode, endNode, rationale, ordering);
    }

    public HasTechnologyOption(Component startNode, TechnologyOption endNode) {
        super(startNode, endNode);
    }

    public HasTechnologyOption() {
        super();
    }
}
