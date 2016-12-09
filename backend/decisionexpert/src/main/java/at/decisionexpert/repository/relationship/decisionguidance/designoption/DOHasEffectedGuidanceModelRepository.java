package at.decisionexpert.repository.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasEffectedGuidanceModels;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
public interface DOHasEffectedGuidanceModelRepository extends GraphRepository<HasEffectedGuidanceModels> {
    @Query("MATCH (do:DesignOption)->[rel:HAS_EFFECTEDGUIDANCEMODEL]->() WHERE id(do) = {0} and rel.ordering = {1} RETURN rel")
    HasEffectedGuidanceModels findByOrdering(Long idDesignOption, int ordering);

}
