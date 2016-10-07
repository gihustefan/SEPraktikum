package at.archkb.server.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Rainer on 02.06.2016.
 */
public class SecurityUtils {

    /**
     * Method to check if an authentication contains a specific role
     *
     * @param role           Name of the Role e.g. ROLE_ADMIN, ROLE_USER,...
     * @param authentication The spring security authentication
     * @return true if the authentication contains the role, false if not
     */
    public static boolean hasRole(String role, Authentication authentication) {

        // If the authentication contains the role -> return true
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }

        // If the authentication does not contain it
        return false;
    }

}
