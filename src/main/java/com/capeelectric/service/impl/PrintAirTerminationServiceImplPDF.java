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
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.repository.AirTerminationLpsRepository;
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
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@Override
	public void printAirTermination(String userName, Integer lpsId) throws AirTerminationException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AirTermination.pdf"));

				List<LpsAirDiscription> s = airTerminationLpsRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				LpsAirDiscription lpsAirDiscription = s.get(0);

				List<AirClamps> airClamps = lpsAirDiscription.getAirClamps();
				List<AirConnectors> airConnectors = lpsAirDiscription.getAirConnectors();
				List<AirExpansion> airExpansion = lpsAirDiscription.getAirExpansion();
				List<AirHolderDescription> airHolderdesc = lpsAirDiscription.getAirHolderDescription();
				List<AirMeshDescription> airMeshDesc = lpsAirDiscription.getAirMeshDescription();
				List<LpsVerticalAirTermination> lpsVerticalAirTermination = lpsAirDiscription
						.getLpsVerticalAirTermination();

				document.open();

				Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				float[] pointColumnWidths4 = { 30F, 150F, 50F, 50F };

				PdfPTable table = new PdfPTable(pointColumnWidths4);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
				table.setWidthPercentage(100);

				PdfPCell cell = new PdfPCell(new Paragraph("SL.NO", font1));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				PdfPCell cell1 = new PdfPCell(new Paragraph("Description", font1));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setFixedHeight(25f);
				cell1.setGrayFill(0.92f);
				table.addCell(cell);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Observation", font1));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setFixedHeight(25f);
				cell2.setGrayFill(0.92f);
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Paragraph("Remarks", font1));
				cell3.setGrayFill(0.92f);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
				tableData(table, lpsAirDiscription, document);
				document.add(table);

				float[] pointColumnWidths41 = { 30F, 150F, 50F, 50F };

				PdfPTable table1 = new PdfPTable(pointColumnWidths41);
				table1.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				table1.setWidthPercentage(100);

//				table1.setSpacingAfter(20f); // Space after table
//				table1.setSpacingBefore(20f); // Space before table

				for (LpsVerticalAirTermination lpsVerticalAirTermination1 : lpsVerticalAirTermination) {
					verticalAirTerminationIter(table1, lpsVerticalAirTermination1);

				}

				document.add(table1);

				PdfPTable table2 = new PdfPTable(pointColumnWidths41);
				table2.setWidthPercentage(100); // Width 100%
				table2.setSpacingAfter(20f); // Space after table
				table2.setSpacingBefore(20f); // Space before table
				table2.setWidthPercentage(100);

				for (AirMeshDescription airMeshDesc1 : airMeshDesc) {
					meshConductorIter(table2, airMeshDesc1);
				}

				document.add(table2);

				PdfPTable table3 = new PdfPTable(pointColumnWidths41);
				table3.setWidthPercentage(100); // Width 100%
//				table3.setSpacingAfter(20f); // Space after table
//				table3.setSpacingBefore(20f); // Space before table
				table3.setWidthPercentage(100);

				for (AirHolderDescription airHolderdesc1 : airHolderdesc) {
					airHolderIter(table3, airHolderdesc1);
				}

				document.add(table3);

				PdfPTable table4 = new PdfPTable(pointColumnWidths41);
				table4.setWidthPercentage(100); // Width 100%
//				table4.setSpacingAfter(20f); // Space after table
//				table4.setSpacingBefore(20f); // Space before table
				table4.setWidthPercentage(100);

				for (AirClamps airClamps1 : airClamps) {
					clampsIter(table4, airClamps1);
				}

				document.add(table4);

				PdfPTable table5 = new PdfPTable(pointColumnWidths41);
				table5.setWidthPercentage(100); // Width 100%
//				table5.setSpacingAfter(20f); // Space after table
//				table5.setSpacingBefore(20f); // Space before table
				table5.setWidthPercentage(100);

				for (AirExpansion airExpansion1 : airExpansion) {
					expansionPiecesIter(table5, airExpansion1);
				}

				document.add(table5);

				PdfPTable table6 = new PdfPTable(pointColumnWidths41);
				table6.setWidthPercentage(100); // Width 100%
//				table6.setSpacingAfter(20f); // Space after table
//				table6.setSpacingBefore(20f); // Space before table
				table6.setWidthPercentage(100);

				for (AirConnectors airConnectors1 : airConnectors) {
					connectorIter(table6, airConnectors1);
				}

				document.add(table6);

				document.close();
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

		cell.setPhrase(new Phrase("Connections are made by brazing/welding/crimping/ seaming/screwing/boltinh", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell3 = new PdfPCell(new Paragraph(lpsAirDiscription.getConnectionMadeBraOb(), font2));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell3);
		if (lpsAirDiscription.getConnectionMadeBraRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getConnectionMadeBraOb(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("2", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Electrical and electronic equipment is placed on walls outside structures", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell4 = new PdfPCell(new Paragraph(lpsAirDiscription.getElectricalEquipPlacedOb(), font2));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell4);
		if (lpsAirDiscription.getElectricalEquipPlacedRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getElectricalEquipPlacedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("3"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(
				new Phrase("Easily combustible part should not directly contact with air termination systems", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell5 = new PdfPCell(new Paragraph(lpsAirDiscription.getCombustablePartOb(), font2));
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell5);
		if (lpsAirDiscription.getCombustablePartOb() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getCombustablePartOb(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("4"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase(
				"No power/ control/ instrumentation/telecommunication cable or cable passage in path/near air terminals and/or air termination mesh condcutor",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell6 = new PdfPCell(new Paragraph(lpsAirDiscription.getTerminationMeshConductorOb(), font2));
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell6);
		if (lpsAirDiscription.getTerminationMeshConductorRe() != null) {

			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getTerminationMeshConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		}

		cell.setPhrase(new Phrase("5"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Equipotenial bonding carried out or not", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell);

		PdfPCell cell7 = new PdfPCell(new Paragraph(lpsAirDiscription.getBondingEquipotentialOb(), font2));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell7);
		if (lpsAirDiscription.getBondingEquipotentialRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsAirDiscription.getBondingEquipotentialRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
		}
	}

	private void connectorIter(PdfPTable table6, AirConnectors airConnectors1) throws DocumentException, IOException {

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("11", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell11);

		cell11.setPhrase(new Phrase("Clamps", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table6.addCell(cell11);

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("11a", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell9);
		if (airConnectors1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase(
				"check the connection of cross connector /T connector /straight connector/L connector  (tight/loose/corroded)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell30 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsOb(), font2));
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell30);
		if (airConnectors1.getCheckConnectionConnectorsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getCheckConnectionConnectorsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Material of connector Cross connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell31 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorOb(), font2));
		cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell31);
		if (airConnectors1.getMaterialOfConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getMaterialOfConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Straight connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell32 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorOb(), font2));
		cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell32);
		if (airConnectors1.getStrightConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getStrightConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("T connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell33 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorOb(), font2));
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell33);
		if (airConnectors1.gettConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.gettConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("L connector", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell34 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorOb(), font2));
		cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell34);
		if (airConnectors1.getlConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getlConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Total number of connectors", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell35 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorOb(), font2));
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell35);
		if (airConnectors1.getTotalNoConnectorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getTotalNoConnectorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of connectors inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell36 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoOb(), font2));
		cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell36);
		if (airConnectors1.getInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell37 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoOb(), font2));
		cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell37);
		if (airConnectors1.getInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}

		cell.setPhrase(new Phrase("11j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table6.addCell(cell);

		PdfPCell cell38 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedOb(), font2));
		cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
		table6.addCell(cell38);
		if (airConnectors1.getInspectionFailedRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airConnectors1.getInspectionFailedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table6.addCell(cell1);
		}
	}

	private void expansionPiecesIter(PdfPTable table5, AirExpansion airExpansion1)
			throws DocumentException, IOException {

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		PdfPCell cell11 = new PdfPCell();
		cell11.setPhrase(new Phrase("10", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell11);

		cell11.setPhrase(new Phrase("Clamps", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table5.addCell(cell11);

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("10a", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionOb(), font2));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell22);
		if (airExpansion1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(
				new Phrase("Straight connector  is properly crimped with expansion piece and a conductor", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell23 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecOb(), font2));
		cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell23);
		if (airExpansion1.getStrightConnectorPiecRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getStrightConnectorPiecRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Material of expansion piece", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell24 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionOb(), font2));
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell24);
		if (airExpansion1.getMaterialOfExpansionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getMaterialOfExpansionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Total number of expansion pieces", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell25 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionOb(), font2));
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell25);
		if (airExpansion1.getTotalNoExpansionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getTotalNoExpansionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of expansion pieces inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell26 = new PdfPCell(new Paragraph(airExpansion1.getInspectionNoOb(), font2));
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell26);
		if (airExpansion1.getInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell27 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoOb(), font2));
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell27);
		if (airExpansion1.getInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}

		cell.setPhrase(new Phrase("10g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table5.addCell(cell);

		PdfPCell cell28 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoOb(), font2));
		cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
		table5.addCell(cell28);
		if (airExpansion1.getInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airExpansion1.getInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.addCell(cell1);
		}
	}

	private void clampsIter(PdfPTable table4, AirClamps airClamps1) throws DocumentException, IOException {

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
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table4.addCell(cell11);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("9a", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionOb(), font2));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell22);
		if (airClamps1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Conductor clamp is firmly fixed/mounted/pasted over the flat surface", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell23 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceOb(), font2));
		cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell23);
		if (airClamps1.getConductorClampsFlatSurafaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getConductorClampsFlatSurafaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Interconnection of conductor with clamp (tight/loose/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell24 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsOb(), font2));
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell24);
		if (airClamps1.getInterConnectionOfClampsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInterConnectionOfClampsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Type of clamp", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell25 = new PdfPCell(new Paragraph(airClamps1.getClampTypeOb(), font2));
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell25);
		if (airClamps1.getClampTypRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getClampTypRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Material of clamp", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell26 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsOb(), font2));
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell26);
		if (airClamps1.getMaterialOfClampsRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getMaterialOfClampsRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Total number of clamps", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell27 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoOb(), font2));
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell26);
		if (airClamps1.getTotalClampsNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getTotalClampsNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of clamps inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell28 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoOb(), font2));
		cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell28);
		if (airClamps1.getInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell29 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedOb(), font2));
		cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell29);
		if (airClamps1.getInspectionPassedRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionPassedRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

		cell.setPhrase(new Phrase("9i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table4.addCell(cell);

		PdfPCell cell30 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReOb(), font2));
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		table4.addCell(cell30);
		if (airClamps1.getInspectionFailedReRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airClamps1.getInspectionFailedReRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table4.addCell(cell1);
		}

	}

	private void airHolderIter(PdfPTable table3, AirHolderDescription airHolderdesc1)
			throws DocumentException, IOException {

		PdfPCell cell11 = new PdfPCell();

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		cell11.setPhrase(new Phrase("8", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell11);

		cell11.setPhrase(new Phrase("Holders", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table3.addCell(cell11);

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase("8a", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionOb(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell8);
		if (airHolderdesc1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Conductor holder is firmly fixed/mounted/pasted over the flat surface", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell9);
		if (airHolderdesc1.getConductorHolderFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Conductor is properly  holded  in the holder", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderOb(), font2));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell10);
		if (airHolderdesc1.getConductorHolderRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getConductorHolderRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Type of holder", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell111 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeOb(), font2));
		cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell111);
		if (airHolderdesc1.getHolderTypeRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderTypeRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Material of holder", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderOb(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell12);
		if (airHolderdesc1.getMaterailOfHolderRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfHolderRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Total number of holders", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell13 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoOb(), font2));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell13);
		if (airHolderdesc1.getTotalHolderNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalHolderNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of holders inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell14 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoOb(), font2));
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell14);
		if (airHolderdesc1.getHolderInspNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell15 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoOb(), font2));
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell15);
		if (airHolderdesc1.getHolderInspPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell16 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoOb(), font2));
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell16);
		if (airHolderdesc1.getHolderInspFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getHolderInspFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Material of parpet holder", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell17 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderOb(), font2));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell17);
		if (airHolderdesc1.getMaterailOfParpetHolderRem() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getMaterailOfParpetHolderRem(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8k", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Total number of holders", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell18 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoOb(), font2));
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell18);
		if (airHolderdesc1.getTotalParpetHolderNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getTotalParpetHolderNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8l", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of holders inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell19 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoOb(), font2));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell19);
		if (airHolderdesc1.getParpetInspectionNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8m", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell20 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoOb(), font2));
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell20);
		if (airHolderdesc1.getParpetInspectionPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}

		cell.setPhrase(new Phrase("8n", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table3.addCell(cell);

		PdfPCell cell21 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoOb(), font2));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell21);
		if (airHolderdesc1.getParpetInspectionFailedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airHolderdesc1.getParpetInspectionFailedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table3.addCell(cell1);
		}
	}

	private void meshConductorIter(PdfPTable table2, AirMeshDescription airMeshDesc1)
			throws DocumentException, IOException {

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);

		PdfPCell cell11 = new PdfPCell();

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		cell11.setPhrase(new Phrase("7", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell11);

		cell11.setPhrase(new Phrase("Mesh conductors", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table2.addCell(cell11);

		PdfPCell cell = new PdfPCell();

		cell.setPhrase(new Phrase("7c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Size/cross section area of conductor", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorOb(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell8);
		if (airMeshDesc1.getSizeOfConductorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Mesh Size", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(airMeshDesc1.getMeshSizeOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell9);
		if (airMeshDesc1.getMeshSizeRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getSizeOfConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Maximum distance between mesh conductors", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceOb(), font2));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell9);
		if (airMeshDesc1.getMaximumDistanceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMaximumDistanceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase("Minimum distance between mesh conductors", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell22 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceOb(), font1));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell22);
		if (airMeshDesc1.getMinimumDistanceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getMinimumDistanceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		}

		cell.setPhrase(new Phrase("7g", font2));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		cell.setPhrase(new Phrase(
				"Height of mesh conductors above flat surface (For flat roof, air termination system must be installed above probable water level)",
				font2));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table2.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceOb(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell12);
		if (airMeshDesc1.getHeightOfConductorFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(airMeshDesc1.getHeightOfConductorFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cell1);
		}
	}

	private void verticalAirTerminationIter(PdfPTable table1, LpsVerticalAirTermination lpsVerticalAirTermination1)
			throws DocumentException, IOException {

		table1.setSpacingBefore(20f); // Space before table

		table1.setSpacingAfter(20f); // Space after table

		Font font1 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
		Font font2 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell();

		PdfPCell cell11 = new PdfPCell();

		Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
		cell11.setPhrase(new Phrase("6", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell11);

		cell11.setPhrase(new Phrase("Vertical air terminal (VAT)", font));
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setColspan(3);
		table1.addCell(cell11);

		cell.setPhrase(new Phrase("6a", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Physical inspection (damage/break/bend/interception tip/position)", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell8 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionOb(), font2));
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell8);
		if (lpsVerticalAirTermination1.getPhysicalInspectionRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getPhysicalInspectionRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6b", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Material of air terminal", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell9 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalOb(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell9);
		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6c", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Size/cross section area of air terminal", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell10 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalOb(), font2));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell10);
		if (lpsVerticalAirTermination1.getSizeOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSizeOfTerminalRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6d", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Height of vertical air terminal", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell111 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalOb(), font2));
		cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell111);
		if (lpsVerticalAirTermination1.getHeightOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightOfTerminalRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6e", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Angle of protection based on heigh", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell12 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightOb(), font2));
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell12);
		if (lpsVerticalAirTermination1.getAngleProtectionHeightRe() != null) {
			PdfPCell cell1 = new PdfPCell(
					new Paragraph(lpsVerticalAirTermination1.getAngleProtectionHeightRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6f", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Any metal installations protrudes outside the volume protected by air termination systems (coverage area check)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell13 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalOb(), font2));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell13);
		if (lpsVerticalAirTermination1.getMaterialOfTerminalRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getMaterialOfTerminalRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6g", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Support (clip/clamp/concrete) for vertical air terminal is firmly fixed/mounted/pasted over the flat surface",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell14 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceOb(), font2));
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell14);
		if (lpsVerticalAirTermination1.getSupportFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getSupportFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6h", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Height of air terminals above flat surface(for flat roof, air termination system must be installed above probable water level)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell15 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceOb(), font2));
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell15);
		if (lpsVerticalAirTermination1.getHeightFlatSurfaceRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getHeightFlatSurfaceRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6i", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase(
				"Interconnection VAT to roof conductor & metal bodies to roof conductors (tight/loose/corroded)",
				font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell16 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorOB(), font2));
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell16);
		if (lpsVerticalAirTermination1.getVatToRoofConductorRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getVatToRoofConductorRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6j", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Total number of air terminals", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell17 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberOb(), font2));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell17);
		if (lpsVerticalAirTermination1.getTotalNumberRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getTotalNumberRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);

		}

		cell.setPhrase(new Phrase("6k", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of air terminals inspected", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell18 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspNoOb(), font2));
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell18);
		if (lpsVerticalAirTermination1.getInspFaileddNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6l", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections passed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell19 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoOb(), font2));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell19);
		if (lpsVerticalAirTermination1.getInspPassedNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspPassedNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

		cell.setPhrase(new Phrase("6m", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		cell.setPhrase(new Phrase("Number of inspections failed", font1));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell);

		PdfPCell cell20 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoOb(), font2));
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell20);
		if (lpsVerticalAirTermination1.getInspFaileddNoRe() != null) {
			PdfPCell cell1 = new PdfPCell(new Paragraph(lpsVerticalAirTermination1.getInspFaileddNoRe(), font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		} else {
			PdfPCell cell1 = new PdfPCell(new Paragraph("null", font2));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cell1);
		}

	}
}
