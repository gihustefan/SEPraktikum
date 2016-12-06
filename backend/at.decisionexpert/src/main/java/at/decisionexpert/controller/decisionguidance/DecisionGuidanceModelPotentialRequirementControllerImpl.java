package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.Requirement;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasPotentialRequirement;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanhaselboeck on 17.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/potentialrequirements")
@ResponseBody
public class DecisionGuidanceModelPotentialRequirementControllerImpl implements DecisionGuidanceModelRelationController{

    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DecisionGuidanceModelRelationDto> getPotentialRequirements(@PathVariable Long idDecisionGuidanceModel) {
        return decisionGuidanceModelService.getPotentialRequirements(idDecisionGuidanceModel);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRelationDto requirement) {
        return decisionGuidanceModelService.createRelation(idDecisionGuidanceModel, requirement, HasPotentialRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRequirementRelationID}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRequirementRelationID, @RequestBody DecisionGuidanceModelRelationDto newValues) {
        return decisionGuidanceModelService.updateExistingRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRequirementRelationID, newValues, HasPotentialRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRequirementRelationID}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRequirementRelationID) {
        decisionGuidanceModelService.deleteRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRequirementRelationID, HasPotentialRequirement.class);
    }
}
