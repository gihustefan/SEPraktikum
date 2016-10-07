package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.archkb.server.neo4jentity.node.Node;


public abstract class Relationship <K extends Node, V extends Node> {
	
	@GraphId
	private Long id;
	
	@StartNode
	private K startNode;
	
	@EndNode
	private V endNode;
	
	public Relationship(K startNode, V endNode) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
	}
	public Relationship() {
		super();
	}
	public K getStartNode() {
		return startNode;
	}
	public void setStartNode(K startNode) {
		this.startNode = startNode;
	}
	public V getEndNode() {
		return endNode;
	}
	public void setEndNode(V endNode) {
		this.endNode = endNode;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return "Relationship [id=" + id + ", startNode=" + startNode + ", endNode=" + endNode + "]";
		}
	}
	
	

}
