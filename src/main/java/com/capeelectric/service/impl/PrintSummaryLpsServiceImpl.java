package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SummaryLps;
import com.capeelectric.model.SummaryLpsBuildings;
import com.capeelectric.model.SummaryLpsInnerObservation;
import com.capeelectric.model.SummaryLpsObservation;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SummaryLpsRepository;
import com.capeelectric.service.PrintSummaryLpsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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
public class PrintSummaryLpsServiceImpl implements PrintSummaryLpsService {

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Autowired
	private SummaryLpsRepository summaryLpsRepository;

	@Override
	public List<SummaryLps> printLpsSummaryDetails(String userName, Integer basicLpsId) throws SummaryLpsException {

		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("LpsSummary.pdf"));

				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				BasicLps basicLps1 = basicLps.get(0);

				List<SummaryLps> lpsSum = summaryLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				SummaryLps lpsSummary = lpsSum.get(0);

				List<SummaryLpsBuildings> summaryLPsBuild = lpsSummary.getSummaryLpsBuildings();
				SummaryLpsBuildings summaryLpsBuilding = summaryLPsBuild.get(0);

				List<SummaryLpsObservation> SummaryLpsOb = summaryLpsBuilding.getSummaryLpsObservation();
				SummaryLpsObservation summaryLpsObse = SummaryLpsOb.get(0);

				List<SummaryLpsInnerObservation> summaryLpsInnerObs = summaryLpsObse.getSummaryLpsInnerObservation();
//				SummaryLpsInnerObservation summaryLpsInnerObser = summaryLpsInnerObs.get(0);

//				List<SummaryLpsDeclaration> summaryLPsDec = lpsSummary.getSummaryLpsDeclaration();
//				SummaryLpsDeclaration summaryLpsDeclar = summaryLPsDec.get(0);

				document.open();

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table

				PdfPCell label = new PdfPCell(new Paragraph("Summary", font12B));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
				label.setFixedHeight(20f);
				headertable.addCell(label);

				document.add(headertable);

				float[] pointColumnWidths = { 120F, 80F };
				PdfPTable table1 = new PdfPTable(pointColumnWidths);

				table1.setWidthPercentage(100); // Width 100%
				table1.setSpacingBefore(10f); // Space before table
				table1.setSpacingAfter(5f); // Space after table
				table1.getDefaultCell().setBorder(0);

				PdfPCell cell1 = new PdfPCell(new Paragraph("Building number:",
						new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
				cell1.setBackgroundColor(new BaseColor(203, 183, 162));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell1);
				PdfPCell cell2 = new PdfPCell(new Paragraph(summaryLpsBuilding.getBuildingNumber().toString(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setBackgroundColor(new BaseColor(203, 183, 162));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table1.addCell(cell2);
				document.add(table1);

				PdfPTable table2 = new PdfPTable(pointColumnWidths);

				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(5f); // Space before table
				table2.setSpacingAfter(5f); // Space after table
				table2.getDefaultCell().setBorder(0);
				PdfPCell cell3 = new PdfPCell(
						new Paragraph("Building name:", new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
				cell3.setBackgroundColor(new BaseColor(203, 183, 162));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell3);
				PdfPCell cell4 = new PdfPCell(new Paragraph(summaryLpsBuilding.getBuildingName(),
						new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setBackgroundColor(new BaseColor(203, 183, 162));
				cell4.setBorder(PdfPCell.NO_BORDER);
				table2.addCell(cell4);
				document.add(table2);

				PdfPTable SRtable = new PdfPTable(pointColumnWidths40);
				SRtable.setWidthPercentage(100); // Width 100%
				SRtable.setSpacingBefore(5f); // Space before table

				PdfPCell label2 = new PdfPCell(new Paragraph("Summary and Recommendations", font11B));
				label2.setHorizontalAlignment(Element.ALIGN_CENTER);
				label2.setGrayFill(0.92f);
				label2.setFixedHeight(20f);
				SRtable.addCell(label2);
				document.add(SRtable);

				PdfPTable AirTerminationlabel = new PdfPTable(pointColumnWidths40);
				AirTerminationlabel.setWidthPercentage(100); // Width 100%
				AirTerminationlabel.setSpacingBefore(10f); // Space before table

				PdfPCell label3 = new PdfPCell(new Paragraph("Air termination", font11B));
				label3.setHorizontalAlignment(Element.ALIGN_CENTER);
				label3.setGrayFill(0.92f);
				label3.setFixedHeight(20f);
				AirTerminationlabel.addCell(label3);
				document.add(AirTerminationlabel);

				PdfPTable AirBAsicDetails = new PdfPTable(pointColumnWidths40);
				AirBAsicDetails.setWidthPercentage(100); // Width 100%
				AirBAsicDetails.setSpacingBefore(10f); // Space before table

// AT_Basic Details Observation Heading Label
				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("AT_Basic Details Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("1. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						AirBAsicDetails.addCell(headerlabel1);
					}
				}
				document.add(AirBAsicDetails);

// Observation And Recommendation Heading Label				
				float[] pointColumnWidths4 = { 50F, 50F };

				PdfPTable table3 = new PdfPTable(pointColumnWidths4);
				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(10f); // Space before table
//				table3.setSpacingAfter(10f);
				table3.setWidthPercentage(100);

				PdfPCell cell6 = new PdfPCell(new Paragraph("Observations", font10B));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setFixedHeight(20f);
				cell6.setGrayFill(0.92f);
				table3.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph("Recommendation", font10B));
				cell7.setGrayFill(0.92f);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(cell7);

				document.add(table3);

				PdfPTable table4 = new PdfPTable(pointColumnWidths4);
				table4.setWidthPercentage(100); // Width 100%
//				table4.setSpacingBefore(5f); // Space before table
//				table4.setSpacingAfter(10f);

// AT_Basic Details Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

					if (summaryLpsObser1.getObservationComponentDetails().contains("airBasicDescription")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table4.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table4.addCell(cell37);

					}
				}
				document.add(table4);

// Verticle Termination Main Heading  Label
				PdfPTable AirVerticlelabel = new PdfPTable(pointColumnWidths40);
				AirVerticlelabel.setWidthPercentage(100); // Width 100%
				AirVerticlelabel.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Vertical Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("2. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						AirVerticlelabel.addCell(headerlabel1);
					}
				}
				document.add(AirVerticlelabel);
				
// Verticle Termination main Remarks Only one main filed display				
				PdfPTable table5 = new PdfPTable(pointColumnWidths4);
				table5.setWidthPercentage(100); // Width 100%
				table5.setSpacingBefore(10f); // Space before table
				table5.setSpacingAfter(5f);

				for (SummaryLpsObservation summaryLpsObser2 : SummaryLpsOb) {
					if (summaryLpsObser2.getHeading().equalsIgnoreCase("Vertical Observation")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table5.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table5.addCell(cell37);

					}
				}
				document.add(table5);

				PdfPTable AirVerticleListLabel = new PdfPTable(pointColumnWidths4);
				AirVerticleListLabel.setWidthPercentage(100); // Width 100%
				AirVerticleListLabel.setSpacingBefore(5f); // Space before table
				AirVerticleListLabel.setSpacingAfter(35F); // Space After table

				
// Verticle Termination List With Iteration 
				for (SummaryLpsObservation summaryLpsObservation : summaryLpsBuilding.getSummaryLpsObservation()) {

					for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
							.getSummaryLpsInnerObservation()) {

						if (summaryLpsInnerObservation.getHeading().contains("Vertical List")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setColspan(2);
							headerlabel1.setFixedHeight(20f);
							AirVerticleListLabel.addCell(headerlabel1);

						}

						if (summaryLpsInnerObservation.getObservationComponentDetails()
								.contains("verticalAirTerminationList")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							AirVerticleListLabel.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							AirVerticleListLabel.addCell(cell37);
						}
					}
				}
//								}
//							}
				document.add(AirVerticleListLabel);
				
// Verticle Termination main Remarks  
				
				PdfPTable table6 = new PdfPTable(pointColumnWidths4);
				table6.setWidthPercentage(100); // Width 100%
				table6.setSpacingBefore(10f); // Space before table
				table6.setSpacingAfter(5f);

				for (SummaryLpsObservation summaryLpsObser2 : SummaryLpsOb) {
					if (!summaryLpsObser2.getHeading().equals("Vertical Observation")
							&& summaryLpsObser2.getObservationComponentDetails().contains("lpsVerticalAirTermination")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table6.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table6.addCell(cell37);

					}
				}
				document.add(table6);
				
// Mesh Observation Main Heading  Label
				PdfPTable MeshObservationHeader = new PdfPTable(pointColumnWidths40);
				MeshObservationHeader.setWidthPercentage(100); // Width 100%
				MeshObservationHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Mesh Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("3. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						MeshObservationHeader.addCell(headerlabel1);
					}
				}
				document.add(MeshObservationHeader);			
				
				
				
				PdfPTable table7 = new PdfPTable(pointColumnWidths4);
				table7.setWidthPercentage(100); // Width 100%
				table7.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f);

// Mesh Conductor Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("airMeshDescription")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table7.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table7.addCell(cell37);
					}
				}
				document.add(table7);
				
// Holder Observation Main Heading  Label
				PdfPTable HolderObservationHeader = new PdfPTable(pointColumnWidths40);
				HolderObservationHeader.setWidthPercentage(100); // Width 100%
				HolderObservationHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("AT_Holder Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("4. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						HolderObservationHeader.addCell(headerlabel1);
					}
				}
				document.add(HolderObservationHeader);			
				
				
// Hoders main Remarks 			
				PdfPTable table8 = new PdfPTable(pointColumnWidths4);
				table8.setWidthPercentage(100); // Width 100%
				table8.setSpacingBefore(10f); // Space before table
				table8.setSpacingAfter(5f);

				for (SummaryLpsObservation summaryLpsObser2 : SummaryLpsOb) {
					if (summaryLpsObser2.getObservationComponentDetails().equalsIgnoreCase("airHolderDescription0")
							|| summaryLpsObser2.getObservationComponentDetails().equalsIgnoreCase("airHolderDescription1")
							|| summaryLpsObser2.getObservationComponentDetails().equalsIgnoreCase("airHolderDescription2")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table8.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table8.addCell(cell37);

					}
				}
				document.add(table8);

				PdfPTable HoldersObservListHeader = new PdfPTable(pointColumnWidths4);
				HoldersObservListHeader.setWidthPercentage(100); // Width 100%
				HoldersObservListHeader.setSpacingBefore(5f); // Space before table
				HoldersObservListHeader.setSpacingAfter(5F); // Space After table

				
// Hoders Observation List With Iteration 
				for (SummaryLpsObservation summaryLpsObservation : summaryLpsBuilding.getSummaryLpsObservation()) {

					for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
							.getSummaryLpsInnerObservation()) {

						if (summaryLpsInnerObservation.getHeading().contains("Holder List")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setColspan(2);
							headerlabel1.setFixedHeight(20f);
							HoldersObservListHeader.addCell(headerlabel1);

						}

						if (summaryLpsInnerObservation.getObservationComponentDetails()
								.contains("airHolderList")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							HoldersObservListHeader.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							HoldersObservListHeader.addCell(cell37);
						}
					}
				}
				document.add(HoldersObservListHeader);
				
//// Hoders Observation main Remarks  
				
				PdfPTable table9 = new PdfPTable(pointColumnWidths4);
				table9.setWidthPercentage(100); // Width 100%
				table9.setSpacingBefore(5f); // Space before table
				table9.setSpacingAfter(5f);

				for (SummaryLpsObservation summaryLpsObser2 : SummaryLpsOb) {
					if (summaryLpsObser2.getObservationComponentDetails().contains("airHolderDescription")
							&& !summaryLpsObser2.getObservationComponentDetails().equals("airHolderDescription0")
							&& !summaryLpsObser2.getObservationComponentDetails().equals("airHolderDescription1")
							&& !summaryLpsObser2.getObservationComponentDetails().equals("airHolderDescription2")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table9.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table9.addCell(cell37);

					}
				}
				document.add(table9);

// Clamps Observation Main Heading  Label
				PdfPTable ClampsObserHeader = new PdfPTable(pointColumnWidths40);
				ClampsObserHeader.setWidthPercentage(100); // Width 100%
				ClampsObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Clamps Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("4. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						ClampsObserHeader.addCell(headerlabel1);
					}
				}
				document.add(ClampsObserHeader);			
				
				PdfPTable table10 = new PdfPTable(pointColumnWidths4);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
//				table10.setSpacingAfter(10f);

// Clamps Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("airClamps")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table10.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table10.addCell(cell37);
					}
				}
				document.add(table10);
				
// Expansion Observation Main Heading  Label
				PdfPTable ExpansionObserHeader = new PdfPTable(pointColumnWidths40);
				ExpansionObserHeader.setWidthPercentage(100); // Width 100%
				ExpansionObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Expansion Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("4. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						ExpansionObserHeader.addCell(headerlabel1);
					}
				}
				document.add(ExpansionObserHeader);			
				
				PdfPTable table11 = new PdfPTable(pointColumnWidths4);
				table11.setWidthPercentage(100); // Width 100%
				table11.setSpacingBefore(10f); // Space before table
//				table11.setSpacingAfter(10f);

// Expansion Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("airExpansion")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table11.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table11.addCell(cell37);
					}
				}
				document.add(table11);	
				
// Connectors Observation Main Heading  Label
				PdfPTable ConnectorsObserHeader = new PdfPTable(pointColumnWidths40);
				ConnectorsObserHeader.setWidthPercentage(100); // Width 100%
				ConnectorsObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("AT_Connectors Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("5. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						ConnectorsObserHeader.addCell(headerlabel1);
					}
				}
				document.add(ConnectorsObserHeader);			
				
				PdfPTable table12 = new PdfPTable(pointColumnWidths4);
				table12.setWidthPercentage(100); // Width 100%
				table12.setSpacingBefore(10f); // Space before table
//				table12.setSpacingAfter(10f);

// Connectors Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("airConnectors")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table12.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table12.addCell(cell37);
					}
				}
				document.add(table12);					

// Down Conductor Start here				
				document.newPage();

				PdfPTable DownConductorlabel = new PdfPTable(pointColumnWidths40);
				DownConductorlabel.setWidthPercentage(100); // Width 100%
				DownConductorlabel.setSpacingBefore(10f); // Space before table

				PdfPCell label1 = new PdfPCell(new Paragraph("Down Conductors", font11B));
				label1.setHorizontalAlignment(Element.ALIGN_CENTER);
				label1.setGrayFill(0.92f);
				label1.setFixedHeight(20f);
				DownConductorlabel.addCell(label1);
				document.add(DownConductorlabel);

				PdfPTable DCBAsicDetails = new PdfPTable(pointColumnWidths40);
				DCBAsicDetails.setWidthPercentage(100); // Width 100%
				DCBAsicDetails.setSpacingBefore(10f); // Space before table

// DC_Basic Details Observation Heading Label
				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("DC_Basic Details Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("1. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						DCBAsicDetails.addCell(headerlabel1);
					}
				}
				document.add(DCBAsicDetails);
				
// Observation And Recommendation Heading Label				

				PdfPTable table13 = new PdfPTable(pointColumnWidths4);
				table13.setWidthPercentage(100); // Width 100%
				table13.setSpacingBefore(10f); // Space before table
//				table13.setSpacingAfter(10f);

				PdfPCell cell8 = new PdfPCell(new Paragraph("Observations", font10B));
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell8.setFixedHeight(20f);
				cell8.setGrayFill(0.92f);
				table13.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph("Recommendation", font10B));
				cell9.setGrayFill(0.92f);
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				table13.addCell(cell9);

				document.add(table13);
				
		
				PdfPTable table14 = new PdfPTable(pointColumnWidths4);
				table14.setWidthPercentage(100); // Width 100%
//				table14.setSpacingBefore(5f); // Space before table
//				table14.setSpacingAfter(10f);

// DC_Basic Details Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

					if (summaryLpsObser1.getObservationComponentDetails().contains("downConductorBasicDescription")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table14.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table14.addCell(cell37);
					}
				}
				document.add(table14);

// Downconductors Observation Main Heading  Label
				PdfPTable DownconductorsObserHeader = new PdfPTable(pointColumnWidths40);
				DownconductorsObserHeader.setWidthPercentage(100); // Width 100%
				DownconductorsObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Downconductors Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("2. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						DownconductorsObserHeader.addCell(headerlabel1);
					}
				}
				document.add(DownconductorsObserHeader);			
				
				PdfPTable table15 = new PdfPTable(pointColumnWidths4);
				table15.setWidthPercentage(100); // Width 100%
				table15.setSpacingBefore(10f); // Space before table
//				table15.setSpacingAfter(10f);

// Downconductors Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("downConductorDescription")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table15.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table15.addCell(cell37);
					}
				}
				document.add(table15);	
				
// Bridging Observation Main Heading  Label
				PdfPTable BridgingObserHeader = new PdfPTable(pointColumnWidths40);
				BridgingObserHeader.setWidthPercentage(100); // Width 100%
				BridgingObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Bridging Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("3. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						BridgingObserHeader.addCell(headerlabel1);
					}
				}
				document.add(BridgingObserHeader);			
				
				PdfPTable table16 = new PdfPTable(pointColumnWidths4);
				table16.setWidthPercentage(100); // Width 100%
				table16.setSpacingBefore(10f); // Space before table
//				table16.setSpacingAfter(10f);

// Bridging Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("bridgingDescription")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table16.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table16.addCell(cell37);
					}
				}
				document.add(table16);	
				
// Holder Observation Main Heading  Label
				PdfPTable HolderObserHeader = new PdfPTable(pointColumnWidths40);
				HolderObserHeader.setWidthPercentage(100); // Width 100%
				HolderObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("DC_Holder Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("4. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						HolderObserHeader.addCell(headerlabel1);
					}
				}
				document.add(HolderObserHeader);			
				
				PdfPTable table17 = new PdfPTable(pointColumnWidths4);
				table17.setWidthPercentage(100); // Width 100%
				table17.setSpacingBefore(10f); // Space before table
//				table17.setSpacingAfter(10f);

// Bridging Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("holder")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table17.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table17.addCell(cell37);
					}
				}
				document.add(table17);	
				
// Connectors Observation Main Heading  Label
				PdfPTable ConnectorObserHeader = new PdfPTable(pointColumnWidths40);
				ConnectorObserHeader.setWidthPercentage(100); // Width 100%
				ConnectorObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("Connectors Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("5. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						ConnectorObserHeader.addCell(headerlabel1);
					}
				}
				document.add(ConnectorObserHeader);			
				
				PdfPTable table18 = new PdfPTable(pointColumnWidths4);
				table18.setWidthPercentage(100); // Width 100%
				table18.setSpacingBefore(10f); // Space before table
//				table18.setSpacingAfter(10f);

// Connectors Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("connectors")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table18.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table18.addCell(cell37);
					}
				}
				document.add(table18);					
				
// TestingJoint Observation Main Heading  Label
				PdfPTable TestingJointObserHeader = new PdfPTable(pointColumnWidths40);
				TestingJointObserHeader.setWidthPercentage(100); // Width 100%
				TestingJointObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("TestingJoint Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("6. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						TestingJointObserHeader.addCell(headerlabel1);
					}
				}
				document.add(TestingJointObserHeader);			
				
				PdfPTable table19 = new PdfPTable(pointColumnWidths4);
				table19.setWidthPercentage(100); // Width 100%
				table19.setSpacingBefore(10f); // Space before table
//				table19.setSpacingAfter(10f);

// TestingJoint Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("testingJoint")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table19.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table19.addCell(cell37);
					}
				}
				document.add(table19);	
				
// LightningCounter Observation Main Heading  Label
				PdfPTable LightningCounterObserHeader = new PdfPTable(pointColumnWidths40);
				LightningCounterObserHeader.setWidthPercentage(100); // Width 100%
				LightningCounterObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("LightningCounter Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("7. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						LightningCounterObserHeader.addCell(headerlabel1);
					}
				}
				document.add(LightningCounterObserHeader);			
				
				PdfPTable table20 = new PdfPTable(pointColumnWidths4);
				table20.setWidthPercentage(100); // Width 100%
				table20.setSpacingBefore(10f); // Space before table
//				table20.setSpacingAfter(10f);

// LightningCounter Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("lightningCounter")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table20.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table20.addCell(cell37);
					}
				}
				document.add(table20);				
				
// DownConductorTesting Observation Main Heading  Label
				PdfPTable DownConducTestObserHeader = new PdfPTable(pointColumnWidths40);
				DownConducTestObserHeader.setWidthPercentage(100); // Width 100%
				DownConducTestObserHeader.setSpacingBefore(10f); // Space before table

				for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
					if (summaryLpsHeading.getHeading().equalsIgnoreCase("DownConductorTesting Observation")) {

						PdfPCell headerlabel1 = new PdfPCell(
								new Paragraph("8. " + summaryLpsHeading.getHeading(), font10B));
						headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
						headerlabel1.setGrayFill(0.92f);
						headerlabel1.setFixedHeight(20f);
						DownConducTestObserHeader.addCell(headerlabel1);
					}
				}
				document.add(DownConducTestObserHeader);			
				
				PdfPTable table21 = new PdfPTable(pointColumnWidths4);
				table21.setWidthPercentage(100); // Width 100%
				table21.setSpacingBefore(10f); // Space before table
//				table21.setSpacingAfter(10f);

// DownConductorTesting Observation And Recommendation List with Iteration
				for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
					if (summaryLpsObser1.getObservationComponentDetails().contains("downConductorTesting")) {

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table21.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table21.addCell(cell37);
					}
				}
				document.add(table21);				
		
				
				
				
				
				
				
				
				
				
				
				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new SummaryLpsException("Invalid Inputs");
		}
		return null;
	}

}
