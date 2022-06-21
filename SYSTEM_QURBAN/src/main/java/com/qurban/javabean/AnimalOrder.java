package com.qurban.javabean;

public class AnimalOrder {
	private int animalOrderID;
	private String animalOrderType;
	private double animalOrderPrice;
	private String dependentName;
	private String supplierName;
	private String bookingID;
	
	public AnimalOrder(int animalOrderID, String animalOrderType, double animalOrderPrice, String dependentName,
			String supplierName, String bookingID) {
		super();
		this.animalOrderID = animalOrderID;
		this.animalOrderType = animalOrderType;
		this.animalOrderPrice = animalOrderPrice;
		this.dependentName = dependentName;
		this.supplierName = supplierName;
		this.bookingID = bookingID;
	}

	public AnimalOrder(String animalOrderType, double animalOrderPrice, String dependentName, String supplierName) {
		super();
		this.animalOrderType = animalOrderType;
		this.animalOrderPrice = animalOrderPrice;
		this.dependentName = dependentName;
		this.supplierName = supplierName;
	}
	

	public AnimalOrder(int animalOrderID, String animalOrderType, double animalOrderPrice, String dependentName, String supplierName) {
		super();
		this.animalOrderID = animalOrderID;
		this.animalOrderType = animalOrderType;
		this.animalOrderPrice = animalOrderPrice;
		this.dependentName = dependentName;
		this.supplierName = supplierName;
	}

	//..
	
	public int getAnimalOrderID() {
		return animalOrderID;
	}

	public void setAnimalOrderID(int animalOrderID) {
		this.animalOrderID = animalOrderID;
	}

	public String getAnimalOrderType() {
		return animalOrderType;
	}

	public void setAnimalOrderType(String animalOrderType) {
		this.animalOrderType = animalOrderType;
	}

	public double getAnimalOrderPrice() {
		return animalOrderPrice;
	}

	public void setAnimalOrderPrice(double animalOrderPrice) {
		this.animalOrderPrice = animalOrderPrice;
	}

	public String getDependentName() {
		return dependentName;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBookingID() {
		return bookingID;
	}

	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	
	
	
	
}
