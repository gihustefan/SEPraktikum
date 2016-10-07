package at.archkb.server.controller.decisionguidance.designoptions;

import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionEffectedGuidanceModelsDto;
import at.archkb.server.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
@RestController
@RequestMapping("api/do/{idDesignOption}/effectedguidancemodels")
@ResponseBody
public class DesignOptionEffectedGuidanceModelControllerImpl implements DesignOptionEffectedGuidanceModelController {

    @Autowired
    private DesignOptionService designOptionService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DesignOptionEffectedGuidanceModelsDto create(@PathVariable Long idDesignOption, @RequestBody DesignOptionEffectedGuidanceModelsDto effectedGuidanceModel) {
        return designOptionService.createGuidanceModelRelation(idDesignOption, effectedGuidanceModel);
    }

    @Override
    @RequestMapping(value = "/{idDesignOptionRelatedGuidanceModel}",method = RequestMethod.PATCH)
    public DesignOptionEffectedGuidanceModelsDto updateAttributes(@PathVariable Long idDesignOption, @PathVariable Long idDesignOptionRelatedGuidanceModel, @RequestBody DesignOptionEffectedGuidanceModelsDto newValues) {
        return designOptionService.updateExistingGuidanceModelRelationAttribute(idDesignOption, idDesignOptionRelatedGuidanceModel, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDesignOptionRelatedGuidanceModel}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idDesignOption, @PathVariable Long idDesignOptionRelatedGuidanceModel) {
        designOptionService.deleteGuidanceModelRelationAttribute(idDesignOption, idDesignOptionRelatedGuidanceModel);
    }
}
