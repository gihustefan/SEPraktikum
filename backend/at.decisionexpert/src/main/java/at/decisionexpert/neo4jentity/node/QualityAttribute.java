package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class QualityAttribute extends CoreData {

	public QualityAttribute(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public QualityAttribute(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public QualityAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
