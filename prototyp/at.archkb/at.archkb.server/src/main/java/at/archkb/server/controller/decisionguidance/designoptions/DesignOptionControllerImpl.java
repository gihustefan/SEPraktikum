package at.archkb.server.controller.decisionguidance.designoptions;

import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionDto;
import at.archkb.server.service.decisionguidancemodel.designoption.DesignOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 06.09.16.
 */
@RestController
@RequestMapping("api/do")
@ResponseBody
public class DesignOptionControllerImpl implements DesignOptionController{

    @Autowired
    DesignOptionService designOptionService;

    @Override
    @RequestMapping(value = "/{idDesignOption}", method = RequestMethod.GET)
    public DesignOptionDto getDesignOption(@PathVariable Long idDesignOption) {
        return designOptionService.getDesignOption(idDesignOption);
    }
}
