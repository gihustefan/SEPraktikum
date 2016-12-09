package at.decisionexpert.controller.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelTradeoffRelationDto;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.

@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/tradeoffs")
@ResponseBody */
public class DecisionDocumentationTradeoffRelationControllerImpl implements DecisionDocumentationTradeoffRelationController {


    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelTradeoffRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelTradeoffRelationDto tradeoff) {
        return decisionDocumentationService.createTradeoffRelation(idDecisionDocumentationModel, tradeoff);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationTradeoff}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelTradeoffRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationTradeoff, @RequestBody DecisionDocumentationModelTradeoffRelationDto newValues) {
        return decisionDocumentationService.updateExistingTradeoffRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationTradeoff, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationTradeoff}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationTradeoff) {
        decisionDocumentationService.deleteDecisionDocumentationTradeoffRelation(idDecisionDocumentationModel, idDecisionDocumentationTradeoff);
    }
}
