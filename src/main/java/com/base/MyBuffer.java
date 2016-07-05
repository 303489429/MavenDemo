package com.base;

import com.base.MyBuffer.NextMyBuffer;

public interface MyBuffer {
	
	void get(int a ,int b) ;
	void inti() ;
	
	public interface NextMyBuffer{
		void sendA();
		void sendB();
	}
	
}

interface OtherBuffer extends MyBuffer {
	
}

class Wzl implements OtherBuffer{

	public void get(int a, int b) {
		// TODO Auto-generated method stub
		
	}

	public void inti() {
		// TODO Auto-generated method stub
		
	}

	
	
}


