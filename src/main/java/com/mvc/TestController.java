//package com.mvc;
//
//import java.io.IOException;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/user")
//public class TestController {
//	@RequestMapping("/handle41")
//	public String handle41(@RequestBody String requestBody){
//		System.out.println(requestBody);
//		return "success";
//	}
//	@ResponseBody
//	@RequestMapping(value="/handle42{imageId}")
//	public byte[] handle42(@PathVariable("imageId") String imageId){
//		System.out.println("imageId:"+imageId);
//		Resource res = new ClassPathResource("image.jpg");
//		byte[] fileData = null;
//		try {
//			fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return fileData ;
//	}
//}
