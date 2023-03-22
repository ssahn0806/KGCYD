package com.kgc.chatbot.model.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kgc.chatbot.model.skill.BasicCard;
import com.kgc.chatbot.model.skill.Carousel;
import com.kgc.chatbot.model.skill.CommerceCard;
import com.kgc.chatbot.model.skill.ItemCard;
import com.kgc.chatbot.model.skill.ListCard;
import com.kgc.chatbot.model.skill.QuickReplies;
import com.kgc.chatbot.model.skill.SimpleImage;
import com.kgc.chatbot.model.skill.SimpleText;

@Service
public class SkillServiceImpl implements SkillService{
	
	@Override
	public SimpleText makeSimpleText() {
		return new SimpleText();
	}
	
	@Override
	public SimpleImage makeSimpleImage() {
		return new SimpleImage();
	}
	
	@Override
	public BasicCard makeBasicCard() {
		return new BasicCard();
	}
	
	@Override
	public CommerceCard makeCommerceCard() {
		return new CommerceCard();
	}
	
	@Override
	public ListCard makeListCard() {
		return new ListCard();
	}
	
	@Override
	public ItemCard makeItemCard() {
		return new ItemCard();
	}
	
	@Override
	public Carousel makeCarousel() {
		return new Carousel();
	}
	
	@Override
	public QuickReplies makeQuickReplies() {
		return new QuickReplies();
	}

	@Override
	public RestTemplate makeRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public HttpHeaders makeHeaders() {
		return new HttpHeaders();
	}
}
