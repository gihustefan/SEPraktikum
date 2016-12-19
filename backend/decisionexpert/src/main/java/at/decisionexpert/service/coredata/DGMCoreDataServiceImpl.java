package at.decisionexpert.service.coredata;

import at.decisionexpert.business.coredata.DGMCoreDataBusiness;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@Service
public class DGMCoreDataServiceImpl implements DGMCoreDataService {

    @Autowired
    DGMCoreDataBusiness dgmCoreDataBusiness;

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRelationDto> getCoreData(@Size(min = 3, message = "min 3 chars") String titlePartial, @NotNull Class<T> coreDataClass) {
        return dgmCoreDataBusiness.getCoreData(titlePartial, coreDataClass);
    }

}
