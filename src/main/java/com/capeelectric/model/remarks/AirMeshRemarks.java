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
@Table(name = "AIR_MESHDESCRIPTION_TABLE")
public class AirMeshRemarks implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESHDESCRIPTION_ID")
	private Integer meshDescriptionId;

	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "PHYSICAL_INSPECTION_REM")
	private String physicalInspectionRe;

	@Column(name = "MATERIAL_OFCONDUCTOR_REM")
	private String materailOfConductorRem;

	@Column(name = "SIZE_OF_CONDUCTORREMARKS")
	private String sizeOfConductorRe;

	@Column(name = "MESH_SIGEREMARKS")
	private String meshSizeRe;

	@Column(name = "MAXIMUM_DISTANCE_X_REMARKS")
	private String maximumDistanceXRe;

	@Column(name = "MAXIMUM_DISTANCE_Y_REMARKS")
	private String maximumDistanceYRe;

	@Column(name = "MINIMUM_DISTANCE_X_REMARKS")
	private String minimumDistanceXRe;

	@Column(name = "MINIMUM_DISTANCE_Y_REMARKS")
	private String minimumDistanceYRe;

	@Column(name = "HEIGHT_OFCONDUCTOR_FLATSURAFACEREMARKS")
	private String heightOfConductorFlatSurfaceRe;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LPSAIRDESCRIPTION_ID")
	private LpsAirDiscriptionRemarks lpsAirDescription;

	public Integer getMeshDescriptionId() {
		return meshDescriptionId;
	}

	public void setMeshDescriptionId(Integer meshDescriptionId) {
		this.meshDescriptionId = meshDescriptionId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPhysicalInspectionRe() {
		return physicalInspectionRe;
	}

	public void setPhysicalInspectionRe(String physicalInspectionRe) {
		this.physicalInspectionRe = physicalInspectionRe;
	}

	public String getMaterailOfConductorRem() {
		return materailOfConductorRem;
	}

	public void setMaterailOfConductorRem(String materailOfConductorRem) {
		this.materailOfConductorRem = materailOfConductorRem;
	}

	public String getSizeOfConductorRe() {
		return sizeOfConductorRe;
	}

	public void setSizeOfConductorRe(String sizeOfConductorRe) {
		this.sizeOfConductorRe = sizeOfConductorRe;
	}

	public String getMeshSizeRe() {
		return meshSizeRe;
	}

	public void setMeshSizeRe(String meshSizeRe) {
		this.meshSizeRe = meshSizeRe;
	}

	public String getMaximumDistanceXRe() {
		return maximumDistanceXRe;
	}

	public void setMaximumDistanceXRe(String maximumDistanceXRe) {
		this.maximumDistanceXRe = maximumDistanceXRe;
	}

	public String getMaximumDistanceYRe() {
		return maximumDistanceYRe;
	}

	public void setMaximumDistanceYRe(String maximumDistanceYRe) {
		this.maximumDistanceYRe = maximumDistanceYRe;
	}

	public String getMinimumDistanceXRe() {
		return minimumDistanceXRe;
	}

	public void setMinimumDistanceXRe(String minimumDistanceXRe) {
		this.minimumDistanceXRe = minimumDistanceXRe;
	}

	public String getMinimumDistanceYRe() {
		return minimumDistanceYRe;
	}

	public void setMinimumDistanceYRe(String minimumDistanceYRe) {
		this.minimumDistanceYRe = minimumDistanceYRe;
	}

	public String getHeightOfConductorFlatSurfaceRe() {
		return heightOfConductorFlatSurfaceRe;
	}

	public void setHeightOfConductorFlatSurfaceRe(String heightOfConductorFlatSurfaceRe) {
		this.heightOfConductorFlatSurfaceRe = heightOfConductorFlatSurfaceRe;
	}

	public LpsAirDiscriptionRemarks getLpsAirDescription() {
		return lpsAirDescription;
	}

	public void setLpsAirDescription(LpsAirDiscriptionRemarks lpsAirDescription) {
		this.lpsAirDescription = lpsAirDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
