package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClient01 {

	private static Logger log = LoggerFactory.getLogger(MinaClient01.class);
	private static String HOST = "127.0.0.1";
	private static int PORT = 3005 ;
	
	public static void main(String[] args) {
		//����һ��������Ŀͻ��˳���
		IoConnector conn = new NioSocketConnector() ; //��������
		//�������ӳ�ʱʱ��
		conn.setConnectTimeoutMillis(30000);
		//��ӹ�����
		conn.getFilterChain().addLast( //�����Ϣ����
				"codec", 
				new ProtocolCodecFilter(new TextLineCodecFactory(
						Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));
		conn.setHandler( new MinaClient01Handler()); //���ҵ���߼���������
		IoSession session = null ;
		try {
			//��������
			ConnectFuture future = conn.connect(new InetSocketAddress(HOST, PORT));
			future.awaitUninterruptibly() ;  //�ȴ����Ӵ������
			session = future.getSession() ; //���session
			session.write("����ѧϰ��MINA");
		} catch (Exception e) {
			log.error("�ͻ��������쳣��", e);
		}
		session.getCloseFuture().awaitUninterruptibly() ; //�ȴ����ӶϿ�
		conn.dispose();
	}

}
