package receive;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.chale.logsystem.bean.ActiveMQConfigBean;
import com.chale.logsystem.util.PropertiesUtil;

 

public class ActiveMqConfigReceiver {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	

 
	public static void main(String[] args) {
		new MqReceiverThread().start();;
	}

	 
 
	static class MqReceiverThread extends Thread{
		
		private static final String MQ_URL = PropertiesUtil.getProperties("url");
		private static final String MQ_USER = PropertiesUtil.getProperties("user",ActiveMQConnection.DEFAULT_USER);
		private static final String MQ_PASSWORD = PropertiesUtil.getProperties("password",ActiveMQConnection.DEFAULT_PASSWORD);
		 
		private static final String QUEUENAME=PropertiesUtil.getProperties("queuename");

		// ConnectionFactory 锛氳繛鎺ュ伐鍘傦紝JMS 鐢ㄥ畠鍒涘缓杩炴帴 , 鏋勯�燙onnectionFactory瀹炰緥瀵硅薄锛屾澶勯噰鐢ˋctiveMq鐨勫疄鐜癹ar
		private  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				MQ_USER,
				MQ_PASSWORD,
	            MQ_URL);
		
		public void run(){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receiveAndExecute();
		}
		
		
		public void receiveAndExecute() {
			   
		    
		    // Connection 锛欽MS 瀹㈡埛绔埌JMS Provider 鐨勮繛鎺�
		    Connection connection = null;
		    // Session锛� 涓�涓彂閫佹垨鎺ユ敹娑堟伅鐨勭嚎绋�
		    Session session;
		    // Destination 锛氭秷鎭殑鐩殑鍦�;娑堟伅鍙戦�佺粰璋�.
		    Destination destination;
		    // 娑堣垂鑰咃紝娑堟伅鎺ユ敹鑰�
		    MessageConsumer consumer;
		    
		    try {
		        // 鏋勯�犱粠宸ュ巶寰楀埌杩炴帴瀵硅薄
		        connection = connectionFactory.createConnection();
		        // 鍚姩
		        connection.start();
		        // 鑾峰彇鎿嶄綔杩炴帴
		        session = connection.createSession(Boolean.FALSE,
		                Session.AUTO_ACKNOWLEDGE);
		        // 鑾峰彇session娉ㄦ剰鍙傛暟鍊紉ingbo.xu-queue鏄竴涓湇鍔″櫒鐨剄ueue锛岄』鍦ㄥ湪ActiveMq鐨刢onsole閰嶇疆
		        destination = session.createQueue(QUEUENAME);
		        consumer = session.createConsumer(destination);
		        while (true) {
		            //璁剧疆鎺ユ敹鑰呮帴鏀舵秷鎭殑鏃堕棿锛屼负浜嗕究浜庢祴璇曪紝杩欓噷璋佸畾涓�100s
		        	Object obj = consumer.receive();
		         
		        	if(obj instanceof ObjectMessage){
		        		ObjectMessage message = (ObjectMessage) obj;
		                if (null != message) {
		                	try {
								ActiveMQConfigBean bean = (ActiveMQConfigBean)message.getObject();
								 if(bean.getCode()!=null&&!bean.getCode().equals("")){
								 
									 ActiveMqReceiver receiver = new ActiveMqReceiver();
									  
									 receiver.process(bean);
								 }
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                }  
		        		
		        	}
		          
		        }
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
	
	
	
}
