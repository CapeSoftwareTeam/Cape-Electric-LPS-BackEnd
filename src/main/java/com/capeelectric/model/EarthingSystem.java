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
@Table(name = "EARTHING_SYSTEM_TABLE")
public class EarthingSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_SYSTEM_ID")
	private Integer earthingSystemId;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "EAST_OB")
	private Integer eastOb;

	@Column(name = "EAST_REM")
	private String eastRem;

	@Column(name = "WEST_OB")
	private Integer westOb;

	@Column(name = "WEST_REM")
	private String westRem;

	@Column(name = "NORTH_OB")
	private Integer northOb;

	@Column(name = "NORTH_REM")
	private String northRem;

	@Column(name = "SOUTH_OB")
	private Integer southOb;

	@Column(name = "SOUTH_REM")
	private String southRem;

	@Column(name = "RINGEARTHWALL_EAST_OB")
	private Integer ringWallEarthEastOb;

	@Column(name = "RINGEARTHWALL_EAST_REM")
	private String ringWallEarthEastRem;

	@Column(name = "RINGEARTHWALL_WEST_OB")
	private Integer ringWallEarthWestOb;

	@Column(name = "RINGEARTHWALL_WEST_REM")
	private String ringWallEarthWestRem;

	@Column(name = "RINGEARTHWALL_NORTH_OB")
	private Integer ringWallEarthNorthOb;

	@Column(name = "RINGEARTHWALL_NORTH_REM")
	private String ringWallEarthNorthRem;

	@Column(name = "RINGEARTHWALL_SOUTH_OB")
	private Integer ringWallEarthSouthOb;

	@Column(name = "RINGEARTHWALL_SOUTH_REM")
	private String ringWallEarthSouthRem;
	
	@Column(name = "CONNECTED_EARTH_ELECTRODE_OB")
	private String connectedEarthElectrodeOb;

	@Column(name = "CONNECTED_EARTH_ELECTRODE_REM")
	private String connectedEarthElectrodeRem;

	@Column(name = "JOINTSMADE_BRAZING_OB")
	private String jointsMadeBrazingOb;

	@Column(name = "JOINTSMADE_BRAZING_REM")
	private String jointsMadeBrazingRem;

	@Column(name = "MATERIAL_OF_EARTHELECTRODE_OB")
	private String materialOfEartElectrodeOb;

	@Column(name = "MATERIAL_OF_EARTHELECTRODE_REM")
	private String materialOfEartElectrodeRem;
	
	@Column(name = "TYPE_OF_EARTHELECTRODE_OB")
	private String typeOfEarthElectrodeOb;

	@Column(name = "TYPE_OF_EARTHELECTRODE_REM")
	private String typeOfEarthElectrodeRem;

	@Column(name = "SIZE_OFEARTHELECTRODE_OB")
	private String sizeOfEarthElectrodeOb;

	@Column(name = "SIZE_OFEARTHELECTRODE_REM")
	private String sizeOfEarthElectrodeRem;

	@Column(name = "MAXIMUMDISTANCE_EARTHELECTRODEWAL_OB")
	private Integer maximumDistanceEartElectrodeWalOb;

	@Column(name = "MAXIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String maximumDistanceEartElectrodeWalRem;

	@Column(name = "MINIMUMDISTANCE_EARTHELECTRODEWALL_OB")
	private Integer manimumDistanceEartElectrodeWalOb;

	@Column(name = "MINIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String manimumDistanceEartElectrodeWalRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingSystemId() {
		return earthingSystemId;
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

	public void setEarthingSystemId(Integer earthingSystemId) {
		this.earthingSystemId = earthingSystemId;
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


	public String getJointsMadeBrazingOb() {
		return jointsMadeBrazingOb;
	}

	public void setJointsMadeBrazingOb(String jointsMadeBrazingOb) {
		this.jointsMadeBrazingOb = jointsMadeBrazingOb;
	}

	public String getJointsMadeBrazingRem() {
		return jointsMadeBrazingRem;
	}

	public void setJointsMadeBrazingRem(String jointsMadeBrazingRem) {
		this.jointsMadeBrazingRem = jointsMadeBrazingRem;
	}

	public String getMaterialOfEartElectrodeOb() {
		return materialOfEartElectrodeOb;
	}

	public void setMaterialOfEartElectrodeOb(String materialOfEartElectrodeOb) {
		this.materialOfEartElectrodeOb = materialOfEartElectrodeOb;
	}

	public String getMaterialOfEartElectrodeRem() {
		return materialOfEartElectrodeRem;
	}

	public void setMaterialOfEartElectrodeRem(String materialOfEartElectrodeRem) {
		this.materialOfEartElectrodeRem = materialOfEartElectrodeRem;
	}

	public String getSizeOfEarthElectrodeOb() {
		return sizeOfEarthElectrodeOb;
	}

	public void setSizeOfEarthElectrodeOb(String sizeOfEarthElectrodeOb) {
		this.sizeOfEarthElectrodeOb = sizeOfEarthElectrodeOb;
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

	public EarthingLpsDescription getEarthingLpsDescription() {
		return earthingLpsDescription;
	}

	public void setEarthingLpsDescription(EarthingLpsDescription earthingLpsDescription) {
		this.earthingLpsDescription = earthingLpsDescription;
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

	public String getConnectedEarthElectrodeOb() {
		return connectedEarthElectrodeOb;
	}

	public void setConnectedEarthElectrodeOb(String connectedEarthElectrodeOb) {
		this.connectedEarthElectrodeOb = connectedEarthElectrodeOb;
	}

	public String getConnectedEarthElectrodeRem() {
		return connectedEarthElectrodeRem;
	}

	public void setConnectedEarthElectrodeRem(String connectedEarthElectrodeRem) {
		this.connectedEarthElectrodeRem = connectedEarthElectrodeRem;
	}

	public String getTypeOfEarthElectrodeOb() {
		return typeOfEarthElectrodeOb;
	}

	public void setTypeOfEarthElectrodeOb(String typeOfEarthElectrodeOb) {
		this.typeOfEarthElectrodeOb = typeOfEarthElectrodeOb;
	}

	public String getTypeOfEarthElectrodeRem() {
		return typeOfEarthElectrodeRem;
	}

	public void setTypeOfEarthElectrodeRem(String typeOfEarthElectrodeRem) {
		this.typeOfEarthElectrodeRem = typeOfEarthElectrodeRem;
	}

	public Integer getEastOb() {
		return eastOb;
	}


	public void setEastOb(Integer eastOb) {
		this.eastOb = eastOb;
	}


	public Integer getWestOb() {
		return westOb;
	}


	public void setWestOb(Integer westOb) {
		this.westOb = westOb;
	}


	public Integer getNorthOb() {
		return northOb;
	}


	public void setNorthOb(Integer northOb) {
		this.northOb = northOb;
	}


	public Integer getSouthOb() {
		return southOb;
	}


	public void setSouthOb(Integer southOb) {
		this.southOb = southOb;
	}


	public Integer getRingWallEarthEastOb() {
		return ringWallEarthEastOb;
	}


	public void setRingWallEarthEastOb(Integer ringWallEarthEastOb) {
		this.ringWallEarthEastOb = ringWallEarthEastOb;
	}


	public Integer getRingWallEarthWestOb() {
		return ringWallEarthWestOb;
	}


	public void setRingWallEarthWestOb(Integer ringWallEarthWestOb) {
		this.ringWallEarthWestOb = ringWallEarthWestOb;
	}


	public Integer getRingWallEarthNorthOb() {
		return ringWallEarthNorthOb;
	}


	public void setRingWallEarthNorthOb(Integer ringWallEarthNorthOb) {
		this.ringWallEarthNorthOb = ringWallEarthNorthOb;
	}


	public Integer getRingWallEarthSouthOb() {
		return ringWallEarthSouthOb;
	}


	public void setRingWallEarthSouthOb(Integer ringWallEarthSouthOb) {
		this.ringWallEarthSouthOb = ringWallEarthSouthOb;
	}


	public Integer getMaximumDistanceEartElectrodeWalOb() {
		return maximumDistanceEartElectrodeWalOb;
	}


	public void setMaximumDistanceEartElectrodeWalOb(Integer maximumDistanceEartElectrodeWalOb) {
		this.maximumDistanceEartElectrodeWalOb = maximumDistanceEartElectrodeWalOb;
	}


	public Integer getManimumDistanceEartElectrodeWalOb() {
		return manimumDistanceEartElectrodeWalOb;
	}


	public void setManimumDistanceEartElectrodeWalOb(Integer manimumDistanceEartElectrodeWalOb) {
		this.manimumDistanceEartElectrodeWalOb = manimumDistanceEartElectrodeWalOb;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}