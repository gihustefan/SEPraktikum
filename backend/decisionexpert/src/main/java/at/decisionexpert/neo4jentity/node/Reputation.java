package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Reputation extends Node {
	
	@Relationship(type = "HAS_REPUTATION", direction = Relationship.INCOMING)
    Node concerningNode;
	
	@Relationship(type = "HAS_REPUTATION_TYPE", direction = Relationship.OUTGOING)
    ReputationType reputationType;

	public Reputation(User creator) {
		super(creator);
		// TODO Auto-generated constructor stub
	}

	public Reputation() {
		// TODO Auto-generated constructor stub
	}

	public Reputation(User creator, Node concerningNode, ReputationType reputationType) {
		super(creator);
		this.concerningNode = concerningNode;
		this.reputationType = reputationType;
	}

	public Node getConcerningNode() {
		return concerningNode;
	}

	public void setConcerningNode(Node concerningNode) {
		this.concerningNode = concerningNode;
	}

	public ReputationType getReputationType() {
		return reputationType;
	}

	public void setReputationType(ReputationType reputationType) {
		this.reputationType = reputationType;
	}
	
	

}
