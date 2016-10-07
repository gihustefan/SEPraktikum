package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.dto.ArchProfileTradeoffRelationDto;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ap/{idArchProfile}/tradeoffs")
@ResponseBody
public class ArchProfileTradeoffControllerImpl implements ArchProfileTradeoffRelationController {

	@Autowired
	private ArchProfileService archProfileService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileTradeoffRelationDto create(@PathVariable Long idArchProfile, @RequestBody ArchProfileTradeoffRelationDto tradeoff) {
        return archProfileService.createTradeoffRelation(idArchProfile, tradeoff);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileTradeoff}", method = RequestMethod.PATCH)
    public ArchProfileTradeoffRelationDto updateAttributes(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileTradeoff, @RequestBody ArchProfileTradeoffRelationDto newValues) {
        return archProfileService.updateExistingTradeoffRelationAttribute(idArchProfile, idArchProfileTradeoff, newValues);
    }

    @Override
	@RequestMapping(value = "/{idArchProfileTradeoff}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileTradeoff) {
		archProfileService.deleteArchProfileTradeoffRelation(idArchProfile, idArchProfileTradeoff);
	}

}
