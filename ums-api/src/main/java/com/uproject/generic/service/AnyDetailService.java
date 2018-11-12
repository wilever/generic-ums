package com.uproject.generic.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uproject.generic.domain.repository.AnyDetailRepository;
import com.uproject.generic.domain.repository.ViewAnyDetailRepository;
import com.uproject.generic.domain.repository.entity.AnyEntityDetail;
import com.uproject.generic.domain.repository.entity.AnyEntityDetailPK;
import com.uproject.generic.domain.repository.entity.view.ViewAnyDetail;
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
public class AnyDetailService 
implements UService<AnyEntityDetail, AnyEntityDetailPK, ViewAnyDetail> {
	
	/** The view repository. */
	@Autowired
	ViewAnyDetailRepository viewRepository;
	
	/** The repository. */
	@Autowired
	AnyDetailRepository repository;
	
	/** The validator. */
	@Autowired
	UValidator validator;

	@Override
	public ResponseEntity<Object> get(
			String search, 
			String filter, 
			Pageable pageable,
			PagedResourcesAssembler<ViewAnyDetail> assembler) 
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
			AnyEntityDetail data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {

		processPk(data);
	return execute(data, ApiMethod.ADD);
	}

	@Override
	public ResponseEntity<Object> update(
			AnyEntityDetail data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
		processPk(data);
	return execute(data, ApiMethod.UPDATE);
	}

	@Override
	public ResponseEntity<Object> delete(
			AnyEntityDetailPK id,
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
			AnyEntityDetailPK id) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return execute(
				repository.findOne(id), 
				ApiMethod.RESTORE);
	}

	@Override
	public AnyEntityDetailPK save(
			AnyEntityDetail data) {
		repository.save(data);
	return data.getId();
	}
	
	@Override
	public AnyEntityDetailPK delete(AnyEntityDetail data) {
		repository.delete(data);
		return data.getId();
	}

	@Override
	public void validate(
			AnyEntityDetail data, 
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
			AnyEntityDetail data) 
					throws UException {
			data = repository.findOne(data.getId());
			if (data!=null) {
				throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
			}
	}

	@Override
	public void preUpdate(
			AnyEntityDetail data) 
					throws UException {
		data = repository.findOne(data.getId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public void preDelete(
			AnyEntityDetail data) 
					throws UException {
		data = repository.findOne(data.getId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}
	
	@Override
	public void preDeleteLogical(
			AnyEntityDetail data) 
					throws UException {
		data = repository.findOne(data.getId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public void preRestore(
			AnyEntityDetail data) 
					throws UException {
		data = repository.findOne(data.getId());
		if (data==null) {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);	
		}
	}

	@Override
	public AnyEntityDetail processAdd(
			AnyEntityDetail data) throws UException {
		data.setActiveInd("Y");
		data.setRowCreatedBy("CREATED_USER");
		data.setRowCreatedDate(new Date(System.currentTimeMillis()));
	return data;
	}

	@Override
	public AnyEntityDetail processUpdate(
			AnyEntityDetail data) {
		data.setRowChangedBy("CHANGED_USER");
		data.setRowChangedDate(new Date(System.currentTimeMillis()));
	return data;
	}
	
	@Override
	public AnyEntityDetail processDelete(AnyEntityDetail data) {
		return data;
	}

	@Override
	public AnyEntityDetail processDeleteLogical(
			AnyEntityDetail data) {
		data.setActiveInd("N");
		data.setRowExpiryDate(new Date(System.currentTimeMillis()));
		data.setRowChangedBy("DELETED_USER");
		data.setRowChangedDate(data.getRowExpiryDate());
	return data;
	}

	@Override
	public AnyEntityDetail processRestore(
			AnyEntityDetail data) {
		data.setActiveInd("Y");
		data.setRowExpiryDate(null);
		data.setRowChangedBy("RESTORED_USER");
		data.setRowChangedDate(new Date(System.currentTimeMillis()));
	return data;
	}

	/**
	 * Set primary key data
	 * 
	 * @param data
	 * @throws UException
	 */
	public void processPk(AnyEntityDetail data) throws UException {
		if (data.getAnyId()!=null && data.getAnyEntityDetailId()!=null) {
			if (data.getId()!=null) {
				data.getId().setAnyId(data.getAnyId());
				data.getId().setAnyEntityDetailId(data.getAnyEntityDetailId());
				data.setId(data.getId());
			}else {
				data.setId(
					new AnyEntityDetailPK(
							data.getAnyId(),
							data.getAnyEntityDetailId()));	
			}
		} else {
			throw new UException(ErrorCode.PK_NOT_AVAILABLE);
		}	
	}
}