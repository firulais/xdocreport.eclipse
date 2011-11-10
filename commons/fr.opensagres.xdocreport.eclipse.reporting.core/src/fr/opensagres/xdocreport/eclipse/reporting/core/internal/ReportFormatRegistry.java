package fr.opensagres.xdocreport.eclipse.reporting.core.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.opensagres.xdocreport.eclipse.core.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportFormat;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportFormatRegistry;

public class ReportFormatRegistry extends AbstractRegistry implements
		IReportFormatRegistry {

	private static final IReportFormatRegistry INSTANCE = new ReportFormatRegistry();
	private static final String REPORT_FORMATS_EXTENSION_POINT = "reportFormats";

	private static final String REPORT_FORMAT_ELT = "reportFormat";

	private final Map<String, IReportFormat> formats;

	public static IReportFormatRegistry getRegistry() {
		return INSTANCE;
	}

	private ReportFormatRegistry() {
		this.formats = new HashMap<String, IReportFormat>();
	}

	public IReportFormat getFormat(String id) {
		loadRegistryIfNedded();
		return formats.get(id);
	}

	public Collection<IReportFormat> getFormats() {
		loadRegistryIfNedded();
		return formats.values();
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
			parseReportFormats(cf);
		}

	}

	private void parseReportFormats(IConfigurationElement[] cf) {
		String id = null;
		String name = null;
		String description = null;
		for (IConfigurationElement ce : cf) {
			if (REPORT_FORMAT_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);
				name = ce.getAttribute(NAME_ATTR);
				description = ce.getAttribute(DESCRIPTION_ATTR);

				ReportFormat format = new ReportFormat(id, name);
				format.setDescription(description);
				// Icon.
				format.setImageDescriptor(super.getIconImageDescriptor(ce));

				formats.put(format.getId(), format);
			}
		}

	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseReportFormats(cf);
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
		return REPORT_FORMATS_EXTENSION_POINT;
	}

}
