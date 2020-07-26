package com.consleague.assessment.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.consleague.assessment.entity.MaterialDetails;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.form.MaterialForm;
import com.consleague.assessment.model.MaterialDetailInfo;
import com.consleague.assessment.pagination.PaginationResult;

@Transactional
@Repository
public class MaterialDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDAO productDAO;

	public MaterialDetails findOrder(int detailsId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(MaterialDetails.class, detailsId);
	}

	public MaterialForm getDetailInfo(int detailsId) {
		MaterialDetails details = this.findOrder(detailsId);
		if (details == null)
			return null;
		return new MaterialForm(details.getMaterialColor(), details.getMaterialCost(), details.getMaterialId(),
				details.getMaterialName(), details.getMaterialQuantity(), details.getMaterialThreshold(),
				details.getLastModified());

	}

	public List<MaterialDetailInfo> getRawMaterialListInfo() {
		String sql = "Select new " + MaterialDetailInfo.class.getName()//
				+ "(md.materialName) " + " from " + MaterialDetails.class.getName() + " md ";//

		Session session = this.sessionFactory.getCurrentSession();
		Query<MaterialDetailInfo> query = session.createQuery(sql, MaterialDetailInfo.class);
		return query.getResultList();
	}

	// @page = 1, 2, ...
	public PaginationResult<MaterialDetailInfo> listDetailInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + MaterialDetailInfo.class.getName()//
				+ "(md.materialId, md.materialName, md.materialCost, md.materialColor, md.materialThreshold, md.materialQuantity, md.lastModified) "
				+ " from " + MaterialDetails.class.getName() + " md ";//

		Session session = this.sessionFactory.getCurrentSession();
		Query<MaterialDetailInfo> query = session.createQuery(sql, MaterialDetailInfo.class);
		return new PaginationResult<MaterialDetailInfo>(query, page, maxResult, maxNavigationPage);
	}

	public Boolean doDeduction(Map<String, Integer> materialMap, Boolean isSave) {
		Boolean takeBack = false;
		List<Boolean> isOk = new ArrayList<Boolean>();
		for (Map.Entry<String, Integer> entry : materialMap.entrySet()) {
			Product product = this.productDAO.findProduct(entry.getKey());

			isOk.add(deduceMaterialQuantityCalculation(entry, product.getRawMaterial1(),
					product.getRawMaterial1Quantity(), isSave));
			isOk.add(deduceMaterialQuantityCalculation(entry, product.getRawMaterial2(),
					product.getRawMaterial2Quantity(), isSave));
			isOk.add(deduceMaterialQuantityCalculation(entry, product.getRawMaterial3(),
					product.getRawMaterial3Quantity(), isSave));
		}

		for (int i = 0; i < isOk.size(); i++) {
			if (!isOk.get(i)) {
				takeBack = true;
				break;
			}
		}
		return takeBack;
	}

	private boolean deduceMaterialQuantityCalculation(Map.Entry<String, Integer> entry, String material, int quanity,
			Boolean isSave) {
		int materialDetailsId = getMaterialId(material);
		MaterialForm mtInfo = getDetailInfo(materialDetailsId);

		int quantity = mtInfo.getMaterialQuantity() - (quanity * entry.getValue());
		if (quantity >= 0 && isSave) {
			mtInfo.setMaterialQuantity(quantity);
			save(mtInfo);
			return true;
		} else if (quantity <= 0 && isSave) {
			return false;
		} else if (quantity <= 0 && !isSave) {
			return false;
		} else {
			return true;
		}

	}

	private int getMaterialId(String materialName) {
		String sql = "Select new " + MaterialDetailInfo.class.getName()//
				+ "(md.materialId) " + " from " + MaterialDetails.class.getName() + " md ";//

		sql += " Where md.materialName =:materialName ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<MaterialDetailInfo> query = session.createQuery(sql, MaterialDetailInfo.class);
		query.setParameter("materialName", materialName);

		return query.getSingleResult().getMaterialId();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void save(MaterialForm materialForm) {

		Session session = this.sessionFactory.getCurrentSession();
		MaterialDetails details = null;

		boolean isNew = false;
		if (materialForm.getMaterialId() != 0)
			details = this.findOrder(materialForm.getMaterialId());
		if (details == null) {
			isNew = true;
			details = new MaterialDetails();
			details.setLastModified((Timestamp) new Date());
		}

		details.setMaterialId(materialForm.getMaterialId());
		details.setMaterialName(materialForm.getMaterialName());
		details.setMaterialCost(materialForm.getMaterialCost());
		details.setMaterialColor(materialForm.getMaterialColor());
		details.setMaterialThreshold(materialForm.getMaterialThreshold());
		details.setMaterialQuantity(materialForm.getMaterialQuantity());

		if (isNew)
			session.persist(details);
		// If error in DB, Exceptions will be thrown out immediately
		session.flush();
	}

}