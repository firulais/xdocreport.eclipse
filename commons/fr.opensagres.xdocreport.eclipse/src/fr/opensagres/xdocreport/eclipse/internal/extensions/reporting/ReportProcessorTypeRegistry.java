package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.AbstractReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorType;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessorTypeRegistry;
import fr.opensagres.xdocreport.eclipse.internal.Activator;
import fr.opensagres.xdocreport.eclipse.registry.AbstractRegistry;

public class ReportProcessorTypeRegistry extends AbstractRegistry implements
		IReportProcessorTypeRegistry {

	private static final IReportProcessorTypeRegistry INSTANCE = new ReportProcessorTypeRegistry();
	private static final String REPORT_PROCESSORS_EXTENSION_POINT = "reportProcessors";

	private static final String REPORT_PROCESSOR_ELT = "reportProcessor";
	private static final String REPORT_LOADER_ELT = "reportLoader";

	private static final String REPORT_ENGINE_ID_ATTR = "reportEngineId";

	private final Map<String, IReportProcessorType> processorTypes;

	public static IReportProcessorTypeRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportProcessorTypeRegistry() {
		this.processorTypes = new HashMap<String, IReportProcessorType>();
	}

	public IReportProcessorType getProcessorType(String id) {
		loadRegistryIfNedded();
		return processorTypes.get(id);
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
				IReportEngine engine = PlatformXDocReport
						.getReportEngineRegistry().getEngine(reportEngineId);

				// Processor
				IReportProcessor processor = null;
				try {
					processor = (IReportProcessor) ce
							.createExecutableExtension(CLASS_ATTR);
				} catch (CoreException e) {
					e.printStackTrace();

				}


				ReportProcessorType processorType = new ReportProcessorType(id,
						processor, engine);
				processorType.setName(name);
				processorType.setDescription(description);
				// List of reportLoader.
				List<IReportLoader> reportLoaders = parseReportLoaders(processorType, ce);

				processorType.setReportLoaders(reportLoaders);

				processorTypes.put(processorType.getId(), processorType);
			}
		}

	}

	private List<IReportLoader> parseReportLoaders(ReportProcessorType processorType, IConfigurationElement cf) {
		List<IReportLoader> reportLoaders = new ArrayList<IReportLoader>();
		for (IConfigurationElement ce : cf.getChildren()) {
			if (REPORT_LOADER_ELT.equals(ce.getName())) {
				AbstractReportLoader reportLoader = null;
				try {
					reportLoader = (AbstractReportLoader) ce
							.createExecutableExtension(CLASS_ATTR);
					reportLoader.setProcessorType(processorType);
					reportLoader.setReportId(ce.getAttribute(ID_ATTR));					
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
