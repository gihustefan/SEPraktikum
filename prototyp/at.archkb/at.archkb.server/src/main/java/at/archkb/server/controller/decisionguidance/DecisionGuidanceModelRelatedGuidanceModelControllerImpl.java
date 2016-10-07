package at.archkb.server.controller.decisionguidance;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/relatedguidancemodels")
@ResponseBody
public class DecisionGuidanceModelRelatedGuidanceModelControllerImpl implements DecisionGuidanceModelRelatedGuidanceModelController {

    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelRelatedGuidanceModelsDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRelatedGuidanceModelsDto relatedguidanceModel) {
        return decisionGuidanceModelService.createGuidanceModelRelation(idDecisionGuidanceModel, relatedguidanceModel);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRelatedGuidanceModel}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRelatedGuidanceModelsDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRelatedGuidanceModel, @RequestBody DecisionGuidanceModelRelatedGuidanceModelsDto newValues) {
        return decisionGuidanceModelService.updateExistingGuidanceModelRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelatedGuidanceModel, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelRelatedGuidanceModel}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelRelatedGuidanceModel) {
        decisionGuidanceModelService.deleteGuidanceModelRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelRelatedGuidanceModel);
    }
}
