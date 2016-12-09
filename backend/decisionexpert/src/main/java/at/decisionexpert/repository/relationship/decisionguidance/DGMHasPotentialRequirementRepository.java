package at.decisionexpert.repository.relationship.decisionguidance;

import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasPotentialRequirement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.Set;

/**
 * Created by stefanhaselboeck on 09.12.16.
 */
public interface DGMHasPotentialRequirementRepository extends GraphRepository<HasPotentialRequirement>{

    @Query("MATCH (dgm:DecisionGuidanceModel)-[rel:HAS_POTENTIALREQUIREMENT]->() WHERE id(dgm) = {0} RETURN rel")
    Set<HasPotentialRequirement> findAll(Long idDecisionGuidanceModel);
}
