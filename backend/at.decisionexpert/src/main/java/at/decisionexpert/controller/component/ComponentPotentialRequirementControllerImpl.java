package at.decisionexpert.controller.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;
import at.decisionexpert.neo4jentity.node.Requirement;
import at.decisionexpert.neo4jentity.relationship.component.ComponentHasPotentialRequirement;
import at.decisionexpert.service.component.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/comp/{idComponent}/potentialrequirements")
@ResponseBody
public class ComponentPotentialRequirementControllerImpl implements ComponentRelationController {

    @Autowired
    ComponentService componentService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ComponentRelationDto create(@PathVariable Long idComponent, @RequestBody ComponentRelationDto requirement) {
        return componentService.createRelation(idComponent, requirement, ComponentHasPotentialRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idComponentPotentialRequirement}",method = RequestMethod.PATCH)
    public ComponentRelationDto updateAttributes(@PathVariable Long idComponent,@PathVariable Long idComponentPotentialRequirement, ComponentRelationDto newValues) {
        return componentService.updateExistingRelationAttribute(idComponent, idComponentPotentialRequirement, newValues, ComponentHasPotentialRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idComponentPotentialRequirement}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idComponent,@PathVariable Long idComponentPotentialRequirement) {
        componentService.deleteRelationAttribute(idComponent, idComponentPotentialRequirement, ComponentHasPotentialRequirement.class);
    }
}
