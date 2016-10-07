package at.archkb.server.neo4jentity.relationship;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.CoreData;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "RELATED_TO")
public abstract class APAttributeRelationship<V extends CoreData> extends DateRelationship<ArchProfile, V> {

    @Property
    private String description;
    @Property
    private String definition;

    @Property
    private int ordering;

    public APAttributeRelationship(ArchProfile startNode, V endNode) {
        super(startNode, endNode);
    }

    public APAttributeRelationship(ArchProfile startNode, V endNode, String description, String definition, int ordering) {
        this(startNode, endNode);
        this.description = description;
        this.ordering = ordering;
        this.definition = definition;
    }

    public APAttributeRelationship() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
