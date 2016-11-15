package at.decisionexpert.business.comment;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.*;
import at.decisionexpert.neo4jentity.relationship.HasComment;
import at.decisionexpert.repository.node.comment.CommentRepository;
import at.decisionexpert.repository.node.decisiondocumentation.DecisionDocumentationRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.relationship.comment.HasCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
@Component
public class CommentBusinessImpl implements CommentBusiness {

    @Autowired
    private DecisionGuidanceModelRepository decisionGuidanceModelRepository;

    @Autowired
    private DecisionDocumentationRepository decisionDocumentationRepository;

    @Autowired
    private HasCommentRepository hasCommentRepository;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public <A extends Node> CommentRelationDto createCommentRelation(Long idModel, CommentRelationChangeRequestDto commentValue, Class<A> toNodeType) {
        Assert.notNull(idModel);
        Assert.notNull(commentValue);

        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Assert.notNull(user);

        A startModel = null;
        if (toNodeType == DecisionGuidanceModel.class) {
            startModel = (A) decisionGuidanceModelRepository.findOne(idModel, 0);
        } else if (toNodeType == DecisionDocumentationModel.class) {
            startModel = (A) decisionDocumentationRepository.findOne(idModel, 0);
        }
        Assert.notNull(startModel);

        Comment newComment = new Comment();
        newComment.setText(commentValue.getText());
        newComment.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        newComment.setLastModified(newComment.getCreationDate());
        newComment.setCreator(userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Comment endComment = commentRepository.save(newComment);

        HasComment hasComment = new HasComment(startModel, endComment);
        hasComment = hasCommentRepository.save(hasComment);

        return new CommentRelationDto(hasComment);
    }

    @Override
    public CommentRelationDto updateExistingCommentRelationAttribute(Long idModel, Long idCommentRelation, CommentRelationChangeRequestDto newValues) {
        Assert.notNull(idModel);
        Assert.notNull(idCommentRelation);

        HasComment hasComment = hasCommentRepository.findOne(idCommentRelation);
        Assert.notNull(hasComment);

        Comment comment = (Comment) hasComment.getEndNode();
        comment.setText(newValues.getText());
        hasComment.setEndNode(comment);

        hasCommentRepository.save(hasComment);

        return new CommentRelationDto(hasComment);
    }

    @Override
    public void deleteCommentRelationAttribute(Long idModel, Long idCommentRelation) {
        Assert.notNull(idModel);
        Assert.notNull(idCommentRelation);

        HasComment hasComment = hasCommentRepository.findOne(idCommentRelation);

        if (hasComment == null)
            return;

        hasCommentRepository.delete(hasComment);
    }
}
