package at.decisionexpert.repository.node.component;

import at.decisionexpert.neo4jentity.node.Component;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface ComponentRepository extends GraphRepository<Component> {
}
