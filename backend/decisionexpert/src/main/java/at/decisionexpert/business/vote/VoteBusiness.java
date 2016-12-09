package at.decisionexpert.business.vote;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.Node;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
public interface VoteBusiness {

    <A extends Node> VoteRelationDto createVoteRelation(Long idModel, VoteRelationChangeRequestDto voteValue, Class<A> toNodeType);

    VoteRelationDto updateExistingVoteRelation(Long idModel, Long idVoteRelation, VoteRelationChangeRequestDto newValues);

    void deleteVoteRelation(Long idModel, Long idVoteRelation);
}
