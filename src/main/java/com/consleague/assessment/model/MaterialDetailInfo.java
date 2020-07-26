package com.consleague.assessment.model;

import java.sql.Timestamp;
import java.util.Date;

public class MaterialDetailInfo {
	private String materialColor;
	private double materialCost;
	private int materialId;
	private String materialName;
	private int materialQuantity;
	private int materialThreshold;

	private Timestamp lastModified;

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public MaterialDetailInfo(int materialId) {
		this.materialId = materialId;
	}

	public MaterialDetailInfo(int materialId, String materialName, double materialCost, String materialColor,
			int materialThreshold, int materialQuantity, Date lastModified) {
		this.materialColor = materialColor;
		this.materialCost = materialCost;
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialQuantity = materialQuantity;
		this.materialThreshold = materialThreshold;
		this.lastModified = (Timestamp) lastModified;
	}

	public MaterialDetailInfo(double materialCost) {
		this.materialCost = materialCost;
	}

	public MaterialDetailInfo(String materialName) {
		this.materialName = materialName;
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