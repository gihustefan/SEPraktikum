package at.decisionexpert.controller.belonging;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
public interface BelongingRelationController {

    BelongingRelationDto create(Long idModel, Long idGroup);

    void delete(Long idModel, Long idBelongingRelation);
}
