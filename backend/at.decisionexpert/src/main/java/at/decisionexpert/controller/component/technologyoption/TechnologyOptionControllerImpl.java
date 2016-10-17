package at.decisionexpert.controller.component.technologyoption;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionDto;
import at.decisionexpert.service.component.technologyoption.TechnologyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/to")
@ResponseBody
public class TechnologyOptionControllerImpl implements TechnologyOptionController{

    @Autowired
    TechnologyOptionService technologyOptionService;

    @Override
    @RequestMapping(value = "/{idTechnologyOption}", method = RequestMethod.GET)
    public TechnologyOptionDto getTechnologyOption(@PathVariable Long idTechnologyOption) {
        return technologyOptionService.getTechnologyOption(idTechnologyOption);
    }
}
