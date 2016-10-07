package at.decisionexpert.controller.user;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import at.decisionexpert.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@ResponseBody
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

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
    @RequestMapping(value = "/{idUser}/decisionguidancemodels", method = RequestMethod.GET)
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModelsOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size) {
        return decisionGuidanceModelService.getUserDecisionGuidanceModel(idUser, page, size);
    }

}
