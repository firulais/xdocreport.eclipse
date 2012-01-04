package org.dynaresume.eclipse.search.ui.dialogs;

import org.dynaresume.domain.project.Client;
import org.dynaresume.domain.project.Project;
import org.dynaresume.eclipse.search.ui.internal.Messages;
import org.dynaresume.services.ProjectService;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.pagination.PageableController;
import org.eclipse.nebula.widgets.pagination.table.SortTableColumnSelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.opensagres.xdocreport.eclipse.ui.dialogs.SearchDialog;

public class SearchProjectDialog extends SearchDialog<Project> {

	public final static String ID = "org.dynaresume.eclipse.search.ui.dialogs.SearchProjectDialog";

	private ProjectService projectService;

	private String projectNameCriteria;

	private Text projectNameText;

	public void setProjectService(ProjectService groupService) {
		this.projectService = groupService;
	}

	public SearchProjectDialog() {
		super();
	}

	@Override
	protected void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent) {
		toolkit.createLabel(parent, "Label:");
		projectNameText = toolkit.createText(parent,
				projectNameCriteria != null ? projectNameCriteria : "",
				SWT.BORDER);
		projectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void prepareCriteria() {
		projectNameCriteria = projectNameText.getText();
	}

	@Override
	protected String getSearchCriteriaGroupText() {
		return Messages.SearchProjectDialog_searchCriteriaGroupText;
	}

	@Override
	protected String getSearchResultSectionDescription() {
		return Messages.SearchProjectDialog_searchResultSectionDescription;
	}

	@Override
	protected String getSearchResultSectionText() {
		return Messages.SearchProjectDialog_searchResultSectionText;
	}

	public Page<Project> loadPage(PageableController controller) {
		return projectService.findByName(projectNameCriteria,
				(Pageable) controller);
	}

	@Override
	protected void createColumns(final TableViewer viewer) {

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, "Name", 200);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				org.dynaresume.domain.project.Project p = (org.dynaresume.domain.project.Project) element;
				return p.getName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("name"));

		col = createTableViewerColumn(viewer, "Client", 200);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Client client = ((Project) element).getClient();
				if (client == null) {
					return "";
				}
				return client.getName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("client.name"));

	}

}
