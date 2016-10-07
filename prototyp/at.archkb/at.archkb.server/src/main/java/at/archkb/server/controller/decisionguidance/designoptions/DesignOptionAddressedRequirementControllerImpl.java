package at.archkb.server.controller.decisionguidance.designoptions;

import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.archkb.server.neo4jentity.node.Requirement;
import at.archkb.server.neo4jentity.relationship.decisionguidance.designoption.HasAddressedRequirement;
import at.archkb.server.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 07.09.16.
 */
@RestController
@RequestMapping("api/do/{idDesginOption}/addressedrequirements")
@ResponseBody
public class DesignOptionAddressedRequirementControllerImpl implements DesignOptionRelationController {

    @Autowired
    private DesignOptionService designOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionRelationDto create(@PathVariable Long idDesginOption, @RequestBody DesignOptionRelationDto requirement) {
        return designOptionService.createRelation(idDesginOption, requirement, HasAddressedRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionRequirement}",method = RequestMethod.PATCH)
    public DesignOptionRelationDto updateAttributes(@PathVariable Long idDesginOption, @PathVariable Long idDesginOptionRequirement, @RequestBody DesignOptionRelationDto newValues) {
        return designOptionService.updateExistingRelationAttribute(idDesginOption, idDesginOptionRequirement, newValues, HasAddressedRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDesginOptionRequirement}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesginOption, @PathVariable Long idDesginOptionRequirement) {
        designOptionService.deleteRelationAttribute(idDesginOption, idDesginOptionRequirement, HasAddressedRequirement.class);
    }
}
