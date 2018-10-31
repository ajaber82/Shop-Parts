  package com.partsshop.rest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cars")
public class Car {
	
	@Id
	private String id;
	private String make ; 
	private String type ; 
	private String makeAr ; 
	private String typeAr;
	
	
	//No Argument Constructor 
	public Car() {} 
	//Argument Constuctor 
	public Car(String id, String make, String type, String makeAr, String typeAr) {
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
