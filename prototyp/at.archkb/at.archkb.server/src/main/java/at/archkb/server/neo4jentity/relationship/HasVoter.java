package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.Comment;
import at.archkb.server.neo4jentity.node.Node;
import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "HAS VOTER")
public class HasVoter extends DateRelationship<Node, User> {
	
	private boolean positiv;

	public HasVoter(ArchProfile startNode, User endNode, boolean positiv) {
		super(startNode, endNode);
		this.positiv = positiv;
	}
	
	public HasVoter(Comment startNode, User endNode, boolean positiv) {
		super(startNode, endNode);
		this.positiv = positiv;
	}

	public HasVoter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isPositiv() {
		return positiv;
	}

	public void setPositiv(boolean positiv) {
		this.positiv = positiv;
	}
}
