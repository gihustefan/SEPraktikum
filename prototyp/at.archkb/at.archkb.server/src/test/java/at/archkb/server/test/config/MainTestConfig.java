package at.archkb.server.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Main Test Config file
 * Defining basePackages to scan
 *  - Excluding MainConfig and Neo4JConfig -> should not be included in the Test Config
 */
@Configuration
@ComponentScan(basePackages = {"at.archkb.server"})
public class MainTestConfig {

}
