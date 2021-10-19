package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.Circuit;
import com.capeelectric.model.ConsumerUnit;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.IsolationCurrent;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.SupplyCharacteristicComment;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.InspectionServicePDF;
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
public class InspectionServiceImplPDF implements InspectionServicePDF {

	@Autowired
	private InspectionRepository inspectionRepository;

	@Override
	public List<PeriodicInspection> retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException {

		if (userName != null && !userName.isEmpty() && siteId != null) {

			Document document = new Document(PageSize.A4, 36, 36, 50, 36);
			document.setMargins(68, 68, 62, 68);

			try {
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream("PrintInspectionDetailsData.pdf"));

				Optional<PeriodicInspection> inspectionDetails = inspectionRepository.findBySiteId(siteId);
				PeriodicInspection inspection = inspectionDetails.get();

				List<IpaoInspection> ipo = inspection.getIpaoInspection();
				IpaoInspection ipoInspection = ipo.get(0);

				List<ConsumerUnit> consumer = ipoInspection.getConsumerUnit();
				ConsumerUnit consumerUnit = consumer.get(0);

				List<Circuit> circuitDetails = ipoInspection.getCircuit();
				Circuit circuit = circuitDetails.get(0);

				List<IsolationCurrent> isolationCurrentDetails = ipoInspection.getIsolationCurrent();
				IsolationCurrent isolationCurrent = isolationCurrentDetails.get(0);

				List<PeriodicInspectionComment> reportComments = inspection.getPeriodicInspectorComment();
				PeriodicInspectionComment comments = reportComments.get(0);

				document.open();

				Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Paragraph paragraph1 = new Paragraph("TIC of LV electrical installation", font11B);
				paragraph1.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph1);

				float[] pointColumnWidths1 = { 100F };
				PdfPTable part3 = new PdfPTable(pointColumnWidths1);

				part3.setWidthPercentage(100); // Width 100%
				part3.setSpacingBefore(5f); // Space before table
				part3.setSpacingAfter(5f); // Space after table
				part3.setWidthPercentage(100);
				part3.getDefaultCell().setBorder(0);

				PdfPCell basic = new PdfPCell(new Paragraph("Part - 3: Inspection",
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
				basic.setBackgroundColor(new GrayColor(0.82f));
				basic.setHorizontalAlignment(Element.ALIGN_LEFT);
				basic.setBorder(PdfPCell.NO_BORDER);
				part3.addCell(basic);
				document.add(part3);

				Font noteFont = new Font(BaseFont.createFont(), 8, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
				Paragraph paragraph4 = new Paragraph(
						"Note: For periodic inspection, a visual inspection should be made to find out the external condition of all electrical equipment which is not concealed. Further detailed inspection, including partial dismantling of equipment (as required), should be carried out as agreed with the customer.",
						noteFont);
				document.add(paragraph4);

				for (IpaoInspection arr : ipo) {

					inspectionIteration(document, arr, consumerUnit, circuit, isolationCurrent);

				}

				if (comments.getViewerUserName() != null && comments.getInspectorUserName() != null) {

					document.newPage();

					PdfPTable table199 = new PdfPTable(1);
					table199.setWidthPercentage(100); // Width 100%
					table199.setSpacingBefore(10f); // Space before table
					table199.setWidthPercentage(100);
					table199.getDefaultCell().setBorder(0);
					Font font = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD);
					PdfPCell cell65 = new PdfPCell(
							new Paragraph(15, "Section - 9: Viewer And Inspector Comment:", font));
					cell65.setBorder(PdfPCell.NO_BORDER);
					cell65.setBackgroundColor(BaseColor.LIGHT_GRAY);
					table199.addCell(cell65);
					document.add(table199);
					Font font61 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
					Font font91 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
					float[] pointColumnWidths4 = { 90F, 90F, 90F, 90F };

					PdfPTable table44 = new PdfPTable(pointColumnWidths4);
					table44.setWidthPercentage(100); // Width 100%
					table44.setSpacingBefore(10f); // Space before table
					table44.setWidthPercentage(100);

					PdfPCell cell55 = new PdfPCell(new Paragraph(comments.getViewerUserName(), font91));
					cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell371 = new PdfPCell(new Paragraph("ViewerUserName:", font91));
					cell371.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell371.setFixedHeight(25f);
					cell371.setGrayFill(0.92f);
					table44.addCell(cell371);
					table44.addCell(cell55);
					PdfPCell cell381 = new PdfPCell(new Paragraph(comments.getInspectorUserName(), font91));
					cell381.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell3711 = new PdfPCell(new Paragraph("InspectorUserName:", font91));
					cell3711.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell3711.setFixedHeight(25f);
					cell3711.setGrayFill(0.92f);
					table44.addCell(cell3711);
					table44.addCell(cell381);

					PdfPCell cell561 = new PdfPCell(new Paragraph("ViewerComment Date:", font91));
					cell561.setGrayFill(0.92f);
					cell561.setHorizontalAlignment(Element.ALIGN_CENTER);

					PdfPCell cell5611 = new PdfPCell(new Paragraph("ViewerComment:", font91));
					cell5611.setGrayFill(0.92f);
					cell5611.setHorizontalAlignment(Element.ALIGN_CENTER);
					table44.addCell(cell5611);
					table44.addCell(cell561);

					PdfPCell cell401 = new PdfPCell(new Paragraph("InspectorComment Date:", font91));
					cell401.setGrayFill(0.92f);
					cell401.setHorizontalAlignment(Element.ALIGN_CENTER);
					PdfPCell cell391 = new PdfPCell(new Paragraph("InspectorComment:", font91));
					cell391.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell391.setGrayFill(0.92f);
					table44.addCell(cell391);
					table44.addCell(cell401);

					tableData(table44, reportComments);

					document.add(table44);
				}

				document.close();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new InspectionException("Invalid Inputs");
		}
		return null;
	}

	private void tableData(PdfPTable table44, List<PeriodicInspectionComment> reportComments)
			throws DocumentException, IOException {

		Collections.sort(reportComments, new Comparator<PeriodicInspectionComment>() {
			public int compare(PeriodicInspectionComment periodic1, PeriodicInspectionComment periodic2) {

				if (periodic1.getViewerDate() != null && periodic2.getViewerDate() != null) {

					return periodic1.getViewerDate().compareTo(periodic2.getViewerDate());

				} else {
					return 0;
				}
			}
		});

		Collections.sort(reportComments, new Comparator<PeriodicInspectionComment>() {
			public int compare(PeriodicInspectionComment periodic1, PeriodicInspectionComment periodic2) {

				if (periodic1.getInspectorDate() != null && periodic2.getInspectorDate() != null) {

					return periodic1.getInspectorDate().compareTo(periodic2.getInspectorDate());

				} else {
					return 0;
				}
			}
		});

		for (PeriodicInspectionComment arr : reportComments) {
			Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase(arr.getViewerComment(), font));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table44.addCell(cell);

			if (arr.getViewerDate() != null) {
				cell.setPhrase(new Phrase(arr.getViewerDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No viewer date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}
			cell.setPhrase(new Phrase(arr.getInspectorComment(), font));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table44.addCell(cell);

			if (arr.getInspectorDate() != null) {
				cell.setPhrase(new Phrase(arr.getInspectorDate().toString(), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table44.addCell(cell);
			} else {
				cell.setPhrase(new Phrase("No inspector date available", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table44.addCell(cell);
			}
		}
	}

	private void inspectionIteration(Document document, IpaoInspection ipoInspection, ConsumerUnit consumerUnit,
			Circuit circuit, IsolationCurrent isolationCurrent) throws DocumentException, IOException {

		Font font10B = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths4 = { 100F };
		PdfPTable part3 = new PdfPTable(pointColumnWidths4);

		part3.setWidthPercentage(100); // Width 100%
		part3.setSpacingBefore(5f); // Space before table
		part3.setSpacingAfter(5f); // Space after table
		part3.setWidthPercentage(100);
		part3.getDefaultCell().setBorder(0);

		PdfPCell Section1 = new PdfPCell(new Paragraph("Section - 1: Incoming equipment",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		Section1.setBackgroundColor(new GrayColor(0.82f));
		Section1.setHorizontalAlignment(Element.ALIGN_LEFT);
		Section1.setBorder(PdfPCell.NO_BORDER);
		part3.addCell(Section1);
		document.add(part3);

		float[] pointColumnWidths = { 100F, 100F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths);

		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(5f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.setWidthPercentage(100);
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell80 = new PdfPCell(new Paragraph(ipoInspection.getLocationNumber().toString()));
		table1.addCell(new Phrase("Location number:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell80.setFixedHeight(30f);
		cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell80.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell80);

		PdfPCell cell36 = new PdfPCell(
				new Paragraph("Location name:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell36.setBackgroundColor(new GrayColor(0.93f));
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell36.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell36);
		PdfPCell cell81 = new PdfPCell(
				new Paragraph(ipoInspection.getLocationName(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell81.setBackgroundColor(new GrayColor(0.93f));
		cell81.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell81);

		PdfPCell cell = new PdfPCell(
				new Paragraph(ipoInspection.getServiceCable(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table1.addCell(new Phrase("Service cable:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell.setFixedHeight(30f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell);

		PdfPCell cell37 = new PdfPCell(
				new Paragraph("Service cut out / fuse:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell37.setBackgroundColor(new GrayColor(0.93f));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell37.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell37);
		PdfPCell cell1 = new PdfPCell(
				new Paragraph(ipoInspection.getServiceFuse(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		cell1.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterDistributor(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table1.addCell(new Phrase("Meter tails – Distributor:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell2.setFixedHeight(30f);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell2);

		PdfPCell cell38 = new PdfPCell(
				new Paragraph("Meter tails – Consumer:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell38.setBackgroundColor(new GrayColor(0.93f));
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell38.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell38);
		PdfPCell cell3 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterConsumer(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setBackgroundColor(new GrayColor(0.93f));
		cell3.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell3);

		PdfPCell cell4 = new PdfPCell(
				new Paragraph(ipoInspection.getMeterEqu(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table1.addCell(new Phrase("Metering Equipment:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell4.setFixedHeight(30f);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell4.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell4);

		PdfPCell cell39 = new PdfPCell(new Paragraph("Isolator (Means to isolate the public supply system):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell39.setBackgroundColor(new GrayColor(0.93f));
		cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell39.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell39);
		PdfPCell cell5 = new PdfPCell(
				new Paragraph(ipoInspection.getIsolator(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		cell5.setBorder(PdfPCell.NO_BORDER);
		table1.addCell(cell5);
		document.add(table1);

		Font noteFont = new Font(BaseFont.createFont(), 8, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph8 = new Paragraph(
				"Note: Where inadequacies in distributor’s equipment are encountered, it is recommended that the user informs this to the appropriate authority.",
				noteFont);
		document.add(paragraph8);

		float[] pointColumnWidths5 = { 100F };
		PdfPTable section2 = new PdfPTable(pointColumnWidths5);

		section2.setWidthPercentage(100); // Width 100%
		section2.setSpacingBefore(10f); // Space before table
		section2.setSpacingAfter(5f); // Space after table
		section2.setWidthPercentage(100);
		section2.getDefaultCell().setBorder(0);

		PdfPCell arrangements = new PdfPCell(
				new Paragraph("Section - 2: Arrangements for parallel or switched alternative sources of supply",
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		arrangements.setBackgroundColor(new GrayColor(0.82f));
		arrangements.setHorizontalAlignment(Element.ALIGN_LEFT);
		arrangements.setBorder(PdfPCell.NO_BORDER);
		section2.addCell(arrangements);
		document.add(section2);

		float[] pointColumnWidths1 = { 200F, 50F };
		PdfPTable table2 = new PdfPTable(pointColumnWidths1);

		table2.setWidthPercentage(100); // Width 100%
//		table2.setSpacingBefore(5f); // Space before table
		table2.setSpacingAfter(5f); // Space after table
		table2.setWidthPercentage(100);
		table2.getDefaultCell().setBorder(0);

		PdfPCell cell6 = new PdfPCell(new Paragraph(ipoInspection.getEarthingArrangement(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table2.addCell(new Phrase("Dedicated earthing arrangement independent to that of public supply:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell6.setFixedHeight(15f);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell6);

		PdfPCell cell40 = new PdfPCell(new Paragraph(
				"Presence of adequate arrangements where generator to operate in parallel with the public supply system:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell40.setBackgroundColor(new GrayColor(0.93f));
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell40.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell40);
		PdfPCell cell7 = new PdfPCell(new Paragraph(ipoInspection.getAdequateArrangement(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell7.setFixedHeight(15f);
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		cell7.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell7);

		PdfPCell cell8 = new PdfPCell(new Paragraph(ipoInspection.getConnectionGenerator(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table2.addCell(new Phrase(
				"Correct connections of generator in parallel. (note: Special attention to circulating currents):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell8.setFixedHeight(15f);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell8.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell8);

		PdfPCell cell41 = new PdfPCell(new Paragraph("Compatibility of characteristics of means of generation:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell41.setBackgroundColor(new GrayColor(0.93f));
		cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell41.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell41);
		PdfPCell cell9 = new PdfPCell(new Paragraph(ipoInspection.getCompatibilityCharacteristics(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell9.setFixedHeight(15f);
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBackgroundColor(new GrayColor(0.93f));
		cell9.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell9);

		PdfPCell cell10 = new PdfPCell(new Paragraph(ipoInspection.getAutomaticDisconnectGenerator(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table2.addCell(new Phrase(
				"Means to provide automatic disconnection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell10.setFixedHeight(15f);
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell10);

		PdfPCell cell42 = new PdfPCell(new Paragraph(
				"Means to prevent connection of generator in the event of loss of public supply system or voltage or frequency deviation beyond declared values:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell42.setBackgroundColor(new GrayColor(0.93f));
		cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell42.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell42);
		PdfPCell cell11 = new PdfPCell(new Paragraph(ipoInspection.getPreventConnectGenerator(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell11.setFixedHeight(15f);
		cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell11.setBackgroundColor(new GrayColor(0.93f));
		cell11.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell11);

		PdfPCell cell12 = new PdfPCell(
				new Paragraph(ipoInspection.getIsolateGenerator(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table2.addCell(new Phrase("Means to isolate generator from the public supply system :",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell12.setFixedHeight(15f);
		cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell12.setBorder(PdfPCell.NO_BORDER);
		table2.addCell(cell12);
		document.add(table2);

		PdfPTable section3 = new PdfPTable(pointColumnWidths5);

		section3.setWidthPercentage(100); // Width 100%
		section3.setSpacingBefore(5f); // Space before table
		section3.setSpacingAfter(5f); // Space after table
		section3.setWidthPercentage(100);
		section3.getDefaultCell().setBorder(0);

		PdfPCell Automatic = new PdfPCell(new Paragraph("Section - 3: Automatic disconnection of supply",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		Automatic.setBackgroundColor(new GrayColor(0.82f));
		Automatic.setHorizontalAlignment(Element.ALIGN_LEFT);
		Automatic.setBorder(PdfPCell.NO_BORDER);
		section3.addCell(Automatic);
		document.add(section3);

		float[] pointColumnWidths3 = { 200F, 40F };
		PdfPTable table3 = new PdfPTable(pointColumnWidths3);

		table3.setWidthPercentage(100); // Width 100%
//		table3.setSpacingBefore(10f); // Space before table
		table3.setSpacingAfter(10f); // Space after table
		table3.setWidthPercentage(100);
		table3.getDefaultCell().setBorder(0);

		PdfPCell cell14 = new PdfPCell(
				new Paragraph(ipoInspection.getMainEarting(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table3.addCell(
				new Phrase("Main earthing / bonding arrangements:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell14.setFixedHeight(25f);
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell14.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell14);

		PdfPCell cell43 = new PdfPCell(new Paragraph(
				"Presence and adequacy of energy suppliers earthing arrangement or installation earth electrode arrangement:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell43.setBackgroundColor(new GrayColor(0.93f));
		cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell43.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell43);
		PdfPCell cell15 = new PdfPCell(new Paragraph(ipoInspection.getEarthElectordeArrangement(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell15.setBackgroundColor(new GrayColor(0.93f));
		cell15.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph(ipoInspection.getEarthConductorConnection(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table3.addCell(new Phrase("Presence and adequacy of earthing conductor and connections:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell16.setFixedHeight(30f);
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell16.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell16);

		PdfPCell cell44 = new PdfPCell(new Paragraph("Accessibility of earthing conductor connections:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell44.setBackgroundColor(new GrayColor(0.93f));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell44.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell44);
		PdfPCell cell17 = new PdfPCell(
				new Paragraph(ipoInspection.getAccessibility(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		cell17.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(
				new Paragraph(ipoInspection.getAainProtectBonding(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table3.addCell(new Phrase(
				"Presence and adequacy of main protective bonding conductors and connections (colour, sizes, termination, and provision of independent earthing):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell18.setFixedHeight(45f);
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell18.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell18);

		PdfPCell cell45 = new PdfPCell(new Paragraph("Accessibility of all protective bonding connections:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell45.setBackgroundColor(new GrayColor(0.93f));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell45.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell45);
		PdfPCell cell19 = new PdfPCell(
				new Paragraph(ipoInspection.getAllProtectBonding(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBackgroundColor(new GrayColor(0.93f));
		cell19.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(new Paragraph(ipoInspection.getAllAppropriateLocation(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table3.addCell(
				new Phrase("Presence and adequacy of electrical earthing/bonding labels at all appropriate locations:",
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell20.setFixedHeight(35f);
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell20.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell20);

		PdfPCell cell46 = new PdfPCell(new Paragraph("Accessibility of FELV requirements satisfied:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell46.setBackgroundColor(new GrayColor(0.93f));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell46);
		PdfPCell cell21 = new PdfPCell(
				new Paragraph(ipoInspection.getFelvRequirement(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setBackgroundColor(new GrayColor(0.93f));
		cell21.setBorder(PdfPCell.NO_BORDER);
		table3.addCell(cell21);
		document.add(table3);

		PdfPTable section4 = new PdfPTable(pointColumnWidths5);
		section4.setWidthPercentage(100); // Width 100%
		section4.setSpacingBefore(5f); // Space before table
		section4.setSpacingAfter(3f); // Space after table
		section4.setWidthPercentage(100);
		section4.getDefaultCell().setBorder(0);

		PdfPCell protection = new PdfPCell(new Paragraph("Section - 4: Other methods of protection ",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		protection.setBackgroundColor(new GrayColor(0.82f));
		protection.setHorizontalAlignment(Element.ALIGN_LEFT);
		protection.setBorder(PdfPCell.NO_BORDER);
		section4.addCell(protection);
		document.add(section4);

		Paragraph paragraph13 = new Paragraph(
				"Applicable to locations where automatic disconnection of supply is not employed. If any of the methods listed below are employed details should be provided on separate page",
				noteFont);
		document.add(paragraph13);

		Paragraph gap2 = new Paragraph(6, " ", font10B);
		document.add(gap2);

		PdfPTable Basic = new PdfPTable(pointColumnWidths5);
		Basic.setWidthPercentage(100); // Width 100%
		Basic.setSpacingBefore(5f); // Space before table
		Basic.setSpacingAfter(3f); // Space after table
		Basic.setWidthPercentage(100);
		Basic.getDefaultCell().setBorder(0);

		PdfPCell fault = new PdfPCell(new Paragraph("4.1: Basic and fault protection.",
				new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		fault.setBackgroundColor(new GrayColor(0.82f));
		fault.setHorizontalAlignment(Element.ALIGN_LEFT);
		fault.setBorder(PdfPCell.NO_BORDER);
		Basic.addCell(fault);
		document.add(Basic);

		PdfPTable table4 = new PdfPTable(pointColumnWidths3);

		table4.setWidthPercentage(100); // Width 100%
		table4.setSpacingBefore(5f); // Space before table
		table4.setSpacingAfter(5f); // Space after table
		table4.setWidthPercentage(100);
		table4.getDefaultCell().setBorder(0);

		PdfPCell cell22 = new PdfPCell(
				new Paragraph(ipoInspection.getSelvSystem(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table4.addCell(new Phrase("SELV system, including the source and associated circuits:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell22.setFixedHeight(10f);
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell22);

		PdfPCell cell47 = new PdfPCell(new Paragraph("PELV system, including the source and associated circuits:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell47.setBackgroundColor(new GrayColor(0.93f));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell47.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell47);
		PdfPCell cell23 = new PdfPCell(
				new Paragraph(ipoInspection.getPelvSystem(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell23.setFixedHeight(10f);
		cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell23.setBackgroundColor(new GrayColor(0.93f));
		cell23.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell23);

		PdfPCell cell24 = new PdfPCell(
				new Paragraph(ipoInspection.getDoubleInsulation(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table4.addCell(new Phrase("Double insulation (Class II or equivalent equipment and associated circuits):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell24.setFixedHeight(10f);
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell24.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell24);

		PdfPCell cell48 = new PdfPCell(
				new Paragraph("Reinforced insulation:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell48.setBackgroundColor(new GrayColor(0.93f));
		cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell48.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell48);
		PdfPCell cell25 = new PdfPCell(new Paragraph(ipoInspection.getReinforcedInsulation(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell25.setFixedHeight(10f);
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		cell25.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph(ipoInspection.getBasicElectricalSepartion(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table4.addCell(new Phrase("Electrical separation for one item of equipment (shaver supply unit):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell26.setFixedHeight(10f);
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell26.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(cell26);

		PdfPCell isolatorCellLabel = new PdfPCell(new Paragraph("Isolator (Means to isolate the public supply system):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		isolatorCellLabel.setBackgroundColor(new GrayColor(0.93f));
		isolatorCellLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
		isolatorCellLabel.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(isolatorCellLabel);
		PdfPCell isolatorCell = new PdfPCell(new Paragraph(ipoInspection.getIsolatePublicSupply(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		isolatorCell.setFixedHeight(10f);
		isolatorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		isolatorCell.setBackgroundColor(new GrayColor(0.93f));
		isolatorCell.setBorder(PdfPCell.NO_BORDER);
		table4.addCell(isolatorCell);
		document.add(table4);

		PdfPTable BasicPro = new PdfPTable(pointColumnWidths5);
		BasicPro.setWidthPercentage(100); // Width 100%
		BasicPro.setSpacingBefore(5f); // Space before table
		BasicPro.setSpacingAfter(3f); // Space after table
		BasicPro.setWidthPercentage(100);
		BasicPro.getDefaultCell().setBorder(0);

		PdfPCell liveParts = new PdfPCell(
				new Paragraph("4.2: Basic protection (prevention of contact with live parts).",
						new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		liveParts.setBackgroundColor(new GrayColor(0.82f));
		liveParts.setHorizontalAlignment(Element.ALIGN_LEFT);
		liveParts.setBorder(PdfPCell.NO_BORDER);
		BasicPro.addCell(liveParts);
		document.add(BasicPro);

		PdfPTable table5 = new PdfPTable(pointColumnWidths3);

		table5.setWidthPercentage(100); // Width 100%
		table5.setSpacingBefore(5f); // Space before table
		table5.setSpacingAfter(5f); // Space after table
		table5.setWidthPercentage(100);
		table5.getDefaultCell().setBorder(0);

		PdfPCell cell27 = new PdfPCell(new Paragraph(ipoInspection.getInsulationLiveParts(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table5.addCell(
				new Phrase("Insulation of live parts (conductors completely covered with durable insulating material):",
						new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell27.setFixedHeight(35f);
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell27.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell27);

		PdfPCell cell50 = new PdfPCell(new Paragraph("Barriers or enclosures (correct IP rating):",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell50.setBackgroundColor(new GrayColor(0.93f));
		cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell50.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell50);
		PdfPCell cell29 = new PdfPCell(
				new Paragraph(ipoInspection.getBarriersEnclosers(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell29.setBackgroundColor(new GrayColor(0.93f));
		cell29.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell29);

		PdfPCell cell30 = new PdfPCell(
				new Paragraph(ipoInspection.getObstacles(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table5.addCell(new Phrase("Obstacles:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell30.setFixedHeight(25f);
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell30.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell30);

		PdfPCell cell51 = new PdfPCell(
				new Paragraph("Placing out of reach:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell51.setBackgroundColor(new GrayColor(0.93f));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell51);
		PdfPCell cell31 = new PdfPCell(
				new Paragraph(ipoInspection.getPlacingOutReach(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell31.setBackgroundColor(new GrayColor(0.93f));
		cell31.setBorder(PdfPCell.NO_BORDER);
		table5.addCell(cell31);
		document.add(table5);

		PdfPTable Fault = new PdfPTable(pointColumnWidths5);
		Fault.setWidthPercentage(100); // Width 100%
		Fault.setSpacingBefore(5f); // Space before table
		Fault.setSpacingAfter(3f); // Space after table
		Fault.setWidthPercentage(100);
		Fault.getDefaultCell().setBorder(0);

		PdfPCell protection1 = new PdfPCell(
				new Paragraph("4.3: Fault protection.", new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		protection1.setBackgroundColor(new GrayColor(0.82f));
		protection1.setHorizontalAlignment(Element.ALIGN_LEFT);
		protection1.setBorder(PdfPCell.NO_BORDER);
		Fault.addCell(protection1);
		document.add(Fault);

		PdfPTable table6 = new PdfPTable(pointColumnWidths3);

		table6.setWidthPercentage(100); // Width 100%
		table6.setSpacingBefore(5f); // Space before table
//		table6.setSpacingAfter(10f); // Space after table
		table6.setWidthPercentage(100);
		table6.getDefaultCell().setBorder(0);

		PdfPCell cell32 = new PdfPCell(new Paragraph(ipoInspection.getFaultNonConductLocation(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table6.addCell(new Phrase("Non-Conducting location – earth free local equipotential bonding:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell32.setFixedHeight(35f);
		cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell32.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell32);

		PdfPCell cell52 = new PdfPCell(
				new Paragraph("Electrical separation:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell52.setBackgroundColor(new GrayColor(0.93f));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell52.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell52);
		PdfPCell cell33 = new PdfPCell(new Paragraph(ipoInspection.getFaultElectricalSepartion(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell33.setBackgroundColor(new GrayColor(0.93f));
		cell33.setBorder(PdfPCell.NO_BORDER);
		table6.addCell(cell33);
		document.add(table6);

		PdfPTable Additional = new PdfPTable(pointColumnWidths5);
		Additional.setWidthPercentage(100); // Width 100%
		Additional.setSpacingBefore(10f); // Space before table
		Additional.setSpacingAfter(3f); // Space after table
		Additional.setWidthPercentage(100);
		Additional.getDefaultCell().setBorder(0);

		PdfPCell protection2 = new PdfPCell(new Paragraph("4.4: Additional protection.",
				new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
		protection2.setBackgroundColor(new GrayColor(0.82f));
		protection2.setHorizontalAlignment(Element.ALIGN_LEFT);
		protection2.setBorder(PdfPCell.NO_BORDER);
		Additional.addCell(protection2);
		document.add(Additional);

		PdfPTable table7 = new PdfPTable(pointColumnWidths3);

		table7.setWidthPercentage(100); // Width 100%
		table7.setSpacingBefore(5f); // Space before table
		table7.setSpacingAfter(5f); // Space after table
		table7.setWidthPercentage(100);
		table7.getDefaultCell().setBorder(0);

		PdfPCell cell34 = new PdfPCell(
				new Paragraph(ipoInspection.getOperatingCurrent(), new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		table7.addCell(new Phrase("RCD(s) not exceeding 30 mA operating current:",
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
//		cell34.setFixedHeight(30f);
		cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell34.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell34);

		PdfPCell cell53 = new PdfPCell(
				new Paragraph("Supplementary bonding:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell53.setBackgroundColor(new GrayColor(0.93f));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell53.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell53);
		PdfPCell cell35 = new PdfPCell(new Paragraph(ipoInspection.getSupplementaryBonding(),
				new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell35.setBackgroundColor(new GrayColor(0.93f));
		cell35.setBorder(PdfPCell.NO_BORDER);
		table7.addCell(cell35);
		document.add(table7);

		Font font10N = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.NORMAL, BaseColor.BLACK);
		Paragraph paragraph18 = new Paragraph("Specific examples of section 4:", font10N);
		document.add(paragraph18);

		document.newPage();

		PdfPTable Section5 = new PdfPTable(pointColumnWidths5);
		Section5.setWidthPercentage(100); // Width 100%
		Section5.setSpacingBefore(10f); // Space before table
		Section5.setSpacingAfter(3f); // Space after table
		Section5.setWidthPercentage(100);
		Section5.getDefaultCell().setBorder(0);

		PdfPCell consumer = new PdfPCell(
				new Paragraph("Section - 5: Consumer unit(s) / distribution board(s) / distribution equipment",
						new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		consumer.setBackgroundColor(new GrayColor(0.82f));
		consumer.setHorizontalAlignment(Element.ALIGN_LEFT);
		consumer.setBorder(PdfPCell.NO_BORDER);
		Section5.addCell(consumer);
		document.add(Section5);

		float[] pointColumnWidths2 = { 30F, 255F, 65F };

		PdfPTable table8 = new PdfPTable(pointColumnWidths2); // 3 columns.
		table8.setWidthPercentage(100); // Width 100%
		table8.setSpacingBefore(15f); // Space before table
		table8.setSpacingAfter(5f); // Space after table
		table8.setWidthPercentage(100);

		addRow(table8, "1",
				"Adequacy of access and working space for items of electrical equipment including switchgear",
				consumerUnit.getAccessWorking());
		addRow(table8, "2", "Security of fixing ", consumerUnit.getSecurityFixing());
		addRow(table8, "3", "Insulation of live parts not damaged during erection", consumerUnit.getLivePartsDamage());
		addRow(table8, "4", "Adequacy / security of barriers", consumerUnit.getSecurityBarriers());
		addRow(table8, "5", "Suitability of enclosure(s) for IP and fire ratings",
				consumerUnit.getSuitabilityEnclosure());
		addRow(table8, "6", "Enclosure not damaged during installation", consumerUnit.getEnclosureDamaged());
		addRow(table8, "7", "Presence and effectiveness of obstacles", consumerUnit.getPresenceObstacles());
		addRow(table8, "8", "Placing out of reach", consumerUnit.getPlacingOutOfConsumer());
		addRow(table8, "9", "Presence of main switches linked where required", consumerUnit.getPresenceMainSwitches());
		addRow(table8, "10", "Operation of main switches (functional checks)", consumerUnit.getOperationMainSwitches());
		addRow(table8, "11", "Manual operation of circuit breakers and RCD’s to prove functionally",
				consumerUnit.getManualCircuitBreakers());
		addRow(table8, "12",
				"Confirmation that integral test button / switch causes RCD’s to trip when operated (functional check)",
				consumerUnit.getSwitchCausesRcd());
		addRow(table8, "13", "RCD’s provided for fault protection, where specified",
				consumerUnit.getRcdFaultProtection());
		addRow(table8, "14", "RCD’s provided for additional protection, where specified",
				consumerUnit.getRcdAdditionalProtection());
		addRow(table8, "15", "Confirmation of over voltage protection (SPD’s) provided where specified",
				consumerUnit.getOverVoltageProtection());
		addRow(table8, "16", "Confirmation of indication that SPD is functional", consumerUnit.getIndicationOfSpd());
		addRow(table8, "17", "Presence of RCD quarterly test notice at or near origin",
				consumerUnit.getRcdQuarterlyTest());
		addRow(table8, "18",
				"Presence of diagrams charts or schedules at or near each distribution board where required",
				consumerUnit.getDiagramsCharts());
		addRow(table8, "19",
				"presence of nonstandard (mixed) cable colour warning notice near appropriate distribution board, as required",
				consumerUnit.getNonstandardCableColour());
		addRow(table8, "20", "Presence of alternative supply - warning notice at or near the origin",
				consumerUnit.getNonstandardCableColour());
		addRow(table8, "21",
				"Presence of alternative supply - warning notice at or near the meter position, if remote from origin",
				consumerUnit.getAlSupplyOfMeter());
		addRow(table8, "22",
				"Presence of alternative supply - warning notice at or near the distribution board to which alternative sources are connected",
				consumerUnit.getAlSupplyDistribution());
		addRow(table8, "23",
				"Presence of alternative supply - warning notice at or near all points of isolation of ALL sources of supply",
				consumerUnit.getAllPointsIsolation());
		addRow(table8, "24", "Presence of next inspection recommendation label", consumerUnit.getNextInspection());
		addRow(table8, "25", "Presence of other required labelling", consumerUnit.getOtherRequiredLabelling());
		addRow(table8, "26",
				"Selection of protective devices and bases correct type and rating (no signs of unacceptable thermal damage, arcing or overheating)",
				consumerUnit.getBasesCorrectType());
		addRow(table8, "27", "Single pole protective devices in line conductor only", consumerUnit.getSinglePole());
		addRow(table8, "28", "Protection against mechanical damage where cables enter equipment",
				consumerUnit.getMechanicalDamage());
		addRow(table8, "29",
				"Protection against electromagnetic/heating effects where cables enter ferromagnetic enclosures",
				consumerUnit.getElectromagnetic());
		addRow(table8, "30",
				"Confirmation that all conductor connections including connections to busbars are correctly located in terminals and are tight and secure",
				consumerUnit.getAllConductorCon());
		document.add(table8);

		document.newPage();

		PdfPTable Section6 = new PdfPTable(pointColumnWidths5);
		Section6.setWidthPercentage(100); // Width 100%
		Section6.setSpacingBefore(10f); // Space before table
		Section6.setSpacingAfter(3f); // Space after table
		Section6.setWidthPercentage(100);
		Section6.getDefaultCell().setBorder(0);

		PdfPCell circuit1 = new PdfPCell(
				new Paragraph("Section - 6: Circuits", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		circuit1.setHorizontalAlignment(Element.ALIGN_LEFT);
		circuit1.setBackgroundColor(new GrayColor(0.82f));
		circuit1.setBorder(PdfPCell.NO_BORDER);
		Section6.addCell(circuit1);
		document.add(Section6);

		PdfPTable table9 = new PdfPTable(pointColumnWidths2);

		table9.setWidthPercentage(100); // Width 100%
		table9.setSpacingBefore(15f); // Space before table
		table9.setSpacingAfter(5f); // Space after table
		table9.setWidthPercentage(100);
		table9.getDefaultCell().setBorder(0);

		addRow1(table9, "1", "Identification of conductors including main earthing / bonding arrangements",
				circuit.getCableInstallation());
		addRow1(table9, "2",
				"Cable installation methods (including support) suitable for the location(s) and external influences (Cables correctly supported throughout)",
				circuit.getIdentificationConductors());
		addRow1(table9, "3", "Examination of cables for signs of mechanical damage during installation",
				circuit.getExaminationCables());
		addRow1(table9, "4", "Examination of insulation of live parts not damaged during erection",
				circuit.getExaminationInsulation());
		addRow1(table9, "5", "Non-Sheathed cables protected by enclosure in conduit, ducting or trucking",
				circuit.getNonSheathedCables());
		addRow1(table9, "6", "Suitability of containment systems including flexible conduit",
				circuit.getContainmentSystems());
		addRow1(table9, "7", "Correct temperature rating of cable insulation", circuit.getTemperatureRating());
		addRow1(table9, "8", "Cables correctly terminated in enclosures", circuit.getCablesTerminated());
		addRow1(table9, "9",
				"Adequacy of conductors for current-carrying capacity with respect to type and nature of the installation",
				circuit.getCurrentCarryCapacity());
		addRow1(table9, "10", "Adequacy of protective devices, type and fault current rating for fault protection",
				circuit.getAdequacyProtectDevices());
		addRow1(table9, "11", "Presence, adequacy, and correct termination of protective conductors",
				circuit.getPresenceProtectConductors());
		addRow1(table9, "12", "Co-ordination between conductors and overload protective devices",
				circuit.getCoOrdination());
		addRow1(table9, "13",
				"Wiring systems and cable installation methods/ practices with regard to the type and nature of installation and external influences",
				circuit.getWiringSystems());
		addRow1(table9, "14",
				"Cables concealed under floors above ceilings, in wall adequately protected against damage by contact with fixings",
				circuit.getCablesConcealUnderFloors());
		addRow1(table9, "15",
				"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for circuits used to supply mobile equipment not exceeding 32A rating for use outdoors in all cases",
				circuit.getOperatingCurrentCircuits());
		addRow1(table9, "16",
				"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for all socket outlets of rating 20A or less provided for use by ordinary persons unless exempt",
				circuit.getOperatingCurrentSocket());
		addRow1(table9, "17",
				"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for cables concealed in walls at a depth of less than 50mm",
				circuit.getCablesConcDepth());
		addRow1(table9, "18",
				"Additional protection by RCD’s having residual operating current (I∆n) not exceeding 30mA for Cables concealed in walls / sections containing metal sections regardless of depth",
				circuit.getSectionsRegardlessDepth());
		addRow1(table9, "19", "Provision of fire barriers, sealing arrangements so as to minimize the spread of fire",
				circuit.getProvisionFireBarriers());
		addRow1(table9, "20", "Segregation/separation of Band I (ELV) and Band II (LV) circuits",
				circuit.getSeparationBand());
		addRow1(table9, "21", "Segregation/separation of electrical and non-electrical services",
				circuit.getSeparationElectrical());
		addRow1(table9, "22", "Condition of circuit accessories", circuit.getConditionCircuitAccessories());
		addRow1(table9, "23",
				"Cables and conductors correctly terminated, enclosed and with no undue mechanical strain",
				circuit.getConductorCorrectTerminated());
		addRow1(table9, "24", "No basic insulation of a conductor visible outside enclosure",
				circuit.getConductorVisibleOutside());
		addRow1(table9, "25", "Connections of live conductors adequately enclosed", circuit.getConnLiveConductors());
		addRow1(table9, "26", "Adequately connected at the point of entry to enclosure (glands, bushes etc.)",
				circuit.getAdequatelyConnectedEnclosure());
		addRow1(table9, "27", "Suitability of circuit accessories for external influences",
				circuit.getSuitabilityCircuitAccessories());
		addRow1(table9, "28",
				"Condition of accessories including socket-outlets, switches and joint boxes (Circuit accessories not damaged, securely fixed, correctly connected, suitable for external influences)",
				circuit.getConditionAccessories());
		addRow1(table9, "29", "Single-pole devices for switching or protection in line conductors only",
				circuit.getSinglePoleDevices());
		addRow1(table9, "30",
				"Adequacy of connections, including protective conductors, within accessories and fixed and stationary equipment",
				circuit.getAdequacyConnections());
		addRow1(table9, "31",
				"Presence, operation, and correct location of appropriate devices for isolation and switching",
				circuit.getIsolationSwitching());
		document.add(table9);

		document.newPage();

		PdfPTable Section7 = new PdfPTable(pointColumnWidths5);
		Section7.setWidthPercentage(100); // Width 100%
		Section7.setSpacingBefore(10f); // Space before table
		Section7.setSpacingAfter(3f); // Space after table
		Section7.setWidthPercentage(100);
		Section7.getDefaultCell().setBorder(0);

		PdfPCell isolation = new PdfPCell(new Paragraph("Section - 7: Isolation and switching",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		isolation.setHorizontalAlignment(Element.ALIGN_LEFT);
		isolation.setBackgroundColor(new GrayColor(0.82f));
		isolation.setBorder(PdfPCell.NO_BORDER);
		Section7.addCell(isolation);
		document.add(Section7);

		PdfPTable table10 = new PdfPTable(pointColumnWidths2);

		table10.setWidthPercentage(100); // Width 100%
		table10.setSpacingBefore(15f); // Space before table
		table10.setSpacingAfter(5f); // Space after table
		table10.setWidthPercentage(100);
		table10.getDefaultCell().setBorder(0);

		addRow2(table10, "1", "Isolators - Presence of appropriate devices ", isolationCurrent.getPresenceDevices());
		addRow2(table10, "2", "Isolators – Condition of appropriate devices ", isolationCurrent.getConditionDevices());
		addRow2(table10, "3",
				"Isolators - Location of appropriate devices (state if local or remote from equipment in question)",
				isolationCurrent.getLocationDevices());
		addRow2(table10, "4", "Isolators - Capable of being secured in OFF position",
				isolationCurrent.getCapableSecured());
		addRow2(table10, "5", "Isolators - Correct operation verified (functional checks)",
				isolationCurrent.getOperationVerify());
		addRow2(table10, "6",
				"Isolators - The installation, circuit or Section thereof that will be isolated is clearly identified by location and/or durable marking",
				isolationCurrent.getInstallCircuit());
		addRow2(table10, "7",
				"Isolators - Warning label posted in the situation where live Sections cannot be isolated by the operation of single device",
				isolationCurrent.getWarningLabel());
		addRow2(table10, "8", "Switching off for mechanical maintenance - Presence of appropriate devices",
				isolationCurrent.getSwPresenceDevices());
		addRow2(table10, "9", "Switching off for mechanical maintenance - Condition of appropriate devices",
				isolationCurrent.getSwConditionDevices());
		addRow2(table10, "10",
				"Switching off for mechanical maintenance - Acceptable location (state if local or remote from equipment in question)",
				isolationCurrent.getSwAcceptableLocation());
		addRow2(table10, "11", "Switching off for mechanical maintenance - Capable of being secured in OFF position",
				isolationCurrent.getSwCapableOffPosition());
		addRow2(table10, "12",
				"Switching off for mechanical maintenance - Correct operation verified (functional check)",
				isolationCurrent.getSwCorrectOperation());
		addRow2(table10, "13",
				"Switching off for mechanical maintenance - The circuit or section there of that will be disconnected clearly identified by location and / or durable marking",
				isolationCurrent.getSwCircuit());
		addRow2(table10, "14",
				"Switching off for mechanical maintenance - Warning label posted in situations where live parts cannot be isolated by the operation of a single device",
				isolationCurrent.getSwWarningLabel());
		addRow2(table10, "15", "Emergency switching / stopping - Presence of appropriate devices",
				isolationCurrent.getEmSwitPresenceDevices());
		addRow2(table10, "16", "Emergency switching / stopping – Condition of appropriate devices",
				isolationCurrent.getEmSwitConditionDevices());
		addRow2(table10, "17",
				"Emergency switching / stopping - Location of appropriate devices (readily accessible for operation where danger might occur)",
				isolationCurrent.getEmSwitLocationDevices());
		addRow2(table10, "18", "Emergency switching / stopping - Correct operation verified (functional check)",
				isolationCurrent.getEmSwitOperationVerify());
		addRow2(table10, "19",
				"Emergency switching / stopping - The installation circuit or Section there of that will be disconnected clearly identified by location and / or durable marking",
				isolationCurrent.getEmSwitInstallCircuit());
		addRow2(table10, "20", "Functional switching - Presence of appropriate devices",
				isolationCurrent.getFuSwitPresenceDevices());
		addRow2(table10, "21", "Functional switching - Location of appropriate devices",
				isolationCurrent.getFuSwitLocationDevices());
		addRow2(table10, "22", "Functional switching - Correct operation verified (functional check)",
				isolationCurrent.getFuSwitOperationVerify());
		document.add(table10);

		document.newPage();

		PdfPTable Section8 = new PdfPTable(pointColumnWidths5);
		Section8.setWidthPercentage(100); // Width 100%
		Section8.setSpacingBefore(10f); // Space before table
		Section8.setSpacingAfter(3f); // Space after table
		Section8.setWidthPercentage(100);
		Section8.getDefaultCell().setBorder(0);

		PdfPCell current = new PdfPCell(new Paragraph("Section - 8: Current – using equipment (permanently connected)",
				new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
		current.setHorizontalAlignment(Element.ALIGN_LEFT);
		current.setBackgroundColor(new GrayColor(0.82f));
		current.setBorder(PdfPCell.NO_BORDER);
		Section8.addCell(current);
		document.add(Section8);

		PdfPTable table11 = new PdfPTable(pointColumnWidths2);

		table11.setWidthPercentage(100); // Width 100%
		table11.setSpacingBefore(15f); // Space before table
		table11.setSpacingAfter(15f); // Space after table
		table11.setWidthPercentage(100);
		table11.getDefaultCell().setBorder(0);

		addRow3(table11, "1", "Suitability of the equipment in terms of IP and fire ratings",
				isolationCurrent.getSuitabilityEquipment());
		addRow3(table11, "2", "Enclosure not damaged / deteriorated during installation so as to impair safety",
				isolationCurrent.getEnclosureNotDamaged());
		addRow3(table11, "3", "Suitability for the environment and external influences",
				isolationCurrent.getSuitabilityEnvironment());
		addRow3(table11, "4", "Security of fixing", isolationCurrent.getSecurityFixing());
		addRow3(table11, "5",
				"Cable entry holes in ceilings above luminaries, seized or sealed so as to restrict the spread of fire.",
				isolationCurrent.getCableEntryHoles());
		addRow3(table11, "6", "Provision (condition) of under voltage protection, where specified.",
				isolationCurrent.getProvisionVoltage());
		addRow3(table11, "7", "Provision (condition) of overload protection, where specified.",
				isolationCurrent.getProvisionOverload());
		addRow3(table11, "8", "Recessed luminaires (downlighters) - Correct type of lamps fitted ",
				isolationCurrent.getCorrectTypeLamps());
		addRow3(table11, "9",
				"Recessed luminaires (downlighters) - Installed to minimise build-up of heat by use of “fire rated” fittings, insulation displacement box or similar ",
				isolationCurrent.getInsulaDisplacementBox());
		addRow3(table11, "10",
				"No signs of overheating to surrounding building fabric (applicable for periodic inspection)",
				isolationCurrent.getOverheatSurrounding());
		addRow3(table11, "11",
				"No signs of overheating to conductors / terminations (applicable for periodic inspection)",
				isolationCurrent.getOverheatConductors());
		document.add(table11);
	}

	private void addRow(PdfPTable table8, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell1 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell2 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table8.addCell(nameCell);
		table8.addCell(valueCell1);
		table8.addCell(valueCell2);
	}

	private void addRow1(PdfPTable table9, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell1 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell3 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell4 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table9.addCell(nameCell1);
		table9.addCell(valueCell3);
		table9.addCell(valueCell4);
	}

	private void addRow2(PdfPTable table10, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell2 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell5 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell6 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table10.addCell(nameCell2);
		table10.addCell(valueCell5);
		table10.addCell(valueCell6);
	}

	private void addRow3(PdfPTable table11, String string, String string2, String string3)
			throws DocumentException, IOException {
		PdfPCell nameCell3 = new PdfPCell(new Paragraph(string, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell7 = new PdfPCell(new Paragraph(string2, new Font(BaseFont.createFont(), 10, Font.NORMAL)));
		PdfPCell valueCell8 = new PdfPCell(new Paragraph(string3, new Font(BaseFont.createFont(), 10, Font.NORMAL)));

		nameCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		valueCell7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		valueCell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

		table11.addCell(nameCell3);
		table11.addCell(valueCell7);
		table11.addCell(valueCell8);
	}

}