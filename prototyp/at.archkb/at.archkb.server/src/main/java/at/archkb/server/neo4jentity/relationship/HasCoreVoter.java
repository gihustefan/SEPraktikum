package at.archkb.server.neo4jentity.relationship;

import at.archkb.server.neo4jentity.node.CoreData;
import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "HAS_CORE_VOTER")
public class HasCoreVoter extends DateRelationship<CoreData, User> {
	
	private boolean positiv;
	
	public HasCoreVoter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HasCoreVoter(CoreData startNode, User endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

	public HasCoreVoter(CoreData startNode, User endNode, boolean positiv) {
		super(startNode, endNode);
		this.positiv = positiv;
	}

	public boolean isPositiv() {
		return positiv;
	}

	public void setPositiv(boolean positiv) {
		this.positiv = positiv;
	}

}
