package fr.opensagres.xdocreport.eclipse.reporting.core;

import java.io.File;

public class ReportConfiguration {

	public static ReportConfiguration DEFAULT = new ReportConfiguration(null);
	private final File tempBaseDir;
	private final IReportFormat format;

	public ReportConfiguration(File tempBaseDir, IReportFormat format) {
		this.tempBaseDir = tempBaseDir;
		this.format = format;
	}

	public ReportConfiguration(IReportFormat format) {
		this(new File(getDefaultTempBaseDir()), format);
	}

	private static String getDefaultTempBaseDir() {
		String tempdir = System.getProperty("java.io.tmpdir");
		if (!(tempdir.endsWith("/") || tempdir.endsWith("\\")))
			tempdir = tempdir + System.getProperty("file.separator");
		if (tempdir != null) {
			return tempdir;
		}
		return "C:/xdocreport";
	}

	public File getTempBaseDir() {
		return tempBaseDir;
	}

	public IReportFormat getFormat() {
		return format;
	}

}
