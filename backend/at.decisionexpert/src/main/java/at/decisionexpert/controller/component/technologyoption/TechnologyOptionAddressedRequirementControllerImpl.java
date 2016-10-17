package at.decisionexpert.controller.component.technologyoption;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.Requirement;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.HasAddressedRequirement;
import at.decisionexpert.service.component.technologyoption.TechnologyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/to/{idTechnologyOption}/addressedrequirements")
@ResponseBody
public class TechnologyOptionAddressedRequirementControllerImpl implements TechnologyOptionRelationController {

    @Autowired
    TechnologyOptionService technologyOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public TechnologyOptionRelationDto create(@PathVariable Long idTechnologyOption, @RequestBody TechnologyOptionRelationDto requirement) {
        return technologyOptionService.createRelation(idTechnologyOption, requirement, HasAddressedRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idTechnologyOptionAddressedRequirement}",method = RequestMethod.PATCH)
    public TechnologyOptionRelationDto updateAttributes(@PathVariable Long idTechnologyOption, @PathVariable Long idTechnologyOptionAddressedRequirement, @RequestBody TechnologyOptionRelationDto newValues) {
        return technologyOptionService.updateExistingRelationAttribute(idTechnologyOption, idTechnologyOptionAddressedRequirement, newValues, HasAddressedRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idTechnologyOptionAddressedRequirement}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idTechnologyOption, @PathVariable Long idTechnologyOptionAddressedRequirement) {
        technologyOptionService.deleteRelationAttribute(idTechnologyOption, idTechnologyOptionAddressedRequirement, HasAddressedRequirement.class);
    }
}
