package at.archkb.server.repository.node;

import java.util.Collection;

import at.archkb.server.neo4jentity.node.CoreData;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ArchProfileAttributeRepositoryImpl implements ArchProfileAttributeRepository {

	@Autowired
	private Neo4jOperations neo4jOperations;
	
	
	@Override
	public <T extends CoreData> Collection<T> findAllByTitle(String partialTitle, Class<T> attributeClass) {
		// Building a query with filters
		Filters filters = new Filters();
		Filter filter = new Filter();
		filter.setPropertyName("name");
		// Regular expression for finding partials -> case insensitive (?i)
		filter.setPropertyValue("(?i)" + partialTitle);
		filter.setComparisonOperator(ComparisonOperator.MATCHES);
		filters.add(filter);

		// Load all by the given filter
		return neo4jOperations.loadAllByProperties(attributeClass, filters);
	}

	@Override
	public <T extends CoreData> T findById(Long id, Class<T> attributeClass) {
		if(id == null)
			return null;

		return neo4jOperations.load(attributeClass, id, 0);
	}

	@Override
	public <T extends CoreData> T save(T archProfileAttribute) {
		return neo4jOperations.save(archProfileAttribute);
	}

}
