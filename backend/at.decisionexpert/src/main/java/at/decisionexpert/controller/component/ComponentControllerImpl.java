package at.decisionexpert.controller.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentDto;
import at.decisionexpert.service.component.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
@RestController
@RequestMapping("api/comp")
@ResponseBody
public class ComponentControllerImpl implements ComponentController {

    @Autowired
    ComponentService componentService;

    @Override
    @RequestMapping(value = "/{idComponent}", method = RequestMethod.GET)
    public ComponentDto getComponent(@PathVariable Long idComponent) {
        return componentService.getComponent(idComponent);
    }
}
