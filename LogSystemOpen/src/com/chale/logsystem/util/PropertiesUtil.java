package com.chale.logsystem.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取properties 工具�??
 * @author Jarven.wei
 *
 */
public class PropertiesUtil {
	
	private static final Logger log = Logger.getLogger(PropertiesUtil.class);
	
	private static Properties prop = new Properties();// 属�?�集合对�??   
	static{
		try{
			InputStream fis = null;
			fis =  ClassLoaderUtils.getResourceAsStream("lmms.properties", PropertiesUtil.class);
			 if(fis!=null){
				 prop.load(fis);// 将属性文件流装载到Properties对象�??   
			     fis.close();// 关闭�??   
			 }
		}catch (Exception e) {
			log.error("读取配置文件出错�??"+e);
		}
	}
	
	
	/**
	 * 根据key获取配置的�??
	 * @param key
	 * @return
	 */
	public static String getProperties(String key){
		if(key==null) return null;
		return prop.getProperty(key);
		
	}
	
	/**
	 * 根据key获取配置的�?�，若没有，则取传过来的默认的�??
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperties(String key,String defaultValue){
		if(key==null) return null;
		return prop.getProperty(key, defaultValue);
		
	}
	
	 
	
	
	
	 
}
