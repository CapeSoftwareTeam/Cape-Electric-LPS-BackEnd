package com.capeelectric.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
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

	public void store(MultipartFile file, Integer lpsId, String componentName, Integer index)
			throws IOException, SerialException, SQLException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
		ResponseFile fileDB = new ResponseFile();
		fileDB.setLpsId(lpsId);
		fileDB.setFileName(fileName);
		fileDB.setData(blob);
		fileDB.setFileType(file.getContentType());
		fileDB.setComponentName(componentName);
		fileDB.setIndex(index);
		logger.debug("File Saved In DB");
		fileDBRepository.save(fileDB);
	}

	public ResponseFile downloadFile(Integer lpsId, String componentName, String fileName) throws IOException {
		if (lpsId != null && lpsId != 0 && fileName != null) {
			ResponseFile fileDB = fileDBRepository.findByLpsIdAndComponentNameAndFileName(lpsId, componentName, fileName);
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
			List<ResponseFile> responseFileRepo = fileDBRepository.findByLpsId(fileDB.getLpsId());

			if (fileDB != null && fileDB.getFileId().equals(fileId)) {
				for (ResponseFile responseFile : responseFileRepo) {
					if(fileDB.getIndex() == responseFile.getIndex()) {
						logger.info("File Deleted");
						fileDBRepository.delete(responseFile);
					}
					else if(fileDB.getIndex()<responseFile.getIndex()) {
						if (fileDB.getIndex().equals(0)) {
							responseFile.setIndex(responseFile.getIndex()-1);
						} else {
							responseFile.setIndex(responseFile.getIndex()-fileDB.getIndex());
						}
						
						fileDBRepository.save(responseFile);
					}
			}
				
			 
			} else {
				logger.error("File Not Preset");
				throw new IOException("File Not Preset");
			}

		} else {
			logger.error("Id Not Preset");
			throw new IOException("Id Not Preset");
		}

	}

	public void updateFile(MultipartFile file, String componentName, Integer fileId)
			throws SerialException, SQLException, IOException {
		if (fileId != null && fileId != 0) {
			ResponseFile fileDB = fileDBRepository.findById(fileId).get();
			if (fileDB != null && fileDB.getFileId().equals(fileId)) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
				// fileDB.setEmcId(emcId);
				fileDB.setFileName(fileName);
				fileDB.setData(blob);
				fileDB.setFileType(file.getContentType());
				fileDB.setComponentName(componentName);
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

//	public void removeFile(Integer lpsId, String componentName, Integer index) throws IOException {
//		if (lpsId != null&&componentName != null) {
//			ResponseFile fileDB = fileDBRepository.findByLpsIdAndComponentNameAndIndex(lpsId,componentName,index);
//			if (fileDB != null && fileDB.getLpsId().equals(lpsId)) {
//				logger.info("File Deleted");
//				fileDBRepository.delete(fileDB);
//			} else {
//				logger.error("File Not Preset");
//				throw new IOException("File Not Preset");
//			}
//
//		} else {
//			logger.error("Id Not Preset");
//			throw new IOException("Id Not Preset");
//		}
//
//	}
//
//	public void updateFile(MultipartFile file, Integer lpsId, String componentName, Integer index) throws SerialException, SQLException, IOException {
//		if (lpsId != null && lpsId != 0) {
//			ResponseFile fileDB = fileDBRepository.findByLpsIdAndComponentNameAndIndex(lpsId,componentName,index);
//			if (fileDB != null && fileDB.getLpsId().equals(lpsId)) {
//				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
//				// fileDB.setEmcId(emcId);
//				fileDB.setFileName(fileName);
//				fileDB.setData(blob);
//				fileDB.setFileType(file.getContentType());
//				fileDB.setComponentName(componentName);
//				fileDB.setIndex(index);
//				logger.debug("File Update In DB");
//				fileDBRepository.save(fileDB);
//			} else {
//				logger.error("File Not Preset");
//				throw new IOException("File Not Preset");
//			}
//		} else {
//			logger.error("Id Not Preset");
//			throw new IOException("Id Not Preset");
//		}
//
//	}

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

	public void updateFileId(Integer basicLpsId, Integer index) {
		// TODO Auto-generated method stub
		if (basicLpsId != null && basicLpsId != 0) {
			List<ResponseFile> fileDB = fileDBRepository.findByLpsId(basicLpsId);
			for (ResponseFile responseFile : fileDB) {
				if(index == responseFile.getIndex()) {
					fileDBRepository.delete(responseFile);
				}
				else if(index<responseFile.getIndex()) {
					if (index.equals(0)) {
						responseFile.setIndex(responseFile.getIndex()-1);	
					} else {
						responseFile.setIndex(responseFile.getIndex()-index);
					}
					
//					fileDBRepository.save(responseFile);
				}
				}
		} else {
			logger.error("Id Not Present");
		}
	}

	public void updateAllFileId(List<String> listOfResponseFile, Integer basicLpsId) {
		// TODO Auto-generated method stub
		List<ResponseFile> fileRepo = fileDBRepository.findByLpsId(basicLpsId);
		for (ResponseFile responseFile : fileRepo) {
			for (String fileIdList:listOfResponseFile) {
			String[] split = fileIdList.split("-");
			;
				if(responseFile.getFileId().equals(Integer.parseInt(split[0]))) {
					responseFile.setIndex(Integer.parseInt(split[1]));
				}
			}
		}
		fileDBRepository.saveAll(fileRepo);
	}
}
