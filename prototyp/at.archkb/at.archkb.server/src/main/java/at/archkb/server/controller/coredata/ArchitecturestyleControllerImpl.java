package at.archkb.server.controller.coredata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.Architecturestyle;
import at.archkb.server.service.coredata.CoreDataService;

@RestController
@RequestMapping("api/architecturestyles")
@ResponseBody
public class ArchitecturestyleControllerImpl implements CoreDataController {
	
	@Autowired
	private CoreDataService coreDataService;
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ArchProfileRelationDto> getCoreData(@RequestParam String titlePartial) {
		return coreDataService.getCoreData(titlePartial, Architecturestyle.class);
	}

}
