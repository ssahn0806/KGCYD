package com.kgc.chatbot.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kgc.chatbot.jpa.entity.ClientInfo;
import com.kgc.chatbot.model.dto.Board;
import com.kgc.chatbot.model.dto.MainCode;
import com.kgc.chatbot.model.dto.ProductCode;
import com.kgc.chatbot.model.dto.ProductInfo;
import com.kgc.chatbot.model.dto.SubCode;
import com.kgc.chatbot.model.dto.User;
import com.kgc.chatbot.model.service.BoardService;
import com.kgc.chatbot.model.service.ClientInfoService;
import com.kgc.chatbot.model.service.ClientProfileService;
import com.kgc.chatbot.model.service.ProductService;
import com.kgc.chatbot.model.service.SkillService;
import com.kgc.chatbot.model.service.UserService;
import com.kgc.chatbot.model.skill.BasicCard;
import com.kgc.chatbot.model.skill.Button;
import com.kgc.chatbot.model.skill.Carousel;
import com.kgc.chatbot.model.skill.ClientProfile;
import com.kgc.chatbot.model.skill.CommerceCard;
import com.kgc.chatbot.model.skill.Head;
import com.kgc.chatbot.model.skill.ItemCard;
import com.kgc.chatbot.model.skill.ItemList;
import com.kgc.chatbot.model.skill.ItemListSummary;
import com.kgc.chatbot.model.skill.Link;
import com.kgc.chatbot.model.skill.ListCard;
import com.kgc.chatbot.model.skill.ListItem;
import com.kgc.chatbot.model.skill.Profile;
import com.kgc.chatbot.model.skill.QuickReplies;
import com.kgc.chatbot.model.skill.SimpleText;
import com.kgc.chatbot.model.skill.SkillResponseBuilder;
import com.kgc.chatbot.model.skill.Thumbnail;

@RequestMapping("/api/chat")
@RestController
public class ChatController {

	@Autowired
	BoardService boardService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	SkillService skillService;
	@Autowired
	ClientInfoService clientInfoService;
	@Autowired
	ClientProfileService clientProfileService;
	
	@Value("${KAKAO_API_KEY}")
	String apiKey;
		
	@Value("${CONVERT_COORDS}")
	String convertUrl;

	@Value("${IMAGE_DISPLAY}")
	String imageURL;
	
	@Value("${MAIN_MENU}")
	String mainMenuId;
	
	@Value("${ARTICLE_LIST}")
	String articleListId;
	
	@Value("${ARTICLE_DETAIL}")
	String articleBlockId;
	
	@Value("${EVENT_LIST}")
	String eventBlockId;
	
	@Value("${SEARCH_OPTION}")
	String searchOptId;
	
	@Value("${SEARCH_MAIN}")
	String searchMainId;
	
	@Value("${SEARCH_SUB}")
	String searchSubId;
	
	@Value("${SEARCH_AGE}")
	String searchAgeId;
	
	@Value("${SEARCH_PRICE}")
	String searchPriceId;
	
	@Value("${SEARCH_NAME}")
	String searchNameId;
	
	@Value("${SEARCH_LIST}")
	String productListId;
	
	@Value("${SEARCH_DETAIL}")
	String productDetailId;
	
	@Value("${ALERT_DESCRIPTION}")
	String productAlertId;
	
	@Value("${OTP_BLOCK}")
	String otpId;
	
	final String mapUrl = "https://spi.maps.daum.net/map2/map/imageservice?IW=550&IH=350";

	final String marketName = "정관장 충북영동점";
	StringBuilder sb;
	
	//오시는 길
	@PostMapping("/introduce")
	private JSONObject doIntroduce(@RequestBody JSONObject payload) throws ParseException, UnsupportedEncodingException {
		
		JSONParser jsonParser = new JSONParser();
		
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		User admin = userService.selectUser("admin");
		
		RestTemplate rt = skillService.makeRestTemplate();	
		HttpHeaders headers = skillService.makeHeaders();
		headers.set("Authorization", apiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity(headers);
		UriComponentsBuilder urib = UriComponentsBuilder.fromHttpUrl(convertUrl).queryParam("x",admin.getLng()).queryParam("y", admin.getLat()).queryParam("output_coord","WCONGNAMUL");
		ResponseEntity response = rt.exchange(urib.toUriString(),HttpMethod.GET,request,String.class);
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 
		JSONArray docuArray = (JSONArray) jsonObject.get("documents");
		JSONObject docuObject = (JSONObject) docuArray.get(0); 
		int x = ((Double)docuObject.get("x")).intValue();
		int y = ((Double)docuObject.get("y")).intValue();
		sb = new StringBuilder();
		sb.append(mapUrl).append("&MX=").append(x).append("&MY=").append(y).append("&CX=").append(x).append("&CY=").append(y).append("&TX=").append(x).append("&TY=").append(y+36).append("&SCALE=0.625&TEXT=%EC%A0%95%EA%B4%80%EC%9E%A5%20%EC%98%81%EB%8F%99%EC%A0%90&service=open.png");
		
		BasicCard basicCard = skillService.makeBasicCard();	
		basicCard.title("정관장 영동점");
		basicCard.thumbnail(new Thumbnail().imageUrl(sb.toString()));
		basicCard.description(admin.getAddress());
		basicCard.buttons(new Button().label("전화 문의").action("phone").phoneNumber(admin.getTel()));
		basicCard.buttons(new Button().label("자세히 보기").action("webLink").webLinkUrl(admin.getLink()));
//		basicCard.buttons(new Button().label("길찾기").action("webLink").webLinkUrl("nmap://route/car?dlat="+admin.getLat()+"&dlng="+admin.getLng()+"&dname="+URLEncoder.encode("정관장 영동점","UTF-8").replaceAll("\\+","%20")+"&appname=devsu.iptime.org:9000"));
		basicCard.buttons(new Button().label("길찾기").action("webLink").webLinkUrl("https://map.kakao.com/link/to/"+URLEncoder.encode("정관장 영동점","UTF-8").replaceAll("\\+","%20")+","+admin.getLat()+","+admin.getLng()));
		srb.addOutputs("basicCard", basicCard.build());
		srb.addReplies(new QuickReplies().label("다른 메뉴 보기").action("block").blockId(mainMenuId));
		return srb.build();
	}
	
	//공지목록
	@PostMapping("/notices")
	private JSONObject doNotices(@RequestBody JSONObject payload) {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		List<Board> notices = boardService.getBoards(false);
			
		//캐로셀 - 5개의 카드까지
		int maxIdx = Math.min(24, notices.size()-1);
		
		if(maxIdx==-1) {
			ListCard card = new ListCard();
			card.header(new ListItem().title("공지사항"));
			ListItem item = new ListItem();
			item.title("등록된 공지사항이 없습니다.");
			item.description("중요한 소식은 바로 전해드릴게요.");
			card.items(item);
			
//			Button button = new Button();
//			button.label("메인 메뉴로 이동").action("message").messageText("메인 메뉴");
			
//			card.buttons(button);
			
			srb.addOutputs("listCard",card.build());
		}
		else {
			Carousel carousel = skillService.makeCarousel();
			carousel.type("listCard");
			int cardCnt = 1 + maxIdx/5;
			//하나의 카드 - 5개 이벤트까지
			
			for(int i=0;i<cardCnt;i++) {
				ListCard card = new ListCard();
				card.header(new ListItem().title("공지사항"));
				for(int j=i*5;j<Math.min(maxIdx+1,(i+1)*5);j++) {
					Board notice = notices.get(j);
					
					ListItem item = new ListItem();
					item.title(notice.getSubject());
					item.imageUrl(imageURL+notice.getThumbnail());
					item.action("block").blockId(articleBlockId);
					card.items(item);
					JSONObject extra = new JSONObject();
					extra.put("articleNo",notice.getArticleNo());
					item.extra(extra);
				}
//				Button button = new Button();
//				button.label("메인 메뉴로 이동").action("message").messageText("메인 메뉴");
//				card.buttons(button);
				carousel.items(card.build());
			}
			
			srb.addOutputs("carousel", carousel.build());
		}

		srb.addReplies(new QuickReplies().label("다른 메뉴 보기").action("block").blockId(mainMenuId));
		return srb.build();
	}
	
	//이벤트 목록
	@PostMapping("/events")
	private JSONObject doEvents(@RequestBody JSONObject payload) {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String Today = sdf.format(new Date()).toString();
		System.out.println(Today);
		List<Board> notices = boardService.getBoards(true).stream().filter(notice->notice.getEndEvent().compareTo(Today)>=0).collect(Collectors.toList());
		
//		System.out.println(notices.size());
		//캐로셀 - 5개의 카드까지
		int maxIdx = Math.min(24, notices.size()-1);
		
		if(maxIdx==-1) {
			ListCard card = new ListCard();
			card.header(new ListItem().title("행사 현황"));
			ListItem item = new ListItem();
			item.title("진행중인 행사가 없습니다.");
			item.description("곧 풍성한 행사로 찾아뵙겠습니다.");
			card.items(item);
//			
//			Button button = new Button();
//			button.label("메인 메뉴로 이동").action("message").messageText("메인 메뉴");
//			
//			card.buttons(button);
			
			srb.addOutputs("listCard", card.build());
		}
		else {
			Carousel carousel = skillService.makeCarousel();
			carousel.type("listCard");
			
			int cardCnt = 1 + maxIdx/5;
			//하나의 카드 - 5개 이벤트까지
			
			for(int i=0;i<cardCnt;i++) {
				ListCard card = new ListCard();
				card.header(new ListItem().title("행사 현황"));
				for(int j=i*5;j<Math.min(maxIdx+1,(i+1)*5);j++) {
					Board notice = notices.get(j);
					
					ListItem item = new ListItem();
					item.title(notice.getSubject());
					String start = notice.getStartEvent();
					if(start!=null) {
						item.description(notice.getStartEvent()+" ~ "+notice.getEndEvent());
					}
					else {
						item.description("~"+notice.getEndEvent());
					}
					item.link(new Link().web(imageURL+notice.getThumbnail()));
					item.imageUrl(imageURL+notice.getThumbnail());
					item.action("block").blockId(articleBlockId);
					
					JSONObject extra = new JSONObject();
					extra.put("articleNo",notice.getArticleNo());
					item.extra(extra);
					card.items(item);
				}
//				Button button = new Button();
//				button.label("메인 메뉴로 이동").action("message").messageText("메인 메뉴");
//				card.buttons(button);
				carousel.items(card.build());
			}
			
			srb.addOutputs("carousel", carousel.build());
		}
		srb.addReplies(new QuickReplies().label("다른 메뉴 보기").action("block").blockId(mainMenuId));
		return srb.build();
	}
	
	//공지,이벤트 상세
	@PostMapping("/article")
	private JSONObject doArticle(@RequestBody JSONObject payload) throws ParseException {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		JSONParser jsonParser = new JSONParser();
				
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject clientExtra = (JSONObject)action.get("clientExtra");
		
		int articleNo = ((Long)clientExtra.get("articleNo")).intValue();
		
		Board article = boardService.getBoard(articleNo);
		
//		ItemCard card = new ItemCard();
//		
//		card.thumbnail(new Thumbnail().imageUrl(imageURL+article.getThumbnail()).width(640).height(640));
//		
//		card.imageTitle(new ImageTitle().title(article.getSubject()));
//		
//		ItemList item = new ItemList();
//		item.title("오늘 날짜");
//		item.description(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		card.itemList(item);
//		
//		if(article.isEvent()) {
//			item = new ItemList();
//			item.title("행사 종료일");
//			item.description(article.getEndEvent());
//			card.itemList(item);
//		}
//		
////		card.buttons(new Button().label("다른 이벤트 보기").action("block").blockId(eventBlockId));  
////		card.buttons(new Button().label("메인 메뉴로 가기").action("message").messageText("메인 메뉴"));
//		srb.addReplies(new QuickReplies().label("다른 이벤트 보기").action("block").blockId(eventBlockId));
//		card.forwardable(true);
//		if(article.getContent().length()<200) {
//			card.title("").description(article.getContent());
//			
//		}
//		srb.addOutputs("itemCard", card.build());
//		
//		if(article.getContent().length()>=200) {
//			SimpleText text = new SimpleText();
//			text.text(article.getContent());
//			srb.addOutputs("simpleText", text.build());
//		}
		BasicCard card = new BasicCard();
		
		card.title(article.getSubject());
		card.thumbnail(new Thumbnail().imageUrl(imageURL+article.getThumbnail()).fixedRatio(true).width(640).height(640).link(new Link().web(imageURL+article.getThumbnail())));
		if(article.isEvent()) {
			
			if(article.getStartEvent() == null) {
				card.description("행사 기간 : " + "~" + article.getEndEvent());
			}
			else {
				card.description("행사 기간 : " + article.getStartEvent() + " ~ " + article.getEndEvent());
			}
			card.forwardable(true);
			srb.addReplies(new QuickReplies().label("다른 행사 확인하기").action("block").blockId(eventBlockId));
		}
		else {
			srb.addReplies(new QuickReplies().label("다른 공지 보기").action("block").blockId(articleListId));
		}
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		srb.addOutputs("basicCard", card.build());
		
		SimpleText text = new SimpleText().text(article.getContent());
		srb.addOutputs("simpleText", text.build());
		return srb.build();
	}
	
	
	//제품 카테고리 선택 1
	@PostMapping("/products/main")
	private JSONObject doProductMainCategory(@RequestBody JSONObject payload) {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		Carousel carousel = new Carousel();
		
		carousel.type("listCard");
		List<MainCode> mainCodes = productService.selectMainCodes();
		
		int codeCnt = mainCodes.size();
		
		if(codeCnt == 0) {
			ListCard card = new ListCard();
			card.header(new ListItem().title("정관장 제품 카테고리"));
			ListItem item = new ListItem();
			item.title("등록된 카테고리가 없습니다.");
			card.items(item);
			
			Button button = new Button();
			button.label("다른 방식으로 조회").action("block").blockId(searchOptId);
			
			card.buttons(button);
			
			carousel.items(card.build());
		}
		else {
			int cardCnt = (codeCnt-1)/4+1;
			for(int i=0;i<cardCnt;i++) {
				ListCard card = new ListCard();
				card.header(new ListItem().title("정관장 제품 카테고리 "+(i+1)));
				for(int j=i*4;j<Math.min((i+1)*4, codeCnt);j++) {
					MainCode code = mainCodes.get(j);
					ListItem item = new ListItem();
					item.title(code.getMainName());
					item.description(code.getMainDescription());
					item.imageUrl(imageURL+code.getThumbnail());
					item.action("block");
					item.blockId(searchSubId);
					JSONObject extra = new JSONObject();
					extra.put("mainCode",code.getMainCode());
					extra.put("mainName",code.getMainName());
					item.extra(extra);
					card.items(item);
				}
				carousel.items(card.build());
			}
		}
		srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		srb.addOutputs("carousel", carousel.build());
		return srb.build();
	}
	
	//제품 카테고리 선택 2
	@PostMapping("/products/sub")
	private JSONObject doProductSubCategory(@RequestBody JSONObject payload) throws ParseException {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		JSONParser jsonParser = new JSONParser();
		
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject clientExtra = (JSONObject)action.get("clientExtra");
		
		String mainCode = (String)clientExtra.get("mainCode");
		String mainName = (String)clientExtra.get("mainName");
		Carousel carousel = new Carousel();
		
		carousel.type("listCard");
		List<SubCode> subCodes = productService.selectSubCodes(mainCode).stream().filter(code->code.getSaleCount()>0).collect(Collectors.toList());
		int codeCnt = subCodes.size();
		
		if(codeCnt == 0) {
			ListCard card = new ListCard();
			card.header(new ListItem().title(mainName+" 제품군"));
			ListItem item = new ListItem();
			item.title("등록된 카테고리가 없습니다.");
			card.items(item);
			Button button = new Button();
			button.label("다른 카테고리 보기").action("block").blockId(searchMainId);
			
			card.buttons(button);
			
			carousel.items(card.build());
		}
		else {
			int cardCnt = (codeCnt-1)/4+1;
			for(int i=0;i<cardCnt;i++) {
				ListCard card = new ListCard();
				card.header(new ListItem().title(mainName+" 제품군"));
				for(int j=i*4;j<Math.min((i+1)*4, codeCnt);j++) {
					SubCode code = subCodes.get(j);
					ListItem item = new ListItem();
					item.title(code.getSubName());
					item.description(code.getSubDescription());
					item.imageUrl(imageURL+code.getThumbnail());
					item.action("block");
					item.blockId(productListId);
					JSONObject extra = new JSONObject();
					extra.put("subCode",code.getSubCode());
					extra.put("mainCode",mainCode);
					extra.put("mainName",mainName);
					item.extra(extra);
					card.items(item);
				}
				carousel.items(card.build());
			}
		}
		srb.addReplies(new QuickReplies().label("다른 카테고리 보기").action("block").blockId(searchMainId));
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		srb.addOutputs("carousel", carousel.build());
		return srb.build();
	}
		
	
	
	
	//제품명으로 검색된 결과
	@PostMapping("/products/name")
	private JSONObject doListName(@RequestBody JSONObject payload) throws ParseException {
		SkillResponseBuilder srb = new SkillResponseBuilder();
				
		JSONParser jsonParser = new JSONParser();
		
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		
		JSONObject clientExtra = (JSONObject)action.get("detailParams");
		JSONObject productName = (JSONObject)clientExtra.get("productName");
		String originVal = (String)productName.get("origin");
		String searchVal = (String)productName.get("value");
		
		List<ProductInfo> productInfos = productService.selectProductInfosByName(searchVal).stream().filter(info->info.isSale()).collect(Collectors.toList());
		
		
		BasicCard card = new BasicCard();
		
		card.thumbnail(new Thumbnail().imageUrl(imageURL+"searchResult.png"));
		card.title("입력 값 : "+originVal);
		card.description("총 "+productInfos.size()+"개 제품이 검색되었습니다.");
		
		Button button = new Button();
		button.label("검색된 제품 보기");
		button.action("block");
		button.blockId(productListId);
		
		JSONObject extra = new JSONObject();
		extra.put("searchName",searchVal);
		
		button.extra(extra);
		
		card.buttons(button);
		
		srb.addOutputs("basicCard", card.build());
		srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		return srb.build();
	}
	
	//제품 리스트
	@PostMapping("/products/list")
	private JSONObject doListProduct(@RequestBody JSONObject payload) throws ParseException{
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		Date sta = new Date(); 
		Carousel carousel = new Carousel();
		carousel.type("commerceCard");
		JSONParser jsonParser = new JSONParser();
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject clientExtra = (JSONObject)action.get("clientExtra");
		
		boolean isPage = false;
		
		List<ProductCode> products = new ArrayList<>();
		List<ProductInfo> infos = new ArrayList<>();
		
		int categoryNo = 1;
		//카테고리 조회
		if(clientExtra.containsKey("subCode")) {
			if(clientExtra.containsKey("limit"))
				categoryNo = (int)(long)clientExtra.get("limit");
			
			String subCode = (String)clientExtra.get("subCode");
			products = productService.selectProductCodesWithInfo(subCode).stream().filter(code->code.getProductInfo().isSale()).collect(Collectors.toList());
			String mainCode = (String)clientExtra.get("mainCode");
			String mainName = (String)clientExtra.get("mainName");
			String fromAge = (String)clientExtra.get("fromAge");
			JSONObject ext = new JSONObject();
			ext.put("mainCode",mainCode);
			ext.put("mainName",mainName);
			
			if(categoryNo==1 && categoryNo*10<products.size()) {
				JSONObject next = new JSONObject();
				next.put("limit",categoryNo+1);
				next.put("subCode",subCode);
				next.put("mainName",mainName);
				next.put("mainCode",mainCode);
				srb.addReplies(new QuickReplies().label((categoryNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(categoryNo>1) {
				JSONObject prev = new JSONObject();
				prev.put("limit",categoryNo-1);
				prev.put("subCode",subCode);
				prev.put("mainName",mainName);
				prev.put("mainCode",mainCode);
				srb.addReplies(new QuickReplies().label((categoryNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(categoryNo*10<products.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",categoryNo+1);
					next.put("subCode",subCode);
					next.put("mainName",mainName);
					next.put("mainCode",mainCode);
					srb.addReplies(new QuickReplies().label((categoryNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}
			if(fromAge!=null && fromAge.equals("1")) {
				srb.addReplies(new QuickReplies().label("다른 연령대 보기").action("block").blockId(searchAgeId));		
			}
			else {
				srb.addReplies(new QuickReplies().label(mainName+" 제품군 보기").action("block").blockId(searchSubId).extra(ext));				
			}
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		}
		//연령대별 조회
		else if(clientExtra.containsKey("subCodes")) {
			if(clientExtra.containsKey("limit"))
				categoryNo = (int)(long)clientExtra.get("limit");
			String fromAge = (String)clientExtra.get("fromAge");
			StringTokenizer st = new StringTokenizer((String)clientExtra.get("subCodes"),",");
			while(st.hasMoreTokens()) {
				String subCode = st.nextToken();
				List<ProductCode> items = productService.selectProductCodesWithInfo(subCode).stream().filter(code->code.getProductInfo().isSale()).collect(Collectors.toList());	
				for(ProductCode item : items)	products.add(item);
			}
			
			if(categoryNo==1 && categoryNo*10<products.size()) {
				JSONObject next = new JSONObject();
				next.put("limit",categoryNo+1);
				next.put("subCodes",clientExtra.get("subCodes"));
				srb.addReplies(new QuickReplies().label((categoryNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(categoryNo>1) {
				JSONObject prev = new JSONObject();
				prev.put("limit",categoryNo-1);
				prev.put("subCodes",clientExtra.get("subCodes"));
				srb.addReplies(new QuickReplies().label((categoryNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(categoryNo*10<products.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",categoryNo+1);
					next.put("subCodes",clientExtra.get("subCodes"));
					srb.addReplies(new QuickReplies().label((categoryNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}
			
			if(fromAge!=null && fromAge.equals("1")) {
				srb.addReplies(new QuickReplies().label("다른 연령대 보기").action("block").blockId(searchAgeId));		
			}
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		}
		//상품명 조회
		else if(clientExtra.containsKey("searchName")) {
			int pgNo = 1;
			if(clientExtra.containsKey("limit"))
				pgNo = (int)(long)clientExtra.get("limit");
			String searchName = (String)clientExtra.get("searchName");
			infos = productService.selectProductInfosWithCategoryByName(searchName,pgNo).stream().filter(info->info.isSale()).collect(Collectors.toList());
			List<ProductInfo> total = productService.selectProductInfosByName(searchName).stream().filter(info->info.isSale()).collect(Collectors.toList());
			if(pgNo==1 && pgNo*10<total.size()) {
				JSONObject next = new JSONObject();
				next.put("limit",pgNo+1);
				next.put("searchName",searchName);
				srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(pgNo>1) {
				JSONObject prev = new JSONObject();
				prev.put("limit",pgNo-1);
				prev.put("searchName",searchName);
				srb.addReplies(new QuickReplies().label((pgNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(pgNo*10<total.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",pgNo+1);
					next.put("searchName",searchName);
					srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}
			srb.addReplies(new QuickReplies().label("다른 상품명으로 조회").action("block").blockId(searchNameId));
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		}
		//가격대 조회
		else if(clientExtra.containsKey("lower") || clientExtra.containsKey("upper")) {
			Map<String,Integer> condition = new HashMap<>();
			if(clientExtra.containsKey("lower")) {
				condition.put("lower",Integer.valueOf((String)clientExtra.get("lower")));
			}
			if(clientExtra.containsKey("upper")) {
				condition.put("upper",Integer.valueOf((String)clientExtra.get("upper")));
			}
			
			isPage = true;
			int pgNo = 1;
			if(clientExtra.containsKey("limit"))
				pgNo = (int)(long)clientExtra.get("limit");
			
			infos = productService.selectProductInfosWithCategoryByPrice(condition,pgNo).stream().filter(info->info.isSale()).collect(Collectors.toList());
			
			System.out.println(infos.size());
			List<ProductInfo> total = productService.selectProductInfosByPrice(condition).stream().filter(info->info.isSale()).collect(Collectors.toList());
			if(pgNo==1 && pgNo*10<total.size()) {		
				JSONObject next = new JSONObject();
				next.put("limit",pgNo+1);
				if(clientExtra.containsKey("lower"))
					next.put("lower", clientExtra.get("lower"));
				if(condition.containsKey("upper"))					
					next.put("upper", clientExtra.get("upper"));
				srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(pgNo>1){
				JSONObject prev = new JSONObject();
				prev.put("limit",pgNo-1);
				if(clientExtra.containsKey("lower"))
					prev.put("lower", clientExtra.get("lower"));
				if(clientExtra.containsKey("upper"))					
					prev.put("upper", clientExtra.get("upper"));
				srb.addReplies(new QuickReplies().label((pgNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(pgNo*10<total.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",pgNo+1);
					if(clientExtra.containsKey("lower"))
						next.put("lower", clientExtra.get("lower"));
					if(clientExtra.containsKey("upper"))					
						next.put("upper", clientExtra.get("upper"));
					srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}				
			srb.addReplies(new QuickReplies().label("다른 가격대 조회").action("block").blockId(searchPriceId));
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		}
		//할인 제품
		else if(clientExtra.containsKey("rate")) {
			isPage = true;
			String srate = (String)clientExtra.get("rate");
			int rate = Integer.valueOf(srate);
			int pgNo = 1;
			if(clientExtra.containsKey("limit"))
				pgNo = (int)(long)clientExtra.get("limit");
			
			infos = productService.selectProductInfosWithCategoryByRate(rate,pgNo).stream().filter(info->info.isSale()).collect(Collectors.toList());
			List<ProductInfo> total = productService.selectProductInfosByRate(rate).stream().filter(info->info.isSale()).collect(Collectors.toList());
			if(pgNo==1 && pgNo*10<total.size()) {		
				JSONObject next = new JSONObject();
				next.put("limit",pgNo+1);
				next.put("rate",srate);
				srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(pgNo>1){
				JSONObject prev = new JSONObject();
				prev.put("limit",pgNo-1);
				prev.put("rate",srate);
				srb.addReplies(new QuickReplies().label((pgNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(pgNo*10<total.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",pgNo+1);
					next.put("rate",srate);
					srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}				
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		}
		//추천 상품
		else {
			isPage = true;
			int pgNo = 1;
			if(clientExtra.containsKey("limit"))
				pgNo = (int)(long)clientExtra.get("limit");
			
			infos = productService.selectProductInfosWithCategoryByLevel(1,pgNo).stream().filter(info->info.isSale()).collect(Collectors.toList());
			List<ProductInfo> total = productService.selectProductInfosByLevel(1).stream().filter(info->info.isSale()).collect(Collectors.toList());
			if(pgNo==1 && pgNo*10<total.size()) {		
				JSONObject next = new JSONObject();
				next.put("limit",pgNo+1);
				srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
			}
			else if(pgNo>1){
				JSONObject prev = new JSONObject();
				prev.put("limit",pgNo-1);
				srb.addReplies(new QuickReplies().label((pgNo-1)+"페이지 보기").action("block").blockId(productListId).extra(prev));
				if(pgNo*10<total.size()) {
					JSONObject next = new JSONObject();
					next.put("limit",pgNo+1);
					srb.addReplies(new QuickReplies().label((pgNo+1)+"페이지 보기").action("block").blockId(productListId).extra(next));
				}
			}				
			srb.addReplies(new QuickReplies().label("다른 방식으로 조회").action("block").blockId(searchOptId));
			srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
			
		}
		
		//상품명 조회
		if(infos.size()!=0) {
			for(ProductInfo info : infos) {
				ProductCode product = info.getCategory();
				CommerceCard card = new CommerceCard();
				if(info.getComposition()!=null && !info.getComposition().equals("")) {
//				card.description(info.getProductName()+" ["+info.getComposition()+"]");
					card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
					card.title(info.getProductName()+" ["+info.getComposition()+"]");
				}
				else if(info.getVolume()!=null && !info.getVolume().equals("")){
//				card.description(info.getProductName()+" ["+info.getVolume()+"]");
					card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
					card.title(info.getProductName()+" ["+info.getVolume()+"]");
				}
				else {
					card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
					card.title(info.getProductName());
				}
				card.price(info.getPrice());
				if(info.isDiscount()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
					String today = sdf.format(new Date()).toString();
					String start = info.getStartdiscount();
					String end = info.getEnddiscount();
					if(today.compareTo(start)>=0 && today.compareTo(end)<=0) {

						int totalPrice = (int)((info.getPrice()*(1.0-info.getDiscountRate()*0.01))- info.getDiscountPrice());
						card.discountedPrice(totalPrice);
						if(info.getDiscountPrice()==0 && info.getDiscountRate()>0) {
							card.discountRate(info.getDiscountRate());
						}
						card.discount(info.getPrice()-totalPrice);
					}
				}

				card.description(info.getComment());
				card.thumbnails(new Thumbnail().imageUrl(imageURL+product.getThumbnail()).link(new Link().web(imageURL+product.getThumbnail())));
				JSONObject extra = new JSONObject();
				extra.put("productCode",product.getProductCode());
//				if(clientExtra.containsKey("subCode")) extra.put("subCode",)
				card.buttons(new Button().label("전화문의").action("phone").phoneNumber("043-744-2304"));
				card.buttons(new Button().label("상세보기").messageText(info.getProductName()+" "+info.getComposition()).action("block").blockId(productDetailId).extra(extra));
				card.buttons(new Button().label("공유하기").action("share"));
				carousel.items(card.build());
			}
		}
		else {
			if(products.size()!=0) {
//				for(ProductCode product : products) {
				int sidx = (categoryNo-1)*10;
				int eidx = sidx+10;
				if(eidx>products.size())
					eidx = products.size();
				for(int idx=sidx;idx<eidx;idx++) {
//					ProductInfo info = product.getProductInfo();
					ProductInfo info = products.get(idx).getProductInfo();
					ProductCode product = products.get(idx);
					CommerceCard card = new CommerceCard();
//					if(info.getComposition()!=null && !info.getComposition().equals("")) {
////						card.description(info.getProductName()+" ["+info.getComposition()+"]");
//						card.profile(new Profile().nickname(info.getProductName()+" ["+info.getComposition()+"]").imageUrl(imageURL+"profile.png"));
//					}
//					else if(info.getVolume()!=null && !info.getVolume().equals("")){
////						card.description(info.getProductName()+" ["+info.getVolume()+"]");
//						card.profile(new Profile().nickname(info.getProductName()+" ["+info.getVolume()+"]").imageUrl(imageURL+"profile.png"));
//					}
//					else {
//						card.profile(new Profile().nickname(info.getProductName()).imageUrl(imageURL+"profile.png"));
//					}
					if(info.getComposition()!=null && !info.getComposition().equals("")) {
//						card.description(info.getProductName()+" ["+info.getComposition()+"]");
							card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
							card.title(info.getProductName()+" ["+info.getComposition()+"]");
					}
					else if(info.getVolume()!=null && !info.getVolume().equals("")){
//						card.description(info.getProductName()+" ["+info.getVolume()+"]");
						card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
						card.title(info.getProductName()+" ["+info.getVolume()+"]");
					}
					else {
						card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
						card.title(info.getProductName());
					}
					card.price(info.getPrice());
					if(info.isDiscount()) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
						String today = sdf.format(new Date()).toString();
						String start = info.getStartdiscount();
						String end = info.getEnddiscount();
						if(today.compareTo(start)>=0 && today.compareTo(end)<=0) {

							int totalPrice = (int)((info.getPrice()*(1.0-info.getDiscountRate()*0.01))- info.getDiscountPrice());
							card.discountedPrice(totalPrice);
							if(info.getDiscountPrice()==0 && info.getDiscountRate()>0) {
								card.discountRate(info.getDiscountRate());
							}
							card.discount(info.getPrice()-totalPrice);
						}
					}
					
					card.description(info.getComment());
					card.thumbnails(new Thumbnail().imageUrl(imageURL+product.getThumbnail()).link(new Link().web(imageURL+product.getThumbnail())));
					
					JSONObject extra = new JSONObject();
					extra.put("productCode",product.getProductCode());
					card.buttons(new Button().label("전화문의").action("phone").phoneNumber("043-744-2304"));
					card.buttons(new Button().label("상세보기").messageText(info.getProductName()+" "+info.getComposition()).action("block").blockId(productDetailId).extra(extra));
					card.buttons(new Button().label("공유하기").action("share"));
					carousel.items(card.build());
				}	
			}
			else {
				carousel.type("basicCard");
				BasicCard card = new BasicCard();
				card.title("조회 결과 : 0건");
				card.description("선택하신 조건으로 검색된 제품이 없습니다.");
				card.thumbnail(new Thumbnail().imageUrl(imageURL+"empty.png"));
				card.profile(new Profile().nickname(marketName).imageUrl(imageURL+"profile.png"));
				card.buttons(new Button().action("phone").label("전화로 문의하기").phoneNumber("043-744-2304"));
				card.buttons(new Button().action("block").label("조회 방법 선택").blockId(searchOptId));
				card.buttons(new Button().action("block").label("전체 메뉴 보기").blockId(mainMenuId));
				carousel.items(card.build());
			}
		}
		srb.addOutputs("carousel", carousel.build());
		Date end = new Date();
//		System.out.println(end.getTime()-sta.getTime());
		return srb.build();
	}
	
	//제품 상세 조회
	@PostMapping("/products/detail")
	private JSONObject doProduct(@RequestBody JSONObject payload) throws ParseException {
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
//		System.out.println(payload);
		JSONParser jsonParser = new JSONParser();
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject clientExtra = (JSONObject)action.get("clientExtra");
		String productCode = (String)clientExtra.get("productCode");
		
		ProductCode product = productService.selectProductCodeWithInfo(productCode);
		ProductInfo info = product.getProductInfo();
		
		String productThumbnail = product.getThumbnail();
		
		String mainName = product.getMainName();
		String subName = product.getSubName();

		String productName = product.getProductName()+" ";
		String composition = info.getComposition()!=null ? info.getComposition()+" " : "";
		String volume = info.getVolume()!=null ? info.getVolume()+" " : "";
		
		String comment = info.getComment()!=null ? info.getComment() : "";
		String detail = info.getDetail()!=null ? info.getDetail() : "";
		
		String usage = info.getUsage()!=null ? info.getUsage() : "";
//		boolean isSale = info.isSale();
		boolean isHit = info.getProductLevel()==1 ? true : false;
		boolean isHealth = info.isHealth();
		boolean isDiscount = info.isDiscount();
		
		String startdiscount = info.getStartdiscount();
		String enddiscount = info.getEnddiscount();

		int price = info.getPrice();
		int discountprice = info.getDiscountPrice();

		int discountrate = info.getDiscountRate();
		
		int totalprice = info.getTotalPrice();

		int totaldiscount = (int)(price*(0.01*discountrate))+discountprice; 

		String alert = info.getAlert();
				
		
		ItemCard card = new ItemCard();
		
		card.thumbnail(new Thumbnail().imageUrl(imageURL+productThumbnail).width(800).height(400));
		
		card.head(new Head().title(productName+composition));
		
//		card.imageTitle(new ImageTitle().title(productName).description(volume+composition).imageUrl(imageURL+productThumbnail));
		card.title(comment);
		card.description(detail);
		
		if(isHit) {
			ItemList item1 = new ItemList();
			item1.title("추천 상품");
			item1.description("O");
			card.itemList(item1);
		}
		ItemList item2 = new ItemList();
		item2.title("건강기능식품");
		item2.description(info.isHealth() ? "O" : "X");
		card.itemList(item2);
		
		ItemList item3 = new ItemList();
		item3.title("용량/용법");
		item3.description(volume+"/ "+usage);
		
		card.itemList(item3);
		
		ItemList item4 = new ItemList();
		item4.title("정상 가격(원)");
		item4.description(String.valueOf(price).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
		card.itemList(item4);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		String today = sdf.format(new Date()).toString();
		
		ItemList item5 = new ItemList();
		item5.title("행사여부");
		item5.description(isDiscount && today.compareTo(startdiscount)>=0 && today.compareTo(enddiscount)<=0 ? "O" : "X");
		card.itemList(item5);
		if(isDiscount && today.compareTo(startdiscount)>=0 && today.compareTo(enddiscount)<=0) {
			ItemList item6 = new ItemList();
			item6.title("행사 시작일");
			item6.description(startdiscount);
			card.itemList(item6);
			
			ItemList item7 = new ItemList();
			item7.title("행사 종료일");
			item7.description(enddiscount);
			card.itemList(item7);
			
			ItemList item8 = new ItemList();
			item8.title("할인율(%)");
			item8.description(discountrate+"%");
			card.itemList(item8);
			
			ItemList item9 = new ItemList();
			item9.title("할인 금액(원)");
			item9.description(String.valueOf(discountprice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
			card.itemList(item9);
			
			ItemList item10 = new ItemList();
			item10.title("총 할인액(원)");
			item10.description(String.valueOf(totaldiscount).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
			card.itemList(item10);
			card.itemListSummary(new ItemListSummary().title("판매 금액(원)").description(String.valueOf(totalprice).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")));
		}
		else {
			card.itemListSummary(new ItemListSummary().title("판매 금액(원)").description(String.valueOf(price).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",")));

		}
		
		JSONObject extra = new JSONObject();
		extra.put("alert",alert);
		if(alert!=null) {
			card.buttons(new Button().label("섭취 시 유의사항").action("block").blockId(productAlertId).extra(extra));
		}
		card.buttons(new Button().label("상담원에게 전화문의").action("phone").phoneNumber("0437442304"));
		card.buttonLayout("vertical");
		card.buttons(new Button().label("상담원에게 채팅문의").action("operator"));
//		card.buttons(new Button().label("공유하기").action("share"));
		srb.addOutputs("itemCard", card.build());
//		srb.addReplies(new QuickReplies().label("이전 상품 목록").action("block").blockId(productListId));
		srb.addReplies(new QuickReplies().label("다시 조회하기").action("block").blockId(searchOptId));
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		return srb.build();
	}
	
	@PostMapping("/products/alert")
	private JSONObject doProductAlert(@RequestBody JSONObject payload) throws ParseException {

		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject clientExtra = (JSONObject)action.get("clientExtra");
		
		String alert = (String)clientExtra.get("alert");
		SimpleText text = new SimpleText().text(alert);
		srb.addReplies(new QuickReplies().label("전체 메뉴 보기").action("block").blockId(mainMenuId));
		srb.addOutputs("simpleText", text.build());
		return srb.build();
	}
	
	@PostMapping("/personal")
	private JSONObject doPersonal(@RequestBody JSONObject payload) throws ParseException{
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		srb.addOutputs("simpleText",new SimpleText().text("현재 이용불가능한 서비스입니다.").build());
		srb.addReplies(new QuickReplies().action("block").label("test").blockId(otpId));
		return srb.build();
	}
	
	@PostMapping("/client")
	private JSONObject doClientInfo(@RequestBody JSONObject payload) throws ParseException{
		SkillResponseBuilder srb = new SkillResponseBuilder();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject userReq = (JSONObject)jsonParser.parse(payload.toString());
		JSONObject action= (JSONObject)userReq.get("action");
		JSONObject params = (JSONObject)action.get("params");
		JSONObject profile = (JSONObject)jsonParser.parse((String)params.get("profile"));
		JSONObject req = (JSONObject)userReq.get("userRequest");
		JSONObject user= (JSONObject)((JSONObject)req.get("user")).get("properties");
	
		ClientProfile cp = clientProfileService.getOtpData(profile);
		System.out.println(cp.getNickname()+" "+cp.getPhone_number());
		
		ClientInfo info = clientInfoService.createClientInfo(user, profile, cp);
		clientInfoService.saveClientInfo(info);
		SimpleText text = new SimpleText().text("정상 등록되었습니다.");
		srb.addOutputs("simpleText", text.build());
		return srb.build();
	}
}
