package com.chale.logsystem.bean.log;

import java.io.Serializable;

public class LogRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String project;
	
	private String server;
	
	
	private LogData data;


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}


	public LogData getData() {
		return data;
	}


	public void setData(LogData data) {
		this.data = data;
	}


	public String getServer() {
		return server;
	}


	public void setServer(String server) {
		this.server = server;
	}


	public LogRecord(String project, String server, LogData data) {
		super();
		this.project = project;
		this.server = server;
		this.data = data;
	}


	
	
	
	

}
