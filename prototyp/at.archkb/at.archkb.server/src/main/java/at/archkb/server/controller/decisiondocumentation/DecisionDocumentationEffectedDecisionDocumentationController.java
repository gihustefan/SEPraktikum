package at.archkb.server.controller.decisiondocumentation;

import at.archkb.server.neo4jentity.dto.decisiondocumentation.DecisionDocumentationModelEffectedDocumentationModelDto;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DecisionDocumentationEffectedDecisionDocumentationController {

    DecisionDocumentationModelEffectedDocumentationModelDto create(Long idDecisionDocumentation, DecisionDocumentationModelEffectedDocumentationModelDto value);

    DecisionDocumentationModelEffectedDocumentationModelDto updateAttributes(Long idDecisionDocumentation, Long idRelation, DecisionDocumentationModelEffectedDocumentationModelDto newValues);

    void delete(Long idDecisionDocumentation, Long idRelation);
}
