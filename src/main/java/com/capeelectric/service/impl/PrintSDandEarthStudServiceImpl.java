package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SeparateDistance;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.PrintSDandEarthStudService;
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
public class PrintSDandEarthStudServiceImpl implements PrintSDandEarthStudService{
	
	@Autowired
	private SeperationDistanceRepository seperationDistanceRepository;
	
	@Autowired
	private EarthStudRepository earthStudRepository;

	@Override
	public void printSDandEarthStud(String userName, Integer lpsId) throws EarthStudException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			
			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SDandEarthStud.pdf"));
				document.open();
				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				float[] pointColumnWidths30 = { 30F, 150F, 50F, 50F };
				
				List<SeperationDistanceDescription> separateDistance = seperationDistanceRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				SeperationDistanceDescription separateDistance1 = separateDistance.get(0);
				
				List<SeparateDistance> separateDistance2 = separateDistance1.getSeparateDistanceDescription();
				//SeparateDistance separateDistance3 = separateDistance2.get(0);
				
				List<EarthStudDescription> earthStud1 = earthStudRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				EarthStudDescription earthStud = earthStud1.get(0);

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
				document.add(table);
				
				Font font12 = new Font(BaseFont.createFont(), 10, Font.NORMAL , BaseColor.BLACK);
				
				PdfPTable table2 = new PdfPTable(pointColumnWidths30);
				table2.setWidthPercentage(100); // Width 100%
				//table.setSpacingBefore(10f); // Space before table
				table2.setWidthPercentage(100);

				PdfPCell cell39 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell39.setGrayFill(0.92f);
				table2.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(new Paragraph("Description", font11));
				cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell40.setFixedHeight(25f);
				cell40.setGrayFill(0.92f);
				table2.addCell(cell40);

				PdfPCell cell41 = new PdfPCell(new Paragraph("Observation", font11));
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setFixedHeight(25f);
				cell32.setGrayFill(0.92f);
				table2.addCell(cell32);

				PdfPCell cell42 = new PdfPCell(new Paragraph("Remarks", font11));
				cell42.setGrayFill(0.92f);
				cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell42);
				
				PdfPCell cell43 = new PdfPCell(new Paragraph("1", font12));
				cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell43.setGrayFill(0.92f);
				table2.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph("Earth stud visibility if any", font12));
				cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell44.setFixedHeight(25f);
				table2.addCell(cell44);

				PdfPCell cell45 = new PdfPCell(new Paragraph(earthStud.getEarthStudVisibilityOb(), font12));
				cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell45.setFixedHeight(25f);
				table2.addCell(cell45);

				PdfPCell cell46 = new PdfPCell(new Paragraph(earthStud.getEarthStudVisibilityRem(), font12));
				cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell46);
				
				PdfPCell cell47 = new PdfPCell(new Paragraph("2", font12));
				cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell47.setGrayFill(0.92f);
				table2.addCell(cell47);

				PdfPCell cell48 = new PdfPCell(new Paragraph("Earth stud bend if any", font12));
				cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell48.setFixedHeight(25f);
				table2.addCell(cell48);

				PdfPCell cell49 = new PdfPCell(new Paragraph(earthStud.getEarthStudBendOb(), font12));
				cell49.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell49.setFixedHeight(25f);
				table2.addCell(cell49);

				PdfPCell cell50 = new PdfPCell(new Paragraph(earthStud.getEarthStudBendRem(), font12));
				cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell50);
				
				
				PdfPCell cell51 = new PdfPCell(new Paragraph("3", font12));
				cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell51.setGrayFill(0.92f);
				table2.addCell(cell51);

				PdfPCell cell52 = new PdfPCell(new Paragraph("Proper bonding rail", font12));
				cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell52.setFixedHeight(25f);
				table2.addCell(cell52);

				PdfPCell cell53 = new PdfPCell(new Paragraph(earthStud.getProperBondingRailOb(), font12));
				cell53.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell53.setFixedHeight(25f);
				table2.addCell(cell53);

				PdfPCell cell54 = new PdfPCell(new Paragraph(earthStud.getProperBondingRailRem(), font12));
				cell54.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell54);
				
				
				PdfPCell cell55 = new PdfPCell(new Paragraph("4", font12));
				cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell55.setGrayFill(0.92f);
				table2.addCell(cell55);

				PdfPCell cell56 = new PdfPCell(new Paragraph("Physical damage of stud", font12));
				cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell56.setFixedHeight(25f);
				table2.addCell(cell56);

				PdfPCell cell57 = new PdfPCell(new Paragraph(earthStud.getPhysicalDamageStudOb(), font12));
				cell57.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell57.setFixedHeight(25f);
				table2.addCell(cell57);

				PdfPCell cell58 = new PdfPCell(new Paragraph(earthStud.getPhysicalDamageStudRem(), font12));
				cell58.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell58);
				
				PdfPCell cell59 = new PdfPCell(new Paragraph("5", font12));
				cell59.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell59.setGrayFill(0.92f);
				table2.addCell(cell59);

				PdfPCell cell60 = new PdfPCell(new Paragraph("Whether continuity exist in earth stud", font12));
				cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell60.setFixedHeight(25f);
				table2.addCell(cell60);

				PdfPCell cell61 = new PdfPCell(new Paragraph(earthStud.getContinutyExistaEarthOb(), font12));
				cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell61.setFixedHeight(25f);
				table2.addCell(cell61);

				PdfPCell cell62 = new PdfPCell(new Paragraph(earthStud.getContinutyExistaEarthRem(), font12));
				cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell62);
				
				
				for(SeparateDistance separateDistance3:separateDistance2) {
				PdfPTable table1 = separateDistanceIter(pointColumnWidths30, separateDistance3);
		     document.add(table1);
				}
				document.newPage();
				document.add(table2);
		document.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
			}
		
		else {
			throw new EarthStudException("Invalid Inputs");
		}
		
	}

	private PdfPTable separateDistanceIter(float[] pointColumnWidths30, SeparateDistance separateDistance3)
			throws DocumentException, IOException {
		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL , BaseColor.BLACK);
		
		PdfPTable table1 = new PdfPTable(pointColumnWidths30);
		table1.setWidthPercentage(100); // Width 100%
		//table1.setSpacingBefore(10f); // Space before table
		table1.setWidthPercentage(100);

		PdfPCell cell35 = new PdfPCell(new Paragraph("1", font1));
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell35.setGrayFill(0.92f);
		table1.addCell(cell35);

		PdfPCell cell36 = new PdfPCell(new Paragraph(separateDistance3.getSeperationDistanceDesc(), font1));
		cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell36.setFixedHeight(25f);
		//cell36.setGrayFill(0.92f);
		table1.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph(separateDistance3.getSeperationDistanceOb(), font1));
		cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell37.setFixedHeight(25f);
		//cell37.setGrayFill(0.92f);
		table1.addCell(cell37);

		PdfPCell cell38 = new PdfPCell(new Paragraph(separateDistance3.getSeperationDistanceRem(), font1));
		//cell38.setGrayFill(0.92f);
		cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell38);
		return table1;
	}

}
