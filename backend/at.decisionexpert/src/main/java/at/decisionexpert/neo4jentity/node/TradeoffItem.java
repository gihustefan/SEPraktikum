package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class TradeoffItem extends CoreData {

	public TradeoffItem(User creator, String name, String definition) {
		super(creator, name, definition);
		// TODO Auto-generated constructor stub
	}

	public TradeoffItem(String name, String definition) {
		super(name, definition);
		// TODO Auto-generated constructor stub
	}

	public TradeoffItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}
