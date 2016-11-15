package at.decisionexpert.neo4jentity.dto.vote;

import at.decisionexpert.neo4jentity.node.Node;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteRelationChangeRequestDto<V extends Node> {

    private boolean vote;

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
