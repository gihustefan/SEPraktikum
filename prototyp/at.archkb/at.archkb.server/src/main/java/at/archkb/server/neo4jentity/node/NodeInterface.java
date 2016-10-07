package at.archkb.server.neo4jentity.node;

public interface NodeInterface {
	
	public long getCreationDate();

	public void setCreationDate(long creationDate);

	public long getLastModified();

	public void setLastModified(long lastModified);

	public User getCreator();

	public void setCreator(User creator);

	public Long getId();

}
