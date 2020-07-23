package com.consleague.assessment.form;

import com.consleague.assessment.model.CustomerInfo;

public class CustomerForm {

	private String address;
	private String email;
	private String name;
	private String phone;

	private boolean valid;

	public CustomerForm() {

	}

	public CustomerForm(CustomerInfo customerInfo) {
		if (customerInfo != null) {
			this.name = customerInfo.getName();
			this.address = customerInfo.getAddress();
			this.email = customerInfo.getEmail();
			this.phone = customerInfo.getPhone();
		}
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isValid() {
		return valid;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}