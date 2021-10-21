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
@Table(name = "AIR_MESHDESCRIPTION_TABLE")
public class AirMeshDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESHDESCRIPTION_ID")
	private Integer meshDescriptionId;

	@Column(name = "SIZE_OF_CONDUCTOROBSERVATION")
	private String sizeOfConductorOb;

	@Column(name = "SIZE_OF_CONDUCTORREMARKS")
	private String sizeOfConductorRe;

	@Column(name = "MESH_SIGEOBSERVATION")
	private String meshSizeOb;

	@Column(name = "MESH_SIGEREMARKS")
	private String meshSizeRe;

	@Column(name = "MAXIMUM_DISTANCEOBSERVATION")
	private String maximumDistanceOb;

	@Column(name = "MAXIMUM_DISTANCEREMARKS")
	private String maximumDistanceRe;

	@Column(name = "MINIMUM_DISTANCEOBSERVATION")
	private String  minimumDistanceOb;
	
	@Column(name = "MINIMUM_DISTANCEREMARKS")
	private String minimumDistanceRe;

	@Column(name = "HEIGHT_OFCONDUCTOR_FLATSURAFACEOBSERVATION")
	private String heightOfConductorFlatSurfaceOb;

	@Column(name = "HEIGHT_OFCONDUCTOR_FLATSURAFACEREMARKS")
	private String heightOfConductorFlatSurfaceRe;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private BasicLps lpsAirDes;

	public Integer getMeshDescriptionId() {
		return meshDescriptionId;
	}

	public void setMeshDescriptionId(Integer meshDescriptionId) {
		this.meshDescriptionId = meshDescriptionId;
	}

	public String getSizeOfConductorOb() {
		return sizeOfConductorOb;
	}

	public void setSizeOfConductorOb(String sizeOfConductorOb) {
		this.sizeOfConductorOb = sizeOfConductorOb;
	}

	public String getSizeOfConductorRe() {
		return sizeOfConductorRe;
	}

	public void setSizeOfConductorRe(String sizeOfConductorRe) {
		this.sizeOfConductorRe = sizeOfConductorRe;
	}

	public String getMeshSizeOb() {
		return meshSizeOb;
	}

	public void setMeshSizeOb(String meshSizeOb) {
		this.meshSizeOb = meshSizeOb;
	}

	public String getMeshSizeRe() {
		return meshSizeRe;
	}

	public void setMeshSizeRe(String meshSizeRe) {
		this.meshSizeRe = meshSizeRe;
	}

	public String getMaximumDistanceOb() {
		return maximumDistanceOb;
	}

	public void setMaximumDistanceOb(String maximumDistanceOb) {
		this.maximumDistanceOb = maximumDistanceOb;
	}

	public String getMaximumDistanceRe() {
		return maximumDistanceRe;
	}

	public void setMaximumDistanceRe(String maximumDistanceRe) {
		this.maximumDistanceRe = maximumDistanceRe;
	}

	public String getMinimumDistanceOb() {
		return minimumDistanceOb;
	}

	public void setMinimumDistanceOb(String minimumDistanceOb) {
		this.minimumDistanceOb = minimumDistanceOb;
	}

	public String getMinimumDistanceRe() {
		return minimumDistanceRe;
	}

	public void setMinimumDistanceRe(String minimumDistanceRe) {
		this.minimumDistanceRe = minimumDistanceRe;
	}

	public String getHeightOfConductorFlatSurfaceOb() {
		return heightOfConductorFlatSurfaceOb;
	}

	public void setHeightOfConductorFlatSurfaceOb(String heightOfConductorFlatSurfaceOb) {
		this.heightOfConductorFlatSurfaceOb = heightOfConductorFlatSurfaceOb;
	}

	public String getHeightOfConductorFlatSurfaceRe() {
		return heightOfConductorFlatSurfaceRe;
	}

	public void setHeightOfConductorFlatSurfaceRe(String heightOfConductorFlatSurfaceRe) {
		this.heightOfConductorFlatSurfaceRe = heightOfConductorFlatSurfaceRe;
	}

	public BasicLps getLpsAirDes() {
		return lpsAirDes;
	}

	public void setLpsAirDes(BasicLps lpsAirDes) {
		this.lpsAirDes = lpsAirDes;
	}
	
}
