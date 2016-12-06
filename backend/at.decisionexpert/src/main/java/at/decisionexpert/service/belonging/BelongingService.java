package at.decisionexpert.service.belonging;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.node.Node;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
public interface BelongingService {
    @PreAuthorize("hasRole('ROLE_USER')")
    <A extends Node> BelongingRelationDto createBelongingRelation(@NotNull Long idModel, @NotNull Long idGroup, @NotNull Class<A> toNodeType);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteBelongingRelation(@NotNull Long idModel, @NotNull Long idCommentRelation);
}
