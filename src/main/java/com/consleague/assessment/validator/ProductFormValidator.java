package com.consleague.assessment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.consleague.assessment.dao.ProductDAO;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.form.ProductForm;

@Component
public class ProductFormValidator implements Validator {

	@Autowired
	private ProductDAO productDAO;

	// This validator only checks for the ProductForm.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ProductForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductForm productForm = (ProductForm) target;

		// Check the fields of ProductForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.productForm.code");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.productForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.productForm.price");

		String code = productForm.getCode();
		if (code != null && code.length() > 0)
			if (code.matches("\\s+"))
				errors.rejectValue("code", "Pattern.productForm.code");
			else if (productForm.isNewProduct()) {
				Product product = productDAO.findProduct(code);
				if (product != null)
					errors.rejectValue("code", "Duplicate.productForm.code");
			}

		if (productForm.getRawMaterial1Quantity() <= 0) {
			errors.rejectValue("rawMaterial1Quantity", "NotEmpty.MaterialForm.materialQuantity");

		}

		if (productForm.getRawMaterial2Quantity() <= 0) {
			errors.rejectValue("rawMaterial2Quantity", "NotEmpty.MaterialForm.materialQuantity");

		}

		if (productForm.getRawMaterial3Quantity() <= 0) {
			errors.rejectValue("rawMaterial3Quantity", "NotEmpty.MaterialForm.materialQuantity");

		}

		if (productForm.getRawMaterial1().equals("--") || productForm.getRawMaterial1().isEmpty()
				|| (productForm.getRawMaterial1().equals(productForm.getRawMaterial2())
						|| productForm.getRawMaterial1().equals(productForm.getRawMaterial3())))
			errors.rejectValue("rawMaterial1", "NotEmpty.MaterialForm.materialName");

		if (productForm.getRawMaterial2().equals("--") || productForm.getRawMaterial2().isEmpty()
				|| (productForm.getRawMaterial2().equals(productForm.getRawMaterial1())
						|| productForm.getRawMaterial2().equals(productForm.getRawMaterial3())))
			errors.rejectValue("rawMaterial2", "NotEmpty.MaterialForm.materialName");

		if (productForm.getRawMaterial3().equals("--") || productForm.getRawMaterial3().isEmpty()
				|| (productForm.getRawMaterial3().equals(productForm.getRawMaterial1())
						|| productForm.getRawMaterial3().equals(productForm.getRawMaterial2())))
			errors.rejectValue("rawMaterial3", "NotEmpty.MaterialForm.materialName");

	}

}