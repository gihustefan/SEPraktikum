package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/designoptions")
@ResponseBody
public class DecisionGuidanceModelDesignOptionControllerImpl implements DecisionGuidanceModelDesignOptionController {
    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelDesignOptionRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelDesignOptionRelationDto designOption) {
        return decisionGuidanceModelService.createDesignOptionRelation(idDecisionGuidanceModel, designOption);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelDesignOption}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelDesignOptionRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelDesignOption, @RequestBody DecisionGuidanceModelDesignOptionRelationDto newValues) {
        return decisionGuidanceModelService.updateExistingDesignOptionRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelDesignOption, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelDesignOption}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelDesignOption) {
        decisionGuidanceModelService.deleteDesignOptionRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelDesignOption);
    }
}
