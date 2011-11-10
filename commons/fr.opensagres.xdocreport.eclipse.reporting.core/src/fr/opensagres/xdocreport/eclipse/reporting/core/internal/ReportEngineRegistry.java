package fr.opensagres.xdocreport.eclipse.reporting.core.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.xdocreport.eclipse.core.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.AbstractReportEngine;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportEngine;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportEngineRegistry;

public class ReportEngineRegistry extends AbstractRegistry implements
		IReportEngineRegistry {

	private static final IReportEngineRegistry INSTANCE = new ReportEngineRegistry();
	private static final String REPORT_ENGINES_EXTENSION_POINT = "reportEngines";

	private static final String REPORT_ENGINE_ELT = "reportEngine";

	private final Map<String, IReportEngine> engines;

	public static IReportEngineRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportEngineRegistry() {
		this.engines = new HashMap<String, IReportEngine>();
	}

	public IReportEngine getEngine(String id) {
		loadRegistryIfNedded();
		return engines.get(id);
	}
	
	public Collection<IReportEngine> getEngines() {
		loadRegistryIfNedded();
		return engines.values();
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
			parseReportEngines(cf);
		}

	}

	private void parseReportEngines(IConfigurationElement[] cf) {
		String id = null;
		String name = null;
		String description = null;
		for (IConfigurationElement ce : cf) {
			if (REPORT_ENGINE_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				name = ce.getAttribute(NAME_ATTR);
				description = ce.getAttribute(DESCRIPTION_ATTR);

				AbstractReportEngine engine = null;
				try {
					engine = (AbstractReportEngine) ce
							.createExecutableExtension(CLASS_ATTR);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				engine.setId(id);
				engine.setName(name);
				engine.setDescription(description);

				engines.put(engine.getId(), engine);
			}
		}

	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseReportEngines(cf);
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
		return REPORT_ENGINES_EXTENSION_POINT;
	}

}
