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
		MaterialForm materialForm = (MaterialForm) target;

		// Check the fields of MaterialForm.

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialId", "NotEmpty.MaterialForm.materialId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialName", "NotEmpty.MaterialForm.materialName");

		if (materialForm.getMaterialQuantity() <= 0)
			errors.rejectValue("materialQuantity", "NotEmpty.MaterialForm.materialQuantity");

		if (materialForm.getMaterialCost() <= 0)
			errors.rejectValue("materialCost", "NotEmpty.MaterialForm.materialCost");

		if (materialForm.getMaterialColor().isEmpty())
			errors.rejectValue("materialColor", "NotEmpty.MaterialForm.materialColor");

		if (materialForm.getMaterialThreshold() <= 5)
			errors.rejectValue("materialThreshold", "NotEmpty.MaterialForm.materialThreshold");

	}

}