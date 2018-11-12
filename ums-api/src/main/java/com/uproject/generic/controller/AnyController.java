package com.uproject.generic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.view.ViewAnyEntity;
import com.uproject.generic.domain.utils.AnyConstant;
import com.uproject.generic.service.AnyService;
import com.uproject.library.ums.controller.UController;
import com.uproject.library.ums.controller.apimodel.UApiResource;
import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;
import com.uproject.library.ums.domain.response.UHandlerResponse;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.response.UValidatorResponse;
import com.uproject.library.ums.domain.util.ControllerConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * Controller for {@link AnyEntity}.
 * It provide CRUD operations over entity.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * 
 */
@Api(tags= {AnyConstant.CONTROLLER_NAME},
description=AnyConstant.CONTROLLER_DESCRIPTION)
@RestController
@RequestMapping(AnyConstant.PATH_TO_CONTROLLER)
public class AnyController 
implements UController<AnyEntity, Integer, ViewAnyEntity>{
	
	/** The any service. */
	@Autowired
	AnyService service;
	
	/**
	 * Gets data from database.
	 * It can return on element of data by id 
	 * or search and filter data by content.
	 * This call {@link AnyService#get(String, String, String, Pageable, PagedResourcesAssembler)}.
	 *
	 * @param id The identifier
	 * @param search The search
	 * @param filter The filter
	 * @param pageable The pageable
	 * @param assembler The assembler
	 * @return The response entity
	 * @throws UValidatorException 
	 * @throws IllegalAccessException 
	 * @throws UException the k exception.
	 */
	@ApiOperation(
			value= AnyConstant.GET_OPERATION_VALUE,
			notes= AnyConstant.GET_OPERATION_NOTE)
	@ApiImplicitParams({
	    @ApiImplicitParam(
	    		name = ControllerConstant.SEARCH_NAME, 
	    		dataType = ControllerConstant.SEARCH_DATA_TYPE,
	    		paramType = ControllerConstant.SEARCH_PARAM_TYPE,
	            value = ControllerConstant.SEARCH_VALUE,
	            required = ControllerConstant.SEARCH_REQUIRED),
	    @ApiImplicitParam(
	    		name = ControllerConstant.FILTER_NAME,
	    		dataType = ControllerConstant.FILTER_DATA_TYPE,
	    		paramType = ControllerConstant.FILTER_PARAM_TYPE,
	            value = ControllerConstant.FILTER_VALUE,
	            required = ControllerConstant.FILTER_REQUIRED),
	    @ApiImplicitParam(
	    		name = ControllerConstant.PAGE_NAME, 
	    		dataType = ControllerConstant.PAGE_DATA_TYPE,
	    		paramType = ControllerConstant.PAGE_PARAM_TYPE,
	            value = ControllerConstant.PAGE_VALUE,
	            required = ControllerConstant.PAGE_REQUIRED),
	    @ApiImplicitParam(
	    		name = ControllerConstant.SIZE_NAME, 
	    		dataType = ControllerConstant.SIZE_DATA_TYPE, 
	    		paramType = ControllerConstant.SIZE_PARAM_TYPE,
	            value = ControllerConstant.SIZE_VALUE,
	            required = ControllerConstant.SIZE_REQUIRED),
	    @ApiImplicitParam(
	    		name = ControllerConstant.SORT_NAME,
	    		allowMultiple = ControllerConstant.SORT_ALLOW_MULTIPLE, 
	    		dataType = ControllerConstant.SORT_DATA_TYPE,
	    		paramType = ControllerConstant.SORT_PARAM_TYPE,
	            value = ControllerConstant.SORT_VALUE,
	            required = ControllerConstant.SORT_REQUIRED)})
	@ApiResponses(value= {
			@ApiResponse(
					code= ControllerConstant.RESPONSE_SUCESS_CODE , 
					response= UApiResource.class,
					message = AnyConstant.GET_RESPONSE_SUCESS_MESSAGE),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_ERROR_CODE, 
					response= UHandlerResponse.class, 
					message = ControllerConstant.RESPONSE_ERROR_MESSAGE,
					responseHeaders= {
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_NAME_NAME,
									description=ControllerConstant.HEADER_ERROR_NAME_DESCRIPTION,
									response=String.class),
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_MESSAGE_NAME,
									description=ControllerConstant.HEADER_ERROR_MESSAGE_DESCRIPTION,
									response=String.class)
					})
					
	})
	@GetMapping(AnyConstant.PATH_TO_GET)
	@ResponseBody
	public ResponseEntity<Object> get(
			String search,
			String filter,
			Pageable pageable,
			PagedResourcesAssembler<ViewAnyEntity> assembler) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return service.get(search, filter, pageable, assembler);
	}

	/**
	 * Add new data to database. If data has same primary key 
	 * that already exist on database the request is rejected.
	 * Otherwise, call {@link AnyService#add(AnyEntity)}.
	 *
	 * @param data The data to be add.
	 * @return The response entity with data id created.
	 * @throws UException 
	 * @throws UValidatorException 
	 * @throws IllegalAccessException 
	 * @throws Exception The exception.
	 */
	@ApiOperation(
			value=	AnyConstant.POST_OPERATION_VALUE,
			notes=  AnyConstant.POST_OPERATION_NOTE)
	@ApiResponses(value= {
			@ApiResponse(
					code= ControllerConstant.RESPONSE_SUCESS_CODE, 
					response= USuccessResponse.class,
					message = AnyConstant.POST_RESPONSE_SUCESS_MESSAGE),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_ERROR_CODE, 
					response= UHandlerResponse.class, 
					message = ControllerConstant.RESPONSE_ERROR_MESSAGE,
					responseHeaders= {
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_NAME_NAME,
									description=ControllerConstant.HEADER_ERROR_NAME_DESCRIPTION,
									response=String.class),
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_MESSAGE_NAME,
									description=ControllerConstant.HEADER_ERROR_MESSAGE_DESCRIPTION,
									response=String.class)
					}),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_VALIDATOR_ERROR_CODE, 
					response= UValidatorResponse.class, 
					message = ControllerConstant.RESPONSE_VALIDATOR_ERROR_MESSAGE)	
	})
	@PostMapping(AnyConstant.PATH_TO_POST)
	@ResponseBody
	public ResponseEntity<Object> add(
			@RequestBody(required = AnyConstant.DATA_REQUIRED)
			@ApiParam(value = AnyConstant.DATA_VALUE,
		            required = AnyConstant.DATA_REQUIRED,
		            type=AnyConstant.DATA_TYPE)
			AnyEntity data) 
					throws 
						IllegalAccessException,	
						UException,
						UValidatorException {
	return service.add(data);
	}
	
	/**
	 * Update data on database.
	 * If data with same id on database has 
	 * active_ind different to 'Y' is reject.
	 * <p>It call {@link AnyService#update(AnyEntity)}. 
	 * 
	 * @param data The data to update.
	 * @param id The id of data to logically restore.
	 * @return The response entity with data id restored or updated.
	 * @throws UValidatorException 
	 * @throws UException 
	 * @throws IllegalAccessException 
	 * @throws Exception The exception.
	 */
	@ApiOperation(
			value=	AnyConstant.PUT_OPERATION_VALUE,
			notes=  AnyConstant.PUT_OPERATION_NOTE)
	@ApiResponses(value= {
			@ApiResponse(
					code= ControllerConstant.RESPONSE_SUCESS_CODE, 
					response= USuccessResponse.class,
					message = AnyConstant.PUT_RESPONSE_SUCESS_MESSAGE),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_ERROR_CODE, 
					response= UHandlerResponse.class, 
					message = ControllerConstant.RESPONSE_ERROR_MESSAGE,
					responseHeaders= {
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_NAME_NAME,
									description=ControllerConstant.HEADER_ERROR_NAME_DESCRIPTION,
									response=String.class),
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_MESSAGE_NAME,
									description=ControllerConstant.HEADER_ERROR_MESSAGE_DESCRIPTION,
									response=String.class)
					}),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_VALIDATOR_ERROR_CODE, 
					response= UValidatorResponse.class, 
					message = ControllerConstant.RESPONSE_VALIDATOR_ERROR_MESSAGE)	
	})
	@PutMapping(AnyConstant.PATH_TO_PUT)
	@ResponseBody
	public ResponseEntity<Object> update(
			@RequestBody(required=AnyConstant.DATA_REQUIRED)
			@ApiParam(
					value = AnyConstant.DATA_VALUE,
					type= AnyConstant.DATA_TYPE,
					required= AnyConstant.DATA_REQUIRED)
			AnyEntity data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return service.update(data);
	}
	
	/**
	 * Logically delete data on database.
	 * This call {@link AnyService#delete(String)}.
	 *
	 * @param id The id of data to logically delete.
	 * @return The response entity with data id deleted. 
	 * @throws Exception The exception
	 */
	@ApiOperation(
			value=	AnyConstant.DELETE_OPERATION_VALUE,
			notes=  AnyConstant.DELETE_OPERATION_NOTE)
	@ApiImplicitParams({
	    @ApiImplicitParam(
	    		name = AnyConstant.ID_NAME, 
	    		dataType = AnyConstant.ID_DATA_TYPE,
	    		paramType = AnyConstant.ID_PARAM_TYPE,
	            value = AnyConstant.ID_VALUE,
	            required = AnyConstant.ID_REQUIRED),
	    @ApiImplicitParam(
	    		name = AnyConstant.LOGICAL_NAME, 
	    		dataType = AnyConstant.LOGICAL_DATA_TYPE,
	    		paramType = AnyConstant.LOGICAL_PARAM_TYPE,
	            value = AnyConstant.LOGICAL_VALUE,
	            required = AnyConstant.LOGICAL_REQUIRED,
	            defaultValue= AnyConstant.LOGICAL_DEFAULT_VALUE)})
	@ApiResponses(value= {
			@ApiResponse(
					code= ControllerConstant.RESPONSE_SUCESS_CODE, 
					response= USuccessResponse.class,
					message = AnyConstant.DELETE_RESPONSE_SUCESS_MESSAGE),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_ERROR_CODE, 
					response= UHandlerResponse.class, 
					message = ControllerConstant.RESPONSE_ERROR_MESSAGE,
					responseHeaders= {
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_NAME_NAME,
									description=ControllerConstant.HEADER_ERROR_NAME_DESCRIPTION,
									response=String.class),
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_MESSAGE_NAME,
									description=ControllerConstant.HEADER_ERROR_MESSAGE_DESCRIPTION,
									response=String.class)
					}),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_VALIDATOR_ERROR_CODE, 
					response= UValidatorResponse.class, 
					message = ControllerConstant.RESPONSE_VALIDATOR_ERROR_MESSAGE)	
	})
	@DeleteMapping(AnyConstant.PATH_TO_DELETE)
	@ResponseBody
	public ResponseEntity<Object> delete(
			Integer id,
			boolean logical) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return service.delete(id,logical);
	}
	
	/**
	 * Logically restore data on database. 
	 * If data with id indicate on database has active_ind = 'N' 
	 *  it process and restore data. After data restore active_ind = 'Y'."
	 *  <p>It call {@link AnyService#restore(String)}.
	 * 
	 * @param data The data to update.
	 * @param id The id of data to logically restore.
	 * @return The response entity with data id restored or updated.
	 * @throws Exception The exception.
	 */
	@ApiOperation(
			value=	AnyConstant.PATCH_OPERATION_VALUE,
			notes=  AnyConstant.PATCH_OPERATION_NOTE)
	@ApiImplicitParams({
	    @ApiImplicitParam(
	    		name = AnyConstant.ID_NAME, 
	    		dataType = AnyConstant.ID_DATA_TYPE,
	    		paramType = AnyConstant.ID_PARAM_TYPE,
	            value = AnyConstant.ID_VALUE,
	            required = AnyConstant.ID_REQUIRED)})
	@ApiResponses(value= {
			@ApiResponse(
					code= ControllerConstant.RESPONSE_SUCESS_CODE, 
					response= USuccessResponse.class,
					message = AnyConstant.PATCH_RESPONSE_SUCESS_MESSAGE),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_ERROR_CODE, 
					response= UHandlerResponse.class, 
					message = ControllerConstant.RESPONSE_ERROR_MESSAGE,
					responseHeaders= {
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_NAME_NAME,
									description=ControllerConstant.HEADER_ERROR_NAME_DESCRIPTION,
									response=String.class),
							@ResponseHeader(
									name=ControllerConstant.HEADER_ERROR_MESSAGE_NAME,
									description=ControllerConstant.HEADER_ERROR_MESSAGE_DESCRIPTION,
									response=String.class)
					}),
			@ApiResponse(
					code= ControllerConstant.RESPONSE_VALIDATOR_ERROR_CODE, 
					response= UValidatorResponse.class, 
					message = ControllerConstant.RESPONSE_VALIDATOR_ERROR_MESSAGE)	
	})
	@PatchMapping(AnyConstant.PATH_TO_PATCH)
	@ResponseBody
	public ResponseEntity<Object> restore(
			Integer id)
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
	return service.restore(id);
	}
}
