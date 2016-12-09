package at.decisionexpert.controller.vote;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/votes")
@ResponseBody
public class DecisionGuidanceModelVoteRelationControllerImpl implements VoteRelationController {

    @Autowired
    private VoteService voteService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public VoteRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody VoteRelationChangeRequestDto voteValue) {
        return voteService.createVoteRelation(idDecisionGuidanceModel, voteValue, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idVoteRelation}",method = RequestMethod.PATCH)
    public VoteRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idVoteRelation, @RequestBody VoteRelationChangeRequestDto newVoteValue) {
        return voteService.updateExistingVoteRelation(idDecisionGuidanceModel, idVoteRelation, newVoteValue);
    }

    @Override
    @RequestMapping(value = "/{idVoteRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idVoteRelation) {
        voteService.deleteVoteRelation(idDecisionGuidanceModel, idVoteRelation);
    }
}
