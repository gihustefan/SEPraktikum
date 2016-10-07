package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.archkb.server.neo4jentity.node.Component;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.HasRequiredComponent;
import at.archkb.server.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/requiredcomponents")
@ResponseBody
public class DecisionDocumentationRequiredComponentsControllerImpl implements DecisionDocumentationRelationController {

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelRelationDto requiredComponent) {
        return decisionDocumentationService.createRelation(idDecisionDocumentationModel, requiredComponent, HasRequiredComponent.class, Component.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationRequiredComponent}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationRequiredComponent, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return decisionDocumentationService.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationRequiredComponent, newValues, HasRequiredComponent.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationRequiredComponent}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationRequiredComponent) {
        decisionDocumentationService.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationRequiredComponent, HasRequiredComponent.class);
    }
}
