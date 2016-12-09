package at.decisionexpert.controller.decisionguidance.designoptions;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasEffectedGuidanceModels;
import at.decisionexpert.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
@RestController
@RequestMapping("api/do/{idDesignOption}/effectedguidancemodels")
@ResponseBody
public class DesignOptionEffectedGuidanceModelControllerImpl implements DesignOptionRelationController {

    @Autowired
    private DesignOptionService designOptionService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionRelationDto create(@PathVariable Long idDesignOption, @RequestBody DesignOptionRelationDto decisionGuidanceModel) {
        return designOptionService.createRelation(idDesignOption, decisionGuidanceModel, HasEffectedGuidanceModels.class, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idRelatedGuidanceModel}",method = RequestMethod.PATCH)
    public DesignOptionRelationDto updateAttributes(@PathVariable Long idDesignOption, @PathVariable Long idRelatedGuidanceModel, @RequestBody DesignOptionRelationDto newValues) {
        return designOptionService.updateExistingRelationAttribute(idDesignOption, idRelatedGuidanceModel, newValues, HasEffectedGuidanceModels.class);
    }

    @Override
    @RequestMapping(value = "/{idDesignOptionRelatedGuidanceModel}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesignOption, @PathVariable Long idDesignOptionRelatedGuidanceModel) {
        designOptionService.deleteRelationAttribute(idDesignOption, idDesignOptionRelatedGuidanceModel, HasEffectedGuidanceModels.class);
    }
}
