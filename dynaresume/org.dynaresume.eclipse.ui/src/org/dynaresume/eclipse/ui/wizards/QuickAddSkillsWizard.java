package org.dynaresume.eclipse.ui.wizards;

import java.util.Collection;
import java.util.List;

import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.viewers.SkillCategoryWrapper;
import org.dynaresume.services.SkillService;
import org.eclipse.jface.wizard.Wizard;

public class QuickAddSkillsWizard extends Wizard {

	public static final String ID = "org.dynaresume.eclipse.ui.wizards.QuickAddSkillsWizard";

	private final QuickAddSillsFillSkillsWizardPage page1;
	private final QuickAddSillsPreviewWizardPage page2;

	private SkillCategoryWrapper category;
	private SkillService skillService;

	public QuickAddSkillsWizard() {
		page1 = new QuickAddSillsFillSkillsWizardPage();
		addPage(page1);
		page2 = new QuickAddSillsPreviewWizardPage();
		addPage(page2);
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean canFinish() {
		return getSkills().size() > 0 || getFreeSkills().size() > 0;
	}

	public void setCategory(SkillCategoryWrapper category) {
		this.category = category;
	}

	public SkillCategoryWrapper getCategory() {
		return category;
	}

	public SkillService getSkillService() {
		return skillService;
	}

	public List<SkillResume> getSkills() {
		return page1.getSkills();
	}

	public List<SkillResume> getFreeSkills() {
		return page1.getFreeSkills();
	}

	public List<SkillResume> getExistingSkills() {
		return page1.getExistingSkills();
	}
}
