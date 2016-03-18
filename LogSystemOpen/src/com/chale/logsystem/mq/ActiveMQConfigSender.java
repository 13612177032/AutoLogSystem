package com.chale.logsystem.mq;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.chale.logsystem.util.PropertiesUtil;
import com.chale.logsystem.bean.ActiveMQConfigBean;

 

public class ActiveMQConfigSender {

	
	private static final String MQ_URL = PropertiesUtil.getProperties("url");
	private static final String MQ_USER = PropertiesUtil.getProperties("user",ActiveMQConnection.DEFAULT_USER);
	private static final String MQ_PASSWORD = PropertiesUtil.getProperties("password",ActiveMQConnection.DEFAULT_PASSWORD);
	 
  
	// ConnectionFactory ：连接工厂，JMS 用它创建连接 , 构�?�ConnectionFactory实例对象，此处采用ActiveMq的实现jar
	private static  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			MQ_USER,
			MQ_PASSWORD,
            MQ_URL);
	

	
	public static void mqConfig(ActiveMQConfigBean obj){
        
        // Connection ：JMS 客户端到JMS Provider 的连�?
        Connection connection = null;
        // Session�? �?个发送或接收消息的线�?
        Session session;
        // Destination ：消息的目的�?;消息发�?�给�?.
        Destination destination;
        // MessageProducer：消息发送�??
        MessageProducer producer;
        // TextMessage message;
        
        try {
            // 构�?�从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置 
            destination = session.createQueue(obj.getQueue());
            // 得到消息生成者�?�发送�?��??
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构�?�消息，此处写死，项目就是参数，或�?�方法获�?
            ObjectMessage message = session.createObjectMessage(obj);
            producer.send(message);
             session.commit();
            System.out.println("信息已经发�?�成�?====");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    
		
	} 
	
	 
}
