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
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportFormat;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportException;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportMimeMapping;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class XDocReportEngine implements IReportEngine {

	private static final String REPORT_MIME_MAPPING_KEY = "___ReportMimeMapping";

	public void process(IReportProcessor p, Object model,
			ReportConfiguration configuration, OutputStream out)
			throws IOException, ReportException {
		XDocReportProcessor processor = (XDocReportProcessor) p;
		// 1) Get XDoc report
		try {
			IXDocReport report = internalGetReport(processor);
			Options options = getOptionsConverter(configuration, report,
					processor);
			if (options == null) {
				doProcessReport(report, processor, model, out);
			} else {
				doProcessReportWithConverter(report, processor, model, options,
						out);
			}
		} catch (XDocReportException e) {
			throw new ReportException(e);
		}
		// return true;
	}

	/**
	 * 
	 * @param processor
	 * @return
	 * @throws IOException
	 * @throws XDocReportException
	 */
	protected IXDocReport getReport(XDocReportProcessor processor)
			throws IOException, ReportException, XDocReportException {
		XDocReportRegistry registry = getRegistry(processor);
		// 1) Get report id
		String reportId = getReportId(processor);
		if (StringUtils.isNotEmpty(reportId)) {
			// Search if report is cached in the registry
			IXDocReport report = registry.getReport(reportId);
			if (report != null) {
				return report;
			}
		}
		return loadReport(reportId, registry, processor);
	}

	protected String getReportId(XDocReportProcessor processor) {
		return processor.getReportId();
	}

	protected XDocReportRegistry getRegistry(XDocReportProcessor processor) {
		return XDocReportRegistry.getRegistry();
	}

	private IXDocReport loadReport(String reportId,
			XDocReportRegistry registry, XDocReportProcessor processor)
			throws XDocReportException, IOException {
		InputStream sourceStream = getSourceStream(reportId, processor);
		if (sourceStream == null) {
			throw new XDocReportException("Input stream is null with reportId="
					+ reportId);
		}
		IXDocReport report = null;
		// 3) Get template engine to use for the report
		ITemplateEngine templateEngine = null;

		String templateEngineKind = getTemplateEngineKind(reportId, processor);
		if (StringUtils.isNotEmpty(templateEngineKind)) {
			// 3.1) Load report with template engine kind
			report = registry.loadReport(sourceStream, reportId,
					templateEngineKind);
		} else {
			// 3.1) Load report with template engine
			templateEngine = getTemplateEngine(reportId, processor);
			report = registry
					.loadReport(sourceStream, reportId, templateEngine);
		}

		// 6) Set FieldsMetaData
		FieldsMetadata fieldsMetadata = getFieldsMetadata(reportId, processor);
		report.setFieldsMetadata(fieldsMetadata);

		// 7) Set cache
		report.setCacheOriginalDocument(isCacheOriginalDocument(reportId,
				processor));
		return report;
	}

	private boolean isCacheOriginalDocument(String reportId,
			XDocReportProcessor processor) {
		// TODO Auto-generated method stub
		return false;
	}

	private FieldsMetadata getFieldsMetadata(String reportId,
			XDocReportProcessor processor) {
		return processor.getFieldsMetadata();
	}

	private ITemplateEngine getTemplateEngine(String reportId,
			XDocReportProcessor processor) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTemplateEngineKind(String reportId,
			XDocReportProcessor processor) {
		return processor.getTemplateEngineKind();
	}

	private InputStream getSourceStream(String reportId,
			XDocReportProcessor processor) {
		return processor.getSourceStream();
	}

	private void doProcessReport(IXDocReport report,
			XDocReportProcessor processor, Object model, OutputStream out)
			throws XDocReportException, IOException {
		// 1) Prepare Java model context
		IContext context = report.createContext();
		populateContext(context, model, processor);

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
			XDocReportProcessor processor) {
		processor.populateContext(context, model);
	}

	protected Options getOptionsConverter(ReportConfiguration configuration,
			IXDocReport report, XDocReportProcessor processor) {
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

	private boolean isFormatConverter(IXDocReport report, IReportFormat format) {
		if (report.getKind().equals(format.getId())) {
			return false;
		}
		return true;
	}

	public ReportMimeMapping getMimeMapping(IReportProcessor p,
			ReportConfiguration configuration) throws IOException,
			ReportException {
		XDocReportProcessor processor = (XDocReportProcessor) p;
		try {
			String key = getMimeMappingKey(configuration);
			IXDocReport report = internalGetReport(processor);
			ReportMimeMapping mimeMapping = (ReportMimeMapping) report
					.getData(key);
			if (mimeMapping == null) {

				if (isFormatConverter(report, configuration.getFormat())) {
					Options options = getOptionsConverter(configuration,
							report, processor);
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

	private String getMimeMappingKey(ReportConfiguration configuration) {
		return REPORT_MIME_MAPPING_KEY + configuration.getFormat().getId();
	}

	private static ReportMimeMapping toReportMimeMapping(MimeMapping mimeMapping) {
		return new ReportMimeMapping(mimeMapping.getExtension(),
				mimeMapping.getMimeType());
	}

	public boolean canSupportFormat(IReportProcessor p, IReportFormat format)
			throws IOException, ReportException {
		XDocReportProcessor processor = (XDocReportProcessor) p;
		try {
			IXDocReport report = internalGetReport(processor);
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

	private IXDocReport internalGetReport(XDocReportProcessor processor)
			throws IOException, ReportException, XDocReportException {
		IXDocReport report = getReport(processor);
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
			XDocReportProcessor processor, Object model, Options options,
			OutputStream out) throws XDocReportException, IOException,
			ReportException {
		IContext context = null;
		ITemplateEngine templateEngine = report.getTemplateEngine();
		if (templateEngine != null) {
			// 1) Prepare Java model context
			context = report.createContext();
			populateContext(context, model, processor);
		}

		// 2) Get converter
		IConverter converter = report.getConverter(options);
		// 3) Prepare HTTP response content type
		// prepareHTTPResponse(report.getId(), converter.getMimeMapping(),
		// request, response);
		// 4) Generate report with conversion
		report.convert(context, options, out);
	}
}