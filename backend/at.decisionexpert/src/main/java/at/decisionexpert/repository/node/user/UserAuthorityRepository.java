package at.decisionexpert.repository.node.user;

import at.decisionexpert.neo4jentity.node.UserAuthority;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserAuthorityRepository extends GraphRepository<UserAuthority> {
	
	UserAuthority findByAuthority(String authority);

}
