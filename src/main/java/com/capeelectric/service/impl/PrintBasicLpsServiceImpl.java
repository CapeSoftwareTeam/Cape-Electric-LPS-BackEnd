package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.BasicLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.PrintAirTerminationService;
import com.capeelectric.service.PrintBasicLpsService;
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
public class PrintBasicLpsServiceImpl implements PrintBasicLpsService {

	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Override
	public void printBasicLps(String userName, Integer lpsId) throws BasicLpsException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			
			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("BasicLps.pdf"));
				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				BasicLps basicLps1 = basicLps.get(0);
				Set<BasicLpsDescription> basicDesc=basicLps1.getBasicLpsDescription();
				List<BasicLpsDescription>basicDesc1 = new ArrayList<>(basicDesc);
				
				BasicLpsDescription basicDesc2 = basicDesc1.get(0);
				//BasicLpsDescription basicDesc1 = basicDesc.get(0);
				document.open();
				float[] pointColumnWidths4 = { 100F };
				
				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL , BaseColor.BLACK);

				PdfPTable table = new PdfPTable(pointColumnWidths4);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell = new PdfPCell(new Paragraph("Check List for lightning protection system Basic details ",font1));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				cell.setFixedHeight(30f);
				table.addCell(cell);	
				document.add(table);
				
				float[] pointColumnWidths1 = { 30F,70F };
				
				PdfPTable table1 = new PdfPTable(pointColumnWidths1);
				table1.setWidthPercentage(100); // Width 100%
				//table1.setSpacingBefore(10f); // Space before table
				table1.setWidthPercentage(100);
				
				PdfPCell cell1 = new PdfPCell(new Paragraph("Client Name",font2));
				cell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell1.setFixedHeight(20f);
				cell1.setGrayFill(0.92f);
				table1.addCell(cell1);	
				
				PdfPCell cell2 = new PdfPCell(new Paragraph(basicLps1.getClientName(),font2));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell2);
				
				
				
				PdfPCell cell3 = new PdfPCell(new Paragraph("Project Name",font2));
				cell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell3.setFixedHeight(20f);
				cell3.setGrayFill(0.92f);
				table1.addCell(cell3);	
				
				PdfPCell cell4 = new PdfPCell(new Paragraph(basicLps1.getProjectName(),font2));
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell4);
				
				PdfPCell cell5 = new PdfPCell(new Paragraph("PMC Name",font2));
				cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell5.setFixedHeight(20f);
				cell5.setGrayFill(0.92f);
				table1.addCell(cell5);	
				
				PdfPCell cell6 = new PdfPCell(new Paragraph(basicLps1.getPmcName(),font2));
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell6);
				
				
				PdfPCell cell7 = new PdfPCell(new Paragraph("Consultant Name",font2));
				cell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell7.setFixedHeight(20f);
				cell7.setGrayFill(0.92f);
				table1.addCell(cell7);	
				
				PdfPCell cell8 = new PdfPCell(new Paragraph(basicLps1.getConsultantName(),font2));
				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell8);
				
				PdfPCell cell9 = new PdfPCell(new Paragraph("Contractor Name",font2));
				cell9.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell9.setFixedHeight(20f);
				cell9.setGrayFill(0.92f);
				table1.addCell(cell9);	
				
				PdfPCell cell10 = new PdfPCell(new Paragraph(basicLps1.getContractorName(),font2));
				cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell10);
				
				PdfPCell cell11 = new PdfPCell(new Paragraph("Dealer/Sub-contractor",font2));
				cell11.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell11.setFixedHeight(20f);
				cell11.setGrayFill(0.92f);
				table1.addCell(cell11);	
				
				PdfPCell cell12 = new PdfPCell(new Paragraph(basicLps1.getDealerContractorName(),font2));
				cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell12);
				
				PdfPCell cell13 = new PdfPCell(new Paragraph("Address",font2));
				cell13.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell13.setFixedHeight(20f);
				cell13.setGrayFill(0.92f);
				table1.addCell(cell13);	
				
				PdfPCell cell14 = new PdfPCell(new Paragraph(basicLps1.getAddress(),font2));
				cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell14);
				
				
				PdfPCell cell15 = new PdfPCell(new Paragraph("Location/Region/Branch",font2));
				cell15.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell15.setFixedHeight(20f);
				cell15.setGrayFill(0.92f);
				table1.addCell(cell15);	
				
				PdfPCell cell16 = new PdfPCell(new Paragraph(basicLps1.getLocation(),font2));
				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell16);
				
				
				PdfPCell cell17 = new PdfPCell(new Paragraph("Installation by CAPE/Contractor/Dealer",font2));
				cell17.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				//cell17.setFixedHeight(30f);
				cell17.setGrayFill(0.92f);
				table1.addCell(cell17);	
				
				PdfPCell cell18 = new PdfPCell(new Paragraph(basicLps1.getLocation(),font2));
				cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell18);
				
				PdfPCell cell19 = new PdfPCell(new Paragraph("Type of Industry",font2));
				cell19.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell19.setGrayFill(0.92f);
				cell19.setFixedHeight(20f);
				table1.addCell(cell19);	
				
				PdfPCell cell20 = new PdfPCell(new Paragraph(basicLps1.getLocation(),font2));
				cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell20);

				
				PdfPCell cell21 = new PdfPCell(new Paragraph("Type of Building",font2));
				cell21.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell21.setGrayFill(0.92f);
				cell21.setFixedHeight(20f);
				table1.addCell(cell21);	
				
				PdfPCell cell22 = new PdfPCell(new Paragraph(basicLps1.getLocation(),font2));
				cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell22);
				
				
				document.add(table1);
				
				float[] pointColumnWidths2 = { 39F,15F,15F,15F,15F,15F,15F };
				PdfPTable table3 = new PdfPTable(pointColumnWidths2);
				table3.setWidthPercentage(100); // Width 100%
				//table3.setSpacingBefore(10f); // Space before table
				table3.setWidthPercentage(100);
				
				PdfPCell cell23 = new PdfPCell(new Paragraph("Building Dimension",font2));
				cell23.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell23.setGrayFill(0.92f);
				cell23.setFixedHeight(20f);
				table3.addCell(cell23);	
				PdfPCell cell111 = new PdfPCell(new Paragraph("Length(m)",font2));
				cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell111.setGrayFill(0.92f);
				cell111.setFixedHeight(20f);
				table3.addCell(cell111);
				PdfPCell cell112 = new PdfPCell(new Paragraph(basicLps1.getBuildingLength(),font2));
				cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell112.setFixedHeight(20f);
				table3.addCell(cell112);	
			
				
				PdfPCell cell114 = new PdfPCell(new Paragraph("Width(m)",font2));
				cell114.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell114.setGrayFill(0.92f);
				cell114.setFixedHeight(20f);
				table3.addCell(cell114);	
				
				PdfPCell cell115 = new PdfPCell(new Paragraph(basicLps1.getBuildingWidth(),font2));
				cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
			
				cell115.setFixedHeight(20f);
				table3.addCell(cell115);
				
				
				PdfPCell cell113 = new PdfPCell(new Paragraph("Height(m)",font2));
				cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell113.setGrayFill(0.92f);
				cell111.setFixedHeight(20f);
				table3.addCell(cell113);
				
				PdfPCell cell24 = new PdfPCell(new Paragraph(basicLps1.getBuildingHeight(),font2));
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(cell24);
				
				
				document.add(table3);
	
				
				PdfPTable table4 = new PdfPTable(pointColumnWidths1);
				table4.setWidthPercentage(100); // Width 100%
				//table4.setSpacingBefore(10f); // Space before table
				table4.setWidthPercentage(100);

				PdfPCell cell25 = new PdfPCell(new Paragraph("Level of protection",font2));
				cell25.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell25.setFixedHeight(20f);
				cell25.setGrayFill(0.92f);
				table4.addCell(cell25);	
				
				PdfPCell cell26 = new PdfPCell(new Paragraph(basicLps1.getLevelOfProtection(),font2));
				cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.addCell(cell26);
				
				PdfPCell cell27 = new PdfPCell(new Paragraph("Soil Resistivity",font2));
				cell27.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell27.setGrayFill(0.92f);
				cell27.setFixedHeight(20f);
				table4.addCell(cell27);	
				
				PdfPCell cell28 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(),font2));
				cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.addCell(cell28);

				
			
				document.add(table4);
				
				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				float[] pointColumnWidths3 = { 30F, 150F, 50F, 50F };

				PdfPTable table2 = new PdfPTable(pointColumnWidths3);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(10f); // Space before table
				table2.setWidthPercentage(100);

				PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell30.setGrayFill(0.92f);
				table2.addCell(cell30);
				
				PdfPCell cell31 = new PdfPCell(new Paragraph("Description", font11));
				cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell31.setFixedHeight(25f);
				cell31.setGrayFill(0.92f);
			    table2.addCell(cell31);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font11));
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setFixedHeight(25f);
				cell32.setGrayFill(0.92f);
				table2.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font11));
				cell33.setGrayFill(0.92f);
				cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell33);
			
			
				
				basicDescription(basicDesc2, table2);
				
				
                document.add(table2);
				document.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}
		 else {
				throw new BasicLpsException("Invalid Inputs");
			}
	}

	private void basicDescription(BasicLpsDescription basicDesc2, PdfPTable table2) throws DocumentException, IOException {
		
		Font font = new Font(BaseFont.createFont(),10, Font.NORMAL, BaseColor.BLACK);
		
		PdfPCell cell34 = new PdfPCell(new Paragraph("1",font));
		cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell34.setFixedHeight(20f);
		cell34.setGrayFill(0.92f);
		table2.addCell(cell34);
		
		PdfPCell cell35 = new PdfPCell(new Paragraph("Collect approved copy of drawing from the site",font));
		cell35.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell35.setFixedHeight(20f);
		cell35.setGrayFill(0.92f);
		table2.addCell(cell35);

		PdfPCell cell36 = new PdfPCell(new Paragraph(basicDesc2.getApprovedByObserv(),font));
		cell36.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell36.setFixedHeight(20f);
		
		table2.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph(basicDesc2.getApprovedByRemarks(),font));
		cell37.setFixedHeight(20f);
		cell37.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell37);
		
		
		PdfPCell cell40 = new PdfPCell(new Paragraph("1a",font));
		cell40.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell40.setFixedHeight(20f);
		cell40.setGrayFill(0.92f);
		table2.addCell(cell40);
		
		PdfPCell cell41 = new PdfPCell(new Paragraph("Architect Name",font));
		cell41.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell41.setFixedHeight(20f);
		cell41.setGrayFill(0.92f);
		table2.addCell(cell41);

		PdfPCell cell42 = new PdfPCell(new Paragraph(basicDesc2.getArchitectNameObserv(),font));
		cell42.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell42.setFixedHeight(20f);
		cell42.setFixedHeight(20f);
	
		table2.addCell(cell42);

		PdfPCell cell43 = new PdfPCell(new Paragraph(basicDesc2.getArchitectNameRemarks(),font));
		cell43.setFixedHeight(20f);
		cell43.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell43);
		
		
		PdfPCell cell44 = new PdfPCell(new Paragraph("1b",font));
		cell44.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell43.setFixedHeight(20f);
		cell44.setGrayFill(0.92f);
		table2.addCell(cell44);
		
		PdfPCell cell45 = new PdfPCell(new Paragraph("Date of Design",font));
		cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell45.setFixedHeight(20f);
		cell45.setGrayFill(0.92f);
		table2.addCell(cell45);

		PdfPCell cell46 = new PdfPCell(new Paragraph(basicDesc2.getDesignDateObserv(),font));
		cell46.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		
		cell46.setFixedHeight(20f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(basicDesc2.getDesignDateRemarks(),font));
		cell47.setFixedHeight(20f);
		cell47.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell47);
		
		
		
		PdfPCell cell48 = new PdfPCell(new Paragraph("1c",font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table2.addCell(cell48);
		
		PdfPCell cell49 = new PdfPCell(new Paragraph("Approved by",font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setFixedHeight(20f);
		cell49.setGrayFill(0.92f);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph(basicDesc2.getApprovedByObserv(),font));
		cell50.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell50.setFixedHeight(20f);
		
		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(basicDesc2.getApprovedByRemarks(),font));
		cell51.setFixedHeight(20f);
		cell51.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell51);
		
		
		
		PdfPCell cell52 = new PdfPCell(new Paragraph("1d",font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		cell52.setFixedHeight(20f);
		table2.addCell(cell52);
		
		PdfPCell cell53 = new PdfPCell(new Paragraph("Date of approval",font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setFixedHeight(20f);
		cell53.setGrayFill(0.92f);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(basicDesc2.getDateOfApprovalOb(),font));
		cell54.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell54.setFixedHeight(20f);
	
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(basicDesc2.getDateOfApprovalRem(),font));
	
		cell55.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell55);
		
		

		PdfPCell cell56 = new PdfPCell(new Paragraph("1e",font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table2.addCell(cell56);
		
		PdfPCell cell57 = new PdfPCell(new Paragraph("Drawing number",font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setFixedHeight(20f);
		cell57.setGrayFill(0.92f);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(basicDesc2.getDrawingObserv(),font));
		cell58.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell58.setFixedHeight(20f);
		
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(basicDesc2.getDrawingRemarks(),font));
	
		cell59.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell59);
		
		
		PdfPCell cell60 = new PdfPCell(new Paragraph("1f",font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table2.addCell(cell60);
		
		PdfPCell cell61 = new PdfPCell(new Paragraph("Revision number",font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setFixedHeight(20f);
		cell61.setGrayFill(0.92f);
		table2.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(basicDesc2.getRevisionNoObserv(),font));
		cell62.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell62.setFixedHeight(20f);
	
		table2.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(basicDesc2.getRevisionNoRemarks(),font));
		
		cell63.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell63);
		
		
		PdfPCell cell64 = new PdfPCell(new Paragraph("2",font));
		cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell64.setGrayFill(0.92f);
		table2.addCell(cell64);
		
		PdfPCell cell65 = new PdfPCell(new Paragraph("Check for any deviation of design with standard",font));
		cell65.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell65.setFixedHeight(20f);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(basicDesc2.getDesignDateObserv(),font));
		cell66.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell66.setFixedHeight(20f);
	
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(basicDesc2.getDeviationRemarks(),font));
		
		cell67.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell67);
		
		PdfPCell cell68 = new PdfPCell(new Paragraph("3",font));
		cell68.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell68.setGrayFill(0.92f);
		table2.addCell(cell68);
		
		PdfPCell cell69 = new PdfPCell(new Paragraph("Quality of installation",font));
		cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell69.setFixedHeight(20f);
		cell69.setGrayFill(0.92f);
		table2.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(basicDesc2.getInstallationQualityObserv(),font));
		cell70.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell70.setFixedHeight(20f);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(basicDesc2.getInstallationQualityRemarks(),font));
		cell71.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell71);
		
		PdfPCell cell72 = new PdfPCell(new Paragraph("",font));
		cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell72.setGrayFill(0.92f);
		table2.addCell(cell72);
		
		PdfPCell cell73 = new PdfPCell(new Paragraph("",font));
		cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell73.setFixedHeight(20f);
		cell73.setGrayFill(0.92f);
		table2.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph("",font));
		cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell74.setFixedHeight(20f);
		table2.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph("",font));
		cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell75);
		
		PdfPCell cell76 = new PdfPCell(new Paragraph("",font));
		cell76.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell76.setGrayFill(0.92f);
		table2.addCell(cell76);
		
		PdfPCell cell77 = new PdfPCell(new Paragraph("",font));
		cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell77.setFixedHeight(20f);
		cell77.setGrayFill(0.92f);
		table2.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph("",font));
		cell78.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell78.setFixedHeight(20f);
		table2.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph("",font));
		cell79.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell79);
		
		
		PdfPCell cell80 = new PdfPCell(new Paragraph("",font));
		cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell80.setGrayFill(0.92f);
		table2.addCell(cell80);
		
		PdfPCell cell81 = new PdfPCell(new Paragraph("",font));
		cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell81.setFixedHeight(20f);
		cell81.setGrayFill(0.92f);
		table2.addCell(cell81);

		PdfPCell cell82 = new PdfPCell(new Paragraph("",font));
		cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell82.setFixedHeight(20f);
		table2.addCell(cell82);

		PdfPCell cell83 = new PdfPCell(new Paragraph("",font));
		cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell83);
		
		
		PdfPCell cell84 = new PdfPCell(new Paragraph("",font));
		cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell84.setGrayFill(0.92f);
		table2.addCell(cell84);
		
		PdfPCell cell85 = new PdfPCell(new Paragraph("",font));
		cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell85.setFixedHeight(20f);
		cell85.setGrayFill(0.92f);
		table2.addCell(cell85);

		PdfPCell cell86 = new PdfPCell(new Paragraph("",font));
		cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell86.setFixedHeight(20f);
		table2.addCell(cell86);

		PdfPCell cell87 = new PdfPCell(new Paragraph("",font));
		cell87.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell87);
		
		
		PdfPCell cell88 = new PdfPCell(new Paragraph("",font));
		cell88.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell88.setGrayFill(0.92f);
		table2.addCell(cell88);
		
		PdfPCell cell89 = new PdfPCell(new Paragraph("",font));
		cell89.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell89.setFixedHeight(20f);
		cell89.setGrayFill(0.92f);
		table2.addCell(cell89);

		PdfPCell cell90 = new PdfPCell(new Paragraph("",font));
		cell90.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell90.setFixedHeight(20f);
		table2.addCell(cell90);

		PdfPCell cell91 = new PdfPCell(new Paragraph("",font));
		cell91.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell91);
		
		
	}

}
