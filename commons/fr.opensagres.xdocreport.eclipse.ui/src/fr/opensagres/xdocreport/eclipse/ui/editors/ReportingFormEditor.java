package fr.opensagres.xdocreport.eclipse.ui.editors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import fr.opensagres.eclipse.forms.editor.IModelProvider;
import fr.opensagres.eclipse.forms.editor.ModelFormEditor;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntryProvider;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportFormat;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportLoader;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.reporting.core.IReportProcessors;
import fr.opensagres.xdocreport.eclipse.reporting.core.ReportException;
import fr.opensagres.xdocreport.eclipse.ui.actions.ActionMenu;
import fr.opensagres.xdocreport.eclipse.ui.actions.GenerationReportAction;

public abstract class ReportingFormEditor<Model> extends ModelFormEditor<ModelAndEntryEditorInput<Model>, Model> implements
		IReportModuleEntryProvider, IModelProvider<Model> {

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof ModelAndEntryEditorInput<?>)) {
			throw new PartInitException("UserInput");
		}
		super.init(site, input);
	}

	public Model getModel() {
		return getModelObject();
	}

	public IReportModuleEntry getEntry() {
		return ((ModelAndEntryEditorInput<?>) getEditorInput()).getEntry();
	}

	public IReportModule getModule() {
		IReportModuleEntry entry = getEntry();
		if (entry == null) {
			return null;
		}
		return entry.getModule();
	}

	public IReportProcessors getReportProcessors() {
		IReportModule module = getModule();
		if (module == null) {
			return null;
		}
		return module.getProcessors();
	}

	public void contributeToToolbar(IToolBarManager manager) {
		contributeReportProcessorsToToolbar(manager);
	}

	protected void contributeReportProcessorsToToolbar(IToolBarManager manager) {

		IReportProcessors processors = getReportProcessors();
		if (processors == null || processors.getProcessors().size() < 1) {
			return;
		}

		Map<IReportFormat, List<IReportProcessor>> supportedFormats = processors
				.getSupportedFormats();
		for (Map.Entry<IReportFormat, List<IReportProcessor>> supportedFormat : supportedFormats
				.entrySet()) {
			Action runAction = new ActionMenu(getReportProcessorsAction(
					supportedFormat.getValue(), supportedFormat.getKey()));
			manager.add(runAction);
		}
	}

	private List<Action> getReportProcessorsAction(
			List<IReportProcessor> processors, IReportFormat format) {
		List<Action> actions = new ArrayList<Action>();
		for (IReportProcessor processor : processors) {
			List<IReportLoader> reportLoaders = processor.getReportLoaders();
			for (IReportLoader reportLoader : reportLoaders) {
				
				try {
					if (reportLoader.canSupportFormat(format)) {
						actions.add(new GenerationReportAction(this, this, reportLoader,
								format, this.getSite()));	
					}
				} catch (IOException e) {					
					e.printStackTrace();
				} catch (ReportException e) {
					e.printStackTrace();
				}
			}			
		}
		return actions;
	}
}
