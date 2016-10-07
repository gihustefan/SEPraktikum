package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Badge extends CoreData {
	
	private String condition;
	private int points;

	public Badge(String name, String definition, String condition, int points) {
		super(name, definition);
		this.condition = condition;
		this.points = points;
	}

	public Badge() {
		// TODO Auto-generated constructor stub
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
