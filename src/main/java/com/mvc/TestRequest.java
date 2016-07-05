//package com.mvc;
//
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//public class TestRequest {
//
//	public void testHandle41(){
//		RestTemplate rt = new RestTemplate();
//		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
//		form.add("userName", "tom");
//		form.add("password", "123456");
//		form.add("age", "45");
//		rt.postForLocation("http://localhost:8080/mavenDemo/user/handle41.html", form);
//	}
//	
//	public static void main(String[] args) {
//
//	}
//	
//	
//
//}
