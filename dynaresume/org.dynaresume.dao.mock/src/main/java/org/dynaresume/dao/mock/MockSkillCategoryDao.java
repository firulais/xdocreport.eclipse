package org.dynaresume.dao.mock;

import java.util.Map;

import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.stereotype.Repository;

@Repository("skillCategoryDao")
public class MockSkillCategoryDao extends
		AbstractDaoMock<SkillCategory> implements SkillCategoryDao {

	private final Map<Long, SkillCategory> categorys = SkillsData.categories;

	public Iterable<SkillCategory> findAll() {
		return categorys.values();
	}

	public SkillCategory findOne(Long id) {
		SkillCategory category = categorys.get(id);
		if (category != null) {
			return clone(category);
		}
		return categorys.get(id);
	}

	public SkillCategory save(SkillCategory category) {
		if (category.getId() == null) {
			category.setId(SkillsData.getId());
		}
		categorys.put(category.getId(), category);
		return clone(category);
	}

	private SkillCategory clone(SkillCategory category) {
		SkillCategory newSkillCategory = new SkillCategory();
		newSkillCategory.setId(category.getId());
		newSkillCategory.setLabel(category.getLabel());
		newSkillCategory.setParent(category.getParent());
		return newSkillCategory;
	}

}
