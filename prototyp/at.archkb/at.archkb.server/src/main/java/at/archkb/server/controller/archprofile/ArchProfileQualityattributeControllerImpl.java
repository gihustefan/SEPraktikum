package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.QualityAttribute;
import at.archkb.server.neo4jentity.relationship.HasQualityAttribute;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ap/{idArchProfile}/qualityattributes")
@ResponseBody
public class ArchProfileQualityattributeControllerImpl implements ArchProfileRelationController {

    @Autowired
    private ArchProfileService archProfileService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileRelationDto create(@PathVariable Long idArchProfile,
                                         @RequestBody ArchProfileRelationDto qualityAttribute) {
        return archProfileService.createRelation(idArchProfile, qualityAttribute, HasQualityAttribute.class, QualityAttribute.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileQualityattribute}", method = RequestMethod.PATCH)
    public ArchProfileRelationDto updateAttributes(@PathVariable Long idArchProfile,
                                                   @PathVariable Long idArchProfileQualityattribute, @RequestBody ArchProfileRelationDto newValues) {

        return archProfileService.updateExistingRelationAttribute(idArchProfile,
                idArchProfileQualityattribute, newValues, HasQualityAttribute.class);
    }

    @Override
    @RequestMapping(value = "/{idArchProfileQualityAttribute}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long idArchProfile, @PathVariable Long idArchProfileQualityAttribute) {
        archProfileService.deleteRelationAttribute(idArchProfile, idArchProfileQualityAttribute,
                HasQualityAttribute.class);
    }
}