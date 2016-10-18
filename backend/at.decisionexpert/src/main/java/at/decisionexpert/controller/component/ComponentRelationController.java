package at.decisionexpert.controller.component;

import at.decisionexpert.neo4jentity.dto.component.ComponentRelationDto;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface ComponentRelationController {

    ComponentRelationDto create(Long idComponent, ComponentRelationDto value);

    ComponentRelationDto updateAttributes(Long idComponent, Long idRelation, ComponentRelationDto newValues);

    void delete(Long idComponent, Long idRelation);
}
