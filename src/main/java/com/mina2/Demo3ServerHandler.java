package com.mina2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3ServerHandler extends IoHandlerAdapter {
	private static final Logger log = LoggerFactory.getLogger(IoHandlerAdapter.class);

   
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("服务端发生异常……", cause);
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        String phoneMsg = message.toString();
        String[] msgs = phoneMsg.split(";");
        String sendPhone = msgs[0];
        String receivePhone  = msgs[1];
        String msg  = msgs[2];
        log.info("发送人手机号码：{}", sendPhone);
        log.info("接收人手机号码：{}", receivePhone);
        log.info("发送信息：{}",msg);
        session.write("发送成功！");
    }

    public void messageSent(IoSession session, Object message) throws Exception {
       session.close(true);
    }
}	
