package at.archkb.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import at.archkb.server.StartupHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan({"at.archkb.server"})
public class MainConfig {
	
	@Bean
	public StartupHandler startupHandler() {
		return new StartupHandler();
	}
	
}
