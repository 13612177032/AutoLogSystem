package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

import com.chale.logsystem.bean.ModuleModel;
 
 
public class LogDataCommon  extends LogData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String LEVEL_INFO="info";
	public final static String LEVEL_DUBUG="debug";
	public final static String LEVEL_ERROR="error";
	public final static String LEVEL_WARN="warn";
	public final static String LEVEL_FETAL="fetal";
	
	private String path;
	private String data;
	private String level;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public LogDataCommon(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime,  String path,
			String data, String level,String remark) {
		super(threadCode, modules, keyword, recordTime, LogData.LOGTYPE_COMMON);
		this.path = path;
		this.data = data;
		this.level = level;
		this.setRemark(remark);
	}

	
	
	
}
