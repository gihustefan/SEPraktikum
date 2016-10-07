package at.archkb.server.test.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * Created by Rainer on 09.06.2016.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = { "at.archkb.server.repository" })
public class Neo4JTestConfig extends Neo4jConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        // Using an embedded neo4j instance for testing
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
        config.driverConfiguration().setCredentials("neo4j", "neo4j");

        return config;
    }

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "at.archkb.server.neo4jentity");
    }
}
