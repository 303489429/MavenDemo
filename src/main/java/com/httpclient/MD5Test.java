package com.httpclient;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
	
	public final static char[] HEX = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	} ; 
	
	public static char[] toHex(byte[] bytes){
		int num = bytes.length ;
		char[] result = new char[2*num] ;
		int j=0 ;
		for (int i = 0; i < num; i++) {
			// Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4 ];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
		}
		return result;
	}
	
	public static String getMD5(String rawString) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(rawString.getBytes("utf-8"));
		return new String(toHex(md5.digest()));
	}
	
}
