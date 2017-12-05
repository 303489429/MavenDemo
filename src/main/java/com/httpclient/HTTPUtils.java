package com.httpclient;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HTTPUtils {
	private static Logger LOG = Logger.getLogger(HTTPUtils.class);

	private HTTPUtils() {
	}


	/**
	 * 发送HTTP_POST请求
	 *
	 * @see 若发送的<sendData>中含有中文,记得按照双方约定的字符集将中文
	 *      URLEncoder.encode(string,encodeCharset)
	 * @see 本方法默认的连接和读取超时均为30秒
	 * @param reqURL
	 *            请求地址
	 * @param sendData
	 *            发送到远程主机的正文数据
	 * @return
	 */
	public static String sendPostRequest(String reqURL, String sendData) {
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null; // 写
		InputStream in = null; // 读
		int responseCode = 0; // 远程主机响应的HTTP状态码
		try {
			URL sendUrl = new URL(reqURL);
			httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true); // 指示应用程序要将数据写入URL连接,其值默认为false
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setConnectTimeout(30000); // 30秒连接超时
			httpURLConnection.setReadTimeout(120000); // 30秒读取超时

			out = httpURLConnection.getOutputStream();
			String sendString = sendData.toString();
			LOG.debug(sendString);
			out.write(sendString.getBytes());
			out.flush(); // 清空缓冲区,发送数据

			// 使用httpURLConnection.getResponseMessage()可以获取到[HTTP/1.0 200
			// OK]中的[OK]
			responseCode = httpURLConnection.getResponseCode();

			// 处理返回结果
			BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String row = br.readLine();
			StringBuilder respData = new StringBuilder();
			while (row != null) {
				respData.append(row);
				row = br.readLine();
			}
			br.close();
			return respData.toString();
			// in = httpURLConnection.getInputStream();
			// byte[] dataBytes = new byte[in.available()];
			// in.read(dataBytes);
			// return new String(dataBytes);
		} catch (Exception e) {
			LOG.error(e);
			;
			e.printStackTrace();
			return responseCode + "`" + "Failed`";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.println("关闭输出流时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println("关闭输入流时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		}
	}

	/**
	 * 发起http get请求获取网页源代码
	 * 
	 * @param requestUrl
	 *            String 请求地址
	 * @return String 该地址返回的html字符串
	 */
	public static String sendGetRequest(String requestUrl) {
		StringBuffer buffer = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConn = null;

		try {
			// 建立get请求
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
			// 获取输入流
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			// 从输入流读取结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpUrlConn != null) {
				httpUrlConn.disconnect();
			}
		}
		return buffer.toString();
	}


}