package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.decisionexpert.neo4jentity.node.DesignOption;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasDesignOption;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/designoptions")
@ResponseBody
public class DecisionGuidanceModelDesignOptionControllerImpl implements DecisionGuidanceModelRelationController {
    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionGuidanceModelRealtionDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRealtionDto designOption) {
        return decisionGuidanceModelService.createRelation(idDecisionGuidanceModel, designOption, HasDesignOption.class, DesignOption.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelDesignOption}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRealtionDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelDesignOption, @RequestBody DecisionGuidanceModelRealtionDto newValues) {
        return decisionGuidanceModelService.updateExistingRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelDesignOption, newValues, HasDesignOption.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionGuidanceModelDesignOption}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDecisionGuidanceModelDesignOption) {
        decisionGuidanceModelService.deleteRelationAttribute(idDecisionGuidanceModel, idDecisionGuidanceModelDesignOption, HasDesignOption.class);
    }
}
