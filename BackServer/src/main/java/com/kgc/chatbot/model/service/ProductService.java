package com.kgc.chatbot.model.service;

import java.util.List;
import java.util.Map;

import com.kgc.chatbot.model.dto.MainCode;
import com.kgc.chatbot.model.dto.ProductCode;
import com.kgc.chatbot.model.dto.ProductInfo;
import com.kgc.chatbot.model.dto.SubCode;

public interface ProductService {
	List<MainCode> selectMainCodes();
	
	MainCode selectMainCode(String code);
	
	boolean registMainCode(MainCode mainCode);
	
	boolean modifyMainCode(MainCode mainCode);
	
	boolean removeMainCode(String code);
	
	List<SubCode> selectSubCodes(String mainCode);
	
	SubCode selectSubCode(String code);
	
	boolean registSubCode(SubCode subCode);
	
	boolean modifySubCode(SubCode subCode);
	
	boolean removeSubCode(String code);
	
	boolean updateCountPlus(String code);
	
	boolean updateCountMinus(String code);
	
	List<ProductCode> selectProductCodes(String subCode);
	
	ProductCode selectProductCode(String code);
	
	boolean registProductCode(Map<String,Object> condition);
	
	boolean modifyProductCode(ProductCode productCode);
	
	boolean removeProductCode(String code);
	
	String getLastProductCode(String subCode);
	
	List<ProductCode> selectProductCodesWithInfo(String subCode);
	
	ProductCode selectProductCodeWithInfo(String code);
	
	List<ProductInfo> selectProductInfosByName(String name);
	
	List<ProductInfo> selectProductInfosWithCategoryByName(String name);
	
	List<ProductInfo> selectProductInfosByPrice(Map<String,Integer> condition);
	
	List<ProductInfo> selectProductInfosWithCategoryByPrice(Map<String,Integer> condition,int pgNo);
	
	List<ProductInfo> selectProductInfosByLevel(int level);
	
	List<ProductInfo> selectProductInfosWithCategoryByLevel(int level,int pgNo);
	
	List<ProductInfo> selectProductInfosByRate(int rate);
	
	List<ProductInfo> selectProductInfosWithCategoryByRate(int rate,int pgNo);
	
	ProductInfo selectProductInfo(String code);
	
	ProductInfo selectProductInfoWithCategory(String code);
	
	boolean registProductInfo(ProductInfo productInfo);
	
	boolean modifyProductInfo(ProductInfo productInfo);
	
	boolean removeProductInfo(String code);
}
