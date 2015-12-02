package com.gamedesigns.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import com.gamedesigns.entities.Category;
import com.gamedesigns.entities.Design;

public class JpaDesignDAO implements DesignDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Design get(Long byID) {
		return entityManager.find(Design.class, byID);
	}

	@Override
	public List<Design> getList() {
		return entityManager.createQuery("select d from Design d", Design.class).getResultList();
	}

	@Override
	public void add(Design design) {
		entityManager.merge(design);
	}

	@Override
	public void delete(Design design) {
		design = entityManager.find(Design.class, design.getID());
		entityManager.remove(design);
	}

	@Override
	public void update(Design design) {
		entityManager.merge(design);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Design> getFiltredList(Category category, List<Long> categoryIDS) {
		String hql = "select distinct d from Design d " +
                "join d.gameTypes t " +
                "join d.category c " +
                "where t.ID in (:ids) and " +
                "c.ID = (:cid)";
		return entityManager.createQuery(hql, Design.class).setParameter("ids", categoryIDS).setParameter("cid", category.getID()).getResultList();
	}

	@Override
	public Design getWithPreview(Long byID) {
		Design design = entityManager.find(Design.class, byID);
		Hibernate.initialize(design.getImages());
		return design;
	}

	@Override
	public Design getComplete(Long byID) {
		Design design = entityManager.find(Design.class, byID);
		Hibernate.initialize(design.getImages());
		Hibernate.initialize(design.getUser());
		Hibernate.initialize(design.getCategory());
		Hibernate.initialize(design.getGameTypes());
		return design;
	}

	@Override
	public boolean exist(Design design) {
		List<Design> designs = entityManager
				.createQuery("select c from Design c where name = ? and ID <> ?", Design.class)
				.setParameter(1, design.getName()).setParameter(2, design.getID()).getResultList();
		if (designs != null && designs.size() > 0){
			return true;
		}

		return false;
	}

}
