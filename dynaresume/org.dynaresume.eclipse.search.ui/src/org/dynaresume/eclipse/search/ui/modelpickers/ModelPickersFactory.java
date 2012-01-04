package org.dynaresume.eclipse.search.ui.modelpickers;

import org.dynaresume.domain.project.Client;
import org.dynaresume.services.ClientService;
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

}
