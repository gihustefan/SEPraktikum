package at.archkb.server.controller.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.archkb.server.neo4jentity.node.Requirement;
import at.archkb.server.neo4jentity.relationship.decisionguidance.HasPotentialRequirement;
import at.archkb.server.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/potentialrequirements")
@ResponseBody
public class DecisionGuidanceModelPotentialRequirementControllerImpl implements DecisionGuidanceModelRelationController{

    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelRealtionDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRealtionDto requirement) {
        return decisionGuidanceModelService.createRelation(idDecisionGuidanceModel, requirement, HasPotentialRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRequirement}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRealtionDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRequirement, @RequestBody DecisionGuidanceModelRealtionDto newValues) {
        return decisionGuidanceModelService.updateExistingRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRequirement, newValues, HasPotentialRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRequirement}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRequirement) {
        decisionGuidanceModelService.deleteRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRequirement, HasPotentialRequirement.class);
    }
}
