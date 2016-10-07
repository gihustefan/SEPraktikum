package at.archkb.server.neo4jentity.relationship;

import at.archkb.server.neo4jentity.node.CoreData;
import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "HAS_SUGGESTOR")
public class HasSuggestor extends DateRelationship<CoreData, User> {
	
	public HasSuggestor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HasSuggestor(CoreData startNode, User endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

}
