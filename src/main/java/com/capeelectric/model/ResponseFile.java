package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "FILE_UPLOAD_LPS_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "FileDBRepository.findByLpsId", query = "select s from ResponseFile s where s.lpsId=:lpsId"),
 
})
public class ResponseFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILE_ID")
	private Integer fileId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "LPS_ID")
	private Integer lpsId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "DATA")
	private Blob data;

	@Column(name = "COMPONENT_NAME")
	private String componentName;
	
	@Column(name = "INDEX_NU")
	private Integer index;
	
	@Column(name = "BUILDING_COUNT")
	private Integer buildingCount;
	
	@Column(name = "STATUS")
	private String status;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getLpsId() {
		return lpsId;
	}

	public void setLpsId(Integer lpsId) {
		this.lpsId = lpsId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getBuildingCount() {
		return buildingCount;
	}

	public void setBuildingCount(Integer buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
