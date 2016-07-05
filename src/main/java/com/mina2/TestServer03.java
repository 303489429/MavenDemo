package com.mina2;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServer03 {
	private static Logger log = LoggerFactory.getLogger(TestServer03.class);
	private static int PORT = 3005 ; //服务器监控端口
	public static void main(String[] args) {
		IoAcceptor acceptor = null ; 
		try {
			//创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			//设置过滤器
			/*acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(
					Charset.forName("UTF-8"),
					LineDelimiter.WINDOWS.getValue(),
					LineDelimiter.WINDOWS.getValue())));*/
			//日志过滤器
			acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			//直接发送对象
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			
			//获取Iosession对象
			IoSessionConfig cfg = acceptor.getSessionConfig();
			//读写通道10秒内无操作进入空闲状态
			cfg.setIdleTime(IdleStatus.BOTH_IDLE, 100);
			//绑定逻辑处理器
			acceptor.setHandler(new DemoServerHandler());
			//绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
			log.info("服务端启动成功...端口号为：{}",PORT);
		} catch (Exception e) {
			log.error("服务端启动异常...");
			e.printStackTrace();
		}

	}

}
