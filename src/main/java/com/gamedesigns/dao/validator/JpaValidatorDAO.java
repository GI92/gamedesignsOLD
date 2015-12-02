package com.gamedesigns.dao.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaValidatorDAO implements ValidatorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean exist(String tableName, String columnName, String value) {
		List<?> result = entityManager.createQuery("select o from " + tableName + " o where " + columnName + " = ?")
				.setParameter(1, value).getResultList();
		if (result == null || result.isEmpty()) {
			return false;
		}
		return true;
	}

}
