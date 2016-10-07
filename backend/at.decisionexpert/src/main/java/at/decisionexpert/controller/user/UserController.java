package at.decisionexpert.controller.user;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;
import at.decisionexpert.neo4jentity.dto.user.UserDto;

public interface UserController {
	
	Iterable<UserDto> getAllUsers();

	UserDto getUserById(Long userId);

	/**
	 * Fetching paginated DecisionGuidanceModels of a user
	 * @param idUser Which user
	 * @param page Which page
	 * @param size How many items per page
	 * @return A List of DecisionGuidanceModels (based on the user)
	 */
	DecisionGuidanceModelPageableDto getDecisionGuidanceModelsOfUser(Long idUser, Integer page, Integer size);
}
