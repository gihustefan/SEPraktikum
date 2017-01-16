package at.decisionexpert.repository.relationship.decisionguidance.designoption;

import at.decisionexpert.neo4jentity.relationship.decisionguidance.designoption.HasAffectedGuidanceModels;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 08.09.16.
 */
public interface DOHasAffectedGuidanceModelRepository extends GraphRepository<HasAffectedGuidanceModels> {
    @Query("MATCH (do:DesignOption)->[rel:HAS_AFFECTEDGUIDANCEMODEL]->() WHERE id(do) = {0} and rel.ordering = {1} RETURN rel")
    HasAffectedGuidanceModels findByOrdering(Long idDesignOption, int ordering);

}
