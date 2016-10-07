package at.archkb.server.controller.coredata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.TradeoffItem;
import at.archkb.server.service.coredata.CoreDataService;

@RestController
@RequestMapping("api/tradeoffitems")
@ResponseBody
public class TradeoffItemControllerImpl implements CoreDataController {
	
	@Autowired
	private CoreDataService coreDataService;
	
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public List<ArchProfileRelationDto> getCoreData(@RequestParam String titlePartial) {
		return coreDataService.getCoreData(titlePartial, TradeoffItem.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	ArchProfileRelationDto createTradeoffItem(@RequestBody ArchProfileRelationDto tradeoffItem) {
        return new ArchProfileRelationDto(coreDataService.createArchProfileAttribute(tradeoffItem, TradeoffItem.class));
    }



}
