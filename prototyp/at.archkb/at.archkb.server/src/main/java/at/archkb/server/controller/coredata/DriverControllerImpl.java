package at.archkb.server.controller.coredata;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.Driver;
import at.archkb.server.service.coredata.CoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rainer on 24.05.2016.
 */
@RestController
@RequestMapping("api/drivers")
@ResponseBody
public class DriverControllerImpl implements CoreDataController {

    @Autowired
    private CoreDataService coreDataService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<ArchProfileRelationDto> getCoreData(@RequestParam String titlePartial) {
        return coreDataService.getCoreData(titlePartial, Driver.class);

    }
}
