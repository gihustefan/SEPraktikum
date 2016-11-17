package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.HasMember;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@NodeEntity
public class Group extends Node {

    private String name;

    private String description;

    @Relationship(type = "HAS_MEMBER", direction = Relationship.OUTGOING)
    private Set<HasMember> members = new HashSet<>(0);

    public Group(User creator, String name, String description) {
        super(creator);
        this.name = name;
        this.description = description;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Group(User creator) {
        super(creator);
    }

    public Group() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HasMember> getMembers() {
        return members;
    }

    public void setMembers(Set<HasMember> members) {
        this.members = members;
    }
}
