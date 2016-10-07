package at.archkb.server.repository.node;

import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.node.Constraint;

public interface ConstraintRepository extends GraphRepository<Constraint>{
	
	Constraint findByName(String name);

}
