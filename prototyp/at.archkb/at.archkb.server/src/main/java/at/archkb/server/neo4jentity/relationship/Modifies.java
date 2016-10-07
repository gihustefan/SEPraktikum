package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.User;

@RelationshipEntity(type = "MODIFIES")
public class Modifies extends DateRelationship<User, CoreData> {
	
	String changedPropertie;
	String originalValue;

	public Modifies() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Modifies(User startNode, CoreData endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}

	public Modifies(User startNode, CoreData endNode, String changedPropertie, String originalValue) {
		super(startNode, endNode);
		this.changedPropertie = changedPropertie;
		this.originalValue = originalValue;
	}

	public String getChangedPropertie() {
		return changedPropertie;
	}

	public void setChangedPropertie(String changedPropertie) {
		this.changedPropertie = changedPropertie;
	}

	public String getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}
	
	
}
