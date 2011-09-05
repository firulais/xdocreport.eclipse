package fr.opensagres.xdocreport.eclipse.internal.extensions.reporting;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineRegistry;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngineType;
import fr.opensagres.xdocreport.eclipse.internal.Activator;
import fr.opensagres.xdocreport.eclipse.registry.AbstractRegistry;

public class ReportEngineTypeRegistry extends AbstractRegistry implements
		IReportEngineRegistry {

	private static final IReportEngineRegistry INSTANCE = new ReportEngineTypeRegistry();
	private static final String REPORT_ENGINES_EXTENSION_POINT = "reportEngines";

	private static final String REPORT_ENGINE_ELT = "reportEngine";

	private final Map<String, IReportEngineType> engineTypes;

	public static IReportEngineRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportEngineTypeRegistry() {
		this.engineTypes = new HashMap<String, IReportEngineType>();
	}

	public IReportEngine getEngine(String id) {
		IReportEngineType engineType = getEngineType(id);
		if (engineType != null) {
			return engineType.getEngine();
		}
		return null;
	}

	public IReportEngineType getEngineType(String id) {
		loadRegistryIfNedded();
		return engineTypes.get(id);
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

				IReportEngine engine = null;
				try {
					engine = (IReportEngine) ce
							.createExecutableExtension(CLASS_ATTR);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				ReportEngineType engineType = new ReportEngineType(id, engine);
				engineType.setName(name);
				engineType.setDescription(description);

				engineTypes.put(engineType.getId(), engineType);
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
