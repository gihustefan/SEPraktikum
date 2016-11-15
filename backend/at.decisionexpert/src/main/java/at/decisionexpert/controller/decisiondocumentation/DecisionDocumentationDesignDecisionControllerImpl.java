package at.decisionexpert.controller.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.decisionexpert.neo4jentity.node.DesignDecision;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasAddressedRequirement;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasDesignDecision;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/designdecisions")
@ResponseBody
public class DecisionDocumentationDesignDecisionControllerImpl implements DecisionDocumentationRelationController {

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelRelationDto decisionDocumentation) {
        return null; //decisionDocumentationService.createDesignDecisionRelation(idDecisionDocumentationModel, decisionDocumentation);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationDesignDecision}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationDesignDecision, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return null; //decisionDocumentationService.updateExistingDesignDecisionRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationDesignDecision, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationDesignDecision}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationDesignDecision) {
        //decisionDocumentationService.deleteDesignDecisionRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationDesignDecision);
    }
}
