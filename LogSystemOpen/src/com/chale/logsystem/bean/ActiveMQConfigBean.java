package com.chale.logsystem.bean;

import java.io.Serializable;


public class ActiveMQConfigBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5487376485647139801L;

	private String code;
	
	private String queue;
	
	private Object data;

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
