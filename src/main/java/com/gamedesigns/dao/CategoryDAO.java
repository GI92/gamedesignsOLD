package com.gamedesigns.dao;

import java.util.List;

import com.gamedesigns.entities.Category;

public interface CategoryDAO {

	Category get(Long byID);
	
	Category getWithDesings(Long byID);

	List<Category> getList();

	void add(Category category);

	void delete(Category category);

	void update(Category category);

	boolean exist(Category category);

}
