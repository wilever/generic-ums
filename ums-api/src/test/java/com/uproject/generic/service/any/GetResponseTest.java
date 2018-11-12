package com.uproject.generic.service.any;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.service.AnyService;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.util.ApiMethod;

/**
 * Unit test for {@link AnyService#execute(AnyEntity, ApiMethod)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
public class GetResponseTest {

	/** The service. */
	private AnyService service = new AnyService();
	
	
	/** The entity. */
	private AnyEntity entity = new AnyEntity(0);
	
	/**
	 * Test.
	 *
	 * @param content the content
	 * @param method the method
	 * @param status the status
	 */
	private void test(
			Object content, 
			ApiMethod method, 
			HttpStatus status) {
		if (method.equals(ApiMethod.GET)) {
			assertThat(service.getResponse(content, method))
			.isEqualToComparingFieldByFieldRecursively(
					new ResponseEntity<>(
								content,
								status));	
		} else {
			assertThat(service.getResponse(content, method))
			.isEqualToComparingFieldByFieldRecursively(
					new ResponseEntity<>(
							new USuccessResponse<>(method,content),
							status));
		}
	}
	
	/**
	 * Gets the ok.
	 *
	 * @return the ok
	 */
	@Test
	public void getOk() {
		Object content = new Object();
		test(content, 
			ApiMethod.GET, 
			HttpStatus.OK);
	}

	/**
	 * Gets the no content.
	 *
	 * @return the no content
	 */
	@Test
	public void getNoContent() {
		test(null, 
			ApiMethod.GET, 
			HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Adds the.
	 */
	@Test
	public void add() {
		test(entity.getAnyId(), 
			ApiMethod.ADD,
			HttpStatus.CREATED);
	}
	
	/**
	 * Update.
	 */
	@Test
	public void update() {
		test(entity.getAnyId(), 
			ApiMethod.UPDATE,
			HttpStatus.OK);
	}
	
	/**
	 * Delete.
	 */
	@Test
	public void delete() {
		test(entity.getAnyId(), 
			ApiMethod.DELETE, 
			HttpStatus.OK);
	}
	
	/**
	 * Delete logical.
	 */
	@Test
	public void deleteLogical() {
		test(entity.getAnyId(), 
			ApiMethod.DELETE_LOGICAL, 
			HttpStatus.OK);
	}
	
	/**
	 * Restore.
	 */
	@Test
	public void restore() {
		test(entity.getAnyId(), 
			ApiMethod.RESTORE, 
			HttpStatus.OK);
	}
}
