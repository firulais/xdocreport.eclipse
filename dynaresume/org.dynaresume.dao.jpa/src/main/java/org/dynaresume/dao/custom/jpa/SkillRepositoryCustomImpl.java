package org.dynaresume.dao.custom.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.dynaresume.dao.custom.SkillRepositoryCustom;
import org.dynaresume.domain.hr.Skill;
import org.springframework.stereotype.Repository;
@Repository("skillRepositoryCustom")
public class SkillRepositoryCustomImpl implements SkillRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Iterable<Skill> findByNames(List<String> names) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Skill> criteriaQuery = criteriaBuilder.createQuery(Skill.class);
		Root<Skill> from = criteriaQuery.from(Skill.class);
		CriteriaQuery<Skill> select = criteriaQuery.select(from);
		Expression<String> strExpression=from.get("name");
		
		for (String name : names) {
			select.where(criteriaBuilder.like(strExpression, name));
		}
		TypedQuery<Skill> typedQuery = em.createQuery(select);
		List<Skill> resultList = typedQuery.getResultList();
		return resultList;
	}

}
