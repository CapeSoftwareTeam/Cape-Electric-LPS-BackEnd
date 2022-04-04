package com.capeelectric.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.util.IOUtils;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.service.FileStorageService;

@RestController
@RequestMapping("/api/lps/v2")
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private FileStorageService storageService;

	@PostMapping("/upload/{lpsId}/{componentName}/{index}")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer lpsId,
			@PathVariable String componentName, @PathVariable Integer index)
			throws IOException, SerialException, SQLException {
		logger.debug("File Upload Start");

		storageService.store(file, lpsId, componentName, index);
		logger.debug("File Upload 	End");
		return new ResponseEntity<String>("File  Upload Successfully", HttpStatus.OK);
	}

	@GetMapping("/downloadFile/{lpsId}/{componentName}/{index}")
	public ResponseEntity<String> downloadFile(@PathVariable Integer lpsId, HttpServletResponse response,
			@PathVariable String componentName, @PathVariable Integer index) throws IOException, SQLException {
		logger.debug("DownloadFile File Start lpsId : {}", lpsId, componentName);
		ResponseFile fileDB = storageService.downloadFile(lpsId, componentName, index);
		response.setHeader("Content-Disposition", "inline; fileDB.getfileId()=\"" + fileDB.getFileId() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileDB.getFileName());
		IOUtils.copy(fileDB.getData().getBinaryStream(), out);
		out.flush();
		out.close();
		return null;

	}

	@GetMapping("/retrieveFileName/{lpsId}")
	public ResponseEntity<List<Map>> retrieveFileNameByLpsId(@PathVariable Integer lpsId)
			throws IOException, SQLException {
		logger.debug("Retrieve File Start lpsId : {}", lpsId);
		List<ResponseFile> fileDB = storageService.retrieveFileNameByLpsId(lpsId);

		if (null != fileDB) {
			List<Map> list = new ArrayList();
			for (ResponseFile responseFile : fileDB) {
				if (null != responseFile) {
					HashMap<String, String> hashMap = new HashMap<>();
					hashMap.put("fileId", responseFile.getFileId().toString());
					hashMap.put("fileType", responseFile.getFileType());
					hashMap.put("fileLpsId", responseFile.getLpsId().toString());
					hashMap.put("fileName", responseFile.getFileName());
					hashMap.put("componentName", responseFile.getComponentName());
					hashMap.put("index", responseFile.getIndex().toString());
					list.add(hashMap);
				}

			}
			return new ResponseEntity<List<Map>>(list, HttpStatus.OK);
		}
		return null;

	}

//	@PutMapping("/updateFile/{lpsId}/{componentName}/{index}")
//	public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file, @PathVariable Integer lpsId,@PathVariable String componentName,@PathVariable Integer index)
//			throws IOException, SerialException, SQLException {
//		logger.debug("UpdateFile File Start");
//		storageService.updateFile(file, lpsId, componentName,index);
//		logger.debug("UpdateFile File End");
//		return new ResponseEntity<String>("File Updated Successfully", HttpStatus.OK);
//	}
//
//	@DeleteMapping("/removeFile/{lpsId}/{componentName}/{index}")
//	public ResponseEntity<String> removeFile(@PathVariable Integer lpsId,@PathVariable String componentName,@PathVariable Integer index) throws IOException {
//		logger.debug("Remove File Start");
//		storageService.removeFile(lpsId, componentName,index );
//		logger.debug("Remove File End");
//		return new ResponseEntity<String>("File  Deleted Successfully", HttpStatus.OK);
//	}

	@PutMapping("/updateFile/{componentName}/{fileId}")
	public ResponseEntity<String> updateFile(@RequestParam("file") MultipartFile file,@PathVariable String componentName, @PathVariable Integer fileId)
			throws IOException, SerialException, SQLException {
		logger.debug("UpdateFile File Start");
		storageService.updateFile(file,componentName, fileId);
		logger.debug("UpdateFile File End");
		return new ResponseEntity<String>("File Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/removeFile/{fileId}")
	public ResponseEntity<String> removeFile(@PathVariable Integer fileId) throws IOException {
		logger.debug("Remove File Start");
		storageService.removeFile(fileId);
		logger.debug("Remove File End");
		return new ResponseEntity<String>("File  Deleted Successfully", HttpStatus.OK);
	}
}
