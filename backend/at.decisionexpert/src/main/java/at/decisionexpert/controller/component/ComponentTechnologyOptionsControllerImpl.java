package at.decisionexpert.controller.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.TechnologyOption;
import at.decisionexpert.neo4jentity.relationship.component.HasTechnologyOption;
import at.decisionexpert.service.component.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/comp/{idComponent}/technologyoptions")
@ResponseBody
public class ComponentTechnologyOptionsControllerImpl implements ComponentRelationController{

    @Autowired
    ComponentService componentService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ComponentRelationDto create(@PathVariable Long idComponent, @RequestBody ComponentRelationDto technologyOption) {
        return componentService.createRelation(idComponent, technologyOption, HasTechnologyOption.class, TechnologyOption.class);
    }

    @Override
    @RequestMapping(value = "/{idComponentTechnologyOption}",method = RequestMethod.PATCH)
    public ComponentRelationDto updateAttributes(@PathVariable Long idComponent, @PathVariable Long idComponentTechnologyOption, @RequestBody ComponentRelationDto newValues) {
        return componentService.updateExistingRelationAttribute(idComponent, idComponentTechnologyOption, newValues, HasTechnologyOption.class);
    }

    @Override
    @RequestMapping(value = "/{idComponentTechnologyOption}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idComponent, @PathVariable Long idComponentTechnologyOption) {
        componentService.deleteRelationAttribute(idComponent, idComponentTechnologyOption, HasTechnologyOption.class);
    }
}
