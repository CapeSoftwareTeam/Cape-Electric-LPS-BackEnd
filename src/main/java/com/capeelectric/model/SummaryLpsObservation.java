/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "SUMMARY_LPS_OBSERVATION_TABLE")
public class SummaryLpsObservation implements Serializable  {
	
	private static final long serialVersionUID = -7161836502468880542L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUMMARY_LPS_OBSERVATION_ID")
	private Integer summaryLpsObservationId;
	
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
	@JoinColumn(name = "SUMMARY_LPS_BUILDINGS_ID")
	private SummaryLpsBuildings summaryLpsBuildings;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "summaryLpsObservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SummaryLpsInnerObservation> summaryLpsInnerObservation;

	public Integer getSummaryLpsObservationId() {
		return summaryLpsObservationId;
	}

	public void setSummaryLpsObservationId(Integer summaryLpsObservationId) {
		this.summaryLpsObservationId = summaryLpsObservationId;
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

	public SummaryLpsBuildings getSummaryLpsBuildings() {
		return summaryLpsBuildings;
	}

	public void setSummaryLpsBuildings(SummaryLpsBuildings summaryLpsBuildings) {
		this.summaryLpsBuildings = summaryLpsBuildings;
	}

	public List<SummaryLpsInnerObservation> getSummaryLpsInnerObservation() {
		return summaryLpsInnerObservation;
	}

	public void setSummaryLpsInnerObservation(List<SummaryLpsInnerObservation> summaryLpsInnerObservation) {
		this.summaryLpsInnerObservation = summaryLpsInnerObservation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
