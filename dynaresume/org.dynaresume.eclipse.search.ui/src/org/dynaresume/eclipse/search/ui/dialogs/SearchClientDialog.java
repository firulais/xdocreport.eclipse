package org.dynaresume.eclipse.search.ui.dialogs;

import org.dynaresume.domain.project.Client;
import org.dynaresume.eclipse.search.ui.internal.Messages;
import org.dynaresume.services.ClientService;
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

public class SearchClientDialog extends SearchDialog<Client> {

	public final static String ID = "org.dynaresume.eclipse.search.ui.dialogs.SearchClientDialog";

	private ClientService clientService;

	private String clientNameCriteria;

	private Text clientNameText;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public SearchClientDialog() {
		super();
	}

	@Override
	protected void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent) {
		toolkit.createLabel(parent, "Label:");
		clientNameText = toolkit.createText(parent,
				clientNameCriteria != null ? clientNameCriteria : "",
				SWT.BORDER);
		clientNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void prepareCriteria() {
		clientNameCriteria = clientNameText.getText();
	}

	public Page<Client> loadPage(PageableController controller) {
		return clientService.findByName(clientNameCriteria,
				(Pageable) controller);
	}

	@Override
	protected void createColumns(final TableViewer viewer) {
		// First column is for the client name
		TableViewerColumn col = createTableViewerColumn(viewer, "Name", 200);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Client p = (Client) element;
				return p.getName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("name"));

	}

	public void setClientNameCriteria(String clientNameCriteria) {
		this.clientNameCriteria = clientNameCriteria;
	}

	@Override
	protected String getSearchCriteriaGroupText() {
		return Messages.SearchClientDialog_searchCriteriaGroupText;
	}

	@Override
	protected String getSearchResultSectionDescription() {
		return Messages.SearchClientDialog_searchResultSectionDescription;
	}

	@Override
	protected String getSearchResultSectionText() {
		return Messages.SearchClientDialog_searchResultSectionText;
	}
}
