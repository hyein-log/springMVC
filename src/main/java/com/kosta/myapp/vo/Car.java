package com.kosta.myapp.vo;

public class Car {
	private String model;
	private int price;
	private String color;
	
	public Car() {
		System.out.println("default?��?��?���? ?��?��?��?�� car�? 만듦");
		
	}
	public Car(String model, int price, String color) {
		super();
		this.model = model;
		this.price = price;
		this.color = color;
		System.out.println("argument 3개�? ?��?�� ?��?��?���? ?��?��?��?��.");
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
		System.out.println("setmodel : "+model);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		System.out.println("price : "+price);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
		System.out.println("color : "+color);
	}
	@Override
	public String toString() {
		return "Car [model=" + model + ", price=" + price + ", color=" + color + "]";
	}
	
	
}
