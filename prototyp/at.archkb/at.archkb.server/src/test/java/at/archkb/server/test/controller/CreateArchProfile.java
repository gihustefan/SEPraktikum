package at.archkb.server.test.controller;

import at.archkb.server.neo4jentity.dto.ArchProfileChangeRequestDto;
import at.archkb.server.test.config.MainTestConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * Created by stefanhaselboeck on 16.08.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainTestConfig.class})
@WebAppConfiguration
public class CreateArchProfile {

    @Test
    public void createArchitectureProfile() {
        ArchProfileChangeRequestDto apd = new ArchProfileChangeRequestDto();
        apd.setTitle("test");
        apd.setDescription("desc");
        apd.setPublished(false);

        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(apd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
