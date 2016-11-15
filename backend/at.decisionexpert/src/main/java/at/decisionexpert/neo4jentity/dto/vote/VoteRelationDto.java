package at.decisionexpert.neo4jentity.dto.vote;

import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.relationship.HasVote;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteRelationDto<V extends Node> {

    private Long id;

    private boolean vote;

    private Long idAttribute;

    public VoteRelationDto(HasVote<V> hasVote) {
        id = hasVote.getId();
        vote = hasVote.isVote();
        idAttribute = hasVote.getEndNode().getId();
    }

    public VoteRelationDto(Long id, boolean vote, Long idAttribute) {
        super();
        this.id = id;
        this.vote = vote;
        this.idAttribute = idAttribute;
    }

    public VoteRelationDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }
}
