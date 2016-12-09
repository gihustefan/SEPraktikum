package at.decisionexpert.service.vote;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.Node;
import javax.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
public interface VoteService {

    @PreAuthorize("hasRole('ROLE_USER')")
    <A extends Node> VoteRelationDto createVoteRelation(Long idModel, VoteRelationChangeRequestDto voteValue, Class<A> toNodeType);

    @PreAuthorize("hasRole('ROLE_USER')")
    VoteRelationDto updateExistingVoteRelation(@NotNull Long idModel, @NotNull Long idVoteRelation, @NotNull VoteRelationChangeRequestDto newValues);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteVoteRelation(@NotNull Long idModel, @NotNull Long idVoteRelation);
}
