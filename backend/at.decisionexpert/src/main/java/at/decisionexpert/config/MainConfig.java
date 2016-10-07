package at.decisionexpert.config;

import at.decisionexpert.StartupHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan({"at.decisionexpert"})
public class MainConfig {
	
	@Bean
	public StartupHandler startupHandler() {
		return new StartupHandler();
	}
	
}
