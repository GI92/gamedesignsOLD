package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.dao.CategoryDAO;
import com.gamedesigns.entities.Category;

public class DefaultCategoryServices implements CategoryServices{

	private CategoryDAO categoryDAO;
	
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	@Override
	public Category get(Long byID) {
		return categoryDAO.get(byID);
	}

	@Override
	public List<Category> getList() {
		return categoryDAO.getList();
	}

	@Override
	public void add(Category category) {
		categoryDAO.add(category);
	}

	@Override
	public void delete(Category category) {
		categoryDAO.delete(category);
	}

	@Override
	public void update(Category category) {
		categoryDAO.update(category);
	}

	@Override
	public Category getWithDesings(Long byID) {
		return categoryDAO.getWithDesings(byID);
	}

	@Override
	public boolean exist(Category category) {
		return categoryDAO.exist(category);
	}

}
