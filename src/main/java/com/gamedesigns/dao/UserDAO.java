package com.gamedesigns.dao;

import java.util.List;

import com.gamedesigns.entities.User;

public interface UserDAO {

	User get(String username, String password);

	List<User> get(String username, String email, Long ID);
	
	List<User> getList();

	void add(User user);

	void delete(User user);

	User get(Long byID);

	User getWithDesigns(Long byID);

	void update(User user);

}
