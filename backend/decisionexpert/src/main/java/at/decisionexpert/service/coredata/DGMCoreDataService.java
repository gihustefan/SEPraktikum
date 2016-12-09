package at.decisionexpert.service.coredata;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import javax.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DGMCoreDataService {
    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelRelationDto> getCoreData(@Size(min = 3) String titlePartial, @NotNull Class<T> coreDataClass);
}
