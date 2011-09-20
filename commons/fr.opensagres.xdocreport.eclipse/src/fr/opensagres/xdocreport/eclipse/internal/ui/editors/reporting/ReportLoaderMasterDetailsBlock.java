package fr.opensagres.xdocreport.eclipse.internal.ui.editors.reporting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportEngine;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportProcessor;
import fr.opensagres.xdocreport.eclipse.internal.ImageResources;
import fr.opensagres.xdocreport.eclipse.internal.Messages;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportLoaderContentProvider;
import fr.opensagres.xdocreport.eclipse.internal.ui.viewers.ReportLoaderLabelProvider;
import fr.opensagres.xdocreport.eclipse.ui.editors.reporting.GenericReportLoaderDetailsPage;

/**
 *
 */
public class ReportLoaderMasterDetailsBlock extends MasterDetailsBlock
		implements IDetailsPageProvider {
	private FormPage page;
	private GenericReportLoaderDetailsPage typeOneDetailsPage;

	public ReportLoaderMasterDetailsBlock(FormPage page) {
		this.page = page;
	}

	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		// final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText(Messages.ReportLoaderMasterDetailsBlock_title); //$NON-NLS-1$
		section.setDescription(Messages.ReportLoaderMasterDetailsBlock_desc); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		// Engine
		// Label processorLabel = new Label(client, SWT.NONE);
		// processorLabel.setText("Processor:");
		// processorLabel.setLayoutData(new GridData(SWT.FILL));

		// Combo processorCombo = new Combo(client, SWT.READ_ONLY);
		// processorCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// final ComboViewer processorViewer = new ComboViewer(processorCombo);
		// processorViewer.setContentProvider(ReportProcessorTypeContentProvider
		// .getInstance());
		// processorViewer.setLabelProvider(ReportProcessorTypeLabelProvider
		// .getInstance());
		// processorViewer.setInput(processorTypes);
		// if (processorTypes.size() > 0) {
		// processorCombo.select(0);
		// }

		Table t = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		t.setLayoutData(gd);
		// toolkit.paintBordersFor(client);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);

		Button b = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		section.setClient(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		TableViewer viewer = new TableViewer(t);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
		viewer.setContentProvider(ReportLoaderContentProvider.getInstance());
		viewer.setLabelProvider(ReportLoaderLabelProvider.getInstance());

		viewer.setInput(getAllReportLoaders());

		// refreshReportLoaders(processorViewer, viewer);
	}

	private void refreshReportLoaders(final ComboViewer moduleViewer,
			final TableViewer engineViewer) {
		IStructuredSelection selection = (IStructuredSelection) moduleViewer
				.getSelection();
		if (!selection.isEmpty()) {
			IReportProcessor processor = (IReportProcessor) selection
					.getFirstElement();
			engineViewer.setInput(processor.getReportLoaders());
			engineViewer.refresh();
		}
	}

	private List<IReportLoader> getAllReportLoaders() {
		return getModule().getAllReportLoaders();
	}

	protected IReportModule getModule() {
		return ((ReportingEditorInput) page.getEditor().getEditorInput())
				.getEntry().getModule();
	}

	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		Action haction = new Action("hor", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};
		haction.setChecked(true);
		//		haction.setToolTipText(Messages.getString("ScrolledPropertiesBlock.horizontal")); //$NON-NLS-1$
		haction.setImageDescriptor(ImageResources
				.getImageDescriptor(ImageResources.IMG_TH_HORIZONTAL));
		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};
		vaction.setChecked(false);
		//		vaction.setToolTipText(Messages.getString("ScrolledPropertiesBlock.vertical")); //$NON-NLS-1$
		vaction.setImageDescriptor(ImageResources
				.getImageDescriptor(ImageResources.IMG_TH_VERTICAL));
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);
	}

	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.setPageProvider(this);
		typeOneDetailsPage = new GenericReportLoaderDetailsPage();
		// detailsPart.registerPage(IReportLoader.class, new
		// TypeOneDetailsPage());
		// detailsPart.registerPage(TypeOne.class, new TypeOneDetailsPage());
		// detailsPart.registerPage(TypeTwo.class, new TypeTwoDetailsPage());
	}

	public Object getPageKey(Object object) {
		IReportLoader reportLoader = (IReportLoader) object;
		return reportLoader.getProcessor().getEngine();
	}

	public IDetailsPage getPage(Object key) {
		IReportEngine engine = (IReportEngine) key;

		IDetailsPage page = null;
		try {
			page = PlatformXDocReport.getUIFragmentRegistry()
					.createDetailsPage(engine.getId());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (page == null) {
			page = typeOneDetailsPage;
		}
		return page;
	}
}