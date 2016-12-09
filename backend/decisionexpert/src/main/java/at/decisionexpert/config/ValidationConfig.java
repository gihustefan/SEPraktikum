package at.decisionexpert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidationConfig {
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("validation");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		validator.setParameterNameDiscoverer(new LocalVariableTableParameterNameDiscoverer());
		return validator;
	}

	@Bean
	@Autowired
    MethodValidationPostProcessor getValidationPostProcessor(LocalValidatorFactoryBean validator) {
		MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
		processor.setValidator(validator);
		return processor;
	}
}
