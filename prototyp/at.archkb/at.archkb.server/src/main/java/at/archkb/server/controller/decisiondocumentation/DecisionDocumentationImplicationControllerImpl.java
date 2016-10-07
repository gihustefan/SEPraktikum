package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.archkb.server.neo4jentity.node.Implication;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.HasImplication;
import at.archkb.server.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/implications")
@ResponseBody
public class DecisionDocumentationImplicationControllerImpl implements DecisionDocumentationRelationController {

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelRelationDto implication) {
        return decisionDocumentationService.createRelation(idDecisionDocumentationModel, implication, HasImplication.class, Implication.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationImplication}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationImplication, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return decisionDocumentationService.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationImplication, newValues, HasImplication.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationImplication}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationImplication) {
        decisionDocumentationService.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationImplication, HasImplication.class);
    }
}
