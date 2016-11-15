package at.decisionexpert.controller.coredata.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;
import at.decisionexpert.service.coredata.DGMCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RestController
@RequestMapping("api/designoptions/{titlePartial}")
@ResponseBody
public class DGMDesignOptionsControllerImpl implements DGMDesignOptionsController{
    @Autowired
    DGMCoreDataService dgmCoreDataService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<DecisionGuidanceModelDesignOptionRelationDto> getDesignOptions(@PathVariable String titlePartial) {
        return dgmCoreDataService.getDesignOptions(titlePartial);
    }
}
