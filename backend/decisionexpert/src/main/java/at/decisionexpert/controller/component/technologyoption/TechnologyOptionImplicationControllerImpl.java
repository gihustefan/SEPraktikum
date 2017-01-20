package at.decisionexpert.controller.component.technologyoption;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;
import at.decisionexpert.neo4jentity.node.Implication;
import at.decisionexpert.neo4jentity.relationship.component.technologyoption.TOHasImplication;
import at.decisionexpert.service.component.technologyoption.TechnologyOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/to/{idTechnologyOption}/implications")
@ResponseBody
public class TechnologyOptionImplicationControllerImpl implements TechnologyOptionRelationController {

    @Autowired
    TechnologyOptionService technologyOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public TechnologyOptionRelationDto create(@PathVariable Long idTechnologyOption, @RequestBody TechnologyOptionRelationDto implication) {
        return technologyOptionService.createRelation(idTechnologyOption, implication, TOHasImplication.class, Implication.class);
    }

    @Override
    @RequestMapping(value = "/{idTechnologyOptionImplication}",method = RequestMethod.PATCH)
    public TechnologyOptionRelationDto updateAttributes(@PathVariable Long idTechnologyOption, @PathVariable Long idTechnologyOptionImplication, @RequestBody TechnologyOptionRelationDto newValues) {
        return technologyOptionService.updateExistingRelationAttribute(idTechnologyOption, idTechnologyOptionImplication, newValues, TOHasImplication.class);
    }

    @Override
    @RequestMapping(value = "/{idTechnologyOptionImplication}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idTechnologyOption, @PathVariable Long idTechnologyOptionImplication) {
        technologyOptionService.deleteRelationAttribute(idTechnologyOption, idTechnologyOptionImplication, TOHasImplication.class);
    }
}
