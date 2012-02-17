package org.dynaresume.eclipse.search.ui.modelpickers.skill;

import org.eclipse.nebula.widgets.modelpicker.ISearchable;
import org.eclipse.nebula.widgets.modelpicker.ModelHolder;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionSearcher;

public class SkillCompletionSearcher extends CompletionSearcher {

	private ISearchable searchable;

	public SkillCompletionSearcher(ISearchable searchable) {
		this.searchable = searchable;
	}

	@Override
	public void init(ModelHolder modelPicker, String key) {
		super.init(modelPicker, key);
		super.setSearchable(searchable);
		super.setCompletionLabelProvider(SkillCompletionLabelProvider
				.getInstance());
	}

}
