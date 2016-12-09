package at.decisionexpert.controller.decisiondocumentation;

import at.decisionexpert.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationRelationController {

    DecisionDocumentationModelRelationDto create(Long idDecisionDocumentationModel, DecisionDocumentationModelRelationDto value);

    DecisionDocumentationModelRelationDto updateAttributes(Long idDecisionDocumentationModel, Long idRelation, DecisionDocumentationModelRelationDto newValues);

    void delete(Long idDecisionDocumentationModel, Long idRelation);
}
