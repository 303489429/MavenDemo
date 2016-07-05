package com.base;

import java.math.BigDecimal;


public strictfp class JavaBase {
	
	public static void main(String[] args) {
		float a = 1.02332323332f ; 
		double b = 0.3223232223d;
		System.out.println("a+b="+(a+b));
//		System.out.println("a+b="+Arithmetic.add(a, b));
		testUnPrecision();
		testUnPrecisionOK();
	}
	
	private  static void testUnPrecision() {
		System.out.println("--------Java自身的Double类型有精度损失----------");
		System.out.println(0.05+0.01); //0.060000000000000005
		System.out.println(1.0-0.54); //0.45999999999999996
		System.out.println(4.015*1000);//4014.9999999999995
		System.out.println(12.3/100);//0.12300000000000001
	}
	
	private  static void testUnPrecisionOK() {
		System.out.println("--------Java自身的Double类型有精度损失----------");
		System.out.println(add(0.05,0.01)); //0.060000000000000005
		System.out.println(add(1.0,0.54)); //0.45999999999999996
		System.out.println(add(4.015,1000));//4014.9999999999995
		System.out.println(add(12.3,100));//0.12300000000000001
	}
	
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
}
