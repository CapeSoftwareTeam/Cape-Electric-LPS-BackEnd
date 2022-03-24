package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.BridgingDescription;
import com.capeelectric.model.Connectors;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.DownConductorTesting;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintDownConductorServiceImpl implements PrintDownConductorService {

	private static final Logger logger = LoggerFactory.getLogger(PrintDownConductorServiceImpl.class);

//	@Autowired
//	private BasicLpsRepository basicLpsRepository;
//
//	@Autowired
//	private DownConductorRepository downConductorRepository;

	@Override
	public void printDownConductor(String userName, Integer lpsId, Optional<BasicLps> basicLpsDetails,
			Optional<DownConductorReport> downConductorDetails) throws DownConductorException {

//	@Override
//	public void printDownConductor(String userName, Integer lpsId) throws DownConductorException {

		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
			try {

//				List<BasicLps> basicLps = basicLpsRepository.findByUserNameAndBasicLpsId(userName, lpsId);
//				BasicLps basicLps1 = basicLps.get(0);
				BasicLps basicLps1 = basicLpsDetails.get();

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("DownConductorLps.pdf"));

//				List<DownConductorReport> downConductorDetails = downConductorRepository
//						.findByUserNameAndBasicLpsId(userName, lpsId);
//				DownConductorReport downConductorReport1 = downConductorDetails.get(0);

				DownConductorReport downConductorReport1 = downConductorDetails.get();

				List<DownConductorDescription> downConductorDesc = downConductorReport1.getDownConductorDescription();
//				DownConductorDescription downConDesc = downConductorDesc.get(0);
//
//				List<DownConductor> downConductor = downConDesc.getDownConductor();
//				List<BridgingDescription> bridgingDesc = downConDesc.getBridgingDescription();
//				List<Holder> holder1 = downConDesc.getHolder();
//				List<Connectors> connector1 = downConDesc.getConnectors();
//				List<LightningCounter> lightingCounter1 = downConDesc.getLightningCounter();
//				List<TestingJoint> testJoint1 = downConDesc.getTestingJoint();
//				List<DownConductorTesting> downConductorTesting1 = downConDesc.getDownConductorTesting();

				logger.debug("printing DownConductor Module");

				document.open();

				Font font1 = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
//				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				Font font3 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				for (DownConductorDescription downConDesc : downConductorDesc) {

					if (!downConDesc.getFlag().equalsIgnoreCase("R")) {

//   					this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						float[] pointColumnWidths40 = { 100F };

						PdfPTable headertable = new PdfPTable(pointColumnWidths40);
						headertable.setWidthPercentage(100); // Width 100%
						headertable.setSpacingBefore(10f); // Space before table
						headertable.setWidthPercentage(100);

						PdfPCell label = new PdfPCell(new Paragraph("Down Conductors", font1));
						label.setHorizontalAlignment(Element.ALIGN_CENTER);
						label.setGrayFill(0.92f);
						label.setFixedHeight(20f);
						headertable.addCell(label);
						document.add(headertable);

						float[] pointColumnWidths5 = { 100F };

						PdfPTable BasicDetailsTable = new PdfPTable(pointColumnWidths5);

						BasicDetailsTable.setWidthPercentage(100); // Width 100%
						BasicDetailsTable.setSpacingBefore(10f); // Space before table
//					BasicDetailsTable.setSpacingAfter(5f); // Space after table
						BasicDetailsTable.getDefaultCell().setBorder(0);

						PdfPCell arrangements = new PdfPCell(new Paragraph("Basic Details",
								new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
						arrangements.setBackgroundColor(new GrayColor(0.82f));
						arrangements.setHorizontalAlignment(Element.ALIGN_CENTER);
						arrangements.setBorder(PdfPCell.NO_BORDER);
						BasicDetailsTable.addCell(arrangements);
						document.add(BasicDetailsTable);

						float[] pointColumnWidths = { 120F, 80F };

						Font font = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

						Font font11B = new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD, BaseColor.BLACK);

// Down Conductor Basic Details Method 

//					for (DownConductorDescription downConDesc : downConductorReport.getDownConductorDescription()) {

						PdfPTable table = DownConductorBasicDetails(document, pointColumnWidths, font, downConDesc);
						document.add(table);

// Down Conductor accordian Method 

						document.newPage();

//					    this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						float[] pointColumnWidths4 = { 25F, 150F, 55F, 50F };

						PdfPTable downConductorHead = new PdfPTable(pointColumnWidths4);
						downConductorHead.setWidthPercentage(100); // Width 100%
						downConductorHead.setSpacingBefore(10f); // Space before table
						downConductorHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell11 = new PdfPCell();
						cell11.setPhrase(new Phrase("Down Conductors (Exposed / Natural)", font11B));
						cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell11.setBackgroundColor(new GrayColor(0.93f));
						cell11.setFixedHeight(20f);
						cell11.setColspan(4);
						downConductorHead.addCell(cell11);

						document.add(downConductorHead);

						float[] pointColumnWidthsAvailability = { 120F, 80F };

						PdfPTable table10 = new PdfPTable(pointColumnWidthsAvailability);

						table10.setWidthPercentage(100); // Width 100%
//				table10.setSpacingBefore(5f); // Space before table
//				table10.setSpacingAfter(5f); // Space after table
						table10.getDefaultCell().setBorder(0);

						PdfPCell cell70 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell70.setBackgroundColor(new GrayColor(0.93f));
						cell70.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell70.setBorder(PdfPCell.NO_BORDER);
						table10.addCell(cell70);
						PdfPCell cell71 = new PdfPCell(new Paragraph(downConDesc.getDownConductorAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell71.setBackgroundColor(new GrayColor(0.93f));
						cell71.setBorder(PdfPCell.NO_BORDER);
						table10.addCell(cell71);

						PdfPTable table10R = new PdfPTable(pointColumnWidthsAvailability);
						table10R.setWidthPercentage(100); // Width 100%
						table10R.setSpacingBefore(5f); // Space before table
//				table10R.setSpacingAfter(5f); // Space after table
						table10R.getDefaultCell().setBorder(0);

						PdfPCell cell72 = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell72.setBackgroundColor(new GrayColor(0.93f));
						cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell72.setBorder(PdfPCell.NO_BORDER);
						table10R.addCell(cell72);
						PdfPCell cell73 = new PdfPCell(new Paragraph(downConDesc.getDownConductorAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell73.setBackgroundColor(new GrayColor(0.93f));
						cell73.setBorder(PdfPCell.NO_BORDER);
						table10R.addCell(cell73);

						document.add(table10);
						document.add(table10R);

						for (DownConductor downConductor11 : downConDesc.getDownConductor()) {

							if (!downConductor11.getFlag().equalsIgnoreCase("R")) {

								if (downConDesc.getDownConductorAvailabilityOb().equalsIgnoreCase("applicable")) {

									PdfPTable table1 = DownConductorItr(font, downConductor11);

									document.add(table1);

								} else {

//								DownConductor downcondutor = downConductor.get(0);

									float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

									PdfPTable table1 = new PdfPTable(pointColumnWidths30);
									table1.setWidthPercentage(100); // Width 100%
									table1.setSpacingBefore(10f); // Space before table
									table1.setSpacingAfter(5f); // Space after table
									table1.getDefaultCell().setBorder(0);

									PdfPCell cell53 = new PdfPCell();
									cell53.setPhrase(new Phrase("7(q)", font));
									cell53.setHorizontalAlignment(Element.ALIGN_CENTER);
									cell53.setBackgroundColor(new GrayColor(0.93f));
									table1.addCell(cell53);

									PdfPCell cell54 = new PdfPCell();
									cell54.setPhrase(new Phrase(
											"Type of natural down conductor (Metal pipe/ I beam/ others)", font));
									cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell54.setBackgroundColor(new GrayColor(0.93f));
									table1.addCell(cell54);

									PdfPCell cell55 = new PdfPCell(
											new Paragraph(downConductor11.getNaturalDownCondutTypeOb(), font));
									cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
									table1.addCell(cell55);

									if (downConductor11.getNaturalDownCondutTypeRem() != null) {
										PdfPCell cell48 = new PdfPCell(
												new Paragraph(downConductor11.getNaturalDownCondutTypeRem(), font));
										cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
										table1.addCell(cell48);
									} else {
										PdfPCell cell49 = new PdfPCell(new Paragraph("Not Available", font));
										cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
										table1.addCell(cell49);
									}

									PdfPCell cell56 = new PdfPCell();
									cell56.setPhrase(new Phrase("7(r)", font));
									cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
									cell56.setBackgroundColor(new GrayColor(0.93f));
									table1.addCell(cell56);

									PdfPCell cell57 = new PdfPCell();
									cell57.setPhrase(new Phrase("Dimensions of natural down conductor", font));
									cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell57.setBackgroundColor(new GrayColor(0.93f));
									table1.addCell(cell57);

									PdfPCell cell58 = new PdfPCell(
											new Paragraph(downConductor11.getNaturalDownCondDimensionOb(), font));
									cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
									table1.addCell(cell58);

									if (downConductor11.getNaturalDownCondDimensionRem() != null) {
										PdfPCell cell48 = new PdfPCell(
												new Paragraph(downConductor11.getNaturalDownCondDimensionRem(), font));
										cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
										table1.addCell(cell48);
									} else {
										PdfPCell cell49 = new PdfPCell(new Paragraph("Not Available", font));
										cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
										table1.addCell(cell49);
									}
									document.add(table1);
								}
							}
						}

// Bridging Accordian  Method		
						document.newPage();

//				     	this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						PdfPTable BridgingHead = new PdfPTable(pointColumnWidths4);
						BridgingHead.setWidthPercentage(100); // Width 100%
						BridgingHead.setSpacingBefore(5f); // Space before table
						BridgingHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell12 = new PdfPCell();
						cell12.setPhrase(new Phrase("Bridging Cables", font11B));
						cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell12.setBackgroundColor(new GrayColor(0.93f));
						cell12.setFixedHeight(20f);
						cell12.setColspan(4);
						BridgingHead.addCell(cell12);

						document.add(BridgingHead);

						PdfPTable table12 = new PdfPTable(pointColumnWidthsAvailability);

						table12.setWidthPercentage(100); // Width 100%
//				table12.setSpacingBefore(5f); // Space before table
//				table12.setSpacingAfter(5f); // Space after table
						table12.getDefaultCell().setBorder(0);

						PdfPCell cell150 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell150.setBackgroundColor(new GrayColor(0.93f));
						cell150.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell150.setBorder(PdfPCell.NO_BORDER);
						table12.addCell(cell150);
						PdfPCell cell151 = new PdfPCell(new Paragraph(downConDesc.getDownConductorAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell151.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell151.setBackgroundColor(new GrayColor(0.93f));
						cell151.setBorder(PdfPCell.NO_BORDER);
						table12.addCell(cell151);

						PdfPTable table12R = new PdfPTable(pointColumnWidthsAvailability);
						table12R.setWidthPercentage(100); // Width 100%
						table12R.setSpacingBefore(5f); // Space before table
//				table12R.setSpacingAfter(5f); // Space after table
						table12R.getDefaultCell().setBorder(0);

						PdfPCell cell72R = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell72R.setBackgroundColor(new GrayColor(0.93f));
						cell72R.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell72R.setBorder(PdfPCell.NO_BORDER);
						table12R.addCell(cell72R);
						PdfPCell cell73R = new PdfPCell(
								new Paragraph(downConDesc.getBridgingDescriptionAvailabilityRem(),
										new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell73R.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell73R.setBackgroundColor(new GrayColor(0.93f));
						cell73R.setBorder(PdfPCell.NO_BORDER);
						table12R.addCell(cell73R);

						document.add(table12);
						document.add(table12R);

						if (downConDesc.getBridgingDescriptionAvailabilityOb().equalsIgnoreCase("applicable")) {

							for (BridgingDescription bridgingDescription : downConDesc.getBridgingDescription()) {

								if (!bridgingDescription.getFlag().equalsIgnoreCase("R")) {
									PdfPTable table1 = BridgingCablesItr(font, bridgingDescription);
									document.add(table1);
								}
							}
						}

// Holders Accordian  Method

						document.newPage();

//					this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						PdfPTable HoldersHead = new PdfPTable(pointColumnWidths4);
						HoldersHead.setWidthPercentage(100); // Width 100%
						HoldersHead.setSpacingBefore(5f); // Space before table
						HoldersHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell13 = new PdfPCell();
						cell13.setPhrase(new Phrase("Holders", font11B));
						cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell13.setBackgroundColor(new GrayColor(0.93f));
						cell13.setFixedHeight(20f);
						cell13.setColspan(4);
						HoldersHead.addCell(cell13);
						document.add(HoldersHead);

						PdfPTable table13 = new PdfPTable(pointColumnWidthsAvailability);

						table13.setWidthPercentage(100); // Width 100%
//				table13.setSpacingBefore(5f); // Space before table
//				table13.setSpacingAfter(5f); // Space after table
						table13.getDefaultCell().setBorder(0);

						PdfPCell cell152 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell152.setBackgroundColor(new GrayColor(0.93f));
						cell152.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell152.setBorder(PdfPCell.NO_BORDER);
						table13.addCell(cell152);
						PdfPCell cell153 = new PdfPCell(new Paragraph(downConDesc.getHolderAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell153.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell153.setBackgroundColor(new GrayColor(0.93f));
						cell153.setBorder(PdfPCell.NO_BORDER);
						table13.addCell(cell153);

						PdfPTable table13R = new PdfPTable(pointColumnWidthsAvailability);
						table13R.setWidthPercentage(100); // Width 100%
						table13R.setSpacingBefore(5f); // Space before table
//				table13R.setSpacingAfter(5f); // Space after table
						table13R.getDefaultCell().setBorder(0);

						PdfPCell cell7HR = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7HR.setBackgroundColor(new GrayColor(0.93f));
						cell7HR.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7HR.setBorder(PdfPCell.NO_BORDER);
						table13R.addCell(cell7HR);
						PdfPCell cell7H = new PdfPCell(new Paragraph(downConDesc.getHolderAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7H.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7H.setBackgroundColor(new GrayColor(0.93f));
						cell7H.setBorder(PdfPCell.NO_BORDER);
						table13R.addCell(cell7H);

						document.add(table13);
						document.add(table13R);

						if (downConDesc.getHolderAvailabilityOb().equalsIgnoreCase("applicable")) {

							for (Holder holder : downConDesc.getHolder()) {

								if (!holder.getFlag().equalsIgnoreCase("R")) {

									PdfPTable table1 = HoldersItr(font, holder);
									document.add(table1);
								}
							}
						}

// Connectors Accordian  Method		

						document.newPage();

//					this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						PdfPTable ConnectorsHead = new PdfPTable(pointColumnWidths4);
						ConnectorsHead.setWidthPercentage(100); // Width 100%
						ConnectorsHead.setSpacingBefore(5f); // Space before table
						ConnectorsHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell14 = new PdfPCell();
						cell14.setPhrase(new Phrase("Connectors", font11B));
						cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell14.setBackgroundColor(new GrayColor(0.93f));
						cell14.setFixedHeight(20f);
						cell14.setColspan(4);
						ConnectorsHead.addCell(cell14);
						document.add(ConnectorsHead);

						PdfPTable table14 = new PdfPTable(pointColumnWidthsAvailability);

						table14.setWidthPercentage(100); // Width 100%
//				table14.setSpacingBefore(5f); // Space before table
//				table14.setSpacingAfter(5f); // Space after table
						table14.getDefaultCell().setBorder(0);

						PdfPCell cell154 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell154.setBackgroundColor(new GrayColor(0.93f));
						cell154.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell154.setBorder(PdfPCell.NO_BORDER);
						table14.addCell(cell154);
						PdfPCell cell155 = new PdfPCell(new Paragraph(downConDesc.getConnectorsAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell155.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell155.setBackgroundColor(new GrayColor(0.93f));
						cell155.setBorder(PdfPCell.NO_BORDER);
						table14.addCell(cell155);

						PdfPTable table14R = new PdfPTable(pointColumnWidthsAvailability);
						table14R.setWidthPercentage(100); // Width 100%
						table14R.setSpacingBefore(5f); // Space before table
//				table14R.setSpacingAfter(5f); // Space after table
						table14R.getDefaultCell().setBorder(0);

						PdfPCell cell7C = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7C.setBackgroundColor(new GrayColor(0.93f));
						cell7C.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7C.setBorder(PdfPCell.NO_BORDER);
						table14R.addCell(cell7C);
						PdfPCell cell7CR = new PdfPCell(new Paragraph(downConDesc.getConnectorsAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7CR.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7CR.setBackgroundColor(new GrayColor(0.93f));
						cell7CR.setBorder(PdfPCell.NO_BORDER);
						table14R.addCell(cell7CR);

						document.add(table14);
						document.add(table14R);

						if (downConDesc.getConnectorsAvailabilityOb().equalsIgnoreCase("applicable")) {

							for (Connectors connectors : downConDesc.getConnectors()) {

								if (!connectors.getFlag().equalsIgnoreCase("R")) {
									PdfPTable table1 = ConnectorItr(font, connectors);
									document.add(table1);
								}
							}
						}

// Lightning Counters Accordian  Method		

						document.newPage();

//					this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						PdfPTable LightningCountersHead = new PdfPTable(pointColumnWidths4);
						LightningCountersHead.setWidthPercentage(100); // Width 100%
						LightningCountersHead.setSpacingBefore(5f); // Space before table
						LightningCountersHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell15 = new PdfPCell();
						cell15.setPhrase(new Phrase("Lightning Counters", font11B));
						cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell15.setBackgroundColor(new GrayColor(0.93f));
						cell15.setFixedHeight(20f);
						cell15.setColspan(4);
						LightningCountersHead.addCell(cell15);
						document.add(LightningCountersHead);

						PdfPTable table15 = new PdfPTable(pointColumnWidthsAvailability);

						table15.setWidthPercentage(100); // Width 100%
//				table15.setSpacingBefore(5f); // Space before table
//				table15.setSpacingAfter(5f); // Space after table
						table15.getDefaultCell().setBorder(0);

						PdfPCell cell156 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell156.setBackgroundColor(new GrayColor(0.93f));
						cell156.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell156.setBorder(PdfPCell.NO_BORDER);
						table15.addCell(cell156);
						PdfPCell cell157 = new PdfPCell(new Paragraph(downConDesc.getLightningCounterAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell157.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell157.setBackgroundColor(new GrayColor(0.93f));
						cell157.setBorder(PdfPCell.NO_BORDER);
						table15.addCell(cell157);

						PdfPTable table15R = new PdfPTable(pointColumnWidthsAvailability);
						table15R.setWidthPercentage(100); // Width 100%
						table15R.setSpacingBefore(5f); // Space before table
//				table15R.setSpacingAfter(5f); // Space after table
						table15R.getDefaultCell().setBorder(0);

						PdfPCell cell7L = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7L.setBackgroundColor(new GrayColor(0.93f));
						cell7L.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7L.setBorder(PdfPCell.NO_BORDER);
						table15R.addCell(cell7L);
						PdfPCell cell7LR = new PdfPCell(new Paragraph(downConDesc.getLightningCounterAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7LR.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7LR.setBackgroundColor(new GrayColor(0.93f));
						cell7LR.setBorder(PdfPCell.NO_BORDER);
						table15R.addCell(cell7LR);

						document.add(table15);
						document.add(table15R);

						if (downConDesc.getLightningCounterAvailabilityOb().equalsIgnoreCase("applicable")) {

							for (LightningCounter lightningCounter : downConDesc.getLightningCounter()) {

								if (!lightningCounter.getFlag().equalsIgnoreCase("R")) {
									PdfPTable table1 = LightingCountersItr(font, lightningCounter);
									document.add(table1);
								}
							}
						}

// Test Joints Accordian  Method		

						document.newPage();

//					this method for Adding the Main Header Fields for Every Page
						MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

						PdfPTable TestJointsHead = new PdfPTable(pointColumnWidths4);
						TestJointsHead.setWidthPercentage(100); // Width 100%
						TestJointsHead.setSpacingBefore(5f); // Space before table
						TestJointsHead.setSpacingAfter(10f); // Space after table

						PdfPCell cell16 = new PdfPCell();
						cell16.setPhrase(new Phrase("Test Joints", font11B));
						cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell16.setBackgroundColor(new GrayColor(0.93f));
						cell16.setFixedHeight(20f);
						cell16.setColspan(4);
						TestJointsHead.addCell(cell16);
						document.add(TestJointsHead);

						PdfPTable table16 = new PdfPTable(pointColumnWidthsAvailability);

						table16.setWidthPercentage(100); // Width 100%
//				table16.setSpacingBefore(5f); // Space before table
//				table16.setSpacingAfter(5f); // Space after table
						table16.getDefaultCell().setBorder(0);

						PdfPCell cell158 = new PdfPCell(
								new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell158.setBackgroundColor(new GrayColor(0.93f));
						cell158.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell158.setBorder(PdfPCell.NO_BORDER);
						table16.addCell(cell158);
						PdfPCell cell159 = new PdfPCell(new Paragraph(downConDesc.getTestingJointAvailabilityOb(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell159.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell159.setBackgroundColor(new GrayColor(0.93f));
						cell159.setBorder(PdfPCell.NO_BORDER);
						table16.addCell(cell159);

						PdfPTable table16R = new PdfPTable(pointColumnWidthsAvailability);
						table16R.setWidthPercentage(100); // Width 100%
						table16R.setSpacingBefore(5f); // Space before table
//				table16R.setSpacingAfter(5f); // Space after table
						table16R.getDefaultCell().setBorder(0);

						PdfPCell cell7T = new PdfPCell(
								new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7T.setBackgroundColor(new GrayColor(0.93f));
						cell7T.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7T.setBorder(PdfPCell.NO_BORDER);
						table16R.addCell(cell7T);
						PdfPCell cell7TR = new PdfPCell(new Paragraph(downConDesc.getTestingJointAvailabilityRem(),
								new Font(BaseFont.createFont(), 10, Font.NORMAL)));
						cell7TR.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell7TR.setBackgroundColor(new GrayColor(0.93f));
						cell7TR.setBorder(PdfPCell.NO_BORDER);
						table16R.addCell(cell7TR);

						document.add(table16);
						document.add(table16R);

						if (downConDesc.getTestingJointAvailabilityOb().equalsIgnoreCase("Available")) {

							for (TestingJoint testingJoint : downConDesc.getTestingJoint()) {

								if (!testingJoint.getFlag().equalsIgnoreCase("R")) {
									PdfPTable table1 = TestJointsItr(font, testingJoint);
									document.add(table1);
								}
							}
						}

// Testing of earth electrodes Accordian  Method

						if (basicLps1.getAvailabilityOfPreviousReport().equalsIgnoreCase("NO")) {

							document.newPage();

//						this method for Adding the Main Header Fields for Every Page
							MainHeaderPropertiesLabel(document, basicLps1, downConDesc);

							PdfPTable TestEarthElectrodeHead = new PdfPTable(pointColumnWidths4);
							TestEarthElectrodeHead.setWidthPercentage(100); // Width 100%
							TestEarthElectrodeHead.setSpacingBefore(5f); // Space before table
							TestEarthElectrodeHead.setSpacingAfter(10f); // Space after table

							PdfPCell cell17 = new PdfPCell();
							cell17.setPhrase(new Phrase("Testing of Down Conductors", font11B));
							cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell17.setBackgroundColor(new GrayColor(0.93f));
							cell17.setFixedHeight(20f);
							cell17.setColspan(4);
							TestEarthElectrodeHead.addCell(cell17);
							document.add(TestEarthElectrodeHead);

							PdfPTable table17 = new PdfPTable(pointColumnWidthsAvailability);

							table17.setWidthPercentage(100); // Width 100%
//				table17.setSpacingBefore(5f); // Space before table
//				table17.setSpacingAfter(5f); // Space after table
							table17.getDefaultCell().setBorder(0);

							PdfPCell cell160 = new PdfPCell(
									new Paragraph("Availability:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell160.setBackgroundColor(new GrayColor(0.93f));
							cell160.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell160.setBorder(PdfPCell.NO_BORDER);
							table17.addCell(cell160);
							PdfPCell cell161 = new PdfPCell(
									new Paragraph(downConDesc.getDownConductorTestingAvailabilityOb(),
											new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell161.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell161.setBackgroundColor(new GrayColor(0.93f));
							cell161.setBorder(PdfPCell.NO_BORDER);
							table17.addCell(cell161);

							PdfPTable table17R = new PdfPTable(pointColumnWidthsAvailability);
							table17R.setWidthPercentage(100); // Width 100%
							table17R.setSpacingBefore(5f); // Space before table
//				table17R.setSpacingAfter(5f); // Space after table
							table17R.getDefaultCell().setBorder(0);

							PdfPCell cell7TE = new PdfPCell(
									new Paragraph("Remarks:", new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell7TE.setBackgroundColor(new GrayColor(0.93f));
							cell7TE.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell7TE.setBorder(PdfPCell.NO_BORDER);
							table17R.addCell(cell7TE);
							PdfPCell cell7TER = new PdfPCell(
									new Paragraph(downConDesc.getDownConductorTestingAvailabilityRem(),
											new Font(BaseFont.createFont(), 10, Font.NORMAL)));
							cell7TER.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell7TER.setBackgroundColor(new GrayColor(0.93f));
							cell7TER.setBorder(PdfPCell.NO_BORDER);
							table17R.addCell(cell7TER);

							document.add(table17);
							document.add(table17R);

							if (downConDesc.getDownConductorTestingAvailabilityOb().equalsIgnoreCase("In Scope")) {

								Font font11 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD,
										BaseColor.BLACK);
								float[] pointColumnWidths9 = { 15, 40F, 35F, 35F, 50F };

								PdfPTable table18 = new PdfPTable(pointColumnWidths9);
								table18.setWidthPercentage(100); // Width 100%
								table18.setSpacingBefore(10f); // Space before table
								table18.setWidthPercentage(100);

								PdfPCell cell301 = new PdfPCell(new Paragraph("SL.NO", font11));
								cell301.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell301.setGrayFill(0.92f);
								table18.addCell(cell301);

								PdfPCell cell3111 = new PdfPCell(new Paragraph("Reference", font11));
								cell3111.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell3111.setFixedHeight(20f);
								cell3111.setGrayFill(0.92f);
								table18.addCell(cell3111);

								PdfPCell cell321 = new PdfPCell(new Paragraph("Length in mm", font11));
								cell321.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell321.setFixedHeight(20f);
								cell321.setGrayFill(0.92f);
								table18.addCell(cell321);

								PdfPCell cell313 = new PdfPCell(new Paragraph("Resistance in m ohms", font11));
								cell313.setGrayFill(0.92f);
								cell313.setHorizontalAlignment(Element.ALIGN_CENTER);
								table18.addCell(cell313);

								PdfPCell cell332 = new PdfPCell(new Paragraph("Remarks", font11));
								cell332.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell332.setFixedHeight(20f);
								cell332.setGrayFill(0.92f);
								table18.addCell(cell332);

								document.add(table18);

								for (DownConductorTesting downConductorTesting : downConDesc
										.getDownConductorTesting()) {
									if (!downConductorTesting.getFlag().equalsIgnoreCase("R")) {

										PdfPTable table19 = new PdfPTable(pointColumnWidths9);
										table19.setWidthPercentage(100); // Width 100%

										PdfPCell cell305 = new PdfPCell(
												new Paragraph(downConductorTesting.getSerialNo().toString(), font));
										cell305.setHorizontalAlignment(Element.ALIGN_CENTER);
										table19.addCell(cell305);

										PdfPCell cell31 = new PdfPCell(
												new Paragraph(downConductorTesting.getReference(), font));
										cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
//					cell31.setFixedHeight(20f);
										table19.addCell(cell31);

										PdfPCell cell3213 = new PdfPCell(
												new Paragraph(downConductorTesting.getLength().toString(), font));
										cell3213.setHorizontalAlignment(Element.ALIGN_CENTER);
										table19.addCell(cell3213);

										PdfPCell cell90 = new PdfPCell(
												new Paragraph(downConductorTesting.getResistance().toString(), font));
										cell90.setHorizontalAlignment(Element.ALIGN_CENTER);
										table19.addCell(cell90);

										PdfPCell cell3321 = new PdfPCell(
												new Paragraph(downConductorTesting.getRemarks(), font));
										cell3321.setHorizontalAlignment(Element.ALIGN_LEFT);
										table19.addCell(cell3321);

										document.add(table19);
									}
								}
							}
						}

					}
					document.newPage();
				}

				document.close();
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new DownConductorException("Invalid Inputs");
		}
	}

	private void MainHeaderPropertiesLabel(Document document, BasicLps basicLps1, DownConductorDescription downConDesc)
			throws DocumentException, IOException {
		float[] pointColumnWidths200 = { 100F };

		PdfPTable table1111 = new PdfPTable(pointColumnWidths200);
		table1111.setWidthPercentage(100); // Width 100%
//					    table1111.setSpacingBefore(5f); // Space before table
//					    table1111.setSpacingAfter(f); // Space after table
		table1111.getDefaultCell().setBorder(0);

		PdfPCell arrangements1001 = new PdfPCell(new Paragraph(
				basicLps1.getProjectName() + " / " + downConDesc.getBuildingName() + " / "
						+ downConDesc.getBuildingNumber().toString(),
				new Font(BaseFont.createFont(), 8, Font.NORMAL | Font.NORMAL)));
//						arrangements1001.setBackgroundColor(new BaseColor(203, 183, 162));
		arrangements1001.setHorizontalAlignment(Element.ALIGN_RIGHT);
		arrangements1001.setBorder(PdfPCell.NO_BORDER);
		table1111.addCell(arrangements1001);
		document.add(table1111);
	}

	private PdfPTable TestJointsItr(Font font, TestingJoint testingJoint) {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("12(a)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Material of test joint", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(testingJoint.getMaterialTestJointOb(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (testingJoint.getMaterialTestJointRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getMaterialTestJointRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("12(b)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell4);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(new Phrase("Accessibility of test joint", font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(testingJoint.getAccessibilityOfTestJointOb(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (testingJoint.getAccessibilityOfTestJointRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getAccessibilityOfTestJointRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("12(c)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Presence of provision of protection of test joint by non metalic casings", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell9 = new PdfPCell(new Paragraph(testingJoint.getNonMetalicProtectionOb(), font));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell9);

		if (testingJoint.getNonMetalicProtectionRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getNonMetalicProtectionRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell10 = new PdfPCell();
		cell10.setPhrase(new Phrase("12(d)", font));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell10);

		PdfPCell cell17 = new PdfPCell();
		cell17.setPhrase(new Phrase("Is test joint placed 1.5 metre from the ground level", font));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(testingJoint.getTestJointPlacedGroungLevelOb(), font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell18);

		if (testingJoint.getTestJointPlacedGroungLevelRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getTestJointPlacedGroungLevelRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell19 = new PdfPCell();
		cell19.setPhrase(new Phrase("12(e)", font));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell19);

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase(
				"Presence of Bi-metalllic corrosion issue (connections between dissimilar metals are not allowed)",
				font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(testingJoint.getBimetallicIssueCheckOb(), font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell21);

		if (testingJoint.getBimetallicIssueCheckRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getBimetallicIssueCheckRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase("12(f)", font));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell22);

		PdfPCell cell23 = new PdfPCell();
		cell23.setPhrase(new Phrase(
				"Ensure whether down conductor and earthing conductor touch each other at test joint", font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell23.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell23);

		PdfPCell cell24 = new PdfPCell(new Paragraph(testingJoint.getTouchingConductorsOb(), font));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell24);

		if (testingJoint.getTouchingConductorsRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getTouchingConductorsRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell26 = new PdfPCell();
		cell26.setPhrase(new Phrase("12(g)", font));
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell26.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell26);

		PdfPCell cell27 = new PdfPCell();
		cell27.setPhrase(new Phrase("Total number of test joints", font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell27.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(testingJoint.getTotalNoOfTestJointOB().toString(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell28);

		if (testingJoint.getTotalNoOfTestJointRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getTotalNoOfTestJointRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell29 = new PdfPCell();
		cell29.setPhrase(new Phrase("12(h)", font));
		cell29.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell29.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell29);

		PdfPCell cell30 = new PdfPCell();
		cell30.setPhrase(new Phrase("Number of test joints inspected", font));
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell30.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell30);

		PdfPCell cell31 = new PdfPCell(new Paragraph(testingJoint.getInspectedNoOb().toString(), font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell31);

		if (testingJoint.getInspectedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getInspectedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell32 = new PdfPCell();
		cell32.setPhrase(new Phrase("12(i)", font));
		cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell32.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell32);

		PdfPCell cell33 = new PdfPCell();
		cell33.setPhrase(new Phrase("Number of inspections passed", font));
		cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell33.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell33);

		PdfPCell cell34 = new PdfPCell(new Paragraph(testingJoint.getInspectionPassedNoOb().toString(), font));
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell34);

		if (testingJoint.getInspectionPassedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getInspectionPassedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell35 = new PdfPCell();
		cell35.setPhrase(new Phrase("12(j)", font));
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell35.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell35);

		PdfPCell cell36 = new PdfPCell();
		cell36.setPhrase(new Phrase("Number of inspections failed", font));
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell36.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph(testingJoint.getInspectionFailedNoOb().toString(), font));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell37);

		if (testingJoint.getInspectionFailedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(testingJoint.getInspectionFailedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}
		return table1;
	}

	private PdfPTable LightingCountersItr(Font font, LightningCounter lightningCounter) {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("11(a)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Threshold current in kA", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(lightningCounter.getThreadHoldCurrentOb().toString(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (lightningCounter.getThreadHoldCurrentRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getThreadHoldCurrentRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("11(b)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell4);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(new Phrase("Maximum withstand current in kA", font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(lightningCounter.getMaximumWithStandCurrentOb().toString(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (lightningCounter.getMaximumWithStandCurrentRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getMaximumWithStandCurrentRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("11(c)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Counts", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell9 = new PdfPCell(new Paragraph(lightningCounter.getCountsOb().toString(), font));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell9);

		if (lightningCounter.getCountsRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getCountsRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell10 = new PdfPCell();
		cell10.setPhrase(new Phrase("11(d)", font));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell10);

		PdfPCell cell16 = new PdfPCell();
		cell16.setPhrase(new Phrase("Battery life time in years", font));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell16.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell16);

		PdfPCell cell17 = new PdfPCell(new Paragraph(lightningCounter.getBatteryLifeTimeOb().toString(), font));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell17);

		if (lightningCounter.getBatteryLifeTimeRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getBatteryLifeTimeRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell18 = new PdfPCell();
		cell18.setPhrase(new Phrase("11(e)", font));
		cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell18.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell18);

		PdfPCell cell19 = new PdfPCell();
		cell19.setPhrase(new Phrase("Proper connections of lightning counter for correct functioning is made", font));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell19.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell19);

		PdfPCell cell20 = new PdfPCell(new Paragraph(lightningCounter.getProperConnectionLightingCounterOb(), font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell20);

		if (lightningCounter.getProperConnectionLightingCounterRem() != null) {
			PdfPCell cell38 = new PdfPCell(
					new Paragraph(lightningCounter.getProperConnectionLightingCounterRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell21 = new PdfPCell();
		cell21.setPhrase(new Phrase("11(f)", font));
		cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell21.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell18);

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase("Is lightning counter is placed 0.5 metre above test joint", font));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell22);

		PdfPCell cell23 = new PdfPCell(new Paragraph(lightningCounter.getLightingCounterPlacedOb(), font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell23);

		if (lightningCounter.getLightingCounterPlacedRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getLightingCounterPlacedRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell24 = new PdfPCell();
		cell24.setPhrase(new Phrase("11(g)", font));
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell24.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell24);

		PdfPCell cell25 = new PdfPCell();
		cell25.setPhrase(new Phrase("Condition of lightning counter and its connection (loose/tight/corroded)", font));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph(lightningCounter.getConditionOfLightingCounterOb(), font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell26);

		if (lightningCounter.getConditionOfLightingCounterRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getConditionOfLightingCounterRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell27 = new PdfPCell();
		cell27.setPhrase(new Phrase("11(h)", font));
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell27.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell();
		cell28.setPhrase(new Phrase("Total number of lightning counter", font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell28.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph(lightningCounter.getTotalNoLightingCounterOb().toString(), font));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell29);

		if (lightningCounter.getTotalNoLightingCounterRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getConditionOfLightingCounterRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell30 = new PdfPCell();
		cell30.setPhrase(new Phrase("11(i)", font));
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell30.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell31 = new PdfPCell();
		cell31.setPhrase(new Phrase("Number of lightning counter inspected", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell31);

		PdfPCell cell32 = new PdfPCell(new Paragraph(lightningCounter.getInspectedNoOb().toString(), font));
		cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell32);

		if (lightningCounter.getInspectedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getInspectedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell33 = new PdfPCell();
		cell33.setPhrase(new Phrase("11(i)", font));
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell33.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell33);

		PdfPCell cell34 = new PdfPCell();
		cell34.setPhrase(new Phrase("Number of inspections passed", font));
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell34.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell34);

		PdfPCell cell35 = new PdfPCell(new Paragraph(lightningCounter.getInspectionPassedNoOb().toString(), font));
		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell35);

		if (lightningCounter.getInspectionPassedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getInspectionPassedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell36 = new PdfPCell();
		cell36.setPhrase(new Phrase("11(k)", font));
		cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell36.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell36);

		PdfPCell cell37 = new PdfPCell();
		cell37.setPhrase(new Phrase("Number of inspections failed", font));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell37.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell37);

		PdfPCell cell38 = new PdfPCell(new Paragraph(lightningCounter.getInspectionFailedNoOb().toString(), font));
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell38);

		if (lightningCounter.getInspectionFailedNoRem() != null) {
			PdfPCell cell39 = new PdfPCell(new Paragraph(lightningCounter.getInspectionFailedNoRem(), font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}
		return table1;
	}

	private PdfPTable ConnectorItr(Font font, Connectors connectors) {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("10(a)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(connectors.getPhysicalInspectionOb(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (connectors.getPhysicalInspectionRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getPhysicalInspectionRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("10(b)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell4);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(new Phrase("check the connection of straight connector (tight/loose/corroded)", font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(connectors.getStrightConnectCheckOb(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (connectors.getStrightConnectCheckRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getStrightConnectCheckRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("10(c)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Material of straight connector/ferrule/tube", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell9 = new PdfPCell(new Paragraph(connectors.getMaterialConnectorOb(), font));
		cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell9);

		if (connectors.getMaterialConnectorRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getMaterialConnectorRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell10 = new PdfPCell();
		cell10.setPhrase(new Phrase("10(d)", font));
		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell10.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell10);

		PdfPCell cell15 = new PdfPCell();
		cell15.setPhrase(new Phrase("Max number of connector in a down conductor", font));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell15.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph(connectors.getMaxConnectorsDownConductorOb().toString(), font));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell16);

		if (connectors.getMaxConnectorsDownConductorRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getMaxConnectorsDownConductorRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell17 = new PdfPCell();
		cell17.setPhrase(new Phrase("10(e)", font));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell17);

		PdfPCell cell18 = new PdfPCell();
		cell18.setPhrase(new Phrase("Total number of connectors", font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell18.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(connectors.getTotalNoConnectorsOb().toString(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell19);

		if (connectors.getTotalNoConnectorsRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getTotalNoConnectorsRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase("10(f)", font));
		cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell20);

		PdfPCell cell21 = new PdfPCell();
		cell21.setPhrase(new Phrase("Number of connectors inspected", font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell21.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell21);

		PdfPCell cell22 = new PdfPCell(new Paragraph(connectors.getInspectedNoOb().toString(), font));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell22);

		if (connectors.getInspectedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getInspectedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell23 = new PdfPCell();
		cell23.setPhrase(new Phrase("10(g)", font));
		cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell23.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell23);

		PdfPCell cell24 = new PdfPCell();
		cell24.setPhrase(new Phrase("Number of inspection passed", font));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell24.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell24);

		PdfPCell cell25 = new PdfPCell(new Paragraph(connectors.getInspectionPassedNoOb().toString(), font));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell25);

		if (connectors.getInspectionPassedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getInspectionPassedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell26 = new PdfPCell();
		cell26.setPhrase(new Phrase("10(h)", font));
		cell26.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell26.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell26);

		PdfPCell cell27 = new PdfPCell();
		cell27.setPhrase(new Phrase("Number of inspection failed", font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell27.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell(new Paragraph(connectors.getInspectionFailedNoOb().toString(), font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell28);

		if (connectors.getInspectionFailedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(connectors.getInspectionFailedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}
		return table1;
	}

	private PdfPTable HoldersItr(Font font, Holder holder) {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell9 = new PdfPCell();
		cell9.setPhrase(new Phrase("9(a)", font));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell9);

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase("Physical inspection (damage/break/corroded)", font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(holder.getPhysicalInspectionOb(), font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell21);

		if (holder.getPhysicalInspectionRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getPhysicalInspectionRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("9(b)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Conductor holder is firmly fixed/mounted over the flat surface of wall", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(holder.getConductHolderFlatSurfaceOb(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (holder.getConductHolderFlatSurfaceRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getConductHolderFlatSurfaceRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("9(c)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(new Phrase(
				"Conductor is properly holded in the holder and connection of conductor with holder(tight/loose/corroded)",
				font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(holder.getConductorHoldedOb(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (holder.getConductorHoldedRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getConductorHoldedRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("9(d)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Distance between two successive holder in meters", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell10 = new PdfPCell(new Paragraph(holder.getSuccessiveDistanceOb().toString(), font));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell10);

		if (holder.getSuccessiveDistanceRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getSuccessiveDistanceRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell14 = new PdfPCell();
		cell14.setPhrase(new Phrase("9(e)", font));
		cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell14.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell14);

		PdfPCell cell15 = new PdfPCell();
		cell15.setPhrase(new Phrase("Material of holder", font));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell15.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell15);

		PdfPCell cell16 = new PdfPCell(new Paragraph(holder.getMaterialHolderOb(), font));
		cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell16);

		if (holder.getMaterialHolderRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getMaterialHolderRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell17 = new PdfPCell();
		cell17.setPhrase(new Phrase("9(f)", font));
		cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell17);

		PdfPCell cell18 = new PdfPCell();
		cell18.setPhrase(new Phrase("Total number of holders", font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell18.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(holder.getTotalNoHolderOb().toString(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell19);

		if (holder.getTotalNoHolderRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getTotalNoHolderRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase("9(g)", font));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell22);

		PdfPCell cell23 = new PdfPCell();
		cell23.setPhrase(new Phrase("Number of holders inspected", font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell23.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell23);

		PdfPCell cell24 = new PdfPCell(new Paragraph(holder.getInspectedNoOb().toString(), font));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell24);

		if (holder.getInspectedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getInspectedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell25 = new PdfPCell();
		cell25.setPhrase(new Phrase("9(h)", font));
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell();
		cell26.setPhrase(new Phrase("Number of inspection passed", font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(holder.getInspectionPassedNoOb().toString(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell27);

		if (holder.getInspectionPassedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getInspectionPassedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell28 = new PdfPCell();
		cell28.setPhrase(new Phrase("9(i)", font));
		cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell28.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell();
		cell29.setPhrase(new Phrase("Number of inspection failed", font));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell29.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell29);

		PdfPCell cell30 = new PdfPCell(new Paragraph(holder.getInspectionFailedNoOb().toString(), font));
		cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell30);

		if (holder.getInspectionFailedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(holder.getInspectionFailedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}
		return table1;
	}

	private PdfPTable BridgingCablesItr(Font font, BridgingDescription bridgingDescription) {

		float[] pointColumnWidths41 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths41);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell9 = new PdfPCell();
		cell9.setPhrase(new Phrase("8(a)", font));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell9);

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase(
				"Ensure bridging cable is used at corner of structures to avoid sharp bends (applicable in case if bridging cable is used to connect air termination and down condcutor",
				font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(bridgingDescription.getEnsureBridgingCableOb(), font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell21);

		if (bridgingDescription.getEnsureBridgingCableRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getEnsureBridgingCableRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("8(b)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Check the presence of connection of aluminium conductor into the side wall", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(bridgingDescription.getAluminiumConductorSideWallOb(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (bridgingDescription.getAluminiumConductorSideWallRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getAluminiumConductorSideWallRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("8(c)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell4);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(
				new Phrase("Ensure the connection of bridging cable (break/damage/loose/tight /corroded)", font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableConnectionOb(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (bridgingDescription.getBridgingCableConnectionRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableConnectionRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("8(d)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Material of bridging cable", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell10 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableMaterialOb(), font));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell10);

		if (bridgingDescription.getBridgingCableMaterialRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableMaterialRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell13 = new PdfPCell();
		cell13.setPhrase(new Phrase("8(e)", font));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell13);

		PdfPCell cell14 = new PdfPCell();
		cell14.setPhrase(new Phrase("Size/cross section area of bridging cable", font));
		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell14.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableSizeOb(), font));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell15);

		if (bridgingDescription.getBridgingCableSizeRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getBridgingCableSizeRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell16 = new PdfPCell();
		cell16.setPhrase(new Phrase("8(f)", font));
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell16.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell16);

		PdfPCell cell17 = new PdfPCell();
		cell17.setPhrase(new Phrase("Total number of bridging cables", font));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(bridgingDescription.getTotalNoBridgingCableOb().toString(), font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell18);

		if (bridgingDescription.getTotalNoBridgingCableRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getTotalNoBridgingCableRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell19 = new PdfPCell();
		cell19.setPhrase(new Phrase("8(g)", font));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell19);

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase("Number of bridging cables inspected", font));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell22);

		PdfPCell cell23 = new PdfPCell(new Paragraph(bridgingDescription.getInspectedNoOb().toString(), font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell23);

		if (bridgingDescription.getInspectedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getInspectedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell24 = new PdfPCell();
		cell24.setPhrase(new Phrase("8(h)", font));
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell24.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell24);

		PdfPCell cell25 = new PdfPCell();
		cell25.setPhrase(new Phrase("Number of inspections passed", font));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph(bridgingDescription.getInspectionPassedNoOb().toString(), font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell26);

		if (bridgingDescription.getInspectionPassedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getInspectionPassedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell27 = new PdfPCell();
		cell27.setPhrase(new Phrase("8(i)", font));
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell27.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell();
		cell28.setPhrase(new Phrase("Number of inspections failed", font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell28.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph(bridgingDescription.getInspectionFailedNoOb().toString(), font));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell29);

		if (bridgingDescription.getInspectionFailedNoRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(bridgingDescription.getInspectionFailedNoRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}
		return table1;
	}

	private PdfPTable DownConductorItr(Font font, DownConductor downConductor11) {

		float[] pointColumnWidths30 = { 25F, 150F, 55F, 50F };

		PdfPTable table1 = new PdfPTable(pointColumnWidths30);
		table1.setWidthPercentage(100); // Width 100%
		table1.setSpacingBefore(10f); // Space before table
		table1.setSpacingAfter(5f); // Space after table
		table1.getDefaultCell().setBorder(0);

		PdfPCell cell9 = new PdfPCell();
		cell9.setPhrase(new Phrase("7(a)", font));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell9);

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase("Physical inspection (damage/break/bend/corroded/routing as per design)", font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(downConductor11.getPhysicalInspectionOb(), font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell21);

		if (downConductor11.getPhysicalInspectionRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getPhysicalInspectionRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell1 = new PdfPCell();
		cell1.setPhrase(new Phrase("7(b)", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPhrase(new Phrase("Material of conductor", font));
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Paragraph(downConductor11.getConductMaterialOb(), font));
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell3);

		if (downConductor11.getConductMaterialRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getConductMaterialRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell4 = new PdfPCell();
		cell4.setPhrase(new Phrase("7(c)", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell4);

		PdfPCell cell5 = new PdfPCell();
		cell5.setPhrase(new Phrase("Size/cross section area of conductor", font));
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Paragraph(downConductor11.getConductSizeOb(), font));
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell6);

		if (downConductor11.getConductSizeRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getConductSizeRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("7(d)", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell7);

		PdfPCell cell8 = new PdfPCell();
		cell8.setPhrase(new Phrase("Type of down conductor", font));
		cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell8.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell8);

		PdfPCell cell10 = new PdfPCell(new Paragraph(downConductor11.getDownConductExposedOb(), font));
		cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell10);

		if (downConductor11.getDownConductExposedRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getDownConductExposedRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell13 = new PdfPCell();
		cell13.setPhrase(new Phrase("7(e)", font));
		cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell13.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell13);

		PdfPCell cell14 = new PdfPCell();
		cell14.setPhrase(new Phrase(
				"Down conductor location changed as per site condition/approved in case of deviation check routing of down conductor whether shortest path is achieved or not",
				font));
		cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell14.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell14);

		PdfPCell cell15 = new PdfPCell(new Paragraph(downConductor11.getDownConductLocationdOb(), font));
		cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell15);

		if (downConductor11.getDownConductLocationdRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getDownConductLocationdRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell16 = new PdfPCell();
		cell16.setPhrase(new Phrase("7(f)", font));
		cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell16.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell16);

		PdfPCell cell17 = new PdfPCell();
		cell17.setPhrase(new Phrase(
				"Presence of gutters/ glass facade/water spouts near/in the path of down conductor (even if it’s a insulated conductor)",
				font));
		cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell17.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell17);

		PdfPCell cell18 = new PdfPCell(new Paragraph(downConductor11.getDownConductGutterOb(), font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell18);

		if (downConductor11.getDownConductGutterRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getDownConductGutterRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell19 = new PdfPCell();
		cell19.setPhrase(new Phrase("7(g)", font));
		cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell19.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell19);

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase(
				"whether down conductor is installed in service shaft (even if it’s a insulated conductor)", font));
		cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell22);

		PdfPCell cell23 = new PdfPCell(new Paragraph(downConductor11.getInstalledShaftDownConductorOb(), font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell23);

		if (downConductor11.getInstalledShaftDownConductorRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getInstalledShaftDownConductorRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell24 = new PdfPCell();
		cell24.setPhrase(new Phrase("7(h)", font));
		cell24.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell24.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell24);

		PdfPCell cell25 = new PdfPCell();
		cell25.setPhrase(new Phrase("Presence of connection of down conductor with nearest equipotential bar", font));
		cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell25);

		PdfPCell cell26 = new PdfPCell(new Paragraph(downConductor11.getEnsureDownCnoductOb(), font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell26);

		if (downConductor11.getEnsureDownCnoductRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getEnsureDownCnoductRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell27 = new PdfPCell();
		cell27.setPhrase(new Phrase("7(i)", font));
		cell27.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell27.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell27);

		PdfPCell cell28 = new PdfPCell();
		cell28.setPhrase(new Phrase("Installation of down conductor at equal spacing around perimeter", font));
		cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell28.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell28);

		PdfPCell cell29 = new PdfPCell(new Paragraph(downConductor11.getInstallationDownConductOb(), font));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell29);

		if (downConductor11.getInstallationDownConductRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getInstallationDownConductRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell30 = new PdfPCell();
		cell30.setPhrase(new Phrase("7(j)", font));
		cell30.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell30.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell30);

		PdfPCell cell31 = new PdfPCell();
		cell31.setPhrase(new Phrase("Maximum distance between down conductors", font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell31.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell31);

		PdfPCell cell32 = new PdfPCell(new Paragraph(downConductor11.getMaximumDownConductOb().toString(), font));
		cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell32);

		if (downConductor11.getMaximumDownConductRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getMaximumDownConductRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell33 = new PdfPCell();
		cell33.setPhrase(new Phrase("7(k)", font));
		cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell33.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell33);

		PdfPCell cell34 = new PdfPCell();
		cell34.setPhrase(new Phrase("Minimum distance between down conductors", font));
		cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell34.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell34);

		PdfPCell cell35 = new PdfPCell(new Paragraph(downConductor11.getManimumDownConductOb().toString(), font));
		cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell35);

		if (downConductor11.getManimumDownConductRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getManimumDownConductRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell36 = new PdfPCell();
		cell36.setPhrase(new Phrase("7(l)", font));
		cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell36.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell36);

		PdfPCell cell37 = new PdfPCell();
		cell37.setPhrase(new Phrase("Total number of down conductors", font));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell37.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell37);

		PdfPCell cell38 = new PdfPCell(new Paragraph(downConductor11.getTotalNoDownConductOb().toString(), font));
		cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell38);

		if (downConductor11.getTotalNoDownConductRem() != null) {
			PdfPCell cell39 = new PdfPCell(new Paragraph(downConductor11.getTotalNoDownConductRem(), font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell39);
		}

		PdfPCell cell39 = new PdfPCell();
		cell39.setPhrase(new Phrase("7(m)", font));
		cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell39.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell39);

		PdfPCell cell40 = new PdfPCell();
		cell40.setPhrase(new Phrase("Number of down conductors inspected", font));
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell40.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell40);

		PdfPCell cell41 = new PdfPCell(new Paragraph(downConductor11.getInspectedNoOb().toString(), font));
		cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell41);

		if (downConductor11.getInspectedNoRem() != null) {
			PdfPCell cell42 = new PdfPCell(new Paragraph(downConductor11.getInspectedNoRem(), font));
			cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell42);
		} else {
			PdfPCell cell43 = new PdfPCell(new Paragraph("Not Available", font));
			cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell43);
		}

		PdfPCell cell42 = new PdfPCell();
		cell42.setPhrase(new Phrase("7(n)", font));
		cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell42.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell42);

		PdfPCell cell43 = new PdfPCell();
		cell43.setPhrase(new Phrase("Number of inspections passed", font));
		cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell43.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell43);

		PdfPCell cell44 = new PdfPCell(new Paragraph(downConductor11.getInspectionPassedNoOb().toString(), font));
		cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell44);

		if (downConductor11.getInspectionPassedNoRem() != null) {
			PdfPCell cell45 = new PdfPCell(new Paragraph(downConductor11.getInspectionPassedNoRem(), font));
			cell45.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell45);
		} else {
			PdfPCell cell46 = new PdfPCell(new Paragraph("Not Available", font));
			cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell46);
		}

		PdfPCell cell45 = new PdfPCell();
		cell45.setPhrase(new Phrase("7(o)", font));
		cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell45.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell45);

		PdfPCell cell46 = new PdfPCell();
		cell46.setPhrase(new Phrase("Number of inspections failed", font));
		cell46.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell46.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell46);

		PdfPCell cell47 = new PdfPCell(new Paragraph(downConductor11.getInspectionFailedNoOb().toString(), font));
		cell47.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell47);

		if (downConductor11.getInspectionFailedNoRem() != null) {
			PdfPCell cell48 = new PdfPCell(new Paragraph(downConductor11.getInspectionFailedNoRem(), font));
			cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell48);
		} else {
			PdfPCell cell49 = new PdfPCell(new Paragraph("Not Available", font));
			cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell49);
		}

		PdfPCell cell50 = new PdfPCell();
		cell50.setPhrase(new Phrase("7(p)", font));
		cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell50.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell50);

		PdfPCell cell51 = new PdfPCell();
		cell51.setPhrase(new Phrase(
				"Average number of dangerous bends in each down conductor, which are going to affect the performance of LPS",
				font));
		cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell51.setBackgroundColor(new GrayColor(0.93f));
		table1.addCell(cell51);

		PdfPCell cell52 = new PdfPCell(new Paragraph(downConductor11.getAverageBendsOb().toString(), font));
		cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
		table1.addCell(cell52);

		if (downConductor11.getAverageBendsRem() != null) {
			PdfPCell cell48 = new PdfPCell(new Paragraph(downConductor11.getAverageBendsRem(), font));
			cell48.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell48);
		} else {
			PdfPCell cell49 = new PdfPCell(new Paragraph("Not Available", font));
			cell49.setHorizontalAlignment(Element.ALIGN_LEFT);
			table1.addCell(cell49);
		}
		return table1;
	}

	private PdfPTable DownConductorBasicDetails(Document document, float[] pointColumnWidths, Font font,
			DownConductorDescription downLps1) throws DocumentException, IOException {
	
//		PdfPTable table100 = new PdfPTable(pointColumnWidths);
//		table100.setWidthPercentage(100); // Width 100%
//		table100.setSpacingBefore(10f); // Space before table
//		table100.setSpacingAfter(5f); // Space after table
//		table100.getDefaultCell().setBorder(0);
//
//		PdfPCell cell1 = new PdfPCell(
//				new Paragraph("Building Number", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//		cell1.setBackgroundColor(new BaseColor(203, 183, 162));
//		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell1.setBorder(PdfPCell.NO_BORDER);
//		table100.addCell(cell1);
//		PdfPCell cell2 = new PdfPCell(new Paragraph(downLps1.getBuildingNumber().toString(),
//				new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
//		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell2.setBackgroundColor(new BaseColor(203, 183, 162));
//		cell2.setBorder(PdfPCell.NO_BORDER);
//		table100.addCell(cell2);
//		document.add(table100);
//
//		PdfPTable table1001 = new PdfPTable(pointColumnWidths);
//
//		table1001.setWidthPercentage(100); // Width 100%
//		table1001.setSpacingBefore(5f); // Space before table
//		table1001.setSpacingAfter(5f); // Space after table
//		table1001.setWidthPercentage(100);
//		table1001.getDefaultCell().setBorder(0);
//		PdfPCell cell3 = new PdfPCell(
//				new Paragraph("Building name:", new Font(BaseFont.createFont(), 11, Font.NORMAL | Font.BOLD)));
//		cell3.setBackgroundColor(new BaseColor(203, 183, 162));
//		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell3.setBorder(PdfPCell.NO_BORDER);
//		table1001.addCell(cell3);
//		PdfPCell cell4 = new PdfPCell(new Paragraph(downLps1.getBuildingName(),
//				new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD)));
//		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell4.setBackgroundColor(new BaseColor(203, 183, 162));
//		cell4.setBorder(PdfPCell.NO_BORDER);
//		table1001.addCell(cell4);
//		document.add(table1001);

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

		PdfPCell cell7 = new PdfPCell();
		cell7.setPhrase(new Phrase("1", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell7.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell7);

		PdfPCell cell18 = new PdfPCell();
		cell18.setPhrase(new Phrase(
				"Presence of Bi-metalllic corrosion issue (connections between dissimilar metals are not allowed)",
				font));
		cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell18.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell18);

		PdfPCell cell19 = new PdfPCell(new Paragraph(downLps1.getBiMetallicIssueOb(), font));
		cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell19);

		if (downLps1.getBiMetallicIssueRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getBiMetallicIssueRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell9 = new PdfPCell();
		cell9.setPhrase(new Phrase("2(a)", font));
		cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell9.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell9);

		PdfPCell cell20 = new PdfPCell();
		cell20.setPhrase(new Phrase("Presence of warning notice at ground level", font));
		cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell20.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell20);

		PdfPCell cell21 = new PdfPCell(new Paragraph(downLps1.getWarningNoticeGroundLevelOb(), font));
		cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell21);

		if (downLps1.getWarningNoticeGroundLevelRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getWarningNoticeGroundLevelRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell22 = new PdfPCell();
		cell22.setPhrase(new Phrase("2(b)", font));
		cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell22.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell22);

		PdfPCell cell23 = new PdfPCell();
		cell23.setPhrase(
				new Phrase("Presence of insulation of down conductors with PVC upto 3 meter from ground level", font));
		cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell23.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell23);

		PdfPCell cell24 = new PdfPCell(new Paragraph(downLps1.getInsulationPresenceOb(), font));
		cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell24);

		if (downLps1.getInsulationPresenceRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getInsulationPresenceRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell25 = new PdfPCell();
		cell25.setPhrase(new Phrase("3", font));
		cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell25.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell25);

		PdfPCell cell26 = new PdfPCell();
		cell26.setPhrase(new Phrase(
				"presence of power/ control/ instrumentation/telecommunication cable or cable passage in path/near/parallel to down conductors",
				font));
		cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell26.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell26);

		PdfPCell cell27 = new PdfPCell(new Paragraph(downLps1.getNoPowerDownConductorOb(), font));
		cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell27);

		if (downLps1.getNoPowerDownConductorRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getNoPowerDownConductorRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell28 = new PdfPCell();
		cell28.setPhrase(new Phrase("4", font));
		cell28.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell28.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell28);

		PdfPCell cell29 = new PdfPCell();
		cell29.setPhrase(new Phrase("Type of connections", font));
		cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell29.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell29);

		PdfPCell cell31 = new PdfPCell(new Paragraph(downLps1.getConnectMadeBrazingOb(), font));
		cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell31);

		if (downLps1.getConnectMadeBrazingRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getConnectMadeBrazingRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell35 = new PdfPCell();
		cell35.setPhrase(new Phrase("5", font));
		cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell35.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell35);

		PdfPCell cell36 = new PdfPCell();
		cell36.setPhrase(new Phrase("Presence of corrosive material near the down conductor", font));
		cell36.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell36.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell36);

		PdfPCell cell37 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerOb(), font));
		cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell37);

		if (downLps1.getChemicalSprinklerRem() != null) {
			PdfPCell cell38 = new PdfPCell(new Paragraph(downLps1.getChemicalSprinklerRem(), font));
			cell38.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell38);
		} else {
			PdfPCell cell39 = new PdfPCell(new Paragraph("Not Available", font));
			cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell39);
		}

		PdfPCell cell38 = new PdfPCell();
		cell38.setPhrase(new Phrase("6", font));
		cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell38.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell38);

		PdfPCell cell39 = new PdfPCell();
		cell39.setPhrase(new Phrase(
				"If wall is a combustible material , distance between down conductor and wall should be > 1m", font));
		cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell39.setBackgroundColor(new GrayColor(0.93f));
		table.addCell(cell39);

		PdfPCell cell40 = new PdfPCell(new Paragraph(downLps1.getCobustMaterialWallOB(), font));
		cell40.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell40);

		if (downLps1.getCobustMaterialWallRem() != null) {
			PdfPCell cell41 = new PdfPCell(new Paragraph(downLps1.getCobustMaterialWallRem(), font));
			cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell41);
		} else {
			PdfPCell cell42 = new PdfPCell(new Paragraph("Not Available", font));
			cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell42);
		}
		return table;
	}

}