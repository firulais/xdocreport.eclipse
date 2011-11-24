package org.dynaresume.dao.jpa;

import java.util.Collection;
import java.util.Map;

import org.dynaresume.dao.SkillDao;
import org.dynaresume.domain.hr.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class JpaSkillDao implements SkillDao {

	private final Map<Long, Skill> skills = SkillsData.skills;

	public Collection<Skill> findAll() {
		return skills.values();
	}

	public Skill findById(long id) {
		Skill skill = skills.get(id);
		if (skill != null) {
			return clone(skill);
		}
		return skills.get(id);
	}

	public Skill save(Skill skill) {
		if (skill.getId() == null) {
			skill.setId(SkillsData.getId());
		}
		skills.put(skill.getId(), skill);
		return clone(skill);
	}

	private Skill clone(Skill skill) {
		Skill newSkill = new Skill();
		newSkill.setId(skill.getId());
		newSkill.setName(skill.getName());
		newSkill.setParent(skill.getParent());
		return newSkill;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Skill arg0) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Iterable<? extends Skill> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Skill findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Skill> save(Iterable<? extends Skill> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Skill> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<Skill> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
