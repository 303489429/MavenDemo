package com.base;

interface Compare{
	
	int compare(int a , int b) ;
	
}

interface CompareItl extends Compare {
	
}


interface comparaB extends CompareItl , Compare{
	
}


class GTCompare implements Compare {
	public int compare(int a, int b) {
		if(a > b){
			return 1 ;
		}else if(a < b){
			return -1 ;
		}else{
			return 0;
		}
	}
}

class LTCompare implements Compare{
	public int compare(int a, int b) {
		if(a > b){
			return -1 ;
		}else if(a<b){
			return 1 ;
		}else{
			return 0;
		}
	}
}


/**
 * 策略模式实现方式 
 * @author wangzhilong
 * 2016年4月8日上午9:39:16
 */
public class Strategy {
	
	public static void sort(int[] array , Compare compare){  // 通过接口实现C++指针，俗称钩子  及java 策略模式
		if(array != null && array.length > 0){
			for (int i = 0; i < array.length; i++) {
				for (int j = i+1; j < array.length; j++) {
					if(compare.compare(array[i], array[j]) == 1){ //说明不想等
						int temp = array[i] ; //第一个值作为临时值
						array[i] = array[j] ;
						array[j] = temp ;
					}
				}
			}
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]);
				System.out.print(" ");
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {4,2,5,5,9,1,4} ;
		Compare compare = new GTCompare() ;
//		Compare compare = new LTCompare() ;
		sort(array, compare);
	}
	
}
