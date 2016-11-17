package at.decisionexpert.neo4jentity.dto.group;

import at.decisionexpert.neo4jentity.relationship.HasMember;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 17.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupRelationDto {

    private Long id;

    private Long idAttribute;

    public GroupRelationDto(Long id, Long idAttribute) {
        this.id = id;
        this.idAttribute = idAttribute;
    }

    public GroupRelationDto(HasMember hasMember) {
        this.id = hasMember.getId();
        this.idAttribute = hasMember.getEndNode().getId();
    }

    public GroupRelationDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }
}
