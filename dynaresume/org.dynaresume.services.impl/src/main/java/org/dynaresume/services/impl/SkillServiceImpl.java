package org.dynaresume.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("skillService")
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillDao skillDao;

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	public Iterable<Skill> findAll() {
		return skillDao.findAll();
	}

	public Skill findById(long id) {
		return skillDao.findOne(id);
	}

	public Skill save(Skill skill) {
		return skillDao.save(skill);
	}

	public Page<Skill> findAll(Pageable pageable) {
		// TODO : manage pagination with the DAO
		int pageSize = pageable.getPageSize();
		int pageIndex = pageable.getOffset();
		Iterable<Skill> allSkills = findAll();
		List<Skill> fullList = new ArrayList<Skill>();
		for (Skill skill : allSkills) {
			fullList.add(skill);
		}

		List<Skill> filteredList = fullList;
		long totalSize = filteredList.size();
		List<Skill> paginatedList = new ArrayList<Skill>();
		for (int i = pageIndex; i < pageIndex + pageSize && i < totalSize; i++) {
			Skill skill = filteredList.get(i);
			paginatedList.add(skill);
		}
		return new PageImpl<Skill>(paginatedList, pageable, totalSize);
	}

	public Page<Skill> findByName(String name, Pageable pageable) {
		name = name != null ? name + "%" : "%";
		return skillDao.findByNameLike(name, pageable);
	}

	public Iterable<Skill> findByNames(List<String> names) {
		// TODO :manage that in the DAO
		List<Skill> filteredSkills = new ArrayList<Skill>();
		Iterable<Skill> allSkills = findAll();
		for (Skill skill : allSkills) {
			for (String name : names) {
				if (skill.getName().equalsIgnoreCase(name)) {
					filteredSkills.add(skill);
					break;
				}
			}
		}
		return filteredSkills;
	}
}
