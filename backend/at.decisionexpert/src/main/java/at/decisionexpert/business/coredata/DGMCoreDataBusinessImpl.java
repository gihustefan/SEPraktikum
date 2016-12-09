package at.decisionexpert.business.coredata;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.repository.node.AttributeRepository;
import at.decisionexpert.repository.node.NodeAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@Component
public class DGMCoreDataBusinessImpl implements DGMCoreDataBusiness{

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    NodeAttributeRepository nodeAttributeRepository;

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRelationDto> getCoreData(String titlePartial, Class<T> coreDataClass) {
        Assert.notNull(titlePartial);
        Assert.notNull(coreDataClass);

        List<DecisionGuidanceModelRelationDto> result = new ArrayList<>();

        nodeAttributeRepository.findAllByTitle(titlePartial, coreDataClass).forEach(core -> {
            result.add(new DecisionGuidanceModelRelationDto(core));
        });

        return result;
    }
}
