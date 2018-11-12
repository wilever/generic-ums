package com.uproject.generic.service.anydetail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uproject.generic.domain.repository.AnyDetailRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.AnyEntityDetail;
import com.uproject.generic.service.AnyDetailService;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.service.UService;
import com.uproject.library.ums.service.UValidator;

/**
 * Unit test for {@link AnyDetailService#execute(AnyEntity, ApiMethod)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
public class ExecuteTest {

	/** The service. */
	@InjectMocks
	private AnyDetailService service;
	
	/** The repository. */
	@Mock
	private AnyDetailRepository repository;
	
	/** The validator. */
	@Mock
	private UValidator validator;
	
	/** The entity. */
	private AnyEntityDetail entity = new AnyEntityDetail(0,0);
	
	/** The method to test: {@link ApiMethod#ADD}. */
	private ApiMethod method = ApiMethod.ADD;
	
	/**
	 * Call mock annotations.
	 */
	@Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);		
	}
	
	/**
	 * Evaluate ADD success of {@link UService#execute(Object, ApiMethod)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void successAdd() throws Exception {
		when(repository.findOne(entity.getId()))
			.thenReturn(null);
		doNothing().when(validator).isValid(entity, method);
		assertThat(service.execute(entity, method))
			.isEqualToComparingFieldByFieldRecursively(
				new ResponseEntity<>(
						new USuccessResponse<>(
								method, 
								entity.getId()),
						HttpStatus.CREATED));
	}
	
	/**
	 * Evaluate data null of {@link UService#execute(Object, ApiMethod)}.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void dataNull() 
			throws	Exception {
		try {
			doNothing().when(validator).isValid(entity, method);
			service.execute(null, method);
		} catch (Exception e) {
			assertThat(e)
			.isEqualToComparingFieldByFieldRecursively(
					new UException(ErrorCode.DATA_NULL));
		}
	}
	
	/**
	 * Evaluate validate fail of {@link UService#execute(Object, ApiMethod)}.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected=Exception.class)
	public void validateFail() throws Exception {
		doThrow(new Exception()).when(validator).isValid(entity, method);
		service.execute(entity, method);
	}
	
	/**
	 * Evaluate GET success of {@link UService#execute(Object)}
	 *  
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void successGet() throws Exception {
		assertThat(service.execute(new Object()))
		.isEqualToComparingFieldByFieldRecursively(
				new ResponseEntity<>(
						new USuccessResponse<>(
								ApiMethod.GET, 
								new Object()),
						HttpStatus.OK));
	}
}
