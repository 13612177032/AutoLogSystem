package com.chale.logsystem.mq;

import org.apache.log4j.Logger;

import com.chale.logsystem.util.PropertiesUtil;
import com.chale.logsystem.bean.ActiveMQConfigBean;
import com.chale.logsystem.bean.log.LogData;
import com.chale.logsystem.bean.log.LogDataInterface;
import com.chale.logsystem.bean.log.LogRecord;

public class LMMSActiveMQConfigUtil {
	private static final Logger logger = Logger.getLogger(LMMSActiveMQConfigUtil.class);
	private static final ActiveMqService activeMqService = new ActiveMqService();
	
	/**
	 * QUEUENAME  定义消息队列
	 * */	
	private static final String QUEUENAME=PropertiesUtil.getProperties("queuename");
	
	 //区分正式还是测试
	public static final String USE_TYPE = PropertiesUtil.getProperties("use_type"); 
	
	 
	/**
	 * 默认
	 * @param data
	 */
	public static void sendMQMsg(Object data){
		try{
			ActiveMQConfigBean bean = new ActiveMQConfigBean();
			bean.setData(data);
			bean.setQueue(QUEUENAME);
			bean.setCode(USE_TYPE);
			activeMqService.sendActiveMqMsg(bean);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("sendMQMsg error:"+e.getMessage());
		}
	}
}
