package at.decisionexpert.neo4jentity.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //Difficult to handle with js
public abstract class Node implements NodeInterface {
	
	@GraphId
	private Long id;
	
	private long creationDate;
	private long lastModified;
	
	@Relationship(type = "HAS_CREATOR", direction = Relationship.OUTGOING)
	private User creator;

	public Node(User creator) {
		this();
		this.creator=creator;
	}
	
	public Node(){
		super();
		creationDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		lastModified = creationDate;
	}
	
	
	@Override
	public long getCreationDate() {
		return creationDate;
	}
	
	@Override
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public long getLastModified() {
		return lastModified;
	}
	
	@Override
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public User getCreator() {
		return creator;
	}
	@Override
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "Node [id=" + id + ", creationDate=" + creationDate + ", lastModified=" + lastModified + ", creator="
			+ creator + "]";
		}
	}
	
	
}
