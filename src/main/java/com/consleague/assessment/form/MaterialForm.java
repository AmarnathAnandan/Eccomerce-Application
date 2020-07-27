package com.consleague.assessment.form;

import java.sql.Timestamp;

import com.consleague.assessment.entity.MaterialDetails;

public class MaterialForm {

	private String materialColor;
	private double materialCost;
	private int materialId;
	private String materialName;
	private int materialQuantity;
	private int materialThreshold;
	private Timestamp lastModified;

	private boolean newMaterial = false;

	public MaterialForm() {
		this.newMaterial = true;
	}

	public boolean isNewMaterial() {
		return newMaterial;
	}

	public void setNewMaterial(boolean newMaterial) {
		this.newMaterial = newMaterial;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public MaterialForm(MaterialDetails materials) {
		this.materialName = materials.getMaterialName();
		this.materialId = materials.getMaterialId();

		this.materialColor = materials.getMaterialColor();
		this.materialCost = materials.getMaterialCost();
		this.materialQuantity = materials.getMaterialQuantity();
		this.materialThreshold = materials.getMaterialThreshold();
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

	public MaterialForm(String materialColor, double materialCost, int materialId, String materialName,
			int materialQuantity, int materialThreshold, Timestamp lastModified) {
		super();
		this.materialColor = materialColor;
		this.materialCost = materialCost;
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialQuantity = materialQuantity;
		this.materialThreshold = materialThreshold;
		this.lastModified = lastModified;
	}

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
