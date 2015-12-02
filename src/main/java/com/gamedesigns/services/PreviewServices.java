package com.gamedesigns.services;

import com.gamedesigns.entities.Preview;

public interface PreviewServices {

	Preview get(Long byID);

	void add(Preview preview);

	void delete(Preview preview);

	void update(Preview preview);
}
