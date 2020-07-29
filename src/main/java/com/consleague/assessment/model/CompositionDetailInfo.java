package com.consleague.assessment.model;

public class CompositionDetailInfo {
	private int compId;
	private String productCode;
	private int productMaterialQuantity;
	private int materialId;

	public CompositionDetailInfo(int compId, String productCode, int productMaterialQuantity, int materialId) {
		super();
		this.compId = compId;
		this.productCode = productCode;
		this.productMaterialQuantity = productMaterialQuantity;
		this.materialId = materialId;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getProductMaterialQuantity() {
		return productMaterialQuantity;
	}

	public void setProductMaterialQuantity(int productMaterialQuantity) {
		this.productMaterialQuantity = productMaterialQuantity;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

}