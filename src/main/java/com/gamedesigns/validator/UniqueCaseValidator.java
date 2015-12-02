package com.gamedesigns.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gamedesigns.dao.validator.ValidatorDAO;

@Transactional
public class UniqueCaseValidator implements ConstraintValidator<UniqueCase, String> {

	@Autowired
	private ValidatorDAO validatorDAO;

	private String className = null;
	private String clomunName = null;

	@Override
	public void initialize(UniqueCase constraintAnnotation) {
		this.className = constraintAnnotation.className();
		this.clomunName = constraintAnnotation.columnName();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null)
			return true;
		/*
		 * If validatorDAO is null removed from bean by spring
		 * then result is true
		 */
		if (validatorDAO == null)
			return true;
		return !validatorDAO.exist(className, clomunName, value);
	}

}
