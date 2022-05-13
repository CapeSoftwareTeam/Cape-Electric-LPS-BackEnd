package com.capeelectric.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.AirBasicDescription;
import com.capeelectric.model.AirExpansion;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.FileDBRepository;
import com.capeelectric.repository.LpsAirExpansionAirTerminationRepository;
import com.capeelectric.repository.LpsBasicAirTerminationRepository;
import com.capeelectric.repository.LpsVerticalAirTerminationRepository;

@Service
public class FileStorageService {
	private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	@Autowired
	private FileDBRepository fileDBRepository;

	@Autowired
	private LpsVerticalAirTerminationRepository lpsVerticalAirTerminationRepository;

	@Autowired
	private LpsAirExpansionAirTerminationRepository lpsAirExpansionAirTerminationRepository;

	@Autowired
	private LpsBasicAirTerminationRepository lpsBasicAirTerminationRepository;

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
		fileDB.setStatus("A");
		logger.debug("File Saved In DB");
		fileDBRepository.save(fileDB);
	}

	public ResponseFile downloadFile(Integer fileId, String componentName, String fileName) throws IOException {
		if (fileId != null && fileId != 0) {
		Optional<ResponseFile> fileDB = fileDBRepository.findById(fileId);
			if (fileDB.isPresent() && fileDB.get()!=null) {
				return fileDB.get();
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
			Optional<ResponseFile> fileDB = fileDBRepository.findById(fileId);

			if (fileDB != null && fileDB.get().getFileId().equals(fileId)) {
				List<ResponseFile> responseFileRepo = fileDBRepository.findByLpsId(fileDB.get().getLpsId());
				for (ResponseFile responseFile : responseFileRepo) {
					if (fileDB.get().getIndex() == responseFile.getIndex()) {
						logger.info("File Deleted");
						fileDBRepository.delete(responseFile);
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

	public void updateFile(MultipartFile file, String componentName, Integer fileId, Integer index)
			throws SerialException, SQLException, IOException {
		if (fileId != null && fileId != 0) {
			Optional<ResponseFile> fileDB = fileDBRepository.findById(fileId);
			if (fileDB.isPresent() && fileDB.get().getFileId().equals(fileId)) {
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				Blob blob = new javax.sql.rowset.serial.SerialBlob(IOUtils.toByteArray(file.getInputStream()));
				fileDB.get().setFileName(fileName);
				fileDB.get().setData(blob);
				fileDB.get().setFileType(file.getContentType());
				fileDB.get().setComponentName(componentName);
				fileDB.get().setIndex(index);
				fileDB.get().setStatus("A");
				logger.debug("File Update In DB");
				fileDBRepository.save(fileDB.get());
			} else {
				logger.error("Id Not Preset");
				throw new IOException("File Update failed");
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

	public void updateFileId(Integer basicLpsId, Integer index) {
		// TODO Auto-generated method stub
		if (basicLpsId != null && basicLpsId != 0) {
			List<ResponseFile> fileDB = fileDBRepository.findByLpsId(basicLpsId);
			for (ResponseFile responseFile : fileDB) {
				if (index < responseFile.getIndex()) {
					if (index.equals(0)) {
						responseFile.setIndex(responseFile.getIndex() - 1);
					} else {
						responseFile.setIndex(responseFile.getIndex() - index);
					}

//					fileDBRepository.save(responseFile);
				}
			}
		} else {
			logger.error("Id Not Present");
		}
	}

	public void updateAllFileId(List<String> listOfResponseFile, Integer basicLpsId) {
		List<ResponseFile> fileRepo = fileDBRepository.findByLpsId(basicLpsId);
		Set<Integer> allFileIds = new TreeSet<Integer>();
		List<Integer> listOfFileIdFromUI = listOfResponseFile.stream().map(listOfResponseFileId -> {
			if (Integer.parseInt(listOfResponseFileId.split("-")[0]) != 0) {
				allFileIds.add(Integer.parseInt(listOfResponseFileId.split("-")[0]));
			}
			return Integer.parseInt(listOfResponseFileId.split("-")[0]);
		}).collect(Collectors.toList());

		if (fileRepo.size() != 0) {

			List<Integer> listOfFileIdFromDB = fileRepo.stream().map(responseFile -> {
				if (filterAirtermation(responseFile) != null) {
					allFileIds.add(filterAirtermation(responseFile));
				}
				return filterAirtermation(responseFile);
			}).collect(Collectors.toList());

			listOfFileIdFromUI.retainAll(listOfFileIdFromDB);

			for (ResponseFile responseFile : fileRepo) { // updating Object

				for (Integer integer : listOfFileIdFromUI) { // repo fileIds
					if (responseFile.getFileId().equals(integer)) {
						for (String fileIdList : listOfResponseFile) { // UI fileIds
							String[] split = fileIdList.split("-");
							if (responseFile.getFileId().equals(Integer.parseInt(split[0]))) {
								responseFile.setIndex(Integer.parseInt(split[1]));
								responseFile.setBuildingCount(Integer.parseInt(split[3]));
								fileDBRepository.save(responseFile);
								logger.debug(integer + " This fileId updated");
								for (Integer fileId : allFileIds) { // finding deleted fileIds
									if (fileId.equals(responseFile.getFileId())) {
										allFileIds.remove(fileId);
										break;
									}
								}
							}
						}
					}

				}
			}
			// deleteUnusedFileIds(allFileIds, basicLpsId, listOfResponseFile);
		}
		deleteUnusedFileIds(allFileIds, basicLpsId, listOfResponseFile);
	}

	private Integer filterAirtermation(ResponseFile responseFile) {
		if (!responseFile.getComponentName().startsWith("downConductor")) {
			return responseFile.getFileId();
		}
		return null;
	}

	private void deleteUnusedFileIds(Set<Integer> removeItem, Integer basicLpsId, List<String> listOfResponseFile) {

		for (Integer integer : removeItem) {
			Optional<ResponseFile> responseFile = fileDBRepository.findById(integer);
			if (responseFile.isPresent()) {
				fileDBRepository.deleteById(integer);
				logger.debug(integer + " This fileId deleted");
			} else {
				for (String componentName : listOfResponseFile) {
					String[] split = componentName.split("-");
					if (integer.equals(Integer.parseInt(split[0]))) {
						removeFileDetailsFromAirterminationdata(split[0], split[2]);
					}
				}
			}
		}
	}

	private void removeFileDetailsFromAirterminationdata(String fileId, String componetName) {

		if (componetName.equalsIgnoreCase("airUpload")) {
			Optional<AirBasicDescription> basicDescription = lpsBasicAirTerminationRepository.findByFileId(fileId);
			if (basicDescription.isPresent()) {
				basicDescription.get().setFileId(null);
				basicDescription.get().setFileIndex(null);
				basicDescription.get().setFileName(null);
				basicDescription.get().setFileSize(null);
				basicDescription.get().setFileType(null);
				lpsBasicAirTerminationRepository.save(basicDescription.get());
				logger.debug(basicDescription.get().getAirBasicDescriptionId()
						+ " This AirBasicDescription fileDetails data Reseted");
			}

		} else if (componetName.equalsIgnoreCase("airUpload_1")) {
			Optional<LpsVerticalAirTermination> lpsVertivalAirtermination = lpsVerticalAirTerminationRepository
					.findByFileIdVAir(fileId);
			if (lpsVertivalAirtermination.isPresent()) {
				lpsVertivalAirtermination.get().setFileIdVAir(null);
				lpsVertivalAirtermination.get().setFileIndexVAir(null);
				lpsVertivalAirtermination.get().setFileNameVAir(null);
				lpsVertivalAirtermination.get().setFileSize(null);
				lpsVertivalAirtermination.get().setFileTypeVAir(null);
				lpsVerticalAirTerminationRepository.save(lpsVertivalAirtermination.get());
				logger.debug(lpsVertivalAirtermination.get().getLpsVerticalAirTerminationId()
						+ " This LpsVerticalAirTermination fileDetails data Reseted");
			}

		} else if (componetName.equalsIgnoreCase("airUpload_2")) {
			Optional<AirExpansion> airExpansion = lpsAirExpansionAirTerminationRepository
					.findByFileIdEP(Integer.parseInt(fileId));
			if (airExpansion.isPresent()) {
				airExpansion.get().setFileIdEP(null);
				airExpansion.get().setFileIndex_EP(null);
				airExpansion.get().setFileName_EP(null);
				airExpansion.get().setFileSize(null);
				airExpansion.get().setFileType_EP(null);
				lpsAirExpansionAirTerminationRepository.save(airExpansion.get());
				logger.debug(airExpansion.get().getExpansionId() + " This AirExpansion fileDetails data Reseted");
			}
		}
	}
}
