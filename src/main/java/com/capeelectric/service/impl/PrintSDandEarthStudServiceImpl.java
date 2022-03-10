package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.SeparateDistance;
import com.capeelectric.model.SeparateDistanceDownConductors;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.service.PrintSDandEarthStudService;
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
public class PrintSDandEarthStudServiceImpl implements PrintSDandEarthStudService {

//	@Autowired
//	private EarthStudRepository earthStudRepository;
//
//	@Autowired
//	private SeperationDistanceRepository seperationDistanceRepository;

	@Override
	public void printSDandEarthStud(String userName, Integer lpsId, 
			Optional<SeperationDistanceReport> separateDistanceReport, Optional<EarthStudReport> earthStudDetails) throws EarthStudException {

//	@Override
//	public void printSDandEarthStud1(String userName, Integer lpsId) throws EarthStudException {

		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SDandEarthStud.pdf"));

				document.open();

//				List<SeperationDistanceReport> separateDistance2 = seperationDistanceRepository
//						.findByUserNameAndBasicLpsId(userName, lpsId);
			
				SeperationDistanceReport separateDistance = separateDistanceReport.get();

				List<SeperationDistanceDescription> separateDistanceDesc = separateDistance
						.getSeperationDistanceDescription();

				Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths5 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths5);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table

				PdfPCell label = new PdfPCell(new Paragraph(
						"Check list for Seperation Distance System of LPS\r\n" + "as per IS/IEC 62305", font12B));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
//				label.setFixedHeight(20f);
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

				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths30 = { 30F, 145F, 55F, 50F };

				PdfPTable table2 = new PdfPTable(pointColumnWidths30);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(10f); // Space before table
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

				for (SeperationDistanceDescription separateDistance1 : separateDistanceDesc) {

					float[] pointColumnWidths = { 120F, 80F };
					PdfPTable table100 = new PdfPTable(pointColumnWidths);

					table100.setWidthPercentage(100); // Width 100%
					table100.setSpacingBefore(5f); // Space before table
					table100.setSpacingAfter(5f); // Space after table
					table100.setWidthPercentage(100);
					table100.getDefaultCell().setBorder(0);

					PdfPCell cell1 = new PdfPCell(new Paragraph("Building Number:",
							new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
					cell1.setBackgroundColor(new BaseColor(203, 183, 162));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell1.setBorder(PdfPCell.NO_BORDER);
					table100.addCell(cell1);
					PdfPCell cell2 = new PdfPCell(new Paragraph(separateDistance1.getBuildingNumber().toString(),
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
					PdfPCell cell3 = new PdfPCell(new Paragraph("Building Name:",
							new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
					cell3.setBackgroundColor(new BaseColor(203, 183, 162));
					cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell3.setBorder(PdfPCell.NO_BORDER);
					table1001.addCell(cell3);
					PdfPCell cell4 = new PdfPCell(new Paragraph(separateDistance1.getBuildingName(),
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

					PdfPCell cell206 = new PdfPCell(new Paragraph(
							"Calculated seperation distance between LPS and other metallic parts of building", font1));
					cell206.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell206.setGrayFill(0.92f);
					table205.addCell(cell206);

					PdfPCell cell209 = new PdfPCell(
							new Paragraph(separateDistance1.getCalculatedSeperationDistanceOb(), font1));
					cell209.setHorizontalAlignment(Element.ALIGN_LEFT);
					table205.addCell(cell209);

					PdfPCell cell210 = new PdfPCell(
							new Paragraph(separateDistance1.getCalculatedSeperationDistanceRem(), font1));
					cell210.setHorizontalAlignment(Element.ALIGN_LEFT);
					table205.addCell(cell210);

					PdfPCell cell20 = new PdfPCell(new Paragraph("2", font1));
					cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell20.setGrayFill(0.92f);
					table205.addCell(cell20);

					PdfPCell cell201 = new PdfPCell(new Paragraph(
							"Measured Seperation distance between air termination and electrical apparatus (lights, Solar PV, AC chillers, cameras…. Etc)(Measurement required in atleast 10 locations on roof top) in meters",
							font1));
					cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell201.setGrayFill(0.92f);
					cell201.setColspan(3);
					table205.addCell(cell201);

					document.add(table2);
					document.add(table205);

					List<SeparateDistance> separateDistanceIter = separateDistance1.getSeparateDistance();

					int i = 1;
					for (SeparateDistance separateDistanceIter1 : separateDistanceIter) {
						if (!separateDistanceIter1.getFlag().equals("R")) {
							separateDistanceIter(document, font1, separateDistanceIter1, i);
						}
					}

					PdfPTable table20 = new PdfPTable(pointColumnWidths30);
					table20.setWidthPercentage(100); // Width 100%
					// table205.setSpacingBefore(20f); // Space before table
					table20.setWidthPercentage(100);

					PdfPCell cell211 = new PdfPCell(new Paragraph("3", font1));
					cell211.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell211.setGrayFill(0.92f);
					table20.addCell(cell211);

					PdfPCell cell203 = new PdfPCell(new Paragraph(
							"Measured Seperation distance between down conductors and electrical apparatus (lights, Solar PV, AC chillers, cameras…. Etc)(Measurement required in atleast 10 locations on roof top) in meters",
							font1));

					cell203.setFixedHeight(50f);
					cell203.setGrayFill(0.92f);
					cell203.setColspan(3);
					table20.addCell(cell203);
					document.add(table20);

					List<SeparateDistanceDownConductors> separateDistanceDownIter = separateDistance1
							.getSeparateDistanceDownConductors();

					for (SeparateDistanceDownConductors separateDistanceDownIter1 : separateDistanceDownIter) {
						if (!separateDistanceDownIter1.getFlag().equals("R")) {
							separateDistanceDownIter(document, font1, i, separateDistanceDownIter1);
						}
					}
				}
				
				
				
//				List<EarthStudReport> earthStudReport1 = earthStudRepository.findByUserNameAndBasicLpsId(userName,
//						lpsId);
				EarthStudReport earthStudReport = earthStudDetails.get();

				document.newPage();

				PdfPTable headerlabel1 = new PdfPTable(pointColumnWidths5);
				headerlabel1.setWidthPercentage(100); // Width 100%
				headerlabel1.setSpacingBefore(10f); // Space before table

				PdfPCell headlabel = new PdfPCell(new Paragraph(
						"Check list for Equipotential Bonding System of LPS\r\n" + "as per IS/IEC 62305", font12B));
				headlabel.setHorizontalAlignment(Element.ALIGN_CENTER);
				headlabel.setGrayFill(0.92f);
//				headlabel.setFixedHeight(20f);
				headerlabel1.addCell(headlabel);
				document.add(headerlabel1);

				List<EarthStudDescription> earthStudDescription = earthStudReport.getEarthStudDescription();
				for (EarthStudDescription earthStudDesc : earthStudDescription) {
					PdfPTable table20 = earthStudIter(document, font3, font1, pointColumnWidths30, table2,
							earthStudDesc);

					document.add(table2);
					document.add(table20);
				}

				document.close();
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else {
			throw new EarthStudException("Invalid Inputs");
		}

	}

	private PdfPTable earthStudIter(Document document, Font font3, Font font1, float[] pointColumnWidths30,
			PdfPTable table2, EarthStudDescription earthStudDesc) throws DocumentException, IOException {

		float[] pointColumnWidths = { 120F, 80F };
		PdfPTable table100 = new PdfPTable(pointColumnWidths);

		table100.setWidthPercentage(100); // Width 100%
		table100.setSpacingBefore(10f); // Space before table
		table100.setSpacingAfter(5f); // Space after table
		table100.setWidthPercentage(100);
		table100.getDefaultCell().setBorder(0);

		PdfPCell cell1 = new PdfPCell(
				new Paragraph("Building Number:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		cell1.setBackgroundColor(new BaseColor(203, 183, 162));
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setBorder(PdfPCell.NO_BORDER);
		table100.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Paragraph(earthStudDesc.getBuildingNumber().toString(),
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
		PdfPCell cell4 = new PdfPCell(new Paragraph(earthStudDesc.getBuildingName(),
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

		PdfPCell cell206 = new PdfPCell(
				new Paragraph("Whether equipotential bonding between LPS and MET is available", font1));
//		cell206.setFixedHeight(40f);
		cell206.setGrayFill(0.92f);
		table205.addCell(cell206);

		PdfPCell cell209 = new PdfPCell(new Paragraph(earthStudDesc.getAvailableEquipotentialBondingOb(), font3));
		cell209.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell209);

		PdfPCell cell210 = new PdfPCell(new Paragraph(earthStudDesc.getAvailableEquipotentialBondingRem(), font3));
		cell210.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell210);

		if (earthStudDesc.getAvailableEquipotentialBondingOb().equalsIgnoreCase("Yes")) {

			PdfPCell cell20 = new PdfPCell(new Paragraph("2", font1));
			cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell20.setGrayFill(0.92f);
			table205.addCell(cell20);

			PdfPCell cell21 = new PdfPCell(new Paragraph("Number of Equipotential bondings", font1));
			cell21.setFixedHeight(20f);
			cell21.setGrayFill(0.92f);
			table205.addCell(cell21);

			PdfPCell cell29 = new PdfPCell(new Paragraph(earthStudDesc.getNumberOfEquipotentialBondingOb(), font3));
			cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell29);

			PdfPCell cell22 = new PdfPCell(new Paragraph(earthStudDesc.getNumberOfEquipotentialBondingRem(), font3));
			cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell22);

			PdfPCell cell24 = new PdfPCell(new Paragraph("3", font1));
			cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell24.setGrayFill(0.92f);
			table205.addCell(cell24);

			PdfPCell cell23 = new PdfPCell(
					new Paragraph("Size of earthing conductor interconnecting LPS and MET", font1));
//		cell23.setFixedHeight(20f);
			cell23.setGrayFill(0.92f);
			table205.addCell(cell23);

			PdfPCell cell35 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfEarthingConductorOb(), font3));
			cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell35);

			PdfPCell cell36 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfEarthingConductorRem(), font3));
			cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell36);

			PdfPCell cell37 = new PdfPCell(new Paragraph("4", font1));
			cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell37.setGrayFill(0.92f);
			table205.addCell(cell37);

			PdfPCell cell40 = new PdfPCell(new Paragraph(
					"Concept of protevtive equipotential bonding (for fault protection) carried out in the building",
					font1));
//		cell40.setFixedHeight(20f);
			cell40.setGrayFill(0.92f);
			table205.addCell(cell40);

			PdfPCell cell44 = new PdfPCell(new Paragraph(earthStudDesc.getConceptOfEquipotentialBondingOb(), font3));
			cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell44);

			PdfPCell cell47 = new PdfPCell(new Paragraph(earthStudDesc.getConceptOfEquipotentialBondingRem(), font3));
			cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell47);

			PdfPCell cell43 = new PdfPCell(new Paragraph("5", font1));
			cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell43.setGrayFill(0.92f);
			table205.addCell(cell43);

			PdfPCell cell50 = new PdfPCell(
					new Paragraph("Main protective equipotential bonding at service entrance in numbers", font1));
			cell50.setGrayFill(0.92f);
			table205.addCell(cell50);

			PdfPCell cell51 = new PdfPCell(
					new Paragraph(earthStudDesc.getMainProtectiveEquipotentialBondingOb(), font3));
			cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell51);

			PdfPCell cell52 = new PdfPCell(
					new Paragraph(earthStudDesc.getMainProtectiveEquipotentialBondingRem(), font3));
			cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell52);

			PdfPCell cell54 = new PdfPCell(new Paragraph("6", font1));
			cell54.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell54.setGrayFill(0.92f);
			table205.addCell(cell54);

			PdfPCell cell55 = new PdfPCell(new Paragraph("Size of main protective equipotential bonding", font1));
			cell55.setGrayFill(0.92f);
			table205.addCell(cell55);

			PdfPCell cell56 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfMainProtectiveOb(), font3));
			cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell56);

			PdfPCell cell57 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfMainProtectiveRem(), font3));
			cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell57);

			PdfPCell cell58 = new PdfPCell(new Paragraph("7", font1));
			cell58.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell58.setGrayFill(0.92f);
			table205.addCell(cell58);

			PdfPCell cell59 = new PdfPCell(
					new Paragraph("Supplimentary Protective equipotential bondding in numbers", font1));
			cell59.setGrayFill(0.92f);
			table205.addCell(cell59);

			PdfPCell cell60 = new PdfPCell(new Paragraph(earthStudDesc.getSupplimentaryMainProtectiveOb(), font3));
			cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell60);

			PdfPCell cell61 = new PdfPCell(new Paragraph(earthStudDesc.getSupplimentaryMainProtectiveRem(), font3));
			cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
			table205.addCell(cell61);
		}
		
		PdfPCell cell62 = new PdfPCell(new Paragraph("8", font1));
		cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell62.setGrayFill(0.92f);
		table205.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph("Size of supplimentary protective equipotential bonding", font1));
		cell63.setGrayFill(0.92f);
		table205.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfSupplimentaryProtectiveOb(), font3));
		cell64.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph(earthStudDesc.getSizeOfSupplimentaryProtectiveRem(), font3));
		cell65.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell65);

		document.add(table2);
		document.add(table205);

		float[] pointColumnWidths3 = { 100F };

		PdfPTable table5 = new PdfPTable(pointColumnWidths3);

		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(10f); // Space before table
		// table5.setSpacingAfter(10f); // Space after table
		table5.getDefaultCell().setBorder(0);

		PdfPCell arrangements1 = new PdfPCell(new Paragraph("Integrated LPS Tests During Construction",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		arrangements1.setBackgroundColor(new GrayColor(0.82f));
		arrangements1.setHorizontalAlignment(Element.ALIGN_CENTER);
		arrangements1.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(arrangements1);
		document.add(table5);

		PdfPTable table20 = new PdfPTable(pointColumnWidths30);
		table20.setWidthPercentage(100); // Width 100%
		// table205.setSpacingBefore(20f); // Space before table
		table20.setWidthPercentage(100);

		PdfPCell cell = new PdfPCell(new Paragraph("8.1", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setGrayFill(0.92f);
		table20.addCell(cell);

		PdfPCell cell6 = new PdfPCell(new Paragraph("Earth stud visibility if any", font1));
		cell6.setGrayFill(0.92f);
		table20.addCell(cell6);

		PdfPCell cell7 = new PdfPCell(new Paragraph(earthStudDesc.getEarthStudVisibilityOb(), font3));
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell7);

		PdfPCell cell8 = new PdfPCell(new Paragraph(earthStudDesc.getEarthStudVisibilityRem(), font3));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell8);

		PdfPCell cell9 = new PdfPCell(new Paragraph("8.2", font1));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setGrayFill(0.92f);
		table20.addCell(cell9);

		PdfPCell cell70 = new PdfPCell(new Paragraph("Earth stud visibility if any", font1));
		cell70.setGrayFill(0.92f);
		table20.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(earthStudDesc.getEarthStudBendOb(), font3));
		cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(earthStudDesc.getEarthStudBendRem(), font3));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph("8.3", font1));
		cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell73.setGrayFill(0.92f);
		table20.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph("Proper bonding rail", font1));
		cell74.setGrayFill(0.92f);
		table20.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph(earthStudDesc.getProperBondingRailOb(), font3));
		cell75.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph(earthStudDesc.getProperBondingRailRem(), font3));
		cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(new Paragraph("8.4", font1));
		cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell77.setGrayFill(0.92f);
		table20.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph("Physical damage of stud", font1));
		cell78.setGrayFill(0.92f);
		table20.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph(earthStudDesc.getPhysicalDamageStudOb(), font3));
		cell79.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph(earthStudDesc.getPhysicalDamageStudRem(), font3));
		cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell80);

		PdfPCell cell81 = new PdfPCell(new Paragraph("8.5", font1));
		cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell81.setGrayFill(0.92f);
		table20.addCell(cell81);

		PdfPCell cell82 = new PdfPCell(new Paragraph("Whether continuity exist in earth stud", font1));
		cell82.setGrayFill(0.92f);
		table20.addCell(cell82);

		PdfPCell cell83 = new PdfPCell(new Paragraph(earthStudDesc.getContinutyExistaEarthOb(), font3));
		cell83.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell83);

		PdfPCell cell84 = new PdfPCell(new Paragraph(earthStudDesc.getContinutyExistaEarthRem(), font3));
		cell84.setHorizontalAlignment(Element.ALIGN_LEFT);
		table20.addCell(cell84);
		return table20;
	}

	private void separateDistanceDownIter(Document document, Font font1, int i,
			SeparateDistanceDownConductors separateDistanceDownIter1) throws DocumentException, IOException {

		float[] pointColumnWidths = { 30F, 145F, 55F, 50F };
		Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPTable table = new PdfPTable(pointColumnWidths);
		table.setWidthPercentage(100); // Width 100%
		// table205.setSpacingBefore(20f); // Space before table
		table.setWidthPercentage(100);

		PdfPCell cell = new PdfPCell(new Paragraph("3.1", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setGrayFill(0.92f);
		table.addCell(cell);
		++i;
		PdfPCell cell5 = new PdfPCell(new Paragraph(separateDistanceDownIter1.getSeperationDistanceDesc(), font3));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(
				new Paragraph(separateDistanceDownIter1.getSeperationDistanceOb().toString(), font3));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell6);

		PdfPCell cell21 = new PdfPCell(new Paragraph(separateDistanceDownIter1.getSeperationDistanceRem(), font3));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell21);
		document.add(table);
	}

	private void separateDistanceIter(Document document, Font font1, SeparateDistance separateDistanceIter1, int i)
			throws DocumentException, IOException {

		float[] pointColumnWidths = { 30F, 145F, 55F, 50F };
		Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPTable table = new PdfPTable(pointColumnWidths);
		table.setWidthPercentage(100); // Width 100%
		// table205.setSpacingBefore(20f); // Space before table
		table.setWidthPercentage(100);

		PdfPCell cell = new PdfPCell(new Paragraph("2." + i, font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setGrayFill(0.92f);
		table.addCell(cell);
		++i;
		PdfPCell cell5 = new PdfPCell(new Paragraph(separateDistanceIter1.getSeperationDistanceDesc(), font3));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setFixedHeight(20f);
		table.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(separateDistanceIter1.getSeperationDistanceOb().toString(), font3));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell6);

		PdfPCell cell21 = new PdfPCell(new Paragraph(separateDistanceIter1.getSeperationDistanceRem(), font3));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell21);
		document.add(table);
	}
}
