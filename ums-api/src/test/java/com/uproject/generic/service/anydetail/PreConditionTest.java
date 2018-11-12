package com.uproject.generic.service.anydetail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uproject.generic.domain.repository.AnyDetailRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.AnyEntityDetail;
import com.uproject.generic.service.AnyDetailService;
import com.uproject.generic.service.AnyService;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;

/**
 * Unit test for {@link AnyService#processData(AnyEntity, ApiMethod)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
public class PreConditionTest {
	
	/** The service. */
	@InjectMocks
	private AnyDetailService service;
	
	/** The repository. */
	@Mock
	private AnyDetailRepository repository;

	/** The entity. */
	private AnyEntityDetail entity = new AnyEntityDetail(0,0);
	
	/**
	 * Call mock annotations.
	 */
	@Before
	public void beforeEachTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Adds the success.
	 *
	 * @throws UException the u exception
	 */
	@Test
	public void addSuccess() throws UException {
			when(repository.findOne(entity.getId()))
				.thenReturn(null);
			service.preCondition(entity, ApiMethod.ADD);
	}
	
	/**
	 * Adds the PK not available.
	 */
	@Test
	public void addPKNotAvailable() {
		try {
			when(repository.findOne(entity.getId()))
				.thenReturn(entity);
			service.preCondition(entity, ApiMethod.ADD);
		} catch (UException e) {
			assertThat(e)
				.isEqualToComparingFieldByFieldRecursively(
						new UException(ErrorCode.PK_NOT_AVAILABLE));
		}
	}
	
	/**
	 * Update success.
	 *
	 * @throws UException the u exception
	 */
	@Test
	public void updateSuccess() throws UException {
		entity.setActiveInd("Y");
		when(repository.findOne(entity.getId()))
			.thenReturn(entity);
		service.preCondition(entity, ApiMethod.UPDATE);
	}
	
	/**
	 * Update fail PK not available.
	 */
	@Test
	public void updateFailPKNotAvailable() {
		try {
			when(repository.findOne(entity.getId()))
				.thenReturn(null);
			service.preCondition(entity, ApiMethod.UPDATE);
		} catch (UException e) {
			assertThat(e)
				.isEqualToComparingFieldByFieldRecursively(
						new UException(ErrorCode.PK_NOT_AVAILABLE));
		}
	}
	
	/**
	 * Delete success.
	 *
	 * @throws UException the u exception
	 */
	@Test
	public void deleteSuccess() throws UException {
		when(repository.findOne(entity.getId()))
			.thenReturn(entity);
		service.preCondition(entity, ApiMethod.DELETE);
	}
	
	/**
	 * Delete PK not available.
	 */
	@Test
	public void deletePKNotAvailable() {
		try {
			when(repository.findOne(entity.getId()))
				.thenReturn(null);
			service.preCondition(entity, ApiMethod.DELETE);
		} catch (UException e) {
			assertThat(e)
				.isEqualToComparingFieldByFieldRecursively(
						new UException(ErrorCode.PK_NOT_AVAILABLE));
		}
	}
	
	/**
	 * Delete logical success.
	 *
	 * @throws UException the u exception
	 */
	@Test
	public void deleteLogicalSuccess() throws UException {
		entity.setActiveInd("Y");
		when(repository.findOne(entity.getId()))
			.thenReturn(entity);
		service.preCondition(entity, ApiMethod.DELETE_LOGICAL);
	}
	
	/**
	 * Delete logical PK not available.
	 */
	@Test
	public void deleteLogicalPKNotAvailable() {
		try {
			when(repository.findOne(entity.getId()))
				.thenReturn(null);
			service.preCondition(entity, ApiMethod.DELETE_LOGICAL);
		} catch (UException e) {
			assertThat(e)
				.isEqualToComparingFieldByFieldRecursively(
						new UException(ErrorCode.PK_NOT_AVAILABLE));
		}
	}
	
	/**
	 * Restore success.
	 *
	 * @throws UException the u exception
	 */
	@Test
	public void restoreSuccess() throws UException {
		entity.setActiveInd("N");
		when(repository.findOne(entity.getId()))
			.thenReturn(entity);
		service.preCondition(entity, ApiMethod.RESTORE);
	}
	
	/**
	 * Restore PK not available.
	 */
	@Test
	public void restorePKNotAvailable() {
		try {
			when(repository.findOne(entity.getId()))
				.thenReturn(null);
			service.preCondition(entity, ApiMethod.RESTORE);
		} catch (UException e) {
			assertThat(e)
				.isEqualToComparingFieldByFieldRecursively(
						new UException(ErrorCode.PK_NOT_AVAILABLE));
		}
	}
}
