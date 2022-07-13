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
@Table(name = "SEPERATION_DISTANCE_DOWN_CONDUCTORS_TABLE")
public class SeparateDistanceDownConductors implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEPERATION_DISTANCE_DOWN_CONDUCTOR_ID")
	private Integer seperationDistanceDownConductorId;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "SEPERATION_DISTANCE_DESC")
	private String seperationDistanceDesc;
	
	@Column(name = "SEPERATION_DISTANCE_OB")
	private Integer seperationDistanceOb;
	
	@Column(name = "SEPERATION_DISTANCE_REM")
	private String seperationDistanceRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SEPERATION_DISTANCE_ID")
	private SeperationDistanceDescription seperationDistanceDescription;

	public Integer getSeperationDistanceDownConductorId() {
		return seperationDistanceDownConductorId;
	}

	public void setSeperationDistanceDownConductorId(Integer seperationDistanceDownConductorId) {
		this.seperationDistanceDownConductorId = seperationDistanceDownConductorId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSeperationDistanceDesc() {
		return seperationDistanceDesc;
	}

	public void setSeperationDistanceDesc(String seperationDistanceDesc) {
		this.seperationDistanceDesc = seperationDistanceDesc;
	}

	public Integer getSeperationDistanceOb() {
		return seperationDistanceOb;
	}

	public void setSeperationDistanceOb(Integer seperationDistanceOb) {
		this.seperationDistanceOb = seperationDistanceOb;
	}

	public String getSeperationDistanceRem() {
		return seperationDistanceRem;
	}

	public void setSeperationDistanceRem(String seperationDistanceRem) {
		this.seperationDistanceRem = seperationDistanceRem;
	}

	public SeperationDistanceDescription getSeperationDistanceDescription() {
		return seperationDistanceDescription;
	}

	public void setSeperationDistanceDescription(SeperationDistanceDescription seperationDistanceDescription) {
		this.seperationDistanceDescription = seperationDistanceDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
