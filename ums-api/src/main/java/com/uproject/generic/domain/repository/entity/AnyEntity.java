package com.uproject.generic.domain.repository.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * AnyEntity to map data on database to java object.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@ApiModel(value="Any entity")
@Entity
@Table(name="any_entity")
public class AnyEntity extends MetaData{

	/** The any id. */
	@ApiModelProperty(
			value="Unique identifier for any entity",
			example="0",
			required=true)
	@Id
	@Column(name="any_id")
	private Integer anyId;
	
	/** The any type id. */
	@Column(name="any_type_id")
	private Integer anyTypeId;
	
	/** The any data name. */
	@ApiModelProperty(value="The any data name")
	@Column(name="any_name")
	private String anyName;
	
	/** The field. */
	@ApiModelProperty(value="The field")
	@Column(name="field")
	private String field;
	
	/** The description field. */
	@ApiModelProperty(value="The description field")
	@Column(name="description_field")
	private String descriptionField;
	
	/** The another entities. */
	@JsonIgnore
	@OneToMany(
			fetch=FetchType.EAGER,
			cascade=CascadeType.REMOVE,
			mappedBy="anyEntity")
	private Set<AnyEntityDetail> anyEntityDetails;
	
	/**
	 * Instantiates a new any entity.
	 */
	public AnyEntity() {
	}
	
	/**
	 * Instantiates a new any entity.
	 *
	 * @param anyId The any id
	 */
	public AnyEntity(Integer anyId) {
		this.anyId = anyId;
	}
	
	/**
	 * Instantiates a new any entity.
	 * This constructor exist to perform test.
	 *
	 * @param anyId The any id
	 * @param field The field
	 * @param descriptionField The description field
	 */
	public AnyEntity(Integer anyId, String field, String descriptionField) {
		this.anyId = anyId;
		this.field = field;
		this.descriptionField = descriptionField;
	}

	/**
	 * Gets the any id.
	 *
	 * @return The any id
	 */
	public Integer getAnyId() {
		return anyId;
	}

	/**
	 * Sets the any id.
	 *
	 * @param anyId The new any id
	 */
	public void setAnyId(Integer anyId) {
		this.anyId = anyId;
	}

	/**
	 * Gets the field.
	 *
	 * @return The field
	 */
	public String getField() {
		return field;
	}
	
	/**
	 * Sets the field.
	 *
	 * @param field The new field
	 */
	public void setField(String field) {
		this.field = field;
	}
	
	/**
	 * Gets the description field.
	 *
	 * @return The description field
	 */
	public String getDescriptionField() {
		return descriptionField;
	}
	
	/**
	 * Sets the description field.
	 *
	 * @param descriptionField The new description field
	 */
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}

	/**
	 * Gets the any entity details.
	 *
	 * @return the any entity details
	 */
	public Set<AnyEntityDetail> getAnyEntityDetails() {
		return anyEntityDetails;
	}

	/**
	 * Sets the any entity details.
	 *
	 * @param anyEntityDetails the new any entity details
	 */
	public void setAnyEntityDetails(Set<AnyEntityDetail> anyEntityDetails) {
		this.anyEntityDetails = anyEntityDetails;
	}

	/**
	 * Gets the any type id.
	 *
	 * @return the any type id
	 */
	public Integer getAnyTypeId() {
		return anyTypeId;
	}

	/**
	 * Sets the any type id.
	 *
	 * @param anyTypeId the new any type id
	 */
	public void setAnyTypeId(Integer anyTypeId) {
		this.anyTypeId = anyTypeId;
	}

	/**
	 * Gets the any name.
	 *
	 * @return the any name
	 */
	public String getAnyName() {
		return anyName;
	}

	/**
	 * Sets the any name.
	 *
	 * @param anyName the new any name
	 */
	public void setAnyName(String anyName) {
		this.anyName = anyName;
	}

}
