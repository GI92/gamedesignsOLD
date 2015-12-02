package com.gamedesigns.services;

import java.util.List;

import com.gamedesigns.dao.GameTypeDAO;
import com.gamedesigns.entities.GameType;

public class DefaultGameTypeServices implements GameTypeServices{

	private GameTypeDAO gameTypeDAO;
	
	public void setGameTypeDAO(GameTypeDAO gameTypeDAO) {
		this.gameTypeDAO = gameTypeDAO;
	}
	
	@Override
	public GameType get(Long byID) {
		return gameTypeDAO.get(byID);
	}

	@Override
	public List<GameType> getList() {
		return gameTypeDAO.getList();
	}

	@Override
	public void add(GameType gameType) {
		gameTypeDAO.add(gameType);
	}

	@Override
	public void delete(GameType gameType) {
		gameTypeDAO.delete(gameType);
	}

	@Override
	public void update(GameType gameType) {
		gameTypeDAO.update(gameType);
	}

	@Override
	public boolean exist(GameType gameType) {
		return gameTypeDAO.exist(gameType);
	}

}
