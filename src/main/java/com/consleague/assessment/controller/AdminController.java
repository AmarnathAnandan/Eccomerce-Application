package com.consleague.assessment.controller;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.consleague.assessment.dao.CompositionDetailsDAO;
import com.consleague.assessment.dao.MaterialDAO;
import com.consleague.assessment.dao.OrderDAO;
import com.consleague.assessment.dao.ProductDAO;
import com.consleague.assessment.entity.MaterialDetails;
import com.consleague.assessment.entity.Product;
import com.consleague.assessment.form.MaterialForm;
import com.consleague.assessment.form.ProductForm;
import com.consleague.assessment.model.MaterialDetailInfo;
import com.consleague.assessment.model.OrderDetailInfo;
import com.consleague.assessment.model.OrderInfo;
import com.consleague.assessment.pagination.PaginationResult;
import com.consleague.assessment.validator.MaterialFormValidator;
import com.consleague.assessment.validator.ProductFormValidator;

@Controller
@Transactional
public class AdminController {

	@Autowired
	private CompositionDetailsDAO compositionDetailsDAO;

	@Autowired
	private MaterialDAO materialDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductFormValidator productFormValidator;

	@Autowired
	private MaterialFormValidator materialFormValidator;

	@RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}

	@RequestMapping(value = { "/admin/materialList" }, method = RequestMethod.GET)
	public String getMaterialList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<MaterialDetailInfo> paginationResult //
				= materialDAO.listDetailInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "materialDetails";
	}

	// GET: Show Login Page
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null)
			return;
		System.out.println("Target=" + target);

		if (target.getClass() == ProductForm.class)
			dataBinder.setValidator(productFormValidator);

		if (target.getClass() == MaterialForm.class)
			dataBinder.setValidator(materialFormValidator);
	}

	@RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<OrderInfo> paginationResult //
				= orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "orderList";
	}

	@RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null)
			orderInfo = this.orderDAO.getOrderInfo(orderId);
		if (orderInfo == null)
			return "redirect:/admin/orderList";
		List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
		orderInfo.setDetails(details);

		model.addAttribute("orderInfo", orderInfo);

		return "order";
	}

	// GET: Show product.
	@RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductForm productForm = null;

		if (code != null && code.length() > 0) {
			Product product = productDAO.findProduct(code);
			if (product != null)
				productForm = new ProductForm(product);
		}
		if (productForm == null) {
			productForm = new ProductForm();
			productForm.setNewProduct(true);
		}

//		List<MaterialDetailInfo> materialNameList = materialDAO.getRawMaterialListInfo();
//		model.addAttribute("materialNameList", materialNameList);
		model.addAttribute("productForm", productForm);
		return "product";
	}

	// POST: Save product
	@RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
	public String productSave(Model model, //
			@ModelAttribute("productForm") @Validated ProductForm productForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			List<MaterialDetailInfo> materialNameList = materialDAO.getRawMaterialListInfo();
			model.addAttribute("materialNameList", materialNameList);
			model.addAttribute("productForm", productForm);
			return "product";
		}
		try {
			productDAO.save(productForm);
			compositionDetailsDAO.save(productForm);

		} catch (Exception e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			String message = rootCause.getMessage();
			model.addAttribute("errorMessage", message);
			// Show product form.
			return "product";
		}

		return "redirect:/productList";
	}

	// GET: Show product.
	@RequestMapping(value = { "/admin/materialEdit" }, method = RequestMethod.GET)
	public String materialEdit(Model model, @RequestParam(value = "materialId", defaultValue = "1") int materialId) {

		MaterialForm materialForm = null;

		if (materialId > 1) {
			MaterialDetails details = materialDAO.findOrder(materialId);
			if (details != null)
				materialForm = new MaterialForm(details);
		}
		if (materialForm == null) {
			materialForm = new MaterialForm();
			materialForm.setNewMaterial(true);
		}

//			List<MaterialDetailInfo> materialNameList = materialDAO.getRawMaterialListInfo();
//			model.addAttribute("materialNameList", materialNameList);
		model.addAttribute("materialForm", materialForm);
		return "materialEdit";
	}

	// POST: Save product
	@RequestMapping(value = { "/admin/materialEdit" }, method = RequestMethod.POST)
	public String materialEditSave(Model model, //
			@ModelAttribute("materialForm") @Validated MaterialForm materialForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("materialForm", materialForm);
			return "materialEdit";
		}
		try {
			materialDAO.save(materialForm);
		} catch (Exception e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			String message = rootCause.getMessage();
			model.addAttribute("errorMessage", message);
			// Show product form.
			return "materialEdit";
		}

		return "redirect:/admin/materialList";
	}

}