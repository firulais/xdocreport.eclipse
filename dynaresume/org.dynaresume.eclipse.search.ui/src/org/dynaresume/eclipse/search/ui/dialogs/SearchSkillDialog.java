package org.dynaresume.eclipse.search.ui.dialogs;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.eclipse.search.ui.internal.Messages;
import org.dynaresume.services.SkillService;
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

public class SearchSkillDialog extends SearchDialog<Skill> {

	public final static String ID = "org.dynaresume.eclipse.search.ui.dialogs.SearchSkillDialog";

	private SkillService skillService;

	private String skillNameCriteria;
	private Text skillNameText;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public SearchSkillDialog() {
		super();
	}

	@Override
	protected void createSearchCriteriaBody(FormToolkit toolkit,
			Composite parent) {
		toolkit.createLabel(parent, "Label:");
		skillNameText = toolkit.createText(parent,
				skillNameCriteria != null ? skillNameCriteria : "", SWT.BORDER);
		skillNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void prepareCriteria() {
		skillNameCriteria = skillNameText.getText();
	}

	public void setSkillNameCriteria(String skillNameCriteria) {
		this.skillNameCriteria = skillNameCriteria;
	}

	@Override
	protected String getSearchCriteriaGroupText() {
		return Messages.SearchSkillDialog_searchCriteriaGroupText;
	}

	@Override
	protected String getSearchResultSectionDescription() {
		return Messages.SearchSkillDialog_searchResultSectionDescription;
	}

	@Override
	protected String getSearchResultSectionText() {
		return Messages.SearchSkillDialog_searchResultSectionText;
	}

	public Page<Skill> loadPage(PageableController controller) {
		return skillService
				.findByName(skillNameCriteria, (Pageable) controller);
	}

	@Override
	protected void createColumns(final TableViewer viewer) {
		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(viewer, "Label", 180);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Skill p = (Skill) element;
				return p.getName();
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("name"));

		// Second column is for the last name
		col = createTableViewerColumn(viewer, "Parent", 180);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Skill p = (Skill) element;
				if (p.getParent() != null) {
					return p.getParent().getName();
				}
				return "";
			}
		});
		col.getColumn().addSelectionListener(
				new SortTableColumnSelectionListener("parent.name"));

	}

}
