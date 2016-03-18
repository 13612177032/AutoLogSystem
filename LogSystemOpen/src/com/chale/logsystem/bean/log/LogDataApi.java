package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

import com.chale.logsystem.bean.ModuleModel;

public class LogDataApi  extends LogData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String path;
	private String url;
 	private Long beginTime;
	private String paramString;
 	private String returnResult;
	 
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getParamString() {
		return paramString;
	}
	public void setParamString(String paramString) {
		this.paramString = paramString;
	}
	 
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	 
	 
	public String getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	public LogDataApi(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime, String url,String path,
			String remark, Long beginTime,
			String paramString,String returnResult) {
		super(threadCode, modules, keyword, recordTime, LogData.LOGTYPE_API);
		this.url = url;
		this.path = path;
 		this.setRemark(remark);
		this.beginTime = beginTime;
		this.paramString = paramString;
 		this.returnResult = returnResult;
	}
	
	
	

}
