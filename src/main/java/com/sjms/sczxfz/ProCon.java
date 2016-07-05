package com.sjms.sczxfz;
/*
 * 生产者消费者问题其含义就是先生产出了产品，才能拉出去让消费者购买 
 *    1、多个线程数据共享区域化思想！
 *    2、生产者消费者 
 *     
 * 二、synchronized加锁: 
 *  
 */  
public class ProCon { //主方法
	
	public static void main(String[] args) {
		
	}
	
}

class SynStack{ // 此类是（本质上：共同访问的）共享数据区域 
	private String[] str = new String[10] ;
	private int index ;
	
	public synchronized void push(String sst){ //供生产者调用
		if(index == sst.length()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify(); //唤醒在此对象监视器上等待的单个线程
		str[index] = sst ;
		index ++ ;
	}
	
	public synchronized String pop(){ //供消费者调用
		if(index == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notify(); 
		index -- ;
		String product = str[index];
		return product ;
	}
	
	public String[] pro(){ //就是定义一个返回值为数组的方法,返回的是一个String[]引用  
		return str;  //这是一个String[]引用  
	}
}

class Producer implements Runnable{  //消费者
	private SynStack stack ;
	
	public Producer(SynStack stack){
		this.stack = stack ;
	}
	public void run() {
		for (int i = 0; i < stack.pro().length; i++) {
			String product = "产品"+i ;
			stack.push(product);
			System.out.println("生产了："+product);
			
		}
	}
	
}

