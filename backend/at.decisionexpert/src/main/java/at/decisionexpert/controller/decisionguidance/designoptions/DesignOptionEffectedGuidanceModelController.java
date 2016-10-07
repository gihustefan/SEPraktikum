package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
public interface DesignOptionEffectedGuidanceModelController {

    DesignOptionEffectedGuidanceModelsDto create(Long idDesignOption, DesignOptionEffectedGuidanceModelsDto value);

    DesignOptionEffectedGuidanceModelsDto updateAttributes(Long idDesignOption, Long idRelation, DesignOptionEffectedGuidanceModelsDto newValues);

    void delete(Long idDesignOption, Long idRelation);
}
