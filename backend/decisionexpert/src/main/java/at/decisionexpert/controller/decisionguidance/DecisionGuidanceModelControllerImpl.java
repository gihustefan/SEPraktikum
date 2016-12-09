package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
@RestController
@RequestMapping("api/dgm")
@ResponseBody
public class DecisionGuidanceModelControllerImpl implements DecisionGuidanceModelController {

    @Autowired
    DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelDto createDecisionGuidanceModel(@RequestBody(required = false) DecisionGuidanceModelChangeRequestDto decisionGuidanceModel) {
        return decisionGuidanceModelService.createDecisionGuidanceModel(decisionGuidanceModel);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModel}", method = RequestMethod.GET)
    public DecisionGuidanceModelDto getDecisionGuidanceModel(@PathVariable Long idDecisionGuidanceModel) {
        return decisionGuidanceModelService.getDecisionGuidanceModel(idDecisionGuidanceModel);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModel}", method = RequestMethod.PATCH)
    public DecisionGuidanceModelDto updateDecisionGuidanceModelAttribute(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelChangeRequestDto newValues) {
        return decisionGuidanceModelService.updateDecisionGuidanceModelProperties(idDecisionGuidanceModel, newValues);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModels(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) DecisionGuidanceModelType type) {
        return decisionGuidanceModelService.getDecisionGuidanceModels(page, size, type);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModel}", method = RequestMethod.DELETE)
    public void deleteDecisionGuidanceModel(@PathVariable Long idDecisionGuidanceModel) {
        decisionGuidanceModelService.deleteDecisionGuidanceModel(idDecisionGuidanceModel);
    }
}