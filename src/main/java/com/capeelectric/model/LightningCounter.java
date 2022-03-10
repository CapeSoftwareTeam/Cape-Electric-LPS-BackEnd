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
@Table(name = "LIGHTNINGCOUNTERS_TABLE")
public class LightningCounter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LIGHTNINGCOUNTERS_ID")
	private Integer lightingCountersId;

	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "THREADHOLD_CURRENT_OB")
	private Integer threadHoldCurrentOb;

	@Column(name = "THREADHOLD_CURRENT_REM")
	private String threadHoldCurrentRem;

	@Column(name = "MAXIMUMWITHSTAND_CURRENT_OB")
	private Integer maximumWithStandCurrentOb;

	@Column(name = "MAXIMUMWITHSTAND_CURRENT_REM")
	private String maximumWithStandCurrentRem;

	@Column(name = "COUNTS_OB")
	private Integer countsOb;

	@Column(name = "COUNTS_REM")
	private String countsRem;

	@Column(name = "BATTERY_LIFETIME_OB")
	private Integer batteryLifeTimeOb;

	@Column(name = "BATTERY_LIFETIME_REM")
	private String batteryLifeTimeRem;

	@Column(name = "PROPERCONNECTION_LIGHTINGCOUNTER_OB")
	private String properConnectionLightingCounterOb;

	@Column(name = "PROPERCONNECTION_LIGHTINGCOUNTER_REM")
	private String properConnectionLightingCounterRem;

	@Column(name = "LIGHTINGCOUNTER_PLACED_OB")
	private String lightingCounterPlacedOb;

	@Column(name = "LIGHTINGCOUNTER_PLACED_REM")
	private String lightingCounterPlacedRem;

	@Column(name = "CONDITION_OF_LIGHTINGCOUNTER_OB")
	private String conditionOfLightingCounterOb;

	@Column(name = "CONDITION_OF_LIGHTINGCOUNTER_REM")
	private String conditionOfLightingCounterRem;

	@Column(name = "TOTALNO_LIGHTNINGCOUNTER_OB")
	private Integer totalNoLightingCounterOb;

	@Column(name = "TOTALNO_LIGHTNINGCOUNTER_REM")
	private String totalNoLightingCounterRem;

	@Column(name = "INSPECTED_NO_OB")
	private Integer inspectedNoOb;

	@Column(name = "INSPECTED_NO_REM")
	private String inspectedNoRem;

	@Column(name = "INSPECTIONSPASSED_NO_OB")
	private Integer inspectionPassedNoOb;

	@Column(name = "INSPECTIONSPASSED_NO_REM")
	private String inspectionPassedNoRem;

	@Column(name = "INSPECTIONFAILED_NO_OB")
	private Integer inspectionFailedNoOb;

	@Column(name = "INSPECTIONFAILED_NO_REM")
	private String inspectionFailedNoRem;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DOWNCONDUCTORDESCRIPTION_ID")
	private DownConductorDescription downConductorDescription;

	public Integer getLightingCountersId() {
		return lightingCountersId;
	}

	public void setLightingCountersId(Integer lightingCountersId) {
		this.lightingCountersId = lightingCountersId;
	}

	public String getThreadHoldCurrentRem() {
		return threadHoldCurrentRem;
	}

	public void setThreadHoldCurrentRem(String threadHoldCurrentRem) {
		this.threadHoldCurrentRem = threadHoldCurrentRem;
	}

	public String getMaximumWithStandCurrentRem() {
		return maximumWithStandCurrentRem;
	}

	public void setMaximumWithStandCurrentRem(String maximumWithStandCurrentRem) {
		this.maximumWithStandCurrentRem = maximumWithStandCurrentRem;
	}

	public String getCountsRem() {
		return countsRem;
	}

	public void setCountsRem(String countsRem) {
		this.countsRem = countsRem;
	}

	public String getBatteryLifeTimeRem() {
		return batteryLifeTimeRem;
	}

	public void setBatteryLifeTimeRem(String batteryLifeTimeRem) {
		this.batteryLifeTimeRem = batteryLifeTimeRem;
	}

	public String getProperConnectionLightingCounterOb() {
		return properConnectionLightingCounterOb;
	}

	public void setProperConnectionLightingCounterOb(String properConnectionLightingCounterOb) {
		this.properConnectionLightingCounterOb = properConnectionLightingCounterOb;
	}

	public String getProperConnectionLightingCounterRem() {
		return properConnectionLightingCounterRem;
	}

	public void setProperConnectionLightingCounterRem(String properConnectionLightingCounterRem) {
		this.properConnectionLightingCounterRem = properConnectionLightingCounterRem;
	}

	public String getLightingCounterPlacedOb() {
		return lightingCounterPlacedOb;
	}

	public void setLightingCounterPlacedOb(String lightingCounterPlacedOb) {
		this.lightingCounterPlacedOb = lightingCounterPlacedOb;
	}

	public String getLightingCounterPlacedRem() {
		return lightingCounterPlacedRem;
	}

	public void setLightingCounterPlacedRem(String lightingCounterPlacedRem) {
		this.lightingCounterPlacedRem = lightingCounterPlacedRem;
	}

	public String getConditionOfLightingCounterOb() {
		return conditionOfLightingCounterOb;
	}

	public void setConditionOfLightingCounterOb(String conditionOfLightingCounterOb) {
		this.conditionOfLightingCounterOb = conditionOfLightingCounterOb;
	}

	public String getConditionOfLightingCounterRem() {
		return conditionOfLightingCounterRem;
	}

	public void setConditionOfLightingCounterRem(String conditionOfLightingCounterRem) {
		this.conditionOfLightingCounterRem = conditionOfLightingCounterRem;
	}

	public String getTotalNoLightingCounterRem() {
		return totalNoLightingCounterRem;
	}

	public void setTotalNoLightingCounterRem(String totalNoLightingCounterRem) {
		this.totalNoLightingCounterRem = totalNoLightingCounterRem;
	}

	public String getInspectedNoRem() {
		return inspectedNoRem;
	}

	public void setInspectedNoRem(String inspectedNoRem) {
		this.inspectedNoRem = inspectedNoRem;
	}

	public String getInspectionPassedNoRem() {
		return inspectionPassedNoRem;
	}

	public void setInspectionPassedNoRem(String inspectionPassedNoRem) {
		this.inspectionPassedNoRem = inspectionPassedNoRem;
	}

	public String getInspectionFailedNoRem() {
		return inspectionFailedNoRem;
	}

	public void setInspectionFailedNoRem(String inspectionFailedNoRem) {
		this.inspectionFailedNoRem = inspectionFailedNoRem;
	}

	public DownConductorDescription getDownConductorDescription() {
		return downConductorDescription;
	}

	public void setDownConductorDescription(DownConductorDescription downConductorDescription) {
		this.downConductorDescription = downConductorDescription;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getThreadHoldCurrentOb() {
		return threadHoldCurrentOb;
	}

	public void setThreadHoldCurrentOb(Integer threadHoldCurrentOb) {
		this.threadHoldCurrentOb = threadHoldCurrentOb;
	}

	public Integer getMaximumWithStandCurrentOb() {
		return maximumWithStandCurrentOb;
	}

	public void setMaximumWithStandCurrentOb(Integer maximumWithStandCurrentOb) {
		this.maximumWithStandCurrentOb = maximumWithStandCurrentOb;
	}

	public Integer getCountsOb() {
		return countsOb;
	}

	public void setCountsOb(Integer countsOb) {
		this.countsOb = countsOb;
	}

	public Integer getBatteryLifeTimeOb() {
		return batteryLifeTimeOb;
	}

	public void setBatteryLifeTimeOb(Integer batteryLifeTimeOb) {
		this.batteryLifeTimeOb = batteryLifeTimeOb;
	}

	public Integer getTotalNoLightingCounterOb() {
		return totalNoLightingCounterOb;
	}

	public void setTotalNoLightingCounterOb(Integer totalNoLightingCounterOb) {
		this.totalNoLightingCounterOb = totalNoLightingCounterOb;
	}

	public Integer getInspectedNoOb() {
		return inspectedNoOb;
	}

	public void setInspectedNoOb(Integer inspectedNoOb) {
		this.inspectedNoOb = inspectedNoOb;
	}

	public Integer getInspectionPassedNoOb() {
		return inspectionPassedNoOb;
	}

	public void setInspectionPassedNoOb(Integer inspectionPassedNoOb) {
		this.inspectionPassedNoOb = inspectionPassedNoOb;
	}

	public Integer getInspectionFailedNoOb() {
		return inspectionFailedNoOb;
	}

	public void setInspectionFailedNoOb(Integer inspectionFailedNoOb) {
		this.inspectionFailedNoOb = inspectionFailedNoOb;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
