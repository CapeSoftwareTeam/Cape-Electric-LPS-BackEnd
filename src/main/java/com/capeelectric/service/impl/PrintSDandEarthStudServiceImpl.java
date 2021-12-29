package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.SeparateDistance;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.EarthStudRepository;
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
public class PrintSDandEarthStudServiceImpl implements PrintSDandEarthStudService {

	@Autowired
	private EarthStudRepository earthStudRepository;

	@Override
	public void printSDandEarthStud(String userName, Integer lpsId, Optional<BasicLps> basicLpsDetails,
			Optional<SeperationDistanceDescription> separateDistanceDetails) throws EarthStudException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SDandEarthStud.pdf"));

				document.open();

				BasicLps basicLps1 = basicLpsDetails.get();

				SeperationDistanceDescription separateDistance1 = separateDistanceDetails.get();
				List<SeparateDistance> separateDistance2 = separateDistance1.getSeparateDistanceDescription();

				List<EarthStudDescription> earthStud1 = earthStudRepository.findByUserNameAndBasicLpsId(userName,
						lpsId);
//				EarthStudDescription earthStud = earthStud1.get(0);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table
				headertable.setWidthPercentage(100);

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPCell label = new PdfPCell(
						new Paragraph("Check list for Separation Distance of LPS \r\n" + "as per IS/IEC 62305", font1));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
				// label.setFixedHeight(20f);
				headertable.addCell(label);
				document.add(headertable);

				float[] pointColumnWidths1 = { 30F, 70F };

				PdfPTable table11 = new PdfPTable(pointColumnWidths1);
				table11.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table11.setWidthPercentage(100);

				PdfPCell cell11 = new PdfPCell(new Paragraph("Client Name", font2));
				cell11.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell11.setFixedHeight(20f);
				cell11.setGrayFill(0.92f);
				table11.addCell(cell11);

				PdfPCell cell2 = new PdfPCell(new Paragraph(basicLps1.getClientName(), font3));
				cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table11.addCell(cell2);

				PdfPCell cell31 = new PdfPCell(new Paragraph("Project Name", font2));
				cell31.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell31.setFixedHeight(20f);
				cell31.setGrayFill(0.92f);
				table11.addCell(cell31);

				PdfPCell cell41 = new PdfPCell(new Paragraph(basicLps1.getProjectName(), font3));
				cell41.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table11.addCell(cell41);

				PdfPCell cell191 = new PdfPCell(new Paragraph("Type of Industry", font2));
				cell191.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell191.setGrayFill(0.92f);
				cell191.setFixedHeight(20f);
				table11.addCell(cell191);

				PdfPCell cell201 = new PdfPCell(new Paragraph(basicLps1.getIndustryType(), font3));
				cell201.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table11.addCell(cell201);

				PdfPCell cell211 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell211.setGrayFill(0.92f);
				cell211.setFixedHeight(20f);
				table11.addCell(cell211);

				PdfPCell cell22 = new PdfPCell(new Paragraph(basicLps1.getBuildingType(), font3));
				cell22.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table11.addCell(cell22);

				document.add(table11);

				float[] pointColumnWidths20 = { 38.5F, 15F, 15F, 15F, 15F, 15F, 15F };
				PdfPTable table31 = new PdfPTable(pointColumnWidths20);
				table31.setWidthPercentage(100); // Width 100%
				// table3.setSpacingBefore(10f); // Space before table
				table31.setWidthPercentage(100);

				PdfPCell cell23 = new PdfPCell(new Paragraph("Building Dimension", font2));
				cell23.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell23.setGrayFill(0.92f);
				cell23.setFixedHeight(20f);
				table31.addCell(cell23);
				PdfPCell cell1111 = new PdfPCell(new Paragraph("Length(m)", font2));
				cell1111.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell1111.setGrayFill(0.92f);
				cell1111.setFixedHeight(20f);
				table31.addCell(cell1111);
				PdfPCell cell112 = new PdfPCell(new Paragraph(basicLps1.getBuildingLength(), font3));
				cell112.setHorizontalAlignment(Element.ALIGN_CENTER);

				cell112.setFixedHeight(20f);
				table31.addCell(cell112);

				PdfPCell cell114 = new PdfPCell(new Paragraph("Width(m)", font2));
				cell114.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell114.setGrayFill(0.92f);
				cell114.setFixedHeight(20f);
				table31.addCell(cell114);

				PdfPCell cell115 = new PdfPCell(new Paragraph(basicLps1.getBuildingWidth(), font3));
				cell115.setHorizontalAlignment(Element.ALIGN_CENTER);

				cell115.setFixedHeight(20f);
				table31.addCell(cell115);

				PdfPCell cell113 = new PdfPCell(new Paragraph("Height(m)", font2));
				cell113.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell113.setGrayFill(0.92f);
				cell1111.setFixedHeight(20f);
				table31.addCell(cell113);

				PdfPCell cell24 = new PdfPCell(new Paragraph(basicLps1.getBuildingHeight(), font3));
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				table31.addCell(cell24);

				document.add(table31);

				PdfPTable table4 = new PdfPTable(pointColumnWidths1);
				table4.setWidthPercentage(100); // Width 100%
				// table4.setSpacingBefore(10f); // Space before table
				table4.setWidthPercentage(100);

				PdfPCell cell251 = new PdfPCell(new Paragraph("Level of protection", font2));
				cell251.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell251.setFixedHeight(20f);
				cell251.setGrayFill(0.92f);
				table4.addCell(cell251);

				PdfPCell cell26 = new PdfPCell(new Paragraph(basicLps1.getLevelOfProtection(), font3));
				cell26.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table4.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph("Soil Resistivity (ohms)", font2));
				cell27.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell27.setGrayFill(0.92f);
				cell27.setFixedHeight(20f);
				table4.addCell(cell27);

				PdfPCell cell28 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(), font3));
				cell28.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table4.addCell(cell28);

				document.add(table4);

				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

				PdfPTable table = new PdfPTable(pointColumnWidths30);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(20f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell30.setFixedHeight(20f);
				cell30.setGrayFill(0.92f);
				table.addCell(cell30);

				PdfPCell cell311 = new PdfPCell(new Paragraph("Description", font11));
				cell311.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell311.setFixedHeight(20f);
				cell311.setGrayFill(0.92f);
				table.addCell(cell311);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font11));
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setFixedHeight(20f);
				cell32.setGrayFill(0.92f);
				table.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font11));
				cell33.setGrayFill(0.92f);
				cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell33);

				PdfPCell cell430 = new PdfPCell(new Paragraph("1", font2));

				cell430.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell430.setGrayFill(0.92f);
				// cell43.setColspan(3);
				table.addCell(cell430);

				PdfPCell cell400 = new PdfPCell(new Paragraph(
						"Measured separation distance between air termination and electrical apparatus (lights, solar PV, AC chillers, cameras…. Etc)(Measurement required in atleast 10 locations on roof top)",
						font3));

				cell400.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell400.setGrayFill(0.92f);
				cell400.setColspan(3);
				table.addCell(cell400);
				document.add(table);

				PdfPTable table1 = new PdfPTable(pointColumnWidths30);
				table1.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table1.setWidthPercentage(100);

				int i = 2;
				for (SeparateDistance separateDistance3 : separateDistance2) {
					Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

					PdfPCell cell35 = new PdfPCell(new Paragraph(""+i,font111));
					cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell35.setGrayFill(0.92f);
					table1.addCell(cell35);

					PdfPCell cell36 = new PdfPCell(
							new Paragraph(separateDistance3.getSeperationDistanceDesc(), font111));
					cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell36.setFixedHeight(20f);
					cell36.setGrayFill(0.92f);
					table1.addCell(cell36);

					PdfPCell cell37 = new PdfPCell(new Paragraph(separateDistance3.getSeperationDistanceOb(), font111));
					cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell37.setFixedHeight(20f);
					// cell37.setGrayFill(0.92f);
					table1.addCell(cell37);

					PdfPCell cell38 = new PdfPCell(
							new Paragraph(separateDistance3.getSeperationDistanceRem(), font111));
					// cell38.setGrayFill(0.92f);
					cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
					table1.addCell(cell38);
					++i;
				}
				document.add(table1);
				document.newPage();

				PdfPTable headertable1 = new PdfPTable(pointColumnWidths40);
				headertable1.setWidthPercentage(100); // Width 100%
				headertable1.setSpacingBefore(10f); // Space before table
				headertable1.setWidthPercentage(100);

				PdfPCell label1 = new PdfPCell(new Paragraph(
						"Check list for Equipotential bonding of LPS \r\n" + "as per IS/IEC 62305", font1));
				label1.setHorizontalAlignment(Element.ALIGN_CENTER);
				label1.setGrayFill(0.92f);
				// label.setFixedHeight(20f);
				headertable1.addCell(label1);
				document.add(headertable1);

				PdfPTable table12 = new PdfPTable(pointColumnWidths1);
				table12.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table12.setWidthPercentage(100);

				PdfPCell cell20 = new PdfPCell(new Paragraph("Client Name", font2));
				cell20.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell20.setFixedHeight(20f);
				cell20.setGrayFill(0.92f);
				table12.addCell(cell20);

				PdfPCell cell21 = new PdfPCell(new Paragraph(basicLps1.getClientName(), font3));
				cell21.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table12.addCell(cell21);

				PdfPCell cell25 = new PdfPCell(new Paragraph("Project Name", font2));
				cell25.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell25.setFixedHeight(20f);
				cell25.setGrayFill(0.92f);
				table12.addCell(cell25);

				PdfPCell cell29 = new PdfPCell(new Paragraph(basicLps1.getProjectName(), font3));
				cell29.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table12.addCell(cell29);

				PdfPCell cell34 = new PdfPCell(new Paragraph("Type of Industry", font2));
				cell34.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell34.setGrayFill(0.92f);
				cell34.setFixedHeight(20f);
				table12.addCell(cell34);

				PdfPCell cell222 = new PdfPCell(new Paragraph(basicLps1.getIndustryType(), font3));
				cell222.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table12.addCell(cell222);

				PdfPCell cell233 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell233.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell233.setGrayFill(0.92f);
				cell233.setFixedHeight(20f);
				table12.addCell(cell233);

				PdfPCell cell244 = new PdfPCell(new Paragraph(basicLps1.getBuildingType(), font3));
				cell244.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table12.addCell(cell244);

				document.add(table12);

				PdfPTable table6 = new PdfPTable(pointColumnWidths20);
				table6.setWidthPercentage(100); // Width 100%
				// table3.setSpacingBefore(10f); // Space before table
				table6.setWidthPercentage(100);

				PdfPCell cell37 = new PdfPCell(new Paragraph("Building Dimension", font2));
				cell37.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell37.setGrayFill(0.92f);
				cell37.setFixedHeight(20f);
				table6.addCell(cell37);

				PdfPCell cell111 = new PdfPCell(new Paragraph("Length(m)", font2));
				cell111.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell111.setGrayFill(0.92f);
				cell111.setFixedHeight(20f);
				table6.addCell(cell111);

				PdfPCell cell101 = new PdfPCell(new Paragraph(basicLps1.getBuildingLength(), font3));
				cell101.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell101.setFixedHeight(20f);
				table6.addCell(cell101);

				PdfPCell cell155 = new PdfPCell(new Paragraph("Width(m)", font2));
				cell155.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell155.setGrayFill(0.92f);
				cell155.setFixedHeight(20f);
				table6.addCell(cell155);

				PdfPCell cell166 = new PdfPCell(new Paragraph(basicLps1.getBuildingWidth(), font3));
				cell166.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell166.setFixedHeight(20f);
				table6.addCell(cell166);

				PdfPCell cell165 = new PdfPCell(new Paragraph("Height(m)", font2));
				cell165.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell165.setGrayFill(0.92f);
				cell165.setFixedHeight(20f);
				table6.addCell(cell165);

				PdfPCell cell241 = new PdfPCell(new Paragraph(basicLps1.getBuildingHeight(), font3));
				cell241.setHorizontalAlignment(Element.ALIGN_CENTER);
				table6.addCell(cell241);

				document.add(table6);

				PdfPTable table8 = new PdfPTable(pointColumnWidths1);
				table8.setWidthPercentage(100); // Width 100%
				table4.setSpacingAfter(10f); // Space before table
				table8.setWidthPercentage(100);

				PdfPCell cell234 = new PdfPCell(new Paragraph("Level of protection", font2));
				cell234.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell234.setFixedHeight(20f);
				cell234.setGrayFill(0.92f);
				table8.addCell(cell234);

				PdfPCell cell261 = new PdfPCell(new Paragraph(basicLps1.getLevelOfProtection(), font3));
				cell261.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table8.addCell(cell261);

				PdfPCell cell270 = new PdfPCell(new Paragraph("Soil Resistivity (ohms)", font2));
				cell270.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell270.setGrayFill(0.92f);
				cell270.setFixedHeight(20f);
				table8.addCell(cell270);

				PdfPCell cell280 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(), font3));
				cell280.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table8.addCell(cell280);

				document.add(table8);

				Font font12 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPTable table2 = new PdfPTable(pointColumnWidths30);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingBefore(20f); // Space before table
				table2.setWidthPercentage(100);

				PdfPCell cell39 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell39.setFixedHeight(20f);
				cell39.setGrayFill(0.92f);
				table2.addCell(cell39);

				PdfPCell cell40 = new PdfPCell(new Paragraph("Description", font11));
				cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell40.setFixedHeight(20f);
				cell40.setGrayFill(0.92f);
				table2.addCell(cell40);

				PdfPCell cell411 = new PdfPCell(new Paragraph("Observation", font11));
				cell411.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell411.setFixedHeight(20f);
				cell411.setGrayFill(0.92f);
				table2.addCell(cell411);

				PdfPCell cell42 = new PdfPCell(new Paragraph("Remarks", font11));
				cell42.setGrayFill(0.92f);
				cell42.setFixedHeight(20f);
				cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell42);

				for (EarthStudDescription earthStud : earthStud1) {
					
					PdfPCell cell43 = new PdfPCell(new Paragraph("1", font12));
					cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell43.setGrayFill(0.92f);
					table2.addCell(cell43);

					PdfPCell cell44 = new PdfPCell(new Paragraph("Earth stud visibility if any", font12));
					cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell44.setFixedHeight(20f);
					cell44.setGrayFill(0.92f);
					table2.addCell(cell44);

					PdfPCell cell45 = new PdfPCell(new Paragraph(earthStud.getEarthStudVisibilityOb(), font12));
					cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell45.setFixedHeight(20f);
					table2.addCell(cell45);

					PdfPCell cell46 = new PdfPCell(new Paragraph(earthStud.getEarthStudVisibilityRem(), font12));
					cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell46);

					PdfPCell cell47 = new PdfPCell(new Paragraph("2", font12));
					cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell47.setGrayFill(0.92f);
					table2.addCell(cell47);

					PdfPCell cell48 = new PdfPCell(new Paragraph("Earth stud bend if any", font12));
					cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell48.setFixedHeight(20f);
					cell48.setGrayFill(0.92f);
					table2.addCell(cell48);

					PdfPCell cell49 = new PdfPCell(new Paragraph(earthStud.getEarthStudBendOb(), font12));
					cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell49.setFixedHeight(20f);
					table2.addCell(cell49);

					PdfPCell cell50 = new PdfPCell(new Paragraph(earthStud.getEarthStudBendRem(), font12));
					cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell50);

					PdfPCell cell51 = new PdfPCell(new Paragraph("3", font12));
					cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell51.setGrayFill(0.92f);
					table2.addCell(cell51);

					PdfPCell cell52 = new PdfPCell(new Paragraph("Proper bonding rail", font12));
					cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell52.setFixedHeight(20f);
					cell52.setGrayFill(0.92f);
					table2.addCell(cell52);

					PdfPCell cell53 = new PdfPCell(new Paragraph(earthStud.getProperBondingRailOb(), font12));
					cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell53.setFixedHeight(20f);
					table2.addCell(cell53);

					PdfPCell cell54 = new PdfPCell(new Paragraph(earthStud.getProperBondingRailRem(), font12));
					cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell54);

					PdfPCell cell55 = new PdfPCell(new Paragraph("4", font12));
					cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell55.setGrayFill(0.92f);
					table2.addCell(cell55);

					PdfPCell cell56 = new PdfPCell(new Paragraph("Physical damage of stud", font12));
					cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell56.setFixedHeight(20f);
					cell56.setGrayFill(0.92f);
					table2.addCell(cell56);

					PdfPCell cell57 = new PdfPCell(new Paragraph(earthStud.getPhysicalDamageStudOb(), font12));
					cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell57.setFixedHeight(20f);
					table2.addCell(cell57);

					PdfPCell cell58 = new PdfPCell(new Paragraph(earthStud.getPhysicalDamageStudRem(), font12));
					cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell58);

					PdfPCell cell59 = new PdfPCell(new Paragraph("5", font12));
					cell59.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell59.setGrayFill(0.92f);
					table2.addCell(cell59);

					PdfPCell cell60 = new PdfPCell(new Paragraph("Whether continuity exist in earth stud", font12));
					cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell60.setFixedHeight(20f);
					cell60.setGrayFill(0.92f);
					table2.addCell(cell60);

					PdfPCell cell61 = new PdfPCell(new Paragraph(earthStud.getContinutyExistaEarthOb(), font12));
					cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell61.setFixedHeight(20f);
					table2.addCell(cell61);

					PdfPCell cell62 = new PdfPCell(new Paragraph(earthStud.getContinutyExistaEarthRem(), font12));
					cell62.setHorizontalAlignment(Element.ALIGN_LEFT);
					table2.addCell(cell62);

					document.add(table2);

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

}
