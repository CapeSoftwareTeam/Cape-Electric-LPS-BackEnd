/**
 * 
 */
package com.capeelectric.model.remarks;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "AIR_HOLDERS_LIST_TABLE")
public class AirHoldersListRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOLDERS_LIST_ID")
	private Integer holderListId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "HOLDER_TYPE_REMARKS")
	private String holderTypeRe;

	@Column(name = "MATERIAL_HOLDER_REMARKS")
	private String materailOfHolderRem;

	@Column(name = "TOTAL_HOLDERSNO_REMARKS")
	private String totalHolderNoRe;

	@Column(name = "HO_INSP_NO_REM")
	private String holderInspNoRe;

	@Column(name = "HO_INSP_PASSED_NO_REM")
	private String holderInspPassedNoRe;

	@Column(name = "HO_INSP_FAILED_NO_REM")
	private String holderInspFailedNoRe;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "HOLDERS_DESCRIPTION_ID")
	private AirHoldersRemarks airHolderDescription;

	public Integer getHolderListId() {
		return holderListId;
	}

	public void setHolderListId(Integer holderListId) {
		this.holderListId = holderListId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHolderTypeRe() {
		return holderTypeRe;
	}

	public void setHolderTypeRe(String holderTypeRe) {
		this.holderTypeRe = holderTypeRe;
	}

	public String getMaterailOfHolderRem() {
		return materailOfHolderRem;
	}

	public void setMaterailOfHolderRem(String materailOfHolderRem) {
		this.materailOfHolderRem = materailOfHolderRem;
	}

	public String getTotalHolderNoRe() {
		return totalHolderNoRe;
	}

	public void setTotalHolderNoRe(String totalHolderNoRe) {
		this.totalHolderNoRe = totalHolderNoRe;
	}

	public String getHolderInspNoRe() {
		return holderInspNoRe;
	}

	public void setHolderInspNoRe(String holderInspNoRe) {
		this.holderInspNoRe = holderInspNoRe;
	}

	public String getHolderInspPassedNoRe() {
		return holderInspPassedNoRe;
	}

	public void setHolderInspPassedNoRe(String holderInspPassedNoRe) {
		this.holderInspPassedNoRe = holderInspPassedNoRe;
	}

	public String getHolderInspFailedNoRe() {
		return holderInspFailedNoRe;
	}

	public void setHolderInspFailedNoRe(String holderInspFailedNoRe) {
		this.holderInspFailedNoRe = holderInspFailedNoRe;
	}

	public AirHoldersRemarks getAirHolderDescription() {
		return airHolderDescription;
	}

	public void setAirHolderDescription(AirHoldersRemarks airHolderDescription) {
		this.airHolderDescription = airHolderDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
