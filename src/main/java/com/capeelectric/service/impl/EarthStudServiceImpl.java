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
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
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

	@Override
	public void addEarthStudDetails(EarthStudDescription earthStudDescription)
			throws EarthStudException, BasicLpsException, AirTerminationException, DownConductorException,
			EarthingLpsException, SPDException, Exception {
		if (earthStudDescription != null && earthStudDescription.getUserName() != null
				&& !earthStudDescription.getUserName().isEmpty() && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsDetails = basicLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<AirTermination> lpsAirDisc = airTerminationLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());

			Optional<DownConductorDescription> downConductorDetails = downConductorRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<EarthingLpsDescription> earthingLpsDetails = earthingLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<SPD> spdDetails = spdRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<SeperationDistanceDescription> separateDistanceDetails = seperationDistanceRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());

			if (!basicLpsDetails.isPresent() && !lpsAirDisc.isPresent() && !downConductorDetails.isPresent()
					&& !earthingLpsDetails.isPresent() && !separateDistanceDetails.isPresent()
					&& !spdDetails.isPresent()) {
				throw new EarthStudException("Please enter details for all previous steps to proceed further");
			} else if (!basicLpsDetails.isPresent()) {
				throw new EarthStudException("Please enter Basic Information step to proceed further");
			} else if (!lpsAirDisc.isPresent()) {
				throw new EarthStudException("Please enter Air Termination step to proceed further");
			} else if (!downConductorDetails.isPresent()) {
				throw new EarthStudException("Please enter Down Conductors step to proceed further");
			} else if (!earthingLpsDetails.isPresent()) {
				throw new EarthStudException("Please enter Earthing step to proceed further");
			} else if (!spdDetails.isPresent()) {
				throw new EarthStudException("Please enter SPD step to proceed further");

			} else if (!separateDistanceDetails.isPresent()) {
				throw new EarthStudException("Please enter Seperation Distance step to proceed further");
			}
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			if (basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				Optional<EarthStudDescription> earthStudRepo = earthStudRepository
						.findByBasicLpsId(earthStudDescription.getBasicLpsId());
				if (!earthStudRepo.isPresent()
						|| !earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {

					earthStudDescription.setCreatedDate(LocalDateTime.now());
					earthStudDescription.setUpdatedDate(LocalDateTime.now());
					earthStudDescription.setCreatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
					earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
					earthStudRepository.save(earthStudDescription);
					basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
					if (basicLpsRepo.isPresent()
							&& basicLpsRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
						basicLps = basicLpsRepo.get();
						basicLps.setAllStepsCompleted("AllStepCompleted");
						basicLpsRepository.save(basicLps);
					} else {
						throw new EarthStudException("Basic LPS Id Information not Available in Basic LPS Id Details");
					}
				} else {
					throw new EarthStudException("Given Basic LPS Id is Not Registered in Basic LPS");
				}

			} else {
				throw new EarthStudException("Basic LPS Id Already Available.Create New Basic Id");
			}

			printBasicLpsService.printBasicLps(earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId(),
					basicLpsDetails);

			printAirTerminationService.printAirTermination(earthStudDescription.getUserName(),
					earthStudDescription.getBasicLpsId(), basicLpsDetails, lpsAirDisc);

			printDownConductorService.printDownConductor(earthStudDescription.getUserName(),
					earthStudDescription.getBasicLpsId(), basicLpsDetails, downConductorDetails);

			printEarthingLpsService.printEarthingLpsDetails(earthStudDescription.getUserName(),
					earthStudDescription.getBasicLpsId(), basicLpsDetails, earthingLpsDetails);

			printSPDService.printSPD(earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId(),
					basicLpsDetails, spdDetails);

			printSDandEarthStudService.printSDandEarthStud(earthStudDescription.getUserName(),
					earthStudDescription.getBasicLpsId(), basicLpsDetails, separateDistanceDetails);

			printFinalPDFService.printFinalPDF(earthStudDescription.getUserName(),
					earthStudDescription.getBasicLpsId());

		} else {
			throw new EarthStudException("Invalid Inputs");
		}

	}

	@Override
	public List<EarthStudDescription> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException {
		if (userName != null) {
			List<EarthStudDescription> earthStudRepo = earthStudRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthStudRepo != null && !earthStudRepo.isEmpty()) {
				return earthStudRepo;
			} else {
				throw new EarthStudException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new EarthStudException("Invalid Inputs");
		}
	}

	@Override
	public void updateEarthStudDetails(EarthStudDescription earthStudDescription) throws EarthStudException {

		if (earthStudDescription != null && earthStudDescription.getEarthStudDescId() != null
				&& earthStudDescription.getEarthStudDescId() != 0 && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<EarthStudDescription> earthStudRepo = earthStudRepository
					.findById(earthStudDescription.getEarthStudDescId());
			if (earthStudRepo.isPresent()
					&& earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				earthStudDescription.setUpdatedDate(LocalDateTime.now());
				earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
				earthStudRepository.save(earthStudDescription);
			} else {
				throw new EarthStudException("Given Basic LPS Id and Earth Stud Id is Invalid");
			}

		} else {
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
