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
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.UTestAssistant;

/**
 * Integration test for {@link AnyController#add(AnyEntity)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = AnyApplication.class)
@ActiveProfiles("test")
public class AddIT {

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
	
	/** The method. */
	private ApiMethod method = ApiMethod.ADD;
	
	/** The entity. */
	private AnyEntity entity = new AnyEntity(0);
	
	/** The response. */
	private ResponseEntity<String> response;
	
	/** The expected. */
	private ResponseEntity<String> expected;
	
	/**
	 * Sets the assistant.
	 */
	@Before
	public void setAssistant() {
		assistant.setRestTemplate(restTemplate.getRestTemplate());
		assistant.setPathToController(pathToController);
		repository.deleteAllInBatch();
	}
	
	/**
	 * Success, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void Success() throws Exception {
		//Validate response
		response = assistant.post(entity);
		expected = assistant.getResponse(method,entity.getAnyId());
		compareResponse();
		//Validate data on database
		AnyEntity entitySaved = repository.findOne(entity.getAnyId());
		assertThat(entitySaved.getActiveInd()).isEqualTo("Y");
		assertThat(entitySaved.getRowCreatedBy()).isNotNull();
		assertThat(entitySaved.getRowCreatedDate()).isNotNull();
	}
	
	/**
	 * Data is not valid, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void DataIsNotValid() throws Exception {
		AnyEntity dataNotValid = assistant.getDataNotValid(entity,"field","field", method);
		response = assistant.post(dataNotValid);
		expected = assistant.getResponse(assistant.getRule(), dataNotValid.getField());
		compareResponse();
	}
	
	/**
	 * Data without PK, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void DataWithoutPK() throws Exception {
		entity.setAnyId(null);
		response = assistant.post(entity);
		expected = assistant.getResponse(new UException(ErrorCode.PK_NOT_AVAILABLE));
		compareResponse();
	}
	
	/**
	 * PK not available, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void PKNotAvailable() throws Exception {
		repository.save(entity);
		response = assistant.post(entity);
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
