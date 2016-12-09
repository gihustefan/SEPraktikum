package at.decisionexpert.neo4jentity.dto.comment;

import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.relationship.HasComment;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentRelationDto<V extends Node> {
    private Long id;

    private String text;

    private Long idAttribute;

    public CommentRelationDto() {
        super();
    }

    public CommentRelationDto(HasComment<V> hasComment) {
        id = hasComment.getId();
        text = hasComment.getEndNode().getText();
        idAttribute = hasComment.getEndNode().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }
}
