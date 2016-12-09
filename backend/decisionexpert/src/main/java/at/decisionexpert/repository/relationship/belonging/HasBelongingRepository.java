package at.decisionexpert.repository.relationship.belonging;

import at.decisionexpert.neo4jentity.relationship.HasBelonging;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 06.12.16.
 */
public interface HasBelongingRepository extends GraphRepository<HasBelonging> {
}
