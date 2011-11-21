package fr.opensagres.xdocreport.eclipse.reporting.core.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.eclipse.forms.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.AbstractReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.AbstractReportProcessor;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportEngine;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportProcessorRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.PlatformCoreReporting;

public class ReportProcessorRegistry extends AbstractRegistry implements
		IReportProcessorRegistry {

	private static final IReportProcessorRegistry INSTANCE = new ReportProcessorRegistry();
	private static final String REPORT_PROCESSORS_EXTENSION_POINT = "reportProcessors";

	private static final String REPORT_PROCESSOR_ELT = "reportProcessor";
	private static final String REPORT_LOADER_ELT = "reportLoader";

	private static final String REPORT_ENGINE_ID_ATTR = "reportEngineId";

	private final Map<String, IReportProcessor> processors;

	public static IReportProcessorRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportProcessorRegistry() {
		this.processors = new HashMap<String, IReportProcessor>();
	}

	public IReportProcessor getProcessor(String id) {
		loadRegistryIfNedded();
		return processors.get(id);
	}

	@Override
	protected void loadRegistry() {
		if (isRegistryIntialized()) {
			return;
		}
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IConfigurationElement[] cf = registry.getConfigurationElementsFor(
					getPluginId(), getExtensionPoint());
			parseReportProcessors(cf);
		}

	}

	private void parseReportProcessors(IConfigurationElement[] cf) {
		String id = null;
		String name = null;
		String description = null;
		String reportEngineId = null;
		for (IConfigurationElement ce : cf) {
			if (REPORT_PROCESSOR_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				name = ce.getAttribute(NAME_ATTR);
				description = ce.getAttribute(DESCRIPTION_ATTR);

				// Engine
				reportEngineId = ce.getAttribute(REPORT_ENGINE_ID_ATTR);
				IReportEngine engine = PlatformCoreReporting
						.getReportEngineRegistry().getEngine(reportEngineId);

				// Processor
				AbstractReportProcessor processor = null;
				try {
					processor = (AbstractReportProcessor) ce
							.createExecutableExtension(CLASS_ATTR);
				} catch (CoreException e) {
					e.printStackTrace();

				}

				processor.setId(id);
				processor.setName(name);
				processor.setDescription(description);
				processor.setEngine(engine);
				// List of reportLoader.
				List<IReportLoader> reportLoaders = parseReportLoaders(
						processor, ce);

				processor.setReportLoaders(reportLoaders);

				processors.put(processor.getId(), processor);
			}
		}

	}

	private List<IReportLoader> parseReportLoaders(IReportProcessor processor,
			IConfigurationElement cf) {
		List<IReportLoader> reportLoaders = new ArrayList<IReportLoader>();
		for (IConfigurationElement ce : cf.getChildren()) {
			if (REPORT_LOADER_ELT.equals(ce.getName())) {
				AbstractReportLoader reportLoader = null;
				try {
					reportLoader = (AbstractReportLoader) ce
							.createExecutableExtension(CLASS_ATTR);
					reportLoader.setProcessor(processor);
					reportLoader.setReportId(ce.getAttribute(ID_ATTR));
					reportLoader.setName(ce.getAttribute(NAME_ATTR));
					reportLoader.setDescription(ce
							.getAttribute(DESCRIPTION_ATTR));
					reportLoaders.add(reportLoader);
				} catch (CoreException e) {
					e.printStackTrace();

				}
			}
		}
		return reportLoaders;
	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseReportProcessors(cf);
		} else {
			// TODO : remove references
		}

	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String getExtensionPoint() {
		return REPORT_PROCESSORS_EXTENSION_POINT;
	}

}
