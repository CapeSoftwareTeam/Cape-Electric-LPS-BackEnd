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

@Entity
@Table(name = "EARTHDESCRIPTION_TABLE")
public class EarthingDescription implements Serializable {

	
	
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHDESCRIPTION_ID")
	private Integer earthDescriptionId;
	
    @Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINOB")
	private String earthelectMaxiDistWallInOb;
	
	@Column(name = "EARTHELECT_MAXIMUMDISTANCEWALLINREM")
	private String earthelectMaxiDistWallInRem;
	
	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINOB")
	private String earthelectManimumDistanceWallInOb;
	
	@Column(name = "EARTHELECT_MINIMUMDISTANCEWALLINREM")
	private String earthelectManiDistWallInRem;
	
	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINOB")
	private String earthelectMaxiDistOb;
	
	@Column(name = "EARTHELECT_MAXIMUMDISTANCEINREM")
	private String earthelectMaxiDistRem;
	
	@Column(name = "EARTHELECT_MINIMUMDISTANCEINOB")
	private String earthelectManiDistOb;
	
	@Column(name = "EARTHELECT_MINIMUMDISTANCEINREM")
	private String earthelectManiDistRem;
	

	@Column(name = "TOTALNUMBER_OF_ELECTRODEINOB")
	private String totalNumberOfElectrodeOb;
	
	@Column(name = "TOTALNUMBER_OF_ELECTRODEINREM")
	private String totalNumberOfElectrodeRem;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthDescriptionId() {
		return earthDescriptionId;
	}

	public void setEarthDescriptionId(Integer earthDescriptionId) {
		this.earthDescriptionId = earthDescriptionId;
	}


	public String getEarthelectMaxiDistWallInOb() {
		return earthelectMaxiDistWallInOb;
	}

	public void setEarthelectMaxiDistWallInOb(String earthelectMaxiDistWallInOb) {
		this.earthelectMaxiDistWallInOb = earthelectMaxiDistWallInOb;
	}

	public String getEarthelectMaxiDistWallInRem() {
		return earthelectMaxiDistWallInRem;
	}

	public void setEarthelectMaxiDistWallInRem(String earthelectMaxiDistWallInRem) {
		this.earthelectMaxiDistWallInRem = earthelectMaxiDistWallInRem;
	}

	public String getEarthelectManiDistWallInRem() {
		return earthelectManiDistWallInRem;
	}

	public void setEarthelectManiDistWallInRem(String earthelectManiDistWallInRem) {
		this.earthelectManiDistWallInRem = earthelectManiDistWallInRem;
	}

	public String getEarthelectMaxiDistOb() {
		return earthelectMaxiDistOb;
	}

	public void setEarthelectMaxiDistOb(String earthelectMaxiDistOb) {
		this.earthelectMaxiDistOb = earthelectMaxiDistOb;
	}

	public String getEarthelectMaxiDistRem() {
		return earthelectMaxiDistRem;
	}

	public void setEarthelectMaxiDistRem(String earthelectMaxiDistRem) {
		this.earthelectMaxiDistRem = earthelectMaxiDistRem;
	}

	public String getEarthelectManiDistOb() {
		return earthelectManiDistOb;
	}

	public void setEarthelectManiDistOb(String earthelectManiDistOb) {
		this.earthelectManiDistOb = earthelectManiDistOb;
	}

	public String getEarthelectManiDistRem() {
		return earthelectManiDistRem;
	}

	public void setEarthelectManiDistRem(String earthelectManiDistRem) {
		this.earthelectManiDistRem = earthelectManiDistRem;
	}

	public String getEarthelectManimumDistanceWallInOb() {
		return earthelectManimumDistanceWallInOb;
	}

	public void setEarthelectManimumDistanceWallInOb(String earthelectManimumDistanceWallInOb) {
		this.earthelectManimumDistanceWallInOb = earthelectManimumDistanceWallInOb;
	}

	

	public String getTotalNumberOfElectrodeOb() {
		return totalNumberOfElectrodeOb;
	}

	public void setTotalNumberOfElectrodeOb(String totalNumberOfElectrodeOb) {
		this.totalNumberOfElectrodeOb = totalNumberOfElectrodeOb;
	}

	public String getTotalNumberOfElectrodeRem() {
		return totalNumberOfElectrodeRem;
	}

	public void setTotalNumberOfElectrodeRem(String totalNumberOfElectrodeRem) {
		this.totalNumberOfElectrodeRem = totalNumberOfElectrodeRem;
	}

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
	}
	
	
}
