package com.kgc.chatbot.model.dto;

import lombok.Data;

@Data
public class ProductCode {
	private String productCode;
	private String mainName;
	private String subName;
	private String productName;
	private String thumbnail;
	
	private ProductInfo productInfo;
}
