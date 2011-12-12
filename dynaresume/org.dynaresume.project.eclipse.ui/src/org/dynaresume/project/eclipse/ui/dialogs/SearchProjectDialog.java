package org.dynaresume.project.eclipse.ui.dialogs;

import org.dynaresume.services.ProjectService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.pagination.springdata.forms.FormPageableTable;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.collections.PageLoader;

import fr.opensagres.xdocreport.eclipse.ui.dialogs.SearchDialog;

public class SearchProjectDialog extends SearchDialog implements PageLoader {

	public final static String ID = "org.dynaresume.project.eclipse.ui.dialogs.SearchProjectDialog";

	private ProjectService projectService;

	private String labelCriteria;

	private FormPageableTable paginationTable;

	public void setProjectService(ProjectService groupService) {
		this.projectService = groupService;
	}

	public SearchProjectDialog() {
		super();
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		Composite parent = managedForm.getForm().getBody();
		GridLayout layout = new GridLayout();
		parent.setLayout(layout);

		FormToolkit toolkit = managedForm.getToolkit();
		createSearchCriteriaContainer(toolkit, parent, managedForm);
		createSearchResultContainer(toolkit, parent);

		SingleSourcingUtils.FormToolkit_paintBordersFor(
				managedForm.getToolkit(), parent);
	}

	private void createSearchCriteriaContainer(FormToolkit toolkit,
			Composite parent, final IManagedForm managedForm) {
		Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout());

		group.setText("Project search criteria");
		toolkit.adapt(group);

		Composite container = toolkit.createComposite(group);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(4, false));

		toolkit.createLabel(container, "Label:");
		final Text firstNameText = toolkit
				.createText(container, "", SWT.BORDER);
		firstNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button searchButton = toolkit.createButton(container, "Search",
				SWT.BORDER);
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				labelCriteria = firstNameText.getText();
				paginationTable.refreshPage(true);
			}
		});
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		data.horizontalAlignment = GridData.END;
		searchButton.setLayoutData(data);

		// section.setClient(container);
	}

	private void createSearchResultContainer(FormToolkit toolkit,
			Composite parent) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.EXPANDED);
		// td = new TableWrapData(TableWrapData.FILL);
		// td.colspan = 2;
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		section.setLayout(layout);

		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		// section.addExpansionListener(new ExpansionAdapter() {
		// public void expansionStateChanged(ExpansionEvent e) {
		// managedForm.getForm().reflow(true);
		// }
		// });
		section.setText("Project search results");
		section.setDescription("Select one or several resume from the below list of resume search results and click OK to open the selected resumes.");

		Composite container = toolkit.createComposite(section);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		layout = new GridLayout();
		layout.marginHeight = 0;
		container.setLayout(layout);

		paginationTable = new FormPageableTable(container, SWT.NONE,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
						| SWT.BORDER, toolkit);
		paginationTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		paginationTable.setPageLoader(this);
		
		TableViewer viewer = paginationTable.getViewer();
		createColumns(viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());

		paginationTable.setCurrentPage(0);
		section.setClient(container);
	}

	public Page<?> loadPage(Pageable pageable) {
		return projectService.findByName(labelCriteria, pageable);
	}

	// private void createViewer(Composite parent) {
	// viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
	// | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	// createColumns(parent, viewer);
	// final Table table = viewer.getTable();
	// table.setHeaderVisible(true);
	// table.setLinesVisible(true);
	//
	// viewer.setContentProvider(new ArrayContentProvider());
	// // ProjectService resumeService = (ProjectService)
	// // PlatformUI.getWorkbench()
	// // .getService(ProjectService.class);
	// // Get the content for the viewer, setInput will call getElements in the
	// // contentProvider
	// viewer.setInput(resumeService.findAll());
	//
	// // Layout the viewer
	// GridData gridData = new GridData();
	// gridData.verticalAlignment = GridData.FILL;
	// gridData.horizontalSpan = 2;
	// gridData.grabExcessHorizontalSpace = true;
	// gridData.grabExcessVerticalSpace = true;
	// gridData.horizontalAlignment = GridData.FILL;
	// viewer.getControl().setLayoutData(gridData);
	// }
	//
	// public TableViewer getViewer() {
	// return viewer;
	// }

	// This will create the columns for the table
	private void createColumns(final TableViewer viewer) {
		String[] titles = { "Name", };
		int[] bounds = { 200, };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, titles[0],
				bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				org.dynaresume.domain.project.Project p = (org.dynaresume.domain.project.Project) element;
				return p.getName();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;

	}

	/*
	 * Overrides method from Dialog
	 */
	protected void okPressed() {
		// Build a list of selected children.
		IStructuredSelection selection = (IStructuredSelection) paginationTable
				.getViewer().getSelection();
		setResult(selection.toList());
		super.okPressed();
	}

}
