package com.uproject.generic.domain.utils;

/**
 * The Class AnyDetailControllerConstant for SwaggerDocumentation.
 */
public class AnyDetailConstant {
	
	/**
	 * It cannot be initialized
	 */
	private AnyDetailConstant() {
		throw new IllegalStateException("Utility class");
	}
	
	// CONTROLLER_DATA
	
	/** The Constant PATH_TO_CONTROLLER. */
	public static final String PATH_TO_CONTROLLER = "/anydetailcontroller";
	
	/** The Constant CONTROLLER_NAME. */
	public static final String CONTROLLER_NAME = "AnyDetailController";
	
	/** The Constant CONTROLLER_DESCRIPTION. */
	public static final String CONTROLLER_DESCRIPTION = "Allow to perform CRUD operations over ANY_ENTITY_DETAIL";
	
	//GET_OPERATION
	
	/** The Constant PATH_TO_GET. */
	public static final String PATH_TO_GET = "";
	
	/** The Constant GET_OPERATION_VALUE. */
	public static final String GET_OPERATION_VALUE = "Retrive data from database";
	
	/** The Constant GET_OPERATION_NOTE. */
	public static final String GET_OPERATION_NOTE = 	"It can return data by " 
														+ "search and filter parameters.";
	
	/** The Constant GET_RESPONSE_SUCESS_MESSAGE. */
	public static final String GET_RESPONSE_SUCESS_MESSAGE = "Data retrieved successfully!";

	//POST_OPERATION
	
	/** The Constant PATH_TO_POST. */
	public static final String PATH_TO_POST = "";
	
	/** The Constant POST_OPERATION_VALUE. */
	public static final String POST_OPERATION_VALUE = "Add new data to database";
	
	/** The Constant POST_OPERATION_NOTE. */
	public static final String POST_OPERATION_NOTE = 	"Add new data to database. "
														+ "After save active_ind = 'Y'. "
														+ "If data has a primary key "
														+ "that already exist on database the request is rejected. "
														+ "Otherwise, it process and save data. "
														+ "If you try to add data with active='N' "
														+ "then return validation error.";
	
	
	
	/** The Constant POST_RESPONSE_SUCESS_MESSAGE. */
	public static final String POST_RESPONSE_SUCESS_MESSAGE = "Data added successfully!";
	
	
	//PUT_OPERATION
	
	/** The Constant PATH_TO_PUT. */
	public static final String PATH_TO_PUT = "";

	/** The Constant PUT_OPERATION_VALUE. */
	public static final String PUT_OPERATION_VALUE = "Update data on database";
	
	/** The Constant PUT_OPERATION_NOTE. */
	public static final String PUT_OPERATION_NOTE = 	"If data on database has "
											+ "active_ind = 'Y' "
											+ "then it process and update data.";
	
	/** The Constant PUT_RESPONSE_SUCESS_MESSAGE. */
	public static final String PUT_RESPONSE_SUCESS_MESSAGE = "Data updated successfully!";
	
	
	// DELETE_OPERATION
	
	/** The Constant PATH_TO_DELETE. */
	public static final String PATH_TO_DELETE = "";
	
	/** The Constant DELETE_OPERATION_VALUE. */
	public static final String DELETE_OPERATION_VALUE = "Delete data on database";
	
	/** The Constant DELETE_OPERATION_NOTE. */
	public static final String DELETE_OPERATION_NOTE = 	"If data "
														+ "on database has active_ind = 'Y' "
														+ "then it process data. "
														+ "Otherwise, request is reject. "
														+ "Data can be delete totally(false) o logically(true) "
														+ "wiht boolean parameter: \"logical\"";
	
	/** The Constant DELETE_RESPONSE_SUCESS_MESSAGE. */
	public static final String DELETE_RESPONSE_SUCESS_MESSAGE = "Data deleted successfully! / Data logical deleted successfully!";
	
	//PATCH_OPERATION
	
	/** The Constant PATH_TO_PATCH. */
	public static final String PATH_TO_PATCH = "";
	
	/** The Constant PATCH_OPERATION_VALUE. */
	public static final String PATCH_OPERATION_VALUE = "Logical restore data on database";
	
	/** The Constant PATCH_OPERATION_NOTE. */
	public static final String PATCH_OPERATION_NOTE = 	"If data on database has active_ind = 'N' "+
														"then it process and restore data. "+
														"After data restore active_ind = 'Y'.";
	
	/** The Constant PATCH_RESPONSE_SUCESS_MESSAGE. */
	public static final String PATCH_RESPONSE_SUCESS_MESSAGE = "Data restored successfully!";
	
	// DEFAULT_PARAMETER
	
	
	/** The Constant DATA_DESCRIPTION. */
	public static final String DATA_VALUE = "data to be process";
	
	/** The Constant DATA_TYPE. */
	public static final String DATA_TYPE = "AnyEntity";
	
	/** The Constant DATA_REQUIRED. */
	public static final boolean DATA_REQUIRED = true;
	
	/** The Constant ID_NAME. */
	public static final String ANY_ID_NAME = "anyId";
	
	/** The Constant ID_DESCRIPTION. */
	public static final String ANY_ID_VALUE = "AnyId of data to be process";
	
	/** The Constant ID_TYPE. */
	public static final String ANY_ID_DATA_TYPE = "String";
	
	/** The Constant ID_REQUIRED. */
	public static final boolean ANY_ID_REQUIRED = true;
	
	/** The Constant ANY_ID_PARAM_TYPE. */
	public static final String ANY_ID_PARAM_TYPE = "query";
	
	/** The Constant ID_NAME. */
	public static final String ANY_ENTITY_DETAIL_ID_NAME = "anyEntityDetailId";
	
	/** The Constant ID_DESCRIPTION. */
	public static final String ANY_ENTITY_DETAIL_ID_VALUE = "AnyEntityDetailId of data to be process";
	
	/** The Constant ID_TYPE. */
	public static final String ANY_ENTITY_DETAIL_ID_DATA_TYPE = "String";
	
	/** The Constant ID_REQUIRED. */
	public static final boolean ANY_ENTITY_DETAIL_ID_REQUIRED = true;
	
	/** The Constant ANY_ID_PARAM_TYPE. */
	public static final String ANY_ENTITY_DETAIL_ID_PARAM_TYPE = "query";
	
	/** The Constant LOGICAL_NAME. */
	public static final String LOGICAL_NAME = "logical";
	
	/** The Constant LOGICAL_DESCRIPTION. */
	public static final String LOGICAL_VALUE = 	"Logical=true mean Logical detele, "
														+ "Logical=false mean Total delete";
	
	/** The Constant LOGICAL_TYPE. */
	public static final String LOGICAL_DATA_TYPE = "boolean";
	
	/** The Constant LOGICAL_DEFAULT_VALUE. */
	public static final String LOGICAL_DEFAULT_VALUE = "true";
	
	/** The Constant LOGICAL_PARAM_TYPE. */
	public static final String LOGICAL_PARAM_TYPE = "query";
	
	/** The Constant LOGICAL_REQUIRED. */
	public static final boolean LOGICAL_REQUIRED = false;

}
