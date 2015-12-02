package com.gamedesigns.services;

import com.gamedesigns.dao.PreviewDAO;
import com.gamedesigns.entities.Preview;

public class DefaultPreviewServices implements PreviewServices{

	private PreviewDAO previewDAO;
	
	public void setPreviewDAO(PreviewDAO previewDAO) {
		this.previewDAO = previewDAO;
	}
	
	@Override
	public Preview get(Long byID) {
		return previewDAO.get(byID);
	}

	@Override
	public void add(Preview preview) {
		previewDAO.add(preview);
	}

	@Override
	public void delete(Preview preview) {
		previewDAO.delete(preview);
	}

	@Override
	public void update(Preview preview) {
		previewDAO.update(preview);
	}

}
