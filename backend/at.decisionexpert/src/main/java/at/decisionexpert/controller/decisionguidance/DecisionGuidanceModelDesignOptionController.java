package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;

/**
 * Created by stefanhaselboeck on 11.11.16.
 */
public interface DecisionGuidanceModelDesignOptionController {
    DecisionGuidanceModelDesignOptionRelationDto create(Long idDesignOption, DecisionGuidanceModelDesignOptionRelationDto value);

    DecisionGuidanceModelDesignOptionRelationDto updateAttributes(Long idDesignOption, Long idRelation, DecisionGuidanceModelDesignOptionRelationDto newValues);

    void delete(Long idDesignOption, Long idRelation);
}
