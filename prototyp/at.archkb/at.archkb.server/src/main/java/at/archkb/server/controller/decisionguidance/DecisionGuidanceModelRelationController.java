package at.archkb.server.controller.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
public interface DecisionGuidanceModelRelationController {

    DecisionGuidanceModelRealtionDto create(Long idDecisionGuidanceModel, DecisionGuidanceModelRealtionDto value);

    DecisionGuidanceModelRealtionDto updateAttributes(Long idDecisionGuidanceModel, Long idRelation, DecisionGuidanceModelRealtionDto newValues);

    void delete(Long idDecisionGuidanceModel, Long idRelation);
}
