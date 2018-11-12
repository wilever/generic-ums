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
 * Integration test for {@link AnyController#update(AnyEntity, String)}. (RESTORE)
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = AnyApplication.class)
@ActiveProfiles("test")
public class RestoreIT {
	
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
	private ApiMethod method = ApiMethod.RESTORE;
	
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
		entity.setRowExpiryDate(new java.sql.Date(System.currentTimeMillis()));
		setDataTest("N");
		assertThat(entity.getActiveInd()).isEqualTo("N");
		assertThat(entity.getRowExpiryDate()).isNotNull();
		assertThat(entity.getRowChangedBy()).isNull();
		assertThat(entity.getRowChangedDate()).isNull();
		//Validate response
		response = assistant.patch("?id="+entity.getAnyId());
		expected = assistant.getResponse(method, entity.getAnyId());
		compareResponse();
		//Validate data save on database
		AnyEntity entitySaved = repository.findOne(entity.getAnyId());
		assertThat(entitySaved.getActiveInd()).isEqualTo("Y");
		assertThat(entitySaved.getRowExpiryDate()).isNull();
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
		entity.setActiveInd("N");
		AnyEntity dataNotValid = assistant.getDataNotValid(entity, "field", "field", method);
		repository.save(dataNotValid);
		response = assistant.patch("?id="+dataNotValid.getAnyId());
		expected = assistant.getResponse(assistant.getRule(), dataNotValid.getField());
		compareResponse();
	}
	
	/**
	 * PK not available, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void PKNotAvailable() throws Exception {
		response = assistant.patch("?id=1234");
		expected = assistant.getResponse(ErrorCode.DATA_NULL);
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
