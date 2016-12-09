package at.decisionexpert.business.vote;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.*;
import at.decisionexpert.neo4jentity.relationship.HasVote;
import at.decisionexpert.repository.node.decisiondocumentation.DecisionDocumentationRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.node.decisionguidance.designoption.DesignOptionRepository;
import at.decisionexpert.repository.relationship.vote.HasVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@Component
public class VoteBusinessImpl implements VoteBusiness {

    @Autowired
    private DecisionGuidanceModelRepository decisionGuidanceModelRepository;

    @Autowired
    private DecisionDocumentationRepository decisionDocumentationRepository;

    @Autowired
    private DesignOptionRepository designOptionRepository;

    @Autowired
    private HasVoteRepository hasVoteRepository;

    @Autowired
    private UserBusiness userBusiness;

    @Override
    public <A extends Node> VoteRelationDto createVoteRelation(Long idModel, VoteRelationChangeRequestDto voteValue, Class<A> toNodeType) {
        Assert.notNull(idModel);
        Assert.notNull(voteValue);

        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        A startModel = null;
        if (toNodeType == DecisionGuidanceModel.class) {
            startModel = (A) decisionGuidanceModelRepository.findOne(idModel, 0);
        } else if (toNodeType == DesignOption.class) {
            startModel = (A) designOptionRepository.findOne(idModel, 0);
        } else if (toNodeType == DecisionDocumentationModel.class) {
            startModel = (A) decisionDocumentationRepository.findOne(idModel, 0);
        } else if (toNodeType == DesignDecision.class) {
            //startModel = (A) designDecisionRepository.findOne(idModel, 0);
        }
        Assert.notNull(startModel);

        HasVote hasVote = new HasVote(startModel, user, voteValue.isVote());
        hasVote = hasVoteRepository.save(hasVote);

        return new VoteRelationDto(hasVote);
    }

    @Override
    public VoteRelationDto updateExistingVoteRelation(Long idModel, Long idVoteRelation, VoteRelationChangeRequestDto newValues) {
        Assert.notNull(idModel);
        Assert.notNull(idVoteRelation);

        // Fetching the hasTradeoff from DB
        HasVote hasVote = hasVoteRepository.findOne(idVoteRelation);
        Assert.notNull(hasVote);
        hasVote.setVote(newValues.isVote());
        hasVoteRepository.save(hasVote);

        return new VoteRelationDto(hasVote);
    }

    @Override
    public void deleteVoteRelation(Long idModel, Long idVoteRelation) {
        Assert.notNull(idVoteRelation);

        // Fetch the relation that should be deleted
        HasVote hasVote = hasVoteRepository.findOne(idVoteRelation);

        if (hasVote == null)
            return;

        hasVoteRepository.delete(hasVote);
    }
}
