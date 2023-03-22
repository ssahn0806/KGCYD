package com.kgc.chatbot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class FileController {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${FILE_PATH}")
	String folderPath;
	
	@GetMapping("/download")
	private ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName) throws FileNotFoundException {
		String path = folderPath + fileName;
			
		File file = new File(path);
		
		if(file.exists()) {
			String imageName = file.getName();
			String ext = fileName.substring(imageName.lastIndexOf(".")+1);
			
			HttpHeaders header = new HttpHeaders();
			
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+imageName);
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        header.add("Pragma", "no-cache");
	        header.add("Expires", "0");
	        
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().headers(header).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
			
		}
		else {
			return null;			
		}
	}
	
//	@GetMapping("/view/{fileName}")
//	private ResponseEntity<Resource> doGetImageByCode(@PathVariable String fileName){
//		try {
//			FileSystemResource resource = new FileSystemResource(folderPath+fileName);
//			
//			HttpHeaders header = new HttpHeaders();
//			Path filePath = null;
//			filePath = Paths.get(folderPath+fileName);
//
//			header.add("Content-Type",MediaType.IMAGE_JPEG_VALUE);
//			return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
//		}
//		catch(Exception e) {
////			e.printStackTrace();
//		}
//		return new ResponseEntity<Resource>(null,null,HttpStatus.NOT_FOUND);
//	}
	@GetMapping(value= {"/view/{fileName}","/view/"})
	private ResponseEntity<?> doGetImageByCode(@PathVariable(required=false) String fileName){
		
		File file = new File(folderPath+fileName);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type",MediaType.IMAGE_JPEG_VALUE);
			
			return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		}
		catch(IOException e) {
			FileSystemResource resource = new FileSystemResource(folderPath+"product_notfound.png");
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type",MediaType.IMAGE_JPEG_VALUE);
			return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
		}
	}
	
	@GetMapping("/viewer/{pdfName}")
	private ResponseEntity<?> doPreviewPdf(@PathVariable String pdfName){
		try {
			
			System.out.println(pdfName);
			Resource resource = resourceLoader
					.getResource("file:"+folderPath + pdfName); 
			File pdf = resource.getFile();
			
			System.out.println(pdf);
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+ pdf.getName() + "\"")
					.header(HttpHeaders.CONTENT_LENGTH,String.valueOf(pdf.length()))
					.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_PDF.toString())
					.body(resource);
					
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
}
