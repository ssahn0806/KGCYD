package com.kgc.chatbot.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kgc.chatbot.model.dto.ProductCode;

@Mapper
public interface ProductCodeDAO {
	
	List<ProductCode> selectProductCodes(String subCode);
	
	ProductCode selectProductCode(String code);
	
	int insertProductCode(Map<String,Object> condition);
	
	int updateProductCode(ProductCode productCode);
	
	int deleteProductCode(String code);
	
	List<ProductCode> selectProductCodesWithInfo(String subCode);
	
	ProductCode selectProductCodeWithInfo(String code);
	
	String getLastProductCode(String subCode);
	
	
}
