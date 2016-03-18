package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

 import com.chale.logsystem.bean.ModuleModel;
 public class LogData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8853967646685045232L;
	/**
	 * 
	 */
 	protected static final String LOGTYPE_INTERFACE="INTERFACE"; 
	protected static final String LOGTYPE_METHOD="METHOD"; 
	protected static final String LOGTYPE_EXCEPTION="EXCEPTION"; 
	protected static final String LOGTYPE_COMMON="COMMON"; 
	protected static final String LOGTYPE_API="API"; 
	
	private String threadCode;
	private List<ModuleModel> modules;
	private String keyword;
	private Long recordTime;
	
	private String logType;
	
	private String remark;

	
	
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getThreadCode() {
		return threadCode;
	}
	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}
	public List<ModuleModel> getModules() {
		return modules;
	}
	public void setModules(List<ModuleModel> modules) {
		this.modules = modules;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Long recordTime) {
		this.recordTime = recordTime;
	}
	protected LogData(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime,String logType) {
		super();
		this.threadCode = threadCode;
		this.modules = modules;
		this.keyword = keyword;
		this.recordTime = recordTime;
		this.logType =logType;
	}
	protected LogData() {
		super();
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
