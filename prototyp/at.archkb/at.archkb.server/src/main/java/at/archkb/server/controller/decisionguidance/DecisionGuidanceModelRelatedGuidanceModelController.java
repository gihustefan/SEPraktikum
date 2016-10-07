package at.archkb.server.controller.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DecisionGuidanceModelRelatedGuidanceModelController {

    DecisionGuidanceModelRelatedGuidanceModelsDto create(Long idDecisionGuidanceModel, DecisionGuidanceModelRelatedGuidanceModelsDto value);

    DecisionGuidanceModelRelatedGuidanceModelsDto updateAttributes(Long idDecisionGuidanceModel, Long idRelation, DecisionGuidanceModelRelatedGuidanceModelsDto newValues);

    void delete(Long idDecisionGuidanceModel, Long idRelation);
}
