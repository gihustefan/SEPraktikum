package at.decisionexpert.repository.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasDesignOption;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
public interface DGMHasDesignOptionRepository extends GraphRepository<HasDesignOption> {

    @Query("MATCH (dgm:DecisionGuidanceModel)-[rel:HAS_DESIGNOPTION]->() WHERE id(dgm) = {0} and rel.ordering = {1} RETURN rel")
    HasDesignOption findByOrdering(Long idDecisionGuidanceModel, int ordering);
}
