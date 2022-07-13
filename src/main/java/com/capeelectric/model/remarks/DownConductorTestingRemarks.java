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
@Table(name = "DOWN_CONDUCTOR_TESTING_TABLE")
public class DownConductorTestingRemarks implements Serializable  {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOWN_CONDUCTOR_TESTING_ID")
	private Integer downConductorTestingId;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "SERIAL_NUMBER")
	private Integer serialNo;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorsDescriptionRemarks downConductorDescription;

	public Integer getDownConductorTestingId() {
		return downConductorTestingId;
	}

	public void setDownConductorTestingId(Integer downConductorTestingId) {
		this.downConductorTestingId = downConductorTestingId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DownConductorsDescriptionRemarks getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorsDescriptionRemarks downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
