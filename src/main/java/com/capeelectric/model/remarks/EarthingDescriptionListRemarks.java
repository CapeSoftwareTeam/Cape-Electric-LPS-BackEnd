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
@Table(name = "EARTHDESCRIPTION_LIST_TABLE")
public class EarthingDescriptionListRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHDESCRIPTION_LIST_ID")
	private Integer earthDescriptionListId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "EARTHING_CONDUCTOR_MATERIAL_REM")
	private String earthingConductorMaterialInRem;

	@Column(name = "EARTHELECTRODE_MATERIALINREM")
	private String earthElectrodeMaterialInRem;

	@Column(name = "EARTHELECTRODE_TYPE_IN_REM")
	private String earthElectrodeTypeInRem;

	@Column(name = "EARTHELECTRODE_SIZEINREM")
	private String earthElectrodeSizeInRem;

	@Column(name = "EARTHELECTRODE_LENGTHINREM")
	private String earthElectrodeLengthingRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHDESCRIPTION_ID")
	private EarthingDescriptionRemarks earthingDescription;

	public Integer getEarthDescriptionListId() {
		return earthDescriptionListId;
	}

	public void setEarthDescriptionListId(Integer earthDescriptionListId) {
		this.earthDescriptionListId = earthDescriptionListId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEarthingConductorMaterialInRem() {
		return earthingConductorMaterialInRem;
	}

	public void setEarthingConductorMaterialInRem(String earthingConductorMaterialInRem) {
		this.earthingConductorMaterialInRem = earthingConductorMaterialInRem;
	}

	public String getEarthElectrodeMaterialInRem() {
		return earthElectrodeMaterialInRem;
	}

	public void setEarthElectrodeMaterialInRem(String earthElectrodeMaterialInRem) {
		this.earthElectrodeMaterialInRem = earthElectrodeMaterialInRem;
	}

	public String getEarthElectrodeTypeInRem() {
		return earthElectrodeTypeInRem;
	}

	public void setEarthElectrodeTypeInRem(String earthElectrodeTypeInRem) {
		this.earthElectrodeTypeInRem = earthElectrodeTypeInRem;
	}

	public String getEarthElectrodeSizeInRem() {
		return earthElectrodeSizeInRem;
	}

	public void setEarthElectrodeSizeInRem(String earthElectrodeSizeInRem) {
		this.earthElectrodeSizeInRem = earthElectrodeSizeInRem;
	}

	public String getEarthElectrodeLengthingRem() {
		return earthElectrodeLengthingRem;
	}

	public void setEarthElectrodeLengthingRem(String earthElectrodeLengthingRem) {
		this.earthElectrodeLengthingRem = earthElectrodeLengthingRem;
	}

	public EarthingDescriptionRemarks getEarthingDescription() {
		return earthingDescription;
	}

	public void setEarthingDescription(EarthingDescriptionRemarks earthingDescription) {
		this.earthingDescription = earthingDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
