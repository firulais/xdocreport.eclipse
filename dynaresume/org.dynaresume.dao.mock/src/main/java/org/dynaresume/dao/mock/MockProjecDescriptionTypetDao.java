package org.dynaresume.dao.mock;

import org.dynaresume.dao.ProjectDescriptionTypeDao;
import org.dynaresume.domain.project.ProjectDescriptionType;
import org.springframework.stereotype.Repository;

@Repository("projectDescriptionTypeDao")
public class MockProjecDescriptionTypetDao extends
		AbstractDaoMock<ProjectDescriptionType> implements
		ProjectDescriptionTypeDao {

	protected ProjectDescriptionType clone(ProjectDescriptionType type) {
		ProjectDescriptionType newType = new ProjectDescriptionType();
		newType.setId(type.getId());
		newType.setLabel(type.getLabel());
		return newType;
	}

}
