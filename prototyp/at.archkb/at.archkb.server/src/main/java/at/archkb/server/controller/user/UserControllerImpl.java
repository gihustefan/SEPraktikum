package at.archkb.server.controller.user;

import at.archkb.server.neo4jentity.dto.ArchProfilePageableDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.archkb.server.service.archprofile.ArchProfileService;
import at.archkb.server.service.decisionguidancemodel.DecisionGuidanceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import at.archkb.server.neo4jentity.dto.user.UserDto;
import at.archkb.server.service.user.UserService;

@RestController
@RequestMapping("api/users")
@ResponseBody
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArchProfileService archProfileService;

    @Autowired
    private DecisionGuidanceModelService decisionGuidanceModelService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserDto getUserById(@PathVariable Long userId) {
        return new UserDto(userService.getUserById(userId));
    }

    @Override
    @RequestMapping(value = "/{idUser}/archprofiles", method = RequestMethod.GET)
    public ArchProfilePageableDto getArchProfilesOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size) {
        return archProfileService.getUserArchProfiles(idUser, page, size);
    }

    @Override
    @RequestMapping(value = "/{idUser}/decisionguidancemodels", method = RequestMethod.GET)
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModelsOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size) {
        return decisionGuidanceModelService.getUserDecisionGuidanceModel(idUser, page, size);
    }

}
