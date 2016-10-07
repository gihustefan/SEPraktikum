package at.archkb.server.controller.archprofile;

import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.dto.ArchProfileTradeoffRelationDto;

/**
 * Created by Rainer on 23.05.2016.
 */

/**
 * We cannot use the ArchProfileRelationController for the Tradeoffs, becuase the tradeoffs have a more specific struchture than the other ArchProfile Relations
 */
public interface ArchProfileTradeoffRelationController {

    ArchProfileTradeoffRelationDto create(Long idArchProfile, ArchProfileTradeoffRelationDto tradeoff);

    ArchProfileTradeoffRelationDto updateAttributes(Long idArchProfile, Long idRelation, ArchProfileTradeoffRelationDto newValues);

    void delete(Long idArchProfile, Long idRelation);
}
