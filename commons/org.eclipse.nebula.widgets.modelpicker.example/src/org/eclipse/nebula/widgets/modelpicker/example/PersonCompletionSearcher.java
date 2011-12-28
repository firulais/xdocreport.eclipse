package org.eclipse.nebula.widgets.modelpicker.example;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.modelpicker.example.model.Person;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.CompletionSearcher;
import org.eclipse.nebula.widgets.modelpicker.fieldassist.ICompletionLabelProvider;

public class PersonCompletionSearcher extends CompletionSearcher {

	private static final List<Person> persons;
	static {
		persons = new ArrayList<Person>();
		for (int i = 0; i < 2000; i++) {
			persons.add(new Person("FirstName_" + i, "LastName_" + i));
		}
	}

	public Iterable<?> search(String contents, int position) {
		List<Person> filteredList = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getFirstName().startsWith(contents)) {
				filteredList.add(person);
			}
		}
		return filteredList;
	}

	@Override
	protected ICompletionLabelProvider getCompletionLabelProvider() {
		return PersonCompletionLabelProvider.getInstance();
	}

}
