package fr.opensagres.eclipse.forms.samples.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.opensagres.eclipse.forms.samples.model.Person;
import fr.opensagres.eclipse.forms.samples.services.pagination.Page;
import fr.opensagres.eclipse.forms.samples.services.pagination.PageImpl;
import fr.opensagres.eclipse.forms.samples.services.pagination.Pageable;

public class PersonService implements IPersonService {

	private static final IPersonService INSTANCE = new PersonService();
	private Map<Long, Person> persons = new HashMap<Long, Person>();

	public PersonService() {
		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			person.getId();
			person.setName("Name" + i);
			this.savePerson(person);
		}
	}

	public static IPersonService getInstance() {
		return INSTANCE;
	}

	public Person getPerson(long id) {
		Person person = persons.get(id);
		if (person == null) {
			return null;
		}
		person.setDirty(true);
		// Emulate Person instance
		Person newPerson = new Person();
		newPerson.setName(person.getName());
		newPerson.setId(person.getId());
		return newPerson;
	}

	public Person savePerson(Person person) {
		person.setDirty(true);
		// Emulate Person instance
		Person newPerson = new Person();
		newPerson.setName(person.getName());
		newPerson.setId(person.getId());
		persons.put(newPerson.getId(), newPerson);
		return newPerson;
	}

	public Page<Person> getPersons(Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		// int pageNumber = pageable.getPageNumber();

		// int firstIndex = pageNumber * pageSize;
		// int startPageIndex = currentPageIndex - pageSize;
		// if (startPageIndex <= 0)
		// startPageIndex = 1;
		// int maxPageIndex = (model.getRowCount() / itemsPerPage) + 1;
		// int endPageIndex = currentPageIndex + pageSize - 1;
		// if (endPageIndex > maxPageIndex)
		// endPageIndex = maxPageIndex;

		long totalSize = persons.values().size();
		List<Person> fullList = new ArrayList<Person>(persons.values());
		List<Person> paginatedList = new ArrayList<Person>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Person p = fullList.get(i);
			paginatedList.add(p);
		}
		return new PageImpl<Person>(paginatedList, pageable, totalSize);
	}

	public Collection<Person> getPersons() {
		return Collections.unmodifiableCollection(persons.values());
	}
}
