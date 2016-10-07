package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Constraint extends CoreData {

	public Constraint(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public Constraint(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public Constraint() {
		super();
		// TODO Auto-generated constructor stub
	}

}
