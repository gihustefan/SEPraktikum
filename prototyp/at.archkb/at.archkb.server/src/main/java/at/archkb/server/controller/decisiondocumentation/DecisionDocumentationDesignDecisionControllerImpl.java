package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.archkb.server.neo4jentity.node.DesignDecision;
import at.archkb.server.neo4jentity.relationship.decisiondocumentation.HasDesignDecision;
import at.archkb.server.service.decisiondocumentation.DecisionDocumentationService;
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
        return decisionDocumentationService.createRelation(idDecisionDocumentationModel, decisionDocumentation, HasDesignDecision.class, DesignDecision.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationDesignDecision}",method = RequestMethod.PATCH)
    public DecisionDocumentationModelRelationDto updateAttributes(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationDesignDecision, @RequestBody DecisionDocumentationModelRelationDto newValues) {
        return decisionDocumentationService.updateExistingRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationDesignDecision, newValues, HasDesignDecision.class);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationDesignDecision}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDecisionDocumentationModel, @PathVariable Long idDecisionDocumentationDesignDecision) {
        decisionDocumentationService.deleteRelationAttribute(idDecisionDocumentationModel, idDecisionDocumentationDesignDecision, HasDesignDecision.class);
    }
}
