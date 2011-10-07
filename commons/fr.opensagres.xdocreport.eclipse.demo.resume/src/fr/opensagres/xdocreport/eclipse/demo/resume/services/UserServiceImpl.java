package fr.opensagres.xdocreport.eclipse.demo.resume.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.User;

public class UserServiceImpl implements UserService {

	private static final Map<Long, User> users;
	private static long currentId = 0;
	static {
		users = new HashMap<Long, User>();
		User angelo = new User();
		angelo.setId(currentId++);
		angelo.setFirstName("Angelo");
		angelo.setLastName("ZERR");
		angelo.setPhoto(new ClassPathImageProvider(User.class, "AngeloZERR.jpg"));
		users.put(angelo.getId(), angelo);
		User pascal = new User();
		pascal.setId(currentId++);
		pascal.setFirstName("Pascal");
		pascal.setLastName("Leclercq");
		pascal.setPhoto(new ClassPathImageProvider(User.class, "PascalLeclercq.jpg"));
		users.put(pascal.getId(), pascal);
	}

	public Collection<User> findAll() {
		return users.values();
	}

	public User findById(long id) {
		return users.get(id);
	}

	public void createUser(User collaborateur) {
		collaborateur.setId(currentId++);
		users.put(collaborateur.getId(), collaborateur);

	}
}
