package at.decisionexpert.business.belonging;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.node.Node;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
public interface BelongingBusiness {
    <A extends Node> BelongingRelationDto createBelongingRelation(Long idModel, Long idGroup, Class<A> toNodeType);

    void deleteBelongingRelation(Long idModel, Long idBelongingRelation);
}
