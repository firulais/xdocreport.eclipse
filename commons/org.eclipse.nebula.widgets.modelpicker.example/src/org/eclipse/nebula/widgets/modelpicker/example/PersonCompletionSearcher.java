package org.eclipse.nebula.widgets.modelpicker.example;

import org.eclipse.nebula.widgets.modelpicker.ModelHolder;
import org.eclipse.nebula.widgets.modelpicker.ModelPicker;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionSearcher;

public class PersonCompletionSearcher extends CompletionSearcher {
	
	@Override
	public void init(ModelHolder modelPicker, String key) {		
		super.init(modelPicker, key);
		super.setSearchable(PersonSearchable.getInstance());
		super.setCompletionLabelProvider(PersonCompletionLabelProvider
				.getInstance());
	}

}
