package com.consleague.assessment.form;

import org.springframework.web.multipart.MultipartFile;

import com.consleague.assessment.entity.Product;

public class ProductForm {
	private String code;
	// Upload file.
	private MultipartFile fileData;
	private String name;

	private boolean newProduct = false;

	private double price;

	public ProductForm() {
		this.newProduct = true;
	}

	public ProductForm(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public String getCode() {
		return code;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}