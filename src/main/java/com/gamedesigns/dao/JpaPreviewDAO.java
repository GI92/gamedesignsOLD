package com.gamedesigns.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gamedesigns.entities.Preview;

public class JpaPreviewDAO implements PreviewDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Preview get(Long byID) {
		return entityManager.find(Preview.class, byID);
	}

	@Override
	public void add(Preview preview) {
		entityManager.merge(preview);
	}

	@Override
	public void delete(Preview preview) {
		preview = entityManager.find(Preview.class, preview.getID());
		entityManager.remove(preview);
	}

	@Override
	public void update(Preview preview) {
		entityManager.merge(preview);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
