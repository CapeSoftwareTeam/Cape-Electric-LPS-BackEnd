	
package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthElectrodeChamber;
import com.capeelectric.model.EarthElectrodeTesting;
import com.capeelectric.model.EarthingClamps;
import com.capeelectric.model.EarthingDescription;
import com.capeelectric.model.EarthingDescriptionList;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.EarthingSystem;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.PrintEarthingLpsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintEarthingLpsServiceImpl implements PrintEarthingLpsService {

	@Autowired
	private EarthingLpsRepository earthingLpsRepository;

//	@Override
//	public List<EarthingLpsDescription> printEarthingLpsDetails(String userName, Integer basicLpsId,Optional<BasicLps> basicLpsDetails, Optional<EarthingLpsDescription> earthingLpsDetails)
//			throws EarthingLpsException {

	@Override
	public List<EarthingLpsDescription> printEarthingLpsDetails1(String userName, Integer basicLpsId)
			throws EarthingLpsException {

//		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
		Document document = new Document(PageSize.A4, 68, 68, 62, 68);

		try {

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("EarthingLps.pdf"));

			List<EarthingReport> earthingReport1 = earthingLpsRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			EarthingReport earthingReport2 = earthingReport1.get(0);

			List<EarthingLpsDescription> earthingLpsRepo1 = earthingReport2.getEarthingLpsDescription();
			EarthingLpsDescription earthingLpsRepo = earthingLpsRepo1.get(0);

			List<EarthingClamps> earthClamps1 = earthingLpsRepo.getEarthingClamps();
			// EarthingClamps earthClamps=earthClamps1.get(0);

			List<EarthingDescription> earthDesc1 = earthingLpsRepo.getEarthingDescription();
			// EarthingDescription earthDesc=earthDesc1.get(0);

			List<EarthElectrodeChamber> earthElectrodeChamber1 = earthingLpsRepo.getEarthingElectrodeChamber();
			// EarthElectrodeChamber earthElectrodeChamber=earthElectrodeChamber1.get(0);

			List<EarthingSystem> earthingSystem1 = earthingLpsRepo.getEarthingSystem();
			// EarthingSystem earthingSystem=earthingSystem1.get(0);

			List<EarthElectrodeTesting> earthingElectrodeTesting1 = earthingLpsRepo.getEarthElectrodeTesting();
			// EarthElectrodeTesting earthingElectrodeTesting =
			// earthingElectrodeTesting1.get(0);

			document.open();

			Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
			
			Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

			Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

			Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD);
			Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

			
			float[] pointColumnWidths5 = { 100F };

			PdfPTable headertable = new PdfPTable(pointColumnWidths5);
			headertable.setWidthPercentage(100); // Width 100%
			headertable.setSpacingBefore(10f); // Space before table

			PdfPCell label = new PdfPCell(
					new Paragraph("Check list for Earthing of LPS \r\n" + "as per IS/IEC 62305", font12B));
			label.setHorizontalAlignment(Element.ALIGN_CENTER);
			label.setGrayFill(0.92f);
//			label.setFixedHeight(20f);
			headertable.addCell(label);
			document.add(headertable);
			PdfPTable BasicDetailsTable = new PdfPTable(pointColumnWidths5);

			BasicDetailsTable.setWidthPercentage(100); // Width 100%
			BasicDetailsTable.setSpacingBefore(10f); // Space before table
			BasicDetailsTable.setSpacingAfter(5f); // Space after table
			BasicDetailsTable.getDefaultCell().setBorder(0);

			PdfPCell arrangements = new PdfPCell(
					new Paragraph("Basic Details", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements.setBackgroundColor(new GrayColor(0.82f));
			arrangements.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements.setBorder(PdfPCell.NO_BORDER);
			BasicDetailsTable.addCell(arrangements);
			document.add(BasicDetailsTable);

//				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
			Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

			float[] pointColumnWidths30 = { 30F, 145F, 55F, 50F };

			PdfPTable table2 = new PdfPTable(pointColumnWidths30);
			table2.setWidthPercentage(100); // Width 100%
			table2.setSpacingBefore(5f); // Space before table
			table2.setWidthPercentage(100);

			PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font11));
			cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell30.setGrayFill(0.92f);
			table2.addCell(cell30);

			PdfPCell cell311 = new PdfPCell(new Paragraph("Description", font11));
			cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell311.setFixedHeight(20f);
			cell311.setGrayFill(0.92f);
			table2.addCell(cell311);

			PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font11));
			cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell32.setFixedHeight(20f);
			cell32.setGrayFill(0.92f);
			table2.addCell(cell32);

			PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font11));
			cell33.setGrayFill(0.92f);
			cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell33);

			float[] pointColumnWidths = { 120F, 80F };
			PdfPTable table100 = new PdfPTable(pointColumnWidths);

			table100.setWidthPercentage(100); // Width 100%
			table100.setSpacingBefore(5f); // Space before table
			table100.setSpacingAfter(5f); // Space after table
			table100.getDefaultCell().setBorder(0);

			PdfPCell cell1 = new PdfPCell(
					new Paragraph("Building Number:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			cell1.setBackgroundColor(new BaseColor(203, 183, 162));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorder(PdfPCell.NO_BORDER);
			table100.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Paragraph(earthingLpsRepo.getBuildingNumber().toString(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setBackgroundColor(new BaseColor(203, 183, 162));
			cell2.setBorder(PdfPCell.NO_BORDER);
			table100.addCell(cell2);
			document.add(table100);

			PdfPTable table1001 = new PdfPTable(pointColumnWidths);

			table1001.setWidthPercentage(100); // Width 100%
			table1001.setSpacingBefore(5f); // Space before table
			table1001.setSpacingAfter(5f); // Space after table
			table1001.setWidthPercentage(100);
			table1001.getDefaultCell().setBorder(0);
			PdfPCell cell3 = new PdfPCell(
					new Paragraph("Building Name:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			cell3.setBackgroundColor(new BaseColor(203, 183, 162));
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setBorder(PdfPCell.NO_BORDER);
			table1001.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Paragraph(earthingLpsRepo.getBuildingName(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setBackgroundColor(new BaseColor(203, 183, 162));
			cell4.setBorder(PdfPCell.NO_BORDER);
			table1001.addCell(cell4);
			document.add(table1001);

			PdfPTable table205 = new PdfPTable(pointColumnWidths30);
			table205.setWidthPercentage(100); // Width 100%
			// table205.setSpacingBefore(20f); // Space before table
			table205.setWidthPercentage(100);

			PdfPCell cell205 = new PdfPCell(new Paragraph("1", font1));
			cell205.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell205.setGrayFill(0.92f);
			table205.addCell(cell205);

			PdfPCell cell206 = new PdfPCell(new Paragraph("Type of Earthing (Type-A/Type-B/both)", font1));
			cell206.setGrayFill(0.92f);
			table205.addCell(cell206);

			PdfPCell cell209 = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingTypeInOb(), font3));
			cell209.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell209);

			PdfPCell cell210 = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingTypeInRem(), font3));
			cell210.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell210);

			PdfPCell cell20 = new PdfPCell(new Paragraph("2", font1));
			cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell20.setGrayFill(0.92f);
			table205.addCell(cell20);

			PdfPCell cell21 = new PdfPCell(new Paragraph(
					"Check for bimetallic issue (connections between dissimilar metals are not allowed)", font1));
			cell21.setFixedHeight(20f);
			cell21.setGrayFill(0.92f);
			table205.addCell(cell21);

			PdfPCell cell29 = new PdfPCell(new Paragraph(earthingLpsRepo.getBimetallicIssueInOb(), font3));
			cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell29);

			PdfPCell cell22 = new PdfPCell(new Paragraph(earthingLpsRepo.getBimetallicIssueInRem(), font3));
			cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell22);

			PdfPCell cell24 = new PdfPCell(new Paragraph("3", font1));
			cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell24.setGrayFill(0.92f);
			table205.addCell(cell24);

			PdfPCell cell23 = new PdfPCell(
					new Paragraph("Connections made by brazing/welding/crimping /seaming/screwing/bolting", font1));
			cell23.setFixedHeight(20f);
			cell23.setGrayFill(0.92f);
			table205.addCell(cell23);

			PdfPCell cell35 = new PdfPCell(new Paragraph(earthingLpsRepo.getBrazingConnectInOb(), font3));
			cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell35);

			PdfPCell cell36 = new PdfPCell(new Paragraph(earthingLpsRepo.getBrazingConnectInRem(), font3));
			cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell36);

			document.add(table2);
			document.add(table205);

			float[] pointColumnWidths10 = { 100F };

			PdfPTable BasicDetailsTable6 = new PdfPTable(pointColumnWidths10);

			BasicDetailsTable6.setWidthPercentage(100); // Width 100%
			BasicDetailsTable6.setSpacingBefore(10f); // Space before table
			BasicDetailsTable6.setSpacingAfter(5f); // Space after table
			BasicDetailsTable6.getDefaultCell().setBorder(0);

			PdfPCell arrangements7 = new PdfPCell(new Paragraph("Type-A earthing system",
					new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements7.setBackgroundColor(new GrayColor(0.82f));
			arrangements7.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements7.setBorder(PdfPCell.NO_BORDER);
			BasicDetailsTable6.addCell(arrangements7);

			float[] pointColumnWidths4 = { 100F };

			PdfPTable BasicDetailsTable3 = new PdfPTable(pointColumnWidths4);

			BasicDetailsTable3.setWidthPercentage(100); // Width 100%
			BasicDetailsTable3.setSpacingBefore(10f); // Space before table
			BasicDetailsTable3.setSpacingAfter(5f); // Space after table
			BasicDetailsTable3.getDefaultCell().setBorder(0);

			PdfPCell arrangements1 = new PdfPCell(
					new Paragraph("Clamps", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements1.setBackgroundColor(new GrayColor(0.82f));
			arrangements1.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements1.setBorder(PdfPCell.NO_BORDER);
			BasicDetailsTable3.addCell(arrangements1);
			
			float[] pointColumnWidths2 = { 120F, 80F };
			PdfPTable table1 = new PdfPTable(pointColumnWidths2);

			table1.setWidthPercentage(100); // Width 100%
//			table2.setSpacingBefore(5f); // Space before table
//			table2.setSpacingAfter(5f); // Space after table
			table1.getDefaultCell().setBorder(0);

			PdfPCell cell11 = new PdfPCell(
					new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell11.setBackgroundColor(new GrayColor(0.93f));
			cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell11.setBorder(PdfPCell.NO_BORDER);
			table1.addCell(cell11);
			PdfPCell cell11A = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingClampsAvailabilityOb(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell11A.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell11A.setBackgroundColor(new GrayColor(0.93f));
			cell11A.setBorder(PdfPCell.NO_BORDER);
			table1.addCell(cell11A);

			PdfPTable table1R = new PdfPTable(pointColumnWidths2);

			table1R.setWidthPercentage(100); // Width 100%
			table1R.setSpacingBefore(5f); // Space before table
//			table1R.setSpacingAfter(5f); // Space after table
			table1R.getDefaultCell().setBorder(0);
			
			
			PdfPCell cell22R = new PdfPCell(
					new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell22R.setBackgroundColor(new GrayColor(0.93f));
			cell22R.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell22R.setBorder(PdfPCell.NO_BORDER);
			table1R.addCell(cell22R);
			PdfPCell cell23R = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingClampsAvailabilityRem(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell23R.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell23R.setBackgroundColor(new GrayColor(0.93f));
			cell23R.setBorder(PdfPCell.NO_BORDER);
			table1R.addCell(cell23R);

			
			float[] pointColumnWidths6 = { 100F };

			PdfPTable BasicDetailsTable4 = new PdfPTable(pointColumnWidths6);

			BasicDetailsTable4.setWidthPercentage(100); // Width 100%
			BasicDetailsTable4.setSpacingBefore(10f); // Space before table
			BasicDetailsTable4.setSpacingAfter(5f); // Space after table
			BasicDetailsTable4.getDefaultCell().setBorder(0);

			PdfPCell arrangements2 = new PdfPCell(new Paragraph("Earth electrode chambers",
					new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements2.setBackgroundColor(new GrayColor(0.82f));
			arrangements2.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements2.setBorder(PdfPCell.NO_BORDER);
			BasicDetailsTable4.addCell(arrangements2);

			PdfPTable table18 = new PdfPTable(pointColumnWidths2);

			table18.setWidthPercentage(100); // Width 100%
//			table18.setSpacingBefore(5f); // Space before table
//			table18.setSpacingAfter(5f); // Space after table
			table18.getDefaultCell().setBorder(0);

			PdfPCell cell1A = new PdfPCell(
					new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell1A.setBackgroundColor(new GrayColor(0.93f));
			cell1A.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1A.setBorder(PdfPCell.NO_BORDER);
			table18.addCell(cell1A);
			PdfPCell cell12A = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingClampsAvailabilityOb(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell12A.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell12A.setBackgroundColor(new GrayColor(0.93f));
			cell12A.setBorder(PdfPCell.NO_BORDER);
			table18.addCell(cell12A);

			PdfPTable table18R = new PdfPTable(pointColumnWidths2);

			table18R.setWidthPercentage(100); // Width 100%
			table18R.setSpacingBefore(5f); // Space before table
//			table18R.setSpacingAfter(5f); // Space after table
			table18R.getDefaultCell().setBorder(0);
			
			
			PdfPCell cell2R = new PdfPCell(
					new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell2R.setBackgroundColor(new GrayColor(0.93f));
			cell2R.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2R.setBorder(PdfPCell.NO_BORDER);
			table18R.addCell(cell2R);
			PdfPCell cell222R = new PdfPCell(new Paragraph(earthingLpsRepo.getEarthingClampsAvailabilityRem(),
					new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell222R.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell222R.setBackgroundColor(new GrayColor(0.93f));
			cell222R.setBorder(PdfPCell.NO_BORDER);
			table18R.addCell(cell222R);

			float[] pointColumnWidths8 = { 100F };

			PdfPTable table5 = new PdfPTable(pointColumnWidths8);

			table5.setWidthPercentage(100); // Width 100%
			table5.setSpacingBefore(10f); // Space before table
			table5.setSpacingAfter(5f); // Space after table
			table5.getDefaultCell().setBorder(0);

			PdfPCell arrangements4 = new PdfPCell(new Paragraph("Type-B earthing system",
					new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements4.setBackgroundColor(new GrayColor(0.82f));
			arrangements4.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements4.setBorder(PdfPCell.NO_BORDER);
			table5.addCell(arrangements4);

			PdfPTable header = new PdfPTable(pointColumnWidths10);

			header.setWidthPercentage(100); // Width 100%
			header.setSpacingBefore(10f); // Space before table
			header.setSpacingAfter(5f); // Space after table
			header.getDefaultCell().setBorder(0);

			PdfPCell arrangements9 = new PdfPCell(new Paragraph("Testing of earth electrodes",
					new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements9.setBackgroundColor(new GrayColor(0.82f));
			arrangements9.setHorizontalAlignment(Element.ALIGN_CENTER);
			arrangements9.setBorder(PdfPCell.NO_BORDER);
			header.addCell(arrangements9);

			PdfPTable header1 = new PdfPTable(pointColumnWidths10);

			header1.setWidthPercentage(100); // Width 100%
			header1.setSpacingBefore(5f); // Space before table
			header1.setSpacingAfter(5f); // Space after table
			header1.getDefaultCell().setBorder(0);

			PdfPCell arrangements90 = new PdfPCell(
					new Paragraph("8(a).Earth electrode resistance value (Table heading)",
							new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
			arrangements90.setBackgroundColor(new GrayColor(0.82f));
			arrangements90.setHorizontalAlignment(Element.ALIGN_LEFT);
			arrangements90.setBorder(PdfPCell.NO_BORDER);
			header1.addCell(arrangements90);

			float[] pointColumnWidths9 = { 25, 25F, 55F, 50F, 25F, 55F, 50F };

			PdfPTable table7 = new PdfPTable(pointColumnWidths9);
			table7.setWidthPercentage(100); // Width 100%
			table7.setSpacingBefore(5f); // Space before table
			table7.setWidthPercentage(100);

			PdfPCell cell301 = new PdfPCell(new Paragraph("SL.NO", font11));
			cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell301.setGrayFill(0.92f);
			table7.addCell(cell301);

			PdfPCell cell3111 = new PdfPCell(
					new Paragraph("Types of earth electrode(rod/pipe/flat/chemical pipe)", font11));
			cell3111.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3111.setFixedHeight(20f);
			cell3111.setGrayFill(0.92f);
			table7.addCell(cell3111);

			PdfPCell cell321 = new PdfPCell(new Paragraph(
					"Material of earth elctrode (copper/copper bonded steel/galvanised steel/galvanised iron/cast iron)",
					font11));
			cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell321.setFixedHeight(20f);
			cell321.setGrayFill(0.92f);
			table7.addCell(cell321);

			PdfPCell cell313 = new PdfPCell(new Paragraph("Size(diameter)of earth electrode (mm)", font11));
			cell313.setGrayFill(0.92f);
			cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell313);

			PdfPCell cell332 = new PdfPCell(new Paragraph("Depth /length of earth electrode (m)", font11));
			cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell332.setFixedHeight(20f);
			cell332.setGrayFill(0.92f);
			table7.addCell(cell332);

			PdfPCell cell3211 = new PdfPCell(new Paragraph("Electrode resistance to earth in (Ω)", font11));
			cell3211.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3211.setFixedHeight(20f);
			cell3211.setGrayFill(0.92f);
			table7.addCell(cell3211);

			PdfPCell cell3131 = new PdfPCell(new Paragraph("Remarks", font11));
			cell3131.setGrayFill(0.92f);
			cell3131.setHorizontalAlignment(Element.ALIGN_CENTER);
			table7.addCell(cell3131);

			// select Type A
			if (earthingLpsRepo.getEarthingTypeInOb().equals("Type A")) {

				// eartingDescTypeA Iteration
				document.add(BasicDetailsTable6);
				document.add(table2);
				for (EarthingDescription earthDesc : earthDesc1) {

					eartingDescTypeAiter(document, font, earthDesc);
				}

				// earthingClamps
//				document.newPage();
				document.add(BasicDetailsTable3);
				document.add(table1);
				document.add(table1R);
				document.add(table2);
				for (EarthingClamps earthClamps : earthClamps1) {

					if (earthingLpsRepo.getEarthingClampsAvailabilityOb().equals("Applicable")) {

						earthingClamps(document, earthClamps, font);
					}
				}

				// earthElectrodeChamber
				document.add(BasicDetailsTable4);
				document.add(table18);
				document.add(table18R);
				document.add(table2);
				for (EarthElectrodeChamber earthElectrodeChamber : earthElectrodeChamber1) {

					if (earthingLpsRepo.getEarthingElectrodeChamberAvailabilityOb().equals("Applicable")) {

						earthElectrodeChamber(document, font, earthElectrodeChamber);
					}
				}

				// earthElectrodeTesting Iteration
				document.add(header);
				document.add(header1);
				document.add(table7);
				for (EarthElectrodeTesting earthingElectrodeTesting : earthingElectrodeTesting1) {
					earthElectrodeTestingIter(document, font, earthingElectrodeTesting);
				}
			}

			// select Type-B (ring)
			if (earthingLpsRepo.getEarthingTypeInOb().equals("Type-B (ring)")) {
				// earthingSystemType-B Iteration
				document.newPage();
				document.add(table5);
				document.add(table2);
				for (EarthingSystem earthingSystem : earthingSystem1) {

					earthingSystemTypeBiter(document, font, earthingSystem);
				}

				// earthElectrodeTesting Iteration
				document.add(header);
				document.add(header1);
				document.add(table7);
				for (EarthElectrodeTesting earthingElectrodeTesting : earthingElectrodeTesting1) {
					earthElectrodeTestingIter(document, font, earthingElectrodeTesting);
				}
			}

			// select Type A & Type B combined
			if (earthingLpsRepo.getEarthingTypeInOb().equals("Type A & Type B combined")) {
				// eartingDescTypeA Iteration
				document.add(BasicDetailsTable6);
				document.add(table2);
				for (EarthingDescription earthDesc : earthDesc1) {

					eartingDescTypeAiter(document, font, earthDesc);
				}

				// earthingClamps
				document.newPage();
				document.add(BasicDetailsTable3);
				document.add(table1);
				document.add(table1R);
				document.add(table2);
				for (EarthingClamps earthClamps : earthClamps1) {

					if (earthingLpsRepo.getEarthingClampsAvailabilityOb().equals("Applicable")) {

						earthingClamps(document, earthClamps, font);
					}
				}

				// earthElectrodeChamber
				document.add(BasicDetailsTable4);
				document.add(table18);
				document.add(table18R);
				document.add(table2);
				for (EarthElectrodeChamber earthElectrodeChamber : earthElectrodeChamber1) {

					if (earthingLpsRepo.getEarthingElectrodeChamberAvailabilityOb().equals("Applicable")) {

						earthElectrodeChamber(document, font, earthElectrodeChamber);
					}
				}

				// earthingSystemType-B Iteration
				document.newPage();
				document.add(table5);
				document.add(table2);
				for (EarthingSystem earthingSystem : earthingSystem1) {

					earthingSystemTypeBiter(document, font, earthingSystem);
				}

				// earthElectrodeTesting Iteration
				document.add(header);
				document.add(header1);
				document.add(table7);
				for (EarthElectrodeTesting earthingElectrodeTesting : earthingElectrodeTesting1) {
					earthElectrodeTestingIter(document, font, earthingElectrodeTesting);
				}
			}

			// select Foundation
			if (earthingLpsRepo.getEarthingTypeInOb().equals("Foundation")) {
				// earthElectrodeTesting Iteration
				document.add(header);
				document.add(header1);
				document.add(table7);
				for (EarthElectrodeTesting earthingElectrodeTesting : earthingElectrodeTesting1) {
					earthElectrodeTestingIter(document, font, earthingElectrodeTesting);
				}
			}

			document.close();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
//
//		} else {
//			throw new EarthingLpsException("Invalid Inputs");
//		}
		return null;

	}

	private void earthElectrodeTestingIter(Document document, Font font, EarthElectrodeTesting earthingElectrodeTesting)
			throws DocumentException {

		float[] pointColumnWidths9 = { 25, 25F, 55F, 50F, 25F, 55F, 50F };

		PdfPTable table77 = new PdfPTable(pointColumnWidths9);
		table77.setWidthPercentage(100); // Width 100%
		// table77.setSpacingBefore(10f); // Space before table
		table77.setWidthPercentage(100);

		PdfPCell cell305 = new PdfPCell(new Paragraph(earthingElectrodeTesting.getSerialNo().toString(), font));
		cell305.setHorizontalAlignment(Element.ALIGN_LEFT);
		table77.addCell(cell305);

		PdfPCell cell31 = new PdfPCell(new Paragraph(earthingElectrodeTesting.getEarthingElectrodeType(), font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setFixedHeight(20f);
		table77.addCell(cell31);

		PdfPCell cell3213 = new PdfPCell(new Paragraph(earthingElectrodeTesting.getEarthingElectrodeMaterial(), font));
		cell3213.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3213.setFixedHeight(20f);
		table77.addCell(cell3213);

		PdfPCell cell90 = new PdfPCell(
				new Paragraph(earthingElectrodeTesting.getEarthingElectrodeSize().toString(), font));
		cell90.setHorizontalAlignment(Element.ALIGN_LEFT);
		table77.addCell(cell90);

		PdfPCell cell3321 = new PdfPCell(
				new Paragraph(earthingElectrodeTesting.getEarthingElectrodeDepth().toString(), font));
		cell3321.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3321.setFixedHeight(20f);

		table77.addCell(cell3321);

		PdfPCell cell323 = new PdfPCell(
				new Paragraph(earthingElectrodeTesting.getEarthingElectrodeResistance().toString(), font));
		cell323.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell323.setFixedHeight(20f);
		table77.addCell(cell323);

		PdfPCell cell91 = new PdfPCell(new Paragraph(earthingElectrodeTesting.getEarthingElectrodeRemarks(), font));
		cell91.setHorizontalAlignment(Element.ALIGN_LEFT);
		table77.addCell(cell91);

		document.add(table77);
	}

	private void earthingClamps(Document document, EarthingClamps earthClamps, Font font) throws DocumentException {
		float[] pointColumnWidths2 = { 30F, 145F, 55F, 50F };

		PdfPTable table22 = new PdfPTable(pointColumnWidths2);
		table22.setWidthPercentage(100); // Width 100%
		// table22.setSpacingBefore(20f); // Space before table
		table22.setWidthPercentage(100);

		PdfPCell cell128 = new PdfPCell(new Paragraph("5.1", font));
		cell128.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell128.setGrayFill(0.92f);
		table22.addCell(cell128);

		PdfPCell cell129 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell129.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell129.setGrayFill(0.92f);
		table22.addCell(cell129);

		PdfPCell cell130 = new PdfPCell(new Paragraph(earthClamps.getPhysicalInspectionInOb(), font));
		cell130.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell130);

		PdfPCell cell131 = new PdfPCell(new Paragraph(earthClamps.getPsysicalInspectionInRem(), font));
		cell131.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell131);

		PdfPCell cell132 = new PdfPCell(new Paragraph("5.2", font));
		cell132.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell132.setGrayFill(0.92f);
		table22.addCell(cell132);

		PdfPCell cell133 = new PdfPCell(new Paragraph("Clamp is firmly fixed/mounted on to earth electrode", font));
		cell133.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell133.setGrayFill(0.92f);
		table22.addCell(cell133);

		PdfPCell cell134 = new PdfPCell(new Paragraph(earthClamps.getClampsFirmlyOb(), font));
		cell134.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell134);

		PdfPCell cell136 = new PdfPCell(new Paragraph(earthClamps.getClampsFirmlyRem(), font));
		cell136.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell136);

		PdfPCell cell137 = new PdfPCell(new Paragraph("5.3", font));
		cell137.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell137.setGrayFill(0.92f);
		table22.addCell(cell137);

		PdfPCell cell138 = new PdfPCell(
				new Paragraph("Interconnection of earthing conductor with clamp (tight/loose/corroded)", font));
		cell138.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell138.setGrayFill(0.92f);
		table22.addCell(cell138);

		PdfPCell cell139 = new PdfPCell(new Paragraph(earthClamps.getInterConnectOfEarthClampInOb(), font));
		cell139.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell139);

		PdfPCell cell140 = new PdfPCell(new Paragraph(earthClamps.getInterConnectOfEarthClampInRem(), font));
		cell140.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell140);

		PdfPCell cell141 = new PdfPCell(new Paragraph("5.4", font));
		cell141.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell141.setGrayFill(0.92f);
		table22.addCell(cell141);

		PdfPCell cell142 = new PdfPCell(new Paragraph("Type of clamp", font));
		cell142.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell142.setGrayFill(0.92f);
		table22.addCell(cell142);

		PdfPCell cell143 = new PdfPCell(new Paragraph(earthClamps.getTypeOfClampsInOb(), font));
		cell143.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell143);

		PdfPCell cell144 = new PdfPCell(new Paragraph(earthClamps.getTypeOfClampsInRem(), font));
		cell144.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell144);

		PdfPCell cell145 = new PdfPCell(new Paragraph("5.5", font));
		cell145.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell145.setGrayFill(0.92f);
		table22.addCell(cell145);

		PdfPCell cell146 = new PdfPCell(new Paragraph("Material of clamp", font));
		cell146.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell146.setGrayFill(0.92f);
		table22.addCell(cell146);

		PdfPCell cell147 = new PdfPCell(new Paragraph(earthClamps.getMaterialOfClampsInOb(), font));
		cell147.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell147);

		PdfPCell cell148 = new PdfPCell(new Paragraph(earthClamps.getMaterialOfClampsInRem(), font));
		cell148.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell148);

		PdfPCell cell149 = new PdfPCell(new Paragraph("5.6", font));
		cell149.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell149.setGrayFill(0.92f);
		table22.addCell(cell149);

		PdfPCell cell150 = new PdfPCell(new Paragraph("Total number of clamps", font));
		cell150.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell150.setGrayFill(0.92f);
		table22.addCell(cell150);

		PdfPCell cell151 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInOb().toString(), font));
		cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell151);

		PdfPCell cell152 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInRem().toString(), font));
		cell152.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell152);

		PdfPCell cell153 = new PdfPCell(new Paragraph("5.7", font));
		cell153.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell153.setGrayFill(0.92f);
		table22.addCell(cell153);

		PdfPCell cell154 = new PdfPCell(new Paragraph("Number of clamps inspected", font));
		cell154.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell154.setGrayFill(0.92f);
		table22.addCell(cell154);

		PdfPCell cell155 = new PdfPCell(new Paragraph(earthClamps.getInspectedClampsInOb().toString(), font));
		cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell155);

		PdfPCell cell156 = new PdfPCell(new Paragraph(earthClamps.getInspectedClampsInRem().toString(), font));
		cell156.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell156);

		PdfPCell cell157 = new PdfPCell(new Paragraph("5.8", font));
		cell157.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell157.setGrayFill(0.92f);
		table22.addCell(cell157);

		PdfPCell cell158 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell158.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell158.setGrayFill(0.92f);
		table22.addCell(cell158);

		PdfPCell cell159 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInOb().toString(), font));
		cell159.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell159);

		PdfPCell cell160 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInRem().toString(), font));
		cell160.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell160);

		PdfPCell cell161 = new PdfPCell(new Paragraph("5.9", font));
		cell161.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell161.setGrayFill(0.92f);
		table22.addCell(cell161);

		PdfPCell cell162 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell162.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell162.setGrayFill(0.92f);
		table22.addCell(cell162);

		PdfPCell cell163 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInOb().toString(), font));
		cell163.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell163);

		PdfPCell cell164 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInRem().toString(), font));
		cell164.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell164);

		document.add(table22);
	}

	private void earthElectrodeChamber(Document document, Font font, EarthElectrodeChamber earthElectrodeChamber)
			throws DocumentException {
		float[] pointColumnWidths7 = { 30F, 145F, 55F, 50F };

		PdfPTable table202 = new PdfPTable(pointColumnWidths7);
		table202.setWidthPercentage(100); // Width 100%
		// table202.setSpacingBefore(20f); // Space before table
		table202.setWidthPercentage(100);

		PdfPCell cell169 = new PdfPCell(new Paragraph("6.a", font));
		cell169.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell169.setGrayFill(0.92f);
		table202.addCell(cell169);

		PdfPCell cell170 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell170.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell170.setGrayFill(0.92f);
		table202.addCell(cell170);

		PdfPCell cell171 = new PdfPCell(new Paragraph(earthElectrodeChamber.getPhysicalInspeOb(), font));
		cell171.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell171);

		PdfPCell cell172 = new PdfPCell(new Paragraph(earthElectrodeChamber.getPhysicalInspeRem(), font));
		cell172.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell172);

		PdfPCell cell173 = new PdfPCell(new Paragraph("6.d", font));
		cell173.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell173.setGrayFill(0.92f);
		table202.addCell(cell173);

		PdfPCell cell174 = new PdfPCell(new Paragraph("Type of chamber (concrete/metal/plastic)", font));
		cell174.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell174.setGrayFill(0.92f);
		table202.addCell(cell174);

		PdfPCell cell175 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberTypeOb(), font));
		cell175.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell175);

		PdfPCell cell176 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberTypeRem(), font));
		cell176.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell176);

		PdfPCell cell177 = new PdfPCell(new Paragraph("6.c", font));
		cell177.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell177.setGrayFill(0.92f);
		table202.addCell(cell177);

		PdfPCell cell178 = new PdfPCell(new Paragraph("Size of chamber (mm)", font));
		cell178.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell178.setGrayFill(0.92f);
		table202.addCell(cell178);

		PdfPCell cell179 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberSizeOb(), font));
		cell179.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell179);

		PdfPCell cell180 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberSizeRem(), font));
		cell180.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell180);

		PdfPCell cell181 = new PdfPCell(new Paragraph("6.d", font));
		cell181.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell181.setGrayFill(0.92f);
		table202.addCell(cell181);

		PdfPCell cell182 = new PdfPCell(new Paragraph("Maximum withstand load (KN)", font));
		cell182.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell182.setGrayFill(0.92f);
		table202.addCell(cell182);

		PdfPCell cell183 = new PdfPCell(new Paragraph(earthElectrodeChamber.getMaximumWithStandLoadOb(), font));
		cell183.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell183);

		PdfPCell cell184 = new PdfPCell(new Paragraph(earthElectrodeChamber.getMaximumWithStandLoadRem(), font));
		cell184.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell184);

		PdfPCell cell1811 = new PdfPCell(new Paragraph("6.e", font));
		cell1811.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1811.setGrayFill(0.92f);
		table202.addCell(cell1811);

		PdfPCell cell1822 = new PdfPCell(new Paragraph("Location of chamber", font));
		cell1822.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1822.setGrayFill(0.92f);
		table202.addCell(cell1822);

		PdfPCell cell1833 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberLocationOb(), font));
		cell1833.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell1833);

		PdfPCell cell1844 = new PdfPCell(new Paragraph(earthElectrodeChamber.getChamberLocationRem(), font));
		cell1844.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell1844);

		PdfPCell cell185 = new PdfPCell(new Paragraph("6.f", font));
		cell185.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell185.setGrayFill(0.92f);
		table202.addCell(cell185);

		PdfPCell cell186 = new PdfPCell(new Paragraph("Chamber is properly placed in soil", font));
		cell186.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell186.setGrayFill(0.92f);
		table202.addCell(cell186);

		PdfPCell cell187 = new PdfPCell(new Paragraph(earthElectrodeChamber.getMaximumPlacedSoilOb(), font));
		cell187.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell187);

		PdfPCell cell188 = new PdfPCell(new Paragraph(earthElectrodeChamber.getMaximumPlacedSoilRem(), font));
		cell188.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell188);

		PdfPCell cell189 = new PdfPCell(new Paragraph("6.g", font));
		cell189.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell189.setGrayFill(0.92f);
		table202.addCell(cell189);

		PdfPCell cell190 = new PdfPCell(new Paragraph("Total number of chambers", font));
		cell190.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell190.setGrayFill(0.92f);
		table202.addCell(cell190);

		PdfPCell cell1911 = new PdfPCell(new Paragraph(earthElectrodeChamber.getTotalChamberNoOb().toString(), font));
		cell1911.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell1911);

		PdfPCell cell192 = new PdfPCell(new Paragraph(earthElectrodeChamber.getTotalChamberNoRem().toString(), font));
		cell192.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell192);

		PdfPCell cell193 = new PdfPCell(new Paragraph("6.h", font));
		cell193.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell193.setGrayFill(0.92f);
		table202.addCell(cell193);

		PdfPCell cell194 = new PdfPCell(new Paragraph("Number of chambers inspected", font));
		cell194.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell194.setGrayFill(0.92f);
		table202.addCell(cell194);

		PdfPCell cell195 = new PdfPCell(
				new Paragraph(earthElectrodeChamber.getInspectedChamberInOb().toString(), font));
		cell195.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell195);

		PdfPCell cell196 = new PdfPCell(new Paragraph(earthElectrodeChamber.getInspectedChamberInRem(), font));
		cell196.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell196);

		PdfPCell cell197 = new PdfPCell(new Paragraph("6.i", font));
		cell197.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell197.setGrayFill(0.92f);
		table202.addCell(cell197);

		PdfPCell cell198 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell198.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell198.setGrayFill(0.92f);
		table202.addCell(cell198);

		PdfPCell cell199 = new PdfPCell(
				new Paragraph(earthElectrodeChamber.getInspectionPassedInOb().toString(), font));
		cell199.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell199);

		PdfPCell cell200 = new PdfPCell(new Paragraph(earthElectrodeChamber.getInspectionPassedInRem(), font));
		cell200.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell200);

		PdfPCell cell2011 = new PdfPCell(new Paragraph("6.j", font));
		cell2011.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2011.setGrayFill(0.92f);
		table202.addCell(cell2011);

		PdfPCell cell202 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell202.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell202.setGrayFill(0.92f);
		table202.addCell(cell202);

		PdfPCell cell203 = new PdfPCell(
				new Paragraph(earthElectrodeChamber.getInspectionFailedInOb().toString(), font));
		cell203.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell203);

		PdfPCell cell204 = new PdfPCell(new Paragraph(earthElectrodeChamber.getInspectionFailedInRem(), font));
		cell204.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell204);
		document.add(table202);
	}

	private void earthingSystemTypeBiter(Document document, Font font, EarthingSystem earthingSystem)
			throws DocumentException {
		float[] pointColumnWidths9 = { 30F, 145F, 55F, 50F };

		PdfPTable table211 = new PdfPTable(pointColumnWidths9);
		table211.setWidthPercentage(100); // Width 100%
		// table211.setSpacingBefore(20f); // Space before table
		table211.setWidthPercentage(100);

		PdfPCell cell201 = new PdfPCell(new Paragraph("7.a", font));
		cell201.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell201.setGrayFill(0.92f);
		table211.addCell(cell201);

		PdfPCell cell212 = new PdfPCell(new Paragraph(
				"Earth electrode must be buried atleast 0.5m depth and 1m distance from external wall as closed loop.",
				font));
		cell212.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell212.setColspan(3);
		cell212.setGrayFill(0.92f);
		table211.addCell(cell212);

		PdfPCell cell101 = new PdfPCell(new Paragraph("7.b", font));
		cell101.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell101.setRowspan(5);
		cell101.setGrayFill(0.92f);
		table211.addCell(cell101);

		PdfPCell cell102 = new PdfPCell(new Paragraph("Depth of electrode from ground level in meters", font));
		cell102.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell102.setColspan(3);
		cell102.setGrayFill(0.92f);
		table211.addCell(cell102);

		PdfPCell cell218 = new PdfPCell(new Paragraph("East", font));
		cell218.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell218.setGrayFill(0.92f);
		table211.addCell(cell218);

		PdfPCell cell219 = new PdfPCell(new Paragraph(earthingSystem.getEastOb().toString(), font));
		cell219.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell219);

		PdfPCell cell220 = new PdfPCell(new Paragraph(earthingSystem.getEastRem(), font));
		cell220.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell220);

		PdfPCell cell222 = new PdfPCell(new Paragraph("West", font));
		cell222.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell222.setGrayFill(0.92f);
		table211.addCell(cell222);

		PdfPCell cell223 = new PdfPCell(new Paragraph(earthingSystem.getWestOb().toString(), font));
		cell223.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell223);

		PdfPCell cell224 = new PdfPCell(new Paragraph(earthingSystem.getWestRem(), font));
		cell224.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell224);

		PdfPCell cell226 = new PdfPCell(new Paragraph("North", font));
		cell226.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell226.setGrayFill(0.92f);
		table211.addCell(cell226);

		PdfPCell cell227 = new PdfPCell(new Paragraph(earthingSystem.getNorthOb().toString(), font));
		cell227.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell227);

		PdfPCell cell228 = new PdfPCell(new Paragraph(earthingSystem.getNorthRem(), font));
		cell228.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell228);

		PdfPCell cell230 = new PdfPCell(new Paragraph("South", font));
		cell230.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell230.setGrayFill(0.92f);
		table211.addCell(cell230);

		PdfPCell cell231 = new PdfPCell(new Paragraph(earthingSystem.getSouthOb().toString(), font));
		cell231.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell231);

		PdfPCell cell232 = new PdfPCell(new Paragraph(earthingSystem.getSouthRem(), font));
		cell232.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell232);

		PdfPCell cell213 = new PdfPCell(new Paragraph("7.c", font));
		cell213.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell213.setRowspan(5);
		cell213.setGrayFill(0.92f);
		table211.addCell(cell213);

		PdfPCell cell214 = new PdfPCell(new Paragraph("Distance of ring earthing from wall in meters	", font));
		cell214.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell214.setColspan(3);
		cell214.setGrayFill(0.92f);
		table211.addCell(cell214);

		PdfPCell cell2182 = new PdfPCell(new Paragraph("East", font));
		cell2182.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2182.setGrayFill(0.92f);
		table211.addCell(cell2182);

		PdfPCell cell2191 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthEastOb().toString(), font));
		cell2191.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2191);

		PdfPCell cell2201 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthEastRem(), font));
		cell2201.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2201);

		PdfPCell cell2222 = new PdfPCell(new Paragraph("West", font));
		cell2222.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2222.setGrayFill(0.92f);
		table211.addCell(cell2222);

		PdfPCell cell2231 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthWestOb().toString(), font));
		cell2231.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2231);

		PdfPCell cell221 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthWestRem(), font));
		cell221.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell221);

		PdfPCell cell2261 = new PdfPCell(new Paragraph("North", font));
		cell2261.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2261.setGrayFill(0.92f);
		table211.addCell(cell2261);

		PdfPCell cell2271 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthNorthOb().toString(), font));
		cell2271.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2271);

		PdfPCell cell2284 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthNorthRem(), font));
		cell2284.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2284);

		PdfPCell cell2306 = new PdfPCell(new Paragraph("South", font));
		cell2306.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell2306.setGrayFill(0.92f);
		table211.addCell(cell2306);

		PdfPCell cell2314 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthSouthOb().toString(), font));
		cell2314.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2314);

		PdfPCell cell2322 = new PdfPCell(new Paragraph(earthingSystem.getRingWallEarthSouthRem(), font));
		cell2322.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2322);

		PdfPCell cell233 = new PdfPCell(new Paragraph("7.d", font));
		cell233.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell233.setGrayFill(0.92f);
		table211.addCell(cell233);

		PdfPCell cell234 = new PdfPCell(
				new Paragraph("Type B ring earth electrode connected to reinforcement steel", font));
		cell234.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell234.setGrayFill(0.92f);
		table211.addCell(cell234);

		PdfPCell cell235 = new PdfPCell(new Paragraph(earthingSystem.getConnectedEarthElectrodeOb(), font));
		cell235.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell235);

		PdfPCell cell236 = new PdfPCell(new Paragraph(earthingSystem.getConnectedEarthElectrodeRem(), font));
		cell236.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell236);

		PdfPCell cell237 = new PdfPCell(new Paragraph("7(e)", font));
		cell237.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell237.setGrayFill(0.92f);
		table211.addCell(cell237);

		PdfPCell cell238 = new PdfPCell(new Paragraph("Type of Joints", font));
		cell238.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell238.setGrayFill(0.92f);
		table211.addCell(cell238);

		PdfPCell cell239 = new PdfPCell(new Paragraph(earthingSystem.getJointsMadeBrazingOb(), font));
		cell239.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell239);

		PdfPCell cell240 = new PdfPCell(new Paragraph(earthingSystem.getJointsMadeBrazingRem(), font));
		cell240.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell240);

		PdfPCell cell241 = new PdfPCell(new Paragraph("7(f)", font));
		cell241.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell241.setGrayFill(0.92f);
		table211.addCell(cell241);

		PdfPCell cell242 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell242.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell242.setGrayFill(0.92f);
		table211.addCell(cell242);

		PdfPCell cell243 = new PdfPCell(new Paragraph(earthingSystem.getMaterialOfEartElectrodeOb(), font));
		cell243.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell243);

		PdfPCell cell244 = new PdfPCell(new Paragraph(earthingSystem.getMaterialOfEartElectrodeRem(), font));
		cell244.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell244);

		PdfPCell cell245 = new PdfPCell(new Paragraph("7(g)", font));
		cell245.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell245.setGrayFill(0.92f);
		table211.addCell(cell245);

		PdfPCell cell246 = new PdfPCell(new Paragraph("Type of earth electrode", font));
		cell246.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell246.setGrayFill(0.92f);
		table211.addCell(cell246);

		PdfPCell cell247 = new PdfPCell(new Paragraph(earthingSystem.getTypeOfEarthElectrodeOb(), font));
		cell247.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell247);

		PdfPCell cell248 = new PdfPCell(new Paragraph(earthingSystem.getTypeOfEarthElectrodeRem(), font));
		cell248.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell248);

		PdfPCell cell249 = new PdfPCell(new Paragraph("7(h)", font));
		cell249.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell249.setGrayFill(0.92f);
		table211.addCell(cell249);

		PdfPCell cell250 = new PdfPCell(
				new Paragraph("Size(cross section area/diameter/thickness) of earth electrode", font));
		cell250.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell250.setGrayFill(0.92f);
		table211.addCell(cell250);

		PdfPCell cell2511 = new PdfPCell(new Paragraph(earthingSystem.getSizeOfEarthElectrodeOb(), font));
		cell2511.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell2511);

		PdfPCell cell252 = new PdfPCell(new Paragraph(earthingSystem.getSizeOfEarthElectrodeRem(), font));
		cell252.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell252);

		PdfPCell cell253 = new PdfPCell(new Paragraph("7(i)", font));
		cell253.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell253.setGrayFill(0.92f);
		table211.addCell(cell253);

		PdfPCell cell254 = new PdfPCell(
				new Paragraph("Maximum distance between earth electrode and wall in meters", font));
		cell254.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell254.setGrayFill(0.92f);
		table211.addCell(cell254);

		PdfPCell cell255 = new PdfPCell(
				new Paragraph(earthingSystem.getMaximumDistanceEartElectrodeWalOb().toString(), font));
		cell255.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell255);

		PdfPCell cell256 = new PdfPCell(new Paragraph(earthingSystem.getMaximumDistanceEartElectrodeWalRem(), font));
		cell256.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell256);

		PdfPCell cell257 = new PdfPCell(new Paragraph("7(j)", font));
		cell257.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell257.setGrayFill(0.92f);
		table211.addCell(cell257);

		PdfPCell cell258 = new PdfPCell(new Paragraph("Minimum distance between earth electrode and wall", font));
		cell258.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell258.setGrayFill(0.92f);
		table211.addCell(cell258);

		PdfPCell cell259 = new PdfPCell(
				new Paragraph(earthingSystem.getManimumDistanceEartElectrodeWalOb().toString(), font));
		cell259.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell259);

		PdfPCell cell260 = new PdfPCell(new Paragraph(earthingSystem.getManimumDistanceEartElectrodeWalRem(), font));
		cell260.setHorizontalAlignment(Element.ALIGN_LEFT);
		table211.addCell(cell260);

		document.add(table211);
	}

	private void eartingDescTypeAiter(Document document, Font font, EarthingDescription earthDesc)
			throws DocumentException {
		float[] pointColumnWidths1 = { 30F, 145F, 55F, 50F };

		PdfPTable table203 = new PdfPTable(pointColumnWidths1);
		table203.setWidthPercentage(100); // Width 100%
		// table202.setSpacingBefore(20f); // Space before table
		table203.setWidthPercentage(100);

		PdfPCell cell265 = new PdfPCell(new Paragraph("4(a)", font));
		cell265.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell265.setGrayFill(0.92f);
		table203.addCell(cell265);

		PdfPCell cell266 = new PdfPCell(new Paragraph("Soil resistivity in ohm-meter", font));
		cell266.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell266.setGrayFill(0.92f);
		table203.addCell(cell266);

		PdfPCell cell267 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInOb().toString(), font));
		cell267.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell267);

		PdfPCell cell268 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInRem(), font));
		cell268.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell268);

		PdfPCell cell269 = new PdfPCell(new Paragraph("4(b)", font));
		cell269.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell269.setGrayFill(0.92f);
		table203.addCell(cell269);

		PdfPCell cell270 = new PdfPCell(
				new Paragraph("Number of earth electrode should not be less than the number of down conductors", font));
		cell270.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell270.setGrayFill(0.92f);
		table203.addCell(cell270);

		PdfPCell cell271 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInOb(), font));
		cell271.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell271);

		PdfPCell cell272 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInRem(), font));
		cell272.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell272);

		PdfPCell cell1788 = new PdfPCell(new Paragraph("4(c)", font));
		cell1788.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1788.setGrayFill(0.92f);
		table203.addCell(cell1788);

		PdfPCell cell1741 = new PdfPCell(
				new Paragraph("Ensure all down conductors are connected to earth termination system", font));
		cell1741.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1741.setGrayFill(0.92f);
		table203.addCell(cell1741);

		PdfPCell cell1715 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInOb(), font));
		cell1715.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell1715);

		PdfPCell cell1766 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInRem(), font));
		cell1766.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell1766);

		PdfPCell cell1691 = new PdfPCell(new Paragraph("4(d)", font));
		cell1691.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1691.setGrayFill(0.92f);
		table203.addCell(cell1691);

		PdfPCell cell1701 = new PdfPCell(new Paragraph(
				"Route of earthing conductor from test joint to earth electrode (under soil/under concrete/via gutter)",
				font));
		cell1701.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1701.setGrayFill(0.92f);
		table203.addCell(cell1701);

		PdfPCell cell1712 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInOb(), font));
		cell1712.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell1712);

		PdfPCell cell1723 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInRem(), font));
		cell1723.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell1723);

		PdfPCell cell165 = new PdfPCell(new Paragraph("4(e)", font));
		cell165.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell165.setGrayFill(0.92f);
		table203.addCell(cell165);

		PdfPCell cell166 = new PdfPCell(new Paragraph(
				"If earth enhancement materials are used, ensure whether they are filled properly upto ground level",
				font));
		cell166.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell166.setFixedHeight(20f);
		cell166.setGrayFill(0.92f);
		table203.addCell(cell166);

		PdfPCell cell1241 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInOb(), font));
		cell1241.setHorizontalAlignment(Element.ALIGN_LEFT);

		table203.addCell(cell1241);

		PdfPCell cell12412 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInRem(), font));
		cell12412.setHorizontalAlignment(Element.ALIGN_LEFT);
		table203.addCell(cell12412);
		document.add(table203);

		List<EarthingDescriptionList> earthDesTypeAiter1 = earthDesc.getEarthingDescriptionList();
		// EarthingDescriptionList earthDesTypeAiter=earthDesTypeAiter1.get(0);

		// EarthingDescriptionList iteration
		for (EarthingDescriptionList earthDesTypeAiter : earthDesTypeAiter1) {
			if (!earthDesTypeAiter.getFlag().equals("R")) {
				earthDescListIter(document, font, pointColumnWidths1, earthDesTypeAiter);
			}
		}

		PdfPTable table206 = new PdfPTable(pointColumnWidths1);
		table206.setWidthPercentage(100); // Width 100%
		table206.setSpacingBefore(15f); // Space before table
		table206.setWidthPercentage(100);

		PdfPCell cell1794 = new PdfPCell(new Paragraph("4(k)", font));
		cell1794.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1794.setGrayFill(0.92f);
		table206.addCell(cell1794);

		PdfPCell cell11 = new PdfPCell(
				new Paragraph("Maximum distance between earth electrode and wall in meters", font));
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell11.setGrayFill(0.92f);
		table206.addCell(cell11);

		PdfPCell cell112 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInOb().toString(), font));
		cell112.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell112);

		PdfPCell cell13 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInRem(), font));
		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell13);

		PdfPCell cell14 = new PdfPCell(new Paragraph("4(l)", font));
		cell14.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell14.setGrayFill(0.92f);
		table206.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(
				new Paragraph("Minimum distance between earth electrode and wall in meters", font));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell15.setGrayFill(0.92f);
		table206.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(
				new Paragraph(earthDesc.getEarthelectManimumDistanceWallInOb().toString(), font));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell16);

		PdfPCell cell17 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManimumDistanceWallInRem(), font));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph("4(m)", font));
		cell18.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell18.setGrayFill(0.92f);
		table206.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(
				"Maximum distance between earth electrodes in meters (in case of multiple rods in one location)",
				font));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell19.setGrayFill(0.92f);
		table206.addCell(cell19);

		PdfPCell cell26 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistOb().toString(), font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistRem(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell27);

		PdfPCell cell34 = new PdfPCell(new Paragraph("4(n)", font));
		cell34.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell34.setGrayFill(0.92f);
		table206.addCell(cell34);

		PdfPCell cell40 = new PdfPCell(new Paragraph(
				"Minimum distance between earth electrodes in meters (in case of multiple rods in one location)",
				font));
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell40.setGrayFill(0.92f);
		table206.addCell(cell40);

		PdfPCell cell41 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistOb().toString(), font));
		cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell41);

		PdfPCell cell42 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistRem(), font));
		cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell42);

		PdfPCell cell341 = new PdfPCell(new Paragraph("4(o)", font));
		cell341.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell341.setGrayFill(0.92f);
		table206.addCell(cell341);

		PdfPCell cell401 = new PdfPCell(new Paragraph("Total number of earthing electrodes", font));
		cell401.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell401.setGrayFill(0.92f);
		table206.addCell(cell401);

		PdfPCell cell411 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeOb().toString(), font));
		cell411.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell411);

		PdfPCell cell421 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeRem(), font));
		cell421.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell421);

		PdfPCell cell342 = new PdfPCell(new Paragraph("4(p)", font));
		cell342.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell342.setGrayFill(0.92f);
		table206.addCell(cell342);

		PdfPCell cell344 = new PdfPCell(new Paragraph("Number of earthing electrode inspected", font));
		cell344.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell344.setGrayFill(0.92f);
		table206.addCell(cell344);

		PdfPCell cell431 = new PdfPCell(new Paragraph(earthDesc.getInspectedNoOb().toString(), font));
		cell431.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell431);

		PdfPCell cell403 = new PdfPCell(new Paragraph(earthDesc.getInspectedNoRem(), font));
		cell403.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell403);

		PdfPCell cell3421 = new PdfPCell(new Paragraph("4(q)", font));
		cell3421.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell3421.setGrayFill(0.92f);
		table206.addCell(cell3421);

		PdfPCell cell3424 = new PdfPCell(new Paragraph("Number of inspection passed", font));
		cell3424.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3424.setGrayFill(0.92f);
		table206.addCell(cell3424);

		PdfPCell cell4312 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoOb().toString(), font));
		cell4312.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell4312);

		PdfPCell cell4032 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoRem(), font));
		cell4032.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell4032);

		PdfPCell cell34211 = new PdfPCell(new Paragraph("4(r)", font));
		cell34211.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell34211.setGrayFill(0.92f);
		table206.addCell(cell34211);

		PdfPCell cell34241 = new PdfPCell(new Paragraph(
				"Number of inspection failed (loose rods/pipe in soil are also considered as a failure in all tests)",
				font));
		cell34241.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell34241.setGrayFill(0.92f);
		table206.addCell(cell34241);

		PdfPCell cell43121 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedNoOb().toString(), font));
		cell43121.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell43121);

		PdfPCell cell40322 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedNoRem(), font));
		cell40322.setHorizontalAlignment(Element.ALIGN_LEFT);
		table206.addCell(cell40322);

		document.add(table206);
	}

	private void earthDescListIter(Document document, Font font, float[] pointColumnWidths1,
			EarthingDescriptionList earthDesTypeAiter) throws DocumentException {
	
		PdfPTable table204 = new PdfPTable(pointColumnWidths1);
		table204.setWidthPercentage(100); // Width 100%
		table204.setSpacingBefore(15f); // Space before table
		table204.setWidthPercentage(100);

		PdfPCell cell1651 = new PdfPCell(new Paragraph("4(f)", font));
		cell1651.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1651.setGrayFill(0.92f);
		table204.addCell(cell1651);

		PdfPCell cell1661 = new PdfPCell(new Paragraph("Material of earthing conductor", font));
		cell1661.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1661.setFixedHeight(20f);
		cell1661.setGrayFill(0.92f);
		table204.addCell(cell1661);

		PdfPCell cell12413 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthingConductorMaterialInOb(), font));
		cell12413.setHorizontalAlignment(Element.ALIGN_LEFT);

		table204.addCell(cell12413);

		PdfPCell cell12417 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthingConductorMaterialInRem(), font));
		cell12417.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell12417);

		PdfPCell cell16512 = new PdfPCell(new Paragraph("4(g)", font));
		cell16512.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell16512.setGrayFill(0.92f);
		table204.addCell(cell16512);

		PdfPCell cell16613 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell16613.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell16613.setFixedHeight(20f);
		cell16613.setGrayFill(0.92f);
		table204.addCell(cell16613);

		PdfPCell cell124132 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeMaterialInOb(), font));
		cell124132.setHorizontalAlignment(Element.ALIGN_LEFT);

		table204.addCell(cell124132);

		PdfPCell cell124172 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeMaterialInRem(), font));
		cell124172.setHorizontalAlignment(Element.ALIGN_LEFT);

		table204.addCell(cell124172);

		PdfPCell cell1731 = new PdfPCell(new Paragraph("4(h)", font));
		cell1731.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1731.setGrayFill(0.92f);
		table204.addCell(cell1731);

		PdfPCell cell1742 = new PdfPCell(new Paragraph("Type of earth electrode", font));
		cell1742.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1742.setGrayFill(0.92f);
		table204.addCell(cell1742);

		PdfPCell cell1753 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeTypeInOb(), font));
		cell1753.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell1753);

		PdfPCell cell1765 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeTypeInRem(), font));
		cell1765.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell1765);

		PdfPCell cell1790 = new PdfPCell(new Paragraph("4(i)", font));
		cell1790.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1790.setGrayFill(0.92f);
		table204.addCell(cell1790);

		PdfPCell cell1012 = new PdfPCell(new Paragraph("Size/cross section area of earth electrode", font));
		cell1012.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1012.setGrayFill(0.92f);
		table204.addCell(cell1012);

		PdfPCell cell17531 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeSizeInOb(), font));
		cell17531.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell17531);

		PdfPCell cell17653 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeSizeInRem(), font));
		cell17653.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell17653);

		PdfPCell cell17901 = new PdfPCell(new Paragraph("4(j)", font));
		cell17901.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell17901.setGrayFill(0.92f);
		table204.addCell(cell17901);

		PdfPCell cell10121 = new PdfPCell(new Paragraph("Length/depth of earth electrode in meters", font));
		cell10121.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell10121.setGrayFill(0.92f);
		table204.addCell(cell10121);

		PdfPCell cell175313 = new PdfPCell(
				new Paragraph(earthDesTypeAiter.getEarthElectrodeLengthingOb().toString(), font));
		cell175313.setHorizontalAlignment(Element.ALIGN_LEFT);
		table204.addCell(cell175313);

		PdfPCell cell176531 = new PdfPCell(new Paragraph(earthDesTypeAiter.getEarthElectrodeLengthingRem(), font));
		cell176531.setHorizontalAlignment(Element.ALIGN_LEFT);

		table204.addCell(cell176531);

		document.add(table204);
	}
}