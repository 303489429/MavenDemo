package com.base;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Question {
	byte[] array1,array2[] ;
	byte array3[][];
	byte[][] array4;
	public static void main(String[] args) {
		Question q = new Question() ;
//		q.array2=q.array1 ;
//		q.array2=q.array3;
//		q.array2=q.array4 ;
		
//		question2();
		
//		qusetion5();
		testFor();
	}
	
	public static void testFor(){
		long st = System.currentTimeMillis() ;
		long k = 0 ;
		for (int i = 0; i < 1000000000; i++) {
			for (int j = 0; j < 10; j++) {
				k++ ;
			}
		}
		long et = System.currentTimeMillis();
		System.out.println("speet time ="+(et-st)+"ms,k="+k);
	}
	
	
	static A a1,a2,a3 ;
	public static void question2(){
		if((a1 = new D()) instanceof C && (a2=new B()) instanceof A)
			System.out.println("1");
		if(a2 instanceof B || (a3 = new C()) instanceof A)
			System.out.println("2");
		if(a3 instanceof B && (a2 = new D()) instanceof A)
			System.out.println("3");
		if(a2 instanceof C || (a2=new D()) instanceof C)
			System.out.println("4");
		
		System.out.println("12345".valueOf(54321));
	}
	
	public static void qusetion5(){
		int two[][] = new int[4][5];
		int i,j,k=0;
		for (i = 0; i < 4; i++) {
			for(j=0; j<5; j++){
				two[i][j] = k ;
				k++ ;
			}
		}
		for (i = 0; i < 4; i++) {
			for(j=0; j<5; j++)
				System.out.print(two[i][j]+" ");
			System.out.println();
		}
		
	}
	
	public void test(){
		Map<String, String> a = new HashMap<String, String>();
		a.put(null, null);
		
		TreeMap<String,String> tree = new TreeMap<String, String>();
		tree.put(null, null) ;
		
	}

}

class A{}
class B extends A {} ;
class C extends B {} ;
class D extends A {} ;
