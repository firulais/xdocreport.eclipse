package org.dynaresume.project.eclipse.ui.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.domain.project.ProjectDescriptionType;

public class ProjectDescriptionTypeWrapper {

	private final ProjectDescriptionType type;
	private List<ProjectDescription> descriptions;
	private final Set<ProjectDescription> originalDescriptions;

	public ProjectDescriptionTypeWrapper(ProjectDescriptionType type,
			Set<ProjectDescription> originalDescriptions) {
		this.type = type;
		this.descriptions = null;
		this.originalDescriptions = originalDescriptions;
	}

	public ProjectDescriptionType getType() {
		return type;
	}

	public void addDescription(ProjectDescription description) {
		if (descriptions == null) {
			descriptions = new ArrayList<ProjectDescription>();
		}
		descriptions.add(description);
		if (!originalDescriptions.contains(description)) {
			originalDescriptions.add(description);
		}
	}

	public List<ProjectDescription> getDescriptions() {
		if (descriptions == null) {
			return Collections.emptyList();
		}
		return descriptions;
	}

	public void removeDescription(ProjectDescription projectDescription) {
		if (descriptions != null) {
			descriptions.remove(projectDescription);
		}
		originalDescriptions.remove(projectDescription);
		
	}
}
