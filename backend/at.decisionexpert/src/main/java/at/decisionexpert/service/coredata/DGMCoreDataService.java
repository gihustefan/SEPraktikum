package at.decisionexpert.service.coredata;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelatedGuidanceModelsDto;
import at.decisionexpert.neo4jentity.node.CoreData;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 18.08.16.
 */
public interface DGMCoreDataService {
    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelRelationDto> getCoreData(@Size(min = 3) String titlePartial, @NotNull Class<T> coreDataClass);

    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelRelatedGuidanceModelsDto> getRelatedGuidanceModels(@Size(min = 3) String titlePartial);

    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DecisionGuidanceModelDesignOptionRelationDto> getDesignOptions(@Size(min = 3) String titlePartial);
}
