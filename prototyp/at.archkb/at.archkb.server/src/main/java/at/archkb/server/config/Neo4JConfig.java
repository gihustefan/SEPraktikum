package at.archkb.server.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "at.archkb.server.repository")
@EnableTransactionManagement
@PropertySource({"classpath:data.properties"})
public class Neo4JConfig extends Neo4jConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration().setDriverClassName(environment.getRequiredProperty("neo4j.driver"))
				.setURI(environment.getRequiredProperty("neo4j.uri"));
		return config;
	}

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "at.archkb.server.neo4jentity");
	}
}
