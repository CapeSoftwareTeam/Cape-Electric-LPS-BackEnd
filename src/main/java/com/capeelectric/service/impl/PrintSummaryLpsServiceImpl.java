package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SummaryLps;
import com.capeelectric.model.SummaryLpsBuildings;
import com.capeelectric.model.SummaryLpsDeclaration;
import com.capeelectric.model.SummaryLpsInnerObservation;
import com.capeelectric.model.SummaryLpsObservation;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SummaryLpsRepository;
import com.capeelectric.service.PrintSummaryLpsService;
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
				SummaryLpsBuildings summaryLpsBuilding1 = summaryLPsBuild.get(0);

				List<SummaryLpsObservation> SummaryLpsOb = summaryLpsBuilding1.getSummaryLpsObservation();
				SummaryLpsObservation summaryLpsObse = SummaryLpsOb.get(0);

				List<SummaryLpsInnerObservation> summaryLpsInnerObs = summaryLpsObse.getSummaryLpsInnerObservation();
//				SummaryLpsInnerObservation summaryLpsInnerObser = summaryLpsInnerObs.get(0);

				document.open();

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
				Font font9 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);

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

				for (SummaryLpsBuildings summaryLPsBuilding : summaryLPsBuild) {

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
					PdfPCell cell2 = new PdfPCell(new Paragraph(summaryLPsBuilding.getBuildingNumber().toString(),
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
					PdfPCell cell3 = new PdfPCell(new Paragraph("Building name:",
							new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
					cell3.setBackgroundColor(new BaseColor(203, 183, 162));
					cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell3.setBorder(PdfPCell.NO_BORDER);
					table2.addCell(cell3);
					PdfPCell cell4 = new PdfPCell(new Paragraph(summaryLPsBuilding.getBuildingName(),
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

					PdfPCell label3 = new PdfPCell(new Paragraph("Air Termination", font11B));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
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
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

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
						if (!summaryLpsObser2.getHeading().equals("Vertical Observation") && summaryLpsObser2
								.getObservationComponentDetails().contains("lpsVerticalAirTermination")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table6.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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
								|| summaryLpsObser2.getObservationComponentDetails()
										.equalsIgnoreCase("airHolderDescription1")
								|| summaryLpsObser2.getObservationComponentDetails()
										.equalsIgnoreCase("airHolderDescription2")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table8.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
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
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

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

							if (summaryLpsInnerObservation.getObservationComponentDetails().contains("airHolderList")) {

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

// Hoders Observation main Remarks  

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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

						if (summaryLpsObser1.getObservationComponentDetails()
								.contains("downConductorBasicDescription")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table14.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
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

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table21.addCell(cell37);
						}
					}
					document.add(table21);

// Earthing Start here				
					document.newPage();

					PdfPTable Earthinglabel = new PdfPTable(pointColumnWidths40);
					Earthinglabel.setWidthPercentage(100); // Width 100%
					Earthinglabel.setSpacingBefore(10f); // Space before table

					PdfPCell label4 = new PdfPCell(new Paragraph("Earthing", font11B));
					label4.setHorizontalAlignment(Element.ALIGN_CENTER);
					label4.setGrayFill(0.92f);
					label4.setFixedHeight(20f);
					Earthinglabel.addCell(label4);
					document.add(Earthinglabel);

					PdfPTable ETBAsicDetails = new PdfPTable(pointColumnWidths40);
					ETBAsicDetails.setWidthPercentage(100); // Width 100%
					ETBAsicDetails.setSpacingBefore(10f); // Space before table

// ET_BAsic Details Observation Heading Label
					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("ET_Basic Details Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("1. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							ETBAsicDetails.addCell(headerlabel1);
						}
					}
					document.add(ETBAsicDetails);

// Earthing Observation And Recommendation Heading Label				

					PdfPTable table22 = new PdfPTable(pointColumnWidths4);
					table22.setWidthPercentage(100); // Width 100%
					table22.setSpacingBefore(10f); // Space before table
//				table22.setSpacingAfter(10f);

					PdfPCell cell10 = new PdfPCell(new Paragraph("Observations", font10B));
					cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell10.setFixedHeight(20f);
					cell10.setGrayFill(0.92f);
					table22.addCell(cell10);

					PdfPCell cell11 = new PdfPCell(new Paragraph("Recommendation", font10B));
					cell11.setGrayFill(0.92f);
					cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
					table22.addCell(cell11);

					document.add(table22);

					PdfPTable table23 = new PdfPTable(pointColumnWidths4);
					table23.setWidthPercentage(100); // Width 100%
//				table23.setSpacingBefore(5f); // Space before table
//				table23.setSpacingAfter(10f);

// ET_Basic Details Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

						if (summaryLpsObser1.getObservationComponentDetails().contains("earthingLpsDescription")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table23.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table23.addCell(cell37);
						}
					}
					document.add(table23);

					PdfPTable ET_TypeBHeadingDetails = new PdfPTable(pointColumnWidths40);
					ET_TypeBHeadingDetails.setWidthPercentage(100); // Width 100%
					ET_TypeBHeadingDetails.setSpacingBefore(10f); // Space before table



// EarthingDescription Observation Main Heading  Label
					PdfPTable EarthingDescObserHeader = new PdfPTable(pointColumnWidths40);
					EarthingDescObserHeader.setWidthPercentage(100); // Width 100%
					EarthingDescObserHeader.setSpacingBefore(10f); // Space before table

					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthingDescription Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("2. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							EarthingDescObserHeader.addCell(headerlabel1);
						}
					}
					document.add(EarthingDescObserHeader);

					PdfPTable table24 = new PdfPTable(pointColumnWidths4);
					table24.setWidthPercentage(100); // Width 100%
					table24.setSpacingBefore(10f); // Space before table
//				table24.setSpacingAfter(10f);

// EarthingDescription Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
						if (summaryLpsObser1.getObservationComponentDetails().equals("earthingDescription0")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthingDescription1")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthingDescription2")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthingDescription3")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthingDescription4")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table24.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table24.addCell(cell37);
						}
					}
					document.add(table24);

					PdfPTable EarthingDescListHeader = new PdfPTable(pointColumnWidths4);
					EarthingDescListHeader.setWidthPercentage(100); // Width 100%
					EarthingDescListHeader.setSpacingBefore(10f); // Space before table
					EarthingDescListHeader.setSpacingAfter(5F); // Space After table

// EarthingDescription Observation List With Iteration 
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

						for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
								.getSummaryLpsInnerObservation()) {

							if (summaryLpsInnerObservation.getHeading().contains("EarthingDescription List")) {

								PdfPCell headerlabel1 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
								headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
								headerlabel1.setGrayFill(0.92f);
								headerlabel1.setColspan(2);
								headerlabel1.setFixedHeight(20f);
								EarthingDescListHeader.addCell(headerlabel1);

							}

							if (summaryLpsInnerObservation.getObservationComponentDetails()
									.contains("earthingDescriptionList")) {

								PdfPCell cell105 = new PdfPCell();
								cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
								cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell105.setBackgroundColor(new GrayColor(0.93f));
								EarthingDescListHeader.addCell(cell105);

								PdfPCell cell37 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
								cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
								EarthingDescListHeader.addCell(cell37);
							}
						}
					}
					document.add(EarthingDescListHeader);

//// EarthingDescription Observation main Remarks  

					PdfPTable table25 = new PdfPTable(pointColumnWidths4);
					table25.setWidthPercentage(100); // Width 100%
					table25.setSpacingBefore(5f); // Space before table
					table25.setSpacingAfter(5f);

					for (SummaryLpsObservation summaryLpsObser2 : SummaryLpsOb) {
						if (summaryLpsObser2.getObservationComponentDetails().contains("earthingDescription")
								&& !summaryLpsObser2.getObservationComponentDetails().equals("earthingDescription0")
								&& !summaryLpsObser2.getObservationComponentDetails().equals("earthingDescription1")
								&& !summaryLpsObser2.getObservationComponentDetails().equals("earthingDescription2")
								&& !summaryLpsObser2.getObservationComponentDetails().equals("earthingDescription3")
								&& !summaryLpsObser2.getObservationComponentDetails().equals("earthingDescription4")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser2.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table25.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser2.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table25.addCell(cell37);

						}
					}
					document.add(table25);

// EarthingClamps Observation Observation Main Heading  Label
					PdfPTable EarthingClampsObserHeader = new PdfPTable(pointColumnWidths40);
					EarthingClampsObserHeader.setWidthPercentage(100); // Width 100%
					EarthingClampsObserHeader.setSpacingBefore(10f); // Space before table

					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthingClamps Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("3. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							EarthingClampsObserHeader.addCell(headerlabel1);
						}
					}
					document.add(EarthingClampsObserHeader);

					PdfPTable table26 = new PdfPTable(pointColumnWidths4);
					table26.setWidthPercentage(100); // Width 100%
					table26.setSpacingBefore(10f); // Space before table
//				table26.setSpacingAfter(10f);

// DownConductorTesting Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
						if (summaryLpsObser1.getObservationComponentDetails().contains("earthingClamps")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table26.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table26.addCell(cell37);
						}
					}
					document.add(table26);

// EarthingElectrodeChamber Observation Main Heading  Label
					PdfPTable EarthingElectrodeChamberHeader = new PdfPTable(pointColumnWidths40);
					EarthingElectrodeChamberHeader.setWidthPercentage(100); // Width 100%
					EarthingElectrodeChamberHeader.setSpacingBefore(10f); // Space before table

					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthingElectrodeChamber Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("4. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							EarthingElectrodeChamberHeader.addCell(headerlabel1);
						}
					}
					document.add(EarthingElectrodeChamberHeader);

					PdfPTable table27 = new PdfPTable(pointColumnWidths4);
					table27.setWidthPercentage(100); // Width 100%
					table27.setSpacingBefore(10f); // Space before table
//				table27.setSpacingAfter(10f);

// DownConductorTesting Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {
						if (summaryLpsObser1.getObservationComponentDetails().contains("earthingElectrodeChamber")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table27.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table27.addCell(cell37);
						}
					}
					document.add(table27);
					
					// ET_TypeBHeadingDetails Observation Heading Label
					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthingSystem Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("2. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							ET_TypeBHeadingDetails.addCell(headerlabel1);
						}
					}
					document.add(ET_TypeBHeadingDetails);

//	Earthing if Type B(ring) Display the 

					PdfPTable tableTypeB = new PdfPTable(pointColumnWidths4);
					tableTypeB.setWidthPercentage(100); // Width 100%
					tableTypeB.setSpacingBefore(10f); // Space before table
//				tableTypeB.setSpacingAfter(10f);

					for (SummaryLpsObservation summaryLpsObser3 : SummaryLpsOb) {

						if (summaryLpsObser3.getObservationComponentDetails().contains("earthingSystem")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser3.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							tableTypeB.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser3.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							tableTypeB.addCell(cell37);
						}

					}
					document.add(tableTypeB);

// EarthElectrodeTesting Observation Main Heading  Label
					PdfPTable EarthElectrodeTestingHeader = new PdfPTable(pointColumnWidths40);
					EarthElectrodeTestingHeader.setWidthPercentage(100); // Width 100%
					EarthElectrodeTestingHeader.setSpacingBefore(10f); // Space before table

					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthElectrodeTesting Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph("5. " + summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							EarthElectrodeTestingHeader.addCell(headerlabel1);
						}
					}
					document.add(EarthElectrodeTestingHeader);

					PdfPTable table28 = new PdfPTable(pointColumnWidths4);
					table28.setWidthPercentage(100); // Width 100%
					table28.setSpacingBefore(10f); // Space before table
//				table28.setSpacingAfter(10f);

// EarthElectrodeTesting Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser4 : SummaryLpsOb) {
						if (summaryLpsObser4.getObservationComponentDetails().contains("earthElectrodeTesting")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser4.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table28.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser4.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table28.addCell(cell37);
						}
					}
					document.add(table28);

// SPD Start here				
					document.newPage();

					PdfPTable SPDlabel = new PdfPTable(pointColumnWidths40);
					SPDlabel.setWidthPercentage(100); // Width 100%
					SPDlabel.setSpacingBefore(10f); // Space before table

					PdfPCell label5 = new PdfPCell(new Paragraph("SPD", font11B));
					label5.setHorizontalAlignment(Element.ALIGN_CENTER);
					label5.setGrayFill(0.92f);
					label5.setFixedHeight(20f);
					SPDlabel.addCell(label5);
					document.add(SPDlabel);

					PdfPTable SPDDetailsObserv = new PdfPTable(pointColumnWidths40);
					SPDDetailsObserv.setWidthPercentage(100); // Width 100%
					SPDDetailsObserv.setSpacingBefore(10f); // Space before table

// SPD Details Observation Heading Label
					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("SPD Details Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph(summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							SPDDetailsObserv.addCell(headerlabel1);
						}
					}
					document.add(SPDDetailsObserv);

// SPD Observation And Recommendation Heading Label				

					PdfPTable table29 = new PdfPTable(pointColumnWidths4);
					table29.setWidthPercentage(100); // Width 100%
					table29.setSpacingBefore(10f); // Space before table
//				table29.setSpacingAfter(10f);

					PdfPCell cell12 = new PdfPCell(new Paragraph("Observations", font10B));
					cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell12.setFixedHeight(20f);
					cell12.setGrayFill(0.92f);
					table29.addCell(cell12);

					PdfPCell cell13 = new PdfPCell(new Paragraph("Recommendation", font10B));
					cell13.setGrayFill(0.92f);
					cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
					table29.addCell(cell13);

					document.add(table29);

					PdfPTable table30 = new PdfPTable(pointColumnWidths4);
					table30.setWidthPercentage(100); // Width 100%
//				table30.setSpacingBefore(5f); // Space before table
//				table30.setSpacingAfter(10f);

// SPD Details Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

						if (summaryLpsObser1.getObservationComponentDetails().contains("spdReport")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table30.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table30.addCell(cell37);
						}
					}
					document.add(table30);

					PdfPTable SPDListHeader = new PdfPTable(pointColumnWidths4);
					SPDListHeader.setWidthPercentage(100); // Width 100%
					SPDListHeader.setSpacingBefore(10f); // Space before table
					SPDListHeader.setSpacingAfter(5F); // Space After table

// SPD List Observation List With Iteration 
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

						for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
								.getSummaryLpsInnerObservation()) {

							if (summaryLpsInnerObservation.getHeading().contains("SPD List")) {

								PdfPCell headerlabel1 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
								headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
								headerlabel1.setGrayFill(0.92f);
								headerlabel1.setColspan(2);
								headerlabel1.setFixedHeight(20f);
								SPDListHeader.addCell(headerlabel1);

							}

							if (summaryLpsInnerObservation.getObservationComponentDetails()
									.contains("spdDescription")) {

								PdfPCell cell105 = new PdfPCell();
								cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
								cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell105.setBackgroundColor(new GrayColor(0.93f));
								SPDListHeader.addCell(cell105);

								PdfPCell cell37 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
								cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
								SPDListHeader.addCell(cell37);
							}
						}
					}
					document.add(SPDListHeader);

// SeparationDistance  Start here				
					document.newPage();

					PdfPTable SeparationDistancelabel = new PdfPTable(pointColumnWidths40);
					SeparationDistancelabel.setWidthPercentage(100); // Width 100%
					SeparationDistancelabel.setSpacingBefore(10f); // Space before table

					PdfPCell label6 = new PdfPCell(new Paragraph("SeparationDistance Observation", font11B));
					label6.setHorizontalAlignment(Element.ALIGN_CENTER);
					label6.setGrayFill(0.92f);
					label6.setFixedHeight(20f);
					SeparationDistancelabel.addCell(label6);
					document.add(SeparationDistancelabel);

					PdfPTable SeparationDistObserv = new PdfPTable(pointColumnWidths40);
					SeparationDistObserv.setWidthPercentage(100); // Width 100%
					SeparationDistObserv.setSpacingBefore(10f); // Space before table

// SeparationDistance Observation Heading Label
					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("SeparationDistance Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph(summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							SeparationDistObserv.addCell(headerlabel1);
						}
					}
					document.add(SeparationDistObserv);

// SeparationDistance Observation And Recommendation Heading Label				

					PdfPTable table31 = new PdfPTable(pointColumnWidths4);
					table31.setWidthPercentage(100); // Width 100%
					table31.setSpacingBefore(10f); // Space before table
//				table31.setSpacingAfter(10f);

					PdfPCell cell14 = new PdfPCell(new Paragraph("Observations", font10B));
					cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell14.setFixedHeight(20f);
					cell14.setGrayFill(0.92f);
					table31.addCell(cell14);

					PdfPCell cell15 = new PdfPCell(new Paragraph("Recommendation", font10B));
					cell15.setGrayFill(0.92f);
					cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
					table31.addCell(cell15);

					document.add(table31);

					PdfPTable table32 = new PdfPTable(pointColumnWidths4);
					table32.setWidthPercentage(100); // Width 100%
//				table32.setSpacingBefore(5f); // Space before table
//				table32.setSpacingAfter(10f);

// SeparationDistance Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

						if (summaryLpsObser1.getObservationComponentDetails()
								.contains("seperationDistanceDescription")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table32.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table32.addCell(cell37);
						}
					}
					document.add(table32);

					PdfPTable SeparationDistObserHeader = new PdfPTable(pointColumnWidths4);
					SeparationDistObserHeader.setWidthPercentage(100); // Width 100%
					SeparationDistObserHeader.setSpacingBefore(10f); // Space before table
					SeparationDistObserHeader.setSpacingAfter(5F); // Space After table

// SeparationDistance List Observation List With Iteration 
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

						for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
								.getSummaryLpsInnerObservation()) {

							if (summaryLpsInnerObservation.getHeading().contains("SeparateDistance Observation")) {

								PdfPCell headerlabel1 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
								headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
								headerlabel1.setGrayFill(0.92f);
								headerlabel1.setColspan(2);
								headerlabel1.setFixedHeight(20f);
								SeparationDistObserHeader.addCell(headerlabel1);

							}

							if (summaryLpsInnerObservation.getObservationComponentDetails()
									.contains("separateDistanceDesc")) {

								PdfPCell cell105 = new PdfPCell();
								cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
								cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell105.setBackgroundColor(new GrayColor(0.93f));
								SeparationDistObserHeader.addCell(cell105);

								PdfPCell cell37 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
								cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
								SeparationDistObserHeader.addCell(cell37);
							}
						}
					}
					document.add(SeparationDistObserHeader);

					PdfPTable SeparationDistDownHeader = new PdfPTable(pointColumnWidths4);
					SeparationDistDownHeader.setWidthPercentage(100); // Width 100%
					SeparationDistDownHeader.setSpacingBefore(10f); // Space before table
					SeparationDistDownHeader.setSpacingAfter(5F); // Space After table

// SeparationDistance List Observation List With Iteration 
					for (SummaryLpsObservation summaryLpsObservation : summaryLPsBuilding.getSummaryLpsObservation()) {

						for (SummaryLpsInnerObservation summaryLpsInnerObservation : summaryLpsObservation
								.getSummaryLpsInnerObservation()) {

							if (summaryLpsInnerObservation.getHeading()
									.contains("SeparationDistanceDown Observation")) {

								PdfPCell headerlabel1 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getHeading(), font10B));
								headerlabel1.setHorizontalAlignment(Element.ALIGN_CENTER);
								headerlabel1.setGrayFill(0.92f);
								headerlabel1.setColspan(2);
								headerlabel1.setFixedHeight(20f);
								SeparationDistDownHeader.addCell(headerlabel1);

							}

							if (summaryLpsInnerObservation.getObservationComponentDetails()
									.contains("separateDistanceDownConductors")) {

								PdfPCell cell105 = new PdfPCell();
								cell105.setPhrase(new Phrase(summaryLpsInnerObservation.getObservation(), font10N));
								cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell105.setBackgroundColor(new GrayColor(0.93f));
								SeparationDistDownHeader.addCell(cell105);

								PdfPCell cell37 = new PdfPCell(
										new Paragraph(summaryLpsInnerObservation.getRecommendation(), font10N));
								cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
								SeparationDistDownHeader.addCell(cell37);
							}
						}
					}
					document.add(SeparationDistDownHeader);

// Equipotential bonding  Start here				
					document.newPage();

					PdfPTable Equipotentialbondinglabel = new PdfPTable(pointColumnWidths40);
					Equipotentialbondinglabel.setWidthPercentage(100); // Width 100%
					Equipotentialbondinglabel.setSpacingBefore(10f); // Space before table

					PdfPCell label7 = new PdfPCell(new Paragraph("Equipotential bonding", font11B));
					label7.setHorizontalAlignment(Element.ALIGN_CENTER);
					label7.setGrayFill(0.92f);
					label7.setFixedHeight(20f);
					Equipotentialbondinglabel.addCell(label7);
					document.add(Equipotentialbondinglabel);

					PdfPTable EquipotentialbondingObser = new PdfPTable(pointColumnWidths40);
					EquipotentialbondingObser.setWidthPercentage(100); // Width 100%
					EquipotentialbondingObser.setSpacingBefore(10f); // Space before table

// Equipotential bonding Observation Heading Label
					for (SummaryLpsObservation summaryLpsHeading : SummaryLpsOb) {
						if (summaryLpsHeading.getHeading().equalsIgnoreCase("EarthStud Observation")) {

							PdfPCell headerlabel1 = new PdfPCell(
									new Paragraph(summaryLpsHeading.getHeading(), font10B));
							headerlabel1.setHorizontalAlignment(Element.ALIGN_LEFT);
							headerlabel1.setGrayFill(0.92f);
							headerlabel1.setFixedHeight(20f);
							EquipotentialbondingObser.addCell(headerlabel1);
						}
					}
					document.add(EquipotentialbondingObser);

// Equipotential bonding Observation And Recommendation Heading Label				

					PdfPTable table33 = new PdfPTable(pointColumnWidths4);
					table33.setWidthPercentage(100); // Width 100%
					table33.setSpacingBefore(10f); // Space before table
//				table33.setSpacingAfter(10f);

					PdfPCell cell16 = new PdfPCell(new Paragraph("Observations", font10B));
					cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell16.setFixedHeight(20f);
					cell16.setGrayFill(0.92f);
					table33.addCell(cell16);

					PdfPCell cell17 = new PdfPCell(new Paragraph("Recommendation", font10B));
					cell17.setGrayFill(0.92f);
					cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
					table33.addCell(cell17);

					document.add(table33);

					PdfPTable table34 = new PdfPTable(pointColumnWidths4);
					table34.setWidthPercentage(100); // Width 100%
//				table34.setSpacingBefore(5f); // Space before table
//				table34.setSpacingAfter(10f);

// Equipotential bonding Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

						if (summaryLpsObser1.getObservationComponentDetails().contains("earthStudDescription")
								&& !summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription8")
								&& !summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription9")
								&& !summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription10")
								&& !summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription11")
								&& !summaryLpsObser1.getObservationComponentDetails()
										.equals("earthStudDescription12")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table34.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table34.addCell(cell37);
						}
					}
					document.add(table34);

					PdfPTable table35 = new PdfPTable(pointColumnWidths4);
					table35.setWidthPercentage(100); // Width 100%
					table35.setSpacingBefore(10f); // Space before table
//				table35.setSpacingAfter(10f);

// Equipotential bonding Observation And Recommendation List with Iteration
					for (SummaryLpsObservation summaryLpsObser1 : SummaryLpsOb) {

						if (summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription8")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription9")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription10")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription11")
								|| summaryLpsObser1.getObservationComponentDetails().equals("earthStudDescription12")) {

							PdfPCell cell105 = new PdfPCell();
							cell105.setPhrase(new Phrase(summaryLpsObser1.getObservation(), font10N));
							cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell105.setBackgroundColor(new GrayColor(0.93f));
							table35.addCell(cell105);

							PdfPCell cell37 = new PdfPCell(
									new Paragraph(summaryLpsObser1.getRecommendation(), font10N));
							cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
							table35.addCell(cell37);
						}
					}

					document.add(table35);

					document.newPage();
				}

				document.newPage();

				PdfPTable RecommendationTable = new PdfPTable(pointColumnWidths40);
				RecommendationTable.setWidthPercentage(100); // Width 100%
				RecommendationTable.setSpacingBefore(10f); // Space before table

				PdfPCell label8 = new PdfPCell(new Paragraph("Recommendation", font11B));
				label8.setHorizontalAlignment(Element.ALIGN_LEFT);
				label8.setGrayFill(0.92f);
				label8.setFixedHeight(20f);
				RecommendationTable.addCell(label8);
				document.add(RecommendationTable);

				float[] pointColumnWidths1 = { 150F, 40F };

				PdfPTable table36 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table36.setWidthPercentage(100); // Width 100%
				table36.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table36.getDefaultCell().setBorder(0);

				PdfPTable table37 = new PdfPTable(pointColumnWidths1); // 3 columns.
				table37.setWidthPercentage(100); // Width 100%
				table37.setSpacingBefore(10f); // Space before table
//				table37.setSpacingAfter(10f); // Space after table
				table37.getDefaultCell().setBorder(0);

				for (SummaryLps summary : lpsSum) {

					PdfPCell cell132 = new PdfPCell(new Paragraph(
							"Subject to necessary remedial action being taken, I/We recommended that the LPS is further inspected and tested by : ",
							font10N));
					cell132.setBorder(PdfPCell.NO_BORDER);
					cell132.setGrayFill(0.92f);
					table36.addCell(cell132);
					PdfPCell cell29 = new PdfPCell(new Paragraph(summary.getSummaryDate(), font10B));
					cell29.setGrayFill(0.92f);
					cell29.setBorder(PdfPCell.NO_BORDER);
					table36.addCell(cell29);

					PdfPCell cell134 = new PdfPCell(new Paragraph(
							"We also recommend that, ligthning protection system to be periodical inspected for every Years",
							font10N));
					cell134.setBorder(PdfPCell.NO_BORDER);
					cell134.setGrayFill(0.92f);
					table37.addCell(cell134);
					PdfPCell cell135 = new PdfPCell(new Paragraph(summary.getInspectedYear().toString(), font10B));
					cell135.setGrayFill(0.92f);
					cell135.setBorder(PdfPCell.NO_BORDER);
					table37.addCell(cell135);
				}
				document.add(table36);
				document.add(table37);

				PdfPTable DeclarationTable = new PdfPTable(pointColumnWidths40);
				DeclarationTable.setWidthPercentage(100); // Width 100%
				DeclarationTable.setSpacingBefore(10f); // Space before table
				DeclarationTable.setSpacingAfter(5f); // Space before table

				PdfPCell label9 = new PdfPCell(new Paragraph("Declaration", font11B));
				label9.setHorizontalAlignment(Element.ALIGN_LEFT);
				label9.setGrayFill(0.92f);
				label9.setFixedHeight(20f);
				DeclarationTable.addCell(label9);
				document.add(DeclarationTable);

				Paragraph paragraph1 = new Paragraph(
						"I/we being the person responsible for the inspection of the lightning protection system (as indicated by my/our signatures below), particulars of which are described in this report, having exercised reasonable skill and care when carrying out the inspection, hereby declare that information in this report including the observations provides an accurate assessment of condition of lightning protection system taking into account :",
						font10N);
				document.add(paragraph1);

				PdfPTable table38 = new PdfPTable(2);
				table38.setWidthPercentage(100); // Width 100%
				table38.setSpacingBefore(10f); // Space before table
				table38.getDefaultCell().setBorder(0);

				addRow(table38, "Inspected and Tested  By ", "Authorized By");

				List<SummaryLpsDeclaration> summaryLPsDec = lpsSummary.getSummaryLpsDeclaration();
				SummaryLpsDeclaration declaration = summaryLPsDec.get(0);
				SummaryLpsDeclaration declaration11 = summaryLPsDec.get(1);

				float[] pointColumnWidthsSec5 = { 40F, 90F, 40F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidthsSec5); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				addRow(table, "Name", declaration.getName(), "Name", declaration11.getName());
				addRow(table, "Company", declaration.getCompany(), "Company", declaration11.getCompany());
				addRow(table, "Signature	", declaration.getSignature(), "Signature	",
						declaration11.getSignature());
				addRow(table, "Position", declaration.getPosition(), "Position", declaration11.getPosition());
				addRow(table, "Address", declaration.getAddress(), "Address", declaration11.getAddress());
				addRow(table, "Date", declaration.getDate(), "Date", declaration11.getDate());
				document.add(table38);
				document.add(table);

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

	private void addRow(PdfPTable table9, String string, String string2) throws DocumentException, IOException {
		Font font8 = new Font(BaseFont.createFont(), 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, font8));
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string2, font8));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell.setGrayFill(0.92f);
		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell1.setGrayFill(0.92f);
		table9.addCell(nameCell);
		table9.addCell(nameCell1);
	}

	private void addRow(PdfPTable table6, String string, String string2, String string3, String string4)
			throws DocumentException, IOException {
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, font));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3, font));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string4, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell.setGrayFill(0.92f);
		valueCell2.setGrayFill(0.92f);
		table6.addCell(nameCell);
		table6.addCell(valueCell1);
		table6.addCell(valueCell2);
		table6.addCell(valueCell3);

	}

}
