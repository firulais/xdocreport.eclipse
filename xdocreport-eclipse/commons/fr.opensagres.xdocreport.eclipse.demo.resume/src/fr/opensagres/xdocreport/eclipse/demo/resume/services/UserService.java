package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.Collection;

import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;

public interface UserService {

	Collection<User> findAll();
	
	User findById(long id);

	void createUser(User collaborateur);
}
