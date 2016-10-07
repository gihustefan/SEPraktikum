package at.archkb.server.service.coredata;

import at.archkb.server.business.coredata.DGMCoreDataBusiness;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.neo4jentity.node.CoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@Service
public class DGMCoreDataServiceImpl implements DGMCoreDataService {

    @Autowired
    DGMCoreDataBusiness dgmCoreDataBusiness;

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRealtionDto> getCoreData(@Size(min = 3) String titlePartial, @NotNull Class<T> coreDataClass) {
        return dgmCoreDataBusiness.getCoreData(titlePartial, coreDataClass);
    }

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedGuidanceModels(@Size(min = 3) String titlePartial) {
        return dgmCoreDataBusiness.getRelatedTopics(titlePartial);
    }

}
