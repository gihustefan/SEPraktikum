package at.archkb.server.neo4jentity.node;

public class Position extends Node {
	
	private String name;
	private String description;
	
	public Position(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Position(String name) {
		this.name = name;
	}

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
