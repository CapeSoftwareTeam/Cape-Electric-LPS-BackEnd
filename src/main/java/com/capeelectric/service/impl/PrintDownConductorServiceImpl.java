package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.BridgingDescription;
import com.capeelectric.model.Connectors;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.Holder;
import com.capeelectric.model.LightningCounter;
import com.capeelectric.model.TestingJoint;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.PrintDownConductorService;
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
public class PrintDownConductorServiceImpl implements PrintDownConductorService {

	@Autowired
	private DownConductorRepository downConductorRepository;

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	@Override
	public void printDownConductor(String userName, Integer lpsId) throws DownConductorException {
		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			try {

				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, lpsId);
				BasicLps basicLps1 = basicLps.get(0);

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("DownConductorLps.pdf"));
				List<DownConductorDescription> downLps = downConductorRepository.findByUserNameAndBasicLpsId(userName,
						lpsId);
				DownConductorDescription downLps1 = downLps.get(0);

				List<DownConductor> downConductor = downLps1.getDownConductor();
				List<BridgingDescription> bridgingDesc = downLps1.getBridgingDescription();

				List<LightningCounter> lightingCounter = downLps1.getLightningCounter();
				List<Holder> holder1 = downLps1.getHolder();
				List<TestingJoint> testJoint1 = downLps1.getTestingJoint();
				List<Connectors> connector1 = downLps1.getConnectors();

				document.open();

				Font font1 = new Font(BaseFont.createFont(), 12, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				float[] pointColumnWidths40 = { 100F };

				PdfPTable headertable = new PdfPTable(pointColumnWidths40);
				headertable.setWidthPercentage(100); // Width 100%
				headertable.setSpacingBefore(10f); // Space before table
				headertable.setWidthPercentage(100);

				PdfPCell label = new PdfPCell(
						new Paragraph("Checklist for Down Conductors of LPS \r\n" + "as per IS/IEC 62305", font1));
				label.setHorizontalAlignment(Element.ALIGN_CENTER);
				label.setGrayFill(0.92f);
//				label.setFixedHeight(20f);
				headertable.addCell(label);
				document.add(headertable);

				float[] pointColumnWidths1 = { 30F, 70F };

				PdfPTable basicDetailsTable = new PdfPTable(pointColumnWidths1);
				basicDetailsTable.setWidthPercentage(100); // Width 100%
				// table1.setSpacingBefore(10f); // Space before table
				basicDetailsTable.setWidthPercentage(100);

				PdfPCell cell11 = new PdfPCell(new Paragraph("Client Name", font2));
				cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell11.setFixedHeight(20f);
				cell11.setGrayFill(0.92f);
				basicDetailsTable.addCell(cell11);

				PdfPCell cell2 = new PdfPCell(new Paragraph(basicLps1.getClientName(), font3));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicDetailsTable.addCell(cell2);

				PdfPCell cell31 = new PdfPCell(new Paragraph("Project Name", font2));
				cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell31.setFixedHeight(20f);
				cell31.setGrayFill(0.92f);
				basicDetailsTable.addCell(cell31);

				PdfPCell cell41 = new PdfPCell(new Paragraph(basicLps1.getProjectName(), font3));
				cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicDetailsTable.addCell(cell41);

				PdfPCell cell191 = new PdfPCell(new Paragraph("Type of Industry", font2));
				cell191.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell191.setGrayFill(0.92f);
				cell191.setFixedHeight(20f);
				basicDetailsTable.addCell(cell191);

				PdfPCell cell201 = new PdfPCell(new Paragraph(basicLps1.getIndustryType(), font3));
				cell201.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicDetailsTable.addCell(cell201);

				PdfPCell cell211 = new PdfPCell(new Paragraph("Type of Building", font2));
				cell211.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell211.setGrayFill(0.92f);
				cell211.setFixedHeight(20f);
				basicDetailsTable.addCell(cell211);

				PdfPCell cell22 = new PdfPCell(new Paragraph(basicLps1.getBuildingType(), font3));
				cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
				basicDetailsTable.addCell(cell22);

				document.add(basicDetailsTable);

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

				PdfPCell cell27 = new PdfPCell(new Paragraph("Soil Resistivity", font2));
				cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell27.setGrayFill(0.92f);
				cell27.setFixedHeight(20f);
				table4.addCell(cell27);

				PdfPCell cell28 = new PdfPCell(new Paragraph(basicLps1.getSoilResistivity(), font3));
				cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
				table4.addCell(cell28);

				document.add(table4);

				Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

				float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

				PdfPTable table = new PdfPTable(pointColumnWidths30);
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
//				table.setSpacingAfter(10f);
				table.setWidthPercentage(100);

				PdfPCell cell30 = new PdfPCell(new Paragraph("SL.NO", font11));
				cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
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

				// basicDescription(basicDesc2, table21);

				Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				PdfPCell cell34 = new PdfPCell(new Paragraph("1", font));
				cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell34.setFixedHeight(20f);
				cell34.setGrayFill(0.92f);
				table.addCell(cell34);

				PdfPCell cell35 = new PdfPCell(new Paragraph(
						"Check for Bi-metalllic issue (connections between dissimilar metals are not allowed)", font));
				cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
				// cell35.setFixedHeight(20f);
				cell35.setGrayFill(0.92f);
				table.addCell(cell35);

				PdfPCell cell36 = new PdfPCell(new Paragraph(downLps1.getBiMetallicIssueOb(), font));
				cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
				// cell36.setFixedHeight(20f);

				table.addCell(cell36);

				PdfPCell cell37 = new PdfPCell(new Paragraph(downLps1.getBiMetallicIssueRem(), font));
				// cell37.setFixedHeight(20f);
				cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell37);

				PdfPCell cell = new PdfPCell(new Paragraph("2", font));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);

				PdfPCell cell21 = new PdfPCell(new Paragraph("Warning notice at ground level", font));
				cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell21.setGrayFill(0.92f);
				table.addCell(cell21);

				PdfPCell cell3 = new PdfPCell(new Paragraph(downLps1.getWarningNoticeGroundLevelOb(), font));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph(downLps1.getWarningNoticeGroundLevelRem(), font));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(new Paragraph("3", font));
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setGrayFill(0.92f);
				table.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph(
						"No power/ control/ instrumentation/telecommunication cable or cable passage in path/near/parallel to down conductors",
						font));
				cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell6.setGrayFill(0.92f);
				table.addCell(cell6);

				PdfPCell cell7 = new PdfPCell(new Paragraph(downLps1.getNoPowerDownConductorOb(), font));
				cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph(downLps1.getNoPowerDownConductorRem(), font));
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell8);

				PdfPCell cell9 = new PdfPCell(new Paragraph("4", font));
				cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell9.setGrayFill(0.92f);
				table.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(
						new Paragraph("Connections made by brazing/welding/crimping/ seaming/screwing/bolting", font));
				cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell10.setGrayFill(0.92f);
				table.addCell(cell10);

				PdfPCell cell111 = new PdfPCell(new Paragraph(downLps1.getConnectMadeBrazingOb(), font));
				cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell111);

				PdfPCell cell12 = new PdfPCell(new Paragraph(downLps1.getConnectMadeBrazingRem(), font));
				cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell12);

				PdfPCell cell13 = new PdfPCell(new Paragraph("5", font));
				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell13.setGrayFill(0.92f);
				table.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(new Paragraph(
						"Chemical sprinkler is not in the path to detriorate or corrode the down conductor", font));
				cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell14.setGrayFill(0.92f);
				table.addCell(cell14);

				PdfPCell cell15 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerOb(), font));
				cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerRem(), font));
				cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell16);

				PdfPCell cell17 = new PdfPCell(new Paragraph("6", font));
				cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell17.setGrayFill(0.92f);
				table.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph(
						"If wall is a combustible material , distance between down conductor and wall should be > 1m",
						font));
				cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell18.setGrayFill(0.92f);
				table.addCell(cell18);

				PdfPCell cell19 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerOb(), font));
				cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell19);

				PdfPCell cell20 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerRem(), font));
				cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell20);

				document.add(table);

				for (DownConductor downConductor11 : downConductor) {

					PdfPTable table1 = downConductorIter(downConductor11, font);
					document.add(table1);

				}

				for (BridgingDescription bridgingDesc1 : bridgingDesc) {
					PdfPTable table1 = bridzingDescIter(bridgingDesc1, font);

					document.add(table1);
				}

				for (Holder holder : holder1) {
					PdfPTable table2 = holderIter(holder, font);

					document.add(table2);
				}

				for (Connectors connector : connector1) {
					PdfPTable table2 = connectorIter(connector, font);

					document.add(table2);
				}

				for (LightningCounter lightingCounter1 : lightingCounter) {
					PdfPTable table2 = lightingCountingIter(lightingCounter1, font);

					document.add(table2);
				}
				for (TestingJoint testJoint : testJoint1) {
					testingJointIter(document, testJoint, font);
				}
				document.close();
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else {

			throw new DownConductorException("Invalid Inputs");

		}

	}

	private void testingJointIter(Document document, TestingJoint testJoint, Font font)
			throws DocumentException, IOException {

		Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths301 = { 30F, 150F, 50F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths301);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(20f); // Space before table
		table2.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("12", font111));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table2.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Test Joints", font111));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setGrayFill(0.92f);
		cell22.setFixedHeight(20f);
		cell22.setColspan(3);
		table2.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "12.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setFixedHeight(20f);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table2.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font111));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table2.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(testJoint.getLocationName(), font111));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table2.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font111));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table2.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(testJoint.getLocationNumber().toString(), font111));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table2.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("12.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table2.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph("Test joint available or not", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table2.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(testJoint.getTestJointTypeOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(testJoint.getTestJointTypeRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("12.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table2.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(new Paragraph("Material of test joint", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setGrayFill(0.92f);
		table2.addCell(cell31);

		PdfPCell cell44 = new PdfPCell(new Paragraph(testJoint.getMaterialTestJointOb(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph(testJoint.getMaterialTestJointRem(), font));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell45);

		PdfPCell cell48 = new PdfPCell(new Paragraph("12.d", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		table2.addCell(cell48);

		PdfPCell cell46 = new PdfPCell(new Paragraph("Accessibility of test joint", font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setGrayFill(0.92f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(testJoint.getAccessibilityOfTestJointOb(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell47);

		PdfPCell cell49 = new PdfPCell(new Paragraph(testJoint.getAccessibilityOfTestJointRem(), font));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("12.e", font));
		cell50.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell50.setGrayFill(0.92f);
		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(
				new Paragraph("Provision of protection of test joint by non metalic casings", font));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setGrayFill(0.92f);
		table2.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(testJoint.getNonMetalicProtectionOb(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph(testJoint.getNonMetalicProtectionRem(), font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph("12.f", font));
		cell54.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell54.setGrayFill(0.92f);
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph("Is test joint placed 1.5 metre from the ground level", font));
		cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell55.setGrayFill(0.92f);
		table2.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(testJoint.getTestJointPlacedGroungLevelOb(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph(testJoint.getTestJointPlacedGroungLevelRem(), font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("12.g", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(
				"Check for Bi-metalllic issue (connections between dissimilar metals are not allowed)", font));
		cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell59.setGrayFill(0.92f);
		table2.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(testJoint.getBimetallicIssueCheckOb(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph(testJoint.getBimetallicIssueCheckRem(), font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell61);

		PdfPCell cell65 = new PdfPCell(new Paragraph("12.h", font));
		cell65.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph("Total number of test joints", font));
		cell66.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell66.setGrayFill(0.92f);
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(testJoint.getTotalNoOfTestJointOB(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table2.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph(testJoint.getTotalNoOfTestJointRem(), font));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell68);

		PdfPCell cell70 = new PdfPCell(new Paragraph("12.i", font));
		cell70.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell70.setGrayFill(0.92f);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph("Number of test joints inspected", font));
		cell71.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell71.setGrayFill(0.92f);
		table2.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(testJoint.getInspectedNoOb(), font));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph(testJoint.getInspectedNoRem(), font));
		cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph("12.j", font));
		cell74.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell74.setGrayFill(0.92f);
		table2.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell75.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell75.setGrayFill(0.92f);
		table2.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph(testJoint.getInspectionPassedNoOb(), font));
		cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(new Paragraph(testJoint.getInspectedNoRem(), font));
		cell77.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph("12.k", font));
		cell78.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell78.setGrayFill(0.92f);
		table2.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell79.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell79.setGrayFill(0.92f);
		table2.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph(testJoint.getInspectionFailedNoOb(), font));
		cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell80);

		PdfPCell cell81 = new PdfPCell(new Paragraph(testJoint.getInspectionFailedNoRem(), font));
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell81);
		document.add(table2);
	}

	private PdfPTable lightingCountingIter(LightningCounter lightingCounter1, Font font)
			throws DocumentException, IOException {

		Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths301 = { 25F, 150F, 55F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths301);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(20f); // Space before table
		table2.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("11", font111));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table2.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Lightning Counters", font111));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setGrayFill(0.92f);
		cell22.setFixedHeight(20f);
		cell22.setColspan(3);
		table2.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "11.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table2.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font111));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table2.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(lightingCounter1.getLocationName(), font111));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table2.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font111));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table2.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(lightingCounter1.getLocationNumber().toString(), font111));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table2.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("11.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table2.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph("Threshold current", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table2.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(lightingCounter1.getThreadHoldCurrentOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(lightingCounter1.getThreadHoldCurrentRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("11.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table2.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(new Paragraph("Maximum withstand current", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setGrayFill(0.92f);
		table2.addCell(cell31);

		PdfPCell cell44 = new PdfPCell(new Paragraph(lightingCounter1.getMaximumWithStandCurrentOb(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph(lightingCounter1.getMaximumWithStandCurrentRem(), font));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell45);

		PdfPCell cell48 = new PdfPCell(new Paragraph("11.d", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		table2.addCell(cell48);

		PdfPCell cell46 = new PdfPCell(new Paragraph("Counts", font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setGrayFill(0.92f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(lightingCounter1.getCountsOb(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell47);

		PdfPCell cell49 = new PdfPCell(new Paragraph(lightingCounter1.getCountsRem(), font));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("11.e", font));
		cell50.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell50.setGrayFill(0.92f);
		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph("Battery life time", font));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setGrayFill(0.92f);
		table2.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(lightingCounter1.getBatteryLifeTimeOb(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph(lightingCounter1.getBatteryLifeTimeRem(), font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph("11.f", font));
		cell54.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell54.setGrayFill(0.92f);
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(
				new Paragraph("Proper connections of lightning counter for correct functionin", font));
		cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell55.setGrayFill(0.92f);
		table2.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(lightingCounter1.getProperConnectionLightingCounterOb(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph(lightingCounter1.getProperConnectionLightingCounterRem(), font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("11.g", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(
				new Paragraph("Is lightning counter is placed 0.5 metre above test joint", font));
		cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell59.setGrayFill(0.92f);
		table2.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(lightingCounter1.getLightingCounterPlacedOb(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph(lightingCounter1.getLightingCounterPlacedRem(), font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell61);

		PdfPCell cell65 = new PdfPCell(new Paragraph("11.h", font));
		cell65.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(
				new Paragraph("Condition of lightning counter and its connection (loose/tight/corroded)", font));
		cell66.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell66.setGrayFill(0.92f);
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(lightingCounter1.getConditionOfLightingCounterOb(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph(lightingCounter1.getConditionOfLightingCounterRem(), font));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell68);

		PdfPCell cell70 = new PdfPCell(new Paragraph("11.i", font));
		cell70.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell70.setGrayFill(0.92f);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph("Total number of lightning counter", font));
		cell71.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell71.setGrayFill(0.92f);
		table2.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(lightingCounter1.getTotalNoLightingCounterOb(), font));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph(lightingCounter1.getTotalNoLightingCounterRem(), font));
		cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph("11.j", font));
		cell74.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell74.setGrayFill(0.92f);
		table2.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph("Number of lightning counter inspected", font));
		cell75.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell75.setGrayFill(0.92f);
		table2.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph(lightingCounter1.getInspectedNoOb(), font));
		cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(new Paragraph(lightingCounter1.getInspectedNoRem(), font));
		cell77.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph("11.k", font));
		cell78.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell78.setGrayFill(0.92f);
		table2.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell79.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell79.setGrayFill(0.92f);
		table2.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph(lightingCounter1.getInspectionPassedNoOb(), font));
		cell80.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table2.addCell(cell80);

		PdfPCell cell81 = new PdfPCell(new Paragraph(lightingCounter1.getInspectionPassedNoRem(), font));
		cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell81);

		PdfPCell cell82 = new PdfPCell(new Paragraph("11.l", font));
		cell82.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell82.setGrayFill(0.92f);
		table2.addCell(cell82);

		PdfPCell cell83 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell83.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell83.setGrayFill(0.92f);
		table2.addCell(cell83);

		PdfPCell cell84 = new PdfPCell(new Paragraph(lightingCounter1.getInspectionFailedNoOb(), font));
		cell84.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell84);

		PdfPCell cell85 = new PdfPCell(new Paragraph(lightingCounter1.getInspectionFailedNoRem(), font));
		cell85.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell85);
		return table2;
	}

	private PdfPTable connectorIter(Connectors connector, Font font) throws DocumentException, IOException {

		Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths301 = { 25F, 150F, 55F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths301);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(20f); // Space before table
		table2.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("10", font111));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table2.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Connectors", font111));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setFixedHeight(20f);
		cell22.setGrayFill(0.92f);
		cell22.setColspan(3);
		table2.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "10.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table2.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font111));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table2.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(connector.getLocationName(), font111));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table2.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font111));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table2.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(connector.getLocationNumber().toString(), font111));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table2.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("10.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table2.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table2.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(connector.getPhysicalInspectionOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(connector.getPhysicalInspectionRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("10.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table2.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(
				new Paragraph("check the connection of straight connector (tight/loose/corroded)", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setGrayFill(0.92f);
		table2.addCell(cell31);

		PdfPCell cell44 = new PdfPCell(new Paragraph(connector.getStrightConnectCheckOb(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph(connector.getStrightConnectCheckRem(), font));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell45);

		PdfPCell cell48 = new PdfPCell(new Paragraph("10.d", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		table2.addCell(cell48);

		PdfPCell cell46 = new PdfPCell(new Paragraph("Material of connector", font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setGrayFill(0.92f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(connector.getMaterialConnectorOb(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell47);

		PdfPCell cell49 = new PdfPCell(new Paragraph(connector.getMaterialConnectorRem(), font));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("10.e", font));
		cell50.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell50.setGrayFill(0.92f);
		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph("Max number of connector in a down conductor", font));
		cell51.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell51.setGrayFill(0.92f);
		table2.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(connector.getMaxConnectorsDownConductorOb(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph(connector.getMaterialConnectorRem(), font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph("10.f", font));
		cell54.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell54.setGrayFill(0.92f);
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph("Total number of connectors", font));
		cell55.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell55.setGrayFill(0.92f);
		table2.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(connector.getTotalNoConnectorsOb(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph(connector.getTotalNoConnectorsRem(), font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("10.g", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph("Number of connectors inspected", font));
		cell59.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell59.setGrayFill(0.92f);
		table2.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(connector.getInspectedNoOb(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph(connector.getInspectedNoRem(), font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell61);

		PdfPCell cell65 = new PdfPCell(new Paragraph("10.h", font));
		cell65.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph("Number of inspection passed", font));
		cell66.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell66.setGrayFill(0.92f);
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(connector.getInspectionPassedNoOb(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph(connector.getInspectionPassedNoRem(), font));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell68);

		PdfPCell cell70 = new PdfPCell(new Paragraph("10.i", font));
		cell70.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell70.setGrayFill(0.92f);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph("Number of inspection failed", font));
		cell71.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell71.setGrayFill(0.92f);
		table2.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(connector.getInspectionFailedNoOb(), font));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph(connector.getInspectionFailedNoRem(), font));
		cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell73);
		return table2;
	}

	private PdfPTable holderIter(Holder holder, Font font) throws DocumentException, IOException {

		Font font111 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths301 = { 25F, 150F, 55F, 50F };

		PdfPTable table2 = new PdfPTable(pointColumnWidths301);
		table2.setWidthPercentage(100); // Width 100%
		table2.setSpacingBefore(20f); // Space before table
		table2.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("9", font111));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table2.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Holders", font111));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setGrayFill(0.92f);
		cell22.setFixedHeight(20f);
		cell22.setColspan(3);
		table2.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "9.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table2.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font111));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table2.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(holder.getLocationName(), font111));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table2.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font111));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table2.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(holder.getLocationNumber().toString(), font111));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table2.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("9.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table2.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph("Physical inspection (damage/break/corroded)", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table2.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(holder.getPhysicalInspectionOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(holder.getPhysicalInspectionRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("9.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table2.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(
				new Paragraph("Conductor holder is firmly fixed/mounted over the flat surface of wall", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setGrayFill(0.92f);
		table2.addCell(cell31);

		PdfPCell cell44 = new PdfPCell(new Paragraph(holder.getConductHolderFlatSurfaceOb(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph(holder.getConductHolderFlatSurfaceRem(), font));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell45);

		PdfPCell cell48 = new PdfPCell(new Paragraph("9.d", font));
		cell48.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell48.setGrayFill(0.92f);
		table2.addCell(cell48);

		PdfPCell cell46 = new PdfPCell(new Paragraph(
				"Conductor is properly  holded  in the holder and connection of conductor with holder(tight/loose/corroded)",
				font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setGrayFill(0.92f);
		table2.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(holder.getConductorHoldedOb(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell47);

		PdfPCell cell49 = new PdfPCell(new Paragraph(holder.getConductorHoldedRem(), font));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("9.e", font));
		cell50.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell50.setGrayFill(0.92f);
		table2.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph("Material of holder", font));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setGrayFill(0.92f);
		table2.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(holder.getMaterialHolderOb(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph(holder.getMaterialHolderRem(), font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph("9.f", font));
		cell54.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell54.setGrayFill(0.92f);
		table2.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph("Total number of holders", font));
		cell55.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell55.setGrayFill(0.92f);
		table2.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(holder.getTotalNoHolderOb(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph(holder.getTotalNoHolderRem(), font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("9.g", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table2.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph("Number of holders inspected", font));
		cell59.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell59.setGrayFill(0.92f);
		table2.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(holder.getInspectedNoOb(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph(holder.getInspectedNoRem(), font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell61);

		PdfPCell cell65 = new PdfPCell(new Paragraph("9.h", font));
		cell65.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell65.setGrayFill(0.92f);
		table2.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph("Number of inspection passed", font));
		cell66.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell66.setGrayFill(0.92f);
		table2.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(holder.getInspectionPassedNoOb(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph(holder.getInspectionPassedNoRem(), font));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell68);

		PdfPCell cell70 = new PdfPCell(new Paragraph("9.i", font));
		cell70.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell70.setGrayFill(0.92f);
		table2.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph("Number of inspection failed", font));
		cell71.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell71.setGrayFill(0.92f);
		table2.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(holder.getInspectionFailedNoOb(), font));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph(holder.getInspectionFailedNoRem(), font));
		cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
		table2.addCell(cell73);
		return table2;
	}

	private PdfPTable bridzingDescIter(BridgingDescription bridgingDesc1, Font font)
			throws DocumentException, IOException {

		Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths30);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(20f); // Space before table
		table1.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("8", font11));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table1.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Bridging cables", font11));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setGrayFill(0.92f);
		cell22.setFixedHeight(20f);
		cell22.setColspan(3);
		table1.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "8.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table1.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font11));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table1.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(bridgingDesc1.getLocationName(), font11));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table1.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font11));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table1.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(bridgingDesc1.getLocationNumber().toString(), font11));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table1.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("8.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(
				new Paragraph("Ensure bridging cable is used at corner of structures to avoid sharp bends", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table1.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(bridgingDesc1.getBridgingCableConnectionOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(bridgingDesc1.getBridgingCableConnectionRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("8.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table1.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(
				new Paragraph("Check the connection of aluminium conductor into the side wall", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setGrayFill(0.92f);
		table1.addCell(cell31);

		PdfPCell cell44 = new PdfPCell(new Paragraph(bridgingDesc1.getAluminiumConductorSideWallOb(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph(bridgingDesc1.getAluminiumConductorSideWallRem(), font));
		cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell45);

		PdfPCell cell46 = new PdfPCell(new Paragraph("8.d", font));
		cell46.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell46.setGrayFill(0.92f);
		table1.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(
				new Paragraph("Ensure the conection of bridging cable (losse/tight /corroded)", font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell47.setGrayFill(0.92f);
		table1.addCell(cell47);

		PdfPCell cell48 = new PdfPCell(new Paragraph(bridgingDesc1.getEnsureBridgingCableOb(), font));
		cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph(bridgingDesc1.getEnsureBridgingCableRem(), font));
		cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(new Paragraph("8.e", font));
		cell50.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell50.setGrayFill(0.92f);
		table1.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph("Total number of bridging cables", font));
		cell51.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell51.setGrayFill(0.92f);
		table1.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(bridgingDesc1.getTotalNoBridgingCableOb(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph(bridgingDesc1.getTotalNoBridgingCableRem(), font));
		cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(new Paragraph("8.f", font));
		cell54.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell54.setGrayFill(0.92f);
		table1.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph("Number of bridging cables inspected", font));
		cell55.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell55.setGrayFill(0.92f);
		table1.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectedNoOb(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectedNoRem(), font));
		cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("8.g", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table1.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell59.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell59.setGrayFill(0.92f);
		table1.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectionPassedNoOb(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectionPassedNoRem(), font));
		cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph("8.h", font));
		cell62.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell62.setGrayFill(0.92f);
		table1.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell63.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell63.setGrayFill(0.92f);
		table1.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectionFailedNoOb(), font));
		cell64.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph(bridgingDesc1.getInspectionFailedNoRem(), font));
		cell65.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell65);
		return table1;
	}

	private PdfPTable downConductorIter(DownConductor downConductor1, Font font) throws DocumentException, IOException {

		float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

		Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

		PdfPTable table1 = new PdfPTable(pointColumnWidths30);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(20f); // Space before table
		table1.setWidthPercentage(100);

		PdfPCell cell21 = new PdfPCell(new Paragraph("7", font11));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setGrayFill(0.92f);
		table1.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph("Down conductors", font11));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setGrayFill(0.92f);
		cell22.setColspan(3);
		table1.addCell(cell22);

		PdfPCell cell124 = new PdfPCell(new Paragraph("\r\n" + "7.a", font));
		cell124.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell124.setRowspan(2);
		cell124.setGrayFill(0.92f);
		table1.addCell(cell124);

		PdfPCell cell1210 = new PdfPCell(new Paragraph("Location Name", font11));
		cell1210.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell1210.setColspan(1);
		cell1210.setGrayFill(0.92f);
		table1.addCell(cell1210);

		PdfPCell cell2 = new PdfPCell(new Paragraph(downConductor1.getLocationName(), font11));
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell2.setColspan(2);
		table1.addCell(cell2);

		PdfPCell cell121 = new PdfPCell(new Paragraph("Location Number", font11));
		cell121.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell121.setColspan(1);
		cell121.setGrayFill(0.92f);
		table1.addCell(cell121);

		PdfPCell cell211 = new PdfPCell(new Paragraph(downConductor1.getLocationNumber().toString(), font11));
		cell211.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell211.setColspan(2);
		table1.addCell(cell211);

		PdfPCell cell25 = new PdfPCell(new Paragraph("7.b", font));
		cell25.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell25.setGrayFill(0.92f);
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph("Physical inspection (damage/break/bend/corroded/route)", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setGrayFill(0.92f);
		table1.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(downConductor1.getPhysicalInspectionOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(downConductor1.getPhysicalInspectionRem(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph("7.c", font));
		cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell29.setGrayFill(0.92f);
		table1.addCell(cell29);

		PdfPCell cell30 = new PdfPCell(new Paragraph("Material of conductor", font));
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell30.setGrayFill(0.92f);
		table1.addCell(cell30);

		PdfPCell cell31 = new PdfPCell(new Paragraph(downConductor1.getConductMaterialOb(), font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell31);

		PdfPCell cell32 = new PdfPCell(new Paragraph(downConductor1.getConductMaterialRem(), font));
		cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell32);

		PdfPCell cell33 = new PdfPCell(new Paragraph("7.d", font));
		cell33.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell33.setGrayFill(0.92f);
		table1.addCell(cell33);

		PdfPCell cell34 = new PdfPCell(new Paragraph("Size/cross section area of conductor", font));
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell34.setGrayFill(0.92f);
		table1.addCell(cell34);

		PdfPCell cell35 = new PdfPCell(new Paragraph(downConductor1.getConductSizeOb(), font));
		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell35);

		PdfPCell cell36 = new PdfPCell(new Paragraph(downConductor1.getConductSizeRem(), font));
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph("7.e", font));
		cell37.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell37.setGrayFill(0.92f);
		table1.addCell(cell37);

		PdfPCell cell38 = new PdfPCell(new Paragraph("Type of down conductor exposed / insulated", font));
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell38.setGrayFill(0.92f);
		table1.addCell(cell38);

		PdfPCell cell39 = new PdfPCell(new Paragraph(downConductor1.getDownConductExposedOb(), font));
		cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell39);

		PdfPCell cell40 = new PdfPCell(new Paragraph(downConductor1.getDownConductExposedRem(), font));
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell40);

		PdfPCell cell41 = new PdfPCell(new Paragraph("7.f", font));
		cell41.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell41.setGrayFill(0.92f);
		table1.addCell(cell41);

		PdfPCell cell42 = new PdfPCell(new Paragraph(
				"Down conductor location changed as per site condition/approved in case of deviation check routing of down conductor whether shortest path is achieved or not",
				font));
		cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell42.setGrayFill(0.92f);
		table1.addCell(cell42);

		PdfPCell cell43 = new PdfPCell(new Paragraph(downConductor1.getDownConductLocationdOb(), font));
		cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell43);

		PdfPCell cell44 = new PdfPCell(new Paragraph(downConductor1.getDownConductLocationdRem(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell44);

		PdfPCell cell45 = new PdfPCell(new Paragraph("7.g", font));
		cell45.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell45.setGrayFill(0.92f);
		table1.addCell(cell45);

		PdfPCell cell46 = new PdfPCell(new Paragraph(
				"Down conductor not installed in gutters / water spouts even if it’s a insulated conductor", font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setGrayFill(0.92f);
		table1.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(downConductor1.getDownConductGutterOb(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell47);

		PdfPCell cell48 = new PdfPCell(new Paragraph(downConductor1.getDownConductGutterRem(), font));
		cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell48);

		PdfPCell cell49 = new PdfPCell(new Paragraph("7.h", font));
		cell49.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell49.setGrayFill(0.92f);
		table1.addCell(cell49);

		PdfPCell cell50 = new PdfPCell(
				new Paragraph("Ensure the connection of down conductor with nearest equipotential bar", font));
		cell50.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell50.setGrayFill(0.92f);
		table1.addCell(cell50);

		PdfPCell cell51 = new PdfPCell(new Paragraph(downConductor1.getEnsureDownCnoductOb(), font));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(downConductor1.getEnsureDownCnoductRem(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell52);

		PdfPCell cell53 = new PdfPCell(new Paragraph("7.i", font));
		cell53.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell53.setGrayFill(0.92f);
		table1.addCell(cell53);

		PdfPCell cell54 = new PdfPCell(
				new Paragraph("Installation of down conductor at equal spacing around perimeter", font));
		cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell54.setGrayFill(0.92f);
		table1.addCell(cell54);

		PdfPCell cell55 = new PdfPCell(new Paragraph(downConductor1.getInstallationDownConductOb(), font));
		cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell55);

		PdfPCell cell56 = new PdfPCell(new Paragraph(downConductor1.getInstallationDownConductRem(), font));
		cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell56);

		PdfPCell cell57 = new PdfPCell(new Paragraph("7.j", font));
		cell57.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell57.setGrayFill(0.92f);
		table1.addCell(cell57);

		PdfPCell cell58 = new PdfPCell(new Paragraph("Maximum distance between down conductors", font));
		cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell58.setGrayFill(0.92f);
		table1.addCell(cell58);

		PdfPCell cell59 = new PdfPCell(new Paragraph(downConductor1.getMaximumDownConductOb(), font));
		cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell59);

		PdfPCell cell60 = new PdfPCell(new Paragraph(downConductor1.getMaximumDownConductRem(), font));
		cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell60);

		PdfPCell cell61 = new PdfPCell(new Paragraph("7.k", font));
		cell61.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell61.setGrayFill(0.92f);
		table1.addCell(cell61);

		PdfPCell cell62 = new PdfPCell(new Paragraph("Minimum distance between down conductors", font));
		cell62.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell62.setGrayFill(0.92f);
		table1.addCell(cell62);

		PdfPCell cell63 = new PdfPCell(new Paragraph(downConductor1.getManimumDownConductOb(), font));
		cell63.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell63);

		PdfPCell cell64 = new PdfPCell(new Paragraph(downConductor1.getManimumDownConductRem(), font));
		cell64.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell64);

		PdfPCell cell65 = new PdfPCell(new Paragraph("7.l", font));
		cell65.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell65.setGrayFill(0.92f);
		table1.addCell(cell65);

		PdfPCell cell66 = new PdfPCell(new Paragraph("Total number of down conductors", font));
		cell66.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell66.setGrayFill(0.92f);
		table1.addCell(cell66);

		PdfPCell cell67 = new PdfPCell(new Paragraph(downConductor1.getTotalNoDownConductOb(), font));
		cell67.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell67);

		PdfPCell cell68 = new PdfPCell(new Paragraph(downConductor1.getTotalNoDownConductRem(), font));
		cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell68);

		PdfPCell cell69 = new PdfPCell(new Paragraph("7.m", font));
		cell69.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell69.setGrayFill(0.92f);
		table1.addCell(cell69);

		PdfPCell cell70 = new PdfPCell(new Paragraph("Number of down conductors inspected", font));
		cell70.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell70.setGrayFill(0.92f);
		table1.addCell(cell70);

		PdfPCell cell71 = new PdfPCell(new Paragraph(downConductor1.getInspectedNoOb(), font));
		cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell71);

		PdfPCell cell72 = new PdfPCell(new Paragraph(downConductor1.getInspectedNoRem(), font));
		cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell72);

		PdfPCell cell73 = new PdfPCell(new Paragraph("7.n", font));
		cell73.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell73.setGrayFill(0.92f);
		table1.addCell(cell73);

		PdfPCell cell74 = new PdfPCell(new Paragraph("Number of inspections passed", font));
		cell74.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell74.setGrayFill(0.92f);
		table1.addCell(cell74);

		PdfPCell cell75 = new PdfPCell(new Paragraph(downConductor1.getInspectionPassedNoOb(), font));
		cell75.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell75);

		PdfPCell cell76 = new PdfPCell(new Paragraph(downConductor1.getInspectedNoRem(), font));
		cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell76);

		PdfPCell cell77 = new PdfPCell(new Paragraph("7.o", font));
		cell77.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell77.setGrayFill(0.92f);
		table1.addCell(cell77);

		PdfPCell cell78 = new PdfPCell(new Paragraph("Number of inspections failed", font));
		cell78.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell78.setGrayFill(0.92f);
		table1.addCell(cell78);

		PdfPCell cell79 = new PdfPCell(new Paragraph(downConductor1.getInspectionFailedNoOb(), font));
		cell79.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell79);

		PdfPCell cell80 = new PdfPCell(new Paragraph(downConductor1.getInspectionPassedNoRem(), font));
		cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell80);
		return table1;
	}

}