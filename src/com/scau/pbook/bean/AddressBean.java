package com.scau.pbook.bean;

import javafx.beans.property.SimpleStringProperty;

/**
 * 联系人模型
 * 
 * @author Administrator
 *
 */

public class AddressBean {
	private SimpleStringProperty name = new SimpleStringProperty();// 联系人姓名
	private SimpleStringProperty telephone = new SimpleStringProperty();// 联系人电话
	private SimpleStringProperty mobilephone = new SimpleStringProperty();// 手机号码
	private SimpleStringProperty email = new SimpleStringProperty();// 电子邮箱
	private SimpleStringProperty birthday = new SimpleStringProperty();// 生日
	private SimpleStringProperty workplace = new SimpleStringProperty();// 工作单位
	private SimpleStringProperty address = new SimpleStringProperty();// 家庭住址
	private SimpleStringProperty postcode = new SimpleStringProperty();// 邮编
	private SimpleStringProperty group = new SimpleStringProperty();// 所属组
	private SimpleStringProperty remarks = new SimpleStringProperty();// 备注

	public AddressBean() {
		super();
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);;
	}

	public String getTelephone() {
		return telephone.get();
	}

	public void setTelephone(String telephone) {
		this.telephone.set(telephone);
	}

	public String getMobilephone() {
		return mobilephone.get();
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone.set(mobilephone);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getBirthday() {
		return birthday.get();
	}

	public void setBirthday(String birthday) {
		this.birthday.set(birthday);
	}

	public String getWorkplace() {
		return workplace.get();
	}

	public void setWorkplace(String workplace) {
		this.workplace.set(workplace);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public String getPostcode() {
		return postcode.get();
	}

	public void setPostcode(String postcode) {
		this.postcode.set(postcode);
	}

	public String getGroup() {
		return group.get();
	}

	public void setGroup(String group) {
		this.group.set(group);
	}

	public String getRemarks() {
		return remarks.get();
	}

	public void setRemarks(String remarks) {
		this.remarks.set(remarks);
	}

	@Override
	public String toString() {
		return "AddressBean [name=" + name + ", telephone=" + telephone + ", mobilephone=" + mobilephone + ", email="
				+ email + ", birthday=" + birthday + ", workplace=" + workplace + ", address=" + address + ", postcode="
				+ postcode + ", group=" + group + ", remarks=" + remarks + "]";
	}
	
	

}
