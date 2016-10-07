package at.archkb.server.repository.node;

import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.node.DesignDecision;

public interface DesignDecisionRepository extends GraphRepository<DesignDecision> {

}
