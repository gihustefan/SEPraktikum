package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.DesignDecision;
import at.archkb.server.neo4jentity.relationship.HasDesignDecision;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ap/{idArchProfile}/designdecisions")
@ResponseBody
public class ArchProfileDesignDecisionControllerImpl implements ArchProfileRelationController {

    @Autowired
    private ArchProfileService archProfileService;


    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileRelationDto create(@PathVariable Long idArchProfile, @RequestBody ArchProfileRelationDto value) {
        return archProfileService.createRelation(idArchProfile, value, HasDesignDecision.class, DesignDecision.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileDesignDecision}", method = RequestMethod.PATCH)
    public ArchProfileRelationDto updateAttributes(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileDesignDecision,
                                                   @RequestBody ArchProfileRelationDto newValues) {

        return archProfileService.updateExistingRelationAttribute(idArchProfile,
                idArchProfileDesignDecision, newValues, HasDesignDecision.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileDesignDecision}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileDesignDecision) {
        archProfileService.deleteRelationAttribute(idArchProfile, idArchProfileDesignDecision, HasDesignDecision.class);
    }

}
