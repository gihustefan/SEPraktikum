package at.decisionexpert.controller.coredata.designoption;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;

import java.util.List;

/**
 * Created by stefanhaselboeck on 16.01.17.
 */
public interface DOCoreDataController {

    List<DesignOptionRelationDto> getCoreData(String titlePartial);
}
