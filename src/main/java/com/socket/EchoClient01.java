package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClient01 {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String HOST = "127.0.0.1";
	private int PORT = 3015 ;
	private Socket socket ;
	public EchoClient01() throws IOException{
		this.socket = new Socket(HOST,PORT);
	}
	public void talk(){
		try {
			//获取服务端响应信息的输入流
			InputStream socketIn = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socketIn));
			//给服务端发送信息的输出流
			OutputStream socketOut = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(socketOut,true);
			BufferedReader localReader = new BufferedReader(
					new InputStreamReader(System.in));
			String msg = null ;
			while((msg = localReader.readLine()) != null){
				pw.println(msg);
				logger.info(br.readLine());
				if(msg.equals("bye")){
					break;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new EchoClient01().talk();

	}

}
