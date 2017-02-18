package at.decisionexpert.business.comment;

import at.decisionexpert.exception.CommentNotFoundException;
import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.Node;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface CommentBusiness {

    CommentDto getComment(Long idComment) throws CommentNotFoundException;

    <A extends Node> CommentRelationDto createCommentRelation(Long idModel, String commentText, String toNodeType);

    CommentRelationDto updateExistingCommentRelationAttribute(Long idModel, Long idCommentRelation, CommentRelationChangeRequestDto newValues);

    void deleteCommentRelationAttribute(Long idModel, Long idCommentRelation);
}
