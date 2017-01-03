package at.decisionexpert.controller.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelPageableDto;

/**
 * Created by stefanhaselboeck on 12.08.16.
 */
public interface DecisionGuidanceModelController {

    /**
     * Create a completely new DecisionGuidanceModel
     *
     * @param decisionGuidanceModel The decisionGuidanceModel Model where all the data is stored
     * @return The model persisted decisionGuidanceModel
     */
    DecisionGuidanceModelDto createDecisionGuidanceModel(DecisionGuidanceModelChangeRequestDto decisionGuidanceModel);

    /**
     * Receive a single model of an DecisionGuidanceModel
     *
     * @param idDecisionGuidanceModel The decisionGuidanceModel Id of interest
     * @return The decisionGuidanceModel model of the decisionGuidanceModel - selected by
     * idDecisionGuidanceModel
     */
    DecisionGuidanceModelDto getDecisionGuidanceModel(Long idDecisionGuidanceModel);

    /**
     * Updates the direct Attributes of a given DecisionGuidanceModel (e.g. Title,
     * Description)
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel to update
     * @param newValues     new values based on the model
     * @return The new Value for the attribute, after persisting
     */
    DecisionGuidanceModelDto updateDecisionGuidanceModelAttribute(Long idDecisionGuidanceModel, DecisionGuidanceModelChangeRequestDto newValues);

    /**
     * Retrieving paginated DecisionGuidanceModel
     *
     * @param page What page
     * @param size How many items in page
     * @param type What type (e.g. newest, featured, active, ...)
     * @return A List of DecisionGuidanceModelDtos restricted on the given parameters
     */
    DecisionGuidanceModelPageableDto getDecisionGuidanceModels(Integer page, Integer size, DecisionGuidanceModelType type, String searchText, Long groupId);

    /**
     * Deleting an DecisionGuidanceModel
     *
     * @param idDecisionGuidanceModel Which DecisionGuidanceModel
     */
    void deleteDecisionGuidanceModel(Long idDecisionGuidanceModel);

    enum DecisionGuidanceModelType {
        NEWEST, ALPHABET, RATING
    }

    enum ModelState {
        PUBLISH, UNPUBLISHED
    }
}
