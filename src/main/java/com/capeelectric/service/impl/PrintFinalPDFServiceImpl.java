package com.capeelectric.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.util.HeaderFooterPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PrintFinalPDFServiceImpl implements PrintFinalPDFService {

	@Autowired
	private AWSS3ServiceImpl awsS3ServiceImpl;

	@Value("${s3.bucket.name}")
	private String s3BucketName;

	@Value("${access.key.id}")
	private String accessKeyId;

	@Value("${access.key.secret}")
	private String accessKeySecret;

	private static final Logger logger = LoggerFactory.getLogger(PrintFinalPDFServiceImpl.class);

	@Override
	public void printFinalPDF(String userName, Integer lpsId, String projectName) throws Exception {

		if (userName != null && !userName.isEmpty() && lpsId != null && lpsId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);
		
			try {

				List<InputStream> inputPdfList = new ArrayList<InputStream>();
				
				inputPdfList.add(new FileInputStream("BasicLps.pdf"));
				inputPdfList.add(new FileInputStream("AirTermination.pdf"));
				inputPdfList.add(new FileInputStream("DownConductorLps.pdf"));
				inputPdfList.add(new FileInputStream("EarthingLps.pdf"));
				inputPdfList.add(new FileInputStream("SPD.pdf"));
				inputPdfList.add(new FileInputStream("SDandEarthStud.pdf"));

				
				OutputStream outputStream = new FileOutputStream(projectName+".pdf");
				mergePdfFiles(inputPdfList, outputStream, awsS3ServiceImpl);
				
				try {
					
//					Create a S3 client with in-program credential
					BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, accessKeySecret);
					AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
							.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

//					Uploading the PDF File in AWS S3 Bucket with folderName + fileNameInS3

					if (projectName.length() > 0) {
						PutObjectRequest request = new PutObjectRequest(s3BucketName,
								"LPS_Project Name_".concat(projectName) + "/" + (projectName+".pdf"), new File(projectName+".pdf"));
						s3Client.putObject(request);
						
						logger.info("Uploading file done in AWS s3 ");
					} else {
						logger.error("There is no basic details available");
						throw new Exception("There is no basic details available");
					}

				} catch (AmazonS3Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {

			}
		} else {
			throw new Exception("Invalid Inputs");
		}
	}

	private static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream,
			AWSS3ServiceImpl awsS3ServiceImpl) throws Exception {
		Document document = new Document();
		List<PdfReader> readers = new ArrayList<PdfReader>();
		int totalPages = 0;
		Iterator<InputStream> pdfIterator = inputPdfList.iterator();
		while (pdfIterator.hasNext()) {
			InputStream pdf = pdfIterator.next();
			PdfReader pdfReader = new PdfReader(pdf);
			readers.add(pdfReader);
			totalPages = totalPages + pdfReader.getNumberOfPages();
		}
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		Image image = Image.getInstance(awsS3ServiceImpl.findByName("Original1.png"));
		image.scaleToFit(145, 155);
		image.setAbsolutePosition(15, -41);
		
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent((PdfPageEvent) event);
		document.open();
		PdfContentByte pageContentByte = writer.getDirectContent();
		PdfImportedPage pdfImportedPage;
		int currentPdfReaderPage = 1;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
				document.newPage();
				document.add(image);
				pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
				pageContentByte.addTemplate(pdfImportedPage, 0, 0);
				currentPdfReaderPage++;
			}
			currentPdfReaderPage = 1;
		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}
}