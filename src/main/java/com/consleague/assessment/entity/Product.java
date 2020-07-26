package com.consleague.assessment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Products")
public class Product implements Serializable {

	private static final long serialVersionUID = -1000119078147252957L;

	@Id
	@Column(name = "Code", length = 20, nullable = false)
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Create_Date", nullable = false)
	private Date createDate;

	@Lob
	@Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	@Column(name = "Name", length = 255, nullable = false)
	private String name;

	@Column(name = "Price", nullable = false)
	private double price;

	@Column(name = "raw_material_1", nullable = false)
	private String rawMaterial1;

	@Column(name = "raw_material_1_quantity", nullable = false)
	private int rawMaterial1Quantity;

	@Column(name = "raw_material_2", nullable = false)
	private String rawMaterial2;

	@Column(name = "raw_material_2_quantity", nullable = false)
	private int rawMaterial2Quantity;

	@Column(name = "raw_material_3", nullable = false)
	private String rawMaterial3;

	@Column(name = "raw_material_3_quantity", nullable = false)
	private int rawMaterial3Quantity;

	public Product() {
	}

	public String getCode() {
		return code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public byte[] getImage() {
		return image;
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

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
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