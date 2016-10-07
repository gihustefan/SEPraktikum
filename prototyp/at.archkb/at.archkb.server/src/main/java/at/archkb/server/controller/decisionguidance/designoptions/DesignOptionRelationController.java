package at.archkb.server.controller.decisionguidance.designoptions;

import at.archkb.server.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DesignOptionRelationController {

    DesignOptionRelationDto create(Long idDesignOption, DesignOptionRelationDto value);

    DesignOptionRelationDto updateAttributes(Long idDesignOption, Long idRelation, DesignOptionRelationDto newValues);

    void delete(Long idDesignOption, Long idRelation);
}
