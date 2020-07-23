package com.consleague.assessment.model;

import com.consleague.assessment.form.CustomerForm;

public class CustomerInfo {

	private String address;
	private String email;
	private String name;
	private String phone;

	private boolean valid;

	public CustomerInfo() {

	}

	public CustomerInfo(CustomerForm customerForm) {
		this.name = customerForm.getName();
		this.address = customerForm.getAddress();
		this.email = customerForm.getEmail();
		this.phone = customerForm.getPhone();
		this.valid = customerForm.isValid();
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