package at.decisionexpert.controller.comment;

import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.Comment;
import at.decisionexpert.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 23.12.16.
 */
@RestController
@RequestMapping("api/comment/{idComment}/comments")
@ResponseBody
public class CommentCommentRelationControllerImpl implements CommentRelationController{

    @Autowired
    private CommentService commentService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public CommentRelationDto create(@PathVariable Long idComment, @RequestBody CommentRelationChangeRequestDto commentValue) {
        return commentService.createCommentRelation(idComment, commentValue, Comment.class);
    }

    @Override
    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.PATCH)
    public CommentRelationDto updateAttributes(@PathVariable Long idComment, @PathVariable Long idCommentRelation, @RequestBody CommentRelationChangeRequestDto newCommentValue) {
        return commentService.updateExistingCommentRelationAttribute(idComment, idCommentRelation, newCommentValue);
    }

    @Override
    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idComment, @PathVariable Long idCommentRelation) {
        commentService.deleteCommentRelationAttribute(idComment, idCommentRelation);
    }
}