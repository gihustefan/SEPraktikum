package at.decisionexpert.service.comment;

import at.decisionexpert.neo4jentity.dto.comment.CommentDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.comment.CommentRelationDto;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface CommentService {

    @PreAuthorize("hasRole('ROLE_USER')")
    CommentDto getComment(@NotNull Long idComment);

    @PreAuthorize("hasRole('ROLE_USER')")
    CommentRelationDto createCommentRelation(@NotNull Long idModel, @NotNull String comment, @NotNull String toNodeType);

    @PreAuthorize("hasRole('ROLE_USER')")
    CommentRelationDto updateExistingCommentRelationAttribute(@NotNull Long idModel, @NotNull Long idCommentRelation, @NotNull CommentRelationChangeRequestDto newValues);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteCommentRelationAttribute(@NotNull Long idModel, @NotNull Long idCommentRelation);
}
