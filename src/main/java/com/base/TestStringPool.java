package com.base;

public class TestStringPool {
	
	public static void main(String[] args) {
		while(true){
			String a = String.valueOf(Math.random()).substring(2, 8);
			
			String b = "1234" ;
			if(b==a.intern()){
				System.out.println("break a==b");
				break ;
			}
			
		}
	}
	
}
