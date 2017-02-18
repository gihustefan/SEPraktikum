package at.decisionexpert.neo4jentity.dto.comment;

import at.decisionexpert.neo4jentity.node.Node;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentRelationChangeRequestDto<V extends Node> {

    private String text;
    private String parentType;

    public String getText() {
        return text;
    }

    public String getParentType() {
        return parentType;
    }

    public void setText(String text) {
        this.text = text;
    }
}
