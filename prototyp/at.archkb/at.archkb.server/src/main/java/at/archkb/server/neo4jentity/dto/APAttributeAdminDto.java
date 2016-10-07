package at.archkb.server.neo4jentity.dto;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class APAttributeAdminDto {
	
	private long id;
	private String name;
	private long coreAdded;
	
	public APAttributeAdminDto() {
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
