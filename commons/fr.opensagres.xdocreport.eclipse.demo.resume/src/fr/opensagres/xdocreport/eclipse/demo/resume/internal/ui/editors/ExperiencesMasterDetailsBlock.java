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
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Experience;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.Messages;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers.ExperienceContentProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.internal.ui.viewers.ExperienceLabelProvider;

public class ExperiencesMasterDetailsBlock extends
		ModelMasterDetailsBlock<Resume> {

	private TableViewer viewer;

	public ExperiencesMasterDetailsBlock(ExperiencesPage experiencesPage) {
		super(experiencesPage);
	}

	@Override
	protected void onCreateUI(final IManagedForm managedForm, Composite parent) {

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText(Messages.ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_title); //$NON-NLS-1$
		section.setDescription(Messages.ResumeFormEditor_ExperiencesPage_ExperiencesMasterDetailsBlock_desc); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		Table experiencesTable = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		experiencesTable.setLayoutData(gd);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);

		Button b = toolkit.createButton(client, "Add", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		section.setClient(client);
		
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TableViewer(experiencesTable);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
		viewer.setContentProvider(ExperienceContentProvider.getInstance());
		viewer.setLabelProvider(ExperienceLabelProvider.getInstance());

		// viewer.setInput();
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {

	}

	@Override
	public void onBind(DataBindingContext dataBindingContext) {
		Set<Experience> experiences = getModelObject().getExperiences();
		if (experiences == null) {
			getModelObject().setExperiences(experiences);
		}
		viewer.setInput(experiences);

	}

}
