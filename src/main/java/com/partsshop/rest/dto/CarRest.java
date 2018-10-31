package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;

public class CarRest {
	
	
	private String id;
	@NotBlank // for validation.
	private String make ; 
	@NotBlank
	private String type ; 
	private String makeAr ; 
	private String typeAr;
	
	public CarRest() {} 
	
	public CarRest(String id, String make, String type, String makeAr, String typeAr) {
		this.id=id ; 
		this.make = make  ; 
		this.type = type ; 
		this.makeAr = makeAr ; 
		this.typeAr = typeAr ; 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMakeAr() {
		return makeAr;
	}
	public void setMakeAr(String makeAr) {
		this.makeAr = makeAr;
	}
	public String getTypeAr() {
		return typeAr;
	}
	public void setTypeAr(String typeAr) {
		this.typeAr = typeAr;
	}
	
	

}
