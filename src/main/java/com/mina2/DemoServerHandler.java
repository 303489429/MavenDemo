package com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServerHandler extends IoHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(DemoServerHandler.class);
	 public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	        log.error("服务端发送异常...", cause);
	    }

	    public void messageReceived(IoSession session, Object message) throws Exception {
	        PhoneMessageDto phoneMsg = (PhoneMessageDto)message;
	        log.info("发送人手机号码：" + phoneMsg.getSendPhone());
	        log.info("接受人手机号码：" + phoneMsg.getReceivePhone());
	        log.info("发送信息：" + phoneMsg.getMessage());

			// 短信信息存入移动服务端数据库
			// ............

			session.write("发送成功！");

	    }

	    public void messageSent(IoSession session, Object message) throws Exception {
	        session.close(true);
	    }
}
