package at.archkb.server.service.coredata;

import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRealtionDto;
import at.archkb.server.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.archkb.server.neo4jentity.node.CoreData;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DGMCoreDataService {
    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelRealtionDto> getCoreData(@Size(min = 3) String titlePartial, @NotNull Class<T> coreDataClass);

    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedGuidanceModels(@Size(min = 3) String titlePartial);
}
