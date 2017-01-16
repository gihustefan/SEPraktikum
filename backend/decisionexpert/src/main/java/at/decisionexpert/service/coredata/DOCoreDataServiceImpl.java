package at.decisionexpert.service.coredata;

import at.decisionexpert.business.coredata.DOCoreDataBusiness;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 16.01.17.
 */
@Service
public class DOCoreDataServiceImpl implements DOCoreDataService{

    @Autowired
    DOCoreDataBusiness doCoreDataBusiness;

    @Override
    public <T extends CoreData> List<DesignOptionRelationDto> getCoreData(@Size(min = 3, message = "min 3 chars") String titlePartial, @NotNull Class<T> coreDataClass) {
        return doCoreDataBusiness.getCoreData(titlePartial, coreDataClass);
    }
}
