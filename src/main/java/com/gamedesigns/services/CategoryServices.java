package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.entities.Category;

public interface CategoryServices {
	Category get(Long byID);

	List<Category> getList();

	boolean exist(Category category);
	
	void add(Category category);

	void delete(Category category);

	void update(Category category);

	Category getWithDesings(Long byID);
}
