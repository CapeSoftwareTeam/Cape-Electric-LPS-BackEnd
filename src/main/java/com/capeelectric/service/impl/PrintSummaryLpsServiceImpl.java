package com.capeelectric.service.impl;

import java.io.FileOutputStream;
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
//				SummaryLpsObservation summaryLpsObser = SummaryLpsOb.get(0);
//				
//				List<SummaryLpsInnerObservation> summaryLpsInnerObs = 	summaryLpsObser.getSummaryLpsInnerObservation();	
//				SummaryLpsInnerObservation summaryLpsInnerObser = summaryLpsInnerObs.get(0);
//				
//				
//				List<SummaryLpsDeclaration> summaryLPsDec = lpsSummary.getSummaryLpsDeclaration();
//				SummaryLpsDeclaration summaryLpsDeclar = summaryLPsDec.get(0);

				document.open();

				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table

				PdfPCell label = new PdfPCell(new Paragraph("Summary", font11B));
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
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
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
						new Paragraph("Building name:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
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
				SRtable.setSpacingBefore(10f); // Space before table

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
				label3.setHorizontalAlignment(Element.ALIGN_LEFT);
				label3.setGrayFill(0.92f);
				label3.setFixedHeight(20f);
				AirTerminationlabel.addCell(label3);
				document.add(AirTerminationlabel);

				float[] pointColumnWidths4 = { 35F, 15F, 65F, 65F };

				PdfPTable table3 = new PdfPTable(pointColumnWidths4);
				table3.setWidthPercentage(100); // Width 100%
				table3.setSpacingBefore(10f); // Space before table
				table3.setSpacingAfter(10f);
				table3.setWidthPercentage(100);

				PdfPCell cell5 = new PdfPCell(new Paragraph("SL.NO", font11B));
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setGrayFill(0.92f);
				cell5.setColspan(2);
				table3.addCell(cell5);

//				PdfPCell cell25 = new PdfPCell(new Paragraph("Description", font11B));
//				cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
//				cell25.setFixedHeight(20f);
//				cell25.setGrayFill(0.92f);
//				table3.addCell(cell25);

				PdfPCell cell6 = new PdfPCell(new Paragraph("Observations", font11B));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setFixedHeight(20f);
				cell6.setGrayFill(0.92f);
				table3.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph("Recommendation", font11B));
				cell7.setGrayFill(0.92f);
				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(cell7);

				document.add(table3);

				PdfPTable table4 = new PdfPTable(pointColumnWidths4);
				table4.setWidthPercentage(100); // Width 100%
//				table4.setSpacingBefore(f); // Space before table
				table4.setSpacingAfter(10f);
				table4.setWidthPercentage(100);

//				for (SummaryLpsObservation summaryLpsObser : SummaryLpsOb) {
//
//					if (summaryLpsObser.getHeading().equalsIgnoreCase("Basic Details Observation")) {
//
//						PdfPCell cell = new PdfPCell();
//						cell.setPhrase(new Phrase(summaryLpsObser.getHeading(), font10N));
//						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cell.setBackgroundColor(new GrayColor(0.93f));
//						table4.addCell(cell);
//
//						PdfPCell cell8 = new PdfPCell();
//						cell8.setPhrase(new Phrase(summaryLpsObser.getSerialNo().toString(), font10N));
//						cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cell8.setBackgroundColor(new GrayColor(0.93f));
//						table4.addCell(cell8);
//
//						PdfPCell cell105 = new PdfPCell();
//						cell105.setPhrase(new Phrase(summaryLpsObser.getObservation(), font10N));
//						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
//						cell105.setBackgroundColor(new GrayColor(0.93f));
//						table4.addCell(cell105);
//
//						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser.getRecommendation(), font10N));
//						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
//						table4.addCell(cell37);
//
//						document.add(table4);
//					}
//				}
				
				
				for (SummaryLpsObservation summaryLpsObser : SummaryLpsOb) {
					
					if (summaryLpsObser.getHeading().equalsIgnoreCase("Vertical Observation")) {

						PdfPCell cell = new PdfPCell();
						cell.setPhrase(new Phrase(summaryLpsObser.getHeading(), font10N));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBackgroundColor(new GrayColor(0.93f));
						table4.addCell(cell);

						PdfPCell cell8 = new PdfPCell();
						cell8.setPhrase(new Phrase(summaryLpsObser.getSerialNo().toString(), font10N));
						cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell8.setBackgroundColor(new GrayColor(0.93f));
						table4.addCell(cell8);

						PdfPCell cell105 = new PdfPCell();
						cell105.setPhrase(new Phrase(summaryLpsObser.getObservation(), font10N));
						cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell105.setBackgroundColor(new GrayColor(0.93f));
						table4.addCell(cell105);

						PdfPCell cell37 = new PdfPCell(new Paragraph(summaryLpsObser.getRecommendation(), font10N));
						cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
						table4.addCell(cell37);

						document.add(table4);
					}
				}
				
				
				
				
				

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
