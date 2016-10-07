package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.DesignDecision;

@RelationshipEntity(type = "HAS_DESIGN_DECISION")
public class HasDesignDecision extends APAttributeRelationship<DesignDecision> {

	public HasDesignDecision(ArchProfile startNode, DesignDecision endNode, String description, String definition, int ordering) {
		super(startNode, endNode, description, definition, ordering);
		// TODO Auto-generated constructor stub
	}

	public HasDesignDecision(ArchProfile startNode, DesignDecision endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

	public HasDesignDecision() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
