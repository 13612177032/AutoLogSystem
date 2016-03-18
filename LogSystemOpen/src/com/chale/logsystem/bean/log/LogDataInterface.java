package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

import com.chale.logsystem.bean.ModuleModel;

public class LogDataInterface extends LogData implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	
	private String requestData;
	
	private String responseData;
	
	private Long requestTime;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public Long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}

	public LogDataInterface(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime, String url, String requestData,
			String responseData, Long requestTime) {
		super(threadCode, modules, keyword, recordTime,LogData.LOGTYPE_INTERFACE);
		this.url = url;
		this.requestData = requestData;
		this.responseData = responseData;
		this.requestTime = requestTime;
	}

	public LogDataInterface(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime) {
		super(threadCode, modules, keyword, recordTime,LogData.LOGTYPE_INTERFACE);
	}
	public LogDataInterface() {
		super(null, null, null, null,LogData.LOGTYPE_INTERFACE);
	}
	
	
	
	
}
