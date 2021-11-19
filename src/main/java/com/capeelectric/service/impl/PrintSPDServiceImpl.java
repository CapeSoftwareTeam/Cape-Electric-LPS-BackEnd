package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SpdDescription;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.PrintSPDService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@Service
public class PrintSPDServiceImpl implements PrintSPDService {
	
	@Autowired
	private SPDRepository spdRepository;

	@Override
	public void printSPD(String userName, Integer lpsId) throws SPDException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			
			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SPD.pdf"));
				
				List<SPD> spdMain = spdRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				SPD spdMain1 = spdMain.get(0);
				
				List<SpdDescription> spdDesc1 = spdMain1.getSpdDescription();
				
				SpdDescription spdDesc2 = spdDesc1.get(0);
				document.open();
				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				float[] pointColumnWidths30 = { 30F, 150F, 50F, 50F };
				
				PdfPTable table = new PdfPTable(pointColumnWidths30);
				table.setWidthPercentage(100); // Width 100%
				//table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell30.setGrayFill(0.92f);
				table.addCell(cell30);

				PdfPCell cell311 = new PdfPCell(new Paragraph("Description", font11));
				cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell311.setFixedHeight(25f);
				cell311.setGrayFill(0.92f);
				table.addCell(cell311);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font11));
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setFixedHeight(25f);
				cell32.setGrayFill(0.92f);
				table.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font11));
				cell33.setGrayFill(0.92f);
				cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell33);
				
				
				
				
				Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL , BaseColor.BLACK);
				
				PdfPCell cell = new PdfPCell(new Paragraph("1", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Mains incoming panel", font));
				cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell2.setGrayFill(0.92f);
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Paragraph(spdMain1.getMainsIncomingOb(), font));
				cell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph(spdMain1.getMainsIncomingRem(), font));
				cell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell4);
				
				PdfPCell cell34 = new PdfPCell(new Paragraph("2", font));
				cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell34.setFixedHeight(20f);
				cell34.setGrayFill(0.92f);
				table.addCell(cell34);

				PdfPCell cell35 = new PdfPCell(new Paragraph("Total Number of mains incoming panels", font));
				cell35.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell35.setFixedHeight(20f);
				cell35.setGrayFill(0.92f);
				table.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph(spdMain1.getTotalMainsIncomingOb(), font));
				cell36.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell36.setFixedHeight(20f);

				table.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph(spdMain1.getTotalNoOutDoorRequipmentRem(), font));
				cell37.setFixedHeight(20f);
				cell37.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell37);
				
				
				PdfPCell cell40 = new PdfPCell(new Paragraph("3", font));
				cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell40.setFixedHeight(20f);
				cell40.setGrayFill(0.92f);
				table.addCell(cell40);

				PdfPCell cell41 = new PdfPCell(new Paragraph(
						"Total Number of panels supplting power to outdoor equipment such as light fittings / air conditioner chiller units (not split AC outdoor unit)", font));
				cell41.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell41.setFixedHeight(20f);
				cell41.setGrayFill(0.92f);
				table.addCell(cell41);

				PdfPCell cell42 = new PdfPCell(new Paragraph(spdMain1.getNoPannelSupplittingOb(), font));
				cell42.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell42.setFixedHeight(20f);
				cell42.setFixedHeight(20f);

				table.addCell(cell42);

				PdfPCell cell43 = new PdfPCell(new Paragraph(spdMain1.getNoPannelSupplittingRem(), font));
				cell43.setFixedHeight(20f);
				cell43.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell43);
				
				PdfPCell cell44 = new PdfPCell(new Paragraph("4", font));
				cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell43.setFixedHeight(20f);
				cell44.setGrayFill(0.92f);
				table.addCell(cell44);

				PdfPCell cell45 = new PdfPCell(
						new Paragraph("Total Number of outdoor equipment and type each equipment", font));
				cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell45.setFixedHeight(20f);
				cell45.setGrayFill(0.92f);
				table.addCell(cell45);

				PdfPCell cell46 = new PdfPCell(new Paragraph(spdMain1.getTotalNoOutDoorRequipmentOb(), font));
				cell46.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

				cell46.setFixedHeight(20f);
				table.addCell(cell46);

				PdfPCell cell47 = new PdfPCell(new Paragraph(spdMain1.getTotalNoOutDoorRequipmentRem(), font));
				cell47.setFixedHeight(20f);
				cell47.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell47);
				
				mainsIncomingItr(document, spdDesc2, font11, table, font);
                
				streetLightIter(spdDesc2, font11, table, font);
				
				pannelFeedingPowerIter(spdDesc2, font11, table, font);
				
                document.add(table);
				document.close();
			}
			
		
		
	catch (Exception e) {
		e.printStackTrace();
	}
	
		}
	
	else {
		throw new SPDException("Invalid Inputs");
	}
	
		
	}

	private void pannelFeedingPowerIter(SpdDescription spdDesc2, Font font11, PdfPTable table, Font font) throws DocumentException, IOException {
		PdfPCell cell13 = new PdfPCell(new Paragraph("7", font11));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setGrayFill(0.92f);
		table.addCell(cell13);

		PdfPCell cell14 = new PdfPCell(new Paragraph(
				"Other panels feeding power to outdoor equipment if more panels are available data from each panel is necessary)", font11));
		cell14.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell14.setGrayFill(0.92f);
		table.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(new Paragraph("", font));
		cell15.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph("", font));
		cell16.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell16);
		
		
		PdfPCell cell17 = new PdfPCell(new Paragraph("7a", font));
		cell17.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell17.setGrayFill(0.92f);
		table.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(
				"Type of SPD / Model",
				font));
		cell18.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell18.setGrayFill(0.92f);
		table.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(spdDesc2.getSpdTypeOb(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell20.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell20);
		
		PdfPCell cell48 = new PdfPCell(new Paragraph("7b", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("Application", font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setFixedHeight(20f);
		cell49.setGrayFill(0.92f);
		table.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationOb(), font));
		cell50.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell50.setFixedHeight(20f);

		table.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell51.setFixedHeight(20f);
		cell51.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph("7c", font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		cell52.setFixedHeight(20f);
		table.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("Panel name", font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setFixedHeight(20f);
		cell53.setGrayFill(0.92f);
		table.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameOb(), font));
		cell54.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell54.setFixedHeight(20f);
		table.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameRem(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph("7d", font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph("Check Incomer rating of the panel", font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setFixedHeight(20f);
		cell57.setGrayFill(0.92f);
		table.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingOb(), font));
		cell58.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell58.setFixedHeight(20f);
		table.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingRem(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell59);
		
		Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		float[] pointColumnWidths30 = { 30F, 150F, 50F, 50F };
		
//		PdfPTable table1 = new PdfPTable(pointColumnWidths30);
//		table1.setWidthPercentage(100); // Width 100%
//		//table.setSpacingBefore(10f); // Space before table
//		table.setWidthPercentage(100);

		PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font111));
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell30.setGrayFill(0.92f);
		table.addCell(cell30);

		PdfPCell cell311 = new PdfPCell(new Paragraph("Description", font111));
		cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell311.setFixedHeight(25f);
		cell311.setGrayFill(0.92f);
		table.addCell(cell311);

		PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font111));
		cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell32.setFixedHeight(25f);
		cell32.setGrayFill(0.92f);
		table.addCell(cell32);

		PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font111));
		cell33.setGrayFill(0.92f);
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell33);
		

		PdfPCell cell60 = new PdfPCell(new Paragraph("7e", font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph("Check Back up fuse", font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setFixedHeight(20f);
		cell61.setGrayFill(0.92f);
		table.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckOb(), font));
		cell62.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell62.setFixedHeight(20f);
		table.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckRem(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph("7f", font));
		cell64.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell64.setGrayFill(0.92f);
		table.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph("Check Connecting wire length", font));
		cell65.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell65.setFixedHeight(20f);
		cell65.setGrayFill(0.92f);
		table.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell66.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell66.setFixedHeight(20f);
		table.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell67);

		
		PdfPCell cell68 = new PdfPCell(new Paragraph("7g", font));
		cell68.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell68.setGrayFill(0.92f);
		table.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph("Check Connecting wire size", font));
		cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell69.setFixedHeight(20f);
		cell69.setGrayFill(0.92f);
		table.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell70.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell70.setFixedHeight(20f);
		table.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell71);
		
	}

	private void streetLightIter(SpdDescription spdDesc2, Font font11, PdfPTable table, Font font) {
		PdfPCell cell13 = new PdfPCell(new Paragraph("6", font11));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setGrayFill(0.92f);
		table.addCell(cell13);

		PdfPCell cell14 = new PdfPCell(new Paragraph(
				"Street light panel(if more panels are available data from each panel is necessary)", font11));
		cell14.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell14.setGrayFill(0.92f);
		table.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(new Paragraph("", font));
		cell15.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph("", font));
		cell16.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell16);
		
		
		PdfPCell cell17 = new PdfPCell(new Paragraph("6a", font));
		cell17.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell17.setGrayFill(0.92f);
		table.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(
				"Type of SPD / Model",
				font));
		cell18.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell18.setGrayFill(0.92f);
		table.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(spdDesc2.getSpdTypeOb(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell20.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell20);
		
		PdfPCell cell48 = new PdfPCell(new Paragraph("6b", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("Application", font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setFixedHeight(20f);
		cell49.setGrayFill(0.92f);
		table.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationOb(), font));
		cell50.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell50.setFixedHeight(20f);

		table.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell51.setFixedHeight(20f);
		cell51.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph("6c", font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		cell52.setFixedHeight(20f);
		table.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("Panel name", font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setFixedHeight(20f);
		cell53.setGrayFill(0.92f);
		table.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameOb(), font));
		cell54.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell54.setFixedHeight(20f);
		table.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameRem(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph("6d", font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph("Check Incomer rating of the panel", font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setFixedHeight(20f);
		cell57.setGrayFill(0.92f);
		table.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingOb(), font));
		cell58.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell58.setFixedHeight(20f);
		table.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingRem(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph("6e", font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph("Check Back up fuse", font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setFixedHeight(20f);
		cell61.setGrayFill(0.92f);
		table.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckOb(), font));
		cell62.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell62.setFixedHeight(20f);
		table.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckRem(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph("6f", font));
		cell64.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell64.setGrayFill(0.92f);
		table.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph("Check Connecting wire length", font));
		cell65.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell65.setFixedHeight(20f);
		cell65.setGrayFill(0.92f);
		table.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell66.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell66.setFixedHeight(20f);
		table.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell67);

		
		PdfPCell cell68 = new PdfPCell(new Paragraph("6g", font));
		cell68.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell68.setGrayFill(0.92f);
		table.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph("Check Connecting wire size", font));
		cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell69.setFixedHeight(20f);
		cell69.setGrayFill(0.92f);
		table.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell70.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell70.setFixedHeight(20f);
		table.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell71);
	}

	private void mainsIncomingItr(Document document, SpdDescription spdDesc2, Font font11, PdfPTable table, Font font)
			throws DocumentException {
		PdfPCell cell13 = new PdfPCell(new Paragraph("5", font11));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setGrayFill(0.92f);
		table.addCell(cell13);

		PdfPCell cell14 = new PdfPCell(new Paragraph(
				"Mains incoming panel (if more panels are available data from each panel is necessary)", font11));
		cell14.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell14.setGrayFill(0.92f);
		table.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(new Paragraph("", font));
		cell15.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph("", font));
		cell16.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell16);
		
		PdfPCell cell17 = new PdfPCell(new Paragraph("5a", font));
		cell17.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell17.setGrayFill(0.92f);
		table.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(
				"Type of SPD / Model",
				font));
		cell18.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell18.setGrayFill(0.92f);
		table.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(spdDesc2.getSpdTypeOb(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell20.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell20);
		
		PdfPCell cell48 = new PdfPCell(new Paragraph("5b", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("Application", font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setFixedHeight(20f);
		cell49.setGrayFill(0.92f);
		table.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationOb(), font));
		cell50.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell50.setFixedHeight(20f);

		table.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(spdDesc2.getSpdApplicationRem(), font));
		cell51.setFixedHeight(20f);
		cell51.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph("5c", font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		cell52.setFixedHeight(20f);
		table.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("Panel name", font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setFixedHeight(20f);
		cell53.setGrayFill(0.92f);
		table.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameOb(), font));
		cell54.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell54.setFixedHeight(20f);
		table.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(spdDesc2.getPanelNameRem(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph("5d", font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph("Check Incomer rating of the panel", font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setFixedHeight(20f);
		cell57.setGrayFill(0.92f);
		table.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingOb(), font));
		cell58.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell58.setFixedHeight(20f);
		table.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(spdDesc2.getIncomingRatingRem(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph("5e", font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph("Check Back up fuse", font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setFixedHeight(20f);
		cell61.setGrayFill(0.92f);
		table.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckOb(), font));
		cell62.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell62.setFixedHeight(20f);
		table.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(spdDesc2.getBackupFuseCheckRem(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph("5f", font));
		cell64.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell64.setGrayFill(0.92f);
		table.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph("Check Connecting wire length", font));
		cell65.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell65.setFixedHeight(20f);
		cell65.setGrayFill(0.92f);
		table.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell66.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell66.setFixedHeight(20f);
		table.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell67);

		
		PdfPCell cell68 = new PdfPCell(new Paragraph("5g", font));
		cell68.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell68.setGrayFill(0.92f);
		table.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph("Check Connecting wire size", font));
		cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell69.setFixedHeight(20f);
		cell69.setGrayFill(0.92f);
		table.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthOb(), font));
		cell70.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell70.setFixedHeight(20f);
		table.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(spdDesc2.getConnectingWireLengthRem(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell71);
		
	}

}
