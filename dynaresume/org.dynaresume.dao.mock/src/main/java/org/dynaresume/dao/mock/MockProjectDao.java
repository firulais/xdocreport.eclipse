package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dynaresume.dao.ProjectDao;
import org.dynaresume.domain.project.Client;
import org.dynaresume.domain.project.Project;
import org.dynaresume.domain.project.ProjectDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.collections.PageListHelper;
import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class MockProjectDao extends AbstractDaoMock<Project> implements
		ProjectDao {

	protected Project clone(Project project) {
		Project newProject = new Project();
		newProject.setId(project.getId());
		newProject.setName(project.getName());
		newProject.setURL(project.getURL());

		// Descriptions
		Set<ProjectDescription> descriptions = project.getDescriptions();
		if (descriptions != null) {
			Set<ProjectDescription> newDescriptions = new HashSet<ProjectDescription>();
			for (ProjectDescription description : descriptions) {
				newDescriptions.add(clone(description));
			}
			newProject.setDescriptions(newDescriptions);
		}

		// Client
		Client client = project.getClient();
		if (client != null) {
			newProject.setClient(clone(client));
		}
		return newProject;
	}

	private Client clone(Client client) {
		Client newClient = new Client();
		Long id = client.getId();
		if (id == null) {
			id = getId();
		}
		newClient.setId(id);
		newClient.setName(client.getName());
		return newClient;
	}

	private ProjectDescription clone(ProjectDescription description) {
		ProjectDescription newDescription = new ProjectDescription();
		Long id = description.getId();
		if (id == null) {
			id = getId();
		}
		newDescription.setId(id);
		newDescription.setDescription(description.getDescription());
		newDescription.setType(description.getType());
		return newDescription;
	}

	public Page<Project> findByNameLike(String name, Pageable pageable) {
		List<Project> projects = findByNameLike(name);
		return PageListHelper.createPage(projects, pageable);
	}

	public List<Project> findByNameLike(String name) {
		name = Utils.getCriteria(name);
		Iterable<Project> allProjects = findAll();
		List<Project> filteredList = new ArrayList<Project>();
		for (Project project : allProjects) {
			if (isProjectOK(project, name)) {
				filteredList.add(project);
			}
		}
		return filteredList;
	}

	private boolean isProjectOK(Project project, String label) {
		if (label == null) {
			return true;
		}
		if (project.getName() == null) {
			return false;
		}
		return project.getName().toUpperCase().startsWith(label.toUpperCase());
	}

}
