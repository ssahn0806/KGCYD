package com.kgc.chatbot.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgc.chatbot.model.dao.MainCodeDAO;
import com.kgc.chatbot.model.dao.ProductCodeDAO;
import com.kgc.chatbot.model.dao.ProductInfoDAO;
import com.kgc.chatbot.model.dao.SubCodeDAO;
import com.kgc.chatbot.model.dto.MainCode;
import com.kgc.chatbot.model.dto.ProductCode;
import com.kgc.chatbot.model.dto.ProductInfo;
import com.kgc.chatbot.model.dto.SubCode;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private MainCodeDAO mainCodeDAO;
	@Autowired
	private SubCodeDAO subCodeDAO;
	@Autowired
	private ProductCodeDAO productCodeDAO;
	@Autowired
	private ProductInfoDAO productInfoDAO;
	@Override
	public List<MainCode> selectMainCodes() {
		return mainCodeDAO.selectMainCodes();
	}

	@Override
	public MainCode selectMainCode(String code) {
		return mainCodeDAO.selectMainCode(code);
	}

	@Override
	public boolean registMainCode(MainCode mainCode) {
		return mainCodeDAO.insertMainCode(mainCode)>0;
	}

	@Override
	public boolean modifyMainCode(MainCode mainCode) {
		return mainCodeDAO.updateMainCode(mainCode)>0;
	}

	@Override
	public boolean removeMainCode(String code) {
		return mainCodeDAO.deleteMainCode(code)>0;
	}

	@Override
	public List<SubCode> selectSubCodes(String mainCode) {
		return subCodeDAO.selectSubCodes(mainCode);
	}

	@Override
	public SubCode selectSubCode(String code) {
		return subCodeDAO.selectSubCode(code);
	}

	@Override
	public boolean registSubCode(SubCode subCode) {
		return subCodeDAO.insertSubCode(subCode)>0;
	}

	@Override
	public boolean modifySubCode(SubCode subCode) {
		return subCodeDAO.updateSubCode(subCode)>0;
	}

	@Override
	public boolean removeSubCode(String code) {
		return subCodeDAO.deleteSubCode(code)>0;
	}

	@Override
	public boolean updateCountPlus(String code) {
		return subCodeDAO.updateCountPlus(code) > 0;
	}

	@Override
	public boolean updateCountMinus(String code) {
		return subCodeDAO.updateCountMinus(code) > 0;
	}
	
	@Override
	public List<ProductCode> selectProductCodes(String subCode) {
		return productCodeDAO.selectProductCodes(subCode);
	}

	@Override
	public ProductCode selectProductCode(String code) {
		return productCodeDAO.selectProductCode(code);
	}

	// 수정 필
	@Override
	public List<ProductCode> selectProductCodesWithInfo(String subCode) {
		return productCodeDAO.selectProductCodesWithInfo(subCode);
	}

	@Override
	public ProductCode selectProductCodeWithInfo(String code) {
		return productCodeDAO.selectProductCodeWithInfo(code);
	}
	
	@Override
	public boolean registProductCode(Map<String,Object> condition) {
		return productCodeDAO.insertProductCode(condition)>0;
	}

	@Override
	public boolean modifyProductCode(ProductCode productCode) {
		return productCodeDAO.updateProductCode(productCode)>0;
	}

	@Override
	public boolean removeProductCode(String code) {
		return productCodeDAO.deleteProductCode(code)>0;
	}

	@Override
	public String getLastProductCode(String subCode) {
		return productCodeDAO.getLastProductCode(subCode);
	}

	@Override
	public List<ProductInfo> selectProductInfosByName(String name) {
		return productInfoDAO.selectProductInfosByName(name);
	}

	//수정 필요
	@Override
	public List<ProductInfo> selectProductInfosWithCategoryByName(String name, int pgNo) {
		final int pagePerCnt = 10;
		final int start = (pgNo-1)*pagePerCnt;
		final int end = pagePerCnt;
		
		Map<String,Object> condition = new HashMap<>();
		condition.put("name",name);
		condition.put("start",start);
		condition.put("end",end);
		return productInfoDAO.selectProductInfosWithCategoryByName(condition);
	}

	@Override
	public List<ProductInfo> selectProductInfosByPrice(Map<String, Integer> condition) {
		return productInfoDAO.selectProductInfosByPrice(condition);
	}

	@Override
	public List<ProductInfo> selectProductInfosWithCategoryByPrice(Map<String, Integer> condition,int pgNo) {
		final int pagePerCnt = 10;
		final int start = (pgNo-1)*pagePerCnt;
		final int end = pagePerCnt;
		condition.put("start",start);
		condition.put("end",end);
		return productInfoDAO.selectProductInfosWithCategoryByPrice(condition);
	}

	@Override
	public List<ProductInfo> selectProductInfosByLevel(int level) {
		return productInfoDAO.selectProductInfosByLevel(level);
	}

	@Override
	public List<ProductInfo> selectProductInfosWithCategoryByLevel(int level,int pgNo) {
		final int pagePerCnt = 10;
		final int start = (pgNo-1)*pagePerCnt;
		final int end = pagePerCnt;
		// 1 : 0 ~ 14
		// 2 : 15 ~ 29
		// 3: 30 ~ 44
		// i : (i-1)*15 ~ i*15-1
		Map<String,Integer> condition = new HashMap<>();
		condition.put("level",level);
		condition.put("start",start);
		condition.put("end",end);
		return productInfoDAO.selectProductInfosWithCategoryByLevel(condition);
	}

	@Override
	public List<ProductInfo> selectProductInfosByRate(int rate){
		return productInfoDAO.selectProductInfosByRate(rate);
	}
	@Override
	public List<ProductInfo> selectProductInfosWithCategoryByRate(int rate,int pgNo){
		final int pagePerCnt = 10;
		final int start = (pgNo-1)*pagePerCnt;
		final int end = pagePerCnt;
		// 1 : 0 ~ 14
		// 2 : 15 ~ 29
		// 3: 30 ~ 44
		// i : (i-1)*15 ~ i*15-1
		Map<String,Integer> condition = new HashMap<>();
		condition.put("rate",rate);
		condition.put("start",start);
		condition.put("end",end);
		return productInfoDAO.selectProductInfosWithCategoryByRate(condition);
	}
	@Override
	public ProductInfo selectProductInfo(String code) {
		return productInfoDAO.selectProductInfo(code);
	}

	@Override
	public ProductInfo selectProductInfoWithCategory(String code) {
		return productInfoDAO.selectProductInfoWithCategory(code);
	}

	@Override
	public boolean registProductInfo(ProductInfo productInfo) {
		return productInfoDAO.insertProductInfo(productInfo)>0;
	}

	@Override
	public boolean modifyProductInfo(ProductInfo productInfo) {
		return productInfoDAO.updateProductInfo(productInfo)>0;
	}

	@Override
	public boolean removeProductInfo(String code) {
		return productInfoDAO.deleteProductInfo(code)>0;
	}





}
