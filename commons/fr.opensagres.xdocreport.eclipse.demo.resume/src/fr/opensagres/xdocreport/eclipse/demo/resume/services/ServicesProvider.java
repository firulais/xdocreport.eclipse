package fr.opensagres.xdocreport.eclipse.demo.resume.services;

public class ServicesProvider {

	private static final UserService USER_SERVICE = new UserServiceImpl();

	public static UserService getUserService() {
		return USER_SERVICE;
	}
}
