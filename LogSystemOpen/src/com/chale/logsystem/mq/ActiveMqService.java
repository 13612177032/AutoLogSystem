package com.chale.logsystem.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.chale.logsystem.util.PropertiesUtil;
import com.chale.logsystem.bean.ActiveMQConfigBean;

 

public class ActiveMqService implements InitializingBean, DisposableBean {

	
	

	
	private String maxThread = PropertiesUtil.getProperties("maxThread");
	private ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(maxThread));// �?大线程并发数
	
	
	
	@Override
	public void destroy() throws Exception {
		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sendActiveMqMsg(ActiveMQConfigBean bean){
		executor.submit(new SendMqMessageThread(bean));
	}
	
	private static class SendMqMessageThread implements Runnable {

		private ActiveMQConfigBean bean;
		
		public SendMqMessageThread(ActiveMQConfigBean bean){
		    this.bean = bean;	
		}
		
		@Override
		public void run() {
			ActiveMQConfigSender.mqConfig(bean);
		}
		
	}
	
}
