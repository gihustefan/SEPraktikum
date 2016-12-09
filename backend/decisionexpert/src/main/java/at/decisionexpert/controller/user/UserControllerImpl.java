package at.decisionexpert.controller.user;

import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelPageableDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.user.UserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;
import at.decisionexpert.service.decisiondocumentation.DecisionDocumentationService;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import at.decisionexpert.service.group.GroupService;
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

    @Autowired
    private DecisionDocumentationService decisionDocumentationService;

    @Autowired
    private GroupService groupService;

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
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserCreationDto user) {
        return userService.createUser(user);
    }

    @Override
    @RequestMapping(value = "/{idUser}/decisionguidancemodels", method = RequestMethod.GET)
    public DecisionGuidanceModelPageableDto getDecisionGuidanceModelsOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) DecisionGuidanceModelController.DecisionGuidanceModelType ordering, @RequestParam(required = false)DecisionGuidanceModelController.ModelState modelState) {
        return decisionGuidanceModelService.getUserDecisionGuidanceModel(idUser, page, size, ordering, modelState);
    }

    @Override
    @RequestMapping(value = "/{idUser}/decisiondocumentationmodels", method = RequestMethod.GET)
    public DecisionDocumentationModelPageableDto getDecisionDocumentationModelsOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size) {
        return decisionDocumentationService.getUserDecisionDocumentationModel(idUser, page, size);
    }

    @Override
    @RequestMapping(value = "/{idUser}/groups", method = RequestMethod.GET)
    public GroupPageableDto getGroupsOfUser(@PathVariable Long idUser, @RequestParam Integer page, @RequestParam Integer size, @RequestParam GroupType type) {
        return groupService.getGroupsOfUser(idUser, page, size, type);
    }
}
