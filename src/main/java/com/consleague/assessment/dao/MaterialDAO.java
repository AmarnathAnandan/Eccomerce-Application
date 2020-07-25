package com.consleague.assessment.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.consleague.assessment.entity.MaterialDetails;
import com.consleague.assessment.model.MaterialDetailInfo;
import com.consleague.assessment.pagination.PaginationResult;

@Transactional
@Repository
public class MaterialDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public MaterialDetails findOrder(String detailsId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(MaterialDetails.class, detailsId);
	}

//	private int getMaxOrderNum() {
//		String sql = "Select max(o.orderNum) from " + Order.class.getName() + " o ";
//		Session session = this.sessionFactory.getCurrentSession();
//		Query<Integer> query = session.createQuery(sql, Integer.class);
//		Integer value = query.getSingleResult();
//		if (value == null)
//			return 0;
//		return value;
//	}

	public MaterialDetailInfo getDetailInfo(String detailsId) {
		MaterialDetails details = this.findOrder(detailsId);
		if (details == null)
			return null;
		return new MaterialDetailInfo(details.getMaterialId(), details.getMaterialName(), details.getMaterialCost(),
				details.getMaterialColor(), details.getMaterialThreshold(), details.getMaterialQuantity());

	}

//	public List<MaterialDetailInfo> listMAterialDetailInfos(String detailsId) {
//		String sql = "Select new " + MaterialDetailInfo.class.getName() //
//				+ "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
//				+ " from " + MaterialDetails.class.getName() + " d "//
//				+ " where d.order.id = :orderId ";
//
//		Session session = this.sessionFactory.getCurrentSession();
//		Query<OrderDetailInfo> query = session.createQuery(sql, OrderDetailInfo.class);
//		query.setParameter("orderId", orderId);
//
//		return query.getResultList();
//	}
//
	// @page = 1, 2, ...
	public PaginationResult<MaterialDetailInfo> listDetailInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + MaterialDetailInfo.class.getName()//
				+ "(md.materialId, md.materialName, md.materialCost, md.materialColor, md.materialThreshold, md.materialQuantity) "
				+ " from " + MaterialDetails.class.getName() + " md ";//

		Session session = this.sessionFactory.getCurrentSession();
		Query<MaterialDetailInfo> query = session.createQuery(sql, MaterialDetailInfo.class);
		return new PaginationResult<MaterialDetailInfo>(query, page, maxResult, maxNavigationPage);
	}

//	@Transactional(rollbackFor = Exception.class)
//	public void saveOrder(CartInfo cartInfo) {
//		Session session = this.sessionFactory.getCurrentSession();
//
//		int orderNum = this.getMaxOrderNum() + 1;
//		Order order = new Order();
//
//		order.setId(UUID.randomUUID().toString());
//		order.setOrderNum(orderNum);
//		order.setOrderDate(new Date());
//		order.setAmount(cartInfo.getAmountTotal());
//
//		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
//		order.setCustomerName(customerInfo.getName());
//		order.setCustomerEmail(customerInfo.getEmail());
//		order.setCustomerPhone(customerInfo.getPhone());
//		order.setCustomerAddress(customerInfo.getAddress());
//
//		session.persist(order);
//
//		List<CartLineInfo> lines = cartInfo.getCartLines();
//
//		for (CartLineInfo line : lines) {
//			OrderDetail detail = new OrderDetail();
//			detail.setId(UUID.randomUUID().toString());
//			detail.setOrder(order);
//			detail.setAmount(line.getAmount());
//			detail.setPrice(line.getProductInfo().getPrice());
//			detail.setQuanity(line.getQuantity());
//
//			String code = line.getProductInfo().getCode();
//			Product product = this.productDAO.findProduct(code);
//			detail.setProduct(product);
//
//			session.persist(detail);
//		}
//
//		// Order Number!
//		cartInfo.setOrderNum(orderNum);
//		// Flush
//		session.flush();
//	}
}