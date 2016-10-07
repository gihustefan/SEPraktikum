package at.archkb.server.controller.coredata;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.DesignDecision;
import at.archkb.server.service.coredata.CoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rainer on 24.05.2016.
 */
@RestController
@RequestMapping("api/designdecisions")
@ResponseBody
public class DesignDecisionControllerImpl implements CoreDataController {

    @Autowired
    private CoreDataService coreDataService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<ArchProfileRelationDto> getCoreData(String titlePartial) {
        return coreDataService.getCoreData(titlePartial, DesignDecision.class);
    }
}
