package at.decisionexpert.neo4jentity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class AttributeDetailAdminDto {
	
	private long id;
	private String name;
	private String definition;
	private long coreAdded;
	private long creationDate;
	private long lastModified;
	private String type;
	
	private long creatorId;
	private String creatorEmail;
	
	public AttributeDetailAdminDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}
	public long getLastModified() {
		return lastModified;
	}
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	public long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorEmail() {
		return creatorEmail;
	}
	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
