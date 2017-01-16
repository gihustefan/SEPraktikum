package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasAffectedGuidanceModels;
import at.decisionexpert.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
@RestController
@RequestMapping("api/do/{idDesignOption}/affectedguidancemodels")
@ResponseBody
public class DesignOptionAffectedGuidanceModelControllerImpl implements DesignOptionRelationController {

    @Autowired
    private DesignOptionService designOptionService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionRelationDto create(@PathVariable Long idDesignOption, @RequestBody DesignOptionRelationDto decisionGuidanceModel) {
        return designOptionService.createRelation(idDesignOption, decisionGuidanceModel, HasAffectedGuidanceModels.class, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idAffectedGuidanceModelRelation}",method = RequestMethod.PATCH)
    public DesignOptionRelationDto updateAttributes(@PathVariable Long idDesignOption, @PathVariable Long idAffectedGuidanceModelRelation, @RequestBody DesignOptionRelationDto newValues) {
        return designOptionService.updateExistingRelationAttribute(idDesignOption, idAffectedGuidanceModelRelation, newValues, HasAffectedGuidanceModels.class, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idAffectedGuidanceModelRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesignOption, @PathVariable Long idAffectedGuidanceModelRelation) {
        designOptionService.deleteRelationAttribute(idDesignOption, idAffectedGuidanceModelRelation, HasAffectedGuidanceModels.class);
    }
}
