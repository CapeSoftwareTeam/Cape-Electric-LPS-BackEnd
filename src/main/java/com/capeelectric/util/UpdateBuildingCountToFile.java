/**
 * 
 */
package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.AirBasicDescription;
import com.capeelectric.model.AirExpansion;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.model.ResponseFile;
import com.capeelectric.repository.DownConductorDescriptionRepository;
import com.capeelectric.repository.DownDescConductorRepository;
import com.capeelectric.repository.FileDBRepository;
import com.capeelectric.repository.LpsAirExpansionAirTerminationRepository;
import com.capeelectric.repository.LpsBasicAirTerminationRepository;
import com.capeelectric.repository.LpsVerticalAirTerminationRepository;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Configuration
public class UpdateBuildingCountToFile {
	private static final Logger logger = LoggerFactory.getLogger(UpdateBuildingCountToFile.class);
	
	@Autowired
	private FileDBRepository fileDBRepository;
	
	@Autowired
	private LpsVerticalAirTerminationRepository lpsVerticalAirTerminationRepository;

	@Autowired
	private LpsBasicAirTerminationRepository lpsBasicAirTerminationRepository;
	
	@Autowired
	private LpsAirExpansionAirTerminationRepository lpsAirExpansionAirTerminationRepository;
	
	@Autowired
	private DownDescConductorRepository downDescConductorRepository;
	
	@Autowired
	private DownConductorDescriptionRepository downConductorDescriptionRepository;
  
	public void updateAirterminationBuildingCount(AirTermination termination) {
		 
		List<ResponseFile> responseFileRepo = fileDBRepository.findByLpsId(termination.getBasicLpsId());
		List<LpsAirDiscription> lpsAirDescription = termination.getLpsAirDescription();
		if (responseFileRepo != null && !responseFileRepo.isEmpty() && lpsAirDescription !=null && !lpsAirDescription.isEmpty()) {
			for (LpsAirDiscription lpsAirDiscription : lpsAirDescription) {
			 
				if (lpsAirDiscription.getAirBasicDescription() !=null && !lpsAirDiscription.getAirBasicDescription().isEmpty()) {
					for (AirBasicDescription airBasicDescription : lpsAirDiscription.getAirBasicDescription()) {
						if (airBasicDescription.getFileId() != null
								&& !updateFile(lpsAirDiscription.getBuildingCount(), Integer.valueOf(airBasicDescription.getFileId()),airBasicDescription.getFileIndex())) {
							airBasicDescription.setFileId(null);
							airBasicDescription.setFileIndex(null);
							airBasicDescription.setFileName(null);
							airBasicDescription.setFileSize(null);
							airBasicDescription.setFileType(null);
							lpsBasicAirTerminationRepository.save(airBasicDescription);
						} 
					}
				}
				for (AirExpansion airExpansion : lpsAirDiscription.getAirExpansion()) {
					if (airExpansion.getFileIdEP() != null
							&& !updateFile(lpsAirDiscription.getBuildingCount(), airExpansion.getFileIdEP(),airExpansion.getFileIndex_EP())) {
						airExpansion.setFileIdEP(null);
						airExpansion.setFileIndex_EP(null);
						airExpansion.setFileName_EP(null);
						airExpansion.setFileSize(null);
						airExpansion.setFileType_EP(null);
						lpsAirExpansionAirTerminationRepository.save(airExpansion);
					} 
				}
			 
				
				for (LpsVerticalAirTermination lpsVerticalAirTermination : lpsAirDiscription.getLpsVerticalAirTermination()) {
					if (lpsVerticalAirTermination.getFileIdVAir() != null
							&& !updateFile(lpsAirDiscription.getBuildingCount(), lpsVerticalAirTermination.getFileIdVAir(),lpsVerticalAirTermination.getFileIndexVAir())) {
						lpsVerticalAirTermination.setFileIdVAir(null);
						lpsVerticalAirTermination.setFileIndexVAir(null);
						lpsVerticalAirTermination.setFileNameVAir(null);
						lpsVerticalAirTermination.setFileSize(null);
						lpsVerticalAirTermination.setFileTypeVAir(null);
						lpsVerticalAirTerminationRepository.save(lpsVerticalAirTermination);
					} 
				}
			}
			 
		}
		removeUnusedFiles(termination.getBasicLpsId());
	}


	private boolean updateFile(Integer buildingCount, Integer fileId,Integer index) {
		if(fileId!=null &&buildingCount!=null) {
			Optional<ResponseFile> responseFile = fileDBRepository.findById(fileId);
			if (responseFile.isPresent()) {
				responseFile.get().setBuildingCount(buildingCount);
				responseFile.get().setIndex(index);
				responseFile.get().setStatus("U"); 
				fileDBRepository.save(responseFile.get());
				return true;
			}
		}
		return false;
	}
	
	public void removeUnusedFiles(Integer basicLpsId) {
		List<ResponseFile> responseFileRepo = fileDBRepository.findByLpsIdBuildingCount(basicLpsId,null);
		if(!responseFileRepo.isEmpty() && responseFileRepo.size()!=0) {
			//for (ResponseFile responseFile : responseFileRepo) {
				fileDBRepository.deleteAll(responseFileRepo);
				logger.debug("Removed which file doesn't have buildingcount");
			//}
		}
	}


	public void updateDownConductorBuildingCount(DownConductorReport downConductorReportRepo) {
		List<ResponseFile> responseFileRepo = fileDBRepository.findByLpsId(downConductorReportRepo.getBasicLpsId());
		List<DownConductorDescription> downConductorDescription = downConductorReportRepo.getDownConductorDescription();
		List<DownConductorDescription> downConductorDescriptionConcurent = new ArrayList<DownConductorDescription>();
		if (!responseFileRepo.isEmpty() && responseFileRepo.size()!=0 &&
				!downConductorDescription.isEmpty() && downConductorDescription.size()!=0)  {
			for (DownConductorDescription downConductors : downConductorDescription) {
				boolean flag=false;
				if (downConductors.getFileId1() !=null && 
						!updateFile(downConductors.getBuildingCount(), downConductors.getFileId1(),downConductors.getIndex())) {
					downConductors.setFileId1(null);
					downConductors.setFileName1(null);
					downConductors.setFileSize(null);
					downConductors.setFileType1(null);
				//	downConductorDescriptionRepository.save(downConductors);
					flag=true;
					
				}
				if (!downConductors.getDownConductor().isEmpty() && 
						downConductors.getDownConductor().size() !=0 && 
						!updateFile(downConductors.getBuildingCount(), downConductors.getDownConductor().get(0).getFileId() ,downConductors.getDownConductor().get(0).getIndex())) {
					 DownConductor downConductor = downConductors.getDownConductor().get(0);
					 downConductor.setFileId(null);
					 downConductor.setFileName(null);
					 downConductor.setFileSize(null);
					 downConductor.setFileType(null);
					// downDescConductorRepository.save(downConductor);
					 flag=true;
				}
				if(flag) {
					downConductorDescriptionConcurent.add(downConductors);
				}
				
			}
		}
		updateFileDetailsToDownCondutor(downConductorDescriptionConcurent);
		removeUnusedFiles(downConductorReportRepo.getBasicLpsId());
	}


	private void updateFileDetailsToDownCondutor(List<DownConductorDescription> downConductorDescriptionConcurent) {
		
		if (downConductorDescriptionConcurent !=null && !downConductorDescriptionConcurent.isEmpty()) {
			downConductorDescriptionRepository.saveAll(downConductorDescriptionConcurent);
		}
		
	}
}
