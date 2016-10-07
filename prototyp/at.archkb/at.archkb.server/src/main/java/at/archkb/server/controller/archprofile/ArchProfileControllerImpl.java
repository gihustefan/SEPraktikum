package at.archkb.server.controller.archprofile;

import at.archkb.server.exception.ArchProfileNotFoundException;
import at.archkb.server.exception.ArchProfileNotPermittedException;
import at.archkb.server.neo4jentity.dto.ArchProfileChangeRequestDto;
import at.archkb.server.neo4jentity.dto.ArchProfileDto;
import at.archkb.server.neo4jentity.dto.ArchProfilePageableDto;
import at.archkb.server.service.archprofile.ArchProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ap")
@ResponseBody
public class ArchProfileControllerImpl implements ArchProfileController {

    @Autowired
    private ArchProfileService archProfileService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ArchProfileDto createArchProfile(@RequestBody(required = false) ArchProfileChangeRequestDto archProfile) {
        return archProfileService.createArchProfile(archProfile);
    }

    @Override
    @RequestMapping(value = "/{idArchProfile}/related", method = RequestMethod.GET)
    public List<ArchProfileDto> getRelatedArchProfiles(@PathVariable Long idArchProfile, @RequestParam Integer count) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public ArchProfilePageableDto getArchProfiles(@RequestParam Integer page, @RequestParam Integer size,
                                                  @RequestParam(required = false) ArchProfilesType type) {
        return archProfileService.getNewestArchProfiles(page, size);
    }

    @Override
    @RequestMapping(value = "/{idArchProfile}", method = RequestMethod.GET)
    public ArchProfileDto getArchProfile(@PathVariable Long idArchProfile) {
        return archProfileService.getArchProfile(idArchProfile);
    }

    @Override
    @RequestMapping(value = "/{idArchProfile}", method = RequestMethod.DELETE)
    public void deleteArchProfile(@PathVariable Long idArchProfile) {
        archProfileService.deleteArchProfile(idArchProfile);
    }

    @Override
    @RequestMapping(value = "/{idArchProfile}", method = RequestMethod.PATCH)
    public ArchProfileDto updateArchProfileAttribute(@PathVariable Long idArchProfile, @RequestBody ArchProfileChangeRequestDto newValues) {
        return archProfileService.updateArchProfileProperties(idArchProfile, newValues);
    }


    /**
     * Handling ArchProfile not found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ArchProfileNotFoundException.class)
    public void handleArchProfileNotFoundException() {
    }

    /**
     * Handling ArchProfile not Permitted to Read
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ArchProfileNotPermittedException.class)
    public void handleArchProfileNotPermittedException() {
    }

}
