package fr.opensagres.xdocreport.eclipse.internal.ui.editors;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
	private Button flag;
	private Text text;
	private Text reportIdText;
	private static final String RTEXT_DATA = "<form><p>An example of a free-form text that should be " + //$NON-NLS-1$
			"wrapped below the section with widgets.</p>" + //$NON-NLS-1$
			"<p>It can contain simple tags like <a>links</a> and <b>bold text</b>.</p></form>"; //$NON-NLS-1$

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
		Section s1 = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		s1.marginWidth = 10;
		s1.setText(Messages.GenericReportLoaderDetailsPage_title); //$NON-NLS-1$
		s1.setDescription(Messages.GenericReportLoaderDetailsPage_desc); //$NON-NLS-1$
		
		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = glayout.marginHeight = 0;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		// Report ID
		toolkit.createLabel(client, Messages.GenericReportLoaderDetails_reportId_label);
		reportIdText = toolkit.createText(client, "", SWT.SINGLE);
				
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, s1);
		// toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}

	protected void update() {
		if (reportLoader == null) {
			return;
		}
		reportIdText.setText(reportLoader.getReportId()); 
		// for (int i=0; i<TypeOne.CHOICES.length; i++) {
		// choices[i].setSelection(input!=null && input.getChoice()==i);
		// }
		// flag.setSelection(input!=null && input.getFlag());
		//		text.setText(input!=null && input.getText()!=null?input.getText():""); //$NON-NLS-1$
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
