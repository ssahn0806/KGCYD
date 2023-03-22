package com.kgc.chatbot.model.skill;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class SkillResponseBuilder {
	private final String version = "2.0";
	
	private final JSONObject response = new JSONObject();
	
	private final JSONObject template = new JSONObject();
	private final JSONArray outputs = new JSONArray();
	private final JSONArray quickReplies = new JSONArray();
	
	public SkillResponseBuilder() {
		response.put("version", version);
		response.put("template", template);
		template.put("outputs",outputs);
		template.put("quickReplies",quickReplies);
	}
	
	public void addOutputs(String type,JSONObject output) {
		JSONObject job = new JSONObject();
		job.put(type,output);
		outputs.add(job);
	}
	
	public void addReplies(QuickReplies reply) {
		quickReplies.add(reply.build());
	}
	

	
//	public void addSimpleText(String text) {
//		SimpleText simpleText = new SimpleText();
//		simpleText.text(text);
//		
//		JSONObject job = new JSONObject();
//		job.put("simpleText",simpleText.build());
//		outputs.add(job);
//	}
//	
//	public void addSimpleImage(String imageUrl,String altText) {
//		SimpleImage simpleImage = new SimpleImage();
//		simpleImage.imageUrl(imageUrl).altText(altText);
//		
//		JSONObject job = new JSONObject();
//		job.put("simpleImage",simpleImage.build());
//		outputs.add(job);
//		
//	}
//	
//	public void addBasicCard() {
//		BasicCard basicCard = new BasicCard();
//		basicCard.title("카드제목입니다.").description("세부내용을 작성하세요").forwardable();
//		basicCard.thumbnail(new Thumbnail().imageUrl("https://img1.daumcdn.net/thumb/C176x176/?fname=https://t1.daumcdn.net/cfile/tistory/2339B63451F063D60A"));
//		basicCard.buttons(new Button().label("전화걸기").action("phone").phoneNumber("010-5116-3268"));
//		basicCard.buttons(new Button().label("브라우저열기").action("webLink").webLinkUrl("https://www.kw.ac.kr"));
//		JSONObject job = new JSONObject();
//		job.put("basicCard",basicCard.build());
//		outputs.add(job);
////		return basicCard.build();
//	}
//	
//	public void addCommerceCard() {
//		CommerceCard commerceCard = new CommerceCard();
//		commerceCard.description("따끈따끈한 보물 상자 팝니다");
//		commerceCard.price(10000);
//		commerceCard.discount(1000);
//		commerceCard.thumbnails(new Thumbnail().imageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg").link(new Link().web("https://store.kakaofriends.com/kr/products/1542")));
//		commerceCard.profile(new Profile().imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4BJ9LU4Ikr_EvZLmijfcjzQKMRCJ2bO3A8SVKNuQ78zu2KOqM").nickname("보물상자 팝니다"));
//		commerceCard.buttons(new Button().label("구매하기").action("webLink").webLinkUrl("https://store.kakaofriends.com/kr/products/1542"));
//		commerceCard.buttons(new Button().label("전화하기").action("phone").webLinkUrl("354-86-00070"));
//		commerceCard.buttons(new Button().label("공유하기").action("share"));
//		
//		JSONObject job = new JSONObject();
//		job.put("commerceCard",commerceCard.build());
//		outputs.add(job);
//	}
//	
//	public void addListCard() {
//		ListCard listCard = new ListCard();
//		listCard.header(new ListItem().title("챗봇 관리자센터를 소개합니다."));
//		listCard.items(new ListItem().title("챗봇 관리자센터").description("새로운 AI의 내일과 일상의 변화").imageUrl("https://t1.kakaocdn.net/openbuilder/sample/img_001.jpg").link(new Link().web("https://namu.wiki/w/%EB%9D%BC%EC%9D%B4%EC%96%B8(%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88)")));
//		
//		JSONObject extra = new JSONObject();
//		extra.put("key1","value1");
//		extra.put("key2","value2");
//		
//		listCard.buttons(new Button().label("구경가기").action("block").blockId("62654c249ac8ed78441532de").extra(extra));
//		
//		JSONObject job = new JSONObject();
//		
//		job.put("listCard",listCard.build());
//		outputs.add(job);
//	}
//	
//	public void addItemCard() {
//		ItemCard itemCard = new ItemCard();
//		itemCard.imageTitle(new ImageTitle().title("DOFQTK").description("Boarding NUmber"));
//		itemCard.title("");
//		itemCard.description("");
//		itemCard.thumbnail(new Thumbnail().imageUrl("http://dev-mk.kaka.com/dn/bot/scripts/with_barcode_blue_1x1.png").width(800).height(800));
//		itemCard.profile(new Profile().title("AA Airline").imageUrl("https://t1.kakaocdn.net/opebuilder/docs_image/aaairline.jpg"));
//		itemCard.itemList(new ItemList().title("Flight").description("KE0605"));
//		itemCard.itemList(new ItemList().title("Boards").description("8:50 AM"));
//		itemCard.itemList(new ItemList().title("Departs").description("9:50 AM"));
//		itemCard.buttons(new Button().label("View Boarding Pass").action("webLink").webLinkUrl("https://namu.wiki/w/%EB%82%98%EC%97%B0(TWICE)"));
//		JSONObject job = new JSONObject();
//		
//		job.put("itemCard",itemCard.build());
//		outputs.add(job);
//	}
//	
//	public void addCarousel() {
//		Carousel carousel = new Carousel();
//		carousel.type("basicCard");
//		carousel.items(makeBasicCard());
//		carousel.items(makeBasicCard());
//		carousel.header(new CarouselHeader().title("카드헤더 예제").thumbnail(new Thumbnail().imageUrl("https://t1.kakaocdn.net/openbuilder/sample/lj3JUcmrzC53YIjNDkqbWK.jpg").link(new Link().web("https"))));
//		JSONObject job = new JSONObject();
//		job.put("carousel",carousel.build());
//		outputs.add(job);
//	}
//	
//	public QuickReplies makeReplies() {
//		QuickReplies qps = new QuickReplies();
//		qps.label("손흥민").action("message").messageText("손흥민");
//		return qps;
//	}
//	
//	public void addQuickReplies() {
//		quickReplies.add(makeReplies().build());
//		quickReplies.add(makeReplies().build());
//		quickReplies.add(makeReplies().build());
//
//	}
	public JSONObject build() {
		return this.response;
	}
}
