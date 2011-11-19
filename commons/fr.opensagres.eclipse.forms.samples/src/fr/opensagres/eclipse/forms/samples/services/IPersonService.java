package fr.opensagres.eclipse.forms.samples.services;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.opensagres.eclipse.forms.samples.model.Person;

public interface IPersonService {

	Person getPerson(long id);

	Person savePerson(Person person);
	
	Collection<Person> getPersons();
	
	Page<Person> getPersons(Pageable pageable);
}
