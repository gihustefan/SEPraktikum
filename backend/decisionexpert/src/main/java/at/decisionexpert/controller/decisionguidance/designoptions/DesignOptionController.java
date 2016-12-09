package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;

/**
 * Created by stefanhaselboeck on 06.09.16.
 */
public interface DesignOptionController {

    /**
     * Receive a single model of an DesignOption
     *
     * @param idDesignOption The decisionGuidanceModel Id of interest
     * @return The DesignOption model of the DesignOption - selected by
     * idDesignOption
     */
    DesignOptionDto getDesignOption(Long idDesignOption);
}
