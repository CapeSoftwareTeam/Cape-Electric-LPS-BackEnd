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

	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "PHYSICAL_INSPECTION_OB")
	private String physicalInspectionOb;

	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRe;

	@Column(name = " MATERIAL_OFCONDUCTOR_OB")
	private String materailOfConductorOb;

	@Column(name = "MATERIAL_OFCONDUCTOR_REM")
	private String materailOfConductorRem;

	@Column(name = "SIZE_OF_CONDUCTOROBSERVATION")
	private String sizeOfConductorOb;

	@Column(name = "SIZE_OF_CONDUCTORREMARKS")
	private String sizeOfConductorRe;

	@Column(name = "MESH_SIGEOBSERVATION")
	private String meshSizeOb;

	@Column(name = "MESH_SIGEREMARKS")
	private String meshSizeRe;

	@Column(name = "MAXIMUM_DISTANCE_X_OBSERVATION")
	private Integer maximumDistanceXOb;

	@Column(name = "MAXIMUM_DISTANCE_X_REMARKS")
	private String maximumDistanceXRe;
	
	@Column(name = "MAXIMUM_DISTANCE_Y_OBSERVATION")
	private Integer maximumDistanceYOb;

	@Column(name = "MAXIMUM_DISTANCE_Y_REMARKS")
	private String maximumDistanceYRe;

	@Column(name = "MINIMUM_DISTANCE_X_OBSERVATION")
	private Integer minimumDistanceXOb;

	@Column(name = "MINIMUM_DISTANCE_X_REMARKS")
	private String minimumDistanceXRe;
	
	@Column(name = "MINIMUM_DISTANCE_Y_OBSERVATION")
	private Integer minimumDistanceYOb;

	@Column(name = "MINIMUM_DISTANCE_Y_REMARKS")
	private String minimumDistanceYRe;

	@Column(name = "HEIGHT_OFCONDUCTOR_FLATSURAFACEOBSERVATION")
	private Integer heightOfConductorFlatSurfaceOb;

	@Column(name = "HEIGHT_OFCONDUCTOR_FLATSURAFACEREMARKS")
	private String heightOfConductorFlatSurfaceRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscription lpsAirDescription;

	public Integer getMeshDescriptionId() {
		return meshDescriptionId;
	}

	public String getPhysicalInspectionOb() {
		return physicalInspectionOb;
	}

	public void setPhysicalInspectionOb(String physicalInspectionOb) {
		this.physicalInspectionOb = physicalInspectionOb;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getMaterailOfConductorOb() {
		return materailOfConductorOb;
	}

	public void setMaterailOfConductorOb(String materailOfConductorOb) {
		this.materailOfConductorOb = materailOfConductorOb;
	}

	public String getMaterailOfConductorRem() {
		return materailOfConductorRem;
	}

	public void setMaterailOfConductorRem(String materailOfConductorRem) {
		this.materailOfConductorRem = materailOfConductorRem;
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

	
	public Integer getMaximumDistanceXOb() {
		return maximumDistanceXOb;
	}

	public void setMaximumDistanceXOb(Integer maximumDistanceXOb) {
		this.maximumDistanceXOb = maximumDistanceXOb;
	}

	public String getMaximumDistanceXRe() {
		return maximumDistanceXRe;
	}

	public void setMaximumDistanceXRe(String maximumDistanceXRe) {
		this.maximumDistanceXRe = maximumDistanceXRe;
	}

	public Integer getMaximumDistanceYOb() {
		return maximumDistanceYOb;
	}

	public void setMaximumDistanceYOb(Integer maximumDistanceYOb) {
		this.maximumDistanceYOb = maximumDistanceYOb;
	}

	public String getMaximumDistanceYRe() {
		return maximumDistanceYRe;
	}

	public void setMaximumDistanceYRe(String maximumDistanceYRe) {
		this.maximumDistanceYRe = maximumDistanceYRe;
	}

	public Integer getMinimumDistanceXOb() {
		return minimumDistanceXOb;
	}

	public void setMinimumDistanceXOb(Integer minimumDistanceXOb) {
		this.minimumDistanceXOb = minimumDistanceXOb;
	}

	public String getMinimumDistanceXRe() {
		return minimumDistanceXRe;
	}

	public void setMinimumDistanceXRe(String minimumDistanceXRe) {
		this.minimumDistanceXRe = minimumDistanceXRe;
	}

	public Integer getMinimumDistanceYOb() {
		return minimumDistanceYOb;
	}

	public void setMinimumDistanceYOb(Integer minimumDistanceYOb) {
		this.minimumDistanceYOb = minimumDistanceYOb;
	}

	public String getMinimumDistanceYRe() {
		return minimumDistanceYRe;
	}

	public void setMinimumDistanceYRe(String minimumDistanceYRe) {
		this.minimumDistanceYRe = minimumDistanceYRe;
	}

	public Integer getHeightOfConductorFlatSurfaceOb() {
		return heightOfConductorFlatSurfaceOb;
	}

	public void setHeightOfConductorFlatSurfaceOb(Integer heightOfConductorFlatSurfaceOb) {
		this.heightOfConductorFlatSurfaceOb = heightOfConductorFlatSurfaceOb;
	}

	public String getHeightOfConductorFlatSurfaceRe() {
		return heightOfConductorFlatSurfaceRe;
	}

	public void setHeightOfConductorFlatSurfaceRe(String heightOfConductorFlatSurfaceRe) {
		this.heightOfConductorFlatSurfaceRe = heightOfConductorFlatSurfaceRe;
	}

	

	public LpsAirDiscription getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscription lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


}
