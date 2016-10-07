package at.decisionexpert.neo4jentity.node;

public class ReputationType extends Node {
	
	private String name;
	private int points;

	public ReputationType(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public ReputationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
