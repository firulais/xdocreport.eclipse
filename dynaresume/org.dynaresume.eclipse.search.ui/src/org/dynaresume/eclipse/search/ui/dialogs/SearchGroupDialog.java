package org.dynaresume.eclipse.search.ui.dialogs;

import org.dynaresume.eclipse.search.ui.internal.Messages;
import org.dynaresume.services.GroupService;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.nebula.widgets.pagination.PageableController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.opensagres.xdocreport.eclipse.ui.dialogs.SearchDialog;

public class SearchGroupDialog extends
		SearchDialog<org.dynaresume.domain.core.Group> {

	public final static String ID = "org.dynaresume.eclipse.search.ui.dialogs.SearchGroupDialog";

	private GroupService groupService;

	private String labelCriteria;

	private Text groupLabelText;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public SearchGroupDialog() {
		super();
	}

	@Override
	protected void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent) {
		toolkit.createLabel(parent, "Label:");
		groupLabelText = toolkit.createText(parent,
				labelCriteria != null ? labelCriteria : "", SWT.BORDER);
		groupLabelText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void prepareCriteria() {
		labelCriteria = groupLabelText.getText();
	}

	@Override
	protected String getSearchCriteriaGroupText() {
		return Messages.SearchGroupDialog_searchCriteriaGroupText;
	}

	@Override
	protected String getSearchResultSectionDescription() {
		return Messages.SearchGroupDialog_searchResultSectionDescription;
	}

	@Override
	protected String getSearchResultSectionText() {
		return Messages.SearchGroupDialog_searchResultSectionText;
	}

	public Page<org.dynaresume.domain.core.Group> loadPage(
			PageableController controller) {
		return groupService.findByName(labelCriteria, (Pageable) controller);
	}

	@Override
	protected void createColumns(final TableViewer viewer) {
		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, "Name", 200);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				org.dynaresume.domain.core.Group p = (org.dynaresume.domain.core.Group) element;
				return p.getName();
			}
		});

	}

}
