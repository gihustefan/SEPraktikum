package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.Architecturestyle;

@RelationshipEntity(type = "HAS_ARCHITECTURE_STYLE")
public class HasArchitectureStyle extends APAttributeRelationship<Architecturestyle> {

	public HasArchitectureStyle(ArchProfile startNode, Architecturestyle endNode, String description, String definition, int ordering) {
		super(startNode, endNode, description, definition, ordering);
		// TODO Auto-generated constructor stub
	}

	public HasArchitectureStyle(ArchProfile startNode, Architecturestyle endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

	public HasArchitectureStyle() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
