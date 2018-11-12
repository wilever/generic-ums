package com.uproject.generic.service.any;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.Test;

import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.service.AnyService;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.util.ApiMethod;

/**
 * Unit test for {@link AnyService#processData(AnyEntity, ApiMethod)}.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
public class ProcessDataTest {
	
	/** The service. */
	private AnyService service = new AnyService();
	
	/** The entity. */
	private AnyEntity entity = new AnyEntity(0);
	
	/**
	 * Data processed  successfully for {@link ApiMethod#ADD}.
	 * @throws UException 
	 */
	@Test
	public void Add() throws UException {
		entity = service.processData(entity, ApiMethod.ADD);
		assertThat(entity.getActiveInd()).isEqualTo("Y");
		assertThat(entity.getRowCreatedBy()).isNotNull();
		assertThat(entity.getRowCreatedDate()).isNotNull();
	}
	
	/**
	 * Data processed  successfully for {@link ApiMethod#UPDATE}.
	 * @throws UException 
	 */
	@Test
	public void Update() throws UException {
		entity = service.processData(entity, ApiMethod.UPDATE);
		assertThat(entity.getRowChangedBy()).isNotNull();
		assertThat(entity.getRowChangedDate()).isNotNull();
	}
	
	/**
	 * Data processed  successfully for {@link ApiMethod#DELETE}.
	 * @throws UException 
	 */
	@Test
	public void Delete() throws UException {
		entity.setActiveInd("Y");
		entity = service.processData(entity, ApiMethod.DELETE);
	}
	
	/**
	 * Data processed  successfully for {@link ApiMethod#DELETE}.
	 * @throws UException 
	 */
	@Test
	public void DeleteLogical() throws UException {
		entity.setActiveInd("Y");
		entity = service.processData(entity, ApiMethod.DELETE_LOGICAL);
		assertThat(entity.getActiveInd()).isEqualTo("N");
		assertThat(entity.getRowExpiryDate()).isNotNull();
		assertThat(entity.getRowChangedBy()).isNotNull();
		assertThat(entity.getRowChangedDate()).isNotNull();
	}
	
	/**
	 * Data processed  successfully for {@link ApiMethod#RESTORE}.
	 * @throws UException 
	 */
	@Test
	public void Restore() throws UException {
		entity.setActiveInd("N");
		entity.setRowExpiryDate(new Date(System.currentTimeMillis()));
		entity = service.processData(entity, ApiMethod.RESTORE);
		assertThat(entity.getActiveInd()).isEqualTo("Y");
		assertThat(entity.getRowExpiryDate()).isNull();
		assertThat(entity.getRowChangedBy()).isNotNull();
		assertThat(entity.getRowChangedDate()).isNotNull();
	}
}
