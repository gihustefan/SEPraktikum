package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.Driver;
import at.archkb.server.neo4jentity.relationship.HasDriver;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ap/{idArchProfile}/drivers")
@ResponseBody
public class ArchProfileDriverControllerImpl implements ArchProfileRelationController {

    @Autowired
    private ArchProfileService archProfileService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileRelationDto create(@PathVariable Long idArchProfile, @RequestBody ArchProfileRelationDto driver) {
        return archProfileService.createRelation(idArchProfile, driver, HasDriver.class, Driver.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileDriver}", method = RequestMethod.PATCH)
    public ArchProfileRelationDto updateAttributes(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileDriver,
                                                   @RequestBody ArchProfileRelationDto newValues) {
        return archProfileService.updateExistingRelationAttribute(idArchProfile,
                idArchProfileDriver, newValues, HasDriver.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileDriver}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileDriver) {
        archProfileService.deleteRelationAttribute(idArchProfile, idArchProfileDriver, HasDriver.class);
    }
}
