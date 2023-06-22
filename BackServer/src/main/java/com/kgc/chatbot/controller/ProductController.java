package com.kgc.chatbot.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kgc.chatbot.model.dto.MainCode;
import com.kgc.chatbot.model.dto.ProductCode;
import com.kgc.chatbot.model.dto.ProductInfo;
import com.kgc.chatbot.model.dto.SubCode;
import com.kgc.chatbot.model.service.ProductService;

@RequestMapping("/api/products")
@RestController
public class ProductController {
	

	@Value("${FILE_PATH}")
	String folderPath;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/mains")
	private ResponseEntity<List<MainCode>> doListMains() {
		List<MainCode> mainCodes = productService.selectMainCodes();
		
		if(mainCodes!=null) {
			return ResponseEntity.ok(mainCodes);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/mains/{code}")
	private ResponseEntity<MainCode> doDetailMain(@PathVariable String code) {
		MainCode mainCode = productService.selectMainCode(code);
		
		if(mainCode!=null) {
			return ResponseEntity.ok(mainCode);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/mains")
	private ResponseEntity doRegistMain(@RequestBody MainCode mainCode) {
		boolean res = productService.registMainCode(mainCode);
		
		if(res) {
			return ResponseEntity.created(URI.create("/api/products/mains/"+mainCode.getMainCode())).build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/mains")
	private ResponseEntity doModifyMain(@RequestBody MainCode mainCode) {
		
		MainCode target = productService.selectMainCode(mainCode.getMainCode());
		
		if(target!=null) {
			boolean res = productService.modifyMainCode(mainCode);
			
			if(res) {
				return ResponseEntity.ok(mainCode);
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/mains/{code}")
	private ResponseEntity doRemoveMain(@PathVariable String code) {
		MainCode target = productService.selectMainCode(code);
		
		if(target!=null) {
			boolean res = productService.removeMainCode(code);
			
			if(res) {
				return ResponseEntity.noContent().build();
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/subs")
	private ResponseEntity<List<SubCode>> doListSubs(@RequestParam String mainCode){
		List<SubCode> subCodes = productService.selectSubCodes(mainCode);
		
		if(subCodes!=null) {
			return ResponseEntity.ok(subCodes);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/subs/{code}")
	private ResponseEntity doDetailSub(@PathVariable String code) {
		SubCode subCode = productService.selectSubCode(code);
		
		if(subCode!=null) {
			return ResponseEntity.ok(subCode);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping("/subs")
	private ResponseEntity doRegistSub(@RequestBody SubCode subCode) {
		boolean res = productService.registSubCode(subCode);
		
		if(res) {
			return ResponseEntity.created(URI.create("/api/products/subs/"+subCode.getSubCode())).build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/subs")
	private ResponseEntity doModifySub(@RequestBody SubCode subCode) {
		
		SubCode target = productService.selectSubCode(subCode.getSubCode());
		
		if(target!=null) {
			boolean res = productService.modifySubCode(subCode);
			
			if(res) {
				return ResponseEntity.ok(subCode);
			}
			else {
				return ResponseEntity.internalServerError().build();
			}	
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/subs/{code}")
	private ResponseEntity doRemoveSub(@PathVariable String code) {
		SubCode target = productService.selectSubCode(code);
		
		if(target!=null) {
			boolean res = productService.removeSubCode(code);
			if(res) {
				return ResponseEntity.noContent().build();
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	private ResponseEntity<List<ProductCode>> doListProducts(@RequestParam(defaultValue = "") String subCode) {
		List<ProductCode> productCodes = productService.selectProductCodes(subCode);
		
		if(productCodes!=null) {
			return ResponseEntity.ok(productCodes);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{code}")
	private ResponseEntity<?> doDetailProduct(@PathVariable String code) {
		ProductCode productCode = productService.selectProductCode(code);
		
		if(productCode!=null) {
			return ResponseEntity.ok(productCode);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	@GetMapping("/info")
	private ResponseEntity<List<ProductCode>> doListProductsWithInfo(@RequestParam(defaultValue="") String subCode) {
		List<ProductCode> productCodes = productService.selectProductCodesWithInfo(subCode);
		
		if(productCodes!=null) {
			return ResponseEntity.ok(productCodes);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/info/{code}")
	private ResponseEntity<?> doDetailProductWithInfo(@PathVariable String code) {
		ProductCode productCode = productService.selectProductCodeWithInfo(code);
		
		if(productCode!=null) {
			return ResponseEntity.ok(productCode);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping(consumes= {"multipart/form-data"})
	private ResponseEntity<?> doRegistProduct(@RequestPart("product") ProductCode productCode,@RequestPart(value="upfile",required=false) MultipartFile file,@RequestPart("subCode") String subCode) throws IllegalStateException, IOException {
		
		
		System.out.println(productCode);
		System.out.println(subCode);
		Map<String,Object> condition = new HashMap<>();
		condition.put("productCode",productCode);
		condition.put("subCode",subCode);
		boolean res = productService.registProductCode(condition);

				
		if(res) {
			if(file!=null) {
				String originalName = file.getOriginalFilename();
				String newFileName = "product_"+productCode.getProductCode()+"."+originalName.substring(originalName.lastIndexOf(".") + 1);
				
				File upfile = new File(folderPath + newFileName);
				
				file.transferTo(upfile);
				
				productCode.setProductCode(productCode.getProductCode());
				productCode.setThumbnail(newFileName);
				productService.modifyProductCode(productCode);
			}
			

			return ResponseEntity.created(URI.create("/api/products/"+productCode.getProductCode())).body(productCode.getProductCode());
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping(consumes = {"multipart/form-data"})
	private ResponseEntity<?> doModifyProduct(@RequestPart("product") ProductCode productCode,@RequestPart(value="upfile",required=false) MultipartFile file) throws IllegalStateException, IOException {
		ProductCode target = productService.selectProductCode(productCode.getProductCode());
		
		if(target!=null) {
			String oldFileName = target.getThumbnail();
			String oldFilePath = folderPath + oldFileName;
			
			File oldFile = new File(oldFilePath);
			
			if(oldFile.exists()) {
				oldFile.delete();
				productCode.setThumbnail("");
			}
			
			if(file!=null) {
				String originFileName = file.getOriginalFilename();
				String newFileName = "product_"+productCode.getProductCode()+"."+originFileName.substring(originFileName.lastIndexOf(".")+1);
				File upfile = new File(folderPath+newFileName);
				
				file.transferTo(upfile);
				
				productCode.setThumbnail(newFileName);
			}
			boolean res = productService.modifyProductCode(productCode);
			
			if(res) {
				return ResponseEntity.ok(productCode);
			}
			else {
				return ResponseEntity.internalServerError().build();
			}			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{code}")
	private ResponseEntity<?> doRemoveProduct(@PathVariable String code) {
		ProductCode target = productService.selectProductCode(code);
		
		if(target!=null) {
			String oldFileName = target.getThumbnail();
			String oldFilePath = folderPath + oldFileName;
			
			File oldFile = new File(oldFilePath);
			
			if(oldFile.exists()) {
				oldFile.delete();
				target.setThumbnail("");
			}
			boolean res = productService.removeProductCode(code);
			if(res) {
				return ResponseEntity.noContent().build();									
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/search/validate")
	private JSONObject doValidateProductName(@RequestBody JSONObject job) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		
		JSONObject req = (JSONObject) jsonParser.parse(job.toString());
		JSONObject inputVal = (JSONObject)req.get("value");
		String targetName = (String)inputVal.get("resolved");
		
		
		List<ProductInfo> productInfos = productService.selectProductInfosByName(targetName).stream().filter(info->info.isSale()).collect(Collectors.toList());
		
		JSONObject response = new JSONObject();
		if(productInfos == null || productInfos.size()==0) {
			response.put("status","FAIL");
			response.put("value",0);
			response.put("message","판매중인 제품이 없습니다.");			
		}
		else {
			response.put("status","SUCCESS");
//			response.put("value",productInfos.size());
			response.put("value", targetName);
			response.put("message","조회 성공");	
		}
		
		
		return response;
	}
	@GetMapping("/search/name/{target}")
	private ResponseEntity<List<ProductInfo>> doProductsByName(@PathVariable String target){
		List<ProductInfo> productInfos = productService.selectProductInfosByName(target);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@GetMapping("/search/name/{target}/info")
	private ResponseEntity<List<ProductInfo>> doProductsWithCategoryByName(@PathVariable String target){
		List<ProductInfo> productInfos = productService.selectProductInfosWithCategoryByName(target);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping({"/search/price","/search/price/{lower}","/search/price/{lower}/{upper}"})
	private ResponseEntity doProductsByPrice(@PathVariable Optional<Integer> lower, @PathVariable Optional<Integer> upper) {
		Map<String,Integer> opts = new HashMap<>();
		
		System.out.println(lower+" "+upper);
		
		if(lower.isPresent()) {
			opts.put("lower",lower.get());			
		}
		if(upper.isPresent()) {
			opts.put("upper",upper.get());	
		}
		
		List<ProductInfo> productInfos = productService.selectProductInfosByPrice(opts);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping({"/search/price/info","/search/price/{lower}/info","/search/price/{lower}/{upper}/info"})
	private ResponseEntity doProductsWithCategoryByPrice(@PathVariable Optional<Integer> lower, @PathVariable Optional<Integer> upper,@RequestParam int pgNo) {
		Map<String,Integer> opts = new HashMap<>();
		
		System.out.println(lower+" "+upper);
		
		if(lower.isPresent()) {
			opts.put("lower",lower.get());			
		}
		if(upper.isPresent()) {
			opts.put("upper",upper.get());	
		}
		
		List<ProductInfo> productInfos = productService.selectProductInfosWithCategoryByPrice(opts,pgNo);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/search/level/{order}")
	private ResponseEntity doProductsByLevel(@PathVariable int order) {
		List<ProductInfo> productInfos = productService.selectProductInfosByLevel(order);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/search/level/{order}/info")
	private ResponseEntity doProductsWithCategoryByLevel(@PathVariable int order,@RequestParam int pgNo) {
		
		List<ProductInfo> productInfos = productService.selectProductInfosWithCategoryByLevel(order,pgNo);
		
		if(productInfos!=null) {
			return ResponseEntity.ok(productInfos);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}	
	
	@GetMapping("/{code}/info")
	private ResponseEntity doDetailProductInfo(@PathVariable String code) {
		ProductInfo productInfo = productService.selectProductInfo(code);
		
		if(productInfo!=null) {
			return ResponseEntity.ok(productInfo);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{code}/info/category")
	private ResponseEntity doDetailProductInfoWithCategory(@PathVariable String code) {
		ProductInfo productInfo = productService.selectProductInfoWithCategory(code);
		
		if(productInfo!=null) {
			return ResponseEntity.ok(productInfo);
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@Transactional
	@PostMapping("/info")
	private ResponseEntity doRegistProductInfo(@RequestBody ProductInfo productInfo) {
		boolean res = productService.registProductInfo(productInfo);
		
		if(res) {
			String subCode = productInfo.getProductCode().substring(0,3);
			productService.updateCountPlus(subCode);
			return ResponseEntity.created(URI.create("/api/products/"+productInfo.getProductCode()+"/info")).build();
		}
		else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/info")
	private ResponseEntity doModifyProductInfo(@RequestBody ProductInfo productInfo) {
		
		ProductInfo target = productService.selectProductInfo(productInfo.getProductCode());
		
		if(target!=null) {
			boolean res = productService.modifyProductInfo(productInfo);
			
			String subCode = productInfo.getProductCode().substring(0, 3);
			if(subCode!=null) {
				if(target.isSale() && !productInfo.isSale()) {
					productService.updateCountMinus(subCode);
				}
				else if(!target.isSale() && productInfo.isSale()) {
					productService.updateCountPlus(subCode);
				}
				if(res) {
					return ResponseEntity.ok(productInfo);			
				}
				else {
					return ResponseEntity.internalServerError().build();
				}
			}
			else {
				return ResponseEntity.notFound().build();
			}			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{code}/info")
	private ResponseEntity doRemoveProductInfo(@PathVariable String code) {
		ProductInfo target = productService.selectProductInfo(code);
		
		if(target!=null) {
			boolean res = productService.removeProductInfo(code);
			
			if(res) {
				String subCode = code.substring(0, 3);
				productService.updateCountMinus(subCode);
				return ResponseEntity.noContent().build();
			}
			else {
				return ResponseEntity.internalServerError().build();
			}			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
