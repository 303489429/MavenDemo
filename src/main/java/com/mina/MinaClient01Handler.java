package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClient01Handler extends IoHandlerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(MinaClient01Handler.class);

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		logger.info("�ͻ��˽��յ�����ϢΪ��" + msg);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("�ͻ��˷����쳣...", cause);
	}

}
