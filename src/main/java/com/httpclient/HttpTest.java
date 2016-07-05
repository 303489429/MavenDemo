package com.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;


public class HttpTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		get();
		getTen();
	}
	
	private void getGame(){
		String url = "http://10.34.43.50/appweb/lottery/generateGameLotteryPrize.html?uuid=1001&idcard=510183198807045879&snbid=jswx-196&channel=2&sign=2ee05a0a9ebd0aa56d16a9a66c76682e" ;
		
	}

	private static void post() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//创建默认的httpclient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建httppost
		HttpPost httppost = new HttpPost("http://localhost:8080/tenPayCallBack.html");
		//创建参数队列
		List<NameValuePair> fromparams = new ArrayList<NameValuePair>();
//		Map<String, Object> body = new HashMap<String, Object>();
//		body.put("sign", "FFAJHSJJIWM00128");
//		String password = MD5Test.getMD5("123456");
//		body.put("password", password);
		fromparams.add(new BasicNameValuePair("total_fee", "887"));
		fromparams.add(new BasicNameValuePair("notify_id", "8895425581"));
		fromparams.add(new BasicNameValuePair("partner", "9999999"));
		fromparams.add(new BasicNameValuePair("trade_state", "2"));
		fromparams.add(new BasicNameValuePair("pay_info", "15123455665734"));
		fromparams.add(new BasicNameValuePair("out_trade_no", "77777"));
		fromparams.add(new BasicNameValuePair("input_charset", "UTF-8"));
		fromparams.add(new BasicNameValuePair("transaction_id", "cft12039839"));
		fromparams.add(new BasicNameValuePair("sign", "D4FE93AA9163FDF0002BEDD70F28D955"));
		UrlEncodedFormEntity uefEntity;
		uefEntity = new UrlEncodedFormEntity(fromparams, Charset.forName("UTF-8"));
		httppost.setEntity(uefEntity);
		System.out.println("executing request:"+httppost.getURI());
		try {
			CloseableHttpResponse response =httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				System.out.println("---------------------------------");
				System.out.println("response content:"+EntityUtils.toString(entity));
			}
			response.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void getTen() throws UnsupportedEncodingException{
		//创建默认的httpclient实例
		HttpClient httpclient = new DefaultHttpClient();
		StringBuffer sb = new StringBuffer("http://localhost/appweb/getTenPayLink.html");
//		StringBuffer sb = new StringBuffer("http://0013.96ni.net/getTenPayLink.html");
//		StringBuffer sb = new StringBuffer("http://172.30.236.114:8082/getTenPayLink.html");
//		StringBuffer sb = new StringBuffer("http://10.34.52.41:8082/getTenPayLink.html");
		//创建httpget
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("money", "1");
		paramMap.put("type", "1");
		paramMap.put("payType", "2");
//		paramMap.put("snbid", "jswx-196");
		paramMap.put("snbid", "sw1001");
//		paramMap.put("snbid", "wyg4002");  //吴勇刚测试网吧
//		paramMap.put("idCard", "510104199005243493");
//		paramMap.put("idCard", "622101199109040357");
		paramMap.put("idCard", "510183198807045879");
//		paramMap.put("userId", "1554");
		paramMap.put("userId", "69540622");
		paramMap.put("body", "手Q网费充值");
		paramMap.put("jsonp", "callback");
		for(String key : paramMap.keySet()){
			if(sb.toString().indexOf("?") == -1){
				sb.append("?");
			}else{
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			String value = paramMap.get(key);
			sb.append(value);
//			sb.append(URLEncoder.encode(value, "UTF-8"));
		}
		System.out.println("getURL:"+sb.toString());
		HttpGet httpget = new HttpGet(sb.toString());
		//执行get请求
		try {
			HttpResponse response = httpclient.execute(httpget);
			//获取响应实体
			HttpEntity entity = response.getEntity();
			//打印响应状态
			System.out.println(response.getStatusLine());
			if(entity != null){
				System.out.println("response content length:" +entity.getContentLength());
				//打印响应内容
				System.out.println("response content:"+EntityUtils.toString(entity,"UTF-8"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			httpclient.getConnectionManager().shutdown();
		}
		
	}
	
	public static void get() throws UnsupportedEncodingException{
		//创建默认的httpclient实例
		HttpClient httpclient = new DefaultHttpClient();
//		StringBuffer sb = new StringBuffer("http://0013.96ni.net/getTenPayLink.html");
		StringBuffer sb = new StringBuffer("http://localhost:8080/appweb/getTenPayH5Link.html");
		//创建httpget
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("money", "1");
		paramMap.put("snbid", "sw1001");
//		paramMap.put("snbid", "jswx-196");
//		paramMap.put("idCard", "510104199005243493");
//		paramMap.put("idCard", "211223198509150258");
		paramMap.put("idCard", "510183198807045879");
		paramMap.put("userId", "34059980");
		paramMap.put("deviceType", "2");
		paramMap.put("callback_name", "backfn");
		paramMap.put("body", "手Q网费充值");
//		paramMap.put("callback", "callback");
		for(String key : paramMap.keySet()){
			if(sb.toString().indexOf("?") == -1){
				sb.append("?");
			}else{
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			String value = paramMap.get(key);
			sb.append(value);
//			sb.append(URLEncoder.encode(value, "UTF-8"));
		}
		System.out.println("getURL:"+sb.toString());
		HttpGet httpget = new HttpGet(sb.toString());
		//执行get请求
		try {
			HttpResponse response = httpclient.execute(httpget);
			//获取响应实体
			HttpEntity entity = response.getEntity();
			//打印响应状态
			System.out.println(response.getStatusLine());
			if(entity != null){
				System.out.println("response content length:" +entity.getContentLength());
				//打印响应内容
				System.out.println("response content:"+EntityUtils.toString(entity,"UTF-8"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			httpclient.getConnectionManager().shutdown();
		}
		
	}
	
	public static void get1(){
		//创建默认的httpclient实例
		HttpClient httpclient = new DefaultHttpClient();
		StringBuffer sb = new StringBuffer("http://localhost:8080/payCallBack.html");
		//创建httpget
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderNo", "2");
		paramMap.put("sign", "111");
		paramMap.put("disburseNo", "2");
		paramMap.put("cashBalance", "33333333");
		paramMap.put("disburseTime", "51018319872251101254");
		paramMap.put("body", "9998741");
		for(String key : paramMap.keySet()){
			if(sb.toString().indexOf("?") == -1){
				sb.append("?");
			}else{
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			sb.append(paramMap.get(key));
		}
		System.out.println("getURL:"+sb.toString());
		HttpGet httpget = new HttpGet(sb.toString());
		//执行get请求
		try {
			HttpResponse response = httpclient.execute(httpget);
			//获取响应实体
			HttpEntity entity = response.getEntity();
			//打印响应状态
			System.out.println(response.getStatusLine());
			if(entity != null){
				System.out.println("response content length:" +entity.getContentLength());
				//打印响应内容
				System.out.println("response content:"+EntityUtils.toString(entity,"UTF-8"));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			httpclient.getConnectionManager().shutdown();
		}
		
	}
	/**
	 * 
	 * @return
	 */
	public static Map<String , String> getCallBackParam(){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("orderNo", "2");
		paramMap.put("sign", "111");
		paramMap.put("disburseNo", "2");
		paramMap.put("cashBalance", "33333333");
		paramMap.put("disburseTime", "51018319872251101254");
		paramMap.put("body", "9998741");
		
		return paramMap ;
	}
	
	
	public static void get2(){
		//创建默认的httpclient实例
		HttpClient httpclient = new DefaultHttpClient();
//		StringBuffer sb = new StringBuffer("http://10.34.43.50:8080/appweb/getTwoCode.html?content=123");
		String sb = "http://172.30.1.130:8081/cxf/services/querySnbidStatus?source={\"from\":\"0013\"}&parameter={\"snbid\":\"AQXH-0462\"}";
//		StringBuffer sb = new StringBuffer("http://localhost:8080/tenPayHistory.html?pageIndex=0&pageSize=20&payType=2&userId=34060017&jsonp=callback");
		HttpGet httpget = new HttpGet(sb);
		//执行get请求
		try {
			HttpResponse response = httpclient.execute(httpget);
			//获取响应实体
			HttpEntity entity = response.getEntity();
			//打印响应状态
			System.out.println(response.getStatusLine());
			if(entity != null){
				System.out.println("response content length:" +entity.getContentLength());
				String htmlcode = EntityUtils.toString(entity,"UTF-8");
				System.out.println("response content:"+ htmlcode);
				//打印响应内容
//				getRegCode(htmlcode);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			httpclient.getConnectionManager().shutdown();
		}
		
	}
	
	public static String getRegCode(String htmlcode){
		
		String codeUrl = "" ;
		String a = "https://www.tenpay.com/app/wsm/gen_qrcode.cgi?random="+Math.random()+"&code=";
		Pattern p = Pattern.compile("https://wsk.qq.com.*?'") ; //
		Matcher m = p.matcher(htmlcode) ;
		while (m.find()) {
//			System.out.println(m.start());
//			System.out.println(m.end());
			codeUrl = m.group() ;
			String returnstr = codeUrl.substring(0,codeUrl.indexOf("'"));
			System.out.println(a+returnstr);
		}
//		int index = htmlcode.indexOf("")
		return codeUrl;
	}
	
	// 循环访问所有节点，输出包含关键字的值节点
	public static void extractKeyWordText(String url, String keyword) {
		try {
            //生成一个解析器对象，用网页的 url 作为参数
			Parser parser = new Parser(url);
			//设置网页的编码,这里只是请求了一个 gb2312 编码网页
			parser.setEncoding("UTF-8");
			//迭代所有节点, null 表示不使用 NodeFilter
			NodeList list = parser.parse(null);
            //从初始的节点列表跌倒所有的节点
			processNodeList(list, keyword);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	private static void processNodeList(NodeList list, String keyword) {
		//迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			//得到该节点的子节点列表
			NodeList childList = node.getChildren();
			//孩子节点为空，说明是值节点
			if (null == childList)
			{
				//得到值节点的值
				String result = node.toPlainTextString();
				//若包含关键字，则简单打印出来文本
				if (result.indexOf(keyword) != -1)
					System.out.println(result);
			} //end if
			//孩子节点不为空，继续迭代该孩子节点
			else 
			{
				processNodeList(childList, keyword);
			}//end else
		}//end wile
	}
	
	
	
}
