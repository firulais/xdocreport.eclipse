package org.dynaresume.eclipse.search.ui.modelpickers.skill;

import org.dynaresume.services.ClientService;
import org.dynaresume.services.SkillService;
import org.eclipse.nebula.widgets.modelpicker.ISearchable;

public class SkillSearchable implements ISearchable {

	private SkillService skillService;

	public void setClientService(SkillService skillService) {
		this.skillService = skillService;
	}

	public Iterable<?> search(String contents, int position) {
		//return skillService.findByName(contents);
		return null;
	}

}
