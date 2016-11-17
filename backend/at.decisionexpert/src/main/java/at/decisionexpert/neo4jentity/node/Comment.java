package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Comment extends Node {

	private String text;

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

}
