package at.decisionexpert.controller.belonging;

import at.decisionexpert.neo4jentity.dto.belonging.BelongingRelationDto;
import at.decisionexpert.neo4jentity.node.DecisionGuidanceModel;
import at.decisionexpert.service.belonging.BelongingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
@RestController
@RequestMapping("api/dgm/{idDecisionGuidanceModel}/belonging")
@ResponseBody
public class DecisionGuidanceModelBelongingRelationControllerImpl implements BelongingRelationController {

    @Autowired
    private BelongingService belongingService;

    @Override
    @RequestMapping(value = "/{idGroup}", method = RequestMethod.POST)
    public BelongingRelationDto create(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idGroup) {
        return belongingService.createBelongingRelation(idDecisionGuidanceModel, idGroup, DecisionGuidanceModel.class);
    }

    @Override
    @RequestMapping(value = "/{idBelongingRelation}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idBelongingRelation) {
        belongingService.deleteBelongingRelation(idDecisionGuidanceModel, idBelongingRelation);
    }
}
