package org.dynaresume.eclipse.search.ui.dialogs;

import org.dynaresume.domain.hr.Resume;
import org.dynaresume.eclipse.search.ui.internal.Messages;
import org.dynaresume.services.ResumeService;
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

public class SearchResumeDialog extends SearchDialog<Resume> {

	public final static String ID = "org.dynaresume.eclipse.search.ui.dialogs.SearchResumeDialog";

	private ResumeService resumeService;

	private String firstNameCriteria;
	private String lastNameCriteria;

	private Text firstNameText;

	private Text lastNameText;

	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	public SearchResumeDialog() {
		super();
	}

	@Override
	protected void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent) {
		toolkit.createLabel(parent,
				Messages.SearchResumeDialog_searchCriteria_FirstName_label);
		firstNameText = toolkit.createText(parent, "", SWT.BORDER);
		firstNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		toolkit.createLabel(parent,
				Messages.SearchResumeDialog_searchCriteria_LastName_label);
		lastNameText = toolkit.createText(parent, "", SWT.BORDER);
		lastNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}

	@Override
	protected void prepareCriteria() {
		firstNameCriteria = firstNameText.getText();
		lastNameCriteria = lastNameText.getText();
	}

	@Override
	protected String getSearchCriteriaGroupText() {
		return Messages.SearchResumeDialog_searchCriteria_label;
	}

	@Override
	protected String getSearchResultSectionDescription() {
		return Messages.SearchResumeDialog_searchResults_desc;
	}

	@Override
	protected String getSearchResultSectionText() {
		return Messages.SearchResumeDialog_searchResults_label;
	}

	public Page<Resume> loadPage(PageableController controller) {
		return resumeService.findByFirstNameAndLastName(firstNameCriteria,
				lastNameCriteria, (Pageable) controller);

	}

	@Override
	protected void createColumns(final TableViewer viewer) {
		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer,
				Messages.SearchResumeDialog_resumeTable_FirstName_label, 120);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Resume p = (Resume) element;
				return p.getOwner().getFirstName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("owner.firstName"));

		// Second column is for the last name
		col = createTableViewerColumn(viewer,
				Messages.SearchResumeDialog_resumeTable_LastName_label, 120);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Resume p = (Resume) element;
				return p.getOwner().getLastName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("owner.lastName"));

		// Title column is for the last name
		col = createTableViewerColumn(viewer,
				Messages.SearchResumeDialog_resumeTable_ResumeTitle_label, 180);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Resume p = (Resume) element;
				return p.getTitle();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("title"));
	}

}
