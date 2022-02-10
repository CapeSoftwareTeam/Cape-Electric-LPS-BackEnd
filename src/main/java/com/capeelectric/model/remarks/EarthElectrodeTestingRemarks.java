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
@Table(name = "EARTH_ELECTRODE_TESTING")
public class EarthElectrodeTestingRemarks implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTH_ELECTRODE_TESTING_ID")
	private Integer earthingElectrodeTestingId;
	
	@Column(name = "SERIAL_NUMBER")
	private Integer serialNo;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "EARTH_ELECTRODE_REMARKS")
	private String earthingElectrodeRemarks;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescriptionRemarks earthingLpsDescription;

	public Integer getEarthingElectrodeTestingId() {
		return earthingElectrodeTestingId;
	}

	public void setEarthingElectrodeTestingId(Integer earthingElectrodeTestingId) {
		this.earthingElectrodeTestingId = earthingElectrodeTestingId;
	}

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

	public String getEarthingElectrodeRemarks() {
		return earthingElectrodeRemarks;
	}

	public void setEarthingElectrodeRemarks(String earthingElectrodeRemarks) {
		this.earthingElectrodeRemarks = earthingElectrodeRemarks;
	}

	public EarthingLpsDescriptionRemarks getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescriptionRemarks earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
