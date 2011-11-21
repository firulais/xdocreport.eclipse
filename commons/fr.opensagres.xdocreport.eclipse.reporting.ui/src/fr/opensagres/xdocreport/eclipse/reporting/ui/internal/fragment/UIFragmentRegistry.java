package fr.opensagres.xdocreport.eclipse.reporting.ui.internal.fragment;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.forms.IDetailsPage;

import fr.opensagres.eclipse.forms.registry.AbstractRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.ui.fragment.IUIFragmentRegistry;
import fr.opensagres.xdocreport.eclipse.reporting.ui.internal.Activator;

public class UIFragmentRegistry extends AbstractRegistry implements
		IUIFragmentRegistry {

	private static final IUIFragmentRegistry INSTANCE = new UIFragmentRegistry();
	private static final String UI_FRAGMENTS_EXTENSION_POINT = "uiFragments";

	private static final String FRAGMENT_ELT = "fragment";
	private static final String DETAILSPAGE_ELT = "detailsPage";

	private final Map<String, Fragment> fragments;

	public static IUIFragmentRegistry getRegistry() {
		return INSTANCE;
	}

	private UIFragmentRegistry() {
		this.fragments = new HashMap<String, Fragment>();
	}

	public IDetailsPage createDetailsPage(String fragmentId)
			throws CoreException {
		Fragment fragment = getFragment(fragmentId);
		if (fragment != null) {
			return fragment.createDetailsPage();
		}
		return null;
	}

	private Fragment getFragment(String fragmentId) {
		loadRegistryIfNedded();
		return fragments.get(fragmentId);
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
			parseUIFragments(cf);
		}

	}

	private void parseUIFragments(IConfigurationElement[] cf) {
		String id = null;
		for (IConfigurationElement ce : cf) {
			if (FRAGMENT_ELT.equals(ce.getName())) {
				id = ce.getAttribute(ID_ATTR);

				Fragment fragment = new Fragment(id);
				parseFragmentBody(fragment, ce.getChildren());

				fragments.put(fragment.getId(), fragment);
			}
		}

	}

	private void parseFragmentBody(Fragment fragment,
			IConfigurationElement[] children) {
		for (IConfigurationElement ce : children) {
			if (DETAILSPAGE_ELT.equals(ce.getName())) {
				fragment.setDetailsPageElement(ce);
			}
		}

	}

	@Override
	protected void handleExtensionDelta(IExtensionDelta delta) {
		if (delta.getKind() == IExtensionDelta.ADDED) {
			IConfigurationElement[] cf = delta.getExtension()
					.getConfigurationElements();
			parseUIFragments(cf);
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
		return UI_FRAGMENTS_EXTENSION_POINT;
	}

}
