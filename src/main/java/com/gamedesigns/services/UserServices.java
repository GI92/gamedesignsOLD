package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.entities.User;

public interface UserServices {

	User get(Long byID);

	User get(String username, String password);
	
	List<User> get(String username, String email, Long ID);

	List<User> getList();

	void add(User user);

	void delete(User user);

	boolean isValidUser(String username, String password);

	User getWithDesings(Long byID);
	
	void update(User user);
}
