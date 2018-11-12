package com.uproject.generic.domain.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;

/**
 * The Class AnyEntityDetailPK.
 */
@ApiModel(value="Any entity details PK")
@Embeddable
public class AnyEntityDetailPK implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The any id. */
	@Column(name= "any_id", nullable=false, insertable=false, updatable=false)
	private Integer anyId;
	
	/** The any entity detail id. */
	@Column(name= "any_entity_detail_id")
	private Integer anyEntityDetailId;

	/**
	 * Instantiates a new any entity detail PK.
	 */
	public AnyEntityDetailPK() {
		
	}
	
	/**
	 * Instantiates a new any entity detail PK.
	 * 
	 * @param anyId
	 * @param anyEntityDetailId
	 */
	public AnyEntityDetailPK(Integer anyId, Integer anyEntityDetailId) {
		this.anyId = anyId;
		this.anyEntityDetailId = anyEntityDetailId;
	}

	/**
	 * Gets the any id.
	 *
	 * @return the any id
	 */
	public Integer getAnyId() {
		return anyId;
	}

	/**
	 * Sets the any id.
	 *
	 * @param anyId the new any id
	 */
	public void setAnyId(Integer anyId) {
		this.anyId = anyId;
	}

	/**
	 * Gets the any entity detail id.
	 *
	 * @return the any entity detail id
	 */
	public Integer getAnyEntityDetailId() {
		return anyEntityDetailId;
	}

	/**
	 * Sets the any entity detail id.
	 *
	 * @param anyEntityDetailId the new any entity detail id
	 */
	public void setAnyEntityDetailId(Integer anyEntityDetailId) {
		this.anyEntityDetailId = anyEntityDetailId;
	}

}
