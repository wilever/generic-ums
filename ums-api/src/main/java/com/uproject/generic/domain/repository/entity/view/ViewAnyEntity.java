package com.uproject.generic.domain.repository.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uproject.generic.domain.repository.entity.AnyEntityType;
import com.uproject.generic.domain.repository.entity.MetaData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * The Class ViewAnyEntity.
 * It allow to show data from another tables.
 */
@ApiModel(value="View of any entity")
@Entity
@Table(name="any_entity")
public class ViewAnyEntity extends MetaData{
	
	/** The any id. */
	@Id
	@ApiModelProperty(value="The data identifier")
	@Column(name="any_id")
	private Integer anyId;
	
	/** The any type id. */
	@ApiModelProperty(value="The type of entity base on ID")
	@Column(name="any_type_id", insertable= false, updatable= false)
	private Integer anyTypeId;
	
	/** The any data name. */
	@ApiModelProperty(value="The any data name")
	@Column(name="any_name")
	private String anyName;
	
	/** The field. */
	@ApiModelProperty(value="One field of data")
	@Column(name="field")
	private String field;
	
	/** The description field. */
	@ApiModelProperty(value="The description field")
	@Column(name="description_field")
	private String descriptionField;
	
	/** The type name. */
	@ApiModelProperty(value="The name of type of entity")
	@Transient
	private String typeName;
	
	/** The any entity type. */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "any_type_id")
	private AnyEntityType anyEntityType;
	
	
	/**
	 * Instantiates a new view any entity.
	 */
	public ViewAnyEntity() {
		
	}
	
	public ViewAnyEntity(Integer anyId) {
		super();
		this.anyId = anyId;
	}

	/**
	 * Sets the values post load entity data.
	 */
	@PostLoad
	private void postLoad() {
		if(anyEntityType!=null) {
		typeName=anyEntityType.getTypeName();
		}
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
	 * Gets the field.
	 *
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * Gets the description field.
	 *
	 * @return the description field
	 */
	public String getDescriptionField() {
		return descriptionField;
	}

	/**
	 * Sets the description field.
	 *
	 * @param descriptionField the new description field
	 */
	public void setDescriptionField(String descriptionField) {
		this.descriptionField = descriptionField;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Sets the type name.
	 *
	 * @param typeName the new type name
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Gets the any entity type.
	 *
	 * @return the any entity type
	 */
	public AnyEntityType getAnyEntityType() {
		return anyEntityType;
	}

	/**
	 * Sets the any entity type.
	 *
	 * @param anyEntityType the new any entity type
	 */
	public void setAnyEntityType(AnyEntityType anyEntityType) {
		this.anyEntityType = anyEntityType;
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

	public String getAnyName() {
		return anyName;
	}

	public void setAnyName(String anyName) {
		this.anyName = anyName;
	}
}
