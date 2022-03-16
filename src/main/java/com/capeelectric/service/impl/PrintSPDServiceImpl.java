package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SpdDescription;
import com.capeelectric.model.SpdReport;
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
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintSPDServiceImpl implements PrintSPDService {

//	@Autowired
//	private SPDRepository spdRepository;

	@Override
	public void printSPD(String userName, Integer lpsId, Optional<SpdReport> spdDetails) throws SPDException {

//	@Override
//	public void printSPD(String userName, Integer lpsId) throws SPDException {

//		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
		Document document = new Document(PageSize.A4, 68, 68, 62, 68);
//
		try {

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("SPD.pdf"));

//			List<SpdReport> spdMain = spdRepository.findByUserNameAndBasicLpsId(userName, lpsId);
//			SpdReport spdMain1 = spdMain.get(0);
			SpdReport spdMain1 = spdDetails.get();

			List<SPD> spd1 = spdMain1.getSpd();
//			SPD spd = spd1.get(0);

//			List<SpdDescription> spdDesc1 = spd.getSpdDescription();

			document.open();

			Font font12B = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD);
			Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD);
			Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

			float[] pointColumnWidths5 = { 100F };

			PdfPTable headertable = new PdfPTable(pointColumnWidths5);
			headertable.setWidthPercentage(100); // Width 100%
			headertable.setSpacingBefore(10f); // Space before table

			PdfPCell label = new PdfPCell(
					new Paragraph("Check list for SPD System of LPS\r\n" + "as per IS/IEC 62305", font12B));
			label.setHorizontalAlignment(Element.ALIGN_CENTER);
			label.setGrayFill(0.92f);
//			label.setFixedHeight(20f);
			headertable.addCell(label);
			document.add(headertable);

			for (SPD spd : spd1) {
				
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
				PdfPCell cell2 = new PdfPCell(new Paragraph(spd.getBuildingNumber().toString(),
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
				PdfPCell cell4 = new PdfPCell(new Paragraph(spd.getBuildingName(),
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

				PdfPCell cell206 = new PdfPCell(new Paragraph("Mains incoming panel", font1));
				cell206.setGrayFill(0.92f);
				table205.addCell(cell206);

				PdfPCell cell209 = new PdfPCell(new Paragraph(spd.getMainsIncomingOb(), font3));
				cell209.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell209);

				PdfPCell cell210 = new PdfPCell(new Paragraph(spd.getMainsIncomingRem(), font3));
				cell210.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell210);

				PdfPCell cell20 = new PdfPCell(new Paragraph("2", font1));
				cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell20.setGrayFill(0.92f);
				table205.addCell(cell20);

				PdfPCell cell21 = new PdfPCell(new Paragraph("Total Number of mains incoming panels", font1));
				cell21.setFixedHeight(20f);
				cell21.setGrayFill(0.92f);
				table205.addCell(cell21);

				PdfPCell cell29 = new PdfPCell(new Paragraph(spd.getTotalMainsIncomingOb(), font3));
				cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell29);

				PdfPCell cell22 = new PdfPCell(new Paragraph(spd.getTotalMainsIncomingRem(), font3));
				cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell22);

				PdfPCell cell24 = new PdfPCell(new Paragraph("3", font1));
				cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell24.setGrayFill(0.92f);
				table205.addCell(cell24);

				PdfPCell cell23 = new PdfPCell(new Paragraph(
						"Total Number of panels supplting power to outdoor equipment such as light fittings / air conditioner chiller units (not split AC outdoor unit)",
						font1));
				cell23.setFixedHeight(20f);
				cell23.setGrayFill(0.92f);
				table205.addCell(cell23);

				PdfPCell cell35 = new PdfPCell(new Paragraph(spd.getNoPannelSupplittingOb(), font3));
				cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph(spd.getNoPannelSupplittingRem(), font3));
				cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph("4", font1));
				cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell37.setGrayFill(0.92f);
				table205.addCell(cell37);

				PdfPCell cell40 = new PdfPCell(
						new Paragraph("Total Number of outdoor equipment and type each equipment", font1));
				cell40.setFixedHeight(20f);
				cell40.setGrayFill(0.92f);
				table205.addCell(cell40);

				PdfPCell cell44 = new PdfPCell(new Paragraph(spd.getTotalNoOutDoorRequipmentOb(), font3));
				cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell44);

				PdfPCell cell47 = new PdfPCell(new Paragraph(spd.getTotalNoOutDoorRequipmentRem(), font3));
				cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
				table205.addCell(cell47);

				document.add(table2);
				document.add(table205);

				float[] pointColumnWidths2 = { 100F };

				PdfPTable sPDTable = new PdfPTable(pointColumnWidths2);

				sPDTable.setWidthPercentage(100); // Width 100%
				sPDTable.setSpacingBefore(10f); // Space before table
//			sPDTable.setSpacingAfter(5f); // Space after table
				sPDTable.getDefaultCell().setBorder(0);

				PdfPCell arrangements1 = new PdfPCell(
						new Paragraph("SPD", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
				arrangements1.setBackgroundColor(new GrayColor(0.82f));
				arrangements1.setHorizontalAlignment(Element.ALIGN_CENTER);
				arrangements1.setBorder(PdfPCell.NO_BORDER);
				sPDTable.addCell(arrangements1);
				document.add(sPDTable);

				for (SpdDescription spdDesc : spd.getSpdDescription()) {
					if (!spdDesc.getFlag().equalsIgnoreCase("R")) {
						spdDescIter(document, font11, font1, table2, spdDesc);
					}
				}
				document.newPage();
			}

			document.close();
			writer.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
//
//		else {
//			throw new SPDException("Invalid Inputs");
//		}
//
//	}
//

	private void spdDescIter(Document document, Font font11, Font font1, PdfPTable table2, SpdDescription spdDesc)
			throws DocumentException, IOException {

		document.add(table2);
		float[] pointColumnWidths1 = { 24F, 80F, 120F };
		PdfPTable table10 = new PdfPTable(pointColumnWidths1);

		table10.setWidthPercentage(100); // Width 100%
		// table10.setSpacingBefore(10f); // Space before table
		// table10.setSpacingAfter(5f); // Space after table
		table10.getDefaultCell().setBorder(0);

		PdfPCell cell225 = new PdfPCell(new Paragraph("5", font1));
		cell225.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell225.setRowspan(2);
		cell225.setGrayFill(0.92f);
		table10.addCell(cell225);

		PdfPCell cell11 = new PdfPCell(
				new Paragraph("Location:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		cell11.setBackgroundColor(new BaseColor(203, 183, 162));
		cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
		// cell11.setBorder(PdfPCell.NO_BORDER);
		table10.addCell(cell11);
		PdfPCell cell222 = new PdfPCell(
				new Paragraph(spdDesc.getLocation(), new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		cell222.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell222.setBackgroundColor(new BaseColor(203, 183, 162));
		// cell222.setBorder(PdfPCell.NO_BORDER);
		table10.addCell(cell222);

		PdfPCell cell333 = new PdfPCell(
				new Paragraph("Panel Name:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		cell333.setBackgroundColor(new BaseColor(203, 183, 162));
		cell333.setHorizontalAlignment(Element.ALIGN_LEFT);
		// cell333.setBorder(PdfPCell.NO_BORDER);
		table10.addCell(cell333);
		PdfPCell cell444 = new PdfPCell(
				new Paragraph(spdDesc.getPanelName(), new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		cell444.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell444.setBackgroundColor(new BaseColor(203, 183, 162));
		// cell444.setBorder(PdfPCell.NO_BORDER);
		table10.addCell(cell444);

		document.add(table10);

		float[] pointColumnWidths3 = { 30F, 145F, 55F, 50F };
		PdfPTable table11 = new PdfPTable(pointColumnWidths3);

		table11.setWidthPercentage(100); // Width 100%
		// table10.setSpacingBefore(10f); // Space before table
		// table10.setSpacingAfter(5f); // Space after table
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setBorder(0);

		PdfPCell cell221 = new PdfPCell(new Paragraph("5(a)", font1));
		cell221.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell221.setRowspan(5);
		cell221.setGrayFill(0.92f);
		table11.addCell(cell221);

		PdfPCell cell223 = new PdfPCell(new Paragraph("* Details of SPD", font11));
		cell223.setGrayFill(0.92f);
		cell223.setColspan(3);
		table11.addCell(cell223);

		PdfPCell cell226 = new PdfPCell(new Paragraph("Make", font1));
		cell226.setGrayFill(0.92f);
		table11.addCell(cell226);

		PdfPCell cell227 = new PdfPCell(new Paragraph(spdDesc.getSpdMakeOb(), font1));
		table11.addCell(cell227);

		PdfPCell cell228 = new PdfPCell(new Paragraph(spdDesc.getSpdMakeRem(), font1));
		table11.addCell(cell228);

		PdfPCell cell229 = new PdfPCell(new Paragraph("Model", font1));
		cell229.setGrayFill(0.92f);
		table11.addCell(cell229);

		PdfPCell cell330 = new PdfPCell(new Paragraph(spdDesc.getSpdModelOb(), font1));
		table11.addCell(cell330);

		PdfPCell cell331 = new PdfPCell(new Paragraph(spdDesc.getSpdModelRem(), font1));
		table11.addCell(cell331);

		PdfPCell cell332 = new PdfPCell(new Paragraph("Class Type", font1));
		cell332.setGrayFill(0.92f);
		table11.addCell(cell332);

		PdfPCell cell335 = new PdfPCell(new Paragraph(spdDesc.getSpdClassTypeOb(), font1));
		table11.addCell(cell335);

		PdfPCell cell336 = new PdfPCell(new Paragraph(spdDesc.getSpdClassTypeRem(), font1));
		table11.addCell(cell336);

		PdfPCell cell337 = new PdfPCell(new Paragraph("Application", font1));
		cell337.setGrayFill(0.92f);
		table11.addCell(cell337);

		PdfPCell cell338 = new PdfPCell(new Paragraph(spdDesc.getSpdApplicationOb(), font1));
		table11.addCell(cell338);

		PdfPCell cell340 = new PdfPCell(new Paragraph(spdDesc.getSpdApplicationRem(), font1));
		table11.addCell(cell340);

		PdfPCell cell341 = new PdfPCell(new Paragraph("5(b)", font1));
		cell341.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell341.setGrayFill(0.92f);
		table11.addCell(cell341);

		PdfPCell cell350 = new PdfPCell(new Paragraph("Application ", font1));
		cell350.setGrayFill(0.92f);
		table11.addCell(cell350);

		PdfPCell cell351 = new PdfPCell(new Paragraph(spdDesc.getSpdMainApplicationOb(), font1));
		table11.addCell(cell351);

		PdfPCell cell352 = new PdfPCell(new Paragraph(spdDesc.getSpdMainApplicationRem(), font1));
		table11.addCell(cell352);

		PdfPCell cell353 = new PdfPCell(new Paragraph("5(c)", font1));
		cell353.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell353.setGrayFill(0.92f);
		table11.addCell(cell353);

		PdfPCell cell354 = new PdfPCell(new Paragraph("Proper connection made based on application", font1));
		cell354.setGrayFill(0.92f);
		table11.addCell(cell354);

		PdfPCell cell355 = new PdfPCell(new Paragraph(spdDesc.getProperConnectionOb(), font1));
		table11.addCell(cell355);

		PdfPCell cell356 = new PdfPCell(new Paragraph(spdDesc.getProperConnectionRem(), font1));
		table11.addCell(cell356);

		PdfPCell cell357 = new PdfPCell(new Paragraph("5(d)", font1));
		cell357.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell357.setGrayFill(0.92f);
		table11.addCell(cell357);

		PdfPCell cell358 = new PdfPCell(new Paragraph("Incomer rating of the panel in A", font1));
		cell358.setGrayFill(0.92f);
		table11.addCell(cell358);

		PdfPCell cell359 = new PdfPCell(new Paragraph(spdDesc.getIncomerRatingOb().toString(), font1));
		table11.addCell(cell359);

		PdfPCell cell360 = new PdfPCell(new Paragraph(spdDesc.getIncomerRatingRem(), font1));
		table11.addCell(cell360);

		PdfPCell cell361 = new PdfPCell(new Paragraph("5(e)", font1));
		cell361.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell361.setGrayFill(0.92f);
		table11.addCell(cell361);

		PdfPCell cell362 = new PdfPCell(new Paragraph(
				"	Back up fuse/MCB rating in A (Back up fuse in line with safety requirements at \"End of Life\")",
				font1));
		cell362.setGrayFill(0.92f);
		table11.addCell(cell362);

		PdfPCell cell363 = new PdfPCell(new Paragraph(spdDesc.getFuseBackUpOb().toString(), font1));
		table11.addCell(cell363);

		PdfPCell cell364 = new PdfPCell(new Paragraph(spdDesc.getFuseBackUpRem(), font1));
		table11.addCell(cell364);

		PdfPCell cell365 = new PdfPCell(new Paragraph("5(f)", font1));
		cell365.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell365.setRowspan(3);
		cell365.setGrayFill(0.92f);
		table11.addCell(cell365);

		PdfPCell cell366 = new PdfPCell(new Paragraph("	* Length of connecting wire in meters", font11));
		cell366.setGrayFill(0.92f);
		cell366.setColspan(3);
		table11.addCell(cell366);

		PdfPCell cell367 = new PdfPCell(new Paragraph("Phase", font1));
		cell367.setGrayFill(0.92f);
		table11.addCell(cell367);

		PdfPCell cell368 = new PdfPCell(new Paragraph(spdDesc.getLengthOfConnectingWirePhaseOb().toString(), font1));
		table11.addCell(cell368);

		PdfPCell cell369 = new PdfPCell(new Paragraph(spdDesc.getLengthOfConnectingWirePhaseRem(), font1));
		table11.addCell(cell369);

		PdfPCell cell370 = new PdfPCell(new Paragraph("Protective conductor", font1));
		cell370.setGrayFill(0.92f);
		table11.addCell(cell370);

		PdfPCell cell371 = new PdfPCell(
				new Paragraph(spdDesc.getLengthOfConnectingWireProtectiveOb().toString(), font1));
		table11.addCell(cell371);

		PdfPCell cell372 = new PdfPCell(new Paragraph(spdDesc.getLengthOfConnectingWireProtectiveRem(), font1));
		table11.addCell(cell372);

		PdfPCell cell373 = new PdfPCell(new Paragraph("5(g)", font1));
		cell373.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell373.setRowspan(3);
		cell373.setGrayFill(0.92f);
		table11.addCell(cell373);

		PdfPCell cell374 = new PdfPCell(new Paragraph("* Size of connecting wire in sq.mm", font11));
		cell374.setGrayFill(0.92f);
		cell374.setColspan(3);
		table11.addCell(cell374);

		PdfPCell cell375 = new PdfPCell(new Paragraph("Phase", font1));
		cell375.setGrayFill(0.92f);
		table11.addCell(cell375);

		PdfPCell cell376 = new PdfPCell(new Paragraph(spdDesc.getSizeOfConnectingWirePhaseOb().toString(), font1));
		table11.addCell(cell376);

		PdfPCell cell377 = new PdfPCell(new Paragraph(spdDesc.getSizeOfConnectingWirePhaseRem(), font1));
		table11.addCell(cell377);

		PdfPCell cell378 = new PdfPCell(new Paragraph("Protective conductor", font1));
		cell378.setGrayFill(0.92f);
		table11.addCell(cell378);

		PdfPCell cell379 = new PdfPCell(new Paragraph(spdDesc.getSizeOfConnectingWireProtectiveOb().toString(), font1));
		table11.addCell(cell379);

		PdfPCell cell380 = new PdfPCell(new Paragraph(spdDesc.getSizeOfConnectingWireProtectiveRem(), font1));
		table11.addCell(cell380);

		document.add(table11);
	}

}
