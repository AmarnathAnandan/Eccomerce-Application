package com.consleague.assessment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.consleague.assessment.entity.CompositionDetails;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.form.ProductForm;
import com.consleague.assessment.model.CompositionDetailInfo;

@Transactional
@Repository
public class CompositionDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private MaterialDAO materialDAO;

	public CompositionDetails findOrder(int compId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(CompositionDetails.class, compId);
	}

	public List<CompositionDetailInfo> getMaterialDetailLineList(String productCode) {
		String sql = "Select new " + CompositionDetailInfo.class.getName()//
				+ "(cd.materialID, cd.productMaterialQuantity) " + " from " + CompositionDetails.class.getName()
				+ " cd " + " where cd.productId = :productCode ";

		Session session = this.sessionFactory.getCurrentSession();
		Query<CompositionDetailInfo> query = session.createQuery(sql, CompositionDetailInfo.class);
		query.setParameter("productCode", productCode);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(ProductForm productForm) {

		Session session = this.sessionFactory.getCurrentSession();
		int compId = productForm.getCompid();

		CompositionDetails compDetails = null;

		boolean isNew = false;
		if (compId == 0)
			compDetails = this.findOrder(compId);
		if (compDetails == null) {
			isNew = true;
			compDetails = new CompositionDetails();
		}

//		MaterialDetails materialDetails = materialDAO.findOrder(productForm.getMaterialId());
		Product product = this.productDAO.findProduct(productForm.getCode());

		compDetails.setCompid(productForm.getCompid());
//		compDetails.setMaterialDetails(materialDetails);
		compDetails.setProduct(product);
//		compDetails.setProductMaterialQuanity(productForm.getProductMaterialQuanity());

		if (isNew)
			session.persist(compDetails);
		// If error in DB, Exceptions will be thrown out immediately
		session.flush();
	}

}