package at.archkb.server.business.coredata;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import at.archkb.server.repository.node.decisionguidance.DecisionGuidanceModelAttributeRepository;
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
    DecisionGuidanceModelAttributeRepository coreDataRepository;

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRealtionDto> getCoreData(String titlePartial, Class<T> coreDataClass) {
        Assert.notNull(titlePartial);
        Assert.notNull(coreDataClass);

        List<DecisionGuidanceModelRealtionDto> result = new ArrayList<>();

        coreDataRepository.findAllByTitle(titlePartial, coreDataClass).forEach(core -> {
            result.add(new DecisionGuidanceModelRealtionDto(core));
        });

        return result;
    }

    @Override
    public <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedTopics(String titlePartial) {
        Assert.notNull(titlePartial);

        List<DecisionGuidanceModelRelatedGuidanceModelsDto> result = new ArrayList<>();

        coreDataRepository.findDGMByTitle(titlePartial, DecisionGuidanceModel.class).forEach(node -> {
            result.add(new DecisionGuidanceModelRelatedGuidanceModelsDto(node));
        });

        return result;
    }
}
