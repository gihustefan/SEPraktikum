package at.decisionexpert.neo4jentity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class AttributeAdminDto {
	
	private long id;
	private String name;
	private long coreAdded;
	
	public AttributeAdminDto() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCoreAdded() {
		return coreAdded;
	}
	public void setCoreAdded(long coreAdded) {
		this.coreAdded = coreAdded;
	}
	
	

}
