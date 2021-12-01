package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirClamps;
import com.capeelectric.model.AirConnectors;
import com.capeelectric.model.AirExpansion;
import com.capeelectric.model.AirHolderDescription;
import com.capeelectric.model.AirMeshDescription;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.PrintAirTerminationService;
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
public class PrintAirTerminationServiceImplPDF implements PrintAirTerminationService {

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@Override
	public void printAirTermination(String userName, Integer basicLpsId) throws AirTerminationException {
		if (userName != null && !userName.isEmpty() && basicLpsId != null && basicLpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AirTermination.pdf"));

				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, basicLpsId);
				BasicLps basicLps1 = basicLps.get(0);

				List<LpsAirDiscription> s = airTerminationLpsRepository.findByUserNameAndBasicLpsId(userName,
						basicLpsId);
				LpsAirDiscription lpsAirDiscription = s.get(0);

				List<AirClamps> airClamps = lpsAirDiscription.getAirClamps();
				List<AirConnectors> airConnectors = lpsAirDiscription.getAirConnectors();
				List<AirExpansion> airExpansion = lpsAirDiscription.getAirExpansion();
				List<AirHolderDescription> airHolderdesc = lpsAirDiscription.getAirHolderDescription();
				List<AirMeshDescription> airMeshDesc = lpsAirDiscription.getAirMeshDescription();
				List<LpsVerticalAirTermination> lpsVerticalAirTermination = lpsAirDiscription
						.getLpsVerticalAirTermination();

				document.open();

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table
				headertable.setWidthPercentage(100);

				PdfPCell label = new PdfPCell(new Paragraph(
						"Check list for Air Termination System of LPS\r\n" + "as per IS/IEC 62305", font1));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
//				label.setFixedHeight(20f);
				headertable.addCell(label);
				document.add(headertable);

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

				float[] pointColumnWidths4 = { 25F, 150F, 55F, 50F };

				PdfPTable table = new PdfPTable(pointColumnWidths4);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setSpacingAfter(10f);
				table.setWidthPercentage(100);

				PdfPCell cell = new PdfPCell(new Paragraph("SL.NO", font11));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);

				PdfPCell cell1 = new PdfPCell(new Paragraph("Description", font11));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setFixedHeight(20f);
				cell1.setGrayFill(0.92f);
				table.addCell(cell);
				table.addCell(cell1);

				PdfPCell cell21 = new PdfPCell(new Paragraph("Observation", font11));
				cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell21.setFixedHeight(20f);
				cell21.setGrayFill(0.92f);
				table.addCell(cell21);

				PdfPCell cell3 = new PdfPCell(new Paragraph("Remarks", font11));
				cell3.setGrayFill(0.92f);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);

				tableData(table, lpsAirDiscription, document);

				document.add(table);

				for (LpsVerticalAirTermination lpsVerticalAirTermination1 : lpsVerticalAirTermination) {

					PdfPTable table11 = verticalAirTerminationIter(lpsVerticalAirTermination1);
					document.add(table11);
				}

				for (AirMeshDescription airMeshDesc1 : airMeshDesc) {

					PdfPTable table2 = meshConductorIter(airMeshDesc1);
					document.add(table2);
				}

				for (AirHolderDescription airHolderdesc1 : airHolderdesc) {

					PdfPTable table3 = airHolderIter(airHolderdesc1);
					document.add(table3);

				}

				for (AirClamps airClamps1 : airClamps) {

					PdfPTable table41 = clampsIter(airClamps1);
					document.add(table41);
				}

				for (AirExpansion airExpansion1 : airExpansion) {

					PdfPTable table5 = expansionPiecesIter(airExpansion1);
					document.add(table5);
				}

				for (AirConnectors airConnectors1 : airConnectors) {

					PdfPTable table6 = connectorIter(airConnectors1);
					document.add(table6);
				}

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new AirTerminationException("Invalid Inputs");
		}
	}

	private void tableData(PdfPTable table, LpsAirDiscription lpsAirDiscription, Document document)
			throws DocumentException, IOException {

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell = new PdfPCell();

		cell.setPhrase(new Phrase("1", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Connections are made by brazing/welding/crimping/ seaming/screwing/bolting", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell3 = new PdfPCell(new Paragraph(lpsAirDiscription.getConnectionMadeBraOb(), font2));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);

		table.addCell(cell3);
		if (lpsAirDiscription.getConnectionMadeBraRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getConnectionMadeBraRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("2", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Electrical and electronic equipment is placed on walls outside structures", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell4 = new PdfPCell(new Paragraph(lpsAirDiscription.getElectricalEquipPlacedOb(), font2));
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);

		table.addCell(cell4);
		if (lpsAirDiscription.getElectricalEquipPlacedRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getElectricalEquipPlacedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("3"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(
				new Phrase("Easily combustible part should not directly contact with air termination systems", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell5 = new PdfPCell(new Paragraph(lpsAirDiscription.getCombustablePartOb(), font2));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell5);
		if (lpsAirDiscription.getCombustablePartOb() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getCombustablePartRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("4"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase(
				"No power/ control/ instrumentation/telecommunication cable or cable passage in path/near air terminals and/or air termination mesh conductor",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell6 = new PdfPCell(new Paragraph(lpsAirDiscription.getTerminationMeshConductorOb(), font2));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell6);
		if (lpsAirDiscription.getTerminationMeshConductorRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getTerminationMeshConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("5"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Equipotential bonding carried out or not", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell7 = new PdfPCell(new Paragraph(lpsAirDiscription.getBondingEquipotentialOb(), font2));
		cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell7);
		if (lpsAirDiscription.getBondingEquipotentialRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getBondingEquipotentialRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
		}
	}

	private PdfPTable connectorIter(AirConnectors airConnectors1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table6 = new PdfPTable(pointColumnWidths41);
		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(10f); // Space before table
		table6.setSpacingAfter(10f); // Space after table
		table6.setWidthPercentage(100);

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);
		
		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("11", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell11);

		cell11.setPhrase(new Phrase("Connectors", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setFixedHeight(20f);
		cell11.setColspan(3);
		table6.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "11.a", font2));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table6.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table6.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(airConnectors1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table6.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table6.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(airConnectors1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table6.addCell(cell211);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("11.b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell9);
		if (airConnectors1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase(
				"check the connection of cross connector /T connector /straight connector/L connector  (tight/loose/corroded)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell30 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsOb(), font2));
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell30);
		if (airConnectors1.getCheckConnectionConnectorsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Material of connector Cross connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell31 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorOb(), font2));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell31);
		if (airConnectors1.getMaterialOfConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Straight connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell32 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorOb(), font2));
		cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell32);
		if (airConnectors1.getStrightConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("T connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell33 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorOb(), font2));
		cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell33);
		if (airConnectors1.gettConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("L connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell34 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorOb(), font2));
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell34);
		if (airConnectors1.getlConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Total number of connectors", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell35 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorOb(), font2));
		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell35);
		if (airConnectors1.getTotalNoConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of connectors inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell36 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoOb(), font2));
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell36);
		if (airConnectors1.getInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell37 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoOb(), font2));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell37);
		if (airConnectors1.getInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11.k", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedOb(), font2));
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		table6.addCell(cell38);
		if (airConnectors1.getInspectionFailedRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table6.addCell(cell1);
		}
		return table6;
	}

	private PdfPTable expansionPiecesIter(AirExpansion airExpansion1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table5 = new PdfPTable(pointColumnWidths41);
		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(10f); // Space before table
		table5.setSpacingAfter(10f); // Space after table

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("10", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell11);

		cell11.setPhrase(new Phrase("Expansion Pieces", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setFixedHeight(20f);
		cell11.setColspan(3);
		table5.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "10.a", font2));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table5.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table5.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(airExpansion1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table5.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table5.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(airExpansion1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table5.addCell(cell211);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("10.b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionOb(), font2));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell22);
		if (airExpansion1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(
				new Phrase("Straight connector  is properly crimped with expansion piece and a conductor", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell23 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecOb(), font2));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell23);
		if (airExpansion1.getStrightConnectorPiecRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Material of expansion piece", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell24 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionOb(), font2));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell24);
		if (airExpansion1.getMaterialOfExpansionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Total number of expansion pieces", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell25 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionOb(), font2));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell25);
		if (airExpansion1.getTotalNoExpansionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of expansion pieces inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell26 = new PdfPCell(new Paragraph(airExpansion1.getInspectionNoOb(), font2));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell26);
		if (airExpansion1.getInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell27 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoOb(), font2));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell27);
		if (airExpansion1.getInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10.h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell28 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoOb(), font2));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table5.addCell(cell28);
		if (airExpansion1.getInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.addCell(cell1);
		}
		return table5;
	}

	private PdfPTable clampsIter(AirClamps airClamps1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table4 = new PdfPTable(pointColumnWidths41);
		table4.setWidthPercentage(100); // Width 100%
		table4.setSpacingBefore(10f); // Space before table
		table4.setSpacingAfter(10f); // Space after table
		table4.setWidthPercentage(100);

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		
		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("9", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell11);

		cell11.setPhrase(new Phrase("Clamps", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setFixedHeight(20f);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table4.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "9.a", font2));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table4.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table4.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(airClamps1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table4.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table4.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(airClamps1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table4.addCell(cell211);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("9.b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionOb(), font2));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell22);
		if (airClamps1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Conductor clamp is firmly fixed/mounted/pasted over the flat surface", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell23 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceOb(), font2));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell23);
		if (airClamps1.getConductorClampsFlatSurafaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Interconnection of conductor with clamp (tight/loose/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell24 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsOb(), font2));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell24);
		if (airClamps1.getInterConnectionOfClampsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Type of clamp", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell25 = new PdfPCell(new Paragraph(airClamps1.getClampTypeOb(), font2));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell25);
		if (airClamps1.getClampTypRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getClampTypRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Material of clamp", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell26 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsOb(), font2));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell26);
		if (airClamps1.getMaterialOfClampsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Total number of clamps", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell27 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoOb(), font2));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell27);
		if (airClamps1.getTotalClampsNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of clamps inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell28 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoOb(), font2));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell28);
		if (airClamps1.getInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell29 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedOb(), font2));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell29);
		if (airClamps1.getInspectionPassedRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9.j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell30 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReOb(), font2));
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		table4.addCell(cell30);
		if (airClamps1.getInspectionFailedReRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table4.addCell(cell1);
		}
		return table4;
	}

	private PdfPTable airHolderIter(AirHolderDescription airHolderdesc1) throws DocumentException, IOException {

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		
		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table3 = new PdfPTable(pointColumnWidths41);
		table3.setWidthPercentage(100); // Width 100%
		table3.setSpacingBefore(10f); // Space before table
		table3.setSpacingAfter(10f); // Space after table
		table3.setWidthPercentage(100);

		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("8", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell11);

		cell11.setPhrase(new Phrase("Holders", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setFixedHeight(20f);
		cell11.setColspan(3);
		table3.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "8.a", font2));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table3.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table3.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(airHolderdesc1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table3.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table3.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(airHolderdesc1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table3.addCell(cell211);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("8.b", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionOb(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell8);
		if (airHolderdesc1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.c", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Conductor holder is firmly fixed/mounted/pasted over the flat surface", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell9);
		if (airHolderdesc1.getConductorHolderFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.d", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Conductor is properly  holded  in the holder", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderOb(), font2));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell10);
		if (airHolderdesc1.getConductorHolderRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.e", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Type of holder", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell111 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeOb(), font2));
		cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell111);
		if (airHolderdesc1.getHolderTypeRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.f", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Material of holder", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderOb(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell12);
		if (airHolderdesc1.getMaterailOfHolderRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.g", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Total number of holders", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell13 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoOb(), font2));
		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell13);
		if (airHolderdesc1.getTotalHolderNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.h", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of holders inspected", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell14 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoOb(), font2));
		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell14);
		if (airHolderdesc1.getHolderInspNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.i", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell15 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoOb(), font2));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell15);
		if (airHolderdesc1.getHolderInspPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.j", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell16 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoOb(), font2));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell16);
		if (airHolderdesc1.getHolderInspFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.k", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Material of parpet holder", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell17 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderOb(), font2));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell17);
		if (airHolderdesc1.getMaterailOfParpetHolderRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.l", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Total number of holders", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell18 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoOb(), font2));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell18);
		if (airHolderdesc1.getTotalParpetHolderNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.m", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of holders inspected", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell19 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoOb(), font2));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell19);
		if (airHolderdesc1.getParpetInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.n", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell20 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoOb(), font2));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell20);
		if (airHolderdesc1.getParpetInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8.o", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell21 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoOb(), font2));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table3.addCell(cell21);
		if (airHolderdesc1.getParpetInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table3.addCell(cell1);
		}
		return table3;
	}

	private PdfPTable meshConductorIter(AirMeshDescription airMeshDesc1) throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths41);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(10f); // Space before table
		table2.setSpacingAfter(10f); // Space after table

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("7", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell11);

		cell11.setPhrase(new Phrase("Mesh conductors", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setFixedHeight(20f);
		cell11.setColspan(3);
		table2.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "7.a", font2));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table2.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table2.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(airMeshDesc1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table2.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table2.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(airMeshDesc1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table2.addCell(cell211);

		PdfPCell cell200 = new PdfPCell();
		cell200.setPhrase(new Phrase("7.b", font2));
		cell200.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell200.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell200);

		cell200.setPhrase(new Phrase("Physical inspection (damage/break/bend/corroded/route)", font2));
		cell200.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell200.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell200);

		PdfPCell cell8 = new PdfPCell(new Paragraph(airMeshDesc1.getPhysicalInspectionOb(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell8);
		if (airMeshDesc1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		PdfPCell cell201 = new PdfPCell();
		cell201.setPhrase(new Phrase("7.c", font2));
		cell201.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell201.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell201);

		cell201.setPhrase(new Phrase("Material of conductor", font2));
		cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell201.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell201);

		PdfPCell cell88 = new PdfPCell(new Paragraph(airMeshDesc1.getMaterailOfConductorOb(), font2));
		cell88.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell88);
		if (airMeshDesc1.getMaterailOfConductorRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMaterailOfConductorRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("7.d", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Size/cross section area of conductor (mm)", font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell81 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorOb(), font2));
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell81);
		if (airMeshDesc1.getSizeOfConductorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7.e", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Mesh Size (m)", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airMeshDesc1.getMeshSizeOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell9);
		if (airMeshDesc1.getMeshSizeRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7.f", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Maximum distance between mesh conductors (m)", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceOb(), font2));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell10);
		if (airMeshDesc1.getMaximumDistanceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7.g", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Minimum distance between mesh conductors (m)", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceOb(), font2));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell22);
		if (airMeshDesc1.getMinimumDistanceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7.h", font2));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);
		cell.setPhrase(new Phrase(
				"Height of mesh conductors above flat surface (For flat roof, air termination system must be installed above probable water level)",
				font2));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceOb(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell12);
		if (airMeshDesc1.getHeightOfConductorFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table2.addCell(cell1);
		}
		return table2;
	}

	private PdfPTable verticalAirTerminationIter(LpsVerticalAirTermination lpsVerticalAirTermination1)
			throws DocumentException, IOException {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(10f); // Space after table

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell11 = new PdfPCell();

		
		cell11.setPhrase(new Phrase("6", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell11);

		cell11.setPhrase(new Phrase("Vertical air terminal (VAT)", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setFixedHeight(20f);
		cell11.setColspan(3);
		table1.addCell(cell11);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n"+"6.a", font1));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table1.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table1.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getLocationName(), font));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table1.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table1.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(
				new Paragraph(lpsVerticalAirTermination1.getLocationNumber().toString(), font));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table1.addCell(cell211);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("6.b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/bend/interception tip/position)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionOb(), font1));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell8);
		if (lpsVerticalAirTermination1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Material of air terminal", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalOb(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell9);
		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Size/cross section area of air terminal (mm)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalOb(), font1));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell10);
		if (lpsVerticalAirTermination1.getSizeOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Height of vertical air terminal (m)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell111 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalOb(), font1));
		cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell111);
		if (lpsVerticalAirTermination1.getHeightOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Angle of protection based on height", font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightOb(), font1));
		cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell12);
		if (lpsVerticalAirTermination1.getAngleProtectionHeightRe() != null) {
			PdfPCell cell1 = new PdfPCell(
					new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Any metal installations protrudes outside the volume protected by air termination systems (coverage area check)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell13 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalOb(), font1));
		cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell13);
		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Support (clip/clamp/concrete) for vertical air terminal is firmly fixed/mounted/pasted over the flat surface",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell14 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceOb(), font1));
		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell14);
		if (lpsVerticalAirTermination1.getSupportFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Height of air terminals above flat surface(for flat roof, air termination system must be installed above probable water level)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell15 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceOb(), font1));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell15);
		if (lpsVerticalAirTermination1.getHeightFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Interconnection VAT to roof conductor & metal bodies to roof conductors (tight/loose/corroded)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell16 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorOB(), font1));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell16);
		if (lpsVerticalAirTermination1.getVatToRoofConductorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.k", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Total number of air terminals", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell17 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberOb(), font1));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell17);
		if (lpsVerticalAirTermination1.getTotalNumberRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);

		}

		cell.setPhrase(new Phrase("6.l", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of air terminals inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell18 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspNoOb(), font1));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell18);
		if (lpsVerticalAirTermination1.getInspFaileddNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.m", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell19 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoOb(), font1));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell19);
		if (lpsVerticalAirTermination1.getInspPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6.n", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell20 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoOb(), font1));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell20);
		if (lpsVerticalAirTermination1.getInspFaileddNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoRe(), font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font1));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell1);
		}

		return table1;
	}
}