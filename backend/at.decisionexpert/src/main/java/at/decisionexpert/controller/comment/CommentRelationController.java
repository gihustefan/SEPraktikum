package at.decisionexpert.controller.comment;

import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface CommentRelationController {

    CommentRelationDto create(Long idModel, CommentRelationChangeRequestDto commentValue);

    CommentRelationDto updateAttributes(Long idModel, Long idCommentRelation, CommentRelationChangeRequestDto newCommentValue);

    void delete(Long idModel, Long idCommentRelation);
}
