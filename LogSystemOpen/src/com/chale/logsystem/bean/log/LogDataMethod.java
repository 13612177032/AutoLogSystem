package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

import com.chale.logsystem.bean.ModuleModel;

public class LogDataMethod  extends LogData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String path;
 	private Long beginTime;
	private String paramStringIn;
	private String paramStringOut;
	private String returnResult;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	 
	 
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public String getParamStringIn() {
		return paramStringIn;
	}
	public void setParamStringIn(String paramStringIn) {
		this.paramStringIn = paramStringIn;
	}
	public String getParamStringOut() {
		return paramStringOut;
	}
	public void setParamStringOut(String paramStringOut) {
		this.paramStringOut = paramStringOut;
	}
	public String getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	public LogDataMethod(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime, String path,
			String remark, Long beginTime,
			String paramStringIn, String paramStringOut, String returnResult) {
		super(threadCode, modules, keyword, recordTime, LogData.LOGTYPE_METHOD);
		this.path = path;
 		this.setRemark(remark);
		this.beginTime = beginTime;
		this.paramStringIn = paramStringIn;
		this.paramStringOut = paramStringOut;
		this.returnResult = returnResult;
	}
	
	
	

}
