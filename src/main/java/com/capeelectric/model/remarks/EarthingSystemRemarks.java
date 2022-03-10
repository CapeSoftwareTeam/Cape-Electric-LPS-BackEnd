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
@Table(name = "EARTHING_SYSTEM_TABLE")
public class EarthingSystemRemarks implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_SYSTEM_ID")
	private Integer earthingSystemId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "EAST_REM")
	private String eastRem;

	@Column(name = "WEST_REM")
	private String westRem;

	@Column(name = "NORTH_REM")
	private String northRem;

	@Column(name = "SOUTH_REM")
	private String southRem;

	@Column(name = "RINGEARTHWALL_EAST_REM")
	private String ringWallEarthEastRem;

	@Column(name = "RINGEARTHWALL_WEST_REM")
	private String ringWallEarthWestRem;

	@Column(name = "RINGEARTHWALL_NORTH_REM")
	private String ringWallEarthNorthRem;

	@Column(name = "RINGEARTHWALL_SOUTH_REM")
	private String ringWallEarthSouthRem;

	@Column(name = "CONNECTED_EARTH_ELECTRODE_REM")
	private String connectedEarthElectrodeRem;

	@Column(name = "JOINTSMADE_BRAZING_REM")
	private String jointsMadeBrazingRem;

	@Column(name = "MATERIAL_OF_EARTHELECTRODE_REM")
	private String materialOfEartElectrodeRem;

	@Column(name = "TYPE_OF_EARTHELECTRODE_REM")
	private String typeOfEarthElectrodeRem;

	@Column(name = "SIZE_OFEARTHELECTRODE_REM")
	private String sizeOfEarthElectrodeRem;

	@Column(name = "MAXIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String maximumDistanceEartElectrodeWalRem;

	@Column(name = "MINIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String manimumDistanceEartElectrodeWalRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescriptionRemarks earthingLpsDescription;

	public Integer getEarthingSystemId() {
		return earthingSystemId;
	}

	public void setEarthingSystemId(Integer earthingSystemId) {
		this.earthingSystemId = earthingSystemId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEastRem() {
		return eastRem;
	}

	public void setEastRem(String eastRem) {
		this.eastRem = eastRem;
	}

	public String getWestRem() {
		return westRem;
	}

	public void setWestRem(String westRem) {
		this.westRem = westRem;
	}

	public String getNorthRem() {
		return northRem;
	}

	public void setNorthRem(String northRem) {
		this.northRem = northRem;
	}

	public String getSouthRem() {
		return southRem;
	}

	public void setSouthRem(String southRem) {
		this.southRem = southRem;
	}

	public String getRingWallEarthEastRem() {
		return ringWallEarthEastRem;
	}

	public void setRingWallEarthEastRem(String ringWallEarthEastRem) {
		this.ringWallEarthEastRem = ringWallEarthEastRem;
	}

	public String getRingWallEarthWestRem() {
		return ringWallEarthWestRem;
	}

	public void setRingWallEarthWestRem(String ringWallEarthWestRem) {
		this.ringWallEarthWestRem = ringWallEarthWestRem;
	}

	public String getRingWallEarthNorthRem() {
		return ringWallEarthNorthRem;
	}

	public void setRingWallEarthNorthRem(String ringWallEarthNorthRem) {
		this.ringWallEarthNorthRem = ringWallEarthNorthRem;
	}

	public String getRingWallEarthSouthRem() {
		return ringWallEarthSouthRem;
	}

	public void setRingWallEarthSouthRem(String ringWallEarthSouthRem) {
		this.ringWallEarthSouthRem = ringWallEarthSouthRem;
	}

	public String getConnectedEarthElectrodeRem() {
		return connectedEarthElectrodeRem;
	}

	public void setConnectedEarthElectrodeRem(String connectedEarthElectrodeRem) {
		this.connectedEarthElectrodeRem = connectedEarthElectrodeRem;
	}

	public String getJointsMadeBrazingRem() {
		return jointsMadeBrazingRem;
	}

	public void setJointsMadeBrazingRem(String jointsMadeBrazingRem) {
		this.jointsMadeBrazingRem = jointsMadeBrazingRem;
	}

	public String getMaterialOfEartElectrodeRem() {
		return materialOfEartElectrodeRem;
	}

	public void setMaterialOfEartElectrodeRem(String materialOfEartElectrodeRem) {
		this.materialOfEartElectrodeRem = materialOfEartElectrodeRem;
	}

	public String getTypeOfEarthElectrodeRem() {
		return typeOfEarthElectrodeRem;
	}

	public void setTypeOfEarthElectrodeRem(String typeOfEarthElectrodeRem) {
		this.typeOfEarthElectrodeRem = typeOfEarthElectrodeRem;
	}

	public String getSizeOfEarthElectrodeRem() {
		return sizeOfEarthElectrodeRem;
	}

	public void setSizeOfEarthElectrodeRem(String sizeOfEarthElectrodeRem) {
		this.sizeOfEarthElectrodeRem = sizeOfEarthElectrodeRem;
	}

	public String getMaximumDistanceEartElectrodeWalRem() {
		return maximumDistanceEartElectrodeWalRem;
	}

	public void setMaximumDistanceEartElectrodeWalRem(String maximumDistanceEartElectrodeWalRem) {
		this.maximumDistanceEartElectrodeWalRem = maximumDistanceEartElectrodeWalRem;
	}

	public String getManimumDistanceEartElectrodeWalRem() {
		return manimumDistanceEartElectrodeWalRem;
	}

	public void setManimumDistanceEartElectrodeWalRem(String manimumDistanceEartElectrodeWalRem) {
		this.manimumDistanceEartElectrodeWalRem = manimumDistanceEartElectrodeWalRem;
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
