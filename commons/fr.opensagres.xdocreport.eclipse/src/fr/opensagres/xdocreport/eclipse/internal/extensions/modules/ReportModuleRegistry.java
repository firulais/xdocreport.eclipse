package fr.opensagres.xdocreport.eclipse.internal.extensions.modules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.internal.Activator;
import fr.opensagres.xdocreport.eclipse.registry.AbstractRegistry;

public class ReportModuleRegistry extends AbstractRegistry implements
		IReportModuleRegistry {

	private static final ReportModuleRegistry INSTANCE = new ReportModuleRegistry();

	private static final String REPORT_MODULES_EXTENSION_POINT = "reportModules";

	private static final String REPORT_MODULE_ELT = "reportModule";

	private static final String ENTRIES_ELT = "entries";
	private static final String ENTRY_ELT = "entry";
	private static final String COMMAND_ID_ATTR = "commandId";

	private static final String REPORT_PROCESSORS_ELT = "reportProcessors";
	private static final String REPORT_PROCESSOR_ELT = "reportProcessor";
	private static final String REPORT_PROCESSOR_ID_ATTR = "reportProcessorId";

	private final List<IReportModule> reportModules;

	public static IReportModuleRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportModuleRegistry() {
		this.reportModules = new ArrayList<IReportModule>();
	}

	public void addReportModule(ReportModule module) {
		reportModules.add(module);
	}

	public List<IReportModule> getReportModules() {
		loadRegistryIfNedded();
		return reportModules;
	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseReportModules(cf);
		} else {
			// TODO : remove references
		}
	}

	private void parseReportModules(IConfigurationElement[] cf) {
		for (IConfigurationElement ce : cf) {
			String id = null;
			String name = null;
			String description = null;
			if (REPORT_MODULE_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				name = ce.getAttribute(NAME_ATTR);
				description = ce.getAttribute(DESCRIPTION_ATTR);

				ReportModule module = new ReportModule(id);
				module.setName(name);
				module.setDescription(description);
				
				// Icon.
				updateIcon(ce, module);
				
				for (IConfigurationElement cech : ce.getChildren()) {
					if (ENTRIES_ELT.equals(cech.getName())) {
						parseReportModuleEntries(cech, module);
					} else if (REPORT_PROCESSORS_ELT.equals(cech.getName())) {
						parseReportProcessors(cech, module);
					}
				}

				reportModules.add(module);
			}
		}
	}

	private void updateIcon(IConfigurationElement ce, ReportBaseModule module) {
		module.setIcon(super.getIconImage(ce));		
	}

	private void parseReportModuleEntries(IConfigurationElement ce,
			ReportModule module) {
		for (IConfigurationElement cech : ce.getChildren()) {
			if (ENTRY_ELT.equals(cech.getName())) {
				module.addEntry(parseReportModuleEntry(module, cech));
			}
		}
	}

	private ReportModuleEntry parseReportModuleEntry(ReportModule module,
			IConfigurationElement cfig) {
		ReportModuleEntry entry;
		entry = new ReportModuleEntry(module);
		String commandId = cfig.getAttribute(COMMAND_ID_ATTR);
		entry.setCommandId(commandId);
		String entryName = cfig.getAttribute(NAME_ATTR);
		entry.setName(entryName);

		// Icon.
		updateIcon(cfig, entry);

		return entry;
	}

	private void parseReportProcessors(IConfigurationElement ce,
			ReportModule module) {
		IReportProcessor processor = null;
		String commandId = ce.getAttribute(COMMAND_ID_ATTR);
		ReportProcessors processors = new ReportProcessors(commandId);
		module.setProcessors(processors);

		for (IConfigurationElement cech : ce.getChildren()) {
			if (REPORT_PROCESSOR_ELT.equals(cech.getName())) {
				String reportProcessorId = cech
						.getAttribute(REPORT_PROCESSOR_ID_ATTR);

				processor = PlatformXDocReport
						.getReportProcessorRegistry().getProcessor(
								reportProcessorId);

				if (processor != null) {
					processors.addProcessor(processor);
				}
			}
		}
	}

	@Override
	protected synchronized void loadRegistry() {
		if (isRegistryIntialized()) {
			return;
		}
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		if (registry != null) {
			IConfigurationElement[] cf = registry.getConfigurationElementsFor(
					getPluginId(), getExtensionPoint());
			parseReportModules(cf);
		}
	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String getExtensionPoint() {
		return REPORT_MODULES_EXTENSION_POINT;
	}
}
