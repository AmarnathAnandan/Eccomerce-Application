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
	private String rawMaterial1;
	private int rawMaterial1Quantity;
	private String rawMaterial2;
	private int rawMaterial2Quantity;

	private String rawMaterial3;

	private int rawMaterial3Quantity;

	public ProductForm() {
		this.newProduct = true;
	}

	public ProductForm(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
		this.rawMaterial1 = product.getRawMaterial1();
		this.rawMaterial1Quantity = product.getRawMaterial1Quantity();
		this.rawMaterial2 = product.getRawMaterial2();
		this.rawMaterial2Quantity = product.getRawMaterial2Quantity();
		this.rawMaterial3 = product.getRawMaterial3();
		this.rawMaterial3Quantity = product.getRawMaterial3Quantity();
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

	public String getRawMaterial1() {
		return rawMaterial1;
	}

	public int getRawMaterial1Quantity() {
		return rawMaterial1Quantity;
	}

	public String getRawMaterial2() {
		return rawMaterial2;
	}

	public int getRawMaterial2Quantity() {
		return rawMaterial2Quantity;
	}

	public String getRawMaterial3() {
		return rawMaterial3;
	}

	public int getRawMaterial3Quantity() {
		return rawMaterial3Quantity;
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

	public void setRawMaterial1(String rawMaterial1) {
		this.rawMaterial1 = rawMaterial1;
	}

	public void setRawMaterial1Quantity(int rawMaterial1Quantity) {
		this.rawMaterial1Quantity = rawMaterial1Quantity;
	}

	public void setRawMaterial2(String rawMaterial2) {
		this.rawMaterial2 = rawMaterial2;
	}

	public void setRawMaterial2Quantity(int rawMaterial2Quantity) {
		this.rawMaterial2Quantity = rawMaterial2Quantity;
	}

	public void setRawMaterial3(String rawMaterial3) {
		this.rawMaterial3 = rawMaterial3;
	}

	public void setRawMaterial3Quantity(int rawMaterial3Quantity) {
		this.rawMaterial3Quantity = rawMaterial3Quantity;
	}

}