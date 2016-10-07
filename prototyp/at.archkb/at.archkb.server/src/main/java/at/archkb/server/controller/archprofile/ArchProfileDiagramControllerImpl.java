package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.dto.DiagramDto;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/ap/{idArchProfile}/diagrams")
@ResponseBody
public class ArchProfileDiagramControllerImpl implements ArchProfileDiagramController {

    @Autowired
    private ArchProfileService archProfileService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public DiagramDto createDiagram(@PathVariable Long idArchProfile, @RequestParam("file") MultipartFile file) throws IOException {
        return archProfileService.createDiagram(idArchProfile, file);
    }

    @Override
    @RequestMapping(value = "/{idDiagram}", method = RequestMethod.PATCH)
    public DiagramDto updateDiagram(@PathVariable Long idArchProfile, @PathVariable Long idDiagram, @RequestBody DiagramDto newValues) {
        return archProfileService.updateDiagram(idArchProfile, idDiagram, newValues);
    }

    @Override
    @RequestMapping(value = "/{idDiagram}", method = RequestMethod.DELETE)
    public void deleteDiagram(@PathVariable Long idArchProfile, @PathVariable Long idDiagram) {
        archProfileService.deleteDiagram(idArchProfile, idDiagram);
    }
}
