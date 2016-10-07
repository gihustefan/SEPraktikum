package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Architecturestyle extends CoreData {

	public Architecturestyle(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public Architecturestyle(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public Architecturestyle() {
		super();
		// TODO Auto-generated constructor stub
	}

}
