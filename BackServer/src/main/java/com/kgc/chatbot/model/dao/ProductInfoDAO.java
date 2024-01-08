package com.kgc.chatbot.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.ProductInfo;

@Mapper 
public interface ProductInfoDAO {
	
	List<ProductInfo> selectProductInfosByName(String name);
	
	List<ProductInfo> selectProductInfosWithCategoryByName(Map<String,Object> condition);
	
	List<ProductInfo> selectProductInfosByPrice(Map<String,Integer> condition);
	
	List<ProductInfo> selectProductInfosWithCategoryByPrice(Map<String,Integer> condition);
	
	List<ProductInfo> selectProductInfosByLevel(int level);
	
	List<ProductInfo> selectProductInfosWithCategoryByLevel(Map<String,Integer> condition);
	
	List<ProductInfo> selectProductInfosByRate(int rate);
	
	List<ProductInfo> selectProductInfosWithCategoryByRate(Map<String,Integer> condition);
	
	ProductInfo selectProductInfo(String code);
	
	ProductInfo selectProductInfoWithCategory(String code);
	
	int insertProductInfo(ProductInfo productInfo);
	
	int updateProductInfo(ProductInfo productInfo);
	
	int deleteProductInfo(String code);
	
	
}
