package at.decisionexpert.neo4jentity.node;

import at.decisionexpert.neo4jentity.relationship.HasMember;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@QueryResult
@NodeEntity
public class Group extends CoreData {

    @Relationship(type = "HAS_MEMBER", direction = Relationship.OUTGOING)
    private Set<HasMember> members = new HashSet<>(0);

    public Group(User creator, String name, String description) {
        super(creator, name, description);
    }

    public Group(String name, String description) {
        super(name, description);
    }

    public Group() {
        super();
    }


    public Set<HasMember> getMembers() {
        return members;
    }

    public void setMembers(Set<HasMember> members) {
        this.members = members;
    }
}
