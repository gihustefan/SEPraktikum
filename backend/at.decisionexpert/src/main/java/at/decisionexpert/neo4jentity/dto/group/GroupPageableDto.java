package at.decisionexpert.neo4jentity.dto.group;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

/**
 * Created by stefanhaselboeck on 29.11.16.
 */
@QueryResult
public class GroupPageableDto {
    private List<GroupDto> groups;

    private Long countTotal;

    public GroupPageableDto(List<GroupDto> groups, Long countTotal) {
        super();
        this.groups = groups;
        this.countTotal = countTotal;
    }

    public GroupPageableDto() {
        super();
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
