package fr.opensagres.xdocreport.eclipse.ui.handlers;

import java.net.URL;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.service.IServiceHandler;
import org.eclipse.rwt.service.IServiceManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.utils.URLHelper;

public class GenerateReportHandler extends AbstractGenerateReportHandler {

	public static final String REPORT_SERVICE_HANDLER = "generateReportServiceHandler";

	@Override
	protected void generateReport(ExecutionEvent event,
			ContextHandlerEvent contextEvent, IReportLoader reportLoader,
			IReportEngine engine, ReportConfiguration options, Object model)
			throws ExecutionException {

		IServiceManager manager = RWT.getServiceManager();
		IServiceHandler handler = new GenerateReportServiceHandler(
				reportLoader, engine, options, model);
		manager.registerServiceHandler(REPORT_SERVICE_HANDLER, handler);

		IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench()
				.getBrowserSupport();
		try {
			IWebBrowser browser = browserSupport.createBrowser(
					IWorkbenchBrowserSupport.AS_EDITOR, "My", "Name",
					"My tooltip");

			browser.openURL(new URL(createDownloadUrl()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String createDownloadUrl() {
		StringBuilder url = new StringBuilder();
		url.append(URLHelper.getContextURLString());
		url.append(RWT.getRequest().getServletPath());
		url.append("?");
		url.append(IServiceHandler.REQUEST_PARAM);
		url.append("=");
		url.append(REPORT_SERVICE_HANDLER);
		url.append("&filename=");
		url.append(System.currentTimeMillis());
		String encodedURL = RWT.getResponse().encodeURL(url.toString());
		return encodedURL;
	}

}
