package com.kgc.chatbot.model.dto;

import lombok.Data;

@Data
public class ProductInfo {
	private String productCode;
	private String productName;
	private String composition;
	private int price;
	private boolean discount;
	private String startdiscount;
	private String enddiscount;
	private int discountRate;
	private int discountPrice;
	private int totalPrice;
	private String volume;
	private String comment;
	private String detail;
	private String usage;
	private boolean health;
	private int productLevel;
	private boolean sale;
	private String alert;
	
	private ProductCode category;
}
