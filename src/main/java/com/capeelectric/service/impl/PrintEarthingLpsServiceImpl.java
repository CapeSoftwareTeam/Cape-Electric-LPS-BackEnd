package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthElectrodeChamber;
import com.capeelectric.model.EarthingClamps;
import com.capeelectric.model.EarthingDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingSystem;
import com.capeelectric.service.PrintEarthingLpsService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintEarthingLpsServiceImpl implements PrintEarthingLpsService {


	@Override
	public List<EarthingLpsDescription> printEarthingLpsDetails(String userName, Integer basicLpsId,Optional<BasicLps> basicLpsDetails, Optional<EarthingLpsDescription> earthingLpsDetails)
			throws EarthingLpsException {

		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("EarthingLps.pdf"));

//				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				BasicLps basicLps1 = basicLpsDetails.get();

//				List<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository
//						.findByUserNameAndBasicLpsId(userName, basicLpsId);
				
				EarthingLpsDescription erthing = earthingLpsDetails.get();

				List<EarthingDescription> earthDesc1 = erthing.getEarthingDescription();

				List<EarthingClamps> earthClamps1 = erthing.getEarthingClamps();

				List<EarthElectrodeChamber> earthChamber1 = erthing.getEarthingElectrodeChamber();

				List<EarthingSystem> earthSystem1 = erthing.getEarthingSystem();

				document.open();

				float[] pointColumnWidths4 = { 100F };

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPTable table = new PdfPTable(pointColumnWidths4);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell = new PdfPCell(
						new Paragraph("Check list for Earthing System of LPS\r\n" + "as per IS/IEC 62305", font1));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				cell.setFixedHeight(30f);
				table.addCell(cell);
				document.add(table);

				float[] pointColumnWidths1 = { 30F, 70F };

				PdfPTable table1 = new PdfPTable(pointColumnWidths1);
				table1.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table1.setWidthPercentage(100);

				PdfPCell cell11 = new PdfPCell(new Paragraph("Client Name", font2));
				cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell11.setFixedHeight(20f);
				cell11.setGrayFill(0.92f);
				table1.addCell(cell11);

				PdfPCell cell2 = new PdfPCell(new Paragraph(basicLps1.getClientName(), font3));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell2);

				PdfPCell cell31 = new PdfPCell(new Paragraph("Project Name", font2));
				cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell31.setFixedHeight(20f);
				cell31.setGrayFill(0.92f);
				table1.addCell(cell31);

				PdfPCell cell41 = new PdfPCell(new Paragraph(basicLps1.getProjectName(), font3));
				cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell41);

				PdfPCell cell191 = new PdfPCell(new Paragraph("Type of Industry", font2));
				cell191.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell191.setGrayFill(0.92f);
				cell191.setFixedHeight(20f);
				table1.addCell(cell191);

				PdfPCell cell201 = new PdfPCell(new Paragraph(basicLps1.getIndustryType(), font3));
				cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell201);

				PdfPCell cell211 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell211.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell211.setGrayFill(0.92f);
				cell211.setFixedHeight(20f);
				table1.addCell(cell211);

				PdfPCell cell22 = new PdfPCell(new Paragraph(basicLps1.getBuildingType(), font3));
				cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
				table1.addCell(cell22);

				document.add(table1);

				float[] pointColumnWidths20 = { 38.5F, 15F, 15F, 15F, 15F, 15F, 15F };
				PdfPTable table31 = new PdfPTable(pointColumnWidths20);
				table31.setWidthPercentage(100); // Width 100%
				// table3.setSpacingBefore(10f); // Space before table
				table31.setWidthPercentage(100);

				PdfPCell cell23 = new PdfPCell(new Paragraph("Building Dimension", font2));
				cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell23.setGrayFill(0.92f);
				cell23.setFixedHeight(20f);
				table31.addCell(cell23);
				PdfPCell cell1111 = new PdfPCell(new Paragraph("Length(m)", font2));
				cell1111.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1111.setGrayFill(0.92f);
				cell1111.setFixedHeight(20f);
				table31.addCell(cell1111);
				PdfPCell cell112 = new PdfPCell(new Paragraph(basicLps1.getBuildingLength(), font3));
				cell112.setHorizontalAlignment(Element.ALIGN_CENTER);

				cell112.setFixedHeight(20f);
				table31.addCell(cell112);

				PdfPCell cell114 = new PdfPCell(new Paragraph("Width(m)", font2));
				cell114.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell114.setGrayFill(0.92f);
				cell114.setFixedHeight(20f);
				table31.addCell(cell114);

				PdfPCell cell115 = new PdfPCell(new Paragraph(basicLps1.getBuildingWidth(), font3));
				cell115.setHorizontalAlignment(Element.ALIGN_CENTER);

				cell115.setFixedHeight(20f);
				table31.addCell(cell115);

				PdfPCell cell113 = new PdfPCell(new Paragraph("Height(m)", font2));
				cell113.setHorizontalAlignment(Element.ALIGN_LEFT);
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
				cell251.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell251.setFixedHeight(20f);
				cell251.setGrayFill(0.92f);
				table4.addCell(cell251);

				PdfPCell cell26 = new PdfPCell(new Paragraph(basicLps1.getLevelOfProtection(), font3));
				cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
				table4.addCell(cell26);

				PdfPCell cell27 = new PdfPCell(new Paragraph("Soil Resistivity (ohms)", font2));
				cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell27.setGrayFill(0.92f);
				cell27.setFixedHeight(20f);
				table4.addCell(cell27);

				PdfPCell cell28 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(), font3));
				cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
				table4.addCell(cell28);

				document.add(table4);

				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnWidths30 = { 30F, 150F, 50F, 50F };

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

				Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPCell cell34 = new PdfPCell(new Paragraph("1", font));
				cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell34.setGrayFill(0.92f);
				table2.addCell(cell34);

				PdfPCell cell35 = new PdfPCell(new Paragraph("Type of Earthing (Type-A/Type-B/both)", font));
				cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell35.setGrayFill(0.92f);
				table2.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph(erthing.getEarthingTypeInOb(), font));
				cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph(erthing.getEarthingTypeInRem(), font));
				cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell37);

				PdfPCell cell40 = new PdfPCell(new Paragraph("2", font));
				cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell40.setGrayFill(0.92f);
				table2.addCell(cell40);

				PdfPCell cell411 = new PdfPCell(new Paragraph(
						"Check for bimetallic issue (connections between dissimilar metals are not allowed)", font));
				cell411.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell411.setGrayFill(0.92f);
				table2.addCell(cell411);

				PdfPCell cell42 = new PdfPCell(new Paragraph(erthing.getBimetallicIssueInOb(), font));
				cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell42);

				PdfPCell cell43 = new PdfPCell(new Paragraph(erthing.getBimetallicIssueInRem(), font));
				cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell43);

				PdfPCell cell44 = new PdfPCell(new Paragraph("3", font));
				cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell44.setGrayFill(0.92f);
				table2.addCell(cell44);

				PdfPCell cell45 = new PdfPCell(
						new Paragraph("Connections made by brazing/welding/crimping/seaming/screwing/bolting", font));
				cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell45.setGrayFill(0.92f);
				table2.addCell(cell45);

				PdfPCell cell46 = new PdfPCell(new Paragraph(erthing.getBrazingConnectInOb(), font));
				cell46.setHorizontalAlignment(Element.ALIGN_LEFT);

				cell46.setFixedHeight(20f);
				table2.addCell(cell46);

				PdfPCell cell47 = new PdfPCell(new Paragraph(erthing.getBrazingConnectInRem(), font));
				cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
				table2.addCell(cell47);
				document.add(table2);

				for (EarthingDescription earthDesc : earthDesc1) {

					PdfPTable table21 = typeAearthingSystem(font11, font, earthDesc);

					document.add(table21);

				}

				for (EarthingClamps earthClamps : earthClamps1) {

					PdfPTable table22 = earthingClamps(font11, font, earthClamps);

					document.add(table22);
				}

				for (EarthElectrodeChamber earthChamber : earthChamber1) {

					PdfPTable table202 = earthingChamber(font11, font, earthChamber);

					document.add(table202);

				}

				for (EarthingSystem earthSystem : earthSystem1) {

					PdfPTable table205 = typeBearthingSystem(font11, font, earthSystem);

					document.add(table205);

				}

				document.close();
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new EarthingLpsException("Invalid Inputs");
		}
		return null;
	}

	private PdfPTable typeBearthingSystem(Font font11, Font font, EarthingSystem earthSystem) {
		float[] pointColumnWidths2 = { 25F, 150F, 55F, 50F };

		PdfPTable table205 = new PdfPTable(pointColumnWidths2);
		table205.setWidthPercentage(100); // Width 100%
		table205.setSpacingBefore(20f); // Space before table
		table205.setWidthPercentage(100);

		PdfPCell cell205 = new PdfPCell(new Paragraph("7", font11));
		cell205.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell205.setGrayFill(0.92f);
		table205.addCell(cell205);

		PdfPCell cell206 = new PdfPCell(new Paragraph("Type-B earthing system", font11));
		cell206.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell206.setFixedHeight(20f);
		cell206.setColspan(3);
		cell206.setGrayFill(0.92f);
		table205.addCell(cell206);

		PdfPCell cell209 = new PdfPCell(new Paragraph("7.a", font));
		cell209.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell209.setGrayFill(0.92f);
		table205.addCell(cell209);

		PdfPCell cell210 = new PdfPCell(new Paragraph(
				"Earth electrode must be buried atleast 0.5m depth and 1m distance from external wall as closed loop.",
				font));
		cell210.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell210.setGrayFill(0.92f);
		table205.addCell(cell210);

		PdfPCell cell2111 = new PdfPCell(new Paragraph(earthSystem.getBuriedElectrodeOb(), font));
		cell2111.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell2111);

		PdfPCell cell212 = new PdfPCell(new Paragraph(earthSystem.getBuriedElectrodeRem(), font));
		cell212.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell212);

		PdfPCell cell213 = new PdfPCell(new Paragraph("7.b", font));
		cell213.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell213.setGrayFill(0.92f);
		table205.addCell(cell213);

		PdfPCell cell214 = new PdfPCell(new Paragraph("Depth of electrode from ground level (m)", font));
		cell214.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell214.setGrayFill(0.92f);
		table205.addCell(cell214);

		PdfPCell cell215 = new PdfPCell(new Paragraph(earthSystem.getDepthOfElectrodeOb(), font));
		cell215.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell215);

		PdfPCell cell216 = new PdfPCell(new Paragraph(earthSystem.getDepthOfElectrodeRem(), font));
		cell216.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell216);

		PdfPCell cell217 = new PdfPCell(new Paragraph("", font));
		cell217.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell217.setGrayFill(0.92f);
		table205.addCell(cell217);

		PdfPCell cell218 = new PdfPCell(new Paragraph("East", font));
		cell218.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell218.setGrayFill(0.92f);
		table205.addCell(cell218);

		PdfPCell cell219 = new PdfPCell(new Paragraph(earthSystem.getEarthOb(), font));
		cell219.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell219);

		PdfPCell cell220 = new PdfPCell(new Paragraph(earthSystem.getEarthRem(), font));
		cell220.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell220);

		PdfPCell cell221 = new PdfPCell(new Paragraph("", font));
		cell221.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell221.setGrayFill(0.92f);
		table205.addCell(cell221);

		PdfPCell cell222 = new PdfPCell(new Paragraph("West", font));
		cell222.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell222.setGrayFill(0.92f);
		table205.addCell(cell222);

		PdfPCell cell223 = new PdfPCell(new Paragraph(earthSystem.getWestOb(), font));
		cell223.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell223);

		PdfPCell cell224 = new PdfPCell(new Paragraph(earthSystem.getWestRem(), font));
		cell224.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell224);

		PdfPCell cell225 = new PdfPCell(new Paragraph("", font));
		cell225.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell225.setGrayFill(0.92f);
		table205.addCell(cell225);

		PdfPCell cell226 = new PdfPCell(new Paragraph("North", font));
		cell226.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell226.setGrayFill(0.92f);
		table205.addCell(cell226);

		PdfPCell cell227 = new PdfPCell(new Paragraph(earthSystem.getNorthOb(), font));
		cell227.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell227);

		PdfPCell cell228 = new PdfPCell(new Paragraph(earthSystem.getNorthRem(), font));
		cell228.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell228);

		PdfPCell cell229 = new PdfPCell(new Paragraph("", font));
		cell229.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell229.setGrayFill(0.92f);
		table205.addCell(cell229);

		PdfPCell cell230 = new PdfPCell(new Paragraph("South", font));
		cell230.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell230.setGrayFill(0.92f);
		table205.addCell(cell230);

		PdfPCell cell231 = new PdfPCell(new Paragraph(earthSystem.getSouthOb(), font));
		cell231.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell231);

		PdfPCell cell232 = new PdfPCell(new Paragraph(earthSystem.getSouthRem(), font));
		cell232.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell232);

		PdfPCell cell233 = new PdfPCell(new Paragraph("7.c", font));
		cell233.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell233.setGrayFill(0.92f);
		table205.addCell(cell233);

		PdfPCell cell234 = new PdfPCell(new Paragraph("Distance of ring earthing from wall (m)", font));
		cell234.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell234.setGrayFill(0.92f);
		table205.addCell(cell234);

		PdfPCell cell235 = new PdfPCell(new Paragraph(earthSystem.getRingEarthWallDistanceOb(), font));
		cell235.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell235);

		PdfPCell cell236 = new PdfPCell(new Paragraph(earthSystem.getRingEarthWallDistanceRem(), font));
		cell236.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell236);

		PdfPCell cell237 = new PdfPCell(new Paragraph("", font));
		cell237.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell237.setGrayFill(0.92f);
		table205.addCell(cell237);

		PdfPCell cell238 = new PdfPCell(new Paragraph("East", font));
		cell238.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell238.setGrayFill(0.92f);
		table205.addCell(cell238);

		PdfPCell cell239 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthEastOb(), font));
		cell239.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell239);

		PdfPCell cell240 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthEastRem(), font));
		cell240.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell240);

		PdfPCell cell241 = new PdfPCell(new Paragraph("", font));
		cell241.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell241.setGrayFill(0.92f);
		table205.addCell(cell241);

		PdfPCell cell242 = new PdfPCell(new Paragraph("West", font));
		cell242.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell242.setGrayFill(0.92f);
		table205.addCell(cell242);

		PdfPCell cell243 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthWestOb(), font));
		cell243.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell243);

		PdfPCell cell244 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthWestRem(), font));
		cell244.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell244);

		PdfPCell cell245 = new PdfPCell(new Paragraph("", font));
		cell245.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell245.setGrayFill(0.92f);
		table205.addCell(cell245);

		PdfPCell cell246 = new PdfPCell(new Paragraph("North", font));
		cell246.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell246.setGrayFill(0.92f);
		table205.addCell(cell246);

		PdfPCell cell247 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthNorthOb(), font));
		cell247.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell247);

		PdfPCell cell248 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthNorthRem(), font));
		cell248.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell248);

		PdfPCell cell249 = new PdfPCell(new Paragraph("", font));
		cell249.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell249.setGrayFill(0.92f);
		table205.addCell(cell249);

		PdfPCell cell250 = new PdfPCell(new Paragraph("South", font));
		cell250.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell250.setGrayFill(0.92f);
		table205.addCell(cell250);

		PdfPCell cell2511 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthSouthOb(), font));
		cell2511.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell2511);

		PdfPCell cell252 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthSouthRem(), font));
		cell252.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell252);

		PdfPCell cell253 = new PdfPCell(new Paragraph("7.d", font));
		cell253.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell253.setGrayFill(0.92f);
		table205.addCell(cell253);

		PdfPCell cell254 = new PdfPCell(
				new Paragraph("Joints made by brazing/welding/crimping/seaming/screwing /bolting", font));
		cell254.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell254.setGrayFill(0.92f);
		table205.addCell(cell254);

		PdfPCell cell255 = new PdfPCell(new Paragraph(earthSystem.getJointsMadeBrazingOb(), font));
		cell255.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell255);

		PdfPCell cell256 = new PdfPCell(new Paragraph(earthSystem.getJointsMadeBrazingRem(), font));
		cell256.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell256);

		PdfPCell cell257 = new PdfPCell(new Paragraph("7.e", font));
		cell257.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell257.setGrayFill(0.92f);
		table205.addCell(cell257);

		PdfPCell cell258 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell258.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell258.setGrayFill(0.92f);
		table205.addCell(cell258);

		PdfPCell cell259 = new PdfPCell(new Paragraph(earthSystem.getMaterialOfEartElectrodeOb(), font));
		cell259.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell259);

		PdfPCell cell260 = new PdfPCell(new Paragraph(earthSystem.getMaterialOfEartElectrodeRem(), font));
		cell260.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell260);

		PdfPCell cell261 = new PdfPCell(new Paragraph("7.f", font));
		cell261.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell261.setGrayFill(0.92f);
		table205.addCell(cell261);

		PdfPCell cell262 = new PdfPCell(new Paragraph("Size/cross section area of earth electrode (mm)", font));
		cell262.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell262.setGrayFill(0.92f);
		table205.addCell(cell262);

		PdfPCell cell263 = new PdfPCell(new Paragraph(earthSystem.getSizeOfEarthElectrodeOb(), font));
		cell263.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell263);

		PdfPCell cell264 = new PdfPCell(new Paragraph(earthSystem.getSizeOfEarthElectrodeRem(), font));
		cell264.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell264);

		PdfPCell cell265 = new PdfPCell(new Paragraph("7.g", font));
		cell265.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell265.setGrayFill(0.92f);
		table205.addCell(cell265);

		PdfPCell cell266 = new PdfPCell(new Paragraph("Maximum distance between earth electrode and wall (m)", font));
		cell266.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell266.setGrayFill(0.92f);
		table205.addCell(cell266);

		PdfPCell cell267 = new PdfPCell(new Paragraph(earthSystem.getMaximumDistanceEartElectrodeWalOb(), font));
		cell267.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell267);

		PdfPCell cell268 = new PdfPCell(new Paragraph(earthSystem.getMaximumDistanceEartElectrodeWalRem(), font));
		cell268.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell268);

		PdfPCell cell269 = new PdfPCell(new Paragraph("7.h", font));
		cell269.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell269.setGrayFill(0.92f);
		table205.addCell(cell269);

		PdfPCell cell270 = new PdfPCell(new Paragraph("Minimum distance between earth electrode and wall (m)", font));
		cell270.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell270.setGrayFill(0.92f);
		table205.addCell(cell270);

		PdfPCell cell271 = new PdfPCell(new Paragraph(earthSystem.getManimumDistanceEartElectrodeWalOb(), font));
		cell271.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell271);

		PdfPCell cell272 = new PdfPCell(new Paragraph(earthSystem.getManimumDistanceEartElectrodeWalRem(), font));
		cell272.setHorizontalAlignment(Element.ALIGN_LEFT);
		table205.addCell(cell272);
		return table205;
	}

	private PdfPTable earthingChamber(Font font11, Font font, EarthElectrodeChamber earthChamber) {

		float[] pointColumnWidths2 = { 25F, 150F, 55F, 50F };

		PdfPTable table202 = new PdfPTable(pointColumnWidths2);
		table202.setWidthPercentage(100); // Width 100%
		table202.setSpacingBefore(20f); // Space before table
		table202.setWidthPercentage(100);

		PdfPCell cell165 = new PdfPCell(new Paragraph("6", font11));
		cell165.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell165.setGrayFill(0.92f);
		table202.addCell(cell165);

		PdfPCell cell166 = new PdfPCell(new Paragraph("Earth electrode chambers", font11));
		cell166.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell166.setFixedHeight(20f);
		cell166.setColspan(3);
		cell166.setGrayFill(0.92f);
		table202.addCell(cell166);

		PdfPCell cell1241 = new PdfPCell(new Paragraph("\r\n" + "6.a", font));
		cell1241.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1241.setRowspan(2);
		cell1241.setGrayFill(0.92f);
		table202.addCell(cell1241);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font11));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table202.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(earthChamber.getLocationName(), font11));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table202.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font11));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table202.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(earthChamber.getLocationNumber().toString(), font11));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table202.addCell(cell211);

		PdfPCell cell169 = new PdfPCell(new Paragraph("6.b", font));
		cell169.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell169.setGrayFill(0.92f);
		table202.addCell(cell169);

		PdfPCell cell170 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell170.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell170.setGrayFill(0.92f);
		table202.addCell(cell170);

		PdfPCell cell171 = new PdfPCell(new Paragraph(earthChamber.getPhysicalInspeOb(), font));
		cell171.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell171);

		PdfPCell cell172 = new PdfPCell(new Paragraph(earthChamber.getPhysicalInspeRem(), font));
		cell172.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell172);

		PdfPCell cell173 = new PdfPCell(new Paragraph("6.c", font));
		cell173.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell173.setGrayFill(0.92f);
		table202.addCell(cell173);

		PdfPCell cell174 = new PdfPCell(new Paragraph("Type of chamber (concrete/metal/plastic)", font));
		cell174.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell174.setGrayFill(0.92f);
		table202.addCell(cell174);

		PdfPCell cell175 = new PdfPCell(new Paragraph(earthChamber.getChamberTypeOb(), font));
		cell175.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell175);

		PdfPCell cell176 = new PdfPCell(new Paragraph(earthChamber.getChamberTypeRem(), font));
		cell176.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell176);

		PdfPCell cell177 = new PdfPCell(new Paragraph("6.d", font));
		cell177.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell177.setGrayFill(0.92f);
		table202.addCell(cell177);

		PdfPCell cell178 = new PdfPCell(new Paragraph("Size of chamber (mm)", font));
		cell178.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell178.setGrayFill(0.92f);
		table202.addCell(cell178);

		PdfPCell cell179 = new PdfPCell(new Paragraph(earthChamber.getChamberSizeOb(), font));
		cell179.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell179);

		PdfPCell cell180 = new PdfPCell(new Paragraph(earthChamber.getChamberSizeRem(), font));
		cell180.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell180);

		PdfPCell cell181 = new PdfPCell(new Paragraph("6.e", font));
		cell181.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell181.setGrayFill(0.92f);
		table202.addCell(cell181);

		PdfPCell cell182 = new PdfPCell(new Paragraph("Maximum withstand load (KN)", font));
		cell182.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell182.setGrayFill(0.92f);
		table202.addCell(cell182);

		PdfPCell cell183 = new PdfPCell(new Paragraph(earthChamber.getMaximumWithStandLoadOb(), font));
		cell183.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell183);

		PdfPCell cell184 = new PdfPCell(new Paragraph(earthChamber.getMaximumWithStandLoadRem(), font));
		cell184.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell184);

		PdfPCell cell185 = new PdfPCell(new Paragraph("6.f", font));
		cell185.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell185.setGrayFill(0.92f);
		table202.addCell(cell185);

		PdfPCell cell186 = new PdfPCell(new Paragraph("Chamber is properly placed in soil", font));
		cell186.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell186.setGrayFill(0.92f);
		table202.addCell(cell186);

		PdfPCell cell187 = new PdfPCell(new Paragraph(earthChamber.getMaximumPlacedSoilOb(), font));
		cell187.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell187);

		PdfPCell cell188 = new PdfPCell(new Paragraph(earthChamber.getMaximumPlacedSoilRem(), font));
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

		PdfPCell cell1911 = new PdfPCell(new Paragraph(earthChamber.getTotalChamberNoOb(), font));
		cell1911.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell1911);

		PdfPCell cell192 = new PdfPCell(new Paragraph(earthChamber.getTotalChamberNoRem(), font));
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

		PdfPCell cell195 = new PdfPCell(new Paragraph(earthChamber.getInspectedChamberInOb(), font));
		cell195.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell195);

		PdfPCell cell196 = new PdfPCell(new Paragraph(earthChamber.getInspectedChamberInRem(), font));
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

		PdfPCell cell199 = new PdfPCell(new Paragraph(earthChamber.getInspectionPassedInOb(), font));
		cell199.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell199);

		PdfPCell cell200 = new PdfPCell(new Paragraph(earthChamber.getInspectionPassedInRem(), font));
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

		PdfPCell cell203 = new PdfPCell(new Paragraph(earthChamber.getInspectionFailedInOb(), font));
		cell203.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell203);

		PdfPCell cell204 = new PdfPCell(new Paragraph(earthChamber.getInspectionFailedInRem(), font));
		cell204.setHorizontalAlignment(Element.ALIGN_LEFT);
		table202.addCell(cell204);
		return table202;
	}

	private PdfPTable earthingClamps(Font font11, Font font, EarthingClamps earthClamps) {

		float[] pointColumnWidths2 = { 25F, 150F, 55F, 50F };

		PdfPTable table22 = new PdfPTable(pointColumnWidths2);
		table22.setWidthPercentage(100); // Width 100%
		table22.setSpacingBefore(20f); // Space before table
		table22.setWidthPercentage(100);

		PdfPCell cell124 = new PdfPCell(new Paragraph("5", font11));
		cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell124.setGrayFill(0.92f);
		table22.addCell(cell124);

		PdfPCell cell125 = new PdfPCell(new Paragraph("Clamps", font11));
		cell125.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell125.setFixedHeight(20f);
		cell125.setColspan(3);
		cell125.setGrayFill(0.92f);
		table22.addCell(cell125);

		PdfPCell cell1241 = new PdfPCell(new Paragraph("\r\n" + "5.a", font));
		cell1241.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1241.setRowspan(2);
		cell1241.setGrayFill(0.92f);
		table22.addCell(cell1241);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font11));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table22.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(earthClamps.getLocationName(), font11));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table22.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font11));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table22.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(earthClamps.getLocationNumber().toString(), font11));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table22.addCell(cell211);

		PdfPCell cell128 = new PdfPCell(new Paragraph("5.b", font));
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

		PdfPCell cell132 = new PdfPCell(new Paragraph("5.c", font));
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

		PdfPCell cell137 = new PdfPCell(new Paragraph("5.d", font));
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

		PdfPCell cell141 = new PdfPCell(new Paragraph("5.e", font));
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

		PdfPCell cell145 = new PdfPCell(new Paragraph("5.f", font));
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

		PdfPCell cell149 = new PdfPCell(new Paragraph("5.g", font));
		cell149.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell149.setGrayFill(0.92f);
		table22.addCell(cell149);

		PdfPCell cell150 = new PdfPCell(new Paragraph("Total number of clamps", font));
		cell150.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell150.setGrayFill(0.92f);
		table22.addCell(cell150);

		PdfPCell cell151 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInOb(), font));
		cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell151);

		PdfPCell cell152 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInRem(), font));
		cell152.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell152);

		PdfPCell cell153 = new PdfPCell(new Paragraph("5.h", font));
		cell153.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell153.setGrayFill(0.92f);
		table22.addCell(cell153);

		PdfPCell cell154 = new PdfPCell(new Paragraph("Number of clamps inspected", font));
		cell154.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell154.setGrayFill(0.92f);
		table22.addCell(cell154);

		PdfPCell cell155 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInOb(), font));
		cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell155);

		PdfPCell cell156 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInRem(), font));
		cell156.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell156);

		PdfPCell cell157 = new PdfPCell(new Paragraph("5.i", font));
		cell157.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell157.setGrayFill(0.92f);
		table22.addCell(cell157);

		PdfPCell cell158 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell158.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell158.setGrayFill(0.92f);
		table22.addCell(cell158);

		PdfPCell cell159 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInOb(), font));
		cell159.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell159);

		PdfPCell cell160 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInRem(), font));
		cell160.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell160);

		PdfPCell cell161 = new PdfPCell(new Paragraph("5.j", font));
		cell161.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell161.setGrayFill(0.92f);
		table22.addCell(cell161);

		PdfPCell cell162 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell162.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell162.setGrayFill(0.92f);
		table22.addCell(cell162);

		PdfPCell cell163 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInOb(), font));
		cell163.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell163);

		PdfPCell cell164 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInRem(), font));
		cell164.setHorizontalAlignment(Element.ALIGN_LEFT);
		table22.addCell(cell164);
		return table22;
	}

	private PdfPTable typeAearthingSystem(Font font11, Font font, EarthingDescription earthDesc) {

		float[] pointColumnWidths2 = { 25F, 150F, 55F, 50F };

		PdfPTable table21 = new PdfPTable(pointColumnWidths2);
		table21.setWidthPercentage(100); // Width 100%
		table21.setSpacingBefore(20f); // Space before table
		table21.setWidthPercentage(100);

		PdfPCell cell48 = new PdfPCell(new Paragraph("4", font11));
		cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table21.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("Type-A earthing system", font11));
		cell49.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell49.setFixedHeight(20f);
		cell49.setColspan(3);
		cell49.setGrayFill(0.92f);
		table21.addCell(cell49);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "4.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table21.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font11));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table21.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(earthDesc.getLocationName(), font11));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table21.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font11));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table21.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(earthDesc.getLocationNumber().toString(), font11));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table21.addCell(cell211);

		PdfPCell cell52 = new PdfPCell(new Paragraph("4.b", font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		table21.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("Soil resistivity (ohms)", font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell53.setGrayFill(0.92f);
		table21.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInOb(), font));
		cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInRem(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph("4.c", font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table21.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(
				new Paragraph("Earth pit work - Digging or Boring to ensure the soil nature", font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell57.setGrayFill(0.92f);
		table21.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(earthDesc.getEarthPitDigOb(), font));
		cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(earthDesc.getEarthPitDigRem(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph("4.d", font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table21.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(
				new Paragraph("Number of earth electrode should not be less than the number of down conductors", font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell61.setGrayFill(0.92f);
		table21.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInOb(), font));
		cell62.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInRem(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph("4.e", font));
		cell64.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell64.setGrayFill(0.92f);
		table21.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(
				new Paragraph("Ensure all down conductors are connected to earth termination system", font));
		cell65.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell65.setGrayFill(0.92f);
		table21.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInOb(), font));
		cell66.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInRem(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph("4.f", font));
		cell68.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell68.setGrayFill(0.92f);
		table21.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph(
				"Route of earthing conductor from test joint to earth electrode (under soil/under concrete/via gutter)",
				font));
		cell69.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell69.setGrayFill(0.92f);
		table21.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInOb(), font));
		cell70.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInRem(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph("4.g", font));
		cell72.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell72.setGrayFill(0.92f);
		table21.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph("compound filled properly upto gorund level", font));
		cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell73.setGrayFill(0.92f);
		table21.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInOb(), font));
		cell74.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInRem(), font));
		cell75.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph("4.h", font));
		cell76.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell76.setGrayFill(0.92f);
		table21.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(
				new Paragraph("Location of earth electrode (walk pathway/ vehicle runway)", font));
		cell77.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell77.setGrayFill(0.92f);
		table21.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLocationInOb(), font));
		cell78.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLocationInRem(), font));
		cell79.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph("4.i", font));
		cell80.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell80.setGrayFill(0.92f);
		table21.addCell(cell80);

		PdfPCell cell81 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell81.setGrayFill(0.92f);
		table21.addCell(cell81);

		PdfPCell cell82 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeMaterialInOb(), font));
		cell82.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell82);

		PdfPCell cell83 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeMaterialInRem(), font));
		cell83.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell83);

		PdfPCell cell84 = new PdfPCell(new Paragraph("4.j", font));
		cell84.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell84.setGrayFill(0.92f);
		table21.addCell(cell84);

		PdfPCell cell85 = new PdfPCell(new Paragraph("Size/cross section area of earth electrode (mm)", font));
		cell85.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell85.setGrayFill(0.92f);
		table21.addCell(cell85);

		PdfPCell cell86 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeSizeInOb(), font));
		cell86.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell86);

		PdfPCell cell87 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeSizeInRem(), font));
		cell87.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell87);

		PdfPCell cell88 = new PdfPCell(new Paragraph("4.k", font));
		cell88.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell88.setGrayFill(0.92f);
		table21.addCell(cell88);

		PdfPCell cell89 = new PdfPCell(new Paragraph("Length of earth electrode (m)", font));
		cell89.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell89.setGrayFill(0.92f);
		table21.addCell(cell89);

		PdfPCell cell90 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLengthingOb(), font));
		cell90.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell90);

		PdfPCell cell91 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLengthingRem(), font));
		cell91.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell91);

		PdfPCell cell92 = new PdfPCell(new Paragraph("4.l", font));
		cell92.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell92.setGrayFill(0.92f);
		table21.addCell(cell92);

		PdfPCell cell93 = new PdfPCell(new Paragraph("Maximum distance between earth electrode and wall (m)", font));
		cell93.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell93.setGrayFill(0.92f);
		table21.addCell(cell93);

		PdfPCell cell94 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInOb(), font));
		cell94.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell94);

		PdfPCell cell95 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInRem(), font));
		cell95.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell95);

		PdfPCell cell96 = new PdfPCell(new Paragraph("4.m", font));
		cell96.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell96.setGrayFill(0.92f);
		cell96.setGrayFill(0.92f);
		table21.addCell(cell96);

		PdfPCell cell97 = new PdfPCell(new Paragraph("Minimum distance between earth electrode and wall (m)", font));
		cell97.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell97);

		PdfPCell cell98 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManimumDistanceWallInOb(), font));
		cell98.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell98);

		PdfPCell cell99 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistWallInRem(), font));
		cell99.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell99);

		PdfPCell cell100 = new PdfPCell(new Paragraph("4.n", font));
		cell100.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell100.setGrayFill(0.92f);
		table21.addCell(cell100);

		PdfPCell cell101 = new PdfPCell(new Paragraph("Maximum distance between earth electrodes (m)", font));
		cell101.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell101.setGrayFill(0.92f);
		table21.addCell(cell101);

		PdfPCell cell102 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistOb(), font));
		cell102.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell102);

		PdfPCell cell103 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistRem(), font));
		cell103.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell103);

		PdfPCell cell104 = new PdfPCell(new Paragraph("4.o", font));
		cell104.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell104.setGrayFill(0.92f);
		table21.addCell(cell104);

		PdfPCell cell105 = new PdfPCell(new Paragraph("Minimum distance between earth electrodes (m)", font));
		cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell105.setGrayFill(0.92f);
		table21.addCell(cell105);

		PdfPCell cell106 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistOb(), font));
		cell106.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell106);

		PdfPCell cell107 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistRem(), font));
		cell107.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell107);

		PdfPCell cell108 = new PdfPCell(new Paragraph("4.p", font));
		cell108.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell108.setGrayFill(0.92f);
		table21.addCell(cell108);

		PdfPCell cell109 = new PdfPCell(new Paragraph("Total number of earthing electrodes", font));
		cell109.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell109.setGrayFill(0.92f);
		table21.addCell(cell109);

		PdfPCell cell110 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeOb(), font));
		cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell110);

		PdfPCell cell111 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeRem(), font));
		cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell111);

		PdfPCell cell1121 = new PdfPCell(new Paragraph("4.q", font));
		cell1121.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1121.setGrayFill(0.92f);
		table21.addCell(cell1121);

		PdfPCell cell1131 = new PdfPCell(new Paragraph("Number of earthing electrode inspected", font));
		cell1131.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1131.setGrayFill(0.92f);
		table21.addCell(cell1131);

		PdfPCell cell1141 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeOb(), font));
		cell1141.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell1141);

		PdfPCell cell1151 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeRem(), font));
		cell1151.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell1151);

		PdfPCell cell116 = new PdfPCell(new Paragraph("4.r", font));
		cell116.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell116.setGrayFill(0.92f);
		table21.addCell(cell116);

		PdfPCell cell117 = new PdfPCell(new Paragraph("Number of inspection passed", font));
		cell117.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell117.setGrayFill(0.92f);
		table21.addCell(cell117);

		PdfPCell cell118 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoOb(), font));
		cell118.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell118);

		PdfPCell cell119 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoRem(), font));
		cell119.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell119);

		PdfPCell cell120 = new PdfPCell(new Paragraph("4.s", font));
		cell120.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell120.setGrayFill(0.92f);
		table21.addCell(cell120);

		PdfPCell cell1211 = new PdfPCell(new Paragraph("Number of inspection failed", font));
		cell1211.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1211.setGrayFill(0.92f);
		table21.addCell(cell1211);

		PdfPCell cell122 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedNoOb(), font));
		cell122.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell122);

		PdfPCell cell123 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedRem(), font));
		cell123.setHorizontalAlignment(Element.ALIGN_LEFT);
		table21.addCell(cell123);
		return table21;
	}

}