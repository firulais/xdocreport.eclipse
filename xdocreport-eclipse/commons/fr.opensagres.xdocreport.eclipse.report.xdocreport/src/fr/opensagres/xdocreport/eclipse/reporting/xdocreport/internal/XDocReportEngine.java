package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.opensagres.xdocreport.converter.MimeMapping;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.utils.StringUtils;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportException;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportFormat;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.ReportMimeMapping;
import fr.opensagres.xdocreport.eclipse.reporting.xdocreport.XDocReportProcessor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.ITemplateEngine;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class XDocReportEngine implements IReportEngine {

	private static final String REPORT_MIME_MAPPING_KEY = "___ReportMimeMapping";

	public ReportMimeMapping getMimeMapping(IReportProcessor p,
			ReportConfiguration options) throws IOException, ReportException {
		XDocReportProcessor processor = (XDocReportProcessor) p;
		try {
			IXDocReport report = getReport(processor);
			if (report == null) {
				throw new ReportException("Cannot get XDoc Report");
			}
			ReportMimeMapping mimeMapping = (ReportMimeMapping) report
					.getData(REPORT_MIME_MAPPING_KEY);
			if (mimeMapping == null) {
				mimeMapping = toReportMimeMapping(report.getMimeMapping());
				report.setData(REPORT_MIME_MAPPING_KEY, mimeMapping);
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

	public void process(IReportProcessor p, Object model,
			ReportConfiguration o, OutputStream out) throws IOException,
			ReportException {
		XDocReportProcessor processor = (XDocReportProcessor) p;
		// 1) Get XDoc report
		try {
			IXDocReport report = getReport(processor);
			if (report == null) {
				throw new ReportException("Cannot get XDoc Report");
			}
			Options options = getOptionsConverter(report, processor);
			if (options == null) {

				doProcessReport(report, processor, model, out);

			} else {
				// doProcessReportWithConverter(report, options, processor,
				// model,
				// out);
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

	protected Options getOptionsConverter(IXDocReport report,
			XDocReportProcessor processort) {
		final String converterId = getConverterId(report, processort);
		if (StringUtils.isEmpty(converterId)) {
			return null;
		}
		Options options = null;
		int index = converterId.lastIndexOf('_');
		if (index != -1) {
			String to = converterId.substring(0, index);
			String via = converterId.substring(index + 1, converterId.length());
			options = Options.getTo(to).via(via);
		} else {
			options = Options.getTo(converterId);
		}
		prepareOptions(options, report, converterId, processort);
		return options;
	}

	private void prepareOptions(Options options, IXDocReport report,
			String converterId, XDocReportProcessor processort) {
		// TODO Auto-generated method stub

	}

	private String getConverterId(IXDocReport report,
			XDocReportProcessor processort) {
		return null;
	}

	public List<ReportFormat> getSupportedFormat() {
		// TODO : compute the supported format
		List<ReportFormat> formats = new ArrayList<ReportFormat>();
		formats.add(ReportFormat.DOCX);
		return formats;
	}

}