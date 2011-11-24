package org.dynaresume.dao.jpa;

import org.dynaresume.dao.SkillCategoryDao;
import org.dynaresume.domain.hr.SkillCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class JpaSkillCategoryDao implements SkillCategoryDao {

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Long arg0) {
		// TODO Auto-generated method stub

	}

	public void delete(SkillCategory arg0) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<? extends SkillCategory> arg0) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<SkillCategory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public SkillCategory findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public SkillCategory save(SkillCategory arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<SkillCategory> save(Iterable<? extends SkillCategory> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<SkillCategory> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<SkillCategory> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
