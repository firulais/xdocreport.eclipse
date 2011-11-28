package org.dynaresume.dao.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository("skillCategoryDao")
public class MockSkillCategoryDao extends AbstractDaoMock<SkillCategory>
		implements SkillCategoryDao {

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

	public Page<SkillCategory> findByLabel(String label, Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<SkillCategory> allSkillCategorys = findAll();
		List<SkillCategory> filteredList = new ArrayList<SkillCategory>();
		for (SkillCategory category : allSkillCategorys) {
			if (isSkillCategoryOK(category, label)) {
				filteredList.add(category);
			}
		}
		long totalSize = filteredList.size();
		List<SkillCategory> paginatedList = new ArrayList<SkillCategory>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			SkillCategory category = filteredList.get(i);
			paginatedList.add(category);
		}
		return new PageImpl<SkillCategory>(paginatedList, pageable, totalSize);
	}

	private boolean isSkillCategoryOK(SkillCategory category, String label) {
		if (label == null) {
			return true;
		}
		if (category.getLabel() == null) {
			return false;
		}
		return category.getLabel().toUpperCase()
				.startsWith(label.toUpperCase());
	}

	public Iterable<SkillCategory> findByParentIsNull() {
		// TODO : manage criteria with parent null in the DAO
		List<SkillCategory> rootCategories = new ArrayList<SkillCategory>();
		Iterable<SkillCategory> categories = findAll();
		for (SkillCategory skillCategory : categories) {
			if (skillCategory.getParent() == null) {
				rootCategories.add(skillCategory);
			}
		}
		return rootCategories;
	}

}
