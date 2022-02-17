package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirBasicDescription;
import com.capeelectric.model.AirClamps;
import com.capeelectric.model.AirConnectors;
import com.capeelectric.model.AirExpansion;
import com.capeelectric.model.AirHolderDescription;
import com.capeelectric.model.AirHolderList;
import com.capeelectric.model.AirMeshDescription;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.model.VerticalAirTerminationList;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.PrintAirTerminationService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintAirTerminationServiceImplPDF implements PrintAirTerminationService {

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;

//	@Override
//	public void printAirTermination(String userName, Integer basicLpsId,Optional<BasicLps> basicLpsDetails, Optional<LpsAirDiscription> lpsAirDisc) throws AirTerminationException {

	@Override
	public void printAirTermination1(String userName, Integer basicLpsId) throws AirTerminationException {

		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AirTermination.pdf"));

				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				BasicLps basicLps1 = basicLps.get(0);

				List<AirTermination> lpsAirDisc = airTerminationLpsRepository.findByUserNameAndBasicLpsId(userName,
						basicLpsId);
				AirTermination airTermination = lpsAirDisc.get(0);

				List<LpsAirDiscription> lpsAirTermination = airTermination.getLpsAirDescription();
				LpsAirDiscription lpsAirTermination1 = lpsAirTermination.get(0);

				List<AirClamps> airCla = lpsAirTermination1.getAirClamps();
				AirClamps airClamps = airCla.get(0);

				List<AirConnectors> airConn = lpsAirTermination1.getAirConnectors();
				AirConnectors airConnectors = airConn.get(0);

				List<AirExpansion> airExpan = lpsAirTermination1.getAirExpansion();
				AirExpansion airExpansion = airExpan.get(0);

				List<AirHolderDescription> airHolderdesc = lpsAirTermination1.getAirHolderDescription();
				AirHolderDescription airholders = airHolderdesc.get(0);

				List<AirHolderList> airHolderL = airholders.getAirHolderList();
				AirHolderList airHolderList1 = airHolderL.get(0);

				List<AirMeshDescription> airMeshDesc = lpsAirTermination1.getAirMeshDescription();
				AirMeshDescription airMeshDescription = airMeshDesc.get(0);

				List<AirBasicDescription> airBasicDesc = lpsAirTermination1.getAirBasicDescription();
				AirBasicDescription airBasicDesciption = airBasicDesc.get(0);

				List<LpsVerticalAirTermination> lpsVerticalAirTerm = lpsAirTermination1.getLpsVerticalAirTermination();
				LpsVerticalAirTermination lpsVerticalAirTermination = lpsVerticalAirTerm.get(0);

				List<VerticalAirTerminationList> VerticleAirTerminationL = lpsVerticalAirTermination
						.getVerticalAirTerminationList();
				VerticalAirTerminationList VerticleAirTerminationList = VerticleAirTerminationL.get(0);

				document.open();

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table
				headertable.setWidthPercentage(100);

				PdfPCell label = new PdfPCell(new Paragraph(
						"Check list for Air Termination System of LPS\r\n" + "as per IS/IEC 62305", font1));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
//				label.setFixedHeight(20f);
				headertable.addCell(label);
				document.add(headertable);

				float[] pointColumnWidths5 = { 100F };

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

				float[] pointColumnWidths = { 120F, 80F };
				PdfPTable table100 = new PdfPTable(pointColumnWidths);

				table100.setWidthPercentage(100); // Width 100%
				table100.setSpacingBefore(10f); // Space before table
				table100.setSpacingAfter(5f); // Space after table
				table100.setWidthPercentage(100);
				table100.getDefaultCell().setBorder(0);

				PdfPCell cell1 = new PdfPCell(new Paragraph("Building number:",
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
				cell1.setBackgroundColor(new BaseColor(203, 183, 162));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table100.addCell(cell1);
				PdfPCell cell2 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingNumber().toString(),
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
						new Paragraph("Building name:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
				cell3.setBackgroundColor(new BaseColor(203, 183, 162));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setBorder(PdfPCell.NO_BORDER);
				table1001.addCell(cell3);
				PdfPCell cell4 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingName(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setBackgroundColor(new BaseColor(203, 183, 162));
				cell4.setBorder(PdfPCell.NO_BORDER);
				table1001.addCell(cell4);
				document.add(table1001);

				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnWidths1 = { 30F, 70F };

				PdfPTable table1 = new PdfPTable(pointColumnWidths1);
				table1.setWidthPercentage(100); // Width 100%
				table1.setSpacingBefore(5f); // Space before table
				table1.setWidthPercentage(100);

				PdfPCell cell5 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell5.setGrayFill(0.92f);
				cell5.setFixedHeight(20f);
				table1.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingType(), font3));
				cell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph("Others", font2));
				cell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell7.setGrayFill(0.92f);
				cell7.setFixedHeight(20f);
				table1.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingTypeOthers(), font3));
				cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell8);

				document.add(table1);

				float[] pointColumnWidths20 = { 38.5F, 15F, 15F, 15F, 15F, 15F, 15F };

				PdfPTable table31 = new PdfPTable(pointColumnWidths20);
				table31.setWidthPercentage(100); // Width 100%
				// table3.setSpacingBefore(10f); // Space before table
				table31.setWidthPercentage(100);

				PdfPCell cell9 = new PdfPCell(new Paragraph("Building Dimension", font2));
				cell9.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell9.setGrayFill(0.92f);
				cell9.setFixedHeight(20f);
				table31.addCell(cell9);
				PdfPCell cell10 = new PdfPCell(new Paragraph("Length(m)", font2));
				cell10.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell10.setGrayFill(0.92f);
				cell10.setFixedHeight(20f);
				table31.addCell(cell10);

				PdfPCell cell11 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingLength().toString()));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setFixedHeight(20f);
				table31.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(new Paragraph("Width(m)", font2));
				cell12.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell12.setGrayFill(0.92f);
				cell12.setFixedHeight(20f);
				table31.addCell(cell12);

				PdfPCell cell13 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingWidth().toString()));
				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell13.setFixedHeight(20f);
				table31.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(new Paragraph("Height(m)", font2));
				cell14.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell14.setGrayFill(0.92f);
				cell14.setFixedHeight(20f);
				table31.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(new Paragraph(lpsAirTermination1.getBuildingHeight().toString()));
				cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
				table31.addCell(cell15);

				document.add(table31);

				PdfPTable table4 = new PdfPTable(pointColumnWidths1);
				table4.setWidthPercentage(100); // Width 100%
				// table4.setSpacingBefore(10f); // Space before table
				table4.setWidthPercentage(100);

				PdfPCell cell18 = new PdfPCell(new Paragraph("Height of tallest protrusion (m)", font2));
				cell18.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell18.setGrayFill(0.92f);
				cell18.setFixedHeight(20f);
				table4.addCell(cell18);

				PdfPCell cell19 = new PdfPCell(new Paragraph(lpsAirTermination1.getProtrusionHeight().toString()));
				cell19.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table4.addCell(cell19);

				PdfPCell cell16 = new PdfPCell(new Paragraph("Level of protection", font2));
				cell16.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell16.setFixedHeight(20f);
				cell16.setGrayFill(0.92f);
				table4.addCell(cell16);

				PdfPCell cell17 = new PdfPCell(new Paragraph(lpsAirTermination1.getProtectionLevel(), font3));
				cell17.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table4.addCell(cell17);

				document.add(table4);

//				PdfPTable BasicDrawingDetails = new PdfPTable(pointColumnWidths5);
//				BasicDrawingDetails.setWidthPercentage(100); // Width 100%
//				BasicDrawingDetails.setSpacingBefore(10f); // Space before table
//				BasicDrawingDetails.setSpacingAfter(5f); // Space after table
//				BasicDrawingDetails.getDefaultCell().setBorder(0);
//
//				PdfPCell DrawingDetails = new PdfPCell(
//						new Paragraph("Drawing Details", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				DrawingDetails.setBackgroundColor(new GrayColor(0.82f));
//				DrawingDetails.setHorizontalAlignment(Element.ALIGN_CENTER);
//				DrawingDetails.setBorder(PdfPCell.NO_BORDER);
//				BasicDrawingDetails.addCell(DrawingDetails);
//				document.add(BasicDrawingDetails);
				
				float[] pointColumnWidths49 = { 25F, 150F, 55F, 50F };

				PdfPTable BasicDrawingDetails = new PdfPTable(pointColumnWidths49);
				BasicDrawingDetails.setWidthPercentage(100); // Width 100%
				BasicDrawingDetails.setSpacingBefore(10f); // Space before table
				BasicDrawingDetails.setSpacingAfter(10f); // Space after table

				Font font123 = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				cell11.setPhrase(new Phrase("Drawing Details", font123));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				BasicDrawingDetails.addCell(cell11);

				document.add(BasicDrawingDetails);
				
				

				float[] pointColumnWidths2 = { 120F, 80F };
				PdfPTable table2 = new PdfPTable(pointColumnWidths2);

				table2.setWidthPercentage(100); // Width 100%
//				table2.setSpacingBefore(5f); // Space before table
//				table2.setSpacingAfter(5f); // Space after table
				table2.getDefaultCell().setBorder(0);

				PdfPCell cell20 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell20.setBackgroundColor(new GrayColor(0.93f));
				cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell20.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell20);
				PdfPCell cell21 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirBasicDescriptionAvailabilityOb(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell21.setBackgroundColor(new GrayColor(0.93f));
				cell21.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell21);

				PdfPCell cell22 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell22.setBackgroundColor(new GrayColor(0.93f));
				cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell22.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell22);
				PdfPCell cell23 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirBasicDescriptionAvailabilityRem(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell23.setBackgroundColor(new GrayColor(0.93f));
				cell23.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell23);

				document.add(table2);

				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnWidths4 = { 25F, 150F, 55F, 50F };

				PdfPTable table3 = new PdfPTable(pointColumnWidths4);
				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(10f); // Space before table
				table3.setSpacingAfter(10f);
				table3.setWidthPercentage(100);

				PdfPCell cell24 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell24.setGrayFill(0.92f);

				PdfPCell cell25 = new PdfPCell(new Paragraph("Description", font11));
				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell25.setFixedHeight(20f);
				cell25.setGrayFill(0.92f);
				table3.addCell(cell24);
				table3.addCell(cell25);

				PdfPCell cell26 = new PdfPCell(new Paragraph("Observation", font11));
				cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell26.setFixedHeight(20f);
				cell26.setGrayFill(0.92f);
				table3.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph("Remarks", font11));
				cell27.setGrayFill(0.92f);
				cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(cell27);

				tableData(table3, airBasicDesciption, document);

				document.add(table3);

				document.newPage();

				float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

				PdfPTable table5 = new PdfPTable(pointColumnWidths41);
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(10f); // Space before table
				table5.setSpacingAfter(10f); // Space after table

				Font font = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//				Font fonthead = new Font(BaseFont.createFont(), 11  | Font.BOLD, Font.NORMAL, BaseColor.BLACK);

				cell11.setPhrase(new Phrase("Vertical air terminal (VAT)", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				table5.addCell(cell11);

				document.add(table5);
				
				
//				PdfPTable verticalHead = new PdfPTable(pointColumnWidths5);
//
//				verticalHead.setWidthPercentage(100); // Width 100%
//				verticalHead.setSpacingBefore(10f); // Space before table
//				verticalHead.setSpacingAfter(5f); // Space after table
//				verticalHead.getDefaultCell().setBorder(0);
//				
//				PdfPCell verticleLabel = new PdfPCell(
//						new Paragraph("Vertical air terminal (VAT)", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				verticleLabel.setBackgroundColor(new GrayColor(0.82f));
//				verticleLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				verticleLabel.setBorder(PdfPCell.NO_BORDER);
//				verticalHead.addCell(verticleLabel);
//
//				document.add(verticalHead);

//				float[] pointColumnWidthsAvailability = { 120F, 80F };

				PdfPTable table6 = new PdfPTable(pointColumnWidths2);

				table6.setWidthPercentage(100); // Width 100%
//				table6.setSpacingBefore(5f); // Space before table
//				table6.setSpacingAfter(5f); // Space after table
				table6.getDefaultCell().setBorder(0);

				PdfPCell cell29 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell29.setBackgroundColor(new GrayColor(0.93f));
				cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell29.setBorder(PdfPCell.NO_BORDER);
				table6.addCell(cell29);
				PdfPCell cell30 = new PdfPCell(
						new Paragraph(lpsAirTermination1.getVerticalAirTerminationAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell30.setBackgroundColor(new GrayColor(0.93f));
				cell30.setBorder(PdfPCell.NO_BORDER);
				table6.addCell(cell30);

				PdfPCell cell31 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell31.setBackgroundColor(new GrayColor(0.93f));
				cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell31.setBorder(PdfPCell.NO_BORDER);
				table6.addCell(cell31);
				PdfPCell cell32 = new PdfPCell(
						new Paragraph(lpsAirTermination1.getVerticalAirTerminationAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell32.setBackgroundColor(new GrayColor(0.93f));
				cell32.setBorder(PdfPCell.NO_BORDER);
				table6.addCell(cell32);

				document.add(table6);

				if (lpsAirTermination1.getVerticalAirTerminationAvailabilityOb().equalsIgnoreCase("applicable")) {

					PdfPTable table8 = new PdfPTable(pointColumnWidths4);
					table8.setWidthPercentage(100); // Width 100%
					table8.setSpacingBefore(10f); // Space before table
					table8.setSpacingAfter(10f);
					table8.setWidthPercentage(100);

					PdfPCell cell33 = new PdfPCell(new Paragraph("SL.NO", font11));
					cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell33.setGrayFill(0.92f);

					PdfPCell cell34 = new PdfPCell(new Paragraph("Description", font11));
					cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell34.setFixedHeight(20f);
					cell34.setGrayFill(0.92f);
					table8.addCell(cell33);
					table8.addCell(cell34);

					PdfPCell cell35 = new PdfPCell(new Paragraph("Observation", font11));
					cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell35.setFixedHeight(20f);
					cell35.setGrayFill(0.92f);
					table8.addCell(cell35);

					PdfPCell cell36 = new PdfPCell(new Paragraph("Remarks", font11));
					cell36.setGrayFill(0.92f);
					cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
					table8.addCell(cell36);

					Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase("6(a)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell);

					cell.setPhrase(new Phrase(
							"Physical inspection (check for any damage/break/bend/interception tip/position)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell);

					PdfPCell cell37 = new PdfPCell(
							new Paragraph(lpsVerticalAirTermination.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell37);

					if (lpsVerticalAirTermination.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(lpsVerticalAirTermination.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					document.add(table8);

//need to create method with iteration

					for (VerticalAirTerminationList VerticalAirTerminationList : VerticleAirTerminationL) {

						PdfPTable table9 = VerticalAirTerminationItr(VerticalAirTerminationList, font1A);
						document.add(table9);
					}

					PdfPTable tableNote = new PdfPTable(1);

					tableNote.setWidthPercentage(100); // Width 100%
//				    tableNote.setSpacingBefore(5f); // Space before table
					tableNote.setSpacingAfter(5f); // Space after table
					tableNote.setWidthPercentage(100);
					tableNote.getDefaultCell().setBorder(0);

					PdfPCell cell62 = new PdfPCell(new Paragraph(
							"Physical inspection of interconnection of VAT to roof conductor & metal bodies to roof conductors (check for connections (tight/loose), its conditions (healthy/corroded)and its position/location w.r.t. design",
							new Font(BaseFont.createFont(), 10, Font.NORMAL)));
					cell62.setBackgroundColor(new GrayColor(0.93f));
					cell62.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell62.setBorder(PdfPCell.NO_BORDER);
					tableNote.addCell(cell62);

					document.add(tableNote);

					PdfPTable table9 = new PdfPTable(pointColumnWidths4);
					table9.setWidthPercentage(100); // Width 100%
					table9.setSpacingBefore(5f); // Space before table
					table9.setSpacingAfter(10f);
					table9.setWidthPercentage(100);

					PdfPCell cell54 = new PdfPCell();
					cell54.setPhrase(new Phrase("6(j)", font1A));
					cell54.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell54.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell54);

					cell.setPhrase(new Phrase("Total number of air terminals", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell);

					PdfPCell cell55 = new PdfPCell(
							new Paragraph(lpsVerticalAirTermination.getTotalNumberOb().toString(), font1A));
					cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
					table9.addCell(cell55);

					if (lpsVerticalAirTermination.getTotalNumberRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(lpsVerticalAirTermination.getTotalNumberRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell39);
					}

					PdfPCell cell56 = new PdfPCell();
					cell56.setPhrase(new Phrase("6(k)", font1A));
					cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell56.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell56);

					cell.setPhrase(new Phrase("Number of air terminals inspected", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell);

					PdfPCell cell57 = new PdfPCell(
							new Paragraph(lpsVerticalAirTermination.getInspNoOb().toString(), font1A));
					cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
					table9.addCell(cell57);

					if (lpsVerticalAirTermination.getInspNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(lpsVerticalAirTermination.getInspNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell39);
					}

					PdfPCell cell58 = new PdfPCell();
					cell58.setPhrase(new Phrase("6(l)", font1A));
					cell58.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell58.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell58);

					cell.setPhrase(new Phrase("Number of inspections passed", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell);

					PdfPCell cell59 = new PdfPCell(
							new Paragraph(lpsVerticalAirTermination.getInspPassedNoOb().toString(), font1A));
					cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
					table9.addCell(cell59);

					if (lpsVerticalAirTermination.getInspPassedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(lpsVerticalAirTermination.getInspPassedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell39);
					}

					PdfPCell cell60 = new PdfPCell();
					cell60.setPhrase(new Phrase("6(m)", font1A));
					cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell60.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell60);

					cell.setPhrase(new Phrase("Number of inspections failed", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table9.addCell(cell);

					PdfPCell cell61 = new PdfPCell(
							new Paragraph(lpsVerticalAirTermination.getInspFaileddNoOb().toString(), font1A));
					cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
					table9.addCell(cell61);

					if (lpsVerticalAirTermination.getInspFaileddNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(lpsVerticalAirTermination.getInspFaileddNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell39);
					}
					document.add(table9);
				}

				PdfPTable table7 = new PdfPTable(pointColumnWidths41);
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
				table7.setSpacingAfter(10f); // Space after table

//				Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//				Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

//				cell11.setPhrase(new Phrase("Mesh conductors", font));
//				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell11.setBackgroundColor(new GrayColor(0.93f));
//				cell11.setFixedHeight(20f);
//				cell11.setColspan(4);
//				table7.addCell(cell11);
				
//				PdfPTable meshHead = new PdfPTable(pointColumnWidths5);
//
//				meshHead.setWidthPercentage(100); // Width 100%
//				meshHead.setSpacingBefore(10f); // Space before table
//				meshHead.setSpacingAfter(5f); // Space after table
//				meshHead.getDefaultCell().setBorder(0);
//				
//				PdfPCell meshConductorLabel = new PdfPCell(
//						new Paragraph("Mesh conductors", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				meshConductorLabel.setBackgroundColor(new GrayColor(0.82f));
//				meshConductorLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				meshConductorLabel.setBorder(PdfPCell.NO_BORDER);
//				meshHead.addCell(meshConductorLabel);
//
//				document.add(meshHead);
				
				PdfPTable meshHead = new PdfPTable(pointColumnWidths41);
				meshHead.setWidthPercentage(100); // Width 100%
				meshHead.setSpacingBefore(10f); // Space before table
				meshHead.setSpacingAfter(10f); // Space after table

				cell11.setPhrase(new Phrase("Mesh conductors", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				meshHead.addCell(cell11);

				document.add(meshHead);

//				float[] pointColumnWidthsAvailability = { 120F, 80F };

				PdfPTable table10 = new PdfPTable(pointColumnWidths2);

				table10.setWidthPercentage(100); // Width 100%
//				table10.setSpacingBefore(5f); // Space before table
//				table10.setSpacingAfter(5f); // Space after table
				table10.getDefaultCell().setBorder(0);

				PdfPCell cell70 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell70.setBackgroundColor(new GrayColor(0.93f));
				cell70.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell70.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell70);
				PdfPCell cell71 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirMeshDescriptionAvailabilityOb(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell71.setBackgroundColor(new GrayColor(0.93f));
				cell71.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell71);

				PdfPCell cell72 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell72.setBackgroundColor(new GrayColor(0.93f));
				cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell72.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell72);
				PdfPCell cell73 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirMeshDescriptionAvailabilityRem(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell73.setBackgroundColor(new GrayColor(0.93f));
				cell73.setBorder(PdfPCell.NO_BORDER);
				table10.addCell(cell73);

				document.add(table10);

				if (lpsAirTermination1.getAirMeshDescriptionAvailabilityOb().equalsIgnoreCase("applicable")) {

					Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

					PdfPTable table8 = new PdfPTable(pointColumnWidths4);
					table8.setWidthPercentage(100); // Width 100%
					table8.setSpacingBefore(10f); // Space before table
					table8.setSpacingAfter(10f);
					table8.setWidthPercentage(100);

					PdfPCell cell75 = new PdfPCell();
					cell75.setPhrase(new Phrase("7(a)", font1A));
					cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell75.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell75);

					PdfPCell cell76 = new PdfPCell();
					cell76.setPhrase(new Phrase("Physical inspection (damage/break/bend/corroded/routing w.r.t design)",
							font1A));
					cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell76.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell76);

					PdfPCell cell37 = new PdfPCell(new Paragraph(airMeshDescription.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell37);

					if (airMeshDescription.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell77 = new PdfPCell();
					cell77.setPhrase(new Phrase("7(b)", font1A));
					cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell77.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell77);

					PdfPCell cell78 = new PdfPCell();
					cell78.setPhrase(new Phrase("Material of conductor", font1A));
					cell78.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell78.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell78);

					PdfPCell cell79 = new PdfPCell(
							new Paragraph(airMeshDescription.getMaterailOfConductorOb(), font1A));
					cell79.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell79);

					if (airMeshDescription.getMaterailOfConductorRem() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getMaterailOfConductorRem(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell80 = new PdfPCell();
					cell80.setPhrase(new Phrase("7(c)", font1A));
					cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell80.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell80);

					PdfPCell cell81 = new PdfPCell();
					cell81.setPhrase(new Phrase("Size/cross section area of conductor", font1A));
					cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell81.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell81);

					PdfPCell cell82 = new PdfPCell(new Paragraph(airMeshDescription.getSizeOfConductorOb(), font1A));
					cell82.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell82);

					if (airMeshDescription.getSizeOfConductorRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getSizeOfConductorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell83 = new PdfPCell();
					cell83.setPhrase(new Phrase("7(d)", font1A));
					cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell83.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell83);

					PdfPCell cell84 = new PdfPCell();
					cell84.setPhrase(new Phrase("Mesh Size", font1A));
					cell84.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell84.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell84);

					PdfPCell cell85 = new PdfPCell(new Paragraph(airMeshDescription.getMeshSizeOb(), font1A));
					cell85.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell85);

					if (airMeshDescription.getMeshSizeRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airMeshDescription.getMeshSizeRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell86 = new PdfPCell();
					cell86.setPhrase(new Phrase("7(e)", font1A));
					cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell86.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell86);

					PdfPCell cell87 = new PdfPCell();
					cell87.setPhrase(
							new Phrase("Maximum distance between mesh conductors(in x direction) in meters", font1A));
					cell87.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell87.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell87);

					PdfPCell cell88 = new PdfPCell(
							new Paragraph(airMeshDescription.getMaximumDistanceXOb().toString(), font1A));
					cell88.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell88);

					if (airMeshDescription.getMaximumDistanceXRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getMaximumDistanceXRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell89 = new PdfPCell();
					cell89.setPhrase(new Phrase("7(f)", font1A));
					cell89.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell89.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell89);

					PdfPCell cell90 = new PdfPCell();
					cell90.setPhrase(
							new Phrase("Minimum distance between mesh conductors (in x direction) in meters", font1A));
					cell90.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell90.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell90);

					PdfPCell cell91 = new PdfPCell(
							new Paragraph(airMeshDescription.getMinimumDistanceXOb().toString(), font1A));
					cell91.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell91);

					if (airMeshDescription.getMinimumDistanceXRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getMinimumDistanceXRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell92 = new PdfPCell();
					cell92.setPhrase(new Phrase("7(e)", font1A));
					cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell92.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell92);

					PdfPCell cell93 = new PdfPCell();
					cell93.setPhrase(
							new Phrase("Maximum distance between mesh conductors(in y direction) in meters", font1A));
					cell93.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell93.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell93);

					PdfPCell cell94 = new PdfPCell(
							new Paragraph(airMeshDescription.getMaximumDistanceYOb().toString(), font1A));
					cell94.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell94);

					if (airMeshDescription.getMaximumDistanceYRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getMaximumDistanceYRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell95 = new PdfPCell();
					cell95.setPhrase(new Phrase("7(f)", font1A));
					cell95.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell95.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell95);

					PdfPCell cell96 = new PdfPCell();
					cell96.setPhrase(
							new Phrase("Minimum distance between mesh conductors (in y direction) in meters", font1A));
					cell96.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell96.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell96);

					PdfPCell cell97 = new PdfPCell(
							new Paragraph(airMeshDescription.getMinimumDistanceYOb().toString(), font1A));
					cell97.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell97);

					if (airMeshDescription.getMinimumDistanceYRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getMinimumDistanceYRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					PdfPCell cell98 = new PdfPCell();
					cell98.setPhrase(new Phrase("7(g)", font1A));
					cell98.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell98.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell98);

					PdfPCell cell99 = new PdfPCell();
					cell99.setPhrase(new Phrase(
							"Height of mesh conductors above flat surface (For flat roof, air termination system must be installed above probable water level during rain)in mm",
							font1A));
					cell99.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell99.setBackgroundColor(new GrayColor(0.93f));
					table8.addCell(cell99);

					PdfPCell cell100 = new PdfPCell(
							new Paragraph(airMeshDescription.getHeightOfConductorFlatSurfaceOb().toString(), font1A));
					cell100.setHorizontalAlignment(Element.ALIGN_LEFT);
					table8.addCell(cell100);

					if (airMeshDescription.getHeightOfConductorFlatSurfaceRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airMeshDescription.getHeightOfConductorFlatSurfaceRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell39);
					}

					document.add(table8);
				}


//				PdfPTable HoldersHead = new PdfPTable(pointColumnWidths5);
//				HoldersHead.setWidthPercentage(100); // Width 100%
//				HoldersHead.setSpacingBefore(10f); // Space before table
//				HoldersHead.setSpacingAfter(5f); // Space after table
//				HoldersHead.getDefaultCell().setBorder(0);
//
//				PdfPCell holdersLabel = new PdfPCell(
//						new Paragraph("Holders", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				holdersLabel.setBackgroundColor(new GrayColor(0.82f));
//				holdersLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				holdersLabel.setBorder(PdfPCell.NO_BORDER);
//				HoldersHead.addCell(holdersLabel);
//				document.add(HoldersHead);
				
				
				
				PdfPTable HoldersHead = new PdfPTable(pointColumnWidths41);
				HoldersHead.setWidthPercentage(100); // Width 100%
				HoldersHead.setSpacingBefore(10f); // Space before table
				HoldersHead.setSpacingAfter(10f); // Space after table

				cell11.setPhrase(new Phrase("Holders", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				HoldersHead.addCell(cell11);

				document.add(HoldersHead);
				
				

				PdfPTable table12 = new PdfPTable(pointColumnWidths2);

				table12.setWidthPercentage(100); // Width 100%
//				table12.setSpacingBefore(5f); // Space before table
//				table12.setSpacingAfter(5f); // Space after table
				table12.getDefaultCell().setBorder(0);

				PdfPCell cell101 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell101.setBackgroundColor(new GrayColor(0.93f));
				cell101.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell101.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell101);
				PdfPCell cell102 = new PdfPCell(
						new Paragraph(lpsAirTermination1.getAirHolderDescriptionAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell102.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell102.setBackgroundColor(new GrayColor(0.93f));
				cell102.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell102);

				PdfPCell cell103 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell103.setBackgroundColor(new GrayColor(0.93f));
				cell103.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell103.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell103);
				PdfPCell cell104 = new PdfPCell(
						new Paragraph(lpsAirTermination1.getAirHolderDescriptionAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell104.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell104.setBackgroundColor(new GrayColor(0.93f));
				cell104.setBorder(PdfPCell.NO_BORDER);
				table12.addCell(cell23);

				document.add(table12);

				Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				if (lpsAirTermination1.getVerticalAirTerminationAvailabilityOb().equalsIgnoreCase("applicable")) {

					PdfPTable table13 = new PdfPTable(pointColumnWidths4);
					table13.setWidthPercentage(100); // Width 100%
					table13.setSpacingBefore(10f); // Space before table
					table13.setSpacingAfter(10f);
					table13.setWidthPercentage(100);

					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase("8(a)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell);

					PdfPCell cell105 = new PdfPCell();
					cell105.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1A));
					cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell105.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell105);

					PdfPCell cell37 = new PdfPCell(new Paragraph(airholders.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table13.addCell(cell37);

					if (airholders.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airholders.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell39);
					}

					PdfPCell cell106 = new PdfPCell();
					cell106.setPhrase(new Phrase("8(b)", font1A));
					cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell106.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell106);

					PdfPCell cell107 = new PdfPCell();
					cell107.setPhrase(new Phrase(
							"Conductor holder is firmly fixed/mounted/pasted over the flat surface", font1A));
					cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell107.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell107);

					PdfPCell cell108 = new PdfPCell(
							new Paragraph(airholders.getConductorHolderFlatSurfaceOb(), font1A));
					cell108.setHorizontalAlignment(Element.ALIGN_LEFT);
					table13.addCell(cell108);

					if (airholders.getConductorHolderFlatSurfaceRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airholders.getConductorHolderFlatSurfaceRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell39);
					}

					PdfPCell cell109 = new PdfPCell();
					cell109.setPhrase(new Phrase("8(c)", font1A));
					cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell109.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell109);

					PdfPCell cell110 = new PdfPCell();
					cell110.setPhrase(new Phrase(
							"Conductor is properly holded in the holder and connection of conductor with holder(tight/loose/corroded)",
							font1A));
					cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell110.setBackgroundColor(new GrayColor(0.93f));
					table13.addCell(cell110);

					PdfPCell cell111 = new PdfPCell(new Paragraph(airholders.getConductorHolderOb(), font1A));
					cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
					table13.addCell(cell111);

					if (airholders.getConductorHolderOb() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airholders.getConductorHolderOb(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table13.addCell(cell39);
					}

					document.add(table13);

// need to create a method with iteration				

					for (AirHolderList airHolderList : airHolderL) {

						PdfPTable table14 = HoldersItr(airHolderList, pointColumnWidths4, font1A);
						document.add(table14);
					}

					PdfPTable table15 = new PdfPTable(pointColumnWidths4);
					table15.setWidthPercentage(100); // Width 100%
					table15.setSpacingBefore(5f); // Space before table
					table15.setSpacingAfter(10f);

					PdfPCell cell139 = new PdfPCell();
					cell139.setPhrase(new Phrase("8(j)", font1A));
					cell139.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell139.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell139);

					PdfPCell cell140 = new PdfPCell();
					cell140.setPhrase(new Phrase("Material of parpet holder", font1A));
					cell140.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell140.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell140);

					PdfPCell cell141 = new PdfPCell(new Paragraph(airholders.getMaterailOfParpetHolderOb(), font1A));
					cell141.setHorizontalAlignment(Element.ALIGN_LEFT);
					table15.addCell(cell141);

					if (airholders.getMaterailOfParpetHolderRem() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airholders.getMaterailOfParpetHolderRem(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell39);
					}

					PdfPCell cell142 = new PdfPCell();
					cell142.setPhrase(new Phrase("8(k)", font1A));
					cell142.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell142.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell142);

					PdfPCell cell143 = new PdfPCell();
					cell143.setPhrase(new Phrase("Total number of holders", font1A));
					cell143.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell143.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell143);

					PdfPCell cell144 = new PdfPCell(
							new Paragraph(airholders.getTotalParpetHolderNoOb().toString(), font1A));
					cell144.setHorizontalAlignment(Element.ALIGN_LEFT);
					table15.addCell(cell144);

					if (airholders.getTotalParpetHolderNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airholders.getTotalParpetHolderNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell39);
					}

					PdfPCell cell146 = new PdfPCell();
					cell146.setPhrase(new Phrase("8(l)", font1A));
					cell146.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell146.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell146);

					PdfPCell cell147 = new PdfPCell();
					cell147.setPhrase(new Phrase("Number of holders inspected", font1A));
					cell147.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell147.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell147);

					PdfPCell cell148 = new PdfPCell(
							new Paragraph(airholders.getParpetInspectionNoOb().toString(), font1A));
					cell148.setHorizontalAlignment(Element.ALIGN_LEFT);
					table15.addCell(cell148);

					if (airholders.getParpetInspectionNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airholders.getParpetInspectionNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell39);
					}

					PdfPCell cell149 = new PdfPCell();
					cell149.setPhrase(new Phrase("8(m)", font1A));
					cell149.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell149.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell149);

					PdfPCell cell150 = new PdfPCell();
					cell150.setPhrase(new Phrase("Number of inspections passed", font1A));
					cell150.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell150.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell150);

					PdfPCell cell151 = new PdfPCell(
							new Paragraph(airholders.getParpetInspectionPassedNoOb().toString(), font1A));
					cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
					table15.addCell(cell151);

					if (airholders.getParpetInspectionPassedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airholders.getParpetInspectionPassedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell39);
					}

					PdfPCell cell152 = new PdfPCell();
					cell152.setPhrase(new Phrase("8(n)", font1A));
					cell152.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell152.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell152);

					PdfPCell cell153 = new PdfPCell();
					cell153.setPhrase(new Phrase("Number of inspections failed", font1A));
					cell153.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell153.setBackgroundColor(new GrayColor(0.93f));
					table15.addCell(cell153);

					PdfPCell cell154 = new PdfPCell(
							new Paragraph(airholders.getParpetInspectionFailedNoOb().toString(), font1A));
					cell154.setHorizontalAlignment(Element.ALIGN_LEFT);
					table15.addCell(cell154);

					if (airholders.getParpetInspectionFailedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airholders.getParpetInspectionFailedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell39);
					}

					document.add(table15);
				}

//Clamps Accordian Start Here

//				PdfPTable ClampsHead = new PdfPTable(pointColumnWidths5);
//				ClampsHead.setWidthPercentage(100); // Width 100%
//				ClampsHead.setSpacingBefore(10f); // Space before table
//				ClampsHead.setSpacingAfter(5f); // Space after table
//				ClampsHead.getDefaultCell().setBorder(0);
//
//				PdfPCell clampsLabel = new PdfPCell(
//						new Paragraph("Clamps", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				clampsLabel.setBackgroundColor(new GrayColor(0.82f));
//				clampsLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				clampsLabel.setBorder(PdfPCell.NO_BORDER);
//				ClampsHead.addCell(clampsLabel);
//				document.add(ClampsHead);
				
				PdfPTable ClampsHead = new PdfPTable(pointColumnWidths41);
				ClampsHead.setWidthPercentage(100); // Width 100%
				ClampsHead.setSpacingBefore(10f); // Space before table
				ClampsHead.setSpacingAfter(10f); // Space after table

				cell11.setPhrase(new Phrase("Clamps", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				ClampsHead.addCell(cell11);

				document.add(ClampsHead);
				
				

				PdfPTable table16 = new PdfPTable(pointColumnWidths2);

				table16.setWidthPercentage(100); // Width 100%
//				table16.setSpacingBefore(5f); // Space before table
//				table16.setSpacingAfter(5f); // Space after table
				table16.getDefaultCell().setBorder(0);

				PdfPCell cell155 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell155.setBackgroundColor(new GrayColor(0.93f));
				cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell155.setBorder(PdfPCell.NO_BORDER);
				table16.addCell(cell155);
				PdfPCell cell156 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirClampsAvailabilityOb(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell156.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell156.setBackgroundColor(new GrayColor(0.93f));
				cell156.setBorder(PdfPCell.NO_BORDER);
				table16.addCell(cell156);

				PdfPCell cell157 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell157.setBackgroundColor(new GrayColor(0.93f));
				cell157.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell157.setBorder(PdfPCell.NO_BORDER);
				table16.addCell(cell157);
				PdfPCell cell158 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirClampsAvailabilityRem(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell158.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell158.setBackgroundColor(new GrayColor(0.93f));
				cell158.setBorder(PdfPCell.NO_BORDER);
				table16.addCell(cell158);

				document.add(table16);

//				Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				if (lpsAirTermination1.getAirClampsAvailabilityOb().equalsIgnoreCase("applicable")) {

					PdfPTable table17 = new PdfPTable(pointColumnWidths4);
					table17.setWidthPercentage(100); // Width 100%
					table17.setSpacingBefore(10f); // Space before table
					table17.setSpacingAfter(10f);
					table17.setWidthPercentage(100);

					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase("9(a)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell);

					PdfPCell cell105 = new PdfPCell();
					cell105.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1A));
					cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell105.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell105);

					PdfPCell cell37 = new PdfPCell(new Paragraph(airClamps.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell37);

					if (airClamps.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell106 = new PdfPCell();
					cell106.setPhrase(new Phrase("9(b)", font1A));
					cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell106.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell106);

					PdfPCell cell107 = new PdfPCell();
					cell107.setPhrase(
							new Phrase("Conductor clamp is firmly fixed/mounted/pasted over the flat surface", font1A));
					cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell107.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell107);

					PdfPCell cell108 = new PdfPCell(
							new Paragraph(airClamps.getConductorClampsFlatSurafaceOb(), font1A));
					cell108.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell108);

					if (airClamps.getConductorClampsFlatSurafaceRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airClamps.getConductorClampsFlatSurafaceRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell109 = new PdfPCell();
					cell109.setPhrase(new Phrase("9(c)", font1A));
					cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell109.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell109);

					PdfPCell cell110 = new PdfPCell();
					cell110.setPhrase(new Phrase(
							"Interconnection of conductor with metal sheet/metal part to conductor(tight/loose/corroded)",
							font1A));
					cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell110.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell110);

					PdfPCell cell111 = new PdfPCell(new Paragraph(airClamps.getInterConnectionOfClampsOb(), font1A));
					cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell111);

					if (airClamps.getInterConnectionOfClampsRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getInterConnectionOfClampsRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell112 = new PdfPCell();
					cell112.setPhrase(new Phrase("9(d)", font1A));
					cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell112.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell112);

					PdfPCell cell113 = new PdfPCell();
					cell113.setPhrase(new Phrase("Type of clamp", font1A));
					cell113.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell113.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell113);

					PdfPCell cell114 = new PdfPCell(new Paragraph(airClamps.getClampTypeOb(), font1A));
					cell114.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell114);

					if (airClamps.getClampTypRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getClampTypRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell115 = new PdfPCell();
					cell115.setPhrase(new Phrase("9(e)", font1A));
					cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell115.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell115);

					PdfPCell cell116 = new PdfPCell();
					cell116.setPhrase(
							new Phrase("Material of wall clamp (to fix the conductor with wall / Parapet)", font1A));
					cell116.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell116.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell116);

					PdfPCell cell117 = new PdfPCell(new Paragraph(airClamps.getMaterialOfWallClampsOb(), font1A));
					cell117.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell117);

					if (airClamps.getMaterialOfWallClampsRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getMaterialOfWallClampsRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell118 = new PdfPCell();
					cell118.setPhrase(new Phrase("9(f)", font1A));
					cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell118.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell118);

					PdfPCell cell119 = new PdfPCell();
					cell119.setPhrase(
							new Phrase("Material of folding clamp (for stright joints, cross joints etc)", font1A));
					cell119.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell119.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell119);

					PdfPCell cell120 = new PdfPCell(new Paragraph(airClamps.getMaterialOfFoldingClampsOb(), font1A));
					cell120.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell120);

					if (airClamps.getMaterialOfFoldingClampsRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getMaterialOfFoldingClampsRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell121 = new PdfPCell();
					cell121.setPhrase(new Phrase("9(g)", font1A));
					cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell121.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell121);

					PdfPCell cell122 = new PdfPCell();
					cell122.setPhrase(new Phrase("Total number of clamps", font1A));
					cell122.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell122.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell122);

					PdfPCell cell123 = new PdfPCell(new Paragraph(airClamps.getTotalClampsNoOb().toString(), font1A));
					cell123.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell123);

					if (airClamps.getTotalClampsNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getTotalClampsNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell124 = new PdfPCell();
					cell124.setPhrase(new Phrase("9(h)", font1A));
					cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell124.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell124);

					PdfPCell cell125 = new PdfPCell();
					cell125.setPhrase(new Phrase("Number of clamps inspected", font1A));
					cell125.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell125.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell125);

					PdfPCell cell126 = new PdfPCell(new Paragraph(airClamps.getInspectionNoOb().toString(), font1A));
					cell126.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell126);

					if (airClamps.getInspectionNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getInspectionNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell127 = new PdfPCell();
					cell127.setPhrase(new Phrase("9(i)", font1A));
					cell127.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell127.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell127);

					PdfPCell cell128 = new PdfPCell();
					cell128.setPhrase(new Phrase("Number of inspections passed", font1A));
					cell128.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell128.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell128);

					PdfPCell cell129 = new PdfPCell(
							new Paragraph(airClamps.getInspectionPassedOb().toString(), font1A));
					cell129.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell129);

					if (airClamps.getInspectionPassedRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getInspectionPassedRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell130 = new PdfPCell();
					cell130.setPhrase(new Phrase("9(j)", font1A));
					cell130.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell130.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell130);

					PdfPCell cell131 = new PdfPCell();
					cell131.setPhrase(new Phrase("Number of inspections failed", font1A));
					cell131.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell131.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell131);

					PdfPCell cell132 = new PdfPCell(
							new Paragraph(airClamps.getInspectionFailedReOb().toString(), font1A));
					cell132.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell132);

					if (airClamps.getInspectionFailedReRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airClamps.getInspectionFailedReRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}
					document.add(table17);
				}

//Expansion Pieces Accordian Start Here

//				PdfPTable expansionHead = new PdfPTable(pointColumnWidths5);
//				expansionHead.setWidthPercentage(100); // Width 100%
//				expansionHead.setSpacingBefore(10f); // Space before table
//				expansionHead.setSpacingAfter(5f); // Space after table
//				expansionHead.getDefaultCell().setBorder(0);
//
//				PdfPCell expansionsLabel = new PdfPCell(new Paragraph("Expansion Pieces",
//						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				expansionsLabel.setBackgroundColor(new GrayColor(0.82f));
//				expansionsLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				expansionsLabel.setBorder(PdfPCell.NO_BORDER);
//				expansionHead.addCell(expansionsLabel);
//				document.add(expansionHead);
				
				
				PdfPTable expansionHead = new PdfPTable(pointColumnWidths41);
				expansionHead.setWidthPercentage(100); // Width 100%
				expansionHead.setSpacingBefore(10f); // Space before table
				expansionHead.setSpacingAfter(10f); // Space after table

				cell11.setPhrase(new Phrase("Expansion Pieces", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				expansionHead.addCell(cell11);

				document.add(expansionHead);
				

				PdfPTable table18 = new PdfPTable(pointColumnWidths2);

				table18.setWidthPercentage(100); // Width 100%
//				table18.setSpacingBefore(5f); // Space before table
//				table18.setSpacingAfter(5f); // Space after table
				table18.getDefaultCell().setBorder(0);

				PdfPCell cell133 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell133.setBackgroundColor(new GrayColor(0.93f));
				cell133.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell133.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell133);
				PdfPCell cell134 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirExpansionAvailabilityOb(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell134.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell134.setBackgroundColor(new GrayColor(0.93f));
				cell134.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell134);

				PdfPCell cell135 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell135.setBackgroundColor(new GrayColor(0.93f));
				cell135.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell135.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell135);
				PdfPCell cell136 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirExpansionAvailabilityRem(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell136.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell136.setBackgroundColor(new GrayColor(0.93f));
				cell136.setBorder(PdfPCell.NO_BORDER);
				table18.addCell(cell136);

				document.add(table18);

//				Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				if (lpsAirTermination1.getAirExpansionAvailabilityOb().equalsIgnoreCase("applicable")) {

					PdfPTable table17 = new PdfPTable(pointColumnWidths4);
					table17.setWidthPercentage(100); // Width 100%
					table17.setSpacingBefore(10f); // Space before table
					table17.setSpacingAfter(10f);
					table17.setWidthPercentage(100);

					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase("10(a)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell);

					PdfPCell cell105 = new PdfPCell();
					cell105.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1A));
					cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell105.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell105);

					PdfPCell cell37 = new PdfPCell(new Paragraph(airExpansion.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell37);

					if (airExpansion.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell106 = new PdfPCell();
					cell106.setPhrase(new Phrase("10(b)", font1A));
					cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell106.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell106);

					PdfPCell cell107 = new PdfPCell();
					cell107.setPhrase(
							new Phrase("Connector/ ferrule / tube is properly crimped/fixed with expansion piece and a conductor", font1A));
					cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell107.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell107);

					PdfPCell cell108 = new PdfPCell(
							new Paragraph(airExpansion.getStrightConnectorPiecOb(), font1A));
					cell108.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell108);

					if (airExpansion.getStrightConnectorPiecRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airExpansion.getStrightConnectorPiecRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell109 = new PdfPCell();
					cell109.setPhrase(new Phrase("10(c)", font1A));
					cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell109.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell109);

					PdfPCell cell110 = new PdfPCell();
					cell110.setPhrase(new Phrase(
							"Material of connector/ferrule/tube",
							font1A));
					cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell110.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell110);

					PdfPCell cell111 = new PdfPCell(new Paragraph(airExpansion.getMaterialOfConnectorOb(), font1A));
					cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell111);

					if (airExpansion.getMaterialOfConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getMaterialOfConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell112 = new PdfPCell();
					cell112.setPhrase(new Phrase("10(d)", font1A));
					cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell112.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell112);

					PdfPCell cell113 = new PdfPCell();
					cell113.setPhrase(new Phrase("Material of expansion arrangements", font1A));
					cell113.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell113.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell113);

					PdfPCell cell114 = new PdfPCell(new Paragraph(airExpansion.getMaterialOfExpansionOb(), font1A));
					cell114.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell114);

					if (airExpansion.getMaterialOfExpansionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getMaterialOfExpansionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell115 = new PdfPCell();
					cell115.setPhrase(new Phrase("10(e)", font1A));
					cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell115.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell115);

					PdfPCell cell116 = new PdfPCell();
					cell116.setPhrase(
							new Phrase("Interval between two successice expansion piece in meters", font1A));
					cell116.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell116.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell116);

					PdfPCell cell117 = new PdfPCell(new Paragraph(airExpansion.getIntervalBwExpansionOb().toString(), font1A));
					cell117.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell117);

					if (airExpansion.getIntervalBwExpansionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getIntervalBwExpansionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell118 = new PdfPCell();
					cell118.setPhrase(new Phrase("10(f)", font1A));
					cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell118.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell118);

					PdfPCell cell119 = new PdfPCell();
					cell119.setPhrase(
							new Phrase("Total number of arrangements", font1A));
					cell119.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell119.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell119);

					PdfPCell cell120 = new PdfPCell(new Paragraph(airExpansion.getTotalNoExpansionOb().toString(), font1A));
					cell120.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell120);

					if (airExpansion.getTotalNoExpansionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getTotalNoExpansionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell121 = new PdfPCell();
					cell121.setPhrase(new Phrase("10(g)", font1A));
					cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell121.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell121);

					PdfPCell cell122 = new PdfPCell();
					cell122.setPhrase(new Phrase("Number of expansion arrangements inspected", font1A));
					cell122.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell122.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell122);

					PdfPCell cell123 = new PdfPCell(new Paragraph(airExpansion.getInspectionNoOb().toString(), font1A));
					cell123.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell123);

					if (airExpansion.getInspectionFailedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getInspectionFailedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell124 = new PdfPCell();
					cell124.setPhrase(new Phrase("10(h)", font1A));
					cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell124.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell124);

					PdfPCell cell125 = new PdfPCell();
					cell125.setPhrase(new Phrase("Number of inspections passed", font1A));
					cell125.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell125.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell125);

					PdfPCell cell126 = new PdfPCell(new Paragraph(airExpansion.getInspectionPassedNoOb().toString(), font1A));
					cell126.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell126);

					if (airExpansion.getInspectionPassedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getInspectionPassedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell127 = new PdfPCell();
					cell127.setPhrase(new Phrase("10(i)", font1A));
					cell127.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell127.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell127);

					PdfPCell cell128 = new PdfPCell();
					cell128.setPhrase(new Phrase("Number of inspections failed", font1A));
					cell128.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell128.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell128);

					PdfPCell cell129 = new PdfPCell(
							new Paragraph(airExpansion.getInspectionFailedNoOb().toString(), font1A));
					cell129.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell129);

					if (airExpansion.getInspectionFailedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airExpansion.getInspectionFailedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}
					document.add(table17);
				}
				
				
//Connectors Accordian Start Here

//				PdfPTable connectorsHead = new PdfPTable(pointColumnWidths5);
//				connectorsHead.setWidthPercentage(100); // Width 100%
//				connectorsHead.setSpacingBefore(10f); // Space before table
//				connectorsHead.setSpacingAfter(5f); // Space after table
//				connectorsHead.getDefaultCell().setBorder(0);
//
//				PdfPCell connectorsLabel = new PdfPCell(new Paragraph("Connectors",
//						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//				connectorsLabel.setBackgroundColor(new GrayColor(0.82f));
//				connectorsLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
//				connectorsLabel.setBorder(PdfPCell.NO_BORDER);
//				connectorsHead.addCell(connectorsLabel);
//				document.add(connectorsHead);
				
				
				PdfPTable connectorsHead = new PdfPTable(pointColumnWidths41);
				connectorsHead.setWidthPercentage(100); // Width 100%
				connectorsHead.setSpacingBefore(10f); // Space before table
				connectorsHead.setSpacingAfter(10f); // Space after table

				cell11.setPhrase(new Phrase("Connectors", font));
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell11.setBackgroundColor(new GrayColor(0.93f));
				cell11.setFixedHeight(20f);
				cell11.setColspan(4);
				connectorsHead.addCell(cell11);

				document.add(connectorsHead);

				PdfPTable table19 = new PdfPTable(pointColumnWidths2);

				table19.setWidthPercentage(100); // Width 100%
//				table19.setSpacingBefore(5f); // Space before table
//				table19.setSpacingAfter(5f); // Space after table
				table19.getDefaultCell().setBorder(0);

				PdfPCell cell130 = new PdfPCell(
						new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell130.setBackgroundColor(new GrayColor(0.93f));
				cell130.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell130.setBorder(PdfPCell.NO_BORDER);
				table19.addCell(cell130);
				PdfPCell cell131 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirConnectorsAvailabilityOb(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell130.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell130.setBackgroundColor(new GrayColor(0.93f));
				cell130.setBorder(PdfPCell.NO_BORDER);
				table19.addCell(cell130);

				PdfPCell cell132 = new PdfPCell(
						new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//				cell132.setBackgroundColor(new GrayColor(0.93f));
				cell132.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell132.setBorder(PdfPCell.NO_BORDER);
				table19.addCell(cell132);
				PdfPCell cell137 = new PdfPCell(new Paragraph(lpsAirTermination1.getAirConnectorsAvailabilityRem(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
				cell137.setHorizontalAlignment(Element.ALIGN_LEFT);
//				cell137.setBackgroundColor(new GrayColor(0.93f));
				cell137.setBorder(PdfPCell.NO_BORDER);
				table19.addCell(cell137);

				document.add(table19);

//				Font font1A = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				if (lpsAirTermination1.getAirConnectorsAvailabilityOb().equalsIgnoreCase("applicable")) {

					PdfPTable table17 = new PdfPTable(pointColumnWidths4);
					table17.setWidthPercentage(100); // Width 100%
					table17.setSpacingBefore(10f); // Space before table
					table17.setSpacingAfter(10f);
					table17.setWidthPercentage(100);

					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase("11(a)", font1A));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell);

					PdfPCell cell105 = new PdfPCell();
					cell105.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1A));
					cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell105.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell105);

					PdfPCell cell37 = new PdfPCell(new Paragraph(airConnectors.getPhysicalInspectionOb(), font1A));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell37);

					if (airConnectors.getPhysicalInspectionRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getPhysicalInspectionRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell106 = new PdfPCell();
					cell106.setPhrase(new Phrase("11(b)", font1A));
					cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell106.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell106);

					PdfPCell cell107 = new PdfPCell();
					cell107.setPhrase(
							new Phrase("check the connection of cross connector /T connector /straight connector or ferrule or tube/L connector (tight/loose/corroded)",font1A));
					cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell107.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell107);

					PdfPCell cell108 = new PdfPCell(
							new Paragraph(airConnectors.getCheckConnectionConnectorsOb(), font1A));
					cell108.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell108);

					if (airConnectors.getCheckConnectionConnectorsRe() != null) {
						PdfPCell cell38 = new PdfPCell(
								new Paragraph(airConnectors.getCheckConnectionConnectorsRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					
					PdfPCell cell109 = new PdfPCell();
					cell109.setPhrase(new Phrase("11(c)", font1A));
					cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell109.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell109);

					PdfPCell cell110 = new PdfPCell();
					cell110.setPhrase(new Phrase(
							"Material of connector Cross connector",
							font1A));
					cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell110.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell110);

					PdfPCell cell111 = new PdfPCell(new Paragraph(airConnectors.getMaterialOfConnectorOb(), font1A));
					cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell111);

					if (airConnectors.getMaterialOfConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getMaterialOfConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell112 = new PdfPCell();
					cell112.setPhrase(new Phrase("11(d)", font1A));
					cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell112.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell112);

					PdfPCell cell113 = new PdfPCell();
					cell113.setPhrase(new Phrase("Straight connector or ferrule or tube", font1A));
					cell113.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell113.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell113);

					PdfPCell cell114 = new PdfPCell(new Paragraph(airConnectors.getStrightConnectorOb(), font1A));
					cell114.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell114);

					if (airConnectors.getStrightConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getStrightConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell115 = new PdfPCell();
					cell115.setPhrase(new Phrase("11(e)", font1A));
					cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell115.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell115);

					PdfPCell cell116 = new PdfPCell();
					cell116.setPhrase(
							new Phrase("T connector", font1A));
					cell116.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell116.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell116);

					PdfPCell cell117 = new PdfPCell(new Paragraph(airConnectors.gettConnectorOb(), font1A));
					cell117.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell117);

					if (airConnectors.gettConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.gettConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell118 = new PdfPCell();
					cell118.setPhrase(new Phrase("11(f)", font1A));
					cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell118.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell118);

					PdfPCell cell119 = new PdfPCell();
					cell119.setPhrase(
							new Phrase("L Connector", font1A));
					cell119.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell119.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell119);

					PdfPCell cell120 = new PdfPCell(new Paragraph(airConnectors.getlConnectorOb(), font1A));
					cell120.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell120);

					if (airConnectors.getlConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getlConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell121 = new PdfPCell();
					cell121.setPhrase(new Phrase("11(g)", font1A));
					cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell121.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell121);

					PdfPCell cell122 = new PdfPCell();
					cell122.setPhrase(new Phrase("Total number of connectors", font1A));
					cell122.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell122.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell122);

					PdfPCell cell123 = new PdfPCell(new Paragraph(airConnectors.getTotalNoConnectorOb().toString(), font1A));
					cell123.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell123);

					if (airConnectors.getTotalNoConnectorRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getTotalNoConnectorRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell124 = new PdfPCell();
					cell124.setPhrase(new Phrase("11(h)", font1A));
					cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell124.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell124);

					PdfPCell cell125 = new PdfPCell();
					cell125.setPhrase(new Phrase("Number of connectors inspected", font1A));
					cell125.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell125.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell125);

					PdfPCell cell126 = new PdfPCell(new Paragraph(airConnectors.getInspectionNoOb().toString(), font1A));
					cell126.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell126);

					if (airConnectors.getInspectionNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getInspectionNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}

					PdfPCell cell127 = new PdfPCell();
					cell127.setPhrase(new Phrase("11(i)", font1A));
					cell127.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell127.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell127);

					PdfPCell cell128 = new PdfPCell();
					cell128.setPhrase(new Phrase("Number of inspections passed", font1A));
					cell128.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell128.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell128);

					PdfPCell cell129 = new PdfPCell(
							new Paragraph(airConnectors.getInspectionPassedNoOb().toString(), font1A));
					cell129.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell129);

					if (airConnectors.getInspectionPassedNoRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getInspectionPassedNoRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}
					
					PdfPCell cell140 = new PdfPCell();
					cell140.setPhrase(new Phrase("11(j)", font1A));
					cell140.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell140.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell140);

					PdfPCell cell141 = new PdfPCell();
					cell141.setPhrase(new Phrase("Number of inspections failed", font1A));
					cell141.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell141.setBackgroundColor(new GrayColor(0.93f));
					table17.addCell(cell141);

					PdfPCell cell142 = new PdfPCell(
							new Paragraph(airConnectors.getInspectionFailedOb().toString(), font1A));
					cell142.setHorizontalAlignment(Element.ALIGN_LEFT);
					table17.addCell(cell142);

					if (airConnectors.getInspectionFailedRe() != null) {
						PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors.getInspectionFailedRe(), font1A));
						cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell38);
					} else {
						PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
						cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell39);
					}
					
					document.add(table17);
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				

//				for (LpsVerticalAirTermination lpsVerticalAirTermination1 : lpsVerticalAirTermination) {
//
//					PdfPTable table11 = verticalAirTerminationIter(lpsVerticalAirTermination1);
//					document.add(table11);
//				}
//
//				for (AirMeshDescription airMeshDesc1 : airMeshDesc) {
//
//					PdfPTable table2 = meshConductorIter(airMeshDesc1);
//					document.add(table2);
//				}
//
//				for (AirHolderDescription airHolderdesc1 : airHolderdesc) {
//
//					PdfPTable table3 = airHolderIter(airHolderdesc1);
//					document.add(table3);
//
//				}
//
//				for (AirClamps airClamps1 : airClamps) {
//
//					PdfPTable table41 = clampsIter(airClamps1);
//					document.add(table41);
//				}
//
//				for (AirExpansion airExpansion1 : airExpansion) {
//
//					PdfPTable table5 = expansionPiecesIter(airExpansion1);
//					document.add(table5);
//				}
//
//				for (AirConnectors airConnectors1 : airConnectors) {
//
//					PdfPTable table6 = connectorIter(airConnectors1);
//					document.add(table6);
//				}

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new AirTerminationException("Invalid Inputs");
		}
	}

	private PdfPTable HoldersItr(AirHolderList airHolderList, float[] pointColumnWidths4, Font font1A) {
		PdfPTable table14 = new PdfPTable(pointColumnWidths4);
		table14.setWidthPercentage(100); // Width 100%
		table14.setSpacingBefore(10f); // Space before table
		table14.setSpacingAfter(10f);

		PdfPCell cell120 = new PdfPCell();
		cell120.setPhrase(new Phrase("8(d)", font1A));
		cell120.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell120.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell120);

		PdfPCell cell121 = new PdfPCell();
		cell121.setPhrase(new Phrase("Type of holder", font1A));
		cell121.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell121.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell121);

		PdfPCell cell122 = new PdfPCell(new Paragraph(airHolderList.getHolderTypeOb(), font1A));
		cell122.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell122);

		if (airHolderList.getHolderTypeRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getHolderTypeRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}

		PdfPCell cell123 = new PdfPCell();
		cell123.setPhrase(new Phrase("8(e)", font1A));
		cell123.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell123.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell123);

		PdfPCell cell124 = new PdfPCell();
		cell124.setPhrase(new Phrase("Material of holder", font1A));
		cell124.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell124.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell124);

		PdfPCell cell125 = new PdfPCell(new Paragraph(airHolderList.getMaterailOfHolderOb(), font1A));
		cell125.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell125);

		if (airHolderList.getMaterailOfHolderRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getMaterailOfHolderRem(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}

		PdfPCell cell126 = new PdfPCell();
		cell126.setPhrase(new Phrase("8(f)", font1A));
		cell126.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell126.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell126);

		PdfPCell cell127 = new PdfPCell();
		cell127.setPhrase(new Phrase("Total number of holders", font1A));
		cell127.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell127.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell127);

		PdfPCell cell128 = new PdfPCell(new Paragraph(airHolderList.getTotalHolderNoOb().toString(), font1A));
		cell128.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell128);

		if (airHolderList.getTotalHolderNoRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getTotalHolderNoRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}

		PdfPCell cell129 = new PdfPCell();
		cell129.setPhrase(new Phrase("8(g)", font1A));
		cell129.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell129.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell129);

		PdfPCell cell130 = new PdfPCell();
		cell130.setPhrase(new Phrase("Number of holders inspected", font1A));
		cell130.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell130.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell130);

		PdfPCell cell131 = new PdfPCell(new Paragraph(airHolderList.getHolderInspPassedNoOb().toString(), font1A));
		cell131.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell131);

		if (airHolderList.getHolderInspPassedNoRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getHolderInspPassedNoRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}

		PdfPCell cell132 = new PdfPCell();
		cell132.setPhrase(new Phrase("8(h)", font1A));
		cell132.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell132.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell132);

		PdfPCell cell133 = new PdfPCell();
		cell133.setPhrase(new Phrase("Number of inspections passed", font1A));
		cell133.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell133.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell133);

		PdfPCell cell134 = new PdfPCell(new Paragraph(airHolderList.getHolderInspPassedNoOb().toString(), font1A));
		cell134.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell134);

		if (airHolderList.getHolderInspPassedNoRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getHolderInspPassedNoRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}

		PdfPCell cell135 = new PdfPCell();
		cell135.setPhrase(new Phrase("8(i)", font1A));
		cell135.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell135.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell135);

		PdfPCell cell136 = new PdfPCell();
		cell136.setPhrase(new Phrase("Number of inspections failed", font1A));
		cell136.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell136.setBackgroundColor(new GrayColor(0.93f));
		table14.addCell(cell136);

		PdfPCell cell137 = new PdfPCell(new Paragraph(airHolderList.getHolderInspFailedNoOb().toString(), font1A));
		cell137.setHorizontalAlignment(Element.ALIGN_LEFT);
		table14.addCell(cell137);

		if (airHolderList.getHolderInspFailedNoRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(airHolderList.getHolderInspFailedNoRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table14.addCell(cell39);
		}
		return table14;
	}

	private PdfPTable VerticalAirTerminationItr(VerticalAirTerminationList VerticleAirTerminationList, Font font1A) {
		float[] pointColumnWidthsVertical = { 25F, 150F, 55F, 50F };

		PdfPTable table9 = new PdfPTable(pointColumnWidthsVertical);
		table9.setWidthPercentage(100); // Width 100%
		table9.setSpacingBefore(5f); // Space before table
		table9.setSpacingAfter(10f);

		PdfPCell cell40 = new PdfPCell();
		cell40.setPhrase(new Phrase("6(b)", font1A));
		cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell40.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell40);

		PdfPCell cell65 = new PdfPCell();
		cell65.setPhrase(new Phrase("Material of air terminal", font1A));
		cell65.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell65.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell65);

		PdfPCell cell41 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getMaterialOfTerminalOb(), font1A));
		cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell41);

		if (VerticleAirTerminationList.getMaterialOfTerminalRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getMaterialOfTerminalRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell42 = new PdfPCell();
		cell42.setPhrase(new Phrase("6(c)", font1A));
		cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell42.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell42);

		PdfPCell cell66 = new PdfPCell();
		cell66.setPhrase(new Phrase("Size/cross section area of air terminal", font1A));
		cell66.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell66.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell66);

		PdfPCell cell43 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getSizeOfTerminalOb(), font1A));
		cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell43);

		if (VerticleAirTerminationList.getSizeOfTerminalRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getSizeOfTerminalRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell44 = new PdfPCell();
		cell44.setPhrase(new Phrase("6(d)", font1A));
		cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell44.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell44);

		PdfPCell cell67 = new PdfPCell();
		cell67.setPhrase(new Phrase("Height of vertical air terminal in meters", font1A));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell67.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell67);

		PdfPCell cell45 = new PdfPCell(
				new Paragraph(VerticleAirTerminationList.getHeightOfTerminalOb().toString(), font1A));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell45);

		if (VerticleAirTerminationList.getHeightOfTerminalRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getHeightOfTerminalRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell46 = new PdfPCell();
		cell46.setPhrase(new Phrase("6(e)", font1A));
		cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell46.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell46);

		PdfPCell cell68 = new PdfPCell();
		cell68.setPhrase(new Phrase("Angle of protection based on height", font1A));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell68.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell68);

		PdfPCell cell47 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getAngleProtectionHeightOb(), font1A));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell47);

		if (VerticleAirTerminationList.getAngleProtectionHeightRe() != null) {
			PdfPCell cell38 = new PdfPCell(
					new Paragraph(VerticleAirTerminationList.getAngleProtectionHeightRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell48 = new PdfPCell();
		cell48.setPhrase(new Phrase("6(f)", font1A));
		cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell48.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell48);

		PdfPCell cell69 = new PdfPCell();
		cell69.setPhrase(new Phrase(
				"Any metal installations protrudes outside the building volume protected by air termination systems (check coverage area check based on angle of protection)",
				font1A));
		cell69.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell69.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell69);

		PdfPCell cell49 = new PdfPCell(
				new Paragraph(VerticleAirTerminationList.getInstallationTerminationsystemOb(), font1A));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell49);

		if (VerticleAirTerminationList.getInstallationTerminationsystemRem() != null) {
			PdfPCell cell38 = new PdfPCell(
					new Paragraph(VerticleAirTerminationList.getInstallationTerminationsystemRem(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell50 = new PdfPCell();
		cell50.setPhrase(new Phrase("6(g)", font1A));
		cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell50.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell50);

		PdfPCell cell70 = new PdfPCell();
		cell70.setPhrase(new Phrase("Type of support for vertical air terminal", font1A));
		cell70.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell70.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell70);

		PdfPCell cell51 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getSupportFlatSurfaceOb(), font1A));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell51);

		if (VerticleAirTerminationList.getSupportFlatSurfaceRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getSupportFlatSurfaceRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}

		PdfPCell cell52 = new PdfPCell();
		cell52.setPhrase(new Phrase("6(h)", font1A));
		cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell52.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell52);

		PdfPCell cell71 = new PdfPCell();
		cell71.setPhrase(
				new Phrase("Vertical air terminal is firmly fixed/mounted/pasted over the flat surface", font1A));
		cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell71.setBackgroundColor(new GrayColor(0.93f));
		table9.addCell(cell71);

		PdfPCell cell53 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getHeightFlatSurfaceOb(), font1A));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table9.addCell(cell53);

		if (VerticleAirTerminationList.getHeightFlatSurfaceRe() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(VerticleAirTerminationList.getHeightFlatSurfaceRe(), font1A));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font1A));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table9.addCell(cell39);
		}
		return table9;
	}

	private void tableData(PdfPTable table3, AirBasicDescription airBasicDesciption, Document document)
			throws DocumentException, IOException {

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("1(a)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Name of consultant/PMC/Contractor who designed lightning protection system", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell3 = new PdfPCell(new Paragraph(airBasicDesciption.getConsultantNameObserv(), font2));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell3);

		if (airBasicDesciption.getConsultantNameRemarks() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getConsultantNameRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(b)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Architect/designer Name", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell4 = new PdfPCell(new Paragraph(airBasicDesciption.getArchitectNameObserv(), font2));
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell4);

		if (airBasicDesciption.getArchitectNameRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getArchitectNameRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(c)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Date of Design", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell5 = new PdfPCell(new Paragraph(airBasicDesciption.getDesignDateObserv().toString(), font2));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell5);

		if (airBasicDesciption.getDesignDateRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getDesignDateRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(d)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Approved by", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell6 = new PdfPCell(new Paragraph(airBasicDesciption.getApprovedByObserv(), font2));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell6);

		if (airBasicDesciption.getApprovedByRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getApprovedByRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(e)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Date of approval", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell7 = new PdfPCell(new Paragraph(airBasicDesciption.getDateOfApprovalOb(), font2));
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell7);

		if (airBasicDesciption.getDateOfApprovalRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getDateOfApprovalRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(f)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Drawing number", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(airBasicDesciption.getDrawingObserv(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell8);

		if (airBasicDesciption.getDrawingRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getDrawingRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1(g)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Revision number", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airBasicDesciption.getRevisionNoObserv(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell9);

		if (airBasicDesciption.getRevisionNoRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getRevisionNoRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("2", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Check for any deviation of design with standard", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(airBasicDesciption.getDeviationObserv(), font2));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell10);

		if (airBasicDesciption.getDeviationRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getDeviationRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("3", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Check for any deviation of installation with design", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell11 = new PdfPCell(new Paragraph(airBasicDesciption.getDeviationInstallationObserv(), font2));
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell11);

		if (airBasicDesciption.getDeviationInstallationRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getDeviationInstallationRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("4", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Name of company who done the installation", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(airBasicDesciption.getCompanyNameObserv(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell12);

		if (airBasicDesciption.getCompanyNameRemarks() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getCompanyNameRemarks(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("1", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Type of connection", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell13 = new PdfPCell(new Paragraph(airBasicDesciption.getConnectionMadeBraOb(), font2));
		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell13);

		if (airBasicDesciption.getConnectionMadeBraRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getConnectionMadeBraRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("2", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Electrical and electronic equipment is placed on walls outside structures", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell14 = new PdfPCell(new Paragraph(airBasicDesciption.getElectricalEquipPlacedOb(), font2));
		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell14);

		if (airBasicDesciption.getElectricalEquipPlacedRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getElectricalEquipPlacedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("3"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase(
				"Presence of direct contact between easily combustible part and air termination systems", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell15 = new PdfPCell(new Paragraph(airBasicDesciption.getCombustablePartOb(), font2));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell15);

		if (airBasicDesciption.getCombustablePartRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getCombustablePartRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("4"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase(
				"Presence of power/ control/ instrumentation/telecommunication cable or cable passage in path/near air terminals and/or air termination mesh conductor",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell16 = new PdfPCell(new Paragraph(airBasicDesciption.getTerminationMeshConductorOb(), font2));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell16);

		if (airBasicDesciption.getTerminationMeshConductorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getTerminationMeshConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("5"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase(
				"Equipotenial bonding of terrace equipments (i.e expeosed and extraneous conductive parts) are carried out",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell17 = new PdfPCell(new Paragraph(airBasicDesciption.getBondingEquipotentialOb(), font2));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell17);

		if (airBasicDesciption.getBondingEquipotentialRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airBasicDesciption.getBondingEquipotentialRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("Not Available", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}
	}

	private PdfPTable connectorIter(AirConnectors airConnectors1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table6 = new PdfPTable(pointColumnWidths41);
		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(10f); // Space before table
		table6.setSpacingAfter(10f); // Space after table
		table6.setWidthPercentage(100);
//
//		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		
//		PdfPCell cell11 = new PdfPCell();
//		cell11.setPhrase(new Phrase("11", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell11);
//
//		cell11.setPhrase(new Phrase("Connectors", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		cell11.setFixedHeight(20f);
//		cell11.setColspan(3);
//		table6.addCell(cell11);
//
//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "11.a", font2));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table6.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table6.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(airConnectors1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table6.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table6.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(new Paragraph(airConnectors1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table6.addCell(cell211);
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("11.b", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell9 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionOb(), font2));
//		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell9);
//		if (airConnectors1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.c", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase(
//				"check the connection of cross connector /T connector /straight connector/L connector  (tight/loose/corroded)",
//				font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell30 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsOb(), font2));
//		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell30);
//		if (airConnectors1.getCheckConnectionConnectorsRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.d", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of connector Cross connector", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell31 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorOb(), font2));
//		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell31);
//		if (airConnectors1.getMaterialOfConnectorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.e", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Straight connector", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell32 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorOb(), font2));
//		cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell32);
//		if (airConnectors1.getStrightConnectorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.f", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("T connector", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell33 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorOb(), font2));
//		cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell33);
//		if (airConnectors1.gettConnectorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.g", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("L connector", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell34 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorOb(), font2));
//		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell34);
//		if (airConnectors1.getlConnectorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.h", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of connectors", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell35 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorOb(), font2));
//		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell35);
//		if (airConnectors1.getTotalNoConnectorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.i", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of connectors inspected", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell36 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoOb(), font2));
//		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell36);
//		if (airConnectors1.getInspectionNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.j", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell37 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoOb(), font2));
//		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell37);
//		if (airConnectors1.getInspectionPassedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("11.k", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table6.addCell(cell);
//
//		PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedOb(), font2));
//		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table6.addCell(cell38);
//		if (airConnectors1.getInspectionFailedRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table6.addCell(cell1);
//		}
		return table6;
	}

	private PdfPTable expansionPiecesIter(AirExpansion airExpansion1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table5 = new PdfPTable(pointColumnWidths41);
		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(10f); // Space before table
		table5.setSpacingAfter(10f); // Space after table
//
//		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//
//		PdfPCell cell11 = new PdfPCell();
//		cell11.setPhrase(new Phrase("10", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell11);
//
//		cell11.setPhrase(new Phrase("Expansion Pieces", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		cell11.setFixedHeight(20f);
//		cell11.setColspan(3);
//		table5.addCell(cell11);
//
//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "10.a", font2));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table5.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table5.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(airExpansion1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table5.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table5.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(new Paragraph(airExpansion1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table5.addCell(cell211);
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("10.b", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell22 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionOb(), font2));
//		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell22);
//		if (airExpansion1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.c", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(
//				new Phrase("Straight connector  is properly crimped with expansion piece and a conductor", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell23 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecOb(), font2));
//		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell23);
//		if (airExpansion1.getStrightConnectorPiecRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.d", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of expansion piece", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell24 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionOb(), font2));
//		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell24);
//		if (airExpansion1.getMaterialOfExpansionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.e", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of expansion pieces", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell25 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionOb(), font2));
//		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell25);
//		if (airExpansion1.getTotalNoExpansionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.f", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of expansion pieces inspected", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell26 = new PdfPCell(new Paragraph(airExpansion1.getInspectionNoOb(), font2));
//		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell26);
//		if (airExpansion1.getInspectionNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.g", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell27 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoOb(), font2));
//		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell27);
//		if (airExpansion1.getInspectionPassedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("10.h", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table5.addCell(cell);
//
//		PdfPCell cell28 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoOb(), font2));
//		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table5.addCell(cell28);
//		if (airExpansion1.getInspectionFailedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.addCell(cell1);
//		}
		return table5;
	}

	private PdfPTable clampsIter(AirClamps airClamps1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table4 = new PdfPTable(pointColumnWidths41);
		table4.setWidthPercentage(100); // Width 100%
		table4.setSpacingBefore(10f); // Space before table
		table4.setSpacingAfter(10f); // Space after table
		table4.setWidthPercentage(100);

//		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
//		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//		
//		PdfPCell cell11 = new PdfPCell();
//		cell11.setPhrase(new Phrase("9", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell11);
//
//		cell11.setPhrase(new Phrase("Clamps", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setFixedHeight(20f);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		cell11.setColspan(3);
//		table4.addCell(cell11);
//
//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "9.a", font2));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table4.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table4.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(airClamps1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table4.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table4.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(new Paragraph(airClamps1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table4.addCell(cell211);
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("9.b", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell22 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionOb(), font2));
//		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell22);
//		if (airClamps1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.c", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Conductor clamp is firmly fixed/mounted/pasted over the flat surface", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell23 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceOb(), font2));
//		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell23);
//		if (airClamps1.getConductorClampsFlatSurafaceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.d", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Interconnection of conductor with clamp (tight/loose/corroded)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell24 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsOb(), font2));
//		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell24);
//		if (airClamps1.getInterConnectionOfClampsRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.e", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Type of clamp", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell25 = new PdfPCell(new Paragraph(airClamps1.getClampTypeOb(), font2));
//		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell25);
//		if (airClamps1.getClampTypRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getClampTypRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.f", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of clamp", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell26 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsOb(), font2));
//		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell26);
//		if (airClamps1.getMaterialOfClampsRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.g", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of clamps", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell27 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoOb(), font2));
//		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell27);
//		if (airClamps1.getTotalClampsNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.h", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of clamps inspected", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell28 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoOb(), font2));
//		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell28);
//		if (airClamps1.getInspectionNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.i", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell29 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedOb(), font2));
//		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell29);
//		if (airClamps1.getInspectionPassedRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("9.j", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table4.addCell(cell);
//
//		PdfPCell cell30 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReOb(), font2));
//		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table4.addCell(cell30);
//		if (airClamps1.getInspectionFailedReRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.addCell(cell1);
//		}
		return table4;
	}

	private PdfPTable airHolderIter(AirHolderDescription airHolderdesc1) throws DocumentException, IOException {

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table3 = new PdfPTable(pointColumnWidths41);
		table3.setWidthPercentage(100); // Width 100%
		table3.setSpacingBefore(10f); // Space before table
		table3.setSpacingAfter(10f); // Space after table
		table3.setWidthPercentage(100);

//		PdfPCell cell11 = new PdfPCell();
//		cell11.setPhrase(new Phrase("8", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell11);
//
//		cell11.setPhrase(new Phrase("Holders", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		cell11.setFixedHeight(20f);
//		cell11.setColspan(3);
//		table3.addCell(cell11);
//
//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "8.a", font2));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table3.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table3.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(airHolderdesc1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table3.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table3.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(new Paragraph(airHolderdesc1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table3.addCell(cell211);
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("8.b", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell8 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionOb(), font2));
//		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell8);
//		if (airHolderdesc1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.c", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Conductor holder is firmly fixed/mounted/pasted over the flat surface", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell9 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceOb(), font2));
//		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell9);
//		if (airHolderdesc1.getConductorHolderFlatSurfaceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.d", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Conductor is properly  holded  in the holder", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell10 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderOb(), font2));
//		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell10);
//		if (airHolderdesc1.getConductorHolderRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.e", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Type of holder", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell111 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeOb(), font2));
//		cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell111);
//		if (airHolderdesc1.getHolderTypeRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.f", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of holder", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell12 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderOb(), font2));
//		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell12);
//		if (airHolderdesc1.getMaterailOfHolderRem() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderRem(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.g", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of holders", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell13 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoOb(), font2));
//		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell13);
//		if (airHolderdesc1.getTotalHolderNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.h", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of holders inspected", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell14 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoOb(), font2));
//		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell14);
//		if (airHolderdesc1.getHolderInspNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.i", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell15 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoOb(), font2));
//		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell15);
//		if (airHolderdesc1.getHolderInspPassedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.j", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell16 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoOb(), font2));
//		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell16);
//		if (airHolderdesc1.getHolderInspFailedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.k", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of parapet holder", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell17 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderOb(), font2));
//		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell17);
//		if (airHolderdesc1.getMaterailOfParpetHolderRem() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderRem(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.l", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of holders", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell18 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoOb(), font2));
//		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell18);
//		if (airHolderdesc1.getTotalParpetHolderNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.m", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of holders inspected", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell19 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoOb(), font2));
//		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell19);
//		if (airHolderdesc1.getParpetInspectionNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.n", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell20 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoOb(), font2));
//		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell20);
//		if (airHolderdesc1.getParpetInspectionPassedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("8.o", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table3.addCell(cell);
//
//		PdfPCell cell21 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoOb(), font2));
//		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table3.addCell(cell21);
//		if (airHolderdesc1.getParpetInspectionFailedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.addCell(cell1);
//		}
		return table3;
	}

	private PdfPTable meshConductorIter(AirMeshDescription airMeshDesc1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths41);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(10f); // Space before table
		table2.setSpacingAfter(10f); // Space after table

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

//		PdfPCell cell11 = new PdfPCell();
//		cell11.setPhrase(new Phrase("7", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell11);
//
//		cell11.setPhrase(new Phrase("Mesh conductors", font));
//		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell11.setBackgroundColor(new GrayColor(0.93f));
//		cell11.setFixedHeight(20f);
//		cell11.setColspan(3);
//		table2.addCell(cell11);
//
//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "7.a", font2));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table2.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table2.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(airMeshDesc1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table2.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table2.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(new Paragraph(airMeshDesc1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table2.addCell(cell211);
//
//		PdfPCell cell200 = new PdfPCell();
//		cell200.setPhrase(new Phrase("7.b", font2));
//		cell200.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell200.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell200);
//
//		cell200.setPhrase(new Phrase("Physical inspection (damage/break/bend/corroded/route)", font2));
//		cell200.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell200.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell200);
//
//		PdfPCell cell8 = new PdfPCell(new Paragraph(airMeshDesc1.getPhysicalInspectionOb(), font2));
//		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell8);
//		if (airMeshDesc1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getPhysicalInspectionRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		PdfPCell cell201 = new PdfPCell();
//		cell201.setPhrase(new Phrase("7.c", font2));
//		cell201.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell201.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell201);
//
//		cell201.setPhrase(new Phrase("Material of conductor", font2));
//		cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell201.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell201);
//
//		PdfPCell cell88 = new PdfPCell(new Paragraph(airMeshDesc1.getMaterailOfConductorOb(), font2));
//		cell88.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell88);
//		if (airMeshDesc1.getMaterailOfConductorRem() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMaterailOfConductorRem(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("7.d", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		cell.setPhrase(new Phrase("Size/cross section area of conductor (mm)", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		PdfPCell cell81 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorOb(), font2));
//		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell81);
//		if (airMeshDesc1.getSizeOfConductorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("7.e", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		cell.setPhrase(new Phrase("Mesh Size (m)", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		PdfPCell cell9 = new PdfPCell(new Paragraph(airMeshDesc1.getMeshSizeOb(), font2));
//		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell9);
//		if (airMeshDesc1.getMeshSizeRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMeshSizeRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("7.f", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		cell.setPhrase(new Phrase("Maximum distance between mesh conductors (m)", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		PdfPCell cell10 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceOb(), font2));
//		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell10);
//		if (airMeshDesc1.getMaximumDistanceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("7.g", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		cell.setPhrase(new Phrase("Minimum distance between mesh conductors (m)", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		PdfPCell cell22 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceOb(), font2));
//		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell22);
//		if (airMeshDesc1.getMinimumDistanceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("7.h", font2));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//		cell.setPhrase(new Phrase(
//				"Height of mesh conductors above flat surface (For flat roof, air termination system must be installed above probable water level)",
//				font2));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table2.addCell(cell);
//
//		PdfPCell cell12 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceOb(), font2));
//		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table2.addCell(cell12);
//		if (airMeshDesc1.getHeightOfConductorFlatSurfaceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceRe(), font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.addCell(cell1);
//		}
		return table2;
	}

	private PdfPTable verticalAirTerminationIter(LpsVerticalAirTermination lpsVerticalAirTermination1)
			throws DocumentException, IOException {

//		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n"+"6.a", font1));
//		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell124.setRowspan(2);
//		cell124.setGrayFill(0.92f);
//		table1.addCell(cell124);
//
//		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
//		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell1210.setColspan(1);
//		cell1210.setGrayFill(0.92f);
//		table1.addCell(cell1210);
//
//		PdfPCell cell2 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getLocationName(), font));
//		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell2.setColspan(2);
//		table1.addCell(cell2);
//
//		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
//		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell121.setColspan(1);
//		cell121.setGrayFill(0.92f);
//		table1.addCell(cell121);
//
//		PdfPCell cell211 = new PdfPCell(
//				new Paragraph(lpsVerticalAirTermination1.getLocationNumber().toString(), font));
//		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//		cell211.setColspan(2);
//		table1.addCell(cell211);
//
//		PdfPCell cell = new PdfPCell();
//		cell.setPhrase(new Phrase("6.b", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Physical inspection (damage/break/bend/interception tip/position)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell8 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionOb(), font1));
//		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell8);
//		if (lpsVerticalAirTermination1.getPhysicalInspectionRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.c", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Material of air terminal", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell9 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalOb(),
//				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell9);
//		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.d", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Size/cross section area of air terminal (mm)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell10 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalOb(), font1));
//		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell10);
//		if (lpsVerticalAirTermination1.getSizeOfTerminalRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.e", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Height of vertical air terminal (m)", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell111 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalOb(), font1));
//		cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell111);
//		if (lpsVerticalAirTermination1.getHeightOfTerminalRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.f", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Angle of protection based on height", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell12 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightOb(), font1));
//		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell12);
//		if (lpsVerticalAirTermination1.getAngleProtectionHeightRe() != null) {
//			PdfPCell cell1 = new PdfPCell(
//					new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.g", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase(
//				"Any metal installations protrudes outside the volume protected by air termination systems (coverage area check)",
//				font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell13 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInstallationTerminationsystemOb(), font1));
//		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell13);
//		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInstallationTerminationsystemRem(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.h", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase(
//				"Support (clip/clamp/concrete) for vertical air terminal is firmly fixed/mounted/pasted over the flat surface",
//				font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell14 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceOb(), font1));
//		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell14);
//		if (lpsVerticalAirTermination1.getSupportFlatSurfaceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.i", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase(
//				"Height of air terminals above flat surface(for flat roof, air termination system must be installed above probable water level)",
//				font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell15 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceOb(), font1));
//		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell15);
//		if (lpsVerticalAirTermination1.getHeightFlatSurfaceRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.j", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase(
//				"Interconnection VAT to roof conductor & metal bodies to roof conductors (tight/loose/corroded)",
//				font1));
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell16 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorOB(), font1));
//		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell16);
//		if (lpsVerticalAirTermination1.getVatToRoofConductorRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.k", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Total number of air terminals", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell17 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberOb(), font1));
//		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell17);
//		if (lpsVerticalAirTermination1.getTotalNumberRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//
//		}
//
//		cell.setPhrase(new Phrase("6.l", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of air terminals inspected", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell18 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspNoOb(), font1));
//		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell18);
//		if (lpsVerticalAirTermination1.getInspNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspNoRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.m", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections passed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell19 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoOb(), font1));
//		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell19);
//		if (lpsVerticalAirTermination1.getInspPassedNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		cell.setPhrase(new Phrase("6.n", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		cell.setPhrase(new Phrase("Number of inspections failed", font1));
//		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		cell.setBackgroundColor(new GrayColor(0.93f));
//		table1.addCell(cell);
//
//		PdfPCell cell20 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoOb(), font1));
//		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
//		table1.addCell(cell20);
//		if (lpsVerticalAirTermination1.getInspFaileddNoRe() != null) {
//			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoRe(), font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		} else {
//			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
//			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table1.addCell(cell1);
//		}
//
//		return table1;
		return null;

	}
}