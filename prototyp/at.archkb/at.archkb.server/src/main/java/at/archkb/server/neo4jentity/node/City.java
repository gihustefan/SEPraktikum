package at.archkb.server.neo4jentity.node;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class City extends Node {
	
	public City() {
		super();
	}
	public City(String name, String zipCode) {
		this.name = name;
		this.zipCode = zipCode;
	}
	
	public City(String name, String zipCode, Country country) {
		this.name = name;
		this.zipCode = zipCode;
		this.country = country;
	}

	private String name;
	private String zipCode;
	
	@Relationship(type = "HAS_COUNTRY", direction = Relationship.OUTGOING)
	private Country country;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
