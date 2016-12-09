package at.decisionexpert.controller.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentDto;

/**
 * Created by stefanhaselboeck on 14.10.16.
 */
public interface ComponentController {

    /**
     * Receive a single model of an DesignOption
     *
     * @param idComponent The decisionGuidanceModel Id of interest
     * @return The DesignOption model of the DesignOption - selected by
     * idDesignOption
     */
    ComponentDto getComponent(Long idComponent);
}
