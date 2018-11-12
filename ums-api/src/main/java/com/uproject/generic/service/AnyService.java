package com.uproject.generic.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uproject.generic.domain.repository.AnyRepository;
import com.uproject.generic.domain.repository.ViewAnyRepository;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.view.ViewAnyEntity;
import com.uproject.library.ums.controller.UResource;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;
import com.uproject.library.ums.domain.repository.specification.USpecification;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.service.UService;
import com.uproject.library.ums.service.UValidator;

/**
 * Service to validate and process data 
 * before save on database. 
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@Service
public class AnyService 
implements UService<AnyEntity, Integer, ViewAnyEntity> {
	
	/** The view repository. */
	@Autowired
	ViewAnyRepository viewRepository;
	
	/** The entity repository. */
	@Autowired
	AnyRepository repository;
	
	/** The validator. */
	@Autowired
	UValidator validator;

	@Override
	public ResponseEntity<Object> get(
			String search, 
			String filter, 
			Pageable pageable,
			PagedResourcesAssembler<ViewAnyEntity> assembler) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return execute(
				UResource.getResource(
							assembler, 
							viewRepository.findAll(
									USpecification.getSpecifications(search, filter),
									pageable)));
									
	}

	@Override
	public ResponseEntity<Object> add(
			AnyEntity data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return execute(data, ApiMethod.ADD);
	}

	@Override
	public ResponseEntity<Object> update(
			AnyEntity data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return execute(data, ApiMethod.UPDATE);
	}

	@Override
	public ResponseEntity<Object> delete(
			Integer id,
			boolean logical) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
		if (logical) {
			return execute(
					repository.findOne(id),
					ApiMethod.DELETE_LOGICAL);		
		}else {
			return execute(
					repository.findOne(id),
					ApiMethod.DELETE);
		}
	}

	@Override
	public ResponseEntity<Object> restore(
			Integer id) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return execute(
				repository.findOne(id), 
				ApiMethod.RESTORE);
	}

	@Override
	public Integer save(
			AnyEntity data) {
		repository.save(data);
	return data.getAnyId();
	}
	
	@Override
	public Integer delete(AnyEntity data) {
		repository.delete(data);
		return data.getAnyId();
	}

	@Override
	public void validate(
			AnyEntity data, 
			ApiMethod method) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	validator.isValid(data, method);
	}
	
	@Override
	public void preGet() 
			throws UException {
	}

	@Override
	public void preAdd(
			AnyEntity data) 
					throws UException {
		if (data.getAnyId()!=null) {
			data = repository.findOne(data.getAnyId());
			if (data!=null) {
				throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
			}
		}else {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);
		}
	}

	@Override
	public void preUpdate(
			AnyEntity data) 
					throws UException {
		data = repository.findOne(data.getAnyId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public void preDelete(
			AnyEntity data) 
					throws UException {
		data = repository.findOne(data.getAnyId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}
	
	@Override
	public void preDeleteLogical(
			AnyEntity data) 
					throws UException {
		data = repository.findOne(data.getAnyId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public void preRestore(
			AnyEntity data) 
					throws UException {
		data = repository.findOne(data.getAnyId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public AnyEntity processAdd(
			AnyEntity data) {
		data.setActiveInd("Y");
		data.setRowCreatedBy("CREATED_USER");
		data.setRowCreatedDate(new Date(System.currentTimeMillis()));
	return data;
	}

	@Override
	public AnyEntity processUpdate(
			AnyEntity data) {
		data.setRowChangedBy("CHANGED_USER");
		data.setRowChangedDate(new Date(System.currentTimeMillis()));
	return data;
	}
	
	@Override
	public AnyEntity processDelete(AnyEntity data) {
		return data;
	}

	@Override
	public AnyEntity processDeleteLogical(
			AnyEntity data) {
		data.setActiveInd("N");
		data.setRowExpiryDate(new Date(System.currentTimeMillis()));
		data.setRowChangedBy("DELETED_USER");
		data.setRowChangedDate(data.getRowExpiryDate());
	return data;
	}

	@Override
	public AnyEntity processRestore(
			AnyEntity data) {
		data.setActiveInd("Y");
		data.setRowExpiryDate(null);
		data.setRowChangedBy("RESTORED_USER");
		data.setRowChangedDate(new Date(System.currentTimeMillis()));
	return data;
	}

}