package at.archkb.server.repository.node;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import at.archkb.server.neo4jentity.node.Reputation;
import at.archkb.server.neo4jentity.node.User;

public interface ReputationRepository extends GraphRepository<Reputation> {

	@Query("match (u:User) where id(u)={userId} "+
		"optional match (u)<-[:HAS_CREATOR]-()-[:HAS_REPUTATION]->(r:Reputation)-[:HAS_REPUTATION_TYPE]->(rt:ReputationType) "+
		"with u, sum(rt.points) as reppoint "+
		"optional match (u)-[:ACHIEVES_BADGE]-(b:Badge) "+
		"with u, sum(b.points)+reppoint as points "+
		"return points")
	public int findReputationOfUser(@Param("userId")User user);

}
