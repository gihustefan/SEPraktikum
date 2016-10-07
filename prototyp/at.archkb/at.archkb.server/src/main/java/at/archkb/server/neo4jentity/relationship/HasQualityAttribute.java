package at.archkb.server.neo4jentity.relationship;

import org.neo4j.ogm.annotation.RelationshipEntity;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.QualityAttribute;

@RelationshipEntity(type = "HAS_QUALITYATTRIBUTE")
public class HasQualityAttribute extends APAttributeRelationship<QualityAttribute> {

	public HasQualityAttribute(ArchProfile startNode, QualityAttribute endNode, String description, String definition, int ordering) {
		super(startNode, endNode, description, definition, ordering);
		// TODO Auto-generated constructor stub
	}

	public HasQualityAttribute(ArchProfile startNode, QualityAttribute endNode) {
		super(startNode, endNode);
		// TODO Auto-generated constructor stub
	}
	
	public HasQualityAttribute(){
		super();
	}
}
