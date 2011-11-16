package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SkillServiceImpl implements SkillService {

	private SkillDao skillDao;

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	public Collection<Skill> findAll() {
		return skillDao.findAll();
	}

	public Page<Skill> findAll(Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Collection<Skill> allSkills = findAll();
		List<Skill> fullList = new ArrayList<Skill>(allSkills);
		List<Skill> filteredList = fullList;
		long totalSize = filteredList.size();
		List<Skill> paginatedList = new ArrayList<Skill>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Skill skill = filteredList.get(i);
			paginatedList.add(skill);
		}
		return new PageImpl<Skill>(paginatedList, pageable, totalSize);

	}

}
