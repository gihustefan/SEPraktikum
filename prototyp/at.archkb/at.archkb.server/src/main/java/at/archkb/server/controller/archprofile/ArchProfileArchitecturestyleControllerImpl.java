package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.Architecturestyle;
import at.archkb.server.neo4jentity.relationship.HasArchitectureStyle;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ap/{idArchProfile}/architecturestyles")
@ResponseBody
public class ArchProfileArchitecturestyleControllerImpl
        implements ArchProfileRelationController {

    @Autowired
    private ArchProfileService archProfileService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileRelationDto create(@PathVariable Long idArchProfile, @RequestBody ArchProfileRelationDto value) {
        return archProfileService.createRelation(idArchProfile, value, HasArchitectureStyle.class, Architecturestyle.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileArchitecturestyle}", method = RequestMethod.PATCH)
    public ArchProfileRelationDto updateAttributes(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileArchitecturestyle,
                                                   @RequestBody ArchProfileRelationDto newValues) {
        return archProfileService.updateExistingRelationAttribute(idArchProfile,
                idArchProfileArchitecturestyle, newValues, HasArchitectureStyle.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileArchitecturestyle}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileArchitecturestyle) {
        archProfileService.deleteRelationAttribute(idArchProfile, idArchProfileArchitecturestyle, HasArchitectureStyle.class);
    }
}