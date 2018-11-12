package com.uproject.generic.controller.anydetail;

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
import com.uproject.generic.domain.repository.AnyDetailRepository;
import com.uproject.generic.domain.repository.AnyRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.AnyEntityDetail;
import com.uproject.generic.domain.utils.AnyDetailConstant;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.UTestAssistant;

/**
 * Integration test for {@link AnyController#delete(String)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = AnyApplication.class)
@ActiveProfiles("test")
public class DeleteLogicalIT {
	
	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;
	
	/** The any repository. */
	@Autowired
	private AnyDetailRepository repository;
	
	/** The assistant. */
	@Autowired
	private UTestAssistant<AnyEntityDetail> assistant;
	
	/** The path to controller. */
	private String pathToController = AnyDetailConstant.PATH_TO_CONTROLLER;
	
	/** The method. */
	private ApiMethod method = ApiMethod.DELETE_LOGICAL;
	
	/** The entity base. */
	private AnyEntity baseEntity = new AnyEntity(0);
	
	/** Repository for entity base. */
	@Autowired
	private AnyRepository baseRepository;
	
	/** The entity. */
	private AnyEntityDetail entity = new AnyEntityDetail(0,0);
	
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
		baseRepository.save(baseEntity);
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
		assertThat(entity.getRowExpiryDate()).isNull();
		//Validate response
		response = assistant.delete("?anyId="+entity.getId().getAnyId()+
									"&anyEntityDetailId="+entity.getId().getAnyEntityDetailId()+"&logical=true");
		expected = assistant.getResponse(method, entity.getId());
		compareResponse();
		//Validate data saved on database
		AnyEntityDetail entitySaved = repository.findOne(entity.getId());
		assertThat(entitySaved.getActiveInd()).isEqualTo("N");
		assertThat(entitySaved.getRowExpiryDate()).isNotNull();
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
		entity.setActiveInd("Y");
		AnyEntityDetail 
			dataNotValid =
				assistant.
					getDataNotValid(
						entity,
						"detailField", 
						"detail_field", 
						method);
		repository.save(dataNotValid);
		response = assistant.
						delete(	"?anyId="+entity.getId().getAnyId()+
								"&anyEntityDetailId="+entity.getId().getAnyEntityDetailId()+"&logical=true");
		expected = assistant.
						getResponse(
								assistant.getRule(), 
								entity.getDetailField());
		compareResponse();
	}
	
	/**
	 * PK not available, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void PKNotAvailable() throws Exception {
		response = assistant.delete("?anyId=33334"+
									"&anyEntityDetailId=12333"
									+ "&logical=true");
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
