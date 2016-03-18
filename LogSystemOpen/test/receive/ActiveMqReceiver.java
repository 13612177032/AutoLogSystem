package receive;

import com.alibaba.fastjson.JSONObject;
import com.chale.logsystem.bean.ActiveMQConfigBean;
  
public class ActiveMqReceiver {

	public void process(ActiveMQConfigBean bean) {
		// TODO Auto-generated method stub
 		System.out.println(JSONObject.toJSON(bean.getData()));
	}

}
