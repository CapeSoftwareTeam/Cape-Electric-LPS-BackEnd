/**
 * 
 */
package com.capeelectric.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.model.SpdReport;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.EarthStudService;
import com.capeelectric.service.PrintAirTerminationService;
import com.capeelectric.service.PrintBasicLpsService;
import com.capeelectric.service.PrintDownConductorService;
import com.capeelectric.service.PrintEarthingLpsService;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintSDandEarthStudService;
import com.capeelectric.service.PrintSPDService;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.HeaderFooterPageEvent;
import com.capeelectric.util.UserFullName;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This EarthStudServiceImpl service class doing save and retrieve operation
 * related to EarthStudDetails
 * 
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EarthStudServiceImpl implements EarthStudService {
	private static final Logger logger = LoggerFactory.getLogger(EarthStudServiceImpl.class);

	@Autowired
	private EarthStudRepository earthStudRepository;

	@Autowired
	private PrintBasicLpsService printBasicLpsService;

	@Autowired
	private PrintAirTerminationService printAirTerminationService;

	@Autowired
	private PrintDownConductorService printDownConductorService;

	@Autowired
	private PrintEarthingLpsService printEarthingLpsService;

	@Autowired
	private PrintSPDService printSPDService;

	@Autowired
	private PrintSDandEarthStudService printSDandEarthStudService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	private BasicLps basicLps;

	@Autowired
	private UserFullName userFullName;

	@Autowired
	private DownConductorRepository downConductorRepository;

	@Autowired
	private EarthingLpsRepository earthingLpsRepository;

	@Autowired
	private SPDRepository spdRepository;

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@Autowired
	private SeperationDistanceRepository seperationDistanceRepository;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;

	@Transactional
	@Override
	public void addEarthStudDetails(EarthStudReport earthStudReport)
			throws EarthStudException, BasicLpsException, AirTerminationException, DownConductorException,
			EarthingLpsException, SPDException, Exception {
		if (earthStudReport != null && earthStudReport.getUserName() != null
				&& !earthStudReport.getUserName().isEmpty() && earthStudReport.getBasicLpsId() != null
				&& earthStudReport.getBasicLpsId() != 0) {
			//For Basic Lps data
			Optional<BasicLps> basicLpsDetails = basicLpsRepository
					.findByBasicLpsId(earthStudReport.getBasicLpsId());
			
			//For Air Termination data
			Optional<AirTermination> lpsAirDisc = airTerminationLpsRepository
					.findByBasicLpsId(earthStudReport.getBasicLpsId());
			
			//For Down Conductor data
			Optional<DownConductorReport> downConductorDetails = downConductorRepository
					.findByBasicLpsId(earthStudReport.getBasicLpsId());
			
			//For Earthing Lps data
			Optional<EarthingReport> earthingLpsDetails = earthingLpsRepository
					.findByBasicLpsId(earthStudReport.getBasicLpsId());
			
			//For SPD data
			Optional<SpdReport> spdDetails = spdRepository.findByBasicLpsId(earthStudReport.getBasicLpsId());
			
			//For Seperation Distance data
			Optional<SeperationDistanceReport> separateDistanceDetails = seperationDistanceRepository
					.findByBasicLpsId(earthStudReport.getBasicLpsId());

			if (!basicLpsDetails.isPresent() && !lpsAirDisc.isPresent() && !downConductorDetails.isPresent()
					&& !earthingLpsDetails.isPresent() && !separateDistanceDetails.isPresent()
					&& !spdDetails.isPresent()) {
				logger.error("Please enter details for all previous steps to proceed further");
				throw new EarthStudException("Please enter details for all previous steps to proceed further");
			} else if (!basicLpsDetails.isPresent()) {
				logger.error("Please enter Basic Information step to proceed further");
				throw new EarthStudException("Please enter Basic Information step to proceed further");
			} else if (!lpsAirDisc.isPresent()) {
				logger.error("Please enter Air Termination step to proceed further");
				throw new EarthStudException("Please enter Air Termination step to proceed further");
			} else if (!downConductorDetails.isPresent()) {
				logger.error("Please enter Down Conductors step to proceed further");
				throw new EarthStudException("Please enter Down Conductors step to proceed further");
			} else if (!earthingLpsDetails.isPresent()) {
				logger.error("Please enter Earthing step to proceed further");
				throw new EarthStudException("Please enter Earthing step to proceed further");
			} else if (!spdDetails.isPresent()) {
				logger.error("Please enter SPD step to proceed further");
				throw new EarthStudException("Please enter SPD step to proceed further");

			} else if (!separateDistanceDetails.isPresent()) {
				logger.error("Please enter Seperation Distance step to proceed further");
				throw new EarthStudException("Please enter Seperation Distance step to proceed further");
			}
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudReport.getBasicLpsId());
			if (basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthStudReport.getBasicLpsId())) {
				Optional<EarthStudReport> earthStudRepo = earthStudRepository
						.findByBasicLpsId(earthStudReport.getBasicLpsId());
				if (!earthStudRepo.isPresent()
						|| !earthStudRepo.get().getBasicLpsId().equals(earthStudReport.getBasicLpsId())) {
					
					List<EarthStudDescription> earthStudDescription = earthStudReport.getEarthStudDescription();
					if(earthStudDescription != null && earthStudDescription.size() > 0) {
						earthStudReport.setCreatedDate(LocalDateTime.now());
						earthStudReport.setUpdatedDate(LocalDateTime.now());
						earthStudReport.setCreatedBy(userFullName.findByUserName(earthStudReport.getUserName()));
						earthStudReport.setUpdatedBy(userFullName.findByUserName(earthStudReport.getUserName()));
						earthStudRepository.save(earthStudReport);
						logger.debug("Earth Stud Report Details Successfully Saved in DB");
						
						basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudReport.getBasicLpsId());
						if (basicLpsRepo.isPresent()
								&& basicLpsRepo.get().getBasicLpsId().equals(earthStudReport.getBasicLpsId())) {
							basicLps = basicLpsRepo.get();
							basicLps.setAllStepsCompleted("AllStepCompleted");
							basicLpsRepository.save(basicLps);
							logger.debug("Basic Lps Report Details Successfully Updated as All Steps Completed in DB");
							
						} else {
							logger.error("Basic LPS Id Information not Available in Basic LPS Id Details");
							throw new EarthStudException("Basic LPS Id Information not Available in Basic LPS Id Details");
						}
					} else {
						logger.error("Please fill all the fields before clicking next button");
						throw new EarthingLpsException("Please fill all the fields before clicking next button");
					}
				} else {
					logger.error("Given Basic LPS Id is Not Registered in Basic LPS");
					throw new EarthStudException("Given Basic LPS Id is Not Registered in Basic LPS");
				}

			} else {
				logger.error("Basic LPS Id Already Available.Create New Basic Id");
				throw new EarthStudException("Basic LPS Id Already Available.Create New Basic Id");
			}

//			printBasicLpsService.printBasicLps(earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId(),
//					basicLpsDetails);
//
//			printAirTerminationService.printAirTermination(earthStudDescription.getUserName(),
//					earthStudDescription.getBasicLpsId(), basicLpsDetails, lpsAirDisc);
//
//			printDownConductorService.printDownConductor(earthStudDescription.getUserName(),
//					earthStudDescription.getBasicLpsId(), basicLpsDetails, downConductorDetails);
//
//			printEarthingLpsService.printEarthingLpsDetails(earthStudDescription.getUserName(),
//					earthStudDescription.getBasicLpsId(), basicLpsDetails, earthingLpsDetails);
//
//			printSPDService.printSPD(earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId(),
//					basicLpsDetails, spdDetails);
//
//			printSDandEarthStudService.printSDandEarthStud(earthStudDescription.getUserName(),
//					earthStudDescription.getBasicLpsId(), basicLpsDetails, separateDistanceDetails);
//
//			printFinalPDFService.printFinalPDF(earthStudDescription.getUserName(),
//					earthStudDescription.getBasicLpsId(), basicLpsDetails.get().getProjectName());

		} else {
			logger.error("Invalid Inputs");
			throw new EarthStudException("Invalid Inputs");
		}

	}

	@Override
	public List<EarthStudReport> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException {
		if (userName != null) {
			List<EarthStudReport> earthStudRepo = earthStudRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthStudRepo != null && !earthStudRepo.isEmpty()) {
				for(EarthStudReport earthStudReportItr : earthStudRepo) {
					earthStudReportItr.setEarthStudDescription(findNonRemovedObjects.findNonRemovedEarthStudBuildings(earthStudReportItr));
					logger.debug("Successfully done findNonRemovedEarthStudBuildings() call");
				}
				return earthStudRepo;
			} else {
				logger.error("Given UserName & Id doesn't exist in Earth Stud Report Details");
				throw new EarthStudException("Given UserName & Id doesn't exist in Earth Stud Report Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new EarthStudException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public void updateEarthStudDetails(EarthStudReport earthStudReport) throws EarthStudException {

		if (earthStudReport != null && earthStudReport.getEarthStudReportId() != null
				&& earthStudReport.getEarthStudReportId() != 0 && earthStudReport.getBasicLpsId() != null
				&& earthStudReport.getBasicLpsId() != 0) {
			Optional<EarthStudReport> earthStudRepo = earthStudRepository
					.findById(earthStudReport.getEarthStudReportId());
			if (earthStudRepo.isPresent()
					&& earthStudRepo.get().getBasicLpsId().equals(earthStudReport.getBasicLpsId())) {
				earthStudReport.setUpdatedDate(LocalDateTime.now());
				earthStudReport.setUpdatedBy(userFullName.findByUserName(earthStudReport.getUserName()));
				earthStudRepository.save(earthStudReport);
				logger.debug("Earth Stud Report Details Successfully Updated in DB");
			} else {
				logger.error("Given Basic LPS Id and Earth Stud Id is Invalid");
				throw new EarthStudException("Given Basic LPS Id and Earth Stud Id is Invalid");
			}

		} else {
			logger.error("Invalid inputs");
			throw new EarthStudException("Invalid inputs");
		}
	}

	private static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream,
			AWSS3ServiceImpl awss3ServiceImpl) throws Exception {
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;
		Iterator<InputStream> pdfIterator = inputPdfList.iterator();
		while (pdfIterator.hasNext()) {
			InputStream pdf = pdfIterator.next();
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		Image image = Image.getInstance(awss3ServiceImpl.findByName("rush-logo.png"));
		image.scaleToFit(185, 185);
		image.setAbsolutePosition(-3, -9);

		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent((PdfPageEvent) event);
		document.open();
		PdfContentByte pageContentByte = writer.getDirectContent();
		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				document.add(image);
				pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}

}
