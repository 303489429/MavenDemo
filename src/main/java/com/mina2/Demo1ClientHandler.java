package com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1ClientHandler extends IoHandlerAdapter{

	   private static final Logger log = LoggerFactory.getLogger(IoHandlerAdapter.class);

	    public void sessionCreated(IoSession session) throws Exception {
	        // Empty handler
	    }

	    public void sessionOpened(IoSession session) throws Exception {
	        // Empty handler
	    }

	    public void sessionClosed(IoSession session) throws Exception {
	        // Empty handler
	    }

	    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	        // Empty handler
	    }

	    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	        log.error("客户端发生异常……", cause);
	    }

	    public void messageReceived(IoSession session, Object message) throws Exception {
	        String msg = message.toString();
	        log.info("客户端接收到的信息为：{}", msg);
	    }

	    public void messageSent(IoSession session, Object message) throws Exception {
	        // Empty handler
	    }
}
