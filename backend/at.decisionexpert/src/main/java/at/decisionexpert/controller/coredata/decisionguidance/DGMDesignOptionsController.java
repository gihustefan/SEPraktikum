package at.decisionexpert.controller.coredata.decisionguidance;

import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;

import java.util.List;

/**
 * Created by stefanhaselboeck on 11.11.16.
 */
public interface DGMDesignOptionsController {
    /**
     * Getting a list of core data Values based on a partial title
     *
     * TODO: Maybe evaluate if ArchProfileRealationDto is the right Type (we
     * would only need title and definition)
     *
     * @param titlePartial
     *            the partial title of the core data attributes
     * @return a List of core data values filtered by the partial title
     */
    List<DecisionGuidanceModelDesignOptionRelationDto> getDesignOptions(String titlePartial);
}
