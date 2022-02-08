/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;

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
@Table(name = "SUMMARY_LPS_INNER_OBSERVATION_TABLE")
public class SummaryLpsInnerObservation implements Serializable  {
	
	private static final long serialVersionUID = -7161836502468880542L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUMMARY_LPS_INNER_OBSERVATION_ID")
	private Integer summaryLpsInnerObservationId;
	
	@Column(name = "SERIAL_NO")
	private Integer serialNo;
	
	@Column(name = "OBSERVATION")
	private String observation;
	
	@Column(name = "RECOMMENDATION")
	private String recommendation;
	
	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUMMARY_LPS_OBSERVATION_ID")
	private SummaryLpsObservation summaryLpsObservation;

	public Integer getSummaryLpsInnerObservationId() {
		return summaryLpsInnerObservationId;
	}

	public void setSummaryLpsInnerObservationId(Integer summaryLpsInnerObservationId) {
		this.summaryLpsInnerObservationId = summaryLpsInnerObservationId;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}

	public SummaryLpsObservation getSummaryLpsObservation() {
		return summaryLpsObservation;
	}

	public void setSummaryLpsObservation(SummaryLpsObservation summaryLpsObservation) {
		this.summaryLpsObservation = summaryLpsObservation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
