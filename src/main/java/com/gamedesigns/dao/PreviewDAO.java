package com.gamedesigns.dao;

import com.gamedesigns.entities.Preview;

public interface PreviewDAO {

	Preview get(Long byID);

	void add(Preview preview);

	void delete(Preview preview);

	void update(Preview preview);
}
