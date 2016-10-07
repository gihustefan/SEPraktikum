package at.archkb.server.repository.node;

import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.node.QualityAttribute;

public interface QualityAttributeRepository extends GraphRepository<QualityAttribute> {
	
	public QualityAttribute findByName(String name);

}
