package org.dynaresume.eclipse.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryContentProvider;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryLabelProvider;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryWrapper;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class QuickAddSillsFillSkillsWizardPage extends WizardPage implements
		ModifyListener, SelectionListener {

	private static final String ID = "QuickAddSillFillSkillsWizardPage";
	private Text skillsText;
	private boolean dirty;
	private final List<SkillResume> skills;
	private final List<SkillResume> freeSkills;
	private final List<SkillResume> existingSkills;
	private ComboViewer viewer;

	protected QuickAddSillsFillSkillsWizardPage() {
		super(ID, Messages.QuickAddSillFillSkillsWizardPage_title, null);
		super.setDescription(Messages.QuickAddSillFillSkillsWizardPage_desc);
		this.skills = new ArrayList<SkillResume>();
		this.freeSkills = new ArrayList<SkillResume>();
		this.existingSkills = new ArrayList<SkillResume>();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		// Skill category
		Label categoryLabel = new Label(composite, SWT.LEFT);
		categoryLabel
				.setText(Messages.QuickAddSillFillSkillsWizardPage_SkillCategory_label);
		viewer = new ComboViewer(composite, SWT.READ_ONLY);
		viewer.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		viewer.setContentProvider(SkillCategoryContentProvider.getInstance());
		viewer.setLabelProvider(SkillCategoryLabelProvider.getInstance());
		viewer.setInput(getWizard().getCategories());
		SkillCategoryWrapper selectedCategory = getWizard().getSelectedCategory();
		if (selectedCategory != null) {
			viewer.setSelection(new StructuredSelection(selectedCategory));
		}
		viewer.getCombo().addSelectionListener(this);

		// Skills text area
		Label label = new Label(composite, SWT.LEFT);
		label.setText(Messages.QuickAddSillFillSkillsWizardPage_Skills_label);
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		skillsText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		skillsText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		skillsText.addModifyListener(this);
		super.setControl(composite);
	}

	@Override
	public QuickAddSkillsWizard getWizard() {
		return (QuickAddSkillsWizard) super.getWizard();
	}

	public List<SkillResume> getSkills() {
		computeIfNeeded();
		return skills;
	}

	public List<SkillResume> getFreeSkills() {
		computeIfNeeded();
		return freeSkills;
	}

	public List<SkillResume> getExistingSkills() {
		computeIfNeeded();
		return existingSkills;
	}

	private void computeIfNeeded() {
		if (!dirty) {
			return;
		}
		skills.clear();
		freeSkills.clear();
		existingSkills.clear();

		List<String> skillsToSearch = null;
		SkillCategoryWrapper categoryWrapper = getWizard().getSelectedCategory();
		if (categoryWrapper == null) {
			return;
		}
		String skills = skillsText.getText();
		String[] s = skills.split(",");
		String skill = null;
		for (int i = 0; i < s.length; i++) {
			skill = s[i];
			skill = skill.trim();
			if (skill.length() > 0) {
				// 1) Search if categoryWrapper has this skill?
				SkillResume skillResume = categoryWrapper
						.findSkillByName(skill);
				if (skillResume != null) {
					existingSkills.add(skillResume);
				} else {
					if (skillsToSearch == null) {
						skillsToSearch = new ArrayList<String>();
					}
					skillsToSearch.add(skill);
				}
			}
		}
		if (skillsToSearch != null) {
			// 2) Search Skill in the database
			Iterable<Skill> skillsFromDB = getWizard().getSkillService()
					.findByNames(skillsToSearch);
			for (Skill skillFromDB : skillsFromDB) {

				SkillResume skillResume = new SkillResume();
				skillResume
						.setCategory(getWizard().getSelectedCategory().getCategory());
				skillResume.setSkill(skillFromDB);

				this.skills.add(skillResume);
				skillsToSearch.remove(skillFromDB.getName());
			}
			// 3) Add Free Skill
			for (String name : skillsToSearch) {
				SkillResume skillResume = new SkillResume();
				skillResume
						.setCategory(getWizard().getSelectedCategory().getCategory());
				skillResume.setFreeSkill(name);
				freeSkills.add(skillResume);
			}

		}

		dirty = false;
	}

	public void validate() {
		if (!isCategoryFilled()) {
			super.setMessage(
					Messages.QuickAddSillFillSkillsWizardPage_categoryCombo_required,
					IMessageProvider.ERROR);
		} else if (!isSkillsFilled()) {
			super.setMessage(
					Messages.QuickAddSillFillSkillsWizardPage_skillsText_required,
					IMessageProvider.ERROR);

		} else {
			super.setMessage(null);
		}
		getWizard().getContainer().updateButtons();
	}

	@Override
	public boolean canFlipToNextPage() {
		if (!super.canFlipToNextPage()) {
			return false;
		}
		return isSkillsFilled() && isCategoryFilled();
	}

	private boolean isCategoryFilled() {
		return !((IStructuredSelection) viewer.getSelection()).isEmpty();
	}

	private boolean isSkillsFilled() {
		return skillsText.getText().length() > 0;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			if (!isCategoryFilled()) {
				viewer.getCombo().setFocus();
			} else {
				skillsText.setFocus();
			}
			validate();
		}
	}

	public void modifyText(ModifyEvent e) {
		update();
	}

	public void widgetSelected(SelectionEvent e) {
		getWizard().setSelectedCategory(
				(SkillCategoryWrapper) ((IStructuredSelection) viewer
						.getSelection()).getFirstElement());
		update();
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		getWizard().setSelectedCategory(
				(SkillCategoryWrapper) ((IStructuredSelection) viewer
						.getSelection()).getFirstElement());
		update();
	}

	private void update() {
		dirty = true;
		validate();
	}
}
