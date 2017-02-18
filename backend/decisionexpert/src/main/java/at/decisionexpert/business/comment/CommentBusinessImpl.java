package at.decisionexpert.business.comment;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.exception.CommentNotFoundException;
import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import at.decisionexpert.neo4jentity.node.Comment;
import at.decisionexpert.neo4jentity.node.Node;
import at.decisionexpert.neo4jentity.relationship.HasComment;
import at.decisionexpert.repository.node.comment.CommentRepository;
import at.decisionexpert.repository.node.decisiondocumentation.DecisionDocumentationRepository;
import at.decisionexpert.repository.node.decisionguidance.DecisionGuidanceModelRepository;
import at.decisionexpert.repository.relationship.comment.HasCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static at.decisionexpert.controller.comment.CommentRelationControllerImpl.CommentStartNodeType.DGM;
import static at.decisionexpert.controller.comment.CommentRelationControllerImpl.CommentStartNodeType.ParentComment;

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
    public CommentDto getComment(Long idComment) throws CommentNotFoundException {
        if (idComment < 0) {
            throw new CommentNotFoundException();
        }

        Comment comment = commentRepository.findOne(idComment, 4);
        if (comment == null) {
            throw new CommentNotFoundException();
        }

        return new CommentDto(comment);
    }

    @Override
    public <A extends Node> CommentRelationDto createCommentRelation(Long idModel, String commentText, String toNodeType) {
        Assert.notNull(idModel);
//        Assert.notNull(commentValue);

        System.out.println(commentText);
        System.out.println(toNodeType);

        A startNode = null;
        if (toNodeType.equals(DGM.toString())) {
            startNode = (A) decisionGuidanceModelRepository.findOne(idModel, 0);
        }
//        else if (toNodeType == DecisionDocumentationModel.class) {
//            startNode = (A) decisionDocumentationRepository.findOne(idModel, 0);
//        }
        else if (toNodeType.equals(ParentComment.toString())) {
            startNode = (A) commentRepository.findOne(idModel, 0);
        }
        Assert.notNull(startNode);

        Comment newComment = new Comment();
        newComment.setText(commentText);
        newComment.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        newComment.setLastModified(newComment.getCreationDate());
        newComment.setCreator(userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Comment endComment = commentRepository.save(newComment);

        HasComment hasComment = new HasComment(startNode, endComment);
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
        commentRepository.delete(hasComment.getEndNode().getId());
    }
}
