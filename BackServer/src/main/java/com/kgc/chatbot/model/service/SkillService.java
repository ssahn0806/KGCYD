package com.kgc.chatbot.model.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.kgc.chatbot.model.skill.BasicCard;
import com.kgc.chatbot.model.skill.Carousel;
import com.kgc.chatbot.model.skill.CommerceCard;
import com.kgc.chatbot.model.skill.ItemCard;
import com.kgc.chatbot.model.skill.ListCard;
import com.kgc.chatbot.model.skill.QuickReplies;
import com.kgc.chatbot.model.skill.SimpleImage;
import com.kgc.chatbot.model.skill.SimpleText;

public interface SkillService {

	SimpleText makeSimpleText();

	SimpleImage makeSimpleImage();

	BasicCard makeBasicCard();

	CommerceCard makeCommerceCard();

	ListCard makeListCard();

	ItemCard makeItemCard();

	Carousel makeCarousel();

	QuickReplies makeQuickReplies();

	RestTemplate makeRestTemplate();
	
	HttpHeaders makeHeaders();
}