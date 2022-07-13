/**
 * 
 */
package com.capeelectric.model;

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
@Table(name = "EARTH_ELECTRODE_TESTING")
public class EarthElectrodeTesting implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTH_ELECTRODE_TESTING_ID")
	private Integer earthingElectrodeTestingId;
	
	@Column(name = "SERIAL_NUMBER")
	private Integer serialNo;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "EARTH_ELECTRODE_TYPE")
	private String earthingElectrodeType;
	
	@Column(name = "EARTH_ELECTRODE_MATERIAL")
	private String earthingElectrodeMaterial;
	
	@Column(name = "EARTH_ELECTRODE_SIZE")
	private Integer earthingElectrodeSize;
	
	@Column(name = "EARTH_ELECTRODE_DEPTH")
	private Integer earthingElectrodeDepth;
	
	@Column(name = "EARTH_ELECTRODE_RESISTANCE")
	private Integer earthingElectrodeResistance;
	
	@Column(name = "EARTH_ELECTRODE_REMARKS")
	private String earthingElectrodeRemarks;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public Integer getEarthingElectrodeTestingId() {
		return earthingElectrodeTestingId;
	}

	public void setEarthingElectrodeTestingId(Integer earthingElectrodeTestingId) {
		this.earthingElectrodeTestingId = earthingElectrodeTestingId;
	}

	public String getEarthingElectrodeType() {
		return earthingElectrodeType;
	}

	public void setEarthingElectrodeType(String earthingElectrodeType) {
		this.earthingElectrodeType = earthingElectrodeType;
	}

	public String getEarthingElectrodeMaterial() {
		return earthingElectrodeMaterial;
	}

	public void setEarthingElectrodeMaterial(String earthingElectrodeMaterial) {
		this.earthingElectrodeMaterial = earthingElectrodeMaterial;
	}

	public Integer getEarthingElectrodeSize() {
		return earthingElectrodeSize;
	}

	public void setEarthingElectrodeSize(Integer earthingElectrodeSize) {
		this.earthingElectrodeSize = earthingElectrodeSize;
	}

	public Integer getEarthingElectrodeDepth() {
		return earthingElectrodeDepth;
	}

	public void setEarthingElectrodeDepth(Integer earthingElectrodeDepth) {
		this.earthingElectrodeDepth = earthingElectrodeDepth;
	}

	public Integer getEarthingElectrodeResistance() {
		return earthingElectrodeResistance;
	}

	public void setEarthingElectrodeResistance(Integer earthingElectrodeResistance) {
		this.earthingElectrodeResistance = earthingElectrodeResistance;
	}

	public String getEarthingElectrodeRemarks() {
		return earthingElectrodeRemarks;
	}

	public void setEarthingElectrodeRemarks(String earthingElectrodeRemarks) {
		this.earthingElectrodeRemarks = earthingElectrodeRemarks;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
