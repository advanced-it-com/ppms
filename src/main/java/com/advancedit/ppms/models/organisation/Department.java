package com.advancedit.ppms.models.organisation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.advancedit.ppms.models.person.ShortPerson;

@Document(collection = "departements")
public class Department {

	@Id
	private String id;
	private String name;
	private String code;
	private String description;
	private String longDescription;
	private String email;
	private String type;
	private String subType;
	private Address address;
	private String phone;
	private String contactEmail;
	private ShortPerson responsible;
	private List<Sector> sectors = new ArrayList<>();
    
    
	public Department() {
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLongDescription() {
		return longDescription;
	}



	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getContactEmail() {
		return contactEmail;
	}



	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public ShortPerson getResponsible() {
		return responsible;
	}

	public void setResponsible(ShortPerson responsible) {
		this.responsible = responsible;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public List<Sector> getSectors() {
		return sectors;
	}



	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
