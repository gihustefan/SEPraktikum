package at.archkb.server.repository.node.user;

import at.archkb.server.neo4jentity.dto.user.UserDto;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import at.archkb.server.neo4jentity.dto.user.AdminUserCreationDto;
import at.archkb.server.neo4jentity.dto.user.UserActivationDto;
import at.archkb.server.neo4jentity.node.User;
import at.archkb.server.neo4jentity.queryresult.SensibleUserData;

public interface UserRepository extends GraphRepository<User> {

	User findByEmail(String email);

	User findByOriginalUsername(String username);
	
	@Query("match(n:ArchProfile) where id(n) = {0} match (n)-[:HAS_CREATOR]->(u:User) return u")
	User findOwnerOfProfile(long profileId);

	@Query("match(n:DecisionGuidanceModel) where id(n) = {0} match (n)-[:HAS_CREATOR]->(u:User) return u")
	User findOwnerOfDecisionGuidanceModel(long profileId);

	@Query("match(n:DesignOption) where id(n) = {0} match (n)-[:HAS_CREATOR]->(u:User) return u")
	User findOwnerOfDesignOption(long profileId);
	
	@Query("match (user:User) return id(user) as id, user.originalUsername as username, user.email as email, user.firstName as firstName, user.lastName as lastName")
	Iterable<UserDto> findAllUserMin();
	
	@Query("match (n:User) where id(n) = {0} set n.dateActivated=timestamp() return n.dateActivated as dateActivated, n.dateLocked as dateLocked, true as effectivChange")
	UserActivationDto activateUser(Long userId);
	
	@Query("match (n:User) where id(n) = {0} set n.dateLocked=timestamp() return n.dateActivated as dateActivated, n.dateLocked as dateLocked, true as effectivChange")
	UserActivationDto deactivateUser(Long userId);
	
	@Query("match (n:User) where id(n) = {0} optional match (n)-[:HAS_AUTHORITY]->(auth) return n.dateLocked as dateLocked, n.dateActivated as dateActivated, n.mailActivationToken as mailActivationToken, n.password as password, n.email as email, collect(auth) as authorities")
	SensibleUserData findSensibleUserData(Long userId);
	
	@Query("match(user:User) where user.email={0} return id(user)"
			+" union "
			+"match(user:User)where user.originalUsername={1} return id(user)")
	Iterable<Long> checkForExistingUser(String email, String username);
	
	@Query("match (n:User) where id(n) = {0} set n.password = {1} return id(n) as id, n.originalUsername as username, n.email as email")
	AdminUserCreationDto resetPassword(Long userId, String pw);

}
