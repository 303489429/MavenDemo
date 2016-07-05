package com.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServerHandler extends IoHandlerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(MinaServerHandler.class);
	@Override
    public void sessionCreated(IoSession session) throws Exception {
		log.info("�������ͻ��˴������ӡ���");
    }
	@Override
    public void sessionOpened(IoSession session) throws Exception {
		log.info("�������ͻ������Ӵ�");
    }
	@Override
    public void sessionClosed(IoSession session) throws Exception {
       log.info("�Ự�ر�");
    }
	@Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.info("����˽������״̬");
    }
	@Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        if (log.isWarnEnabled()) {
        	log.warn("����˷����쳣�� " + getClass().getName()
                    + ".exceptionCaught() for proper handling: {}", cause);
        }
    }
	@Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();
        log.info("����˽��յ������Ϊ��"+msg);
        if("bye".equals(msg)){
//        	session.close();
        	session.close(true);
        }
        Date date = new Date();
        session.write(date);
    }
	@Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.info("����˷�����Ϣ�ɹ�����");
        session.close(true) ; //������Ϣ�������Ͽ���ͻ��˵�����  �����͵Ķ����� ��ʽ��
    }
	
}
