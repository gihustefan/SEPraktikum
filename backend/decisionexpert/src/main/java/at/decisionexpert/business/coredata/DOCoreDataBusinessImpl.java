package at.decisionexpert.business.coredata;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.repository.node.AttributeRepository;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 07.10.16.
 */
@Component
public class DOCoreDataBusinessImpl implements DOCoreDataBusiness {

    @Autowired
    NodeAttributeRepository nodeAttributeRepository;

    @Override
    public <T extends CoreData> List<DesignOptionRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass) {
        Assert.notNull(titlePartial);
        Assert.notNull(coreDataClass);

        List<DesignOptionRelationDto> result = new ArrayList<>();

        nodeAttributeRepository.findAllByTitle(titlePartial, coreDataClass).forEach(core -> {
            result.add(new DesignOptionRelationDto(core));
        });

        return result;
    }
}
