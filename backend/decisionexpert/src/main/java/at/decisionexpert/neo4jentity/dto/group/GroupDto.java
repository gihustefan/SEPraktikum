package at.decisionexpert.neo4jentity.dto.group;

import at.decisionexpert.neo4jentity.node.Group;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@QueryResult
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto {

    private long id;

    private String name;

    private String description;

    private List<GroupRelationDto> members;

    public GroupDto(Group group) {
        setId(group.getId());
        setName(group.getName());
        setDescription(group.getDescription());

        //Members
        List<GroupRelationDto> members = new ArrayList<>();
        group.getMembers().forEach(to -> {
            members.add(new GroupRelationDto(to));
        });
        setMembers(members);
    }

    public GroupDto () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<GroupRelationDto> getMembers() {
        return members;
    }

    public void setMembers(List<GroupRelationDto> members) {
        this.members = members;
    }
}
