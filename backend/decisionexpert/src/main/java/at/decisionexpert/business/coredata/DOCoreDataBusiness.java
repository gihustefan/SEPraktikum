package at.decisionexpert.business.coredata;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;

import java.util.List;

/**
 * Created by stefanhaselboeck on 07.10.16.
 */
public interface DOCoreDataBusiness {

    <T extends CoreData> List<DesignOptionRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass);
}
