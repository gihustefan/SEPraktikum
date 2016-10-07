package at.archkb.server.security;

import java.io.Serializable;

import at.archkb.server.neo4jentity.node.DecisionGuidanceModel;
import at.archkb.server.neo4jentity.node.DesignOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import at.archkb.server.neo4jentity.node.ArchProfile;
import at.archkb.server.neo4jentity.node.User;
import at.archkb.server.neo4jentity.queryresult.SensibleUserData;
import at.archkb.server.repository.node.user.UserRepository;

/**
 * Customized hasPermissions checks. Easier than to create full featured ACL.
 * 
 * @author Rainer
 *
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (authentication == null || targetDomainObject == null || permission == null)
			return false;

		// check if authentication has owner permission + if ArchProfile
		// instance
		if ("OWNER".equals(permission.toString()) && targetDomainObject instanceof ArchProfile) {
			// check upon user name if it is really the right user
			return authentication.getName()
					.equals(getArchProfileOwnerFromDb(((ArchProfile) targetDomainObject).getId()).getUsername());
		}
		
		//Checks if no sensible data of User is changed
		if("NOSENSIBLEDATA".equals(permission.toString()) && targetDomainObject instanceof User){
			User user = (User) targetDomainObject;
			//if you findAllByTitle the entire user from the db the updated user would be overridden
			SensibleUserData orguser = userRepository.findSensibleUserData(user.getId());
			if(user.getDateActivated()>0&&orguser.getDateActivated()!=user.getDateActivated()){
				return false;
			}
			if(user.getDateLocked()>0&&orguser.getDateLocked()!=user.getDateLocked()){
				return false;
			}
			if(user.getEmail()!=null&&!orguser.getEmail().equals(user.getEmail())){
				return false;
			}
			if(user.getMailActivationToken()!=null&&!orguser.getMailActivationToken().equals(user.getMailActivationToken())){
				return false;
			}
			if(user.getPassword()!=null&&!orguser.getPassword().equals(user.getPassword())){
				return false;
			}
			if(user.getAuthorities()!=null&&user.getAuthorities().size()>0&&!orguser.getAuthorities().equals(user.getAuthorities())){
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		if (authentication == null || targetId == null || targetType == null || permission == null)
			return false;

		// check if authentication has owner permission + if targetType is
		// ArchProfile
		if ("OWNER".equals(permission.toString()) && ArchProfile.class.getName().equals(targetType.toString())) {
			return authentication.getName().equals(getArchProfileOwnerFromDb((Long)targetId).getUsername());
		}

		if ("OWNER".equals(permission.toString()) && DecisionGuidanceModel.class.getName().equals(targetType.toString())) {
			return authentication.getName().equals(getDecisionGuidanceModelOwnerFromDb((Long)targetId).getUsername());
		}

		if ("OWNER".equals(permission.toString()) && DesignOption.class.getName().equals(targetType.toString())) {
			return authentication.getName().equals(getDesignOptionOwnerFromDb((Long)targetId).getUsername());
		}

		return false;
	}

	/**
	 * Help method for fetching the ArchProfileOwner from the DB
	 * 
	 * @param id
	 *            ID of the ArchProfile
	 * @return Owner of the ArchProfile if ArchProfile found, else empty User
	 *         Object
	 */
	private User getArchProfileOwnerFromDb(Long id) {	
		// Fetch user from DB -> the given
		User user = userRepository.findOwnerOfProfile(id);

		// given target Domain Object not found in DB, or owner not set
		if (user == null)
			return new User();

		return user;
	}

	/**
	 * Help method for fetching the DecisionGuidanceModelOwner from the DB
	 *
	 * @param id
	 *            ID of the DecisionGuidanceModel
	 * @return Owner of the DecisionGuidanceModel if DecisionGuidanceModel found, else empty User
	 *         Object
	 */
	private User getDecisionGuidanceModelOwnerFromDb(Long id) {
		// Fetch user from DB -> the given
		User user = userRepository.findOwnerOfDecisionGuidanceModel(id);

		// given target Domain Object not found in DB, or owner not set
		if (user == null)
			return new User();

		return user;
	}

	/**
	 * Help method for fetching the DecisionGuidanceModelOwner from the DB
	 *
	 * @param id
	 *            ID of the DecisionGuidanceModel
	 * @return Owner of the DecisionGuidanceModel if DecisionGuidanceModel found, else empty User
	 *         Object
	 */
	private User getDesignOptionOwnerFromDb(Long id) {
		// Fetch user from DB -> the given
		User user = userRepository.findOwnerOfDesignOption(id);

		// given target Domain Object not found in DB, or owner not set
		if (user == null)
			return new User();

		return user;
	}

}
