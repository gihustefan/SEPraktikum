package at.archkb.server.repository.relationship.decisionguidance;

import at.archkb.server.neo4jentity.relationship.decisionguidance.HasRelatedGuidanceModels;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 19.08.16.
 */
public interface DGMHasGuidanceModelRepository extends GraphRepository<HasRelatedGuidanceModels> {

    @Query("MATCH (dgm:DecisionGuidanceModel)->[rel:HAS_RELATEDGUIDANCEMODEL]->() WHERE id(dgm) = {0} and rel.ordering = {1} RETURN rel")
    HasRelatedGuidanceModels findByOrdering(Long idDecisionGuidanceModel, int ordering);
}
