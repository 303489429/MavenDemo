package com.base;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

public class TestTask implements ServletContextListener {

	private Timer timer = null;

	public void contextInitialized(ServletContextEvent event) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 3); // get 和 set 的字段数字，指示一天中的小时。
		calendar.set(Calendar.MINUTE, 0); // get 和 set 的字段数字，指示一小时中的分钟。
		calendar.set(Calendar.SECOND, 0); // get 和 set 的字段数字，指示一分钟中的秒。
		Date time = calendar.getTime();
		System.out.println("执行时间：" + time); // 创建一个新计时器，可以指定其相关的线程作为守护程序运行。如果计时器将用于安排重复的“维护活动”，则调用守护线程，在应用程序运行期间必须调用守护线程，但是该操作不应延长程序的生命周期。
		timer = new Timer(true);// 创建一个新计时器，可以指定其相关的线程作为守护程序运行。 //设置任务计划，启动和间隔时间
		timer.schedule(new ContractTask(), 0, 2000);// 安排在指定的时间执行指定的任务。执行任务前的延迟时间，单位是毫秒。
	}

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();// 终止此计时器，丢弃所有当前已安排的任务。 }
		
	}
	class ContractTask extends TimerTask {
		public void run() {
			System.out.println("--------------------------------开始执行合同档期定时任务！！----------------------------------------------");

		}

	}
	
	public static void main(String[] args) {
		new TestTask().contextInitialized(null);
	}
}

