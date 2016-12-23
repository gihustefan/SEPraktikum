package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.HasComment;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Comment extends Node {

	private String text;

	@Relationship(type = "HAS_COMMENT", direction = Relationship.OUTGOING)
	private Set<HasComment> comments = new HashSet<>(0);

	public Comment(User creator, String text) {
		super(creator);
		this.text = text;
	}
	
	public Comment(User creator) {
		super(creator);
	}

	public Comment() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<HasComment> getComments() {
		return comments;
	}

	public void setComments(Set<HasComment> comments) {
		this.comments = comments;
	}
}
