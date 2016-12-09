package at.decisionexpert.controller.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.decisionexpert.neo4jentity.node.Component;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMHasRequiredComponent;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.

@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/requiredcomponents")
@ResponseBody */
public class DecisionDocumentationRequiredComponentsControllerImpl implements DecisionDocumentationRelationController {

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelRelationDto requiredComponent) {
        return decisionDocumentationService.createRelation(idDecisionDocumentationModel, requiredComponent, DDMHasRequiredComponent.class, Component.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationRequiredComponent}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationRequiredComponent, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return decisionDocumentationService.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationRequiredComponent, newValues, DDMHasRequiredComponent.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationRequiredComponent}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationRequiredComponent) {
        decisionDocumentationService.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationRequiredComponent, DDMHasRequiredComponent.class);
    }
}
