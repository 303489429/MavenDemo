package com.headfirst.adapter;

public class Hotel {
	//旅馆中有一个德标的插口
    private DBSocketInterface dbSocket;
    
    public Hotel(){
    	
    } 
    public Hotel(DBSocketInterface dbSocket){
    	this.dbSocket = dbSocket ;
    }
	public void setSocket(DBSocketInterface dbSocket) {
		this.dbSocket = dbSocket;
	}
    //旅馆中有一个充电的功能
	public void charge(){
		//使用德标接口充电
		dbSocket.powerWithTwoRound();
	}
 	
}