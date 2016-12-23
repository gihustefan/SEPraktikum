package at.decisionexpert.controller.comment;

import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/comments")
@ResponseBody
public class DecisionGuidanceModelCommentRelationControllerImpl implements CommentRelationController {

    @Autowired
    private CommentService commentService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public CommentRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody CommentRelationChangeRequestDto commentValue) {
        return commentService.createCommentRelation(idDecisionGuidanceModel, commentValue, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.PATCH)
    public CommentRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idCommentRelation, @RequestBody CommentRelationChangeRequestDto newCommentValue) {
        return commentService.updateExistingCommentRelationAttribute(idDecisionGuidanceModel, idCommentRelation, newCommentValue);
    }

    @Override
    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idCommentRelation) {
        commentService.deleteCommentRelationAttribute(idDecisionGuidanceModel, idCommentRelation);
    }
}
