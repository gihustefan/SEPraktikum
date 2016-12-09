package at.decisionexpert.controller.vote;

import at.decisionexpert.neo4jentity.dto.vote.VoteRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.vote.VoteRelationDto;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 15.11.16.
 */
@RestController
@RequestMapping("api/do/{idDesignOption}/votes")
@ResponseBody
public class DesignOptionVoteRelationControllerImpl implements VoteRelationController{

    @Autowired
    private VoteService voteService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public VoteRelationDto create(@PathVariable Long idDesignOption, @RequestBody VoteRelationChangeRequestDto voteValue) {
        return voteService.createVoteRelation(idDesignOption, voteValue, DesignOption.class);
    }

    @Override
    @RequestMapping(value = "/{idVoteRelation}",method = RequestMethod.PATCH)
    public VoteRelationDto updateAttributes(@PathVariable Long idDesignOption, @PathVariable Long idVoteRelation, @RequestBody VoteRelationChangeRequestDto newVoteValue) {
        return voteService.updateExistingVoteRelation(idDesignOption, idVoteRelation, newVoteValue);
    }

    @Override
    @RequestMapping(value = "/{idVoteRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesignOption, @PathVariable Long idVoteRelation) {
        voteService.deleteVoteRelation(idDesignOption, idVoteRelation);
    }
}