package com.uproject.generic.controller.any;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.uproject.generic.AnyApplication;
import com.uproject.generic.controller.AnyController;
import com.uproject.generic.domain.repository.AnyRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.utils.AnyConstant;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.UTestAssistant;

/**
 * Integration test for {@link AnyController#update(AnyEntity, String)}. (UPDATE)
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = AnyApplication.class)
@ActiveProfiles("test")
public class UpdateIT {
	
	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;
	
	/** The any repository. */
	@Autowired
	private AnyRepository repository;
	
	/** The assistant. */
	@Autowired
	private UTestAssistant<AnyEntity> assistant;
	
	/** The path to controller. */
	private String pathToController = AnyConstant.PATH_TO_CONTROLLER;
	
	/** The condition. */
	private ApiMethod condition = ApiMethod.UPDATE;
	
	/** The entity. */
	private AnyEntity entity = new AnyEntity(0);
	
	/** The response. */
	private ResponseEntity<String> response;
	
	/** The expected. */
	private ResponseEntity<String> expected;
	
	/**
	 * Sets the data test.
	 *
	 * @param activeInd the new data test
	 */
	private void setDataTest(String activeInd) {
		entity.setActiveInd(activeInd);
		repository.save(entity);
	}
	
	/**
	 * Sets the assistant.
	 */
	@Before
	public void setAssistant() {
		assistant.setRestTemplate(restTemplate.getRestTemplate());
		assistant.setPathToController(pathToController);
	}
	
	/**
	 * Success, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void Success() throws Exception {
		//Validate data test
		setDataTest("Y");
		assertThat(entity.getActiveInd()).isEqualTo("Y");
		//Validate response
		response = assistant.put(entity);
		expected = assistant.getResponse(condition, entity.getAnyId());
		compareResponse();
		//Validate data save on database
		AnyEntity entitySaved = repository.findOne(entity.getAnyId());
		assertThat(entitySaved.getActiveInd()).isEqualTo("Y");
		assertThat(entitySaved.getRowChangedBy()).isNotNull();
		assertThat(entitySaved.getRowChangedDate()).isNotNull();
	}
	
	/**
	 * Data not validate, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void DataNotValidate() throws Exception {
		AnyEntity dataNotValid = assistant.getDataNotValid(entity, "field", "field", condition);
		dataNotValid.setActiveInd("Y");
		repository.save(dataNotValid);
		response = assistant.put(dataNotValid);
		expected = assistant.getResponse(assistant.getRule(), dataNotValid.getField());
		compareResponse();
	}
	
	/**
	 * Data with PK not available, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void DataWithPKNotAvailable() throws Exception {
		entity.setAnyId(10);
		response = assistant.put(entity);
		expected = assistant.getResponse(ErrorCode.PK_NOT_AVAILABLE);
		compareResponse();
	}
	
	/**
	 * Compare responses.
	 */
	private void compareResponse() {
		assertThat(response.
						getBody()).
		isEqualTo(expected.
						getBody());
		assertThat(response.
						getStatusCode()).
		isEqualTo(expected.
						getStatusCode());
	}
}
