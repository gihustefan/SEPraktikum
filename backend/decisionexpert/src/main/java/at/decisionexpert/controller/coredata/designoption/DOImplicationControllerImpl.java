package at.decisionexpert.controller.coredata.designoption;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.Implication;
import at.decisionexpert.service.coredata.DOCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanhaselboeck on 16.01.17.
 */
@RestController
@RequestMapping("api/dgm/do/implications")
@ResponseBody
public class DOImplicationControllerImpl implements DOCoreDataController {
    @Autowired
    DOCoreDataService doCoreDataService;

    @Override
    @RequestMapping(value = "/{titlePartial}", method = RequestMethod.GET)
    public List<DesignOptionRelationDto> getCoreData(@PathVariable String titlePartial) {
        return doCoreDataService.getCoreData(titlePartial, Implication.class);
    }
}
