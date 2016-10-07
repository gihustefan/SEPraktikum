package at.decisionexpert.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Company extends Node {
	
	private String name;
	private String address;
	
	public Company(String name, String address, City city) {
		this.name = name;
		this.address = address;
		this.city = city;
	}	
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Company(String name) {
		this.name = name;
	}

	public Company(User creator) {
		super(creator);
	}

	@Relationship(type = "LOCATED_IN", direction = Relationship.OUTGOING)
	private City city;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	
	
	

}
