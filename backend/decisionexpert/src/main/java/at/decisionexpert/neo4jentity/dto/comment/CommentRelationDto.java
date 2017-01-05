package at.decisionexpert.neo4jentity.dto.comment;

import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.node.User;
import at.decisionexpert.neo4jentity.relationship.HasComment;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentRelationDto<V extends Node> {

    private Long id;

    private String text;

    private Long idAttribute;

    private User creator;

    private List<CommentRelationDto> comments;

    public CommentRelationDto() {
        super();
    }

    public CommentRelationDto(HasComment<V> hasComment) {
        id = hasComment.getId();
        text = hasComment.getEndNode().getText();
        idAttribute = hasComment.getEndNode().getId();
        creator = hasComment.getEndNode().getCreator();

        //Comments
        List<CommentRelationDto> comments = new ArrayList<>();
        hasComment.getEndNode().getComments().forEach(to -> {
            comments.add(new CommentRelationDto(to));
        });
        setComments(comments);
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

    public List<CommentRelationDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentRelationDto> comments) {
        this.comments = comments;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
