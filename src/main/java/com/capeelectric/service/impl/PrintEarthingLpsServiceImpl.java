package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthElectrodeChamber;
import com.capeelectric.model.EarthingClamps;
import com.capeelectric.model.EarthingDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingSystem;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.PrintEarthingLpsService;
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
public class PrintEarthingLpsServiceImpl implements PrintEarthingLpsService {

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Autowired
	private EarthingLpsRepository earthingLpsRepository;

	@Override
	public List<EarthingLpsDescription> printEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException {

		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("BasicLps.pdf"));

				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				BasicLps basicLps1 = basicLps.get(0);

				List<EarthingLpsDescription> earthingLpsRepo = earthingLpsRepository
						.findByUserNameAndBasicLpsId(userName, basicLpsId);

				EarthingLpsDescription erthing = earthingLpsRepo.get(0);

				List<EarthingDescription> earthDesc1 = erthing.getEarthingDescription();
				EarthingDescription earthDesc = earthDesc1.get(0);

				List<EarthingClamps> earthClamps1 = erthing.getEarthingClamps();
				EarthingClamps earthClamps = earthClamps1.get(0);

				List<EarthElectrodeChamber> earthChamber1 = erthing.getEarthingElectrodeChamber();
				EarthElectrodeChamber earthChamber = earthChamber1.get(0);

				List<EarthingSystem> earthSystem1 = erthing.getEarthingSystem();
				EarthingSystem earthSystem = earthSystem1.get(0);

				document.open();

				float[] pointColumnWidths4 = { 100F };

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPTable table = new PdfPTable(pointColumnWidths4);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell = new PdfPCell(new Paragraph("Check List for lightning protection system", font1));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				cell.setFixedHeight(30f);
				table.addCell(cell);

				PdfPCell cell222 = new PdfPCell(new Paragraph("Earthing Details ", font1));
				cell222.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell222.setGrayFill(0.92f);
				cell222.setFixedHeight(20f);
				table.addCell(cell222);
				document.add(table);

				float[] pointColumnWidths1 = { 30F, 70F };

				PdfPTable table1 = new PdfPTable(pointColumnWidths1);
				table1.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table1.setWidthPercentage(100);

				PdfPCell cell11 = new PdfPCell(new Paragraph("Client Name", font2));
				cell11.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell11.setFixedHeight(20f);
				cell11.setGrayFill(0.92f);
				table1.addCell(cell11);

				PdfPCell cell2 = new PdfPCell(new Paragraph(basicLps1.getClientName(), font3));
				cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell2);

				PdfPCell cell31 = new PdfPCell(new Paragraph("Project Name", font2));
				cell31.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell31.setFixedHeight(20f);
				cell31.setGrayFill(0.92f);
				table1.addCell(cell31);

				PdfPCell cell41 = new PdfPCell(new Paragraph(basicLps1.getProjectName(), font3));
				cell41.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell41);

				PdfPCell cell191 = new PdfPCell(new Paragraph("Type of Industry", font2));
				cell191.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell191.setGrayFill(0.92f);
				cell191.setFixedHeight(20f);
				table1.addCell(cell191);

				PdfPCell cell201 = new PdfPCell(new Paragraph(basicLps1.getIndustryType(), font3));
				cell201.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell201);

				PdfPCell cell211 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell211.setGrayFill(0.92f);
				cell211.setFixedHeight(20f);
				table1.addCell(cell211);

				PdfPCell cell22 = new PdfPCell(new Paragraph(basicLps1.getBuildingType(), font3));
				cell22.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table1.addCell(cell22);

				document.add(table1);

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

				PdfPCell cell27 = new PdfPCell(new Paragraph("Soil Resistivity", font2));
				cell27.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell27.setGrayFill(0.92f);
				cell27.setFixedHeight(20f);
				table4.addCell(cell27);

				PdfPCell cell28 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(), font3));
				cell28.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
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
				cell311.setFixedHeight(25f);
				cell311.setGrayFill(0.92f);
				table2.addCell(cell311);

				PdfPCell cell32 = new PdfPCell(new Paragraph("Observation", font11));
				cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell32.setFixedHeight(25f);
				cell32.setGrayFill(0.92f);
				table2.addCell(cell32);

				PdfPCell cell33 = new PdfPCell(new Paragraph("Remarks", font11));
				cell33.setGrayFill(0.92f);
				cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.addCell(cell33);

				earthingSystem(erthing, earthDesc, earthClamps, earthChamber, earthSystem, table2);

				document.add(table2);
				document.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new EarthingLpsException("Invalid Inputs");
		}
		return null;
	}

	private void earthingSystem(EarthingLpsDescription erthing, EarthingDescription earthDesc,
			EarthingClamps earthClamps, EarthElectrodeChamber earthChamber, EarthingSystem earthSystem,
			PdfPTable table2) throws DocumentException, IOException {

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell34 = new PdfPCell(new Paragraph("1", font));
		cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell34.setFixedHeight(20f);
		cell34.setGrayFill(0.92f);
		table2.addCell(cell34);

		PdfPCell cell35 = new PdfPCell(new Paragraph("Type of Earthing (Type-A/Type-B/both)", font));
		cell35.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell35.setFixedHeight(20f);
		cell35.setGrayFill(0.92f);
		table2.addCell(cell35);

		PdfPCell cell36 = new PdfPCell(new Paragraph(erthing.getEarthingTypeInOb(), font));
		cell36.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell36.setFixedHeight(20f);

		table2.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph(erthing.getEarthingTypeInRem(), font));
		cell37.setFixedHeight(20f);
		cell37.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell37);

		PdfPCell cell40 = new PdfPCell(new Paragraph("2", font));
		cell40.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell40.setFixedHeight(20f);
		cell40.setGrayFill(0.92f);
		table2.addCell(cell40);

		PdfPCell cell41 = new PdfPCell(new Paragraph(
				"Check for bimetallic issue (connections between dissimilar metals are not allowed)", font));
		cell41.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell41.setFixedHeight(20f);
		cell41.setGrayFill(0.92f);
		table2.addCell(cell41);

		PdfPCell cell42 = new PdfPCell(new Paragraph(erthing.getBimetallicIssueInOb(), font));
		cell42.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell42.setFixedHeight(20f);
		cell42.setFixedHeight(20f);

		table2.addCell(cell42);

		PdfPCell cell43 = new PdfPCell(new Paragraph(erthing.getBimetallicIssueInRem(), font));
		cell43.setFixedHeight(20f);
		cell43.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell43);

		PdfPCell cell44 = new PdfPCell(new Paragraph("3", font));
		cell44.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell43.setFixedHeight(20f);
		cell44.setGrayFill(0.92f);
		table2.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(
				new Paragraph("Connections made by brazing/welding/crimping /seaming/screwing/bolting", font));
		cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell45.setFixedHeight(20f);
		cell45.setGrayFill(0.92f);
		table2.addCell(cell45);

		PdfPCell cell46 = new PdfPCell(new Paragraph(erthing.getBrazingConnectInOb(), font));
		cell46.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		cell46.setFixedHeight(20f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(erthing.getBrazingConnectInRem(), font));
		cell47.setFixedHeight(20f);
		cell47.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell47);

		PdfPCell cell48 = new PdfPCell(new Paragraph("4", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		cell48.setFixedHeight(20f);
		table2.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("Type-A earthing system", font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setFixedHeight(20f);
		cell49.setGrayFill(0.92f);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("", font));
		cell50.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell50.setFixedHeight(20f);

		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph("", font));
		cell51.setFixedHeight(20f);
		cell51.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph("4.a", font));
		cell52.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell52.setGrayFill(0.92f);
		cell52.setFixedHeight(20f);
		table2.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("Soil resistivity", font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setFixedHeight(20f);
		cell53.setGrayFill(0.92f);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInOb(), font));
		cell54.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell54.setFixedHeight(20f);
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(earthDesc.getSoilResistivityInRem(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph("4.b", font));
		cell56.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell56.setGrayFill(0.92f);
		table2.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph("Earth pit work - Digging or Boring to ensure the soil nature", font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setFixedHeight(20f);
		cell57.setGrayFill(0.92f);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph(earthDesc.getEarthPitDigOb(), font));
		cell58.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell58.setFixedHeight(20f);
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(earthDesc.getEarthPitDigRem(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph("4.c", font));
		cell60.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell60.setGrayFill(0.92f);
		table2.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph("Number of earth electrode should not be less than the  number of down conductors", font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setFixedHeight(20f);
		cell61.setGrayFill(0.92f);
		table2.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInOb(), font));
		cell62.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell62.setFixedHeight(20f);
		table2.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLesthanDownConductorInRem(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph("4.d", font));
		cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell64.setGrayFill(0.92f);
		table2.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph("Ensure all down conductors are connected to earth termination system", font));
		cell65.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell65.setFixedHeight(20f);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInOb(), font));
		cell66.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell66.setFixedHeight(20f);
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(earthDesc.getConnectedEarthTerminalInRem(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph("4.e", font));
		cell68.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell68.setGrayFill(0.92f);
		table2.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph("Route of earthing conductor from test joint to earth electrode (under soil/under concrete/via gutter)", font));
		cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell69.setFixedHeight(20f);
		cell69.setGrayFill(0.92f);
		table2.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInOb(), font));
		cell70.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(earthDesc.getTestJointEarthElectrodeInRem(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table2.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph("4.f", font));
		cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell72.setGrayFill(0.92f);
		table2.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph("Coumpound filled properly upto gorund level", font));
		cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell73.setFixedHeight(20f);
		cell73.setGrayFill(0.92f);
		table2.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInOb(), font));
		cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell74.setFixedHeight(20f);
		table2.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph(earthDesc.getGrountLevelComponentFilledInRem(), font));
		cell75.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph("4.g", font));
		cell76.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell76.setGrayFill(0.92f);
		table2.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(new Paragraph("Location of earth electrode (walk pathway/ vehicle runway)", font));
		cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell77.setFixedHeight(20f);
		cell77.setGrayFill(0.92f);
		table2.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLocationInOb(), font));
		cell78.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell78.setFixedHeight(20f);
		table2.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLocationInRem(), font));
		cell79.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph("4.h", font));
		cell80.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell80.setGrayFill(0.92f);
		table2.addCell(cell80);

		PdfPCell cell81 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell81.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell81.setFixedHeight(20f);
		cell81.setGrayFill(0.92f);
		table2.addCell(cell81);

		PdfPCell cell82 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeMaterialInOb(), font));
		cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell82.setFixedHeight(20f);
		table2.addCell(cell82);

		PdfPCell cell83 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeMaterialInRem(), font));
		cell83.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell83);

		PdfPCell cell84 = new PdfPCell(new Paragraph("4.i", font));
		cell84.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell84.setGrayFill(0.92f);
		table2.addCell(cell84);

		PdfPCell cell85 = new PdfPCell(new Paragraph("Size/cross section area of earth electrode", font));
		cell85.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell85.setFixedHeight(20f);
		cell85.setGrayFill(0.92f);
		table2.addCell(cell85);

		PdfPCell cell86 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeSizeInOb(), font));
		cell86.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell86.setFixedHeight(20f);
		table2.addCell(cell86);

		PdfPCell cell87 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeSizeInRem(), font));
		cell87.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell87);

		PdfPCell cell88 = new PdfPCell(new Paragraph("4.j", font));
		cell88.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell88.setGrayFill(0.92f);
		table2.addCell(cell88);

		PdfPCell cell89 = new PdfPCell(new Paragraph("Length of earth electrode", font));
		cell89.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell89.setFixedHeight(20f);
		cell89.setGrayFill(0.92f);
		table2.addCell(cell89);

		PdfPCell cell90 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLengthingOb(), font));
		cell90.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell90.setFixedHeight(20f);
		table2.addCell(cell90);

		PdfPCell cell91 = new PdfPCell(new Paragraph(earthDesc.getEarthElectrodeLengthingRem(), font));
		cell91.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell91);

		PdfPCell cell92 = new PdfPCell(new Paragraph("4.k", font));
		cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell92.setFixedHeight(20f);
		cell92.setGrayFill(0.92f);
		table2.addCell(cell92);

		PdfPCell cell93 = new PdfPCell(new Paragraph("Maximum distance between earth electrode and wall", font));
		cell93.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell93.setFixedHeight(20f);
		table2.addCell(cell93);

		PdfPCell cell94 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInOb(), font));
		cell94.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell94);

		PdfPCell cell95 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistWallInRem(), font));
		cell95.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell95.setFixedHeight(20f);
		cell95.setGrayFill(0.92f);
		table2.addCell(cell95);

		PdfPCell cell96 = new PdfPCell(new Paragraph("4.l", font));
		cell96.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell96.setFixedHeight(20f);
		table2.addCell(cell96);

		PdfPCell cell97 = new PdfPCell(new Paragraph("Minimum distance between earth electrode and wall", font));
		cell97.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell97);

		PdfPCell cell98 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManimumDistanceWallInOb(), font));
		cell98.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell98.setFixedHeight(20f);
		cell98.setGrayFill(0.92f);
		table2.addCell(cell98);

		PdfPCell cell99 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistWallInRem(), font));
		cell99.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell99.setFixedHeight(20f);
		table2.addCell(cell99);

		PdfPCell cell100 = new PdfPCell(new Paragraph("4.m", font));
		cell100.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell100);

		PdfPCell cell101 = new PdfPCell(new Paragraph("Maximum distance between earth electrodes", font));
		cell101.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell101.setFixedHeight(20f);
		cell101.setGrayFill(0.92f);
		table2.addCell(cell101);

		PdfPCell cell102 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistOb(), font));
		cell102.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell102.setFixedHeight(20f);
		table2.addCell(cell102);

		PdfPCell cell103 = new PdfPCell(new Paragraph(earthDesc.getEarthelectMaxiDistRem(), font));
		cell103.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell103);

		PdfPCell cell104 = new PdfPCell(new Paragraph("4.n", font));
		cell104.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell104.setFixedHeight(20f);
		cell104.setGrayFill(0.92f);
		table2.addCell(cell104);

		PdfPCell cell105 = new PdfPCell(new Paragraph("Minimum distance between earth electrodes", font));
		cell105.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell105.setFixedHeight(20f);
		table2.addCell(cell105);

		PdfPCell cell106 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistOb(), font));
		cell106.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell106);

		PdfPCell cell107 = new PdfPCell(new Paragraph(earthDesc.getEarthelectManiDistRem(), font));
		cell107.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell107.setFixedHeight(20f);
		cell107.setGrayFill(0.92f);
		table2.addCell(cell107);

		PdfPCell cell108 = new PdfPCell(new Paragraph("4.o", font));
		cell108.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell108.setFixedHeight(20f);
		table2.addCell(cell108);

		PdfPCell cell109 = new PdfPCell(new Paragraph("Total number of earthing electrodes", font));
		cell109.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell109);

		PdfPCell cell110 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeOb(), font));
		cell110.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell110.setFixedHeight(20f);
		cell110.setGrayFill(0.92f);
		table2.addCell(cell110);

		PdfPCell cell111 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeRem(), font));
		cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell111.setFixedHeight(20f);
		table2.addCell(cell111);

		PdfPCell cell112 = new PdfPCell(new Paragraph("4.p", font));
		cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell112);

		PdfPCell cell113 = new PdfPCell(new Paragraph("Number of earthing electrode inspected", font));
		cell113.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell113.setFixedHeight(20f);
		cell113.setGrayFill(0.92f);
		table2.addCell(cell113);

		PdfPCell cell114 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeOb(), font));
		cell114.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell114.setFixedHeight(20f);
		table2.addCell(cell114);

		PdfPCell cell115 = new PdfPCell(new Paragraph(earthDesc.getTotalNumberOfElectrodeRem(), font));
		cell115.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell115);

		PdfPCell cell116 = new PdfPCell(new Paragraph("4.q", font));
		cell116.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell116.setFixedHeight(20f);
		cell116.setGrayFill(0.92f);
		table2.addCell(cell116);

		PdfPCell cell117 = new PdfPCell(new Paragraph("Number of inspection passed", font));
		cell117.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell117.setFixedHeight(20f);
		table2.addCell(cell117);

		PdfPCell cell118 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoOb(), font));
		cell118.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell118);

		PdfPCell cell119 = new PdfPCell(new Paragraph(earthDesc.getInspectedPassedNoRem(), font));
		cell119.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell119.setFixedHeight(20f);
		cell119.setGrayFill(0.92f);
		table2.addCell(cell119);

		PdfPCell cell120 = new PdfPCell(new Paragraph("4.r", font));
		cell120.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell120.setFixedHeight(20f);
		table2.addCell(cell120);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Number of inspection failed", font));
		cell121.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell121);

		PdfPCell cell122 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedNoOb(), font));
		cell122.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell122.setFixedHeight(20f);
		cell122.setGrayFill(0.92f);
		table2.addCell(cell122);

		PdfPCell cell123 = new PdfPCell(new Paragraph(earthDesc.getInspectedFailedRem(), font));
		cell123.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell123.setFixedHeight(20f);
		table2.addCell(cell123);

		
		PdfPCell cell124 = new PdfPCell(new Paragraph("5", font));
		cell124.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell124);

		PdfPCell cell125 = new PdfPCell(new Paragraph("Clamps", font));
		cell125.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell125.setFixedHeight(20f);
		cell125.setGrayFill(0.92f);
		table2.addCell(cell125);

		PdfPCell cell126 = new PdfPCell(new Paragraph("", font));
		cell126.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell126.setFixedHeight(20f);
		table2.addCell(cell126);

		PdfPCell cell127 = new PdfPCell(new Paragraph("", font));
		cell127.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell127);

		PdfPCell cell128 = new PdfPCell(new Paragraph("5.a", font));
		cell128.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell128.setGrayFill(0.92f);
		table2.addCell(cell128);

		PdfPCell cell129 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell129.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell129.setFixedHeight(20f);
		cell129.setGrayFill(0.92f);
		table2.addCell(cell129);

		PdfPCell cell130 = new PdfPCell(new Paragraph(earthClamps.getPhysicalInspectionInOb(), font));
		cell130.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell130.setFixedHeight(20f);
		table2.addCell(cell130);

		PdfPCell cell131 = new PdfPCell(new Paragraph(earthClamps.getPsysicalInspectionInRem(), font));
		cell131.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell131);
		
		PdfPCell cell132 = new PdfPCell(new Paragraph("5.b", font));
		cell132.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell132.setGrayFill(0.92f);
		table2.addCell(cell132);

		PdfPCell cell133 = new PdfPCell(new Paragraph("Clamp is firmly fixed/mounted on to earth electrode", font));
		cell133.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell133.setFixedHeight(20f);
		cell133.setGrayFill(0.92f);
		table2.addCell(cell133);

		PdfPCell cell134 = new PdfPCell(new Paragraph(earthClamps.getClampsFirmlyOb(), font));
		cell134.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell134.setFixedHeight(20f);
		table2.addCell(cell134);

		PdfPCell cell136 = new PdfPCell(new Paragraph(earthClamps.getClampsFirmlyRem(), font));
		cell136.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell136);
		
		PdfPCell cell137 = new PdfPCell(new Paragraph("5.c", font));
		cell137.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell137.setGrayFill(0.92f);
		table2.addCell(cell137);

		PdfPCell cell138 = new PdfPCell(new Paragraph("Interconnection of earthing conductor with clamp (tight/loose/corroded)", font));
		cell138.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell138.setFixedHeight(20f);
		cell138.setGrayFill(0.92f);
		table2.addCell(cell138);

		PdfPCell cell139 = new PdfPCell(new Paragraph(earthClamps.getInterConnectOfEarthClampInOb(), font));
		cell139.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell139.setFixedHeight(20f);
		table2.addCell(cell139);

		PdfPCell cell140 = new PdfPCell(new Paragraph(earthClamps.getInterConnectOfEarthClampInRem(), font));
		cell140.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell140);
		
		PdfPCell cell141 = new PdfPCell(new Paragraph("5.d", font));
		cell141.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell141.setGrayFill(0.92f);
		table2.addCell(cell141);

		PdfPCell cell142 = new PdfPCell(new Paragraph("Type of clamp", font));
		cell142.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell142.setFixedHeight(20f);
		cell142.setGrayFill(0.92f);
		table2.addCell(cell142);

		PdfPCell cell143 = new PdfPCell(new Paragraph(earthClamps.getTypeOfClampsInOb(), font));
		cell143.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell143.setFixedHeight(20f);
		table2.addCell(cell143);

		PdfPCell cell144 = new PdfPCell(new Paragraph(earthClamps.getTypeOfClampsInRem(), font));
		cell144.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell144);
		
		PdfPCell cell145 = new PdfPCell(new Paragraph("5.e", font));
		cell145.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell145.setGrayFill(0.92f);
		table2.addCell(cell145);

		PdfPCell cell146 = new PdfPCell(new Paragraph("Material of clamp", font));
		cell146.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell146.setFixedHeight(20f);
		cell146.setGrayFill(0.92f);
		table2.addCell(cell146);

		PdfPCell cell147 = new PdfPCell(new Paragraph(earthClamps.getMaterialOfClampsInOb(), font));
		cell147.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell147.setFixedHeight(20f);
		table2.addCell(cell147);

		PdfPCell cell148 = new PdfPCell(new Paragraph(earthClamps.getMaterialOfClampsInRem(), font));
		cell148.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell148);
		
		PdfPCell cell149 = new PdfPCell(new Paragraph("5.f", font));
		cell149.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell149.setGrayFill(0.92f);
		table2.addCell(cell149);

		PdfPCell cell150 = new PdfPCell(new Paragraph("Total number of clamps", font));
		cell150.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell150.setFixedHeight(20f);
		cell150.setGrayFill(0.92f);
		table2.addCell(cell150);

		PdfPCell cell151 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInOb(), font));
		cell151.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell151.setFixedHeight(20f);
		table2.addCell(cell151);

		PdfPCell cell152 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInRem(), font));
		cell152.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell152);
		
		PdfPCell cell153 = new PdfPCell(new Paragraph("5.g", font));
		cell153.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell153.setGrayFill(0.92f);
		table2.addCell(cell153);

		PdfPCell cell154 = new PdfPCell(new Paragraph("Number of clamps inspected", font));
		cell154.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell154.setFixedHeight(20f);
		cell154.setGrayFill(0.92f);
		table2.addCell(cell154);

		PdfPCell cell155 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInOb(), font));
		cell155.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell155.setFixedHeight(20f);
		table2.addCell(cell155);

		PdfPCell cell156 = new PdfPCell(new Paragraph(earthClamps.getTotalNoClampsInRem(), font));
		cell156.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell156);
		
		PdfPCell cell157 = new PdfPCell(new Paragraph("5.h", font));
		cell157.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell157.setGrayFill(0.92f);
		table2.addCell(cell157);

		PdfPCell cell158 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell158.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell158.setFixedHeight(20f);
		cell158.setGrayFill(0.92f);
		table2.addCell(cell158);

		PdfPCell cell159 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInOb(), font));
		cell159.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell159.setFixedHeight(20f);
		table2.addCell(cell159);

		PdfPCell cell160 = new PdfPCell(new Paragraph(earthClamps.getInspectionPassedInRem(), font));
		cell160.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell160);
		
		PdfPCell cell161 = new PdfPCell(new Paragraph("5.i", font));
		cell161.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell161.setGrayFill(0.92f);
		table2.addCell(cell161);

		PdfPCell cell162 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell162.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell162.setFixedHeight(20f);
		cell162.setGrayFill(0.92f);
		table2.addCell(cell162);

		PdfPCell cell163 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInOb(), font));
		cell163.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell163.setFixedHeight(20f);
		table2.addCell(cell163);

		PdfPCell cell164 = new PdfPCell(new Paragraph(earthClamps.getInspectionFailedInRem(), font));
		cell164.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell164);
		
		PdfPCell cell165 = new PdfPCell(new Paragraph("6", font));
		cell165.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell165.setGrayFill(0.92f);
		table2.addCell(cell165);

		PdfPCell cell166 = new PdfPCell(new Paragraph("Earth electrode chambers", font));
		cell166.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell166.setFixedHeight(20f);
		cell166.setGrayFill(0.92f);
		table2.addCell(cell166);

		PdfPCell cell167 = new PdfPCell(new Paragraph("", font));
		cell167.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell167.setFixedHeight(20f);
		table2.addCell(cell167);

		PdfPCell cell168 = new PdfPCell(new Paragraph("", font));
		cell168.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell168);
		
		
		PdfPCell cell169 = new PdfPCell(new Paragraph("6.a", font));
		cell169.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell169.setGrayFill(0.92f);
		table2.addCell(cell169);

		PdfPCell cell170 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell170.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell170.setFixedHeight(20f);
		cell170.setGrayFill(0.92f);
		table2.addCell(cell170);

		PdfPCell cell171 = new PdfPCell(new Paragraph(earthChamber.getPhysicalInspeOb(), font));
		cell171.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell171.setFixedHeight(20f);
		table2.addCell(cell171);

		PdfPCell cell172 = new PdfPCell(new Paragraph(earthChamber.getPhysicalInspeRem(), font));
		cell172.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell172);
		
		PdfPCell cell173 = new PdfPCell(new Paragraph("6.b", font));
		cell173.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell173.setGrayFill(0.92f);
		table2.addCell(cell72);

		PdfPCell cell174 = new PdfPCell(new Paragraph("Type of chamber (concrete/metal/plastic)", font));
		cell174.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell174.setFixedHeight(20f);
		cell174.setGrayFill(0.92f);
		table2.addCell(cell174);

		PdfPCell cell175 = new PdfPCell(new Paragraph(earthChamber.getChamberTypeOb(), font));
		cell175.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell175.setFixedHeight(20f);
		table2.addCell(cell175);

		PdfPCell cell176 = new PdfPCell(new Paragraph(earthChamber.getChamberTypeRem(), font));
		cell176.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell176);
		
		PdfPCell cell177 = new PdfPCell(new Paragraph("6.c", font));
		cell177.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell177.setGrayFill(0.92f);
		table2.addCell(cell177);

		PdfPCell cell178 = new PdfPCell(new Paragraph("Size of chamber", font));
		cell178.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell178.setFixedHeight(20f);
		cell178.setGrayFill(0.92f);
		table2.addCell(cell178);

		PdfPCell cell179 = new PdfPCell(new Paragraph(earthChamber.getChamberSizeOb(), font));
		cell179.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell179.setFixedHeight(20f);
		table2.addCell(cell179);

		PdfPCell cell180 = new PdfPCell(new Paragraph(earthChamber.getChamberSizeRem(), font));
		cell180.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell180);
		
		PdfPCell cell181 = new PdfPCell(new Paragraph("6.d", font));
		cell181.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell181.setGrayFill(0.92f);
		table2.addCell(cell181);

		PdfPCell cell182 = new PdfPCell(new Paragraph("Maximum withstand load"));
		cell182.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell182.setFixedHeight(20f);
		cell182.setGrayFill(0.92f);
		table2.addCell(cell182);

		PdfPCell cell183 = new PdfPCell(new Paragraph(earthChamber.getMaximumWithStandLoadOb(), font));
		cell183.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell183.setFixedHeight(20f);
		table2.addCell(cell183);

		PdfPCell cell184 = new PdfPCell(new Paragraph(earthChamber.getMaximumWithStandLoadRem(), font));
		cell184.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell184);
		
		PdfPCell cell185 = new PdfPCell(new Paragraph("6.e", font));
		cell185.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell185.setGrayFill(0.92f);
		table2.addCell(cell185);

		PdfPCell cell186 = new PdfPCell(new Paragraph("Chamber is properly placed in soil", font));
		cell186.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell186.setFixedHeight(20f);
		cell186.setGrayFill(0.92f);
		table2.addCell(cell186);

		PdfPCell cell187 = new PdfPCell(new Paragraph(earthChamber.getMaximumPlacedSoilOb(), font));
		cell187.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell187.setFixedHeight(20f);
		table2.addCell(cell187);

		PdfPCell cell188 = new PdfPCell(new Paragraph(earthChamber.getMaximumPlacedSoilRem(), font));
		cell188.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell188);
		
		PdfPCell cell189 = new PdfPCell(new Paragraph("6.f", font));
		cell189.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell189.setGrayFill(0.92f);
		table2.addCell(cell189);

		PdfPCell cell190 = new PdfPCell(new Paragraph("Total number of chambers", font));
		cell190.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell190.setFixedHeight(20f);
		cell190.setGrayFill(0.92f);
		table2.addCell(cell190);

		PdfPCell cell191 = new PdfPCell(new Paragraph(earthChamber.getTotalChamberNoOb(), font));
		cell191.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell191.setFixedHeight(20f);
		table2.addCell(cell191);

		PdfPCell cell192 = new PdfPCell(new Paragraph(earthChamber.getTotalChamberNoRem(), font));
		cell192.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell192);
		
		PdfPCell cell193 = new PdfPCell(new Paragraph("6.g", font));
		cell193.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell193.setGrayFill(0.92f);
		table2.addCell(cell193);

		PdfPCell cell194 = new PdfPCell(new Paragraph("Number of chambers inspected", font));
		cell194.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell194.setFixedHeight(20f);
		cell194.setGrayFill(0.92f);
		table2.addCell(cell194);

		PdfPCell cell195 = new PdfPCell(new Paragraph(earthChamber.getInspectedChamberInOb(), font));
		cell195.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell195.setFixedHeight(20f);
		table2.addCell(cell195);

		PdfPCell cell196 = new PdfPCell(new Paragraph(earthChamber.getInspectedChamberInRem(), font));
		cell196.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell196);
		
		PdfPCell cell197 = new PdfPCell(new Paragraph("6.h", font));
		cell197.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell197.setGrayFill(0.92f);
		table2.addCell(cell197);

		PdfPCell cell198 = new PdfPCell(new Paragraph("Number of inspections passed"));
		cell198.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell198.setFixedHeight(20f);
		cell198.setGrayFill(0.92f);
		table2.addCell(cell198);

		PdfPCell cell199 = new PdfPCell(new Paragraph(earthChamber.getInspectionPassedInOb(), font));
		cell199.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell199.setFixedHeight(20f);
		table2.addCell(cell199);

		PdfPCell cell200 = new PdfPCell(new Paragraph(earthChamber.getInspectionPassedInRem(), font));
		cell200.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell200);
		
		PdfPCell cell201 = new PdfPCell(new Paragraph("4.i", font));
		cell201.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell201.setGrayFill(0.92f);
		table2.addCell(cell201);

		PdfPCell cell202 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell202.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell202.setFixedHeight(20f);
		cell202.setGrayFill(0.92f);
		table2.addCell(cell202);

		PdfPCell cell203 = new PdfPCell(new Paragraph(earthChamber.getInspectionFailedInOb(), font));
		cell203.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell203.setFixedHeight(20f);
		table2.addCell(cell203);

		PdfPCell cell204 = new PdfPCell(new Paragraph(earthChamber.getInspectionFailedInRem(), font));
		cell204.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell204);
		

		//Type-B earthing system start
		
		PdfPCell cell205 = new PdfPCell(new Paragraph("7", font));
		cell205.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell205.setGrayFill(0.92f);
		table2.addCell(cell205);

		PdfPCell cell206 = new PdfPCell(new Paragraph("Type-B earthing system", font));
		cell206.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell206.setFixedHeight(20f);
		cell206.setGrayFill(0.92f);
		table2.addCell(cell206);

		PdfPCell cell207 = new PdfPCell(new Paragraph("", font));
		cell207.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell207.setFixedHeight(20f);
		table2.addCell(cell207);

		PdfPCell cell208 = new PdfPCell(new Paragraph("", font));
		cell208.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell208);
		
		PdfPCell cell209 = new PdfPCell(new Paragraph("7.a", font));
		cell209.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell209.setGrayFill(0.92f);
		table2.addCell(cell209);

		PdfPCell cell210 = new PdfPCell(new Paragraph("Earth electrode must be buried atleast 0.5m depth and 1m distance from external wall as closed loop.", font));
		cell210.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell210.setFixedHeight(20f);
		cell210.setGrayFill(0.92f);
		table2.addCell(cell210);

		PdfPCell cell211 = new PdfPCell(new Paragraph(earthSystem.getBuriedElectrodeOb(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell211.setFixedHeight(20f);
		table2.addCell(cell211);

		PdfPCell cell212 = new PdfPCell(new Paragraph(earthSystem.getBuriedElectrodeRem(), font));
		cell212.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell212);
		
		PdfPCell cell213 = new PdfPCell(new Paragraph("7.b", font));
		cell213.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell213.setGrayFill(0.92f);
		table2.addCell(cell213);

		PdfPCell cell214 = new PdfPCell(new Paragraph("Depth of electrode from ground level", font));
		cell214.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell214.setFixedHeight(20f);
		cell214.setGrayFill(0.92f);
		table2.addCell(cell214);

		PdfPCell cell215 = new PdfPCell(new Paragraph(earthSystem.getDepthOfElectrodeOb(), font));
		cell215.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell215.setFixedHeight(20f);
		table2.addCell(cell215);

		PdfPCell cell216 = new PdfPCell(new Paragraph(earthSystem.getDepthOfElectrodeRem(), font));
		cell216.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell216);
		
		PdfPCell cell217 = new PdfPCell(new Paragraph("", font));
		cell217.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell217.setGrayFill(0.92f);
		table2.addCell(cell217);

		PdfPCell cell218 = new PdfPCell(new Paragraph("East", font));
		cell218.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell218.setFixedHeight(20f);
		cell218.setGrayFill(0.92f);
		table2.addCell(cell218);

		PdfPCell cell219 = new PdfPCell(new Paragraph(earthSystem.getEarthOb(), font));
		cell219.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell219.setFixedHeight(20f);
		table2.addCell(cell219);

		PdfPCell cell220 = new PdfPCell(new Paragraph(earthSystem.getEarthRem(), font));
		cell220.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell220);
		
		PdfPCell cell221 = new PdfPCell(new Paragraph("", font));
		cell221.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell221.setGrayFill(0.92f);
		table2.addCell(cell221);

		PdfPCell cell222 = new PdfPCell(new Paragraph("West", font));
		cell222.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell222.setFixedHeight(20f);
		cell222.setGrayFill(0.92f);
		table2.addCell(cell222);

		PdfPCell cell223 = new PdfPCell(new Paragraph(earthSystem.getWestOb(), font));
		cell223.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell223.setFixedHeight(20f);
		table2.addCell(cell223);

		PdfPCell cell224 = new PdfPCell(new Paragraph(earthSystem.getWestRem(), font));
		cell224.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell224);
		
		PdfPCell cell225 = new PdfPCell(new Paragraph("", font));
		cell225.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell225.setGrayFill(0.92f);
		table2.addCell(cell225);

		PdfPCell cell226 = new PdfPCell(new Paragraph("North", font));
		cell226.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell226.setFixedHeight(20f);
		cell226.setGrayFill(0.92f);
		table2.addCell(cell226);

		PdfPCell cell227 = new PdfPCell(new Paragraph(earthSystem.getNorthOb(), font));
		cell227.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell227.setFixedHeight(20f);
		table2.addCell(cell227);

		PdfPCell cell228 = new PdfPCell(new Paragraph(earthSystem.getNorthRem(), font));
		cell228.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell228);
		
		PdfPCell cell229 = new PdfPCell(new Paragraph("", font));
		cell229.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell229.setGrayFill(0.92f);
		table2.addCell(cell229);

		PdfPCell cell230 = new PdfPCell(new Paragraph("South", font));
		cell230.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell230.setFixedHeight(20f);
		cell230.setGrayFill(0.92f);
		table2.addCell(cell230);

		PdfPCell cell231 = new PdfPCell(new Paragraph(earthSystem.getSouthOb(), font));
		cell231.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell231.setFixedHeight(20f);
		table2.addCell(cell231);

		PdfPCell cell232 = new PdfPCell(new Paragraph(earthSystem.getSouthRem(), font));
		cell232.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell232);
		
		
		PdfPCell cell233 = new PdfPCell(new Paragraph("7.c", font));
		cell233.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell233.setGrayFill(0.92f);
		table2.addCell(cell233);

		PdfPCell cell234 = new PdfPCell(new Paragraph("Distance of ring earthing from wall", font));
		cell234.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell234.setFixedHeight(20f);
		cell234.setGrayFill(0.92f);
		table2.addCell(cell234);

		PdfPCell cell235 = new PdfPCell(new Paragraph(earthSystem.getRingEarthWallDistanceOb(), font));
		cell235.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell235.setFixedHeight(20f);
		table2.addCell(cell235);

		PdfPCell cell236 = new PdfPCell(new Paragraph(earthSystem.getRingEarthWallDistanceRem(), font));
		cell236.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell236);
		
		PdfPCell cell237 = new PdfPCell(new Paragraph("", font));
		cell237.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell237.setGrayFill(0.92f);
		table2.addCell(cell237);

		PdfPCell cell238 = new PdfPCell(new Paragraph("East", font));
		cell238.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell238.setFixedHeight(20f);
		cell238.setGrayFill(0.92f);
		table2.addCell(cell238);

		PdfPCell cell239 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthEastOb(), font));
		cell239.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell239.setFixedHeight(20f);
		table2.addCell(cell239);

		PdfPCell cell240 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthEastRem(), font));
		cell240.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell240);
		
		PdfPCell cell241 = new PdfPCell(new Paragraph("", font));
		cell241.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell241.setGrayFill(0.92f);
		table2.addCell(cell241);

		PdfPCell cell242 = new PdfPCell(new Paragraph("West", font));
		cell242.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell242.setFixedHeight(20f);
		cell242.setGrayFill(0.92f);
		table2.addCell(cell242);

		PdfPCell cell243 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthWestOb(), font));
		cell243.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell243.setFixedHeight(20f);
		table2.addCell(cell243);

		PdfPCell cell244 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthWestRem(), font));
		cell240.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell240);
		
		PdfPCell cell245 = new PdfPCell(new Paragraph("", font));
		cell245.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell245.setGrayFill(0.92f);
		table2.addCell(cell245);

		PdfPCell cell246 = new PdfPCell(new Paragraph("North", font));
		cell246.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell246.setFixedHeight(20f);
		cell246.setGrayFill(0.92f);
		table2.addCell(cell246);

		PdfPCell cell247 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthNorthOb(), font));
		cell247.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell247.setFixedHeight(20f);
		table2.addCell(cell247);

		PdfPCell cell248 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthNorthRem(), font));
		cell248.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell248);
		
		PdfPCell cell249 = new PdfPCell(new Paragraph("", font));
		cell249.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell249.setGrayFill(0.92f);
		table2.addCell(cell249);

		PdfPCell cell250 = new PdfPCell(new Paragraph("South", font));
		cell250.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell250.setFixedHeight(20f);
		cell250.setGrayFill(0.92f);
		table2.addCell(cell250);

		PdfPCell cell251 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthSouthOb(), font));
		cell251.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell251.setFixedHeight(20f);
		table2.addCell(cell251);

		PdfPCell cell252 = new PdfPCell(new Paragraph(earthSystem.getRingWallEarthSouthRem(), font));
		cell252.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell252);
		
		PdfPCell cell253 = new PdfPCell(new Paragraph("7.d", font));
		cell253.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell253.setGrayFill(0.92f);
		table2.addCell(cell253);

		PdfPCell cell254 = new PdfPCell(new Paragraph("Joints made by brazing/welding/crimping/seaming/screwing /bolting", font));
		cell254.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell254.setFixedHeight(20f);
		cell254.setGrayFill(0.92f);
		table2.addCell(cell254);

		PdfPCell cell255 = new PdfPCell(new Paragraph(earthSystem.getJointsMadeBrazingOb(), font));
		cell255.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell255.setFixedHeight(20f);
		table2.addCell(cell255);

		PdfPCell cell256 = new PdfPCell(new Paragraph(earthSystem.getJointsMadeBrazingRem(), font));
		cell256.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell256);
		
		PdfPCell cell257 = new PdfPCell(new Paragraph("7.e", font));
		cell257.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell257.setGrayFill(0.92f);
		table2.addCell(cell257);

		PdfPCell cell258 = new PdfPCell(new Paragraph("Material of earth electrode", font));
		cell258.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell258.setFixedHeight(20f);
		cell258.setGrayFill(0.92f);
		table2.addCell(cell258);

		PdfPCell cell259 = new PdfPCell(new Paragraph(earthSystem.getMaterialOfEartElectrodeOb(), font));
		cell259.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell259.setFixedHeight(20f);
		table2.addCell(cell259);

		PdfPCell cell260 = new PdfPCell(new Paragraph(earthSystem.getMaterialOfEartElectrodeRem(), font));
		cell260.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell260);
		
		PdfPCell cell261 = new PdfPCell(new Paragraph("7.f", font));
		cell261.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell261.setGrayFill(0.92f);
		table2.addCell(cell261);

		PdfPCell cell262 = new PdfPCell(new Paragraph("Size/cross section area of earth electrode", font));
		cell262.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell262.setFixedHeight(20f);
		cell262.setGrayFill(0.92f);
		table2.addCell(cell262);

		PdfPCell cell263 = new PdfPCell(new Paragraph(earthSystem.getSizeOfEarthElectrodeOb(), font));
		cell263.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell263.setFixedHeight(20f);
		table2.addCell(cell263);

		PdfPCell cell264 = new PdfPCell(new Paragraph(earthSystem.getSizeOfEarthElectrodeRem(), font));
		cell264.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell264);
		
		PdfPCell cell265 = new PdfPCell(new Paragraph("7.g", font));
		cell265.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell265.setGrayFill(0.92f);
		table2.addCell(cell265);

		PdfPCell cell266 = new PdfPCell(new Paragraph("Maximum distance between earth electrode and wall", font));
		cell266.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell266.setFixedHeight(20f);
		cell266.setGrayFill(0.92f);
		table2.addCell(cell266);

		PdfPCell cell267 = new PdfPCell(new Paragraph(earthSystem.getMaximumDistanceEartElectrodeWalOb(), font));
		cell267.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell267.setFixedHeight(20f);
		table2.addCell(cell267);

		PdfPCell cell268 = new PdfPCell(new Paragraph(earthSystem.getMaximumDistanceEartElectrodeWalRem(), font));
		cell268.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell268);
		
		PdfPCell cell269 = new PdfPCell(new Paragraph("7.h", font));
		cell269.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell269.setGrayFill(0.92f);
		table2.addCell(cell269);

		PdfPCell cell270 = new PdfPCell(new Paragraph("Minimum distance between earth electrode and wall", font));
		cell270.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell270.setFixedHeight(20f);
		cell270.setGrayFill(0.92f);
		table2.addCell(cell270);

		PdfPCell cell271 = new PdfPCell(new Paragraph(earthSystem.getManimumDistanceEartElectrodeWalOb(), font));
		cell271.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell271.setFixedHeight(20f);
		table2.addCell(cell271);

		PdfPCell cell272 = new PdfPCell(new Paragraph(earthSystem.getManimumDistanceEartElectrodeWalRem(), font));
		cell272.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell272);
		
		
	}

}