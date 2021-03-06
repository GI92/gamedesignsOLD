package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.entities.Category;
import com.gamedesigns.entities.Design;

public interface DesignServices {
	Design get(Long byID);

	List<Design> getList();

	void add(Design design);

	void delete(Design design);

	void update(Design design);

	List<Design> getFiltredList(Category category, List<Long> categoryIDS);

	Design getWithPreview(Long byID);
	
	boolean exist(Design design);

	Design getComplete(Long long1);
}
