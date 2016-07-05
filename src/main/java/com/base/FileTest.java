package com.base;

import java.io.File;

public class FileTest {
	
	public static void main(String[] args) {
		File file = new File("D:\\008\\0013logs") ;
		if(!(file.exists())){
			System.out.println("file not exists");
			return ;
		}
		File[] list = file.listFiles() ;
		for (int i = 0; i < list.length; i++) {
			if(list[i].isDirectory()){
				System.out.println("目录："+list[i].getName());
			}else if(list[i].isFile()){
				System.out.println("文件："+list[i].getName());
			}else{
				System.out.println("其它："+list[i].getName());
			}
		}
	}
	
}
