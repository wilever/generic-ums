package com.uproject.generic.service.any;

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

import com.uproject.generic.domain.repository.AnyRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.service.AnyService;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.service.UValidator;

// TODO: Auto-generated Javadoc
/**
 * Unit test for {@link AnyService#delete(String)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
public class DeleteTest {

	/** The service. */
	@InjectMocks
	private AnyService service;
	
	/** The repository. */
	@Mock
	private AnyRepository repository;
	
	/** The validator. */
	@Mock
	private UValidator validator;
	
	/** The entity. */
	private AnyEntity entity = new AnyEntity(0);
	
	/** The method to test: {@link ApiMethod#DELETE}. */
	private ApiMethod method = ApiMethod.DELETE;
	
	/**  The logical parameter. */
	private boolean logical = false;
	
	/**
	 * Call mock annotations.
	 */
	@Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);		
	}
	
	/**
	 * Success, {@link #method} return expected response.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void Success() throws Exception {
		doNothing().when(validator).isValid(entity, method);
		when(repository.findOne(entity.getAnyId())).
			thenReturn(entity);
		assertThat(service.delete(entity.getAnyId(),logical)).
			isEqualToComparingFieldByFieldRecursively(
				new ResponseEntity<>(
					new USuccessResponse<>(
							method, 
							entity.getAnyId()),
					HttpStatus.OK));
	}
	
	/**
	 * PK not available, {@link #method} return expected exception.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UException.class)
	public void PKNotAvailable() throws Exception {
		when(repository.findOne(entity.getAnyId())).
			thenReturn(null);
		service.delete(entity.getAnyId(),logical);
	}
	
	/**
	 * Validate fail, {@link #method} return expected exception.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=Exception.class)
	public void ValidateFailAndServerError() throws Exception {
		doThrow(new Exception()).when(validator).isValid(entity, method);
		service.delete(entity.getAnyId(),logical);
	}
}
