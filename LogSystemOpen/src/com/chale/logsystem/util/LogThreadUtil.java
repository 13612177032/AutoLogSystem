package com.chale.logsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chale.logsystem.bean.ModuleInterface;

public class LogThreadUtil {

	
        
    private final static ThreadLocal<ThreadMessage> serialNum = new ThreadLocal<ThreadMessage>() {  
        protected  ThreadMessage initialValue() {  
            return new ThreadMessage(getUnionCode());  
        }  
    };  
    
    
       
    public static ThreadMessage get(){
    	return serialNum.get();
    }
 
    
     
     
 	
 
    
	private static   String getUnionCode(){	
		return new SimpleDateFormat("yyyyMMddhhmmssms").format(new Date())+((int) (Math.random()*100000000));
	}
}

