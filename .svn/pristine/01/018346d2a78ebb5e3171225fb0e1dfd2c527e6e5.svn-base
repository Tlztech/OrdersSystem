package com.rakuten.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.rakuten.r1001.action.GetOrderExpection;

public class GetOrderExpectionListener implements ServletContextListener {
	private Timer iTimer = null;

	private GetOrderExpection myTask = null;

	public void contextDestroyed(ServletContextEvent event) {

		// 在这里关闭监听器，所以在这里销毁定时器。

		iTimer.cancel();

		event.getServletContext().log("定时器已经销毁！");

	}

	public void contextInitialized(ServletContextEvent event) {

		// 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能

		event.getServletContext().log("定时器已经启动！");

		iTimer = new Timer(true);

		myTask = new GetOrderExpection();

		try {
			// 设置任务计划，启动和间隔时间(每隔24(24*60*60*1000)小时触发一次，即凌晨0点)

			iTimer.schedule(myTask, 0, 5 * 60 * 1000);

		} catch (Exception e) {

			System.out.println("异常信息如下：" + e.getMessage());

			e.printStackTrace();

		}

	}

}
