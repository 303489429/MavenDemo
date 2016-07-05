package com.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServer {
	
	private static Logger log = LoggerFactory.getLogger(MinaServer.class);
	
	private static int PORT = 3005 ;
	
	public static void main(String[] args) {
		IoAcceptor acceptor = null ;
		try {
			//����һ���������server�˶�Socket
			acceptor = new NioSocketAcceptor(); 
			//���ù�������ʹ��mina�ṩ���ı����з���������
			acceptor.getFilterChain().addLast(
					"codec", 
					new ProtocolCodecFilter(new TextLineCodecFactory(
							Charset.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue()
							))
					);
			//���ö�ȡ��ݻ������С
			acceptor.getSessionConfig().setReadBufferSize(2048);
			//��дͨ��10�����޲������������״̬
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 100);
			//���߼�������
			acceptor.setHandler(new MinaServerHandler());
			acceptor.bind(new InetSocketAddress(PORT));
			log.info("����������ɹ������˿ںţ�"+PORT);
		} catch (Exception e) {
			log.error("����������쳣����", e);
		}
	}
	
}
