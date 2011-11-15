package fr.opensagres.eclipse.forms.samples.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;

import fr.opensagres.eclipse.forms.editor.ModelFormEditor;
import fr.opensagres.eclipse.forms.samples.model.Person;
import fr.opensagres.eclipse.forms.samples.services.IPersonService;
import fr.opensagres.eclipse.forms.samples.services.PersonService;

public class PersonEditor extends ModelFormEditor<PersonEditorInput, Person> {

	@Override
	protected void doAddPages() {
		try {
			super.addPage(new OverviewPage(this));
			super.addPage(new AdressPage(this));
			super.addPage(new AdressPage2(this));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Person onLoad(PersonEditorInput input) {
		long personId= input.getPersonId();
		IPersonService personService = PersonService.getInstance();
		return personService.getPerson(personId);
	}
	
	@Override
	protected Person onSave(Person person, IProgressMonitor monitor) {
		IPersonService personService = PersonService.getInstance();
		return personService.savePerson(person);
	}

}
