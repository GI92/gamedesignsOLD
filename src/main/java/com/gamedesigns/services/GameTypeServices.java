package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.entities.GameType;

public interface GameTypeServices {
	GameType get(Long byID);

	List<GameType> getList();

	void add(GameType gameType);

	void delete(GameType gameType);

	void update(GameType gameType);

	boolean exist(GameType gameType);
}
