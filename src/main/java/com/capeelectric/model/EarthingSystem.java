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

	@Column(name = "BURIED_ELECTRODEOB")
	private String buriedElectrodeOb;

	@Column(name = "BURIED_ELECTRODEREM")
	private String buriedElectrodeRem;

	@Column(name = "DEPTH_OF_ELECTRODEOB")
	private String depthOfElectrodeOb;

	@Column(name = "DEPTH_OF_ELECTRODEREM")
	private String depthOfElectrodeRem;

	@Column(name = "EAST_OB")
	private String earthOb;

	@Column(name = "EAST_REM")
	private String earthRem;

	@Column(name = "WEST_OB")
	private String westOb;

	@Column(name = "WEST_REM")
	private String westRem;

	@Column(name = "NORTH_OB")
	private String northOb;

	@Column(name = "NORTH_REM")
	private String northRem;

	@Column(name = "SOUTH_OB")
	private String southOb;

	@Column(name = "SOUTH_REM")
	private String southRem;

	@Column(name = "RINGEARTHWALL_DISTANCE_OB")
	private String ringEarthWallDistanceOb;

	@Column(name = "RINGEARTHWALL_DISTANCE_REM")
	private String ringEarthWallDistanceRem;

	@Column(name = "RINGEARTHWALL_EAST_OB")
	private String ringWallEarthEastOb;

	@Column(name = "RINGEARTHWALL_EAST_REM")
	private String ringWallEarthEastRem;

	@Column(name = "RINGEARTHWALL_WEST_OB")
	private String ringWallEarthOb;

	@Column(name = "RINGEARTHWALL_WEST_REM")
	private String ringWallEarthWestRem;

	@Column(name = "RINGEARTHWALL_NORTH_OB")
	private String ringWallEarthNorthOb;

	@Column(name = "RINGEARTHWALL_NORTH_REM")
	private String ringWallEarthNorthRem;

	@Column(name = "RINGEARTHWALL_SOUTH_OB")
	private String ringWallEarthSouthOb;

	@Column(name = "RINGEARTHWALL_SOUTH_REM")
	private String ringWallEarthSouthRem;

	@Column(name = "JOINTSMADE_BRAZING_OB")
	private String jointsMadeBrazingOb;

	@Column(name = "JOINTSMADE_BRAZING_REM")
	private String jointsMadeBrazingRem;

	@Column(name = "MATERIAL_OF_EARTHELECTRODE_OB")
	private String materialOfEartElectrodeOb;

	@Column(name = "MATERIAL_OF_EARTHELECTRODE_REM")
	private String materialOfEartElectrodeRem;

	@Column(name = "SIZE_OFEARTHELECTRODE_OB")
	private String sizeOfEarthElectrodeOb;

	@Column(name = "SIZE_OFEARTHELECTRODE_REM")
	private String sizeOfEarthElectrodeRem;

	@Column(name = "MAXIMUMDISTANCE_EARTHELECTRODEWAL_OB")
	private String maximumDistanceEartElectrodeWalOb;

	@Column(name = "MAXIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String maximumDistanceEartElectrodeWalRem;

	@Column(name = "MINIMUMDISTANCE_EARTHELECTRODEWALL_OB")
	private String manimumDistanceEartElectrodeWalOb;

	@Column(name = "MINIMUMDISTANCE_EARTHELECTRODEWALL_REM")
	private String manimumDistanceEartElectrodeWalRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EARTHING_ID")
	private EarthingLpsDescription earthingLpsDescription;

	public Integer getEarthingSystemId() {
		return earthingSystemId;
	}

	public String getBuriedElectrodeOb() {
		return buriedElectrodeOb;
	}

	public void setBuriedElectrodeOb(String buriedElectrodeOb) {
		this.buriedElectrodeOb = buriedElectrodeOb;
	}

	public String getBuriedElectrodeRem() {
		return buriedElectrodeRem;
	}

	public void setBuriedElectrodeRem(String buriedElectrodeRem) {
		this.buriedElectrodeRem = buriedElectrodeRem;
	}

	public String getRingWallEarthEastOb() {
		return ringWallEarthEastOb;
	}

	public void setRingWallEarthEastOb(String ringWallEarthEastOb) {
		this.ringWallEarthEastOb = ringWallEarthEastOb;
	}

	public String getRingWallEarthEastRem() {
		return ringWallEarthEastRem;
	}

	public void setRingWallEarthEastRem(String ringWallEarthEastRem) {
		this.ringWallEarthEastRem = ringWallEarthEastRem;
	}

	public String getRingWallEarthOb() {
		return ringWallEarthOb;
	}

	public void setRingWallEarthOb(String ringWallEarthOb) {
		this.ringWallEarthOb = ringWallEarthOb;
	}

	public String getRingWallEarthWestRem() {
		return ringWallEarthWestRem;
	}

	public void setRingWallEarthWestRem(String ringWallEarthWestRem) {
		this.ringWallEarthWestRem = ringWallEarthWestRem;
	}

	public String getRingWallEarthNorthOb() {
		return ringWallEarthNorthOb;
	}

	public void setRingWallEarthNorthOb(String ringWallEarthNorthOb) {
		this.ringWallEarthNorthOb = ringWallEarthNorthOb;
	}

	public String getRingWallEarthNorthRem() {
		return ringWallEarthNorthRem;
	}

	public void setRingWallEarthNorthRem(String ringWallEarthNorthRem) {
		this.ringWallEarthNorthRem = ringWallEarthNorthRem;
	}

	public String getRingWallEarthSouthOb() {
		return ringWallEarthSouthOb;
	}

	public void setRingWallEarthSouthOb(String ringWallEarthSouthOb) {
		this.ringWallEarthSouthOb = ringWallEarthSouthOb;
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

	public String getDepthOfElectrodeOb() {
		return depthOfElectrodeOb;
	}

	public void setDepthOfElectrodeOb(String depthOfElectrodeOb) {
		this.depthOfElectrodeOb = depthOfElectrodeOb;
	}

	public String getDepthOfElectrodeRem() {
		return depthOfElectrodeRem;
	}

	public void setDepthOfElectrodeRem(String depthOfElectrodeRem) {
		this.depthOfElectrodeRem = depthOfElectrodeRem;
	}

	public String getEarthOb() {
		return earthOb;
	}

	public void setEarthOb(String earthOb) {
		this.earthOb = earthOb;
	}

	public String getEarthRem() {
		return earthRem;
	}

	public void setEarthRem(String earthRem) {
		this.earthRem = earthRem;
	}

	public String getWestOb() {
		return westOb;
	}

	public void setWestOb(String westOb) {
		this.westOb = westOb;
	}

	public String getWestRem() {
		return westRem;
	}

	public void setWestRem(String westRem) {
		this.westRem = westRem;
	}

	public String getNorthOb() {
		return northOb;
	}

	public void setNorthOb(String northOb) {
		this.northOb = northOb;
	}

	public String getNorthRem() {
		return northRem;
	}

	public void setNorthRem(String northRem) {
		this.northRem = northRem;
	}

	public String getSouthOb() {
		return southOb;
	}

	public void setSouthOb(String southOb) {
		this.southOb = southOb;
	}

	public String getSouthRem() {
		return southRem;
	}

	public void setSouthRem(String southRem) {
		this.southRem = southRem;
	}

	public String getRingEarthWallDistanceOb() {
		return ringEarthWallDistanceOb;
	}

	public void setRingEarthWallDistanceOb(String ringEarthWallDistanceOb) {
		this.ringEarthWallDistanceOb = ringEarthWallDistanceOb;
	}

	public String getRingEarthWallDistanceRem() {
		return ringEarthWallDistanceRem;
	}

	public void setRingEarthWallDistanceRem(String ringEarthWallDistanceRem) {
		this.ringEarthWallDistanceRem = ringEarthWallDistanceRem;
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

	public String getMaximumDistanceEartElectrodeWalOb() {
		return maximumDistanceEartElectrodeWalOb;
	}

	public void setMaximumDistanceEartElectrodeWalOb(String maximumDistanceEartElectrodeWalOb) {
		this.maximumDistanceEartElectrodeWalOb = maximumDistanceEartElectrodeWalOb;
	}

	public String getMaximumDistanceEartElectrodeWalRem() {
		return maximumDistanceEartElectrodeWalRem;
	}

	public void setMaximumDistanceEartElectrodeWalRem(String maximumDistanceEartElectrodeWalRem) {
		this.maximumDistanceEartElectrodeWalRem = maximumDistanceEartElectrodeWalRem;
	}

	public String getManimumDistanceEartElectrodeWalOb() {
		return manimumDistanceEartElectrodeWalOb;
	}

	public void setManimumDistanceEartElectrodeWalOb(String manimumDistanceEartElectrodeWalOb) {
		this.manimumDistanceEartElectrodeWalOb = manimumDistanceEartElectrodeWalOb;
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

}