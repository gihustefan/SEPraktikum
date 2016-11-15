package at.decisionexpert.business.coredata;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.decisionexpert.neo4jentity.node.CoreData;

import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DGMCoreDataBusiness {

    <T extends CoreData> List<DecisionGuidanceModelRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass);

    <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedGuidanceModels(String titlePartial);

    <T extends CoreData> List<DecisionGuidanceModelDesignOptionRelationDto> getDesignOptions(String titlePartial);

}
