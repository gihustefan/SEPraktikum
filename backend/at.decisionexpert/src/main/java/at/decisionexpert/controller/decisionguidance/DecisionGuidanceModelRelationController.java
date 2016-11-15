package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
public interface DecisionGuidanceModelRelationController {

    DecisionGuidanceModelRelationDto create(Long idDecisionGuidanceModel, DecisionGuidanceModelRelationDto value);

    DecisionGuidanceModelRelationDto updateAttributes(Long idDecisionGuidanceModel, Long idRelation, DecisionGuidanceModelRelationDto newValues);

    void delete(Long idDecisionGuidanceModel, Long idRelation);
}
