package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 为每个客户端分配一个线程
 * 服务器的主线程负责接收客户端的连接
 * 每次接收到一个客户端连接，就会创建一个工作线程，由它负责与客户端的通信
 * @author wangzhilong
 * 缺点：大量的请求达到时，不断的创建线程，很容易耗尽系统资源造成服务器崩溃
 * 而且每个线程的创建与销毁都很浪费资源
 */
public class EchoServer02 {

private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int PORT = 3015;
	
	private ServerSocket serverSocket ; 
	
	public EchoServer02() throws IOException{
		//请求队列最大长度为5
		this.serverSocket = new ServerSocket(PORT);  //服务队列的大小
		logger.info("服务端启动... 端口号：" + PORT);
	}
	public void service(){
		while(true){
			Socket socket = null ;
			try {
				socket = this.serverSocket.accept();//请求到达
				Thread workThread = new Thread(new Server02Handler(socket));
				workThread.start(); //启动线程
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
	}
	public static void main(String[] args) {
		try {
			new EchoServer02().service();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class Server02Handler implements Runnable{
		private Socket socket ;
		
		public Server02Handler(Socket socket){
			this.socket = socket ;
		}
		public void run() {
			logger.info("一个新的请求达到并创建："+socket.getInetAddress()
					+":" +socket.getPort());
			try {
				InputStream socketIn = socket.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(socketIn));
				
				OutputStream socketOut = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(socketOut, true);
				
				String msg = null ; 
				while((msg = br.readLine()) != null){
					logger.info("服务器接收到的信息为："+ msg);
					pw.println(new Date());
					if(msg.equals("bye"))
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(socket != null){
						socket.close();
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			
		}
		
	}
	
}


