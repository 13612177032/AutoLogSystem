package com.chale.logsystem.util;

import java.util.HashMap;
import java.util.Map;

import com.chale.logsystem.bean.ModuleInterface;

public class ThreadMessage{
	private String code;
	private String url;
	private Long beginTime;
	private boolean isLog;
	private ModuleInterface module;
	
	private Map<String,Object> message;
	public String getCode() {
		return code;
	}
	public void clear() {
		// TODO Auto-generated method stub
		message=null;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public ThreadMessage(String code) {
		super();
		this.code = code;
	}
	public Map<String, Object> getMessage() {
		return message;
	}
	public Object get(String key) {
		if (message==null) {
			return null;
		}
		return message.get(key);
	}
	
	public void put(String key,Object o) {
		 getMessageNotNull().put(key, o);
 	}
	public Map<String, Object> getMessageNotNull() {
		message=(message==null?new HashMap<String, Object>():message);
		return message;
	}
	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}
	public boolean isLog() {
		return isLog;
	}
	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}
	public ModuleInterface getModule() {
		return module;
	}
	public void setModule(ModuleInterface module) {
		this.module = module;
	}
	
	
	
	
}

