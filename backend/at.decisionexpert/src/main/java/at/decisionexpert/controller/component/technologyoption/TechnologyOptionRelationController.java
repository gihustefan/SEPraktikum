package at.decisionexpert.controller.component.technologyoption;

import at.decisionexpert.neo4jentity.dto.component.technologyoption.TechnologyOptionRelationDto;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TechnologyOptionRelationController {

    TechnologyOptionRelationDto create(Long idTechnologyOption, TechnologyOptionRelationDto value);

    TechnologyOptionRelationDto updateAttributes(Long idTechnologyOption, Long idRelation, TechnologyOptionRelationDto newValues);

    void delete(Long idTechnologyOption, Long idRelation);
}
