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
@Table(name = "SUMMARY_LPS_BUILDINGS_TABLE")
public class SummaryLpsBuildings implements Serializable {
	 
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "SUMMARY_LPS_BUILDINGS_ID")
		private Integer summaryLpsBuildingsId;
		
		@Column(name = "BUILDING_NAME")
		private String buildingName;
		
		@Column(name = "BUILDING_NUMBER")
		private Integer buildingNumber;
		
		@Column(name = "BUILDING_COUNT")
		private Integer buildingCount;
		
		@Column(name = "FLAG")
		private String flag;
		
		@JsonBackReference
		@ManyToOne
		@JoinColumn(name = "SUMMARY_LPS_ID")
		private SummaryLps summaryLps;
		
		@JsonManagedReference
		@OneToMany(mappedBy = "summaryLpsBuildings", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<SummaryLpsObservation> summaryLpsObservation;

		public Integer getSummaryLpsBuildingsId() {
			return summaryLpsBuildingsId;
		}

		public void setSummaryLpsBuildingsId(Integer summaryLpsBuildingsId) {
			this.summaryLpsBuildingsId = summaryLpsBuildingsId;
		}

		public String getBuildingName() {
			return buildingName;
		}

		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}

		public Integer getBuildingNumber() {
			return buildingNumber;
		}

		public void setBuildingNumber(Integer buildingNumber) {
			this.buildingNumber = buildingNumber;
		}

		public Integer getBuildingCount() {
			return buildingCount;
		}

		public void setBuildingCount(Integer buildingCount) {
			this.buildingCount = buildingCount;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public SummaryLps getSummaryLps() {
			return summaryLps;
		}

		public void setSummaryLps(SummaryLps summaryLps) {
			this.summaryLps = summaryLps;
		}

		public List<SummaryLpsObservation> getSummaryLpsObservation() {
			return summaryLpsObservation;
		}

		public void setSummaryLpsObservation(List<SummaryLpsObservation> summaryLpsObservation) {
			this.summaryLpsObservation = summaryLpsObservation;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
}
