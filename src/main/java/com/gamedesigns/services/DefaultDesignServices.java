package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.dao.DesignDAO;
import com.gamedesigns.entities.Category;
import com.gamedesigns.entities.Design;

public class DefaultDesignServices implements DesignServices{

	private DesignDAO designDAO;
	
	public void setDesignDAO(DesignDAO designDAO) {
		this.designDAO = designDAO;
	}

	@Override
	public Design get(Long byID) {
		return designDAO.get(byID);
	}

	@Override
	public List<Design> getList() {
		return designDAO.getList();
	}

	@Override
	public void add(Design design) {
		designDAO.add(design);
	}

	@Override
	public void delete(Design design) {
		designDAO.delete(design);
	}

	@Override
	public void update(Design design) {
		designDAO.update(design);
	}

	@Override
	public List<Design> getFiltredList(Category category, List<Long> categoryIDS) {
		return designDAO.getFiltredList(category, categoryIDS);
	}

	@Override
	public Design getWithPreview(Long byID) {
		return designDAO.getWithPreview(byID);
	}

	@Override
	public Design getComplete(Long byID) {
		return designDAO.getComplete(byID);
	}

	@Override
	public boolean exist(Design design) {
		return designDAO.exist(design);
	}
	
	
	
}
