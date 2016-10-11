package at.decisionexpert.test.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by stefanhaselboeck on 10.10.16.
 */
@Configuration
@ComponentScan(basePackages = {"at.decisionexpert"})
@EnableNeo4jRepositories(basePackages = {"at.decisionexpert.repository"})
@EnableTransactionManagement
public class MainTestConfig extends Neo4jConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        // Using an embedded neo4j instance for testing
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
        config.driverConfiguration().setCredentials("neo4j", "neo4j");

        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "at.decisionexpert.neo4jentity");
    }
}
