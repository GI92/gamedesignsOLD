package com.gamedesigns.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gamedesigns.entities.GameType;

public class JpaGameTypeDAO implements GameTypeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public GameType get(Long byID) {
		return entityManager.find(GameType.class, byID);
	}

	@Override
	public List<GameType> getList() {
		return entityManager.createQuery("select g from GameType g", GameType.class).getResultList();
	}

	@Override
	public void add(GameType gameType) {
		entityManager.merge(gameType);
	}

	@Override
	public void delete(GameType gameType) {
		gameType = entityManager.find(GameType.class, gameType.getID());
		entityManager.remove(gameType);
	}

	@Override
	public void update(GameType gameType) {
		entityManager.merge(gameType);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean exist(GameType gameType) {
		if (gameType.getID() == null) {
			List<GameType> categories = entityManager
					.createQuery("select c from GameType c where name = ?", GameType.class)
					.setParameter(1, gameType.getName()).getResultList();
			if (categories != null && categories.size() > 0) {
				return true;
			}
		} else {
			List<GameType> categories = entityManager
					.createQuery("select g from GameType g where name = ? and ID <> ?", GameType.class)
					.setParameter(1, gameType.getName()).setParameter(2, gameType.getID()).getResultList();
			if (categories != null && categories.size() > 0) {
				return true;
			}
		}

		return false;
	}

}
