package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.MimeMapping;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.utils.StringUtils;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.AbstractReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormat;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportException;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportMimeMapping;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class XDocReportEngine extends AbstractReportEngine {

	private final XDocReportRegistry registry;

	public XDocReportEngine() {
		this(XDocReportRegistry.getRegistry());
	}

	public XDocReportEngine(XDocReportRegistry registry) {
		this.registry = registry;
	}

	public void generateReport(IReportLoader loader, Object model,
			ReportConfiguration configuration, OutputStream out)
			throws IOException, ReportException {
		XDocReportLoader reportLoader = (XDocReportLoader) loader;
		// 1) Get XDoc report
		try {
			IXDocReport report = internalGetReport(reportLoader);
			Options options = getOptionsConverter(configuration, report,
					reportLoader);
			if (options == null) {
				doProcessReport(report, reportLoader, model, out);
			} else {
				doProcessReportWithConverter(report, reportLoader, model,
						options, out);
			}
		} catch (XDocReportException e) {
			throw new ReportException(e);
		}
		// return true;
	}

	/**
	 * 
	 * @param reportLoader
	 * @return
	 * @throws IOException
	 * @throws XDocReportException
	 */
	protected IXDocReport getReport(XDocReportLoader reportLoader)
			throws IOException, ReportException, XDocReportException {
		XDocReportRegistry registry = getRegistry(reportLoader);
		// 1) Get report id
		String reportId = getReportId(reportLoader);
		if (StringUtils.isNotEmpty(reportId)) {
			// Search if report is cached in the registry
			IXDocReport report = registry.getReport(reportId);
			if (report != null) {
				return report;
			}
		}
		return loadReport(reportId, registry, reportLoader);
	}

	protected String getReportId(XDocReportLoader reportLoader) {
		return reportLoader.getReportId();
	}

	protected XDocReportRegistry getRegistry(XDocReportLoader reportLoader) {
		return registry;
	}

	private IXDocReport loadReport(String reportId,
			XDocReportRegistry registry, XDocReportLoader reportLoader)
			throws XDocReportException, IOException, ReportException {
		InputStream sourceStream = getSourceStream(reportId, reportLoader);
		if (sourceStream == null) {
			throw new XDocReportException("Input stream is null with reportId="
					+ reportId);
		}
		IXDocReport report = null;
		// 3) Get template engine to use for the report
		ITemplateEngine templateEngine = null;

		String templateEngineKind = getTemplateEngineKind(reportId,
				reportLoader);
		if (StringUtils.isNotEmpty(templateEngineKind)) {
			// 3.1) Load report with template engine kind
			report = registry.loadReport(sourceStream, reportId,
					templateEngineKind);
		} else {
			// 3.1) Load report with template engine
			templateEngine = getTemplateEngine(reportId, reportLoader);
			report = registry
					.loadReport(sourceStream, reportId, templateEngine);
		}

		// 6) Set FieldsMetaData
		FieldsMetadata fieldsMetadata = getFieldsMetadata(reportId,
				reportLoader);
		report.setFieldsMetadata(fieldsMetadata);

		// 7) Set cache
		report.setCacheOriginalDocument(isCacheOriginalDocument(reportId,
				reportLoader));
		return report;
	}

	private boolean isCacheOriginalDocument(String reportId,
			XDocReportLoader reportLoader) {
		// TODO Auto-generated method stub
		return false;
	}

	private FieldsMetadata getFieldsMetadata(String reportId,
			XDocReportLoader reportLoader) {
		return reportLoader.getFieldsMetadata();
	}

	private ITemplateEngine getTemplateEngine(String reportId,
			XDocReportLoader reportLoader) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTemplateEngineKind(String reportId,
			XDocReportLoader reportLoader) {
		return reportLoader.getTemplateEngineKind();
	}

	private InputStream getSourceStream(String reportId,
			XDocReportLoader reportLoader) throws IOException, ReportException {
		return reportLoader.getSourceStream();
	}

	private void doProcessReport(IXDocReport report,
			XDocReportLoader reportLoader, Object model, OutputStream out)
			throws XDocReportException, IOException {
		// 1) Prepare Java model context
		IContext context = report.createContext();
		populateContext(context, model, reportLoader);

		// if (StringUtils.isEmpty(entryName)) {
		// 2) Prepare HTTP response content type
		// prepareHTTPResponse(report.getId(), report.getMimeMapping(),
		// request, response);
		// 3) Generate report
		report.process(context, out);
		// } else {
		// // 2) Prepare HTTP response content type
		// prepareHTTPResponse(report.getId(), entryName, request, response);
		// // 3) Generate report
		// report.process(context, entryName, response.getOutputStream());
		// }
	}

	private void populateContext(IContext context, Object model,
			XDocReportLoader reportLoader) {
		IReportProcessor processor = reportLoader.getProcessor();
		((XDocReportProcessor) processor).populateContext(context, model);
	}

	protected Options getOptionsConverter(ReportConfiguration configuration,
			IXDocReport report, XDocReportLoader reportLoader) {
		if (configuration == null) {
			return null;
		}
		IReportFormat format = configuration.getFormat();
		if (format == null) {
			return null;
		}
		if (!isFormatConverter(report, format)) {
			return null;
		}
		return Options.getFrom(report.getKind()).to(format.getId());
	}

	private boolean isFormatConverter(IXDocReport report,
			ReportConfiguration configuration) {
		if (configuration == null) {
			return false;
		}
		return isFormatConverter(report, configuration.getFormat());
	}

	private boolean isFormatConverter(IXDocReport report, IReportFormat format) {
		if (format == null) {
			return false;
		}
		if (report.getKind().equals(format.getId())) {
			return false;
		}
		return true;
	}

	public ReportMimeMapping getMimeMapping(IReportLoader loader,
			ReportConfiguration configuration) throws IOException,
			ReportException {
		XDocReportLoader reportLoader = (XDocReportLoader) loader;
		try {
			String key = getMimeMappingKey(configuration);
			IXDocReport report = internalGetReport(reportLoader);
			ReportMimeMapping mimeMapping = (ReportMimeMapping) report
					.getData(key);
			if (mimeMapping == null) {

				if (isFormatConverter(report, configuration)) {
					Options options = getOptionsConverter(configuration,
							report, reportLoader);
					if (options == null) {
						return null;
					}
					IConverter converter = report.getConverter(options);
					if (converter == null) {
						return null;
					}
					mimeMapping = toReportMimeMapping(converter
							.getMimeMapping());
				} else {
					mimeMapping = toReportMimeMapping(report.getMimeMapping());
				}
				report.setData(key, mimeMapping);
			}
			return mimeMapping;
		} catch (XDocReportException e) {
			throw new ReportException(e);
		}
	}

	private static ReportMimeMapping toReportMimeMapping(MimeMapping mimeMapping) {
		return new ReportMimeMapping(mimeMapping.getExtension(),
				mimeMapping.getMimeType());
	}

	public boolean canSupportFormat(IReportLoader loader, IReportFormat format)
			throws IOException, ReportException {
		XDocReportLoader reportLoader = (XDocReportLoader) loader;
		try {
			IXDocReport report = internalGetReport(reportLoader);
			if (format.getId().equalsIgnoreCase(report.getKind())) {
				// ex : docx, odt, etc
				return true;
			}
			// test converter
			return ConverterRegistry.getRegistry().getConverter(
					report.getKind(), format.getId(), null) != null;
		} catch (XDocReportException e) {
			throw new ReportException(e);
		}
	}

	private IXDocReport internalGetReport(XDocReportLoader reportLoader)
			throws IOException, ReportException, XDocReportException {
		IXDocReport report = getReport(reportLoader);
		if (report == null) {
			throw new ReportException("Cannot get XDoc Report");
		}
		return report;
	}

	/**
	 * Generate report with conversion.
	 * 
	 * @param out
	 * @param model2
	 * 
	 */
	private void doProcessReportWithConverter(IXDocReport report,
			XDocReportLoader reportLoader, Object model, Options options,
			OutputStream out) throws XDocReportException, IOException,
			ReportException {
		IContext context = null;
		ITemplateEngine templateEngine = report.getTemplateEngine();
		if (templateEngine != null) {
			// 1) Prepare Java model context
			context = report.createContext();
			populateContext(context, model, reportLoader);
		}

		// 2) Get converter
		IConverter converter = report.getConverter(options);
		// 3) Prepare HTTP response content type
		// prepareHTTPResponse(report.getId(), converter.getMimeMapping(),
		// request, response);
		// 4) Generate report with conversion
		report.convert(context, options, out);
	}

	public void unloadReport(IReportLoader reportLoader) {
		getRegistry((XDocReportLoader) reportLoader).unregisterReport(
				reportLoader.getReportId());
	}
}