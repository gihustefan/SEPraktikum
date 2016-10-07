package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.Badge;
import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "ACHIEVES_BADGE")
public class AchievesBadge extends DateRelationship<User, Badge> {

	public AchievesBadge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AchievesBadge(User startNode, Badge endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

}
