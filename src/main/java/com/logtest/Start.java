package com.logtest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Start {
	private final static Properties prop ;
	static{
		prop = new Properties();
		InputStream in = Object.class.getResourceAsStream( "/wzl.properties" );  
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(prop.getProperty("timeout"));
//		new Start().getURL();
//		System.out.println();
		TestA a = new TestA();
		TestB b = new TestB();
		TestC c = new TestC();
		TestD d = new TestD();
	}
	
	public  String getURL(){
		URL url = this.getClass().getClassLoader().getResource("");
		System.out.println(url.getPath());
		return url.getPath();
	}


}
