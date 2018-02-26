package com.scau.pbook.bean;

import javafx.beans.property.SimpleStringProperty;

/**
 * ��ϵ��ģ��
 * 
 * @author Administrator
 *
 */

public class AddressBean {
	private SimpleStringProperty name = new SimpleStringProperty();// ��ϵ������
	private SimpleStringProperty telephone = new SimpleStringProperty();// ��ϵ�˵绰
	private SimpleStringProperty mobilephone = new SimpleStringProperty();// �ֻ�����
	private SimpleStringProperty email = new SimpleStringProperty();// ��������
	private SimpleStringProperty birthday = new SimpleStringProperty();// ����
	private SimpleStringProperty workplace = new SimpleStringProperty();// ������λ
	private SimpleStringProperty address = new SimpleStringProperty();// ��ͥסַ
	private SimpleStringProperty postcode = new SimpleStringProperty();// �ʱ�
	private SimpleStringProperty group = new SimpleStringProperty();// ������
	private SimpleStringProperty remarks = new SimpleStringProperty();// ��ע

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
