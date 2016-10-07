package at.archkb.server.neo4jentity.node;

public class Country extends Node {
	
	String name;

	public Country(String name) {
		this.name = name;
	}

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
