package at.decisionexpert.repository.node;

import at.decisionexpert.neo4jentity.node.Requirement;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 07.12.16.
 */
public interface RequirementRepository extends GraphRepository<Requirement> {
}
