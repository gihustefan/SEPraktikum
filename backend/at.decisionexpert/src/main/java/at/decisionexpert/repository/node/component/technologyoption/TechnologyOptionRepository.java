package at.decisionexpert.repository.node.component.technologyoption;

import at.decisionexpert.neo4jentity.node.TechnologyOption;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by stefanhaselboeck on 17.10.16.
 */
public interface TechnologyOptionRepository extends GraphRepository<TechnologyOption> {
}
