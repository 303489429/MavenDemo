package com.apns;

import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

public class Ios {
	 public static void main(String[] args) throws Exception
	    {
	        String deviceToken = "f27bfd2c641037f512c211cef17112e6c3b188ce399b7a08a15c548344f38260";
	        String alert = "诶你惨了随时给你发垃圾信息的所得税的方式测试的三大事大四的赛萨迪萨的撒大四的是否的房间阿迪萨斯发生地方的发货的方式的废话是煎熬的按实际的内部";//push的内容
	        System.out.println(alert.getBytes().length);
	        System.out.println(deviceToken.getBytes().length);
	        int badge = 3;//图标小红圈的数值
	        String sound = "default";//铃音

	        List<String> tokens = new ArrayList<String>();
	        tokens.add(deviceToken);
	        String certificatePath = "D:/push/p12/netCafe_development.p12";
	        String certificatePassword = "123456";//此处注意导出的证书密码不能为空因为空密码会报错
	        boolean sendCount = true;

	        try
	        {
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert(alert); // 消息内容
//	            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
	           // payLoad.addCustomAlertBody("噢嘿嘿~捏嘻嘻~哇咔咔咔~");
	          
//	            if (sound != null)
//	            {
//	                payLoad.addSound(sound);//铃音
//	            }
	            PushNotificationManager pushManager = new PushNotificationManager();
	            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	            // 发送push消息
	            if (sendCount)
	            {
	                Device device = new BasicDevice();
	                device.setToken(tokens.get(0));
	                System.out.println("推送消息: " + device.getToken()+"\n"+payLoad.toString() +""+payLoad.toString().length());
	                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
	                notifications.add(notification);
	            }
	            else
	            {
	                List<Device> device = new ArrayList<Device>();
	                for (String token : tokens)
	                {
	                    device.add(new BasicDevice(token));
	                }
	                notifications = pushManager.sendNotifications(payLoad, device);
	                System.out.println("1:"+notifications);
	            }
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	            int failed = failedNotifications.size();
	            int successful = successfulNotifications.size();
	            System.out.println("失败信息："+failedNotifications);
	            System.out.println("成功信息："+successfulNotifications);
	            pushManager.stopConnection();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
}
