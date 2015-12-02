package com.gamedesigns.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

import com.gamedesigns.entities.User;

public class JpaUserDAO implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User get(String username, String password) {
		List<User> users = entityManager
				.createQuery("select u from User u where username = ? and password = ?", User.class)
				.setParameter(1, username).setParameter(2, password).getResultList();
		if (users == null || users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public List<User> getList() {
		return entityManager.createQuery("select u from User u", User.class).getResultList();
	}

	@Override
	public void add(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(User user) {
		user = entityManager.find(User.class, user.getID());
		entityManager.remove(user);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public User get(Long byID) {
		return entityManager.find(User.class, byID);
	}

	@Override
	public User getWithDesigns(Long byID) {
		User user = entityManager.find(User.class, byID);
		Hibernate.initialize(user.getDesigns());
		return user;
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public List<User> get(String username, String email, Long ID) {
		return entityManager
				.createQuery("SELECT u FROM User u where (username = ? or email = ?) and ID <> ?", User.class)
				.setParameter(1, username).setParameter(2, email).setParameter(3, ID).getResultList();
	}

}
