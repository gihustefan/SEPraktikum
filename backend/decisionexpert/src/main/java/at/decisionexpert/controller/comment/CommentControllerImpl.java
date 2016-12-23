package at.decisionexpert.controller.comment;

import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 23.12.16.
 */
@RestController
@RequestMapping("api/comment")
@ResponseBody
public class CommentControllerImpl {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{idComment}", method = RequestMethod.GET)
    public CommentDto getComment(@PathVariable Long idComment) {
        return commentService.getComment(idComment);
    }
}
