package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
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
    public DecisionGuidanceModelRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody DecisionGuidanceModelRelationDto designOption) {
        return decisionGuidanceModelService.createRelation(idDecisionGuidanceModel, designOption, HasDesignOption.class, DesignOption.class);
    }

    @Override
    @RequestMapping(value = "/{idDesignOptionRelation}",method = RequestMethod.PATCH)
    public DecisionGuidanceModelRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDesignOptionRelation, @RequestBody DecisionGuidanceModelRelationDto newValues) {
        return decisionGuidanceModelService.updateExistingRelationAttribute(idDecisionGuidanceModel, idDesignOptionRelation, newValues, HasDesignOption.class);
    }

    @Override
    @RequestMapping(value = "/{idDesignOptionRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idDesignOptionRelation) {
        decisionGuidanceModelService.deleteRelationAttribute(idDecisionGuidanceModel, idDesignOptionRelation, HasDesignOption.class);
    }
}
