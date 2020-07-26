package com.consleague.assessment.dao;

import java.io.IOException;
import java.util.Date;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.consleague.assessment.entity.MaterialDetails;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.form.ProductForm;
import com.consleague.assessment.model.MaterialDetailInfo;
import com.consleague.assessment.model.ProductInfo;
import com.consleague.assessment.pagination.PaginationResult;

@Transactional
@Repository
public class ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Product findProduct(String code) {
		try {
			String sql = "Select e from " + Product.class.getName() + " e Where e.code =:code ";

			Session session = this.sessionFactory.getCurrentSession();
			Query<Product> query = session.createQuery(sql, Product.class);
			query.setParameter("code", code);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product == null)
			return null;
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		String sql = "Select new " + ProductInfo.class.getName() //
				+ "(p.code, p.name, p.price) " + " from "//
				+ Product.class.getName() + " p ";
		if (likeName != null && likeName.length() > 0)
			sql += " Where lower(p.name) like :likeName ";
		sql += " order by p.createDate desc ";
		//
		Session session = this.sessionFactory.getCurrentSession();
		Query<ProductInfo> query = session.createQuery(sql, ProductInfo.class);

		if (likeName != null && likeName.length() > 0)
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(ProductForm productForm) {

		Session session = this.sessionFactory.getCurrentSession();
		String code = productForm.getCode();

		Product product = null;

		boolean isNew = false;
		if (code != null)
			product = this.findProduct(code);
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productForm.getName());

		product.setRawMaterial1(productForm.getRawMaterial1());
		product.setRawMaterial1Quantity(productForm.getRawMaterial1Quantity());
		product.setRawMaterial2(productForm.getRawMaterial2());
		product.setRawMaterial2Quantity(productForm.getRawMaterial2Quantity());
		product.setRawMaterial3(productForm.getRawMaterial3());
		product.setRawMaterial3Quantity(productForm.getRawMaterial3Quantity());

		product.setPrice(calculateProductPrice(productForm));

		if (productForm.getFileData() != null) {
			byte[] image = null;
			try {
				image = productForm.getFileData().getBytes();
			} catch (IOException e) {
			}
			if (image != null && image.length > 0)
				product.setImage(image);
		}
		if (isNew)
			session.persist(product);
		// If error in DB, Exceptions will be thrown out immediately
		session.flush();
	}

	public double calculateProductPrice(ProductForm productForm) {
		return getMaterialCost(productForm.getRawMaterial1(), productForm.getRawMaterial1Quantity())
				+ getMaterialCost(productForm.getRawMaterial2(), productForm.getRawMaterial2Quantity())
				+ getMaterialCost(productForm.getRawMaterial3(), productForm.getRawMaterial3Quantity());
	}

	private double getMaterialCost(String rawMaterial, int rawMaterialQuantity) {
		double cost = 0;
		try {
			String sql = "Select new " + MaterialDetailInfo.class.getName()//
					+ "(md.materialCost) " + " from " + MaterialDetails.class.getName() + " md ";//
			sql += " Where md.materialName =:rawMaterial  ";
			Session session = this.sessionFactory.getCurrentSession();
			Query<MaterialDetailInfo> query = session.createQuery(sql, MaterialDetailInfo.class);
			query.setParameter("rawMaterial", rawMaterial);
			cost = query.getSingleResult().getMaterialCost();

		} catch (NoResultException e) {
			System.out.println("error");
		}
		return cost * rawMaterialQuantity;
	}
}