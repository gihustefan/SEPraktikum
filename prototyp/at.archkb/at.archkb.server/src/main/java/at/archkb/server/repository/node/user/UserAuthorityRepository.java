package at.archkb.server.repository.node.user;

import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.node.UserAuthority;

public interface UserAuthorityRepository extends GraphRepository<UserAuthority> {
	
	UserAuthority findByAuthority(String authority);

}
