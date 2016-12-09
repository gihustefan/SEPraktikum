package at.decisionexpert.repository.relationship.decisiondocumentation;

import at.decisionexpert.neo4jentity.relationship.decisiondocumentation.HasTradeoff;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by stefanhaselboeck on 06.10.16.
 */
public interface DDMHasTradeoffRepository extends GraphRepository<HasTradeoff> {

    @Query("MATCH (ddm:DecisionDocumentationModel)-[rel:HAS_TRADEOFF]->() WHERE id(ap) = {0} RETURN rel")
    List<HasTradeoff> findAllDecisionDocumentationTradeoffs(Long idArchProfile);

    @Query("MATCH (ddm:DecisionDocumentationModel)->[rel:HAS_TRADEOFF]->() WHERE id(ap) = {0} and rel.ordering = {1} RETURN rel")
    HasTradeoff findByOrdering(Long idArchProfile, int ordering);
}
