package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Driver extends CoreData {

	public Driver(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public Driver(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
