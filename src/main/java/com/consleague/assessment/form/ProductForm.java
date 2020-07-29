package com.consleague.assessment.form;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.consleague.assessment.dao.CompositionDetailsDAO;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.model.CompositionDetailInfo;

public class ProductForm {

	@Autowired
	private CompositionDetailsDAO compositionDetailsDAO;

	private String code;
	// Upload file.
	private MultipartFile fileData;
	private String name;

	private boolean newProduct = false;
	private double price;

	private int compid;

	private String productCode;

	private Map<String, Integer> rawMaterialDetails;

	public Map<String, Integer> getRawMaterialDetails() {
		return rawMaterialDetails;
	}

	public void setRawMaterialDetails(Map<String, Integer> rawMaterialDetails) {
		this.rawMaterialDetails = rawMaterialDetails;
	}

	public ProductForm() {
		this.newProduct = true;
	}

	List<CompositionDetailInfo> compDetails = compositionDetailsDAO.getMaterialDetailLineList(productCode);

	public ProductForm(Product product) {

		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();

//		this.compid = compDetails.getCompid();
//		this.productCode = compDetails.getProduct().getCode();
//		this.materialId = compDetails.getMaterialDetails().getMaterialId();
//		this.productMaterialQuanity = compDetails.getProductMaterialQuanity();

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}