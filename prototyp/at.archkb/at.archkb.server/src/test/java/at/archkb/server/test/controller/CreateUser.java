package at.archkb.server.test.controller;

import at.archkb.server.neo4jentity.node.*;
import at.archkb.server.repository.node.user.UserRepository;
import at.archkb.server.test.config.MainTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by stefanhaselboeck on 10.08.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainTestConfig.class})
@WebAppConfiguration
public class CreateUser {
    @Autowired
    UserRepository userRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private Neo4jOperations template;

    @Before
	public void clearDB() {
		final String clearall = "Match (n) Optional Match (n)-[r]-(n2) Delete n,r,n2";
		//template.query(clearall, new HashMap<String, String>());
        template.query("create (n {name: 'Test'})", new HashMap<String, String>());
        System.out.print("DBcreated");
        System.out.println(passwordEncoder.encode("password"));
        User user = new User();
        user.setEmail("test@test.com");

        user.setPassword(passwordEncoder.encode("password"));
        template.save(user);
	}

    @Test
    public void createUser() {
        User user = new User();
        UserAuthority adminrole = new UserAuthority("ROLE_ADMIN");
		UserAuthority userrole = new UserAuthority("ROLE_USER");

		City linz = new City("Linz", "4040", new Country("Austria"));
		Company jku = new Company("JKU", "Altenberger Straße 69", linz);
		Position prof = new Position("Professor", "Teaching at Unversity");

		user.setEmail("user@archkb.at");
		user.setPassword("password");
		user.addAuthority(userrole);
//		user.setAbout("In this section each user can descripe himself");
//		user.setAddress("Altenberger Straße 69");
//		user.setCity(linz);
//		user.setCompany(jku);
		user.setFirstName("Max");
		user.setLastName("Mustermann");
//		user.setPictureUrl(UUID.randomUUID().toString());
		user.setPosition(prof);
		user.setOriginalUsername("max");
		user.setMailActivationToken(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User admin = new User();
		admin.setEmail("admin@archkb.at");
		admin.setPassword("password");
		admin.setOriginalUsername("admin");
		admin.addAuthority(adminrole);
		admin.addAuthority(userrole);
		admin.setMailActivationToken(UUID.randomUUID().toString().replaceAll("-", ""));
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        template.save(user);
        template.save(admin);
    }
}
