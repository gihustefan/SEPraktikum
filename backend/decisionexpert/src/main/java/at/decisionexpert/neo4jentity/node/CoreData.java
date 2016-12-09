package at.decisionexpert.neo4jentity.node;

public abstract class CoreData extends Node {
	
	private String name;

	private String description;
	
	public CoreData(User creator, String name, String description) {
		super(creator);
		this.name = name;
		this.description = description;
	}
	
	public CoreData(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public CoreData() {
		super();
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
