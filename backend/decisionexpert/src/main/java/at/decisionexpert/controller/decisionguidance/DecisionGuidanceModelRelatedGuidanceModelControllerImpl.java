package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/relatedguidancemodels")
@ResponseBody
public class DecisionGuidanceModelRelatedGuidanceModelControllerImpl implements DecisionGuidanceModelRelationController {

    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRelationDto decisionGuidanceModel) {
        return decisionGuidanceModelService.createRelation(idDecisionGuidanceModel, decisionGuidanceModel, HasRelatedGuidanceModels.class, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idRelatedGuidanceModelRelation}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idRelatedGuidanceModelRelation, @RequestBody DecisionGuidanceModelRelationDto newValues) {
        return decisionGuidanceModelService.updateExistingRelationAttribute(idDecisionGuidanceModel, idRelatedGuidanceModelRelation, newValues, HasRelatedGuidanceModels.class);
    }

    @Override
    @RequestMapping(value = "/{idRelatedGuidanceModelRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idRelatedGuidanceModelRelation) {
        decisionGuidanceModelService.deleteRelationAttribute(idDecisionGuidanceModel, idRelatedGuidanceModelRelation, HasRelatedGuidanceModels.class);
    }
}
