package com.oauth.commons.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name = "profiles")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "firstname", "lastname", "zipcode"})  //No I18N
public class Profile {

	@Id
	@Column(name = "id")
	@XmlElement(name = "id")
	@FormParam("id")
	private int id;
	
	@Column(name = "firstname")
	@XmlElement(name = "first_name")
	@FormParam("first_name")
	private String firstName;
	
	@Column(name = "lastname", nullable = true)
	@XmlElement(name = "last_name")
	@FormParam("last_name")
	private String lastName;
	
	@Column(name = "zipcode")
	@XmlElement(name = "zipcode")
	@FormParam("zipcode")
	private String zipcode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", zipcode=" + zipcode
				+ "]";
	}
	
	
	
}
