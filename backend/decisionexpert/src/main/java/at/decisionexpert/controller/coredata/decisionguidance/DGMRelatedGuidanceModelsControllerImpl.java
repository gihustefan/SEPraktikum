package at.decisionexpert.controller.coredata.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.service.coredata.DGMCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanhaselboeck on 22.08.16.
 */
@RestController
@RequestMapping("api/relatedguidancemodels")
@ResponseBody
public class DGMRelatedGuidanceModelsControllerImpl implements DGMCoreDataController {

    @Autowired
    DGMCoreDataService dgmCoreDataService;

    @Override
    @RequestMapping(value = "/{titlePartial}", method = RequestMethod.GET)
    public List<DecisionGuidanceModelRelationDto> getCoreData(@PathVariable String titlePartial) {
        return dgmCoreDataService.getCoreData(titlePartial, DecisionGuidanceModel.class);
    }
}
