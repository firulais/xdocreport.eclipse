package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.domain.hr.SkillCategory;
import org.dynaresume.services.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("skillCategoryService")
public class SkillCategoryServiceImpl implements SkillCategoryService {

	@Autowired
	private SkillCategoryDao categoryDao;

	public void setSkillCategoryDao(SkillCategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Iterable<SkillCategory> findAll() {
		return categoryDao.findAll();
	}
	
	public Iterable<SkillCategory> findAllRoot() {
		// TODO : manage criteria with parent null in the DAO
		List<SkillCategory> rootCategories=new ArrayList<SkillCategory>();
		Iterable<SkillCategory> categories= findAll();
		for (SkillCategory skillCategory : categories) {
			if (skillCategory.getParent()== null) {
				rootCategories.add(skillCategory);
			}
		}
		return rootCategories;
	}

	public SkillCategory findById(long id) {
		return categoryDao.findOne(id);
	}

	public SkillCategory save(SkillCategory category) {
		return categoryDao.save(category);
	}

	public Page<SkillCategory> findAll(Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<SkillCategory> allSkillCategorys = findAll();
		List<SkillCategory> fullList = new ArrayList<SkillCategory>();
		for (SkillCategory category : allSkillCategorys) {
			fullList.add(category);
		}

		List<SkillCategory> filteredList = fullList;
		long totalSize = filteredList.size();
		List<SkillCategory> paginatedList = new ArrayList<SkillCategory>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			SkillCategory category = filteredList.get(i);
			paginatedList.add(category);
		}
		return new PageImpl<SkillCategory>(paginatedList, pageable, totalSize);
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

}
