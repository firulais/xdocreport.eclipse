package org.dynaresume.eclipse.ui.editors;

import java.util.Collection;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryContentProvider;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryLabelProvider;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryWrapper;
import org.dynaresume.eclipse.ui.viewers.SkillsResumeTreeModel;
import org.dynaresume.eclipse.ui.wizards.QuickAddSkillsWizard;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.ModelMasterDetailsBlock;
import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;

public class SkillsMasterDetailsBlock extends ModelMasterDetailsBlock<Resume> {

	private static final Integer ADD_BUTTON_INDEX = 1;
	private static final Integer QUICK_ADD_BUTTON_INDEX = 2;
	private static final Integer REMOVE_BUTTON_INDEX = 3;

	enum TreeItemType {
		Category, SkillFree, Skill
	}

	private final SkillFreeDetailsPage skillFreeDetailsPage;
	private final SkillDetailsPage skillDetailsPage;
	private TreeViewer viewer;

	private Button addButton;
	private Button quickAddButton;
	private Button removeButton;

	private SkillsResumeTreeModel treeModel;

	public SkillsMasterDetailsBlock(SkillsPage skillsPage) {
		super(skillsPage);
		this.skillFreeDetailsPage = new SkillFreeDetailsPage();
		this.skillDetailsPage = new SkillDetailsPage();
	}

	@Override
	protected void onCreateUI(final IManagedForm managedForm, Composite parent) {

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText(Messages.ResumeFormEditor_SkillsPage_SkillsMasterDetailsBlock_title); //$NON-NLS-1$
		section.setDescription(Messages.ResumeFormEditor_SkillsPage_SkillsMasterDetailsBlock_desc); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		Tree skillsTable = toolkit.createTree(client, SWT.MULTI);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		skillsTable.setLayoutData(gd);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);

		createButtons(toolkit, client);

		section.setClient(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TreeViewer(skillsTable);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() == 1) {
					managedForm.fireSelectionChanged(spart,
							event.getSelection());
				}
				modifyEnabledButtons(selection);
			}
		});
		viewer.setContentProvider(SkillCategoryContentProvider.getInstance());
		viewer.setLabelProvider(SkillCategoryLabelProvider.getInstance());
		// viewer.setComparator(SkillsViewerComparator.getInstance());
		modifyEnabledButtons(null);
	}

	private void modifyEnabledButtons(IStructuredSelection selection) {
		boolean enabledAdd = false;
		boolean enabledRemove = false;

		if (selection != null && selection.size() > 0) {
			// One or more selection
			if (selection.size() == 1) {
				// One selection
				boolean selectedCategory = (selection.getFirstElement() instanceof SkillCategoryWrapper);
				// Add button is enabled if category is selected
				enabledAdd = selectedCategory;
				// Remove button is enabled if skill resume is selected
				enabledRemove = !selectedCategory;
			} else {
				// Several selection
				// Add button is disabled and remove button is enabled if there
				// is one or more selected skill resume
				boolean selectedSkill = false;
				Object[] selectedItems = selection.toArray();
				for (int i = 0; i < selectedItems.length; i++) {
					selectedSkill = (selectedItems[i] instanceof SkillResume);
					if (selectedSkill) {
						enabledRemove = true;
						break;
					}
				}
			}
		}
		addButton.setEnabled(enabledAdd);
		quickAddButton.setEnabled(enabledAdd);
		removeButton.setEnabled(enabledRemove);
	}

	private void createButtons(FormToolkit toolkit, Composite parent) {
		GridData gd;
		Composite buttonsContainer = toolkit.createComposite(parent);
		gd = new GridData(GridData.FILL_VERTICAL);
		buttonsContainer.setLayoutData(gd);
		buttonsContainer.setLayout(createButtonsLayout());

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.widget.getData() == ADD_BUTTON_INDEX) {
					handleAddButton();
				} else if (e.widget.getData() == QUICK_ADD_BUTTON_INDEX) {
					handleQuickAddButton();
				} else if (e.widget.getData() == REMOVE_BUTTON_INDEX) {
					handleRemoveButton();
				}
			}
		};

		addButton = toolkit.createButton(buttonsContainer,
				Messages.addButton_label, SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		addButton.setData(ADD_BUTTON_INDEX);
		addButton.setLayoutData(gd);
		addButton.setEnabled(true);
		addButton.addSelectionListener(listener);

		quickAddButton = toolkit.createButton(buttonsContainer,
				Messages.quickAddButton_label, SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		quickAddButton.setData(QUICK_ADD_BUTTON_INDEX);
		quickAddButton.setLayoutData(gd);
		quickAddButton.setEnabled(true);
		quickAddButton.addSelectionListener(listener);

		removeButton = toolkit.createButton(buttonsContainer,
				Messages.removeButton_label, SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		removeButton.setData(REMOVE_BUTTON_INDEX);
		removeButton.setLayoutData(gd);
		removeButton.setEnabled(false);
		removeButton.addSelectionListener(listener);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit,
				buttonsContainer);
	}

	protected void handleAddButton() {

		MessageDialog.openInformation(addButton.getShell(), "TODO", "TODO");
		// skill.setTitle("New skill");
		// getSkills().add(skill);
		// viewer.add(skill);
		// viewer.setSelection(new StructuredSelection(skill));
	}

	protected void handleQuickAddButton() {
		SkillCategoryWrapper selectedCategory = (SkillCategoryWrapper) ((IStructuredSelection) viewer
				.getSelection()).getFirstElement();
		QuickAddSkillsWizard wizard;
		try {
			wizard = PlatformXDocReport.getWizardFactory().createWizard(
					QuickAddSkillsWizard.ID, QuickAddSkillsWizard.class);
			wizard.setCategory(selectedCategory);
			WizardDialog dlg = new WizardDialog(quickAddButton.getShell(),
					wizard);
			dlg.open();

			// Add skills and free skills created by the wizard.
			Collection<SkillResume> newSkills= wizard.getSkills();
			Collection<SkillResume> newFreeSkills= wizard.getFreeSkills();
			if (newSkills.size() > 0 || newFreeSkills.size() > 0) {
				selectedCategory.addAllChild(newSkills);
				selectedCategory.addAllChild(newFreeSkills);
				viewer.refresh();
			}						

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void handleRemoveButton() {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (!selection.isEmpty()) {
			boolean oneSkillWasRemoved = false;
			Object skill = null;
			Object[] skills = selection.toArray();
			for (int i = 0; i < skills.length; i++) {
				skill = skills[i];
				if (skill instanceof SkillResume) {
					SkillCategoryWrapper wrapper = treeModel
							.getCategoryWrapper(((SkillResume) skill)
									.getCategory());
					wrapper.removeChild((SkillResume) skill);
					viewer.remove(skill);
					oneSkillWasRemoved = true;
				}
			}
			if (oneSkillWasRemoved) {
				viewer.refresh();
			}
		}
	}

	protected GridLayout createButtonsLayout() {
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		return layout;
	}

	@Override
	public void onBind(DataBindingContext dataBindingContext) {
		// Set<Skill> skills = getSkills();
		Iterable<SkillCategory> categories = ((ResumeFormEditor) getDetailsPage()
				.getEditor()).getSkillCategoryService().findAll();
		treeModel = new SkillsResumeTreeModel(categories, getModelObject());
		viewer.setInput(treeModel);
		viewer.expandToLevel(2);
	}

	public IDetailsPage getPage(Object key) {
		TreeItemType type = (TreeItemType) key;
		switch (type) {
		case Skill:
			return skillDetailsPage;
		case SkillFree:
			return skillFreeDetailsPage;
		}
		return null;
	}

	public Object getPageKey(Object object) {
		if (object instanceof SkillResume) {
			return ((SkillResume) object).getSkill() == null ? TreeItemType.SkillFree
					: TreeItemType.Skill;
		}
		return TreeItemType.Category;
	}
}
