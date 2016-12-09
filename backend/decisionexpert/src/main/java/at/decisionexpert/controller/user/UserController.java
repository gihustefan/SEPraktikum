package at.decisionexpert.controller.user;

import at.decisionexpert.controller.decisionguidance.DecisionGuidanceModelController;
import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelPageableDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.group.GroupPageableDto;
import at.decisionexpert.neo4jentity.dto.user.UserCreationDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;

public interface UserController {
	
	Iterable<UserDto> getAllUsers();

	UserDto getUserById(Long userId);

	UserDto createUser(UserCreationDto user);

	DecisionGuidanceModelPageableDto getDecisionGuidanceModelsOfUser(Long idUser, Integer page, Integer size, DecisionGuidanceModelController.DecisionGuidanceModelType ordering, DecisionGuidanceModelController.ModelState modelState);

	DecisionDocumentationModelPageableDto getDecisionDocumentationModelsOfUser(Long idUser, Integer page, Integer size);

	GroupPageableDto getGroupsOfUser(Long idUser, Integer page, Integer size, GroupType type);

	enum GroupType {
		MEMBER, CREATOR
	}
}
