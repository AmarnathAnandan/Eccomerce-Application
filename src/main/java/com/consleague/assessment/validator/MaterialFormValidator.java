package com.consleague.assessment.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.consleague.assessment.form.MaterialForm;

@Component
public class MaterialFormValidator implements Validator {

	// This validator only checks for the MaterialForm.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == MaterialForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {

		// Check the fields of MaterialForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialName", "NotEmpty.MaterialForm.materialName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialQuantity", "NotEmpty.MaterialForm.materialQuantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialCost", "NotEmpty.MaterialForm.materialCost");

	}

}