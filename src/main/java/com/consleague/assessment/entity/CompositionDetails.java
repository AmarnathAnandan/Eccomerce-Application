package com.consleague.assessment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "composition_details")
public class CompositionDetails implements Serializable {

	private static final long serialVersionUID = 7550745928843183535L;

	@Id
	@Column(name = "comp_id", length = 50, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int compid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, //
			foreignKey = @ForeignKey(name = "COMP_PROD_FK"))
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id", nullable = false, //
			foreignKey = @ForeignKey(name = "COMP_PROD_FK"))
	private MaterialDetails materialDetails;

	@Column(name = "product_material_quantity", nullable = false)
	private int productMaterialQuanity;

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public MaterialDetails getMaterialDetails() {
		return materialDetails;
	}

	public void setMaterialDetails(MaterialDetails materialDetails) {
		this.materialDetails = materialDetails;
	}

	public int getProductMaterialQuanity() {
		return productMaterialQuanity;
	}

	public void setProductMaterialQuanity(int productMaterialQuanity) {
		this.productMaterialQuanity = productMaterialQuanity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}