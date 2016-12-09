package at.decisionexpert.service.vote;

import at.decisionexpert.business.vote.VoteBusiness;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.Node;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteBusiness voteBusiness;

    @Override
    public <A extends Node> VoteRelationDto createVoteRelation(Long idModel, VoteRelationChangeRequestDto voteValue, Class<A> toNodeType) {
        return voteBusiness.createVoteRelation(idModel, voteValue, toNodeType);
    }

    @Override
    public VoteRelationDto updateExistingVoteRelation(@NotNull Long idModel, @NotNull Long idVoteRelation, @NotNull VoteRelationChangeRequestDto newValues) {
        return voteBusiness.updateExistingVoteRelation(idModel, idVoteRelation, newValues);
    }

    @Override
    public void deleteVoteRelation(@NotNull Long idModel, @NotNull Long idVoteRelation) {
        voteBusiness.deleteVoteRelation(idModel, idVoteRelation);
    }
}
