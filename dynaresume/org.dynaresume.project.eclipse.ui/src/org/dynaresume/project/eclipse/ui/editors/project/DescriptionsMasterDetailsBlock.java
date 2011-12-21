package org.dynaresume.project.eclipse.ui.editors.project;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.dynaresume.domain.project.Project;
import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.domain.project.ProjectDescriptionType;
import org.dynaresume.project.eclipse.ui.internal.Messages;
import org.dynaresume.project.eclipse.ui.viewer.ProjectDescriptionTreeContentProvider;
import org.dynaresume.project.eclipse.ui.viewer.ProjectDescriptionTreeLabelProvider;
import org.dynaresume.project.eclipse.ui.viewer.ProjectDescriptionTypeWrapper;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
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

public class DescriptionsMasterDetailsBlock extends
		ModelMasterDetailsBlock<Project> {

	private static final Integer ADD_BUTTON_INDEX = 1;
	private static final Integer REMOVE_BUTTON_INDEX = 2;

	enum TreeItemType {
		Description, DescriptionType
	}

	private DescriptionTypeDetailsPage projectDescriptionTypeDetailsPage;
	private DescriptionDetailsPage projectDescriptionDetailsPage;

	private TreeViewer viewer;
	private Button removeButton;
	private Button addButton;

	public DescriptionsMasterDetailsBlock(DescriptionsPage descriptionsPage) {
		super(descriptionsPage);
		this.projectDescriptionTypeDetailsPage = new DescriptionTypeDetailsPage();
		this.projectDescriptionDetailsPage = new DescriptionDetailsPage();
	}

	@Override
	protected void onCreateUI(final IManagedForm managedForm, Composite parent) {

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR);
		section.setText(Messages.ResumeFormEditor_DescriptionsPage_DescriptionsMasterDetailsBlock_title); //$NON-NLS-1$
		section.setDescription(Messages.ResumeFormEditor_DescriptionsPage_DescriptionsMasterDetailsBlock_desc); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		Tree descriptionsTree = toolkit.createTree(client, SWT.MULTI);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;
		descriptionsTree.setLayoutData(gd);
		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);

		createButtons(toolkit, client);

		section.setClient(client);

		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TreeViewer(descriptionsTree);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() == 1) {
					managedForm.fireSelectionChanged(spart,
							event.getSelection());
				}
				addButton.setEnabled(true);
				removeButton.setEnabled(selection.getFirstElement() instanceof ProjectDescription);
			}
		});
		viewer.setContentProvider(ProjectDescriptionTreeContentProvider
				.getInstance());
		viewer.setLabelProvider(ProjectDescriptionTreeLabelProvider
				.getInstance());
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
		addButton.setEnabled(false);
		addButton.addSelectionListener(listener);

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
		// ProjectDescriptionType type = null;
		Object selectedElement = ((IStructuredSelection) viewer.getSelection())
				.getFirstElement();
		ProjectDescriptionTypeWrapper selectedWrapper = getSelectedWrapper(selectedElement);
		if (selectedWrapper != null) {
			ProjectDescriptionType type = selectedWrapper.getType();
			ProjectDescription projectDescription = new ProjectDescription();
			projectDescription.setType(type);
			projectDescription.setDescription("New description");
			selectedWrapper.addDescription(projectDescription);
			viewer.refresh();
			// Open the description type tree node where description was been
			// added.
			viewer.expandToLevel(selectedWrapper, 1);
			// Force the dirty flag
			// TODO : manage that with JFace DB.
			getEditor().setForceDirty(true);
		}
	}

	private ProjectDescriptionTypeWrapper getSelectedWrapper(
			Object selectedElement) {
		ProjectDescriptionTypeWrapper selectedWrapper;
		if (selectedElement instanceof ProjectDescriptionTypeWrapper) {
			selectedWrapper = ((ProjectDescriptionTypeWrapper) selectedElement);
		} else {
			TreeSelection treeSelection = (TreeSelection) viewer.getSelection();
			TreePath firstPath = treeSelection.getPaths()[0];
			selectedWrapper = (ProjectDescriptionTypeWrapper) firstPath
					.getFirstSegment();
		}
		return selectedWrapper;
	}

	protected void handleRemoveButton() {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (!selection.isEmpty()) {
			ProjectDescriptionTypeWrapper selectedWrapper = getSelectedWrapper(selection.getFirstElement());
			if (selectedWrapper==null) {
				return;
			}
			ProjectDescription projectDescription = null;
			Object[] descriptions = selection.toArray();
			for (int i = 0; i < descriptions.length; i++) {
				projectDescription = (ProjectDescription) descriptions[i];
				selectedWrapper.removeDescription(projectDescription);
			}
			viewer.refresh();
		}
	}

	protected GridLayout createButtonsLayout() {
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		return layout;
	}

	@Override
	public void onBind(DataBindingContext dataBindingContext) {

		Map<Long, ProjectDescriptionTypeWrapper> wrappers = new LinkedHashMap<Long, ProjectDescriptionTypeWrapper>();
		Iterable<ProjectDescriptionType> types = getProjectDescriptionTypes();
		ProjectDescriptionTypeWrapper wrapper = null;
		Set<ProjectDescription> descriptions = getDescriptions();
		for (ProjectDescriptionType type : types) {
			wrapper = new ProjectDescriptionTypeWrapper(type, descriptions);
			wrappers.put(type.getId(), wrapper);
		}
		for (ProjectDescription description : descriptions) {
			wrapper = wrappers.get(description.getType().getId());
			if (wrapper != null) {
				wrapper.addDescription(description);
			}
		}
		viewer.setInput(wrappers.values());
		viewer.expandToLevel(2);
	}

	private Iterable<ProjectDescriptionType> getProjectDescriptionTypes() {
		return ((ProjectFormEditor) getEditor())
				.getProjectDescriptionTypeService().findAll();
	}

	private Set<ProjectDescription> getDescriptions() {
		Set<ProjectDescription> descriptions = getModelObject()
				.getDescriptions();
		if (descriptions == null) {
			descriptions = new HashSet<ProjectDescription>();
			getModelObject().setDescriptions(descriptions);
		}
		return descriptions;
	}

	public IDetailsPage getPage(Object key) {
		TreeItemType type = (TreeItemType) key;
		switch (type) {
		case Description:
			return projectDescriptionDetailsPage;
		case DescriptionType:
			return projectDescriptionTypeDetailsPage;
		}
		return null;
	}

	public Object getPageKey(Object object) {
		if (object instanceof ProjectDescriptionTypeWrapper) {
			return TreeItemType.DescriptionType;
		}
		return TreeItemType.Description;
	}

}
