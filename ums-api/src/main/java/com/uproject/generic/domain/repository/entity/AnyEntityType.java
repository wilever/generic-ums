package com.uproject.generic.domain.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * AnyEntityType of AnyEntity
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@ApiModel(value="Any entity type")
@Entity
@Table(name="any_entity_type")
public class AnyEntityType extends MetaData{

	/** The any id. */
	@ApiModelProperty(
			value="The type of entity base on ID",
			example="0",
			required=true)
	@Id
	@Column(name="any_type_id")
	
	private Integer anyTypeId;
	
	/** The field. */
	@ApiModelProperty(value="The name of type of entity")
	@Column(name="type_name")
	private String typeName;
	
	/**
	 * Instantiates a new any entity.
	 */
	public AnyEntityType() {
		
	}

	public Integer getAnyTypeId() {
		return anyTypeId;
	}

	public void setAnyTypeId(Integer anyTypeId) {
		this.anyTypeId = anyTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
