package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class DesignDecision extends CoreData {

	public DesignDecision(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public DesignDecision(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public DesignDecision() {
		super();
		// TODO Auto-generated constructor stub
	}

}
