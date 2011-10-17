package fr.opensagres.eclipse.forms.samples.editor;

import fr.opensagres.eclipse.forms.editor.AbstractEditorInput;

public class PersonEditorInput extends AbstractEditorInput {

	private long personId;

	public PersonEditorInput(long personId) {
		this.personId = personId;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "XX";
	}

	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "tt";
	}

	public long getPersonId() {
		return personId;
	}

}
