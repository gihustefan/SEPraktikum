package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.decisionexpert.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.09.16.
 */
@RestController
@RequestMapping("api/do")
@ResponseBody
public class DesignOptionControllerImpl implements DesignOptionController{

    @Autowired
    DesignOptionService designOptionService;

    @Override
    @RequestMapping(value = "/{idDesignOption}", method = RequestMethod.GET)
    public DesignOptionDto getDesignOption(@PathVariable Long idDesignOption) {
        return designOptionService.getDesignOption(idDesignOption);
    }
}
