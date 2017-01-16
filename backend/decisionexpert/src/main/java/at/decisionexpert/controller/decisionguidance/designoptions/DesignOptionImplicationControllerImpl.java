package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.Implication;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasImplication;
import at.decisionexpert.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RestController
@RequestMapping("api/do/{idDesginOption}/implications")
@ResponseBody
public class DesignOptionImplicationControllerImpl implements DesignOptionRelationController {

    @Autowired
    private DesignOptionService designOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionRelationDto create(@PathVariable Long idDesginOption, @RequestBody DesignOptionRelationDto implication) {
        return designOptionService.createRelation(idDesginOption, implication, HasImplication.class, Implication.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionImplication}",method = RequestMethod.PATCH)
    public DesignOptionRelationDto updateAttributes(@PathVariable Long idDesginOption, @PathVariable Long idDesginOptionImplication, @RequestBody DesignOptionRelationDto newValues) {
        return designOptionService.updateExistingRelationAttribute(idDesginOption, idDesginOptionImplication, newValues, HasImplication.class, Implication.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionImplication}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesginOption, @PathVariable Long idDesginOptionImplication) {
        designOptionService.deleteRelationAttribute(idDesginOption, idDesginOptionImplication, HasImplication.class);
    }
}
