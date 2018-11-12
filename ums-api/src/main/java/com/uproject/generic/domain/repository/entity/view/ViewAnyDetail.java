package com.uproject.generic.domain.repository.entity.view;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uproject.generic.domain.repository.entity.AnyEntity;
import com.uproject.generic.domain.repository.entity.AnyEntityDetailPK;
import com.uproject.generic.domain.repository.entity.MetaData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * AnyEntity to map data on database to java object.
 * It can be auto-generate form a database by JPA.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@ApiModel(value="Any entity details")
@Entity
@Table(name="any_entity_detail")
public class ViewAnyDetail extends MetaData{

	/** The id. */
	@JsonIgnore
	@EmbeddedId
	private AnyEntityDetailPK id;
	
	/** The any id. */
	@ApiModelProperty(value="The any entity identifier")
	@Column(name= "any_id", 
			nullable=false, 
			insertable=false, 
			updatable=false)
	private Integer anyId;
	
	/** The any entity detail id. */
	@ApiModelProperty(value="The any entity detail identifier")
	@Column(name= "any_entity_detail_id", 
			nullable=false, 
			insertable=false, 
			updatable=false)
	private Integer anyEntityDetailId;
	
	/** The detail name. */
	@ApiModelProperty(value="The detail name")
	@Column(name="detail_name")
	private String detailName;
	
	/** The detail field. */
	@ApiModelProperty(value="The detail field")
	@Column(name="detail_field")
	private String detailField;
	
	/** The any data name. */
	@ApiModelProperty(value="The any data name")
	@Transient
	private String anyName;

	/** The any entity. */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(
			name="any_id",
			insertable=false,
			updatable=false)
	private AnyEntity anyEntity;
	
	/**
	 * Instantiates a new any entity.
	 */
	public ViewAnyDetail() {
		
	}
	
	public ViewAnyDetail(Integer anyId, Integer anyEntityDetailId) {
		this.anyId = anyId;
		this.anyEntityDetailId = anyEntityDetailId;
		this.id= new AnyEntityDetailPK(anyId, anyEntityDetailId);
	}



	@PostLoad
	private void postLoad() {
		if(anyEntity!=null) {
		anyName=anyEntity.getAnyName();
		}
	}

	/**
	 * Gets the detail name.
	 *
	 * @return the detail name
	 */
	public String getDetailName() {
		return detailName;
	}

	/**
	 * Sets the detail name.
	 *
	 * @param detailName the new detail name
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	/**
	 * Gets the detail field.
	 *
	 * @return the detail field
	 */
	public String getDetailField() {
		return detailField;
	}

	/**
	 * Sets the detail field.
	 *
	 * @param detailField the new detail field
	 */
	public void setDetailField(String detailField) {
		this.detailField = detailField;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public AnyEntityDetailPK getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(AnyEntityDetailPK id) {
		this.id = id;
	}

	/**
	 * Gets the any entity.
	 *
	 * @return the any entity
	 */
	public AnyEntity getAnyEntity() {
		return anyEntity;
	}

	/**
	 * Sets the any entity.
	 *
	 * @param anyEntity the new any entity
	 */
	public void setAnyEntity(AnyEntity anyEntity) {
		this.anyEntity = anyEntity;
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

	public String getAnyName() {
		return anyName;
	}

	public void setAnyName(String anyName) {
		this.anyName = anyName;
	}
}
