package com.uproject.generic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.uproject.generic.domain.utils.ApplicationConstant;
import com.uproject.generic.domain.utils.ProfileConstant;

/**
 * Application that perform 
 * MicroService components.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 *
 */
@ComponentScan(basePackages= {
		ApplicationConstant.BASE_PACKAGE})
@EnableJpaRepositories(basePackages= {
		ApplicationConstant.GENERIC_REPO,
		ApplicationConstant.LIBRARY_REPO})
@EntityScan(basePackages= {
		ApplicationConstant.GENERIC_ENTITY,
		ApplicationConstant.LIBRARY_ENTITY})
@SpringBootApplication
public class AnyApplication {
	/**
	 * Profile for call resource: application-{PROFILE}.properties 
	 */
	private static final String PROFILE = ProfileConstant.DEVELOP; 
	public static void main(String[] args) {
		SpringApplication application = 
				new SpringApplication(AnyApplication.class);
		//Set application-{PROFILE}.properties
		application.setAdditionalProfiles(PROFILE);
		application.run(args);
	}
}
