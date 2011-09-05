package fr.opensagres.xdocreport.eclipse.extensions.reporting;

import java.io.File;

public class ReportConfiguration {

	private final File tempBaseDir;
	private final ReportFormat format;

	public ReportConfiguration(File tempBaseDir, ReportFormat format) {
		this.tempBaseDir = tempBaseDir;
		this.format = format;
	}

	public ReportConfiguration(ReportFormat format) {
		this(new File(getDefaultTempBaseDir()), format);
	}

	private static String getDefaultTempBaseDir() {
		String temp = System.getenv("TEMP");
		if (temp != null) {
			return temp;
		}
		return "C:/xdocreport";
	}

	public File getTempBaseDir() {
		return tempBaseDir;
	}

	public ReportFormat getFormat() {
		return format;
	}

}
