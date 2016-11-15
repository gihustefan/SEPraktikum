package at.decisionexpert.service.comment;

import at.decisionexpert.business.comment.CommentBusiness;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentBusiness commentBusiness;

    @Override
    public <A extends Node> CommentRelationDto createCommentRelation(@NotNull Long idModel, @NotNull CommentRelationChangeRequestDto comment, @NotNull Class<A> toNodeType) {
        return commentBusiness.createCommentRelation(idModel, comment, toNodeType);
    }

    @Override
    public CommentRelationDto updateExistingCommentRelationAttribute(@NotNull Long idModel, @NotNull Long idCommentRelation, @NotNull CommentRelationChangeRequestDto newValues) {
        return commentBusiness.updateExistingCommentRelationAttribute(idModel, idCommentRelation, newValues);
    }

    @Override
    public void deleteCommentRelationAttribute(@NotNull Long idModel, @NotNull Long idCommentRelation) {
        commentBusiness.deleteCommentRelationAttribute(idModel, idCommentRelation);
    }
}
