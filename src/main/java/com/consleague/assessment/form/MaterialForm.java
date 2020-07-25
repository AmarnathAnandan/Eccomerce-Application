package com.consleague.assessment.form;

import com.consleague.assessment.entity.MaterialDetails;

public class MaterialForm {

	private String materialColor;
	private int materialCost;
	private int materialId;
//	private MultipartFile materialImage;
	private String materialName;
	private int materialQuantity;
	private int materialThreshold;

	public MaterialForm(MaterialDetails materials) {
		this.materialName = materials.getMaterialName();
		this.materialId = materials.getMaterialId();
	}

	public String getMaterialColor() {
		return materialColor;
	}

	public int getMaterialCost() {
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

	public void setMaterialCost(int materialCost) {
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
