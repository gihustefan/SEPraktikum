package at.decisionexpert.test.controller;

import at.decisionexpert.business.decisionguidance.DecisionGuidanceModelBusiness;
import at.decisionexpert.config.MainConfig;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelChangeRequestDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDesignOptionRelationDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelDto;
import at.decisionexpert.neo4jentity.dto.decisionguidance.DecisionGuidanceModelRelationDto;
import at.decisionexpert.neo4jentity.dto.user.UserCreationDto;
import at.decisionexpert.neo4jentity.node.*;
import at.decisionexpert.neo4jentity.relationship.decisionguidance.HasPotentialRequirement;
import at.decisionexpert.service.decisionguidancemodel.DecisionGuidanceModelService;
import at.decisionexpert.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by stefanhaselboeck on 10.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CreateDGM {

    @Autowired
    DecisionGuidanceModelBusiness decisionGuidanceModelBusiness;

    @Autowired
    DecisionGuidanceModelService decisionGuidanceModelService;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setSecurityContext() {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("john@doe.com", "password");
        if (!authenticationToken.isAuthenticated()) {
            createUser();
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("john@doe.com", "password"));
    }

    public void createUser() {
        UserCreationDto user = new UserCreationDto();
        user.setEmail("john@doe.com");
        user.setPassword("password");
        user.setUsername("johndoe");
        user.setFirstName("John");
        user.setLastName("Doe");

        userService.createUser(user);
    }

    @Test
    public void createDGM() {
        DecisionGuidanceModelChangeRequestDto dto = new DecisionGuidanceModelChangeRequestDto();
        dto.setName("Service Discovery");
        dto.setDescription("In order to make a request, the calling microservice has to know the location (IP address and port) of the called microservice. In microservice based systems, where microservice can come and go over time (auto-scaling, failures, updates), it’s hard to use a static configuration as in traditional monolithic systems. Therefore, a function to look up the actual running microservices and the location of them is needed. This function is called Service Discovery.");

        //createDecisionGuidanceModel sets published to false per default
        DecisionGuidanceModelDto response = decisionGuidanceModelBusiness.createDecisionGuidanceModel(dto);

        //publish DGM
        dto.setPublished(true);
        decisionGuidanceModelBusiness.updateDecisionGuidanceModelProperties((Long)response.getId(), dto);

        DecisionGuidanceModelRelationDto potentialRequirement = new DecisionGuidanceModelRelationDto();
        potentialRequirement.setName("NFR1: Service Discovery is programming language independent");
        decisionGuidanceModelService.createRelation((Long)response.getId(), potentialRequirement, HasPotentialRequirement.class, Requirement.class);

        DecisionGuidanceModelDesignOptionRelationDto designOption = new DecisionGuidanceModelDesignOptionRelationDto();
        designOption.setName("Server-side discovery");
        decisionGuidanceModelService.createDesignOptionRelation((Long)response.getId(), designOption);
    }
}
