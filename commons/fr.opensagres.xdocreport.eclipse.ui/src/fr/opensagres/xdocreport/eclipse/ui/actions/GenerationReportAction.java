package fr.opensagres.xdocreport.eclipse.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.services.IServiceLocator;

import fr.opensagres.eclipse.forms.editor.IModelProvider;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntryProvider;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportFormat;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportConfiguration;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerEvent;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerUtils;

public class GenerationReportAction extends Action {

	private final IReportModuleEntryProvider entryProvider;
	private final IModelProvider<?> modelProvider;
	private final IServiceLocator serviceLocator;
	private final ReportConfiguration options;
	private final IReportLoader reportLoader;

	public GenerationReportAction(IReportModuleEntryProvider entryProvider,
			IModelProvider<?> modelProvider, IReportLoader reportLoader,
			IReportFormat format, IServiceLocator serviceLocator) {
		ImageDescriptor descriptor = format.getImageDescriptor();
		if (descriptor != null) {
			super.setImageDescriptor(descriptor);
		}
		super.setText(reportLoader.getReportId());
		this.entryProvider = entryProvider;
		this.modelProvider = modelProvider;
		this.serviceLocator = serviceLocator;
		// TODO customize options
		this.options = new ReportConfiguration(format);
		this.reportLoader = reportLoader;
	}

	@Override
	public void run() {
		IReportModuleEntry entry = entryProvider.getEntry();
		Object model = modelProvider.getModel();
		IReportModule module = entry.getModule();
		String commandId = module.getProcessors().getCommandId();

		try {
			Event e = new ContextHandlerEvent(entry, model, reportLoader,
					options);
			ContextHandlerUtils.executeCommand(commandId, serviceLocator, e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
