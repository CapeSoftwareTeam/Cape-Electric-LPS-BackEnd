package com.capeelectric.service;

public interface PrintFinalPDFService {

	public void printFinalPDF(String userName, Integer lpsId, String projectName) throws Exception;
	public void printSummaryPDF(String userName, Integer lpsId, String projectName) throws Exception;

}
