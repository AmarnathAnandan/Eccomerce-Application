package com.consleague.assessment.model;

public class MaterialDetailInfo {
	private String materialColor;
	private double materialCost;
	private int materialId;
//	private MultipartFile materialImage;
	private String materialName;
	private int materialQuantity;
	private int materialThreshold;

//	public MaterialDetailInfo(MaterialDetails materials) {
//		this.materialName = materials.getMaterialName();
//		this.materialId = materials.getMaterialId();
//	}

	public MaterialDetailInfo(int materialId, String materialName, double materialCost, String materialColor,
			int materialThreshold, int materialQuantity) {
		this.materialColor = materialColor;
		this.materialCost = materialCost;
		this.materialId = materialId;
//		this.materialImage = materialImage;
		this.materialName = materialName;
		this.materialQuantity = materialQuantity;
		this.materialThreshold = materialThreshold;
	}

	public String getMaterialColor() {
		return materialColor;
	}

	public double getMaterialCost() {
		return materialCost;
	}

	public int getMaterialId() {
		return materialId;
	}

//	public MultipartFile getMaterialImage() {
//		return materialImage;
//	}

	public String getMaterialName() {
		return materialName;
	}

	public int getMaterialQuantity() {
		return materialQuantity;
	}

	public int getMaterialThreshold() {
		return materialThreshold;
	}

	public void setMaterialColor(String materialColor) {
		this.materialColor = materialColor;
	}

	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

//	public void setMaterialImage(MultipartFile materialImage) {
//		this.materialImage = materialImage;
//	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public void setMaterialQuantity(int materialQuantity) {
		this.materialQuantity = materialQuantity;
	}

	public void setMaterialThreshold(int materialThreshold) {
		this.materialThreshold = materialThreshold;
	}

}