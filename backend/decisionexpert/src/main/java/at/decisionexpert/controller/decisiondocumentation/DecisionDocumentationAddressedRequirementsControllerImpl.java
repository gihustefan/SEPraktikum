package at.decisionexpert.controller.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.decisionexpert.neo4jentity.node.Requirement;
import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.DDMHasAddressedRequirement;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by stefanhaselboeck on 06.10.16.

@RestController
@RequestMapping("api/ddm/{idDecisionDocumentationModel}/addressedrequirements")
@ResponseBody
 */
public class DecisionDocumentationAddressedRequirementsControllerImpl implements DecisionDocumentationRelationController {

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelRelationDto create(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelRelationDto addressedRequirement) {
        return decisionDocumentationService.createRelation(idDecisionDocumentationModel, addressedRequirement, DDMHasAddressedRequirement.class, Requirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationAddressedRequirement}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationAddressedRequirement, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return decisionDocumentationService.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationAddressedRequirement, newValues, DDMHasAddressedRequirement.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationAddressedRequirement}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationAddressedRequirement) {
        decisionDocumentationService.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationAddressedRequirement, DDMHasAddressedRequirement.class);
    }
}
