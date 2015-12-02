package com.gamedesigns.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gamedesigns.dao.UserDAO;
import com.gamedesigns.entities.User;

@Transactional(readOnly = false)
public class DefaultUserServices implements UserServices{
	
	private UserDAO userDAO;
	
	public User get(String username, String password) {
		return userDAO.get(username, password);
	}
	
	public void setUserDAO(UserDAO userDao) {
		this.userDAO = userDao;
	}

	public User get(Long byID){
		return userDAO.get(byID);
	}
	
	public List<User> getList() {
		return userDAO.getList();
	}

	public void add(User user) {
		userDAO.add(user);
	}

	public void delete(User user) {
		userDAO.delete(user);
	}

	public boolean isValidUser(String username, String password) {
		if (userDAO.get(username, password) == null){
			return false;
		}
		return true;
	}

	@Override
	public User getWithDesings(Long byID) {
		return userDAO.getWithDesigns(byID);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public List<User> get(String username, String email, Long ID) {
		return userDAO.get(username, email, ID);
	}
	
}
