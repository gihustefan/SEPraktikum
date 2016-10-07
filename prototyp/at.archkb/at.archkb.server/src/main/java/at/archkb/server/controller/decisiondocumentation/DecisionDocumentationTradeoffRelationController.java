package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelRelationDto;
import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelTradeoffRelationDto;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationTradeoffRelationController {

    DecisionDocumentationModelTradeoffRelationDto create(Long idDecisionDocumentation, DecisionDocumentationModelTradeoffRelationDto tradeoff);

    DecisionDocumentationModelTradeoffRelationDto updateAttributes(Long idDecisionDocumentation, Long idRelation, DecisionDocumentationModelTradeoffRelationDto newValues);

    void delete(Long idDecisionDocumentation, Long idRelation);
}
