package at.archkb.server.business.coredata;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.neo4jentity.node.CoreData;

import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DGMCoreDataBusiness {

    <T extends CoreData> List<DecisionGuidanceModelRealtionDto> getCoreData(String titlePartial, Class<T> coreDataClass);

    <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedTopics(String titlePartial);
}
