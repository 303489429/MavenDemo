package com.mina2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟手机发短信
 * @author wangzhilong
 *
 */
public class TestClient03 {

	private static Logger log = LoggerFactory.getLogger(TestClient03.class);
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005 ;
	public static void main(String[] args) {
		//创建一个非阻塞的客户端程序
		IoConnector conn = new NioSocketConnector();
		//设置连接超时
		conn.setConnectTimeoutMillis(30000);
		//设置过滤器,默认提供的按行解释的类
		/*conn.getFilterChain().addLast(
				"codec", new ProtocolCodecFilter(new TextLineCodecFactory(
						Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));*/
		conn.getFilterChain().addLast("codec", 
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		//添加业务逻辑处理器类
		conn.setHandler(new Demo1ClientHandler());
		IoSession session = null ;
		try {
			//创建连接
			ConnectFuture future = conn.connect(new InetSocketAddress(HOST, PORT));
			future.awaitUninterruptibly(); //等待连接创建完成
			session = future.getSession(); //通过连接获取session
			PhoneMessageDto sendMsg = new PhoneMessageDto();
			sendMsg.setSendPhone("15928613520");
			sendMsg.setReceivePhone("13438082013");
			sendMsg.setMessage("测试发送短信，这个是短信息哦，当然长度是有限制的哦……");
			session.write(sendMsg); //发送给移动服务端
		} catch (Exception e) {
			log.error("客户端链接异常哦...",e);
		}
		session.getCloseFuture().awaitUninterruptibly() ; //等待连接断开
		conn.dispose();
	}

}
