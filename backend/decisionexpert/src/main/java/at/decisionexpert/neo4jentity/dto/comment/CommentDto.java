package at.decisionexpert.neo4jentity.dto.comment;

import at.decisionexpert.neo4jentity.node.Comment;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by stefanhaselboeck on 09.12.16.
 */
public class CommentDto {

    private Long id;

    private String text;

    private Long ownerId;

    private String ownerName;

    private LocalDateTime created;

    private LocalDateTime modified;

    private List<CommentRelationDto> comments;

    public CommentDto() {
        super();
    }

    public CommentDto (Comment comment) {
        super();
        setId(comment.getId());
        setText(comment.getText());
        setOwnerId(comment.getCreator().getId());
        setOwnerName(comment.getCreator().getOriginalUsername());
        setCreated(LocalDateTime.ofInstant(Instant.ofEpochMilli(comment.getCreationDate()),
                TimeZone.getDefault().toZoneId()));
        setModified(LocalDateTime.ofInstant(Instant.ofEpochMilli(comment.getLastModified()),
                TimeZone.getDefault().toZoneId()));

        //Comments
        List<CommentRelationDto> comments = new ArrayList<>();
        comment.getComments().forEach(to -> {
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public List<CommentRelationDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentRelationDto> comments) {
        this.comments = comments;
    }
}
