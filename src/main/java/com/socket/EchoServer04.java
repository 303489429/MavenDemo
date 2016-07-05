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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer04 {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int PORT = 3015;
	
	private ServerSocket serverSocket ; 
	
	private ExecutorService executorService ; //java 自带的线程池
	
	private final int POOL_SIZE = 4 ; 
	
	public EchoServer04() throws IOException{
		serverSocket = new ServerSocket(PORT);
		//创建线程池
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() * POOL_SIZE);
		logger.info("服务启动...  端口号：" + PORT);
	}
	public void service(){
		while(true){
			Socket socket = null ;
			try {
				socket = serverSocket.accept() ;
				executorService.execute(new Server02Handler(socket));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		}
	}
	public static void main(String[] args) throws IOException {
		new EchoServer04().service();
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
