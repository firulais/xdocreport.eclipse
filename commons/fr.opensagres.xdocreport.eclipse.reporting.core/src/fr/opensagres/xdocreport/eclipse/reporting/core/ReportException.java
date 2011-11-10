package fr.opensagres.xdocreport.eclipse.reporting.core;

public class ReportException extends Exception {

	public ReportException(String message) {
		super(message);
	}
	
	public ReportException(Throwable t) {
		super(t);
	}
}
