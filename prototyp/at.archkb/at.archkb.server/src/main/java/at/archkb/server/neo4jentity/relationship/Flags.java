package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.Comment;
import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "FLAGS")
public class Flags extends DateRelationship<User, Comment> {

	public Flags() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flags(User startNode, Comment endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

}
