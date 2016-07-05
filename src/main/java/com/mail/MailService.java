package com.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

public class MailService {
	private final static String MAIL_SMTP_HOST="smtp.163.com";
    private final static String MAIL_SMTP_PORT="465";//465端口是为SMTPS（SMTP-over-SSL）协议服务开放的
    private final static String MAIL_SENDER_MAIL="***@163.com";
    private final static String MAIL_SENDER_PASS="*****";
    private final static String MAIL_SENDER_NICKNAME="163邮件平台";
    private Logger logger = Logger.getLogger(MailService.class);

    public static void main(String[] args) {
      /*  MailService m = new MailService();
        List<String> recipients = new ArrayList<String>();
        recipients.add("wangzhilong@sicent.com");

        List<String> copyToRecipients = new ArrayList<String>();
        copyToRecipients.add("15928613520@qq.com");

        try {
            m.sendMail("title","content",recipients,copyToRecipients,null);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    	System.out.println("准备发送消息");
    	RunProject po = new RunProject("你好啊,我是邮件测试") ;
    	Thread t = new Thread(po, "我是线程1");
    	t.yield();
    	System.out.println("消息发送成功");
    }
    
    public static void sendMsg(String msg){
    	 List<String> recipients = new ArrayList<String>();
         recipients.add("wangzhilong@sicent.com");

         List<String> copyToRecipients = new ArrayList<String>();
         copyToRecipients.add("15928613520@qq.com");

         try {
             sendMail("title",msg,recipients,copyToRecipients,null);
         } catch (MessagingException e) {
             e.printStackTrace();
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
    }
    

    /**
     * 初始化Session
     * @return
     */
    private static Session getMailSession(){
        Properties props = new Properties();

        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_SENDER_MAIL, MAIL_SENDER_PASS);
            }
        });

        return session;
    }

    /**
     *
     * @param title 邮件标题
     * @param content  邮件内容
     * @param recipients 收件人邮箱列表
     * @param copyToRecipients 抄送人邮箱列表
     * @param secretCopyToRecipients 密送人邮箱列表
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static boolean sendMail(String title,String content,Collection<String> recipients,
                          Collection<String> copyToRecipients,Collection<String> secretCopyToRecipients) throws AddressException, MessagingException, UnsupportedEncodingException {
        // 初始化收件人、抄送、密送邮箱列表
        List<InternetAddress> toAddresses = parseStringToAddress(recipients);
        List<InternetAddress> ccAddresses = parseStringToAddress(copyToRecipients);
        List<InternetAddress> bccAddresses = parseStringToAddress(secretCopyToRecipients);
        //初始化邮件内容
        Message message = new MimeMessage(getMailSession());
        message.setFrom(new InternetAddress(MAIL_SENDER_MAIL, MAIL_SENDER_NICKNAME));
        String subject = MimeUtility.encodeWord(title, "UTF-8", "Q");//设置标题编码
        message.setSubject(subject);
        message.setContent(content, "text/html; charset=utf-8");

        // 收件人
        message.setRecipients(Message.RecipientType.TO, toAddresses.toArray(new InternetAddress[toAddresses.size()]));

        // 抄送
        message.setRecipients(Message.RecipientType.CC, ccAddresses.toArray(new InternetAddress[ccAddresses.size()]));

        // 密送
        message.setRecipients(Message.RecipientType.BCC, bccAddresses.toArray(new InternetAddress[bccAddresses.size()]));

        message.saveChanges();

        Transport.send(message);

        return true;//不报异常表示邮件发送成功
    }

    /**
     * 将字符串类型的邮箱地址转成InternetAddress类型的邮箱地址
     * @param mailStrings
     * @return List<InternetAddress>
     */
    private static List<InternetAddress> parseStringToAddress(Collection<String> mailStrings) throws AddressException {

        if(CollectionUtils.isEmpty(mailStrings)){
            return Collections.emptyList();
        }
        List<InternetAddress> addressList = new ArrayList<InternetAddress>();

        for(String mailString:mailStrings){
            InternetAddress internetAddress =  new InternetAddress(mailString);
            addressList.add(internetAddress);
        }
        return addressList;
    }
}

class RunProject implements Runnable{
	private String content ; 
	public RunProject(String content){
		this.content = content ;
	}
	public void run() {
		MailService.sendMsg(content);
	}
}
