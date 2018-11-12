package com.uproject.generic.domain.utils;

/**
 * The Class ApplicationConstant.
 */
public class ApplicationConstant {
	
	/**
	 * It cannot be initialized.
	 */
	private ApplicationConstant() {
		throw new IllegalStateException("Utility class");
	}

	/** The Constant BASE_PACKAGE. */
	public static final String BASE_PACKAGE = "com.uproject";
	
	/** The Constant UMS_NAME. */
	public static final String UMS_NAME = ".generic";
	
	/** The Constant UMS_LIBRARY_NAME. */
	public static final String UMS_LIBRARY_NAME = ".library.ums";
	
	/** The Constant GENERIC_REPO. */
	public static final String GENERIC_REPO = BASE_PACKAGE+UMS_NAME+".domain.repository";
	
	/** The Constant GENERIC_ENTITY. */
	public static final String GENERIC_ENTITY = GENERIC_REPO+".entity";
	
	/** The Constant LIBRARY_REPO. */
	public static final String LIBRARY_REPO = BASE_PACKAGE+UMS_LIBRARY_NAME+".domain.repository";
	
	/** The Constant LIBRARY_ENTITY. */
	public static final String LIBRARY_ENTITY = LIBRARY_REPO+".entity";
	
}
