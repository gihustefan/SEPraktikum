package at.decisionexpert.controller.vote;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
public interface VoteRelationController {

    VoteRelationDto create(Long idModel, VoteRelationChangeRequestDto voteValue);

    VoteRelationDto updateAttributes(Long idModel, Long idVoteRelation, VoteRelationChangeRequestDto newVoteValue);

    void delete(Long idModel, Long idVoteRelation);
}
