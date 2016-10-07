package at.archkb.server.neo4jentity.node;

public abstract class CoreData extends Node {
	
	private String name;

	private String definition;
	
	private long coreAdded;

	public CoreData(User creator, String name, String definition) {
		super(creator);
		this.name = name;
		this.definition = definition;
	}
	
	public CoreData(String name, String definition) {
		super();
		this.name = name;
		this.definition = definition;
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

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public long getCoreAdded() {
		return coreAdded;
	}

	public void setCoreAdded(long coreAdded) {
		this.coreAdded = coreAdded;
	}
	
	
	
	
	

}
