package at.decisionexpert.controller.decisiondocumentation;


import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelDto;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelPageableDto;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
@RestController
@RequestMapping("api/ddm")
@ResponseBody
public class DecisionDocumentationControllerImpl implements DecisionDocumentationController{

    @Autowired
    DecisionDocumentationService decisionDocumentationService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DecisionDocumentationModelDto createDecisionDocumentationModel(@RequestBody(required = false) DecisionDocumentationModelChangeRequestDto decisionDocumentationModel) {
        return decisionDocumentationService.createDecisionDocumentationModel(decisionDocumentationModel);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationModel}", method = RequestMethod.GET)
    public DecisionDocumentationModelDto getDecisionDocumentationModel(@PathVariable Long idDecisionDocumentationModel) {
        return decisionDocumentationService.getDecisionDocumentationModel(idDecisionDocumentationModel);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationModel}", method = RequestMethod.PATCH)
    public DecisionDocumentationModelDto updateDecisionDocumentationModelAttribute(@PathVariable Long idDecisionDocumentationModel, @RequestBody DecisionDocumentationModelChangeRequestDto newValues) {
        return decisionDocumentationService.updateDecisionDocumentationModelProperties(idDecisionDocumentationModel, newValues);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public DecisionDocumentationModelPageableDto getDecisionDocumentationModels(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) DecisionDocumentationModelType type) {
        return decisionDocumentationService.getNewestDecisionDocumentationModels(page, size);
    }

    @Override
    @RequestMapping(value = "/{idDecisionDocumentationModel}", method = RequestMethod.DELETE)
    public void deleteDecisionDocumentationModel(@PathVariable Long idDecisionDocumentationModel) {
        decisionDocumentationService.deleteDecisionDocumentationModel(idDecisionDocumentationModel);
    }
}
