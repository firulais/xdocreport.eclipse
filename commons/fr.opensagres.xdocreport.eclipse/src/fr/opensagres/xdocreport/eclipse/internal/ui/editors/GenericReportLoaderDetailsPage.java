package fr.opensagres.xdocreport.eclipse.internal.ui.editors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.IReportLoader;
import fr.opensagres.xdocreport.eclipse.internal.Messages;

public class GenericReportLoaderDetailsPage implements IDetailsPage {

	private IManagedForm mform;
	private IReportLoader reportLoader;
	// private TypeOne input;
	private Button[] choices;

	private Text reportIdText;
	private Text reportNameText;
	private Text reportDescText;
	private Label reportEngineLabel;

	public GenericReportLoaderDetailsPage() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#initialize(org.eclipse.ui.forms.
	 * IManagedForm)
	 */
	public void initialize(IManagedForm mform) {
		this.mform = mform;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = mform.getToolkit();

		Section reportLoaderSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		reportLoaderSection.marginWidth = 10;
		reportLoaderSection
				.setText(Messages.GenericReportLoaderDetailsPage_title); //$NON-NLS-1$
		reportLoaderSection
				.setDescription(Messages.GenericReportLoaderDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		reportLoaderSection.setLayoutData(td);

		Composite client = toolkit.createComposite(reportLoaderSection);
		reportLoaderSection.setClient(client);
		
		// Create generic content
		createGenericContent(toolkit, client);
		createSpecificContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createGenericContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Report Engine
		toolkit.createLabel(parent,
				Messages.GenericReportLoaderDetails_reportEngine_label);
		reportEngineLabel = toolkit.createLabel(parent, "", SWT.SINGLE);
		reportEngineLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Report ID
		toolkit.createLabel(parent,
				Messages.GenericReportLoaderDetails_reportId_label);
		reportIdText = toolkit.createText(parent, "", SWT.SINGLE);
		reportIdText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Name
		toolkit.createLabel(parent,
				Messages.GenericReportLoaderDetails_reportName_label);
		reportNameText = toolkit.createText(parent, "", SWT.SINGLE);
		reportNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Description
		toolkit.createLabel(parent,
				Messages.GenericReportLoaderDetails_reportDesc_label);
		reportDescText = toolkit.createText(parent, "", SWT.MULTI);
		reportDescText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Source stream of the report
		createSourceContent(toolkit, parent);
	}

	private void createSourceContent(FormToolkit toolkit, Composite parent) {
		toolkit.createLabel(parent,
				Messages.GenericReportLoaderDetails_reportSource_label);
		Composite sourceComposite = toolkit.createComposite(parent);
		sourceComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		sourceComposite.setLayout(new GridLayout(2, false));
		
		// Modify button
		Button addMultiBtn = new Button(sourceComposite, SWT.PUSH);
		addMultiBtn.setText("Modify");
		addMultiBtn
				.setToolTipText("Launches file dialog for file selection.");
		// addMultiBtn.setLayoutData(factory.create());
		addMultiBtn.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.SHELL_TRIM | SWT.SINGLE
						| SWT.APPLICATION_MODAL);
				fd.setText("Upload Multiple Files");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.txt", "*.doc", "*.rtf", "*.*" };
				fd.setFilterExtensions(filterExt);
				String[] filterNames = { "Text Files", "Word Document",
						"Rich Text Format", "All Files" };
				fd.setFilterNames(filterNames);
				String selected = fd.open();
				if (selected != null && selected.length() > 0) {

					File f = new File(selected);
					try {
						FileInputStream in = new FileInputStream(f);

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		// View button
		Button viewButton = new Button(sourceComposite, SWT.PUSH);
		viewButton.setText("View");
		viewButton
				.setToolTipText("View the source document.");
		viewButton.addSelectionListener(new SelectionAdapter() {
		
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
			}
		});
		
	}

	protected void createSpecificContent(FormToolkit toolkit, Composite client) {		
		
	}

	protected void update() {
		if (reportLoader == null) {
			return;
		}
		// reportEngineLabel.setText(reportLoader.getProcessorType().getEngine());
		reportIdText.setText(reportLoader.getReportId());
		reportNameText.setText(reportLoader.getName());
		reportDescText.setText(reportLoader.getDescription());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.forms.IDetailsPage#inputChanged(org.eclipse.jface.viewers
	 * .IStructuredSelection)
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			reportLoader = (IReportLoader) ssel.getFirstElement();
		} else
			reportLoader = null;
		update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	public void commit(boolean onSave) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#setFocus()
	 */
	public void setFocus() {
		choices[0].setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#isDirty()
	 */
	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#refresh()
	 */
	public void refresh() {
		update();
	}

	public boolean setFormInput(Object input) {
		return false;
	}
}
