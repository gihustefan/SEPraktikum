package at.archkb.server.controller.coredata.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.service.coredata.DGMCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by stefanhaselboeck on 22.08.16.
 */
@RestController
@RequestMapping("api/relatedguidancemodels")
@ResponseBody
public class DGMRelatedGuidanceModelsControllerImpl implements DGMRelatedGuidanceModelsController {

    @Autowired
    DGMCoreDataService dgmCoreDataService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<DecisionGuidanceModelRelatedGuidanceModelsDto> getGuidanceModels(String titlePartial) {
        return dgmCoreDataService.getRelatedGuidanceModels(titlePartial);
    }
}
