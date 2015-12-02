package com.gamedesigns.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import com.gamedesigns.entities.Category;

public class JpaCategoryDAO implements CategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Category get(Long byID) {
		return entityManager.find(Category.class, byID);
	}

	@Override
	public List<Category> getList() {
		return entityManager.createQuery("select c from Category c", Category.class).getResultList();
	}

	@Override
	public void add(Category category) {
		entityManager.merge(category);
	}

	@Override
	public void delete(Category category) {
		category = entityManager.find(Category.class, category.getID());
		entityManager.remove(category);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void update(Category category) {
		entityManager.merge(category);
	}

	@Override
	public Category getWithDesings(Long byID) {
		Category category = entityManager.find(Category.class, byID);
		Hibernate.initialize(category.getDesigns());
		return category;
	}

	@Override
	public boolean exist(Category category) {
		if (category.getID() == null) {
			List<Category> categories = entityManager
					.createQuery("select c from Category c where name = ?", Category.class)
					.setParameter(1, category.getName()).getResultList();
			if (categories != null && categories.size() > 0){
				return true;
			}
		} else {
			List<Category> categories = entityManager
					.createQuery("select c from Category c where name = ? and ID <> ?", Category.class)
					.setParameter(1, category.getName()).setParameter(2, category.getID()).getResultList();
			if (categories != null && categories.size() > 0){
				return true;
			}
		}

		return false;
	}

}
