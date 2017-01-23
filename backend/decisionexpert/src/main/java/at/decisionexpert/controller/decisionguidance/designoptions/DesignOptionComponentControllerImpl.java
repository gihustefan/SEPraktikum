package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasRequiredComponent;
import at.decisionexpert.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 21.09.16.
 */
@RestController
@RequestMapping("api/do/{idDesignOption}/components")
@ResponseBody
public class DesignOptionComponentControllerImpl implements DesignOptionRelationController {

    @Autowired
    private DesignOptionService designOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionRelationDto create(@PathVariable Long idDesignOption, @RequestBody DesignOptionRelationDto requiredComponent) {
        return designOptionService.createRelation(idDesignOption, requiredComponent, HasRequiredComponent.class, Component.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionComponentRelation}",method = RequestMethod.PATCH)
    public DesignOptionRelationDto updateAttributes(@PathVariable Long idDesignOption, @PathVariable Long idDesginOptionComponentRelation, @RequestBody DesignOptionRelationDto newValues) {
        return designOptionService.updateExistingRelationAttribute(idDesignOption, idDesginOptionComponentRelation, newValues, HasRequiredComponent.class, Component.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionComponentRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesignOption, @PathVariable Long idDesginOptionComponentRelation) {
        designOptionService.deleteRelationAttribute(idDesignOption, idDesginOptionComponentRelation, HasRequiredComponent.class);
    }
}
