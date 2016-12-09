package at.decisionexpert.config;

import at.decisionexpert.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityMethodConfig extends GlobalMethodSecurityConfiguration {
	
	
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		// Must set a custom permission evaluator for handling hasPermission(...) annotations
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setPermissionEvaluator(permissionEvaluator());
		return handler;
	}
	
	/**
	 * Custom permission Evaluator -> checking ownership of ArchProfiles, ...
	 * @return
	 */
	@Bean
	public CustomPermissionEvaluator permissionEvaluator() {
		return new CustomPermissionEvaluator();
	}

}
