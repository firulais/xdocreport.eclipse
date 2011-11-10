package fr.opensagres.xdocreport.eclipse.ui.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceHandler;
import org.eclipse.rwt.service.IServiceManager;

import fr.opensagres.xdocreport.eclipse.reporting.core.IReportEngine;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportMimeMapping;

public class GenerateReportServiceHandler implements IServiceHandler {

	// HTTP Header constants
	private static final String SAT_6_MAY_1995_12_00_00_GMT = "Sat, 6 May 1995 12:00:00 GMT";
	private static final String EXPIRES = "Expires";
	private static final String POST_CHECK_0_PRE_CHECK_0 = "post-check=0, pre-check=0";
	private static final String NO_CACHE = "no-cache";
	private static final String PRAGMA = "Pragma";
	private static final String NO_STORE_NO_CACHE_MUST_REVALIDATE = "no-store, no-cache, must-revalidate";
	private static final String CACHE_CONTROL_HTTP_HEADER = "Cache-Control";

	// Content-Disposition HTTP response Header
	private static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
	private static final String ATTACHMENT_FILENAME = "attachment; filename=\"";

	private final IReportLoader reportLoader;
	private final IReportEngine engine;
	private final ReportConfiguration configuration;
	private final Object model;

	public GenerateReportServiceHandler(IReportLoader reportLoader,
			IReportEngine engine, ReportConfiguration options, Object model) {
		this.reportLoader = reportLoader;
		this.engine = engine;
		this.configuration = options;
		this.model = model;
	}

	public void service() throws IOException, ServletException {
		HttpServletResponse response = RWT.getResponse();
		try {
			ReportMimeMapping mimeMapping = engine.getMimeMapping(reportLoader,
					configuration);
			response.setContentType(mimeMapping.getMimeType());

			String fileName = mimeMapping.formatFileName(reportLoader);
			String contentDisposition = getContentDisposition(fileName);
			response.setHeader(CONTENT_DISPOSITION_HEADER, contentDisposition);

			// disableHTTPResponCache(response);

			if (model == null) {
				// model is null, open the report source
				engine.writeReportSource(reportLoader, response.getOutputStream());
			}
			else {
				// model is filled, generate the report and open the generated report
				engine.generateReport(reportLoader, model, configuration,
						response.getOutputStream());
			}
			

		} catch (Throwable e) {
			throw new ServletException(e);
		} finally {
			IServiceManager manager = RWT.getServiceManager();
			manager.unregisterServiceHandler(GenerateReportHandler.REPORT_SERVICE_HANDLER);
		}

	}

	private String getContentDisposition(String fileName) {
		StringBuilder contentDisposition = new StringBuilder(
				ATTACHMENT_FILENAME);
		contentDisposition.append(fileName);
		contentDisposition.append("\"");
		return contentDisposition.toString();
	}

	/**
	 * Disable cache HTTP hearder.
	 * 
	 * @param response
	 */
	protected void disableHTTPResponCache(HttpServletResponse response) {
		// see article http://onjava.com/pub/a/onjava/excerpt/jebp_3/index2.html
		// Set to expire far in the past.
		response.setHeader(EXPIRES, SAT_6_MAY_1995_12_00_00_GMT);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader(CACHE_CONTROL_HTTP_HEADER,
				NO_STORE_NO_CACHE_MUST_REVALIDATE);
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader(CACHE_CONTROL_HTTP_HEADER, POST_CHECK_0_PRE_CHECK_0);
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader(PRAGMA, NO_CACHE);
	}
}
