package com.gamedesigns.dao.validator;

public interface ValidatorDAO {

	public boolean exist(String tableName, String columnName, String value);
	
}
