package at.decisionexpert.neo4jentity.dto.comment;

import at.decisionexpert.neo4jentity.node.Comment;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Created by stefanhaselboeck on 09.12.16.
 */
public class CommentDto {

    private Serializable id;

    private String text;

    private Serializable ownerId;

    private String ownerName;

    private LocalDateTime created;

    private LocalDateTime modified;

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
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Serializable getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Serializable ownerId) {
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
}
