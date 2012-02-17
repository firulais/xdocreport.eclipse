package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.project.Client;
import org.dynaresume.eclipse.search.ui.modelpickers.client.ClientCompletionLabelProvider;
import org.dynaresume.eclipse.search.ui.modelpickers.client.ClientCompletionSearcher;
import org.dynaresume.eclipse.search.ui.modelpickers.client.ClientSearchDialogSearcher;
import org.dynaresume.eclipse.search.ui.modelpickers.client.ClientSearchable;
import org.dynaresume.eclipse.search.ui.modelpickers.skill.SkillCompletionLabelProvider;
import org.dynaresume.eclipse.search.ui.modelpickers.skill.SkillCompletionSearcher;
import org.dynaresume.eclipse.search.ui.modelpickers.skill.SkillSearchable;
import org.dynaresume.services.ClientService;
import org.dynaresume.services.SkillService;
import org.eclipse.nebula.widgets.modelpicker.ModelPicker;
import org.eclipse.nebula.widgets.modelpicker.forms.FormModelPicker;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ModelPickersFactory {

	public static ModelPicker<Client> createClientPicker(Composite parent,
			int style, int textStyle, FormToolkit toolkit, ClientService clientService) {
		ModelPicker<Client> clientPicker = new FormModelPicker<Client>(parent,
				style, textStyle, toolkit);
		clientPicker.setModelLabelProvider(ClientCompletionLabelProvider
				.getInstance());
		ClientSearchable searchable = new ClientSearchable();
		searchable.setClientService(clientService);
		clientPicker.addSearcher(new ClientCompletionSearcher(searchable),
				"Ctrl+Space", "Autocomplete", null);
		clientPicker.addSearcher(new ClientSearchDialogSearcher(), "F2",
				"Search dialog", null);
		return clientPicker;
	}

	public static ModelPicker<Skill> createSkillPicker(Composite parent,
			int style, int textStyle, FormToolkit toolkit, SkillService skillService) {
		ModelPicker<Skill> skillPicker = new FormModelPicker<Skill>(parent,
				style, textStyle, toolkit);
		SkillSearchable searchable = new SkillSearchable();
		skillPicker.setModelLabelProvider(SkillCompletionLabelProvider
				.getInstance());
		skillPicker.addSearcher(new SkillCompletionSearcher(searchable),
				"Ctrl+Space", "Autocomplete", null);
		return skillPicker;
	}
}
