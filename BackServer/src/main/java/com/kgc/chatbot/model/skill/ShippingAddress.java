package com.kgc.chatbot.model.skill;

import lombok.Data;

@Data
public class ShippingAddress {
	long id;
	String baseAddress;
	String detailAddress;
	String receiverName;
	String receiverMobileNumber;
	String postNo;
	String zoneCode;
}
