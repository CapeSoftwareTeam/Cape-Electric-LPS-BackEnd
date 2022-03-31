package com.capeelectric.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.FileDBRepository;

@Service
public class FileStorageService {
	private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	@Autowired
	private FileDBRepository fileDBRepository;

	public void store(MultipartFile file, Integer lpsId, String componentName)
			throws IOException, SerialException, SQLException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
		ResponseFile FileDB = new ResponseFile();
		FileDB.setLpsId(lpsId);
		FileDB.setFileName(fileName);
		FileDB.setData(blob);
		FileDB.setFileType(file.getContentType());
		FileDB.setComponentName(componentName);
		logger.debug("File Saved In DB");
		fileDBRepository.save(FileDB);
	}

	public ResponseFile downloadFile(Integer lpsId, String componentName) throws IOException {
		if (lpsId != null && lpsId != 0) {
			ResponseFile fileDB = fileDBRepository.findByLpsIdAndComponentName(lpsId, componentName);
			if (fileDB != null && fileDB.getLpsId().equals(lpsId)) {
				return fileDB;
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}
		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public void removeFile(Integer fileId) throws IOException {
		if (fileId != null && fileId != 0) {
			ResponseFile fileDB = fileDBRepository.findById(fileId).get();
			if (fileDB != null && fileDB.getFileId().equals(fileId)) {
				logger.info("File Deleted");
				fileDBRepository.delete(fileDB);
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}

		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public void updateFile(MultipartFile file, Integer fileId) throws SerialException, SQLException, IOException {
		if (fileId != null && fileId != 0) {
			ResponseFile fileDB = fileDBRepository.findById(fileId).get();
			if (fileDB != null && fileDB.getFileId().equals(fileId)) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
				// fileDB.setEmcId(emcId);
				fileDB.setFileName(fileName);
				fileDB.setData(blob);
				fileDB.setFileType(file.getContentType());
				logger.debug("File Update In DB");
				fileDBRepository.save(fileDB);
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}
		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public List<ResponseFile> retrieveFileNameByLpsId(Integer lpsId) throws IOException {
		if (lpsId != null && lpsId != 0) {
			List<ResponseFile> fileDB = fileDBRepository.findByLpsId(lpsId);
			if (!fileDB.isEmpty()) {
				return fileDB;
			}
		} else {
			logger.error("Id Not Present");
		}
		return null;
	}
}
