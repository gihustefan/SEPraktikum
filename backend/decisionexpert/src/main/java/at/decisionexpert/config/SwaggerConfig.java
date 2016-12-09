package at.decisionexpert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				//.apis(RequestHandlerSelectors.basePackage("at.archkb.server.controller.admin"))
				// .paths(PathSelectors.ant("/api/admin/user*"))
				// .apis(RequestHandlerSelectors.any())
				// .paths(PathSelectors.any())
				//.pathMapping("/api/")
				.build()
				//.host("192.168.0.1")
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo defaultinfo = ApiInfo.DEFAULT;
		ApiInfo apiInfo = new ApiInfo("Decision Expert", "Backend Api for Decision Expert", defaultinfo.getVersion(), defaultinfo.getTermsOfServiceUrl(), new Contact("Stefan Haselboeck", "http://se.jku.at", "stefan.haselboeck@jku.at"), defaultinfo.getLicense(), defaultinfo.getLicenseUrl());
		return apiInfo;
	}

}
