package com.consleague.assessment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Material_Details")
public class MaterialDetails implements Serializable {

	private static final long serialVersionUID = 7550745928843183535L;

	@Column(name = "material_color", nullable = false)
	private String materialColor;

	@Column(name = "material_cost")
	private double materialCost;

	@Id
	@Column(name = "material_id", nullable = false)
	private int materialId;

	@Lob
	@Column(name = "material_image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] materialImage;

	@Column(name = "material_name", nullable = false)
	private String materialName;

	@Column(name = "material_quantity", nullable = false)
	private int materialQuantity;

	@Column(name = "material_threshold", nullable = false)
	private int materialThreshold;

	public String getMaterialColor() {
		return materialColor;
	}

	public double getMaterialCost() {
		return materialCost;
	}

	public int getMaterialId() {
		return materialId;
	}

	public byte[] getMaterialImage() {
		return materialImage;
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

	public void setMaterialImage(byte[] materialImage) {
		this.materialImage = materialImage;
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