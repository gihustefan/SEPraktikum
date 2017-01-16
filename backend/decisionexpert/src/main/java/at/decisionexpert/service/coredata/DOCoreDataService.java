package at.decisionexpert.service.coredata;

import at.decisionexpert.neo4jentity.dto.decisionguidance.designoption.DesignOptionRelationDto;
import at.decisionexpert.neo4jentity.node.CoreData;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by stefanhaselboeck on 16.01.17.
 */
public interface DOCoreDataService {
    @PreAuthorize("hasRole('ROLE_USER')")
    <T extends CoreData> List<DesignOptionRelationDto> getCoreData(@Size(min = 3) String titlePartial, @NotNull Class<T> coreDataClass);
}
