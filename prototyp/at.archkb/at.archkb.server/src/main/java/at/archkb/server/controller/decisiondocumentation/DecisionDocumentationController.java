package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelChangeRequestDto;
import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelDto;
import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelPageableDto;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationController {

    /**
     * Create a completely new DecisionDocumentationModel
     *
     * @param decisionDocumentationModel The decisionDocumentationModel Model where all the data is stored
     * @return The model persisted decisionDocumentationModel
     */
    DecisionDocumentationModelDto createDecisionDocumentationModel(DecisionDocumentationModelChangeRequestDto decisionDocumentationModel);

    /**
     * Receive a single model of an DecisionDocumentationModel
     *
     * @param idDecisionDocumentationModel The decisionDocumentationModel Id of interest
     * @return The decisionDocumentationModel model of the decisionDocumentationModel - selected by
     * idDecisionDocumentationModel
     */
    DecisionDocumentationModelDto getDecisionDocumentationModel(Long idDecisionDocumentationModel);

    /**
     * Updates the direct Attributes of a given DecisionDocumentationModel (e.g. Title,
     * Description)
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel to update
     * @param newValues     new values based on the model
     * @return The new Value for the attribute, after persisting
     */
    DecisionDocumentationModelDto updateDecisionDocumentationModelAttribute(Long idDecisionDocumentationModel, DecisionDocumentationModelChangeRequestDto newValues);

    /**
     * Retrieving paginated DecisionDocumentationModel
     *
     * @param page What page
     * @param size How many items in page
     * @param type What type (e.g. newest, featured, active, ...)
     * @return A List of DecisionDocumentationModelDtos restricted on the given parameters
     */
    DecisionDocumentationModelPageableDto getDecisionDocumentationModels(Integer page, Integer size, DecisionDocumentationModelType type);

    /**
     * Deleting an DecisionDocumentationModel
     *
     * @param idDecisionDocumentationModel Which DecisionDocumentationModel
     */
    void deleteDecisionDocumentationModel(Long idDecisionDocumentationModel);

    enum DecisionDocumentationModelType {
        NEWEST, FEATURED, ACTIVE
    }
}
