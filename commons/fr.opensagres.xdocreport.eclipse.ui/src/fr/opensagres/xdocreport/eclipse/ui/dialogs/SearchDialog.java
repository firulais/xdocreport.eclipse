package fr.opensagres.xdocreport.eclipse.ui.dialogs;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.pagination.springdata.ISpringDataPageLoader;
import org.eclipse.nebula.widgets.pagination.springdata.SpringDataPageContentProvider;
import org.eclipse.nebula.widgets.pagination.table.forms.FormPageableTable;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import fr.opensagres.eclipse.forms.dialogs.SelectionFormDialog;
import fr.opensagres.xdocreport.eclipse.internal.Messages;

public abstract class SearchDialog<T> extends SelectionFormDialog implements
		ISpringDataPageLoader<T> {

	private FormPageableTable paginationTable;
	private boolean multipleSelection;

	public SearchDialog(Shell parentShell) {
		super(parentShell);
		this.multipleSelection = true;
	}

	public SearchDialog() {
		super(null);
		this.multipleSelection = true;
	}

	@Override
	public void setParentShell(Shell newParentShell) {
		super.setParentShell(newParentShell);
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

		group.setText(getSearchCriteriaGroupText());
		toolkit.adapt(group);

		Composite container = toolkit.createComposite(group);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		container.setLayout(new GridLayout(4, false));

		createSearchCriteriaBody(toolkit, container);

		Button searchButton = toolkit.createButton(container, Messages.searchButton_label,
				SWT.BORDER);
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				prepareCriteria();
				paginationTable.refreshPage(true);
			}
		});
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		data.horizontalAlignment = GridData.END;
		searchButton.setLayoutData(data);
	}

	private void createSearchResultContainer(FormToolkit toolkit,
			Composite parent) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION
				| Section.TITLE_BAR | Section.EXPANDED);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		section.setLayout(layout);

		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		section.setText(getSearchResultSectionText());
		section.setDescription(getSearchResultSectionDescription());

		Composite container = toolkit.createComposite(section);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		layout = new GridLayout();
		layout.marginHeight = 0;
		container.setLayout(layout);

		int tableStyle = SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION
				| SWT.BORDER;
		if (multipleSelection) {
			tableStyle = tableStyle | SWT.MULTI;
		}
		paginationTable = new FormPageableTable(container, SWT.NONE,
				tableStyle, toolkit,
				SpringDataPageContentProvider.getInstance());

		paginationTable.setPageLoader(this);
		paginationTable.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableViewer viewer = paginationTable.getViewer();
		createColumns(viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		paginationTable.setCurrentPage(0);
		section.setClient(container);
	}

	@Override
	protected void okPressed() {
		// Build a list of selected children.
		IStructuredSelection selection = (IStructuredSelection) paginationTable
				.getViewer().getSelection();
		setResult(selection.toList());
		super.okPressed();
	}

	protected TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int width) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(width);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	public void setMultipleSelection(boolean multipleSelection) {
		this.multipleSelection = multipleSelection;
	}

	protected abstract void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent);

	protected abstract void prepareCriteria();

	protected abstract void createColumns(final TableViewer viewer);

	protected abstract String getSearchCriteriaGroupText();

	protected abstract String getSearchResultSectionDescription();

	protected abstract String getSearchResultSectionText();
}
