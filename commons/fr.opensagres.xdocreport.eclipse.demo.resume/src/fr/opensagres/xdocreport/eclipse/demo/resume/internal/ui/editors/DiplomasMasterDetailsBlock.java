package fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.editors;

import java.util.Set;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Diploma;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers.DiplomaContentProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers.DiplomaLabelProvider;

public class DiplomasMasterDetailsBlock extends ModelMasterDetailsBlock<Resume> {

	private DiplomaDetailsPage diplomaDetailsPage;
	private TableViewer viewer;

	public DiplomasMasterDetailsBlock(DiplomasPage diplomasPage) {
		super(diplomasPage);
		this.diplomaDetailsPage = new DiplomaDetailsPage();
	}

	@Override
	protected void onCreateUI(final IManagedForm managedForm, Composite parent) {

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText(Messages.ResumeFormEditor_DiplomasPage_DiplomasMasterDetailsBlock_title); //$NON-NLS-1$
		section.setDescription(Messages.ResumeFormEditor_DiplomasPage_DiplomasMasterDetailsBlock_desc); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		Table diplomasTable = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		diplomasTable.setLayoutData(gd);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);

		Button b = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		section.setClient(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TableViewer(diplomasTable);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
		viewer.setContentProvider(DiplomaContentProvider.getInstance());
		viewer.setLabelProvider(DiplomaLabelProvider.getInstance());
	}

	@Override
	public void onBind(DataBindingContext dataBindingContext) {
		Set<Diploma> diplomas = getModelObject().getDiplomas();
		if (diplomas == null) {
			getModelObject().setDiplomas(diplomas);
		}
		viewer.setInput(diplomas);
	}

	public IDetailsPage getPage(Object key) {
		return diplomaDetailsPage;
	}

	public Object getPageKey(Object object) {
		return null;
	}

}
