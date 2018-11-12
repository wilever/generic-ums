package com.uproject.generic.domain.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;

/**
 * The Class MetaData.
 */
@MappedSuperclass
public class MetaData {

	/** The active ind. It can be 'Y' or 'N'.*/
	@ApiModelProperty(
			value="A Y or N flag indicating "
					+ "whether this row of data is currently "
					+ "either active/valid (Y) or inactive/invalid (N).",
			example="Y")
	@Column(name="active_ind")
	private String activeInd;

	/** The effective date. */
	@ApiModelProperty(
			value="The date that the data in "
			+ "this row first came into effect.",
			example="2001-01-01")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="effective_date")
	private Date effectiveDate;

	/** The expiry date. */
	@ApiModelProperty(
			value="The date that the data in " +
			"this row was no longer active or in effect.",
			example="null")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="expiry_date")
	private Date expiryDate;

	/** The remark. */
	@ApiModelProperty(
			value="Narrative remarks about this row of data.",
			example="This is a remark.")
	@Column(name="remark")
	private String remark;

	/** The row changed by. */
	@ApiModelProperty(
			value="Application login id of the user"
					+ " who last changed the row.",
			example="UPDATED_USER")
	@Column(name="row_changed_by")
	private String rowChangedBy;

	/** The row changed date. */
	@ApiModelProperty(
			value="System date of the last time "
					+ "the row was changed.",
			example="2001-01-01")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="row_changed_date")
	private Date rowChangedDate;

	/** The row created by. */
	@ApiModelProperty(
			value="System user who "
				+ "created this row of data.",
			example="CREATED_USER")
	@Column(name="row_created_by")
	private String rowCreatedBy;

	/** The row created date. */
	@ApiModelProperty(
			value="Date that the row was created on.",
			example="2000-01-01")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="row_created_date")
	private Date rowCreatedDate;

	/** The row effective date. */
	@ApiModelProperty(
			value=	"A system assigned date " +
					"that specified when a row of data is effective " +
					"from a systems perspective. This may be the " +
					"date that a row of data was made available to " +
					"end users, for example.",
			example="2001-01-01")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="row_effective_date")
	private Date rowEffectiveDate;

	/** The row expiry date. */
	@ApiModelProperty(
			value=	"A system assigned date that " +
					"specified when a row of data is no longer " +
					"effective from a systems perspective. This " +
					"may be the date that a row of data was no " +
					"longer available to end users, for example.",
			example="null")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="row_expiry_date")
	private Date rowExpiryDate;

	/** The row quality. */
	@ApiModelProperty(
			value=	"A set of values indicating " +
					"the quality of data in this row, usually with " +
					"reference to the method or procedures used to " +
					"load the data, although other types of quality " +
					"reference are permitted.",
			example="REAL")
	@Column(name="row_quality")
	private String rowQuality;

	/** The source. */
	@ApiModelProperty(
			value=	"The individual, company, state, or " +
					"government agency designated as the source " +
					"of information for this row.",
			example="SOURCE_SAMPLE")
	@Column(name="source")
	private String source;

	/**
	 * Instantiates a new meta data.
	 */
	public MetaData() {
		super();
	}

	/**
	 * Gets the active ind.
	 *
	 * @return the active ind
	 */
	public String getActiveInd() {
		return activeInd;
	}

	/**
	 * Sets the active ind.
	 *
	 * @param activeInd the new active ind
	 */
	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}

	/**
	 * Gets the effective date.
	 *
	 * @return the effective date
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effective date.
	 *
	 * @param effectiveDate the new effective date
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Gets the expiry date.
	 *
	 * @return the expiry date
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date.
	 *
	 * @param expiryDate the new expiry date
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Gets the remark.
	 *
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the remark.
	 *
	 * @param remark the new remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Gets the row changed by.
	 *
	 * @return the row changed by
	 */
	public String getRowChangedBy() {
		return rowChangedBy;
	}

	/**
	 * Sets the row changed by.
	 *
	 * @param rowChangedBy the new row changed by
	 */
	public void setRowChangedBy(String rowChangedBy) {
		this.rowChangedBy = rowChangedBy;
	}

	/**
	 * Gets the row changed date.
	 *
	 * @return the row changed date
	 */
	public Date getRowChangedDate() {
		return rowChangedDate;
	}

	/**
	 * Sets the row changed date.
	 *
	 * @param rowChangedDate the new row changed date
	 */
	public void setRowChangedDate(Date rowChangedDate) {
		this.rowChangedDate = rowChangedDate;
	}

	/**
	 * Gets the row created by.
	 *
	 * @return the row created by
	 */
	public String getRowCreatedBy() {
		return rowCreatedBy;
	}

	/**
	 * Sets the row created by.
	 *
	 * @param rowCreatedBy the new row created by
	 */
	public void setRowCreatedBy(String rowCreatedBy) {
		this.rowCreatedBy = rowCreatedBy;
	}

	/**
	 * Gets the row created date.
	 *
	 * @return the row created date
	 */
	public Date getRowCreatedDate() {
		return rowCreatedDate;
	}

	/**
	 * Sets the row created date.
	 *
	 * @param rowCreatedDate the new row created date
	 */
	public void setRowCreatedDate(Date rowCreatedDate) {
		this.rowCreatedDate = rowCreatedDate;
	}

	/**
	 * Gets the row effective date.
	 *
	 * @return the row effective date
	 */
	public Date getRowEffectiveDate() {
		return rowEffectiveDate;
	}

	/**
	 * Sets the row effective date.
	 *
	 * @param rowEffectiveDate the new row effective date
	 */
	public void setRowEffectiveDate(Date rowEffectiveDate) {
		this.rowEffectiveDate = rowEffectiveDate;
	}

	/**
	 * Gets the row expiry date.
	 *
	 * @return the row expiry date
	 */
	public Date getRowExpiryDate() {
		return rowExpiryDate;
	}

	/**
	 * Sets the row expiry date.
	 *
	 * @param rowExpiryDate the new row expiry date
	 */
	public void setRowExpiryDate(Date rowExpiryDate) {
		this.rowExpiryDate = rowExpiryDate;
	}

	/**
	 * Gets the row quality.
	 *
	 * @return the row quality
	 */
	public String getRowQuality() {
		return rowQuality;
	}

	/**
	 * Sets the row quality.
	 *
	 * @param rowQuality the new row quality
	 */
	public void setRowQuality(String rowQuality) {
		this.rowQuality = rowQuality;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(String source) {
		this.source = source;
	}
}
