package com.uproject.generic.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uproject.generic.domain.utils.ApplicationConstant;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

/**
 * API documentation configuration
 * <p>
 * Configuration of the automatic 
 * generation of swagger documentation.
 * <p>
 * Documentation can be found on "~/swagger-ui.html"
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/** The Api title */
	@Value("${ums.api.title:GENERIC-UMS}")
	private String apiTitle;
	
	/**
	 * Configuration of the automatic 
	 * generation of swagger documentation.
	 * 
	 * @return Swagger configuration
	 */
	@Bean
	public Docket areaApi() {
		return new Docket(
				DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(
						ApplicationConstant.BASE_PACKAGE+
						ApplicationConstant.UMS_NAME+
						".controller"))
                .paths(regex("/.*")).build()
				.apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}
	
	/**
	 * API information. Contain title, description and contact name.
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		  return new ApiInfo(
				  apiTitle, //Title
				  "This api allow to perform CRUD operations "+ //Description 
				  "over ANY_ENTITY and ANY_ENTITY_DETAIL on database. "+
				  "NOTE: This project is a reference for other projects.", 
			      "1.0", //Version
			      "Terms of service", 
			      new Contact("UProject Develop Team", null, "wilevergomez@gmail.com"), 
			      null,null, //License
			      Collections.emptyList());//VendorExtensions
			}
}
