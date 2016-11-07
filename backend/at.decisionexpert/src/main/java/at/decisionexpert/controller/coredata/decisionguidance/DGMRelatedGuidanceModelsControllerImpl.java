package at.decisionexpert.controller.coredata.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.decisionexpert.service.coredata.DGMCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanhaselboeck on 22.08.16.
 */
@RestController
@RequestMapping("api/relatedguidancemodels/{titlePartial}")
@ResponseBody
public class DGMRelatedGuidanceModelsControllerImpl implements DGMRelatedGuidanceModelsController {

    @Autowired
    DGMCoreDataService dgmCoreDataService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<DecisionGuidanceModelRelatedGuidanceModelsDto> getGuidanceModels(@PathVariable String titlePartial) {
        return dgmCoreDataService.getRelatedGuidanceModels(titlePartial);
    }
}
