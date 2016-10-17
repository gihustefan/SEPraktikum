package at.decisionexpert.controller.component.technologyoption;


import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TechnologyOptionController {

    /**
     * Receive a single model of an TechnologyOption
     *
     * @param idTechnologyOption The decisionGuidanceModel Id of interest
     * @return The TechnologyOption model of the TechnologyOption - selected by
     * idTechnologyOption
     */
    TechnologyOptionDto getTechnologyOption(Long idTechnologyOption);
}
