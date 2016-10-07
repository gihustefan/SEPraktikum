package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.Constraint;

@RelationshipEntity(type = "HAS_CONSTRAINT")
public class HasConstraint extends APAttributeRelationship<Constraint> {

	public HasConstraint(ArchProfile startNode, Constraint endNode, String description, String definition, int ordering) {
		super(startNode, endNode, description, definition, ordering);
		// TODO Auto-generated constructor stub
	}

	public HasConstraint(ArchProfile startNode, Constraint endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

	public HasConstraint() {
		super();
		// TODO Auto-generated constructor stub
	}

}
